package com.iait.exceptions;

public class NotFoundServiceException extends ServiceException {
    
    private static final long serialVersionUID = 1L;
    
    public NotFoundServiceException(String mensaje, Object... args) {
        super(mensaje, args);
    }
    
    @Override
    public int getCode() {
        return ServiceException.SERVICE_EXCEPTION_NOT_FOUND;
    }
}
