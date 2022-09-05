package com.umut.ubank.model;

import com.umut.ubank.model.enumeration.Gender;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;
    private Gender gender;

    private String name;
    private String surname;
    private Date dateOfBirth;
    private Boolean isActive = Boolean.TRUE;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cust_id", referencedColumnName = "id")
    private List<Address> addresses;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "cust_id", referencedColumnName = "id")
    private List<Account> accounts;

    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

}
