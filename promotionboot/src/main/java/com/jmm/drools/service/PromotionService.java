package com.jmm.drools.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import com.jmm.drools.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.jmm.drools.bean.ApproveSubPromotion;
import com.jmm.drools.bean.Inventory;
import com.jmm.drools.bean.Promotion;
import com.jmm.drools.bean.PromotionInfo;
import com.jmm.drools.bean.PromotionInfoByUserId;
import com.jmm.drools.bean.PromotionMgr;
import com.jmm.drools.bean.SubPromotion;
import com.jmm.drools.common.DroolsConst;
import com.jmm.drools.common.IdUtil;
import com.jmm.drools.common.StrUtils;
import com.jmm.drools.common.exception.BusinessException;
import com.jmm.drools.common.exception.JsonResponse;
import com.jmm.drools.utils.FastJsonUtils;
import com.jmm.drools.vo.ApprovePromotionVo;
import com.jmm.drools.vo.ConditionVo;
import com.jmm.drools.vo.PromotionVo;
import com.jmm.drools.vo.SubPromotionEdit;
import com.jmm.drools.web.PromotionController;



@Service
public class PromotionService {

	@Autowired
	private SupplierInventoryDao supplierInventoryDao;

    @Autowired
    private PromotionDao promotionDao;

    @Autowired
    private SubPromotionDao subPromotionDao;
    
    @Autowired
    private RulesDao rulesDao;
    
    @Resource
	StockPersonService stockPersonService;
    @Resource
	PersonStockDao personStockDao;
      

   /* @Resource
  	private RedisTemplate<String, Object> redisTemplate;
  	private ValueOperations<String, Object> value = null ;*/
  	
  	private final static Logger logger = LoggerFactory.getLogger(PromotionController.class); 
    
    @Transactional
    public Integer savePromotion(PromotionVo promotionVo)
    {	
    	Promotion promotion = promotionVo.getPromotion();
    	
    	Date now = new Date();
    	if(!now.after(promotion.getStartTime())||!"A".equals(promotion.getApproveStatus()))
    	{
    		//待启动
    		promotion.setStatus("T");
    	}
    	else
    	{
    		//未启动
    		promotion.setStatus("P");
    	}
    	
    	
    	Promotion checkPromotion = promotionDao.getByName(promotion.getPromotionName());
    	if(null!= checkPromotion)
    	{
    		throw new BusinessException(10601,"活动名称已存在");
    	}

    	//存入活动主表
    	int promotionId = promotionDao.setPromotion(promotion.getPromotionName(), 
    			promotion.getApproveStatus(), 
    			promotion.getStatus(), 
    			promotion.getCreateTime(),
    			promotion.getStartTime(),
    			promotion.getEndTime(),
    			promotion.getDesc(), 
    			promotion.getProConType(), 
    			promotion.getProThreshold(), 
    			promotion.getProPrice(),
				promotion.getProPic());
    	
    	Promotion resultPromotion = promotionDao.getByName(promotion.getPromotionName());
    	
    	//子活动排重
        List<SubPromotion> subPromotions = new ArrayList<>();
        // 去重
        promotionVo.getSubPromotion().stream().forEach(
                s -> {
                    if (!subPromotions.contains(s)) {
                    	subPromotions.add(s);
                    }
                }
        );
    	
    	//存入子活动表
    	for(SubPromotion sub:subPromotions)
    	{
    		String bankId = sub.getBankId();
    		String productId = sub.getProductId();
    		SubPromotion subPromotion = subPromotionDao.getByDetail(bankId,productId);
    		if(subPromotion==null)
    		{
    			if(4==promotion.getProConType()){
    				sub.setIsSkill(1);
				} else{
    				sub.setIsSkill(0);
				}
        		subPromotionDao.setSubPromotion(sub.getPromotionName(), sub.getBankId(), sub.getProductId(), sub.getOriPrice(),sub.getApproveStatus(), resultPromotion.getId(),sub.getProductName(),sub.getBankName(),sub.getStockcount(),sub.getLabel(),sub.getUserlimit(),sub.getPut_bankCharge(),sub.getIsSkill(),sub.getProCoding());
    		}
    		else
    		{
    			throw new BusinessException(10600,"该活动已存在");
    		}
    	}
    	
    	return promotionId;
    }
    
    @Transactional(propagation=Propagation.SUPPORTS)
    public Integer approve(ApprovePromotionVo approvePromotionVo)
    {
    	ApproveSubPromotion  approveSubPromotion = approvePromotionVo.getSubPromotion();
        subPromotionDao.updateApprove(approveSubPromotion.getId(), approveSubPromotion.getApproveStatus(),approveSubPromotion.getApproveDesc());
        String approveStatus = "A";
        //是否部分通过
        if(subPromotionDao.noApprovedCount(approveSubPromotion.getPromotionId())>0)
        {
        	if(subPromotionDao.approvedCount(approveSubPromotion.getPromotionId())>0)
        	{
        		approveStatus = "P";
        	}
        	else
        	{
        		approveStatus = "W";
        	}
        }

    	return promotionDao.updateApprove(approvePromotionVo.getSubPromotion().getPromotionId(),approveStatus);
    }

    public Integer updateNumberByProCoding(String bankId,int stock,String proCoding){
    	return supplierInventoryDao.updateNumberByProCoding(bankId,stock, proCoding);
	}
    
    public Integer noApprovedCount(int promotionId)
    {
    	return subPromotionDao.noApprovedCount(promotionId);
    }
    
    public Promotion getPromotion(int id)
    {
    	return promotionDao.getById(id);
    }
    
    public List<SubPromotion> getSubPromotions(int promotionId)
    {
    	return subPromotionDao.getByPromotionId(promotionId);
    }
    
    public SubPromotion getSubPromotionForCharge(String bankId,int promotionId)
    {
    	return subPromotionDao.getSubPromotionForCharge(bankId,promotionId);
    }
    
    public Integer setRuleId(int id,int ruleId)
    {
    	return subPromotionDao.updateRuleId(id, ruleId);
    }
    
    public Integer setPromtionRuldId(int id,int ruleId)
    {
    	return subPromotionDao.updatePromotionRuleId(id,ruleId);
    }
    
    public Integer changePromotionStatus(int id,String status)
    {
    	if(status.equals("T"))
    	{
    		return promotionDao.temPromotion(id);
    	}
    	return promotionDao.resetPromotions(id);
    }
    
    
    public PromotionInfo getPromotionInfo(String bankId,String productId)
    {
    	return promotionDao.getPromotionInfo(bankId, productId);
    }
    
    
    
    public PromotionInfoByUserId getPromotionInfoByUserId(String bankId,String productId,String userId)
    {
    	PromotionInfoByUserId pro = promotionDao.getPromotionInfoByUserId(bankId, productId,userId);
    	minStock(pro);
    	return pro;
    }

	private void minStock(PromotionInfoByUserId pro) {
		String restStock = pro.getRestStock();
    	int reststockcount = pro.getReststockcount();
    	int userlimit = pro.getUserlimit();
    	int stockcount = pro.getStockcount();
    	if(userlimit>stockcount){
    		userlimit = stockcount;
    	}
    	
    	int restStockInt = 0;
    	if(restStock==null){
    		restStockInt = userlimit;
    	}else{
    		restStockInt = Integer.valueOf(restStock);
    	}
    	System.out.println("userlimit"+userlimit+"reststockcount"+reststockcount+"restStockInt"+restStockInt);
    	pro.setUserlimit(Math.min(Math.min(userlimit,reststockcount),restStockInt));
	}

	public PromotionInfo getPromotionIdInfo(String bankId,String productId, String promotionId)
    {
    	return promotionDao.getPromotionIdInfo(bankId, productId,promotionId);
    }
    
    public List<PromotionInfo> getMgrPromotionInfoList(List<String> prodIds,String bankId)
    {
    	String prodCond = StrUtils.listToString(prodIds, ',');
    	List<PromotionInfo> promotionInfoList = promotionDao.getMgrPromotionInfoList(prodCond,bankId);
    	return promotionInfoList;
    }
    
    public List<PromotionInfo> getPromotionCenterInfoList(String bankId)
    {
    	List<PromotionInfo> promotionInfoList = promotionDao.getPromotionCenterInfoList(bankId);
    	return promotionInfoList;
    }
    
    public List<PromotionInfo> getPromotionInfoForRemoveList(String bankId)
    {
    	List<PromotionInfo> promotionInfoList = promotionDao.getPromotionInfoForRemoveList(bankId);
    	return promotionInfoList;
    }
    
    

	public int aotoApproval(List<PromotionInfo> promotionInfoList) {
		int state = 0;
		if(promotionInfoList!=null && promotionInfoList.size()>0){
    		Date nowDate =  new Date();
    		for (PromotionInfo promotionInfo : promotionInfoList) {
    			if("W".equals(promotionInfo.getApproveStatus())&&(nowDate.after(promotionInfo.getStartTime())||nowDate.equals(promotionInfo.getStartTime()))&&nowDate.before((promotionInfo.getEndTime()))){
    				//调用审批方法
    				ApprovePromotionVo approvePromotionVo = new ApprovePromotionVo();
    				ApproveSubPromotion approveSubPromotion = new ApproveSubPromotion();
    				approveSubPromotion.setId(promotionInfo.getSubId());
    				approveSubPromotion.setPromotionId(promotionInfo.getId());
    				approveSubPromotion.setApproveStatus(promotionInfo.getApproveStatus());
    				approveSubPromotion.setApproveDesc(promotionInfo.getApproveDesc());
    				approvePromotionVo.setSubPromotion(approveSubPromotion);
    				try {
    					approval(approvePromotionVo);
    					state = 1;
					} catch (Exception e) {
						logger.error(e.toString(), e);
					}
    				
    			}
    		}
    	}
		return state;
	}

	
    
    
    public List<PromotionMgr> getPromotionInfoList(ConditionVo conditionVo)
    {

    	//return promotionDao.getQryPromotionList(conditionVo.getPromotionName(),conditionVo.getProductId(),conditionVo.getBankId(),conditionVo.getProConType(),conditionVo.getStartTime(),conditionVo.getEndTime(),conditionVo.getCurrentResult(),conditionVo.getPageSize());
    	//xumy
		List<PromotionMgr> list = promotionDao.getQryPromotionList(conditionVo.getPromotionName(),conditionVo.getProductId(),conditionVo.getBankId(),conditionVo.getProConType(),conditionVo.getStartTime(),conditionVo.getEndTime(),conditionVo.getCurrentResult(),conditionVo.getPageSize(),conditionVo.getSastatus());
		return list;
    }
    
    public Integer getPromotionInfoListCount(ConditionVo conditionVo)
    {
    	return promotionDao.selectPromotionListCount(conditionVo.getPromotionName(),conditionVo.getProductId(),conditionVo.getBankId(),conditionVo.getStartTime(),conditionVo.getEndTime(),conditionVo.getSastatus(),conditionVo.getProConType());
    }

	@Transactional
    public Integer stopPromotion(String subId, String bankId, String proCoding){
		return promotionDao.stopPromotion(subId,new Date());
    	//promotionDao.cleanRestStock(subId);
    	//return supplierInventoryDao.backStockByProCoding(subId, bankId, proCoding);

	}

    @Transactional
	public Integer deletePromotion(String ids) {
		String Array_IDS[] = ids.split(",");
		promotionDao.deletePromotion(Array_IDS);
		return promotionDao.deleteSubPromotion(Array_IDS);
	}

    /**
     * 
     * @param promotionName
     * @param startTime
     * @param endTime
     * @param pdesc
     * @param proConType
     * @param proThreshold
     * @param proPrice
     * @return
     */
	
    /**
     * 
     * @param promotionName
     * @param startTime
     * @param endTime
     * @param pdesc
     * @param proConType
     * @param proThreshold
     * @param proPrice
     * @return
     */
	
    @Transactional
    public int editPromotionInfo(SubPromotionEdit subPromotionEdit)
    {
    	int res = subPromotionDao.approvedCount(Integer.parseInt(subPromotionEdit.getId()));
    	if(res>=1)
    	{
    		throw new BusinessException(10605,"其他行已有进行活动,无法变更"); 
    	}
    	
    	try
    	{
    	 promotionDao.editPromotion(subPromotionEdit.getPromotionName(), 
    			subPromotionEdit.getStartTime(), 
    			subPromotionEdit.getEndTime(), 
    			subPromotionEdit.getPromoitonDesc(), 
    			subPromotionEdit.getProConType(), 
    			subPromotionEdit.getProThreshold(), 
    			subPromotionEdit.getProPrice(), 
    			subPromotionEdit.getId());
    	
    	promotionDao.editSubPromotion(subPromotionEdit.getPromotionName(), 
    			subPromotionEdit.getProductId(), 
    			subPromotionEdit.getProductName(), 
    			subPromotionEdit.getOriPrice(), 
    			subPromotionEdit.getSubid(),
				subPromotionEdit.getIsSkill(),
				subPromotionEdit.getBankCharge(),
				subPromotionEdit.getPromotlabel(),
				subPromotionEdit.getStock(),
				subPromotionEdit.getUserlimit());
    	}
    	catch(Exception e)
    	{
    		throw new BusinessException(10606,"更新活动失败");
    	}
    	
    	return 0;
    }
    
    public SubPromotion  getSubPromotionId(String bankId,int promotionId){
    	
    	return subPromotionDao.getSubPromotionId(bankId,promotionId);
    }
    
	public Integer updateApproveByJob(Integer id)
	{
		return subPromotionDao.updateApproveByJob(id);
	}
	
	public PromotionInfo getSubPromotion(String id)
    {
    	return subPromotionDao.getInfoById(id);
    }
	
	public PromotionInfo getPromotionIdTimeBySubId(String subId){

		return  promotionDao.getPromotionIdTimeBySubId(subId); 
	}

	@Transactional
	public String deductingInventory(Inventory inventory) {
		
 		long userId = inventory.getUserid();
 		int count = inventory.getCount();
 		String ordersId = inventory.getOrdersId();
 		Map<String, Object> resmap = new HashMap<>();
 		int promotionId = inventory.getPromotionId();
 		String bankId = inventory.getBankId();
 		
 		SubPromotion subPromotion = getSubPromotionId(bankId, promotionId);
 		if(subPromotion==null){
 			logger.info("活动未查询到"+userId+"orderId"+ordersId);
			resmap.put("code", "10601");
			resmap.put("message", "活动未查询到或已中止");
			return FastJsonUtils.toJSONString(resmap);
 		}
 		
 		int subId = subPromotion.getId();
 		int userlimit = subPromotion.getUserlimit();
		int stockResult = subPromotionDao.updateStock(count, subId);
		if(stockResult==1){
			try {
				personStockDao.insertPersonStock(IdUtil.getId(),subId,userlimit, userId);
				return stockPersonService.updatePersonStock(subId,count,userId,ordersId);
			} catch (DuplicateKeyException e) {
				return stockPersonService.updatePersonStock(subId,count,userId,ordersId);
			}
		}else{
			logger.info("购买数量超过银行库存"+userId+"orderId"+ordersId);
			resmap.put("code", "10612");
			resmap.put("message", "购买数量超过银行库存");
			return FastJsonUtils.toJSONString(resmap);
		}
	}
	
	@Transactional
	public void approval(ApprovePromotionVo approvePromotionVo) {
		int ruleId = -1;
		ApproveSubPromotion approvePromotion = approvePromotionVo.getSubPromotion();
		// 审批促销活动
		this.approve(approvePromotionVo);
		Promotion promotion = this.getPromotion(approvePromotion.getPromotionId());
		ArrayList<SubPromotion> subPromotions = (ArrayList<SubPromotion>) 
				this.getSubPromotions(approvePromotion.getPromotionId());
		
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
			ruleId = rulesDao.getByName(promotion.getPromotionName()).getId();

			//插入主活动表
			this.setPromtionRuldId(promotion.getId(),ruleId);
			for (SubPromotion sPromotion : subPromotions) {
				sPromotion.setRuleId(ruleId);
				this.setRuleId(sPromotion.getId(), ruleId);
			}
		}
		else
		{
			ruleId = promotion.getRuleId();
			for (SubPromotion sPromotion : subPromotions) {
				sPromotion.setRuleId(ruleId);
				this.setRuleId(sPromotion.getId(), ruleId);
			}
		}
	}
	
	public PromotionInfoByUserId getPromotionInfoByUserIdForApp(String subId,String userId)
    {
    	return promotionDao.getPromotionInfoByUserIdForApp(subId,userId);
    }

	public String getTime(String subId, String userId) {
		logger.info("subId");
		Map<String, Object> map = new HashMap<>();
		try {
			//value = redisTemplate.opsForValue();
			map.put("code", "10000");
			Map<String, Object> mapdate = new HashMap<>();

			 
			 PromotionInfoByUserId promotionInfo =getPromotionInfoByUserIdForApp(subId,userId);
			 
			 if(promotionInfo!=null){
				 mapdate.put("stock", "" + promotionInfo.getReststockcount());
				 minStock(promotionInfo);
				 mapdate.put("userLimit", "" + promotionInfo.getUserlimit());
				
				String pstatus = promotionInfo.getPstatus();
				String flagOpening = "";
				if ("P".equals(pstatus)) {
					flagOpening = "1";
				} else if ("W".equals(pstatus)) {
					flagOpening = "0";
				} else if ("T".equals(pstatus)) {
					flagOpening = "2";
				}
				mapdate.put("overflag", flagOpening);

				long endtimeouta = 0;
				long bgtimeout = 0;
				Date enddata = promotionInfo.getEndTime();
				endtimeouta = (enddata.getTime() - System.currentTimeMillis()) / 1000;
				Date begindate = promotionInfo.getStartTime();
				bgtimeout = (begindate.getTime() - System.currentTimeMillis()) / 1000 + 2l;

				if (endtimeouta < 0) {
					mapdate.put("edTime", "-2");
				} else {
					mapdate.put("edTime", "" + endtimeouta);
				}

				if (bgtimeout < 0) {
					mapdate.put("bgTime", "-2");
				} else {
					mapdate.put("bgTime", "" + bgtimeout);
				}
			 }else{
				 mapdate.put("overflag", 2);
				 mapdate.put("edTime", "-2");
				 mapdate.put("bgTime", "-2");
				 mapdate.put("stock", 0);
				 mapdate.put("userLimit", 0);
			 }
			 
			map.put("data", mapdate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			map.put("code", "9999");
			map.put("message", "系统异常");
		}

		return JSON.toJSONString(map);
	}

	public JsonResponse getTimeForH5(String subId) {
		logger.info("subId");
		Map<String, Object> mapdate = new HashMap<>();
		try {
			//value = redisTemplate.opsForValue();
			PromotionInfo promotion = getSubPromotion(subId);
			mapdate.put("stock", promotion.getReststockcount());
			
			mapdate.put("approveStatus", promotion.getApproveStatus());
			mapdate.put("pstatus", promotion.getPstatus());
			
			long endtimeouta = 0;
			long bgtimeout = 0;
			Date enddata = promotion.getEndTime();
			endtimeouta = (enddata.getTime() - System.currentTimeMillis()) / 1000;
			Date begindate = promotion.getStartTime();
			bgtimeout = (begindate.getTime() - System.currentTimeMillis()) / 1000 + 2l;

			if (bgtimeout > 0) {
				mapdate.put("second", bgtimeout);
			} else {
				mapdate.put("second", endtimeouta);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return new JsonResponse(mapdate);
	}
	
	
	
	
	
	
	
	 public List<PromotionInfo> getMgrPromotionInfoExceptSckillList(List<String> prodIds, String bankId) {
			String prodCond = StrUtils.listToString(prodIds, ',');
			List<PromotionInfo> promotionInfoList = promotionDao.getMgrPromotionInfoExceptSckillList(prodCond,bankId);
	    	return promotionInfoList;
		}

	    
		public List<PromotionInfo> getMgrpromotionInfoSckillList(String bankId) {
	    	return promotionDao.getMgrpromotionInfoSckillList(bankId);
		}

	
	
	
	
	
	
	
	
	
}
