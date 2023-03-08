package com.vominh.example.simplebank.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "transaction_detail", schema = "public", catalog = "simple_bank")
public class TransactionDetailEntity {
    @Id
    private long transactionId;

    @Basic
    @Column(name = "execute_employee_id")
    private Integer executeEmployeeId;
    @Basic
    @Column(name = "atm_address")
    private String atmAddress;
    @Basic
    @Column(name = "transaction_method")
    private String transactionMethod;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;

}
