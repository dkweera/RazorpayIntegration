package com.spring.implementation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;

import com.razorpay.RazorpayException;
import com.spring.implementation.orders.Orders;
import com.spring.implementation.service.OrderService;


@Controller
@RequestMapping("/api")
public class OrdersController {
	
	@Autowired
	private OrderService orderService;
	
//	
	@GetMapping("/orders")
	public String ordersPage() {
		System.out.println("Hi there Order");
		return "orders";
	}
	
	@PostMapping(value = "/createOrder", produces = "application/json")
	@ResponseBody
	public ResponseEntity<Orders> createOrder(@RequestBody Orders orders) throws RazorpayException {
		System.out.println("Running");
	    Orders razorpayOrder = orderService.createOrders(orders);
	    return new ResponseEntity<>(razorpayOrder, HttpStatus.CREATED);
	}

	@PostMapping("/paymentCallback")
	public String paymentCallback(@RequestParam Map<String, String> response) {
		 	orderService.updateStatus(response);
		 	return "success";
		
	}
	
}
