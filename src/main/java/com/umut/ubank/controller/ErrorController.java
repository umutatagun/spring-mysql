package com.umut.ubank.controller;

import com.umut.ubank.exception.EntityAlreadyExistException;
import com.umut.ubank.exception.NotFoundException;
import io.swagger.v3.core.util.Json;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> customerNotFoundException(NotFoundException err) {
        HashMap<String, String> error = new HashMap<>();
        error.put("success", "false");
        error.put("error", err.getMessage());
        return new ResponseEntity(error, NOT_FOUND);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<HashMap> validationError(SQLIntegrityConstraintViolationException err) {
        HashMap<String, String> error = new HashMap<>();
        error.put("success", "false");
        error.put("error", err.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HashMap> enumError(HttpMessageNotReadableException err) {
        HashMap<String, String> error = new HashMap<>();
        error.put("success", "false");
        error.put("error", err.getMessage());
        return new ResponseEntity(error, BAD_REQUEST);
    }

    @ExceptionHandler(EntityAlreadyExistException.class)
    public ResponseEntity<HashMap> alreadyyExistError(EntityAlreadyExistException err) {
        HashMap<String, String> error = new HashMap<>();
        error.put("success", "false");
        error.put("error", err.getMessage());
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
