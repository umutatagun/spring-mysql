package com.umut.ubank.repository;

import com.umut.ubank.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByCustomerId(Long id);
}
