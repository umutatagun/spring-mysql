package com.umut.ubank.service.impl;

import com.umut.ubank.exception.NotFoundException;
import com.umut.ubank.model.Address;
import com.umut.ubank.repository.AddressRepository;
import com.umut.ubank.service.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository
                .findAll()
                .stream()
                .filter(address -> address.getIsActive() == Boolean.TRUE)
                .collect(Collectors.toList());
    }

    public Address getAddressById(Long id) {
        Address address = findById(id);
        if(address.getIsActive() == Boolean.FALSE) {
            throw new NotFoundException("Account is not active!");
        }
        return address;
    }

    public Address updateAddress(Long id, Address address) {
        Address a1 = findById(id);
        a1.setAddress1(address.getAddress1());
        a1.setAddress2(address.getAddress2());
        a1.setCity(address.getCity());
        a1.setState(address.getState());
        a1.setPostCode(address.getPostCode());
        a1.setCustomer(address.getCustomer());
        a1.setIsActive(address.getIsActive());

        return addressRepository.save(a1);
    }

    public void deleteAddress(Long id) {
        Address address = findById(id);
        address.setIsActive(Boolean.FALSE);
        address.setCustomer(null);
        addressRepository.save(address);
    }

    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Address not found with id "+id));
    }
}
