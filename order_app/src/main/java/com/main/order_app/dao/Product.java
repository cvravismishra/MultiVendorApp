package com.main.order_app.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
  
  private int id;
  private int price;
  private String description;
  private List<Image> images;
  private int categoryId;
  private String title;

}
