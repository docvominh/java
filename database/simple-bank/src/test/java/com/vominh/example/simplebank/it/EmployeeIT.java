package com.vominh.example.simplebank.it;

import com.vominh.example.simplebank.entity.EmployeeEntity;
import com.vominh.example.simplebank.repository.ICustomerRepo;
import com.vominh.example.simplebank.repository.IEmployeeRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeIT {
    @Autowired
    private IEmployeeRepo employeeRepo;

    @Autowired
    private ICustomerRepo customerRepo;

    @BeforeAll
    void setup() {
        employeeRepo.deleteAll();
    }


    @Test
    void create() {
        var employee = EmployeeEntity.builder()
                .firstName("Timothy")
                .lastName("Halo")
                .idNum("201563882")
                .country("VN")
                .build();

        var customers = customerRepo.findAll();
        employee.setCustomers(new HashSet<>(customers));

        employeeRepo.save(employee);
    }
}
