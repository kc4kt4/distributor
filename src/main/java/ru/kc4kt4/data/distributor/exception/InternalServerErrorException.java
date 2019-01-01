package ru.kc4kt4.data.distributor.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message) {
        super(message);
    }
}