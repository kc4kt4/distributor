package ru.kc4kt4.data.distributor.exception;

public class ErrorConnectException extends RuntimeException {

    public ErrorConnectException() {
    }

    public ErrorConnectException(String message) {
        super(message);
    }
}
