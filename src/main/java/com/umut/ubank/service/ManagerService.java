package com.umut.ubank.service;

import com.umut.ubank.model.Manager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ManagerService {
    List<Manager> getAllManagers();
    Manager getManagerById(Long id);
    void deleteManager(Long id);
    Manager updateManager(Long id, Manager manager);
    Manager createManager(Manager manager);
}
