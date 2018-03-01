package com.jmm.drools.web;

import java.util.ArrayList;
import java.util.List;


import com.jmm.drools.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jmm.drools.common.DroolsConst;
import com.jmm.drools.common.exception.JsonResponse;
import com.jmm.drools.dao.RulesDao;
import com.jmm.drools.limit.AccessLimitService;
import com.jmm.drools.service.PromotionService;
import com.jmm.drools.service.RulesService;
import com.jmm.drools.vo.ApprovePromotionVo;
import com.jmm.drools.vo.ConditionVo;
import com.jmm.drools.vo.PromotionVo;
import com.jmm.drools.vo.SubPromotionEdit;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 促销管理
 */
@RequestMapping(value = "/promotion")
@RestController
public class PromotionController {

	@Autowired
	private RulesService rulesService;
	@Autowired
	private RulesDao rulesDao;
	@Autowired
	private PromotionService promotionService;


	private final static Logger logger = LoggerFactory.getLogger(PromotionController.class);

	@ApiOperation(value = "订单规则结果")
	@RequestMapping(value = "/orderResult", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public JsonResponse getOrderResult(@ApiParam @RequestBody Order order) {
		Order tempresult = rulesService.getProRulesWrite(order);
		int promotionId = tempresult.getPromotion();
		Promotion promtion = promotionService.getPromotion(promotionId);
		String bankId = order.getBankId();

		SubPromotion sp = promotionService.getSubPromotionForCharge(bankId, promotionId);
		OrderResult orderResult = new OrderResult();
		BeanUtils.copyProperties(tempresult, orderResult);
		BeanUtils.copyProperties(promtion, orderResult);
		// 增加中收
		orderResult.setPut_bankCharge(Float.parseFloat(sp.getPut_bankCharge()));
		return new JsonResponse(orderResult);
	}
	
	@ApiOperation(value = "订单规则结果")
	@RequestMapping(value = "/deductingInventory", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String deductingInventory(@ApiParam @RequestBody Inventory inventory) {
		return promotionService.deductingInventory(inventory);
	}

	@ApiOperation(value = "新增促销活动")
	@RequestMapping(value = "/addPromotion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public JsonResponse ruleAddPromotion(@ApiParam @RequestBody PromotionVo promotionVo) {
		ArrayList<SubPromotion> subPromotions = (ArrayList<SubPromotion>) promotionVo.getSubPromotion();
		promotionVo.setSubPromotion(subPromotions);
		promotionService.savePromotion(promotionVo);
		return new JsonResponse("");
	}

	@ApiOperation(value = "改变促销活动状态")
	@RequestMapping(value = "/changePromotionStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public JsonResponse changePromotionStatus(@RequestParam(value = "id") int id,
			@RequestParam(value = "status") String status) {
		promotionService.changePromotionStatus(id, status);
		return new JsonResponse("");
	}

	//xu
	@ApiOperation(value="终止促销活动")
	@RequestMapping(value = "/stopPromotion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public JsonResponse stopPromotion(@ApiParam @RequestBody PromotionStop promotionStop){
		String subId = promotionStop.getSubId();
		String bankId = promotionStop.getBankId();
		String proCoding = promotionStop.getProCoding();
		promotionService.stopPromotion(subId, bankId, proCoding);
		return new JsonResponse("");
	}

	@ApiOperation(value = "删除")
	@RequestMapping(value = "/deletePromotion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public JsonResponse deletePromotion(@ApiParam @RequestBody PromotionDelete promotionDelete) {
		String ids = promotionDelete.getIds();
		promotionService.deletePromotion(ids);
		return new JsonResponse("");
	}

	@ApiOperation(value = "审批促销活动")
	@RequestMapping(value = "/approvalPromotion", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@Transactional
	public JsonResponse approvalPromotion(@ApiParam @RequestBody ApprovePromotionVo approvePromotionVo) {

		int ruleId = -1;
		ApproveSubPromotion approvePromotion = approvePromotionVo.getSubPromotion();
		// 审批促销活动
		promotionService.approve(approvePromotionVo);
		Promotion promotion = promotionService.getPromotion(approvePromotion.getPromotionId());
		ArrayList<SubPromotion> subPromotions = (ArrayList<SubPromotion>) promotionService
				.getSubPromotions(approvePromotion.getPromotionId());
		
		
		// 如果待审批,则生成规则
		if (!promotion.getApproveStatus().equals("W")) {
			StringBuffer rule = new StringBuffer();
			rule.append(DroolsConst.pkgName).append(DroolsConst.enter).append(DroolsConst.impPromotionVo)
					.append(DroolsConst.enter).append(DroolsConst.impPromotion).append(DroolsConst.enter)
					.append(DroolsConst.impSubPromotion).append(DroolsConst.enter).append(DroolsConst.impOrder)
					.append(DroolsConst.enter).append(DroolsConst.impOrderItem).append(DroolsConst.enter)
					.append(DroolsConst.porderFunc).append(DroolsConst.enter).append(DroolsConst.rule)
					.append(DroolsConst.blank).append(DroolsConst.doublequote).append("3")
					.append(DroolsConst.doublequote).append(DroolsConst.blank).append(DroolsConst.enter)
					.append(DroolsConst.tab).append(DroolsConst.when).append(DroolsConst.enter).append(DroolsConst.tab)
					.append("$promotion:Promotion(proConType==").append(promotion.getProConType())
					.append(",proThreshold==").append(promotion.getProThreshold()).append(",proPrice==")
					.append(promotion.getProPrice()).append(");").append(DroolsConst.enter).append(DroolsConst.tab)
					.append("$orderItem:OrderItem(");
			for (SubPromotion subPromotion : subPromotions) {
				rule.append("productid==").append(subPromotion.getProductId()).append("||");
			}
			rule.append("productid==0);").append(DroolsConst.enter).append(DroolsConst.tab).append("$order:Order(");
			for (SubPromotion subPromotion : subPromotions) {
				rule.append("bankId==").append(subPromotion.getBankId()).append("||");
			}
			rule.append("bankId==0);").append(DroolsConst.enter).append(DroolsConst.tab).append(DroolsConst.then)
					.append(DroolsConst.enter).append(DroolsConst.tab)
					.append("resultOrder($order,$orderItem,$promotion);").append(DroolsConst.enter)
					.append(DroolsConst.end);
			rulesDao.setRule(promotion.getPromotionName(), rule.toString());
			//ruleId = rulesDao.getByName(promotion.getPromotionName()).getId();
            ruleId = rulesDao.getLastId();
			//插入主活动表
			promotionService.setPromtionRuldId(promotion.getId(),ruleId);
			for (SubPromotion sPromotion : subPromotions) {
				sPromotion.setRuleId(ruleId);
				promotionService.setRuleId(sPromotion.getId(), ruleId);
			}
		}
		else
		{
			ruleId = promotion.getRuleId();
			for (SubPromotion sPromotion : subPromotions) {
				sPromotion.setRuleId(ruleId);
				promotionService.setRuleId(sPromotion.getId(), ruleId);
			}
		}

/*		if (approvePromotion.getApproveStatus().equals("A")) {
			for (SubPromotion sPromotion : subPromotions) {
				int subPromotionId = approvePromotion.getId();
				if (approvePromotion.getId() == sPromotion.getId()) {
					int flag = promotionService.updateNumberByProCoding(sPromotion.getBankId(),sPromotion.getStockcount(), approvePromotion.getProCoding());
					if(0==flag){
						throw new BusinessException(10699,"库存不足");
					}
				
				}
			}
		}*/
		

		return new JsonResponse(ruleId);
	}

	/**
	 * 活动列表
	 * 
	 * @param order
	 * @return
	 */
	@ApiOperation(value = "促销活动查询")
	@RequestMapping(value = "/promotionInfoList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public JsonResponse promotionInfoList(@ApiParam @RequestBody ConditionVo conditionVo) {
		return new JsonResponse(promotionService.getPromotionInfoList(conditionVo));
	}

	/**
	 * 活动列表
	 * 
	 * @param order
	 * @return
	 */
	@ApiOperation(value = "促销活动总数量查询")
	@RequestMapping(value = "/getPromotionInfoListCount", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public JsonResponse getPromotionInfoListCount(@ApiParam @RequestBody ConditionVo conditionVo) {
		return new JsonResponse(promotionService.getPromotionInfoListCount(conditionVo));
	}

	/**
	 * 
	 * @param belongtobank
	 * @param proId
	 * @param promotionId
	 * @return
	 */
	@RequestMapping(value = "/promotionIdInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JsonResponse promotionIdInfo(@RequestParam(value = "belongtobank") String belongtobank,
			@RequestParam(value = "proId") String proId, @RequestParam(value = "promotionId") String promotionId) {
		return new JsonResponse(promotionService.getPromotionIdInfo(belongtobank, proId, promotionId));
	}

	/**
	 * 
	 * @param belongtobank
	 * @param proId
	 * @return
	 */
	@RequestMapping(value = "/promotionInfo", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JsonResponse promotionInfo(@RequestParam(value = "belongtobank") String belongtobank,
			@RequestParam(value = "proId") String proId) {
		return new JsonResponse(promotionService.getPromotionInfo(belongtobank, proId));
	}
	
	/**
	 * 
	 * @param belongtobank
	 * @param proId
	 * @return
	 */
	@RequestMapping(value = "/promotionInfoByUserId", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JsonResponse promotionInfoByUserId(@RequestParam(value = "belongtobank") String belongtobank,
			@RequestParam(value = "proId") String proId,@RequestParam(value = "userId") String userId) {
		return new JsonResponse(promotionService.getPromotionInfoByUserId(belongtobank, proId,userId));
	}

	/**
	 * 微商城
	 * @param prodIds
	 * @param bankId
	 * @return
	 */
	@RequestMapping(value = "/mgrpromotionInfoList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JsonResponse mgrpromotionInfoList(@RequestParam(value = "prodIds") List<String> prodIds,
			@RequestParam(value = "bankId") String bankId) {
		return new JsonResponse(promotionService.getMgrPromotionInfoList(prodIds, bankId));
	}
	
	/**
	 * 活动中心
	 * @param prodIds
	 * @param bankId
	 * @return
	 */
	@RequestMapping(value = "/getPromotionCenterInfoList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JsonResponse getPromotionCenterInfoList(
			@RequestParam(value = "bankId") String bankId) {
		return new JsonResponse(promotionService.getPromotionCenterInfoList(bankId));
	}
	
	/**
	 * 活动中心 为了删除
	 * @param prodIds
	 * @param bankId
	 * @return
	 */
	@RequestMapping(value = "/getPromotionInfoForRemoveList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JsonResponse getPromotionInfoForRemoveList(
			@RequestParam(value = "bankId") String bankId) {
		return new JsonResponse(promotionService.getPromotionInfoForRemoveList(bankId));
	}
	
	


	@RequestMapping(value = "/promotionEdit", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public JsonResponse promotionEdit(@ApiParam @RequestBody SubPromotionEdit subPromotionEdit) {
		return new JsonResponse(promotionService.editPromotionInfo(subPromotionEdit));
	}

	@RequestMapping(value = "/getTime", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getTime(@RequestParam(value = "subId") String subId,@RequestParam(value = "userId") String userId) {
		return promotionService.getTime(subId,userId);
	}


	@RequestMapping(value = "/getTimeForH5", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JsonResponse getTimeForH5(@RequestParam(value = "subId") String subId) {
		return promotionService.getTimeForH5(subId);
	}

	
	@RequestMapping(value = "/mgrpromotionInfoExceptSckillList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JsonResponse mgrpromotionInfoExceptSckillList(@RequestParam(value = "prodIds") List<String> prodIds,
			@RequestParam(value = "bankId") String bankId) {
		return new JsonResponse(promotionService.getMgrPromotionInfoExceptSckillList(prodIds, bankId));
	}

	@RequestMapping(value = "/mgrpromotionInfoSckillList", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public JsonResponse mgrpromotionInfoSckillList(@RequestParam(value = "bankId") String bankId) {
		return new JsonResponse(promotionService.getMgrpromotionInfoSckillList(bankId));
	}
	

}
