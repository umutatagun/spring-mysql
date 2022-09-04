package com.umut.ubank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.umut.ubank.model.enumeration.City;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Address extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID address_id;

    private City city;
    private String address1;
    private String address2;
    private String state;
    private String postCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
}
