package com.main.order_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.order_app.entity.OrderModel;

@Repository
public interface OrderRepository extends JpaRepository<OrderModel,Integer> {
  
}
