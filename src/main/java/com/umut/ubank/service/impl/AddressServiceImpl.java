package com.umut.ubank.service.impl;

import com.umut.ubank.model.Address;
import com.umut.ubank.repository.AddressRepository;
import com.umut.ubank.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(UUID id) {
        return findById(id);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(UUID id, Address address) {
        Address a1 = findById(id);
        a1.setAddress1(address.getAddress1());
        a1.setAddress2(address.getAddress2());
        a1.setCity(address.getCity());
        a1.setState(address.getState());
        a1.setPostCode(address.getPostCode());
        a1.setCustomer(address.getCustomer());

        return addressRepository.save(a1);
    }

    public void deleteAddress(UUID id) {
        Address address = findById(id);
        addressRepository.delete(address);
    }

    private Address findById(UUID id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id "+id));
    }
}
