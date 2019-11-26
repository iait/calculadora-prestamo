package com.iait.exceptions;

public abstract class ServiceException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    public static final int SERVICE_EXCEPTION_NOT_FOUND = 100;
    
    public ServiceException(String mensaje, Object... args) {
        super(String.format(mensaje, args));
    }
    
    public abstract int getCode();
}
