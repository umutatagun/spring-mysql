package com.umut.ubank.model.dto;

import com.umut.ubank.model.Account;
import com.umut.ubank.model.Address;
import com.umut.ubank.model.BaseEntity;
import com.umut.ubank.model.enumeration.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
public class CustomerDto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;
    private Gender gender;

    private String name;
    private String surname;
    private Date dateOfBirth;

    private Set<Address> addresses;

    private Set<Account> accounts;

}
