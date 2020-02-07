package com.gusgo.bo.exception;

import lombok.Getter;

import java.util.Arrays;

@Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;
    private final transient Object[] parameters;

    public ServiceException(String errorCode, Object... parameters) {
        super(errorCode + " - " + Arrays.toString(parameters));

        this.errorCode = errorCode;
        this.parameters = parameters;
    }

}
