package com.umut.ubank.controller;

import com.umut.ubank.model.Manager;
import com.umut.ubank.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/manager")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public ResponseEntity<List<Manager>> getAllManagers() {
        return new ResponseEntity(this.managerService.getAllManagers(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(Long id) {
        return new ResponseEntity(this.managerService.getManagerById(id), OK);
    }

    @PostMapping
    public ResponseEntity<Manager> createManager(Manager manager) {
        return new ResponseEntity(this.managerService.createManager(manager), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@PathVariable Long id, @RequestBody Manager manager) {
        return new ResponseEntity(this.managerService.updateManager(id, manager), CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        this.managerService.deleteManager(id);
        return new ResponseEntity(OK);
    }

}
