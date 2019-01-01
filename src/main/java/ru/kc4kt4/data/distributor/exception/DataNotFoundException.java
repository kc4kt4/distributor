package ru.kc4kt4.data.distributor.exception;

public class DataNotFoundException  extends RuntimeException {

    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
