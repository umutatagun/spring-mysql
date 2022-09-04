package com.umut.ubank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umut.ubank.model.enumeration.Bank;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.UUID;

@Data
@Entity
public class Account extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID account_id;

    @Column(nullable = false)
    private BigInteger amount;

    @Column(nullable = false)
    private Bank bank;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
}
