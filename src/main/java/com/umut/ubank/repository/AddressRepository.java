package com.umut.ubank.repository;

import com.umut.ubank.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCustomer(Long id);
}
