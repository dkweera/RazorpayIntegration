package com.spring.implemnetat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.implemnetation.model.Orders;



public interface OrdersRepository  extends  JpaRepository<Orders,Integer>{
 
}
