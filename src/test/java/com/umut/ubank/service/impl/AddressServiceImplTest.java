package com.umut.ubank.service.impl;


import com.umut.ubank.model.Address;
import com.umut.ubank.model.enumeration.City;
import com.umut.ubank.repository.AddressRepository;
import com.umut.ubank.service.AddressService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class AddressServiceImplTest {

    public AddressService addressService;
    public AddressRepository addressRepository;

    @BeforeEach
    public void setUp() throws Exception{
        addressRepository = Mockito.mock(AddressRepository.class);
        addressService = new AddressServiceImpl(addressRepository);
    }

    @BeforeEach
    public void createAddressTemp() {
        Address address = new Address();
        address.setAddress2("dd");
        address.setAddress1("dd");
        address.setCity(City.ISTANBUL);
        address.setState("dd");
        address.setPostCode("223");
        addressService.createAddress(address);
    }

    @Test
    void whenCreateValidAddressRequestItShouldReturnValidAddress() {
        Address address = new Address();
        address.setAddress2("dd");
        address.setAddress1("dd");
        address.setCity(City.ISTANBUL);
        address.setState("dd");
        address.setPostCode("223");

        Mockito.when(addressService.createAddress(address))
                .thenReturn(address);

    }
    @Test
    void deleteAddressShouldWork() {
        Address address = new Address();
        address.setAddress2("dd");
        address.setAddress1("dd");
        address.setCity(City.ISTANBUL);
        address.setState("dd");
        address.setPostCode("223");
        addressService.createAddress(address);

        Assert.assertEquals(addressService.getAllAddresses().size(), 1);
    }

}