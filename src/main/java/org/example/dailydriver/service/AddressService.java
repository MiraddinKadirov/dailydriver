package org.example.dailydriver.service;

import org.example.dailydriver.mapper.AddressMapper;
import org.example.dailydriver.model.dto.addressDto.AddressCreateDto;
import org.example.dailydriver.model.entity.Address;
import org.example.dailydriver.model.entity.AuthUser;
import org.example.dailydriver.repository.AddressRepository;
import org.example.dailydriver.repository.AuthUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements CrudService<Address, Address, Address, String>{

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    public Address save(Address entity) {
        return null;
    }

    public void save(AddressCreateDto entity, AuthUser authUser) {
//        Address address = addressMapper.toEntity(entity);
//        address.setAuthUser(authUser);
//        addressRepository.save(address);
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
