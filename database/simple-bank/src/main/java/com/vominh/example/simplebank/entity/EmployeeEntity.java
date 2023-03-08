package com.vominh.example.simplebank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "employee", schema = "public", catalog = "simple_bank")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "generate_value", allocationSize = 1)
    @Id
    @Column(name = "employee_id")
    private int employeeId;
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

    @JsonIgnoreProperties("employees")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_customer",
            joinColumns = {@JoinColumn(name = "employee_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private Set<CustomerEntity> customers;

}
