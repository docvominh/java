package com.vominh.example.simplebank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "customer", schema = "public", catalog = "simple_bank")
public class CustomerEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "generate_value", allocationSize = 1)
    @Id
    @Column(name = "customer_id")
    private int customerId;
    @Basic
    @Column(name = "first_name")
    private String firstName;
    @Basic
    @Column(name = "last_name")
    private String lastName;
    @Basic
    @Column(name = "id_num")
    private String idNum;
    @Basic
    @Column(name = "country")
    private String country;

    @JsonIgnoreProperties("customer")
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AccountEntity> accounts;

    @JsonIgnoreProperties("customers")
    @ManyToMany(mappedBy = "customers", fetch = FetchType.LAZY)
    private Set<EmployeeEntity> employees;
}


