package com.umut.ubank.model;

import com.umut.ubank.model.enumeration.ManLevel;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Manager extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;
    private Date dateOfBirth;
    private Boolean isActive= Boolean.TRUE;
    private ManLevel level = ManLevel.JR;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "man_id", referencedColumnName = "id")
    private List<Customer> customers;

    public void addCustomerToManager(Customer customer) {
        this.customers.add(customer);
    }
}
