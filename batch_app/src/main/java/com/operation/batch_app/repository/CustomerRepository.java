package com.operation.batch_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.operation.batch_app.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {
  
}
