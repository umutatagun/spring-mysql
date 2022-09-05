package com.umut.ubank.exception;

public class EntityIsNotActiveException extends RuntimeException{
    public EntityIsNotActiveException(String msg) {
        super(msg);
    }
}
