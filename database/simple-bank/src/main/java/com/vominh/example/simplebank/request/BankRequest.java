package com.vominh.example.simplebank.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class BankRequest {
    protected int accountId;
    protected long amount;
    protected Integer executeEmployee;
    protected String atmAddress;
}
