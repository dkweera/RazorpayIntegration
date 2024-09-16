package com.spring.implementation.service;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.spring.implementation.orders.Orders;
import com.spring.implementation.repopsitory.OrdersRespository;

import jakarta.annotation.PostConstruct;

@Service
public class OrderService {
	@Autowired
	private OrdersRespository  ordersRespository;
	
	@Value("${razorpay.key_id}")
	private String razorpayID;
	@Value("${razorpay.key_secret}")
	private String razorpaySecret;
	
	
	private RazorpayClient razorpayClient;
	
	@PostConstruct
	public void init() throws RazorpayException {
		this.razorpayClient = new RazorpayClient(razorpayID,razorpaySecret);
	}
	
	public  Orders  createOrders(Orders orders) throws RazorpayException {
		   JSONObject orderRequest = new JSONObject();
	        orderRequest.put("amount", orders.getAmount()*100);
	        orderRequest.put("currency", "INR");
	        orderRequest.put("receipt", orders.getEmail());
	        
	        Order razorpayOrders=	razorpayClient.orders.create(orderRequest);
	        
	        if(razorpayOrders!=null) {
	         orders.setRazorpayOrderId(razorpayOrders.get("id"));
	         orders.setOrderStatus(razorpayOrders.get("status"));
	        }
			return ordersRespository.save(orders);
	 			
	    
				
		
	}
	public Orders updateStatus(Map<String, String> map) {
    	String razorpayId = map.get("razorpay_order_id");
    	Orders order = ordersRespository.findByRazorpayOrderId(razorpayId);
    	order.setOrderStatus("PAYMENT DONE");
    	Orders orders = ordersRespository.save(order);
    	return orders;
    }

}
