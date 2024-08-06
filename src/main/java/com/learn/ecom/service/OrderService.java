package com.learn.ecom.service;

import java.util.List;

import com.learn.ecom.model.OrderRequest;
import com.learn.ecom.model.ProductOrder;

public interface OrderService {

	public void saveOrder(Integer userid,OrderRequest orderRequest);
	
	public List<ProductOrder> getOrdersByUser(Integer userId);
	
	public ProductOrder updateOrderStatus(Integer id, String status);
	
	public List<ProductOrder> getAllOrders();

	public ProductOrder getOrdersByOrderId(String orderId);

}
