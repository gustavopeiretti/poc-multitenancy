package com.todopago.pocmultitenancy.config.multitenant.exception;

public class TenantException extends RuntimeException {
    
    public TenantException(String message) {
        super(message);
    }
}
