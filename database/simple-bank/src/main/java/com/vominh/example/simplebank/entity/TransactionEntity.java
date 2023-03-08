package com.vominh.example.simplebank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vominh.example.simplebank.TransactionStatus;
import com.vominh.example.simplebank.enums.TransactionType;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "transaction", schema = "public", catalog = "simple_bank")
public class TransactionEntity {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator")
    @SequenceGenerator(name = "generator", sequenceName = "generate_value", allocationSize = 1)
    @Id
    @Column(name = "transaction_id")
    private long transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @JsonIgnoreProperties({"transactionEntities"})
    @OneToOne
    @JoinColumn(name = "source_account_id")
    private AccountEntity sourceAccount;

    @JsonIgnoreProperties({"transactionEntities"})
    @OneToOne
    @JoinColumn(name = "destination_account_id")
    private AccountEntity destinationAccount;

    @Basic
    @Column(name = "amount")
    private Long amount;

    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Basic
    @Column(name = "create_date")
    private Timestamp createDate;

    @Basic
    @Column(name = "delete_date")
    private Timestamp deleteDate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "transaction", cascade = CascadeType.ALL)
    private TransactionDetailEntity transactionDetail;
}
