package com.main.order_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.main.order_app.dao.OrderRequest;
import com.main.order_app.dao.Product;
import com.main.order_app.entity.OrderModel;
import com.main.order_app.exception.ProductNotFoundException;
import com.main.order_app.repository.OrderRepository;

@Service
public class OrderService {
  
  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private RestTemplate template;

  public OrderModel orderPlace(OrderRequest orderRequest) throws Exception
  {
      OrderModel order = OrderModel.builder()
      .name(orderRequest.getName())
      .quantity(orderRequest.getQuantity())
      .price(orderRequest.getPrice())
      .build();

    Product product =   template.postForObject("http://localhost:8082/api/v1/operation/product/"+orderRequest.getName(),orderRequest,Product.class);
    if(product.getId()!=0)
    {
     OrderModel confirm_order =  orderRepository.save(order);
     return confirm_order;
    }
    else
    {
        throw new ProductNotFoundException("Product Not Found");
    }
     
  }

  public OrderModel getOrder(int orderId) {
    return orderRepository.findById(orderId).orElse(new OrderModel());
  }

}
