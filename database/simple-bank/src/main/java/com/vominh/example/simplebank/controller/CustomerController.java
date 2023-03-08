package com.vominh.example.simplebank.controller;

import com.vominh.example.simplebank.entity.CustomerEntity;
import com.vominh.example.simplebank.repository.ICustomerRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController extends GenericCRUDController<CustomerEntity, Integer> {

    public CustomerController(ICustomerRepo customerRepo) {
        super(customerRepo);
    }


}
