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
    private Long account_id;

    @Column(nullable = false)
    private BigInteger amount;

    @Column(nullable = false)
    private Bank bank;

    private Boolean isActive = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    @JsonIgnore
    private Customer customer;
}
