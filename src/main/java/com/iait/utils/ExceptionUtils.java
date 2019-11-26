package com.iait.utils;

import java.util.function.Supplier;

import com.iait.exceptions.NotFoundServiceException;

public class ExceptionUtils {
    
    public static Supplier<? extends RuntimeException> exceptionSupplier(
            String mensaje, Object... args) {
        return () -> new NotFoundServiceException(mensaje, args);
    }
    
}
