package ru.kc4kt4.data.distributor.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConnectionException extends RuntimeException {

    public ConnectionException(String message) {
        super(message);
    }
}
