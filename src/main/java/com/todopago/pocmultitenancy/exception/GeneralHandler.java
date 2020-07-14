package com.todopago.pocmultitenancy.exception;

import com.todopago.pocmultitenancy.config.multitenant.exception.TenantException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.todopago.pocmultitenancy.constant.MultiTenantConstants.TENANT_GENERAL_ERROR;

@Log4j2
@RestControllerAdvice
public class GeneralHandler {
    
    @ExceptionHandler(TenantException.class)
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    public ExceptionResponse handleTenantException(TenantException ex) {
        final ExceptionResponse response = ExceptionResponse.errorBuilder(TENANT_GENERAL_ERROR, ex.getLocalizedMessage(),
                HttpStatus.PRECONDITION_FAILED);
        log.error(response.toString());
        return response;
    }
}
