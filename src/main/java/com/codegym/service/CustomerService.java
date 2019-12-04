package com.codegym.service;

import com.codegym.model.Customer;

public interface CustomerService {
    Iterable<Customer> findAll();

    Customer findById(Long id);

    Customer save(Customer customer);

    Customer remove(Long id);
}
