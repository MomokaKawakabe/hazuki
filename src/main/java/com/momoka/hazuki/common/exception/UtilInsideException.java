package com.momoka.hazuki.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class UtilInsideException extends RuntimeException {

    private int code;
    private String message;

    public UtilInsideException(String message) {
        super(message);

        this.code = -500;
        this.message = message;
    }
}
