package com.umut.ubank.service.impl;

import com.umut.ubank.exception.EntityAlreadyExistException;
import com.umut.ubank.exception.NotFoundException;
import com.umut.ubank.model.Manager;
import com.umut.ubank.repository.ManagerRepository;
import com.umut.ubank.service.ManagerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> getAllManagers() {
        return managerRepository
                .findAll()
                .stream()
                .filter(manager -> manager.getIsActive() == Boolean.TRUE)
                .collect(Collectors.toList());
    }

    public Manager getManagerById(Long id) {
        Manager manager = findById(id);
        if(manager.getIsActive() == Boolean.FALSE) {
            throw new NotFoundException("Manager is not Active!");
        }
        return manager;
    }

    public void deleteManager(Long id) {
        Manager manager = findById(id);
        manager.setIsActive(Boolean.FALSE);
        managerRepository.save(manager);
    }

    public Manager updateManager(Long id, Manager manager) {
        Manager m1 = findById(id);
        m1.setIsActive(manager.getIsActive());
        m1.setDateOfBirth(manager.getDateOfBirth());
        m1.setName(manager.getName());
        m1.setSurname(manager.getSurname());
        m1.setCustomers(manager.getCustomers());

        return managerRepository.save(m1);
    }

    public Manager createManager(Manager manager) {
        Optional<Manager> m1 = managerRepository.findById(manager.getId());
        if(m1.isPresent()) {
            throw new EntityAlreadyExistException("Manager already Exists with id "+manager.getId());
        }
        return managerRepository.save(manager);
    }

    private Manager findById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manager not found with id "+id));
    }
}
