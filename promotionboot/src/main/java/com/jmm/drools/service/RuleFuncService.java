package com.jmm.drools.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jmm.drools.bean.Order;
import com.jmm.drools.bean.OrderItem;
import com.jmm.drools.bean.Promotion;


public class RuleFuncService {

	private static Logger logger = LogManager.getLogger();
	//订单计算
	public static Order resultOrder(Order order,OrderItem orderItem,Promotion promotion) throws Exception
	{

		Order result = new Order();

			if(promotion.getProConType() == 1 && order.getOrderspayprice()>=promotion.getProThreshold())
			{
				order.setOrderspayprice(order.getOrderspayprice()-promotion.getProPrice());

				if(null!=order.getOrderItem())
				{
					orderItem.setOrderitemspayprice(orderItem.getOrderitemspayprice()-promotion.getProPrice());
					result = order;
					result.setOrderItem(orderItem);
				}
			}
			if(promotion.getProConType() == 2 && order.getOrderItem().getQuantity()>=promotion.getProThreshold())
			{
				order.setOrderspayprice(order.getOrderspayprice()-promotion.getProPrice());

				if(null!=order.getOrderItem())
				{
					orderItem.setOrderitemspayprice(orderItem.getOrderitemspayprice()-promotion.getProPrice());
					result = order;
					result.setOrderItem(orderItem);
				}
			}
			else if (promotion.getProConType() == 3)
			{
				order.setOrderspayprice(order.getOrderspayprice()-order.getOrderItem().getQuantity()*promotion.getProPrice());
				if(null!=order.getOrderItem())
				{
					orderItem.setOrderitemspayprice(order.getOrderspayprice());
					result = order;
					result.setOrderItem(orderItem);
				}
			}

		return result;
	}
}
