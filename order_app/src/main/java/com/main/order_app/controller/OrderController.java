package com.main.order_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.main.order_app.dao.OrderRequest;
import com.main.order_app.entity.OrderModel;
import com.main.order_app.service.OrderService;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping("/place")
  public OrderModel orderPlace(@RequestBody OrderRequest orderRequest) throws Exception
  {
     return orderService.orderPlace(orderRequest);
  }

  @PostMapping("/{orderId}")
  public OrderModel getOrder(@PathVariable int orderId)
  {
     return orderService.getOrder(orderId);
  }
  
}
