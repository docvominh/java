package com.vominh.example.simplebank.it;

import com.vominh.example.simplebank.entity.AccountEntity;
import com.vominh.example.simplebank.entity.CustomerEntity;
import com.vominh.example.simplebank.enums.AccountType;
import com.vominh.example.simplebank.repository.ICustomerRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerIT {

    @Autowired
    private ICustomerRepo customerRepo;

    @BeforeAll
    void setup() {
        customerRepo.deleteAll();
    }

    @Test
    @Order(0)
    void create_customer_and_account() {
//        CustomerEntity customer1 = new CustomerEntity();
//        customer1.setFirstName("Minh");
//        customer1.setLastName("Pham Duc");
//        customer1.setIdNum("201563882");
//        customer1.setCountry("VN");
//
//        var accounts = new HashSet<AccountEntity>();
//
//        accounts.add(AccountEntity.builder()
//                .customer(customer1)
//                .balance(50000L)
//                .accountType(AccountType.INDIVIDUAL)
//                .build());
//        accounts.add(AccountEntity.builder()
//                .customer(customer1)
//                .balance(1000000L)
//                .accountType(AccountType.BUSINESS)
//                .build());
//
//        customer1.setAccounts(accounts);
//        customerRepo.save(customer1);
//
        CustomerEntity customer2 = new CustomerEntity();
        customer2.setFirstName("Doc Vominh");
        customer2.setIdNum("201563882");
        customer2.setCountry("VN");

        var accounts2 = new HashSet<AccountEntity>();

        accounts2.add(AccountEntity.builder()
                .customer(customer2)
                .balance(500000000L)
                .accountType(AccountType.INDIVIDUAL)
                .build());


        customer2.setAccounts(accounts2);
        customerRepo.save(customer2);


    }
}
