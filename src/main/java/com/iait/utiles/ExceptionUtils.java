package com.iait.utiles;

import java.util.function.Supplier;

import com.iait.exceptions.NotFoundServiceException;

public class ExceptionUtils {
    
    public static Supplier<? extends RuntimeException> notFoundExceptionSupplier(
            String mensaje, Object... args) {
        return () -> new NotFoundServiceException(mensaje, args);
    }
    
}
