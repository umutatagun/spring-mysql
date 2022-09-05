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
    private Long address_id;

    private City city;
    private String address1;
    private String address2;
    private String state;
    private String postCode;
    private Boolean isActive = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cust_id")
    @JsonIgnore
    private Customer customer;

}
