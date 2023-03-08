package com.vominh.example.simplebank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vominh.example.simplebank.enums.AccountType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "account", schema = "public", catalog = "simple_bank")
public class AccountEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "generate_value", allocationSize = 1)
    @Id
    @Column(name = "account_id")
    private int accountId;

    @Basic
    @Column(name = "balance")
    private Long balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type")
    private AccountType accountType;

    @JsonIgnoreProperties({"accounts", "employees", "hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @JsonIgnoreProperties({"sourceAccount", "destinationAccount", "hibernateLazyInitializer", "handler"})
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private Set<TransactionEntity> transactionEntities;
}
