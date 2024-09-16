package com.spring.implementation.repopsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.implementation.orders.Orders;

public  interface  OrdersRespository extends JpaRepository<Orders, Integer> {

	Orders findByRazorpayOrderId(String razorpayId);
	
	
}
