package com.umut.ubank.model.dto;

import com.umut.ubank.model.Account;
import com.umut.ubank.model.Address;
import com.umut.ubank.model.BaseEntity;
import com.umut.ubank.model.enumeration.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
public class CustomerDto extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;
    private Gender gender;

    private String name;
    private String surname;
    private Date dateOfBirth;
    private Boolean isActive;

    private List<Address> addresses;

    private List<Account> accounts;

}
