package com.main.order_app.advice;

import java.util.HashMap;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.main.order_app.exception.ProductNotFoundException;

@RestControllerAdvice
public class GlobalException {
  
  @ExceptionHandler(ProductNotFoundException.class)
  public HashMap<String,String> productNotFound(ProductNotFoundException exception)
  {
    HashMap map = new HashMap<>();
    map.put("Error Message", exception.getMessage());
    return map;

  }

}
