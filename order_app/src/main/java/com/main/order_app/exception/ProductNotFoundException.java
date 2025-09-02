package com.main.order_app.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ProductNotFoundException  extends RuntimeException{
  
  private String message;
}
