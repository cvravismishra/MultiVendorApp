package com.operation.batch_app.config;

import org.springframework.batch.item.ItemProcessor;

import com.operation.batch_app.entity.Customer;

public class CustomerProcessor implements ItemProcessor<Customer,Customer> {

    @Override
    public Customer process(Customer customer) throws Exception {
        if(customer.getCountry().equals("United States")) {
            return customer;
        }else{
            return null;
        }
    }
}