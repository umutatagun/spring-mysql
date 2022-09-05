package com.umut.ubank.service;

import com.umut.ubank.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AddressService {
    List<Address> getAllAddresses();

    Address getAddressById(Long id);

    Address updateAddress(Long id, Address address);

    void deleteAddress(Long id);

    Address findById(Long id);
}
