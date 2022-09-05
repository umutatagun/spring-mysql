package com.umut.ubank.exception;

public class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException(String msg) {
        super(msg);
    }
}
