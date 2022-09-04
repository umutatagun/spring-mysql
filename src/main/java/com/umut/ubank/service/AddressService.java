package com.umut.ubank.service;

import com.umut.ubank.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface AddressService {
    List<Address> getAllAddresses();

    Address getAddressById(UUID id);

    Address createAddress(Address address);

    Address updateAddress(UUID id, Address address);

    void deleteAddress(UUID id);
}
