package com.umut.ubank.service.impl;

import com.umut.ubank.exception.EntityAlreadyExistException;
import com.umut.ubank.exception.NotFoundException;
import com.umut.ubank.model.Manager;
import com.umut.ubank.repository.ManagerRepository;
import com.umut.ubank.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;
    private final Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    public List<Manager> getAllManagers() {
        logger.info("GetAllManager runs");
        return managerRepository
                .findAll()
                .stream()
                .filter(manager -> manager.getIsActive() == Boolean.TRUE)
                .collect(Collectors.toList());
    }

    public Manager getManagerById(Long id) {
        logger.info("Get manager By Id runs");
        Manager manager = findById(id);
        if(manager.getIsActive() == Boolean.FALSE) {
            logger.warn("Get manager By Id: id is not active "+id);
            throw new NotFoundException("Manager is not Active!");
        }
        return manager;
    }

    public void deleteManager(Long id) {
        Manager manager = findById(id);
        logger.info("Delete Manager runs");
        manager.setIsActive(Boolean.FALSE);
        managerRepository.save(manager);
    }

    public Manager updateManager(Long id, Manager manager) {
        Manager m1 = findById(id);
        logger.info("Update manager runs for id "+id);
        m1.setIsActive(manager.getIsActive());
        m1.setEmail(manager.getEmail());
        m1.setDateOfBirth(manager.getDateOfBirth());
        m1.setName(manager.getName());
        m1.setGender(manager.getGender());
        m1.setSurname(manager.getSurname());
        m1.setCustomers(manager.getCustomers());

        return managerRepository.save(m1);
    }

    public Manager createManager(Manager manager) {
        Optional<Manager> m1 = managerRepository.findByEmail(manager.getEmail());
        if(m1.isPresent()) {
            throw new EntityAlreadyExistException("Manager already Exists with id "+manager.getId());
        }
        logger.info("Manager created");
        return managerRepository.save(manager);
    }

    private Manager findById(Long id) {
        return managerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Manager not found with id "+id));
    }
}
