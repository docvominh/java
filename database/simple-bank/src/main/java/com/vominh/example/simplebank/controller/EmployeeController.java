package com.vominh.example.simplebank.controller;

import com.vominh.example.simplebank.entity.EmployeeEntity;
import com.vominh.example.simplebank.repository.IEmployeeRepo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends GenericCRUDController<EmployeeEntity, Integer> {

    public EmployeeController(IEmployeeRepo employeeRepo) {
        super(employeeRepo);
    }


}
