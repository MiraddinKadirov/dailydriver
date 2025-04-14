package org.example.dailydriver.service;

import org.example.dailydriver.model.entity.Address;
import org.example.dailydriver.repository.AddressRepository;
import org.example.dailydriver.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements CrudService<Address, Address, Address, String>{

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address entity) {
        return null;
    }

    @Override
    public Address update(Address entity, String id) {

        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public Address findById(String id) {
        return null;
    }

    @Override
    public List<Address> findAll() {
        return List.of();
    }
}
