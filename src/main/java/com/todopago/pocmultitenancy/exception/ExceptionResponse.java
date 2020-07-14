package com.todopago.pocmultitenancy.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    
    private int status;
    private List<MiddlewareError> errors;
    
    public static ExceptionResponse errorBuilder(String customMessage, String exceptionMessage, HttpStatus code) {
        final MiddlewareError middlewareError = MiddlewareError
                .builder()
                .developerMessage(customMessage)
                .internalMessage(exceptionMessage)
                .build();
        
        return ExceptionResponse.builder()
                .status(code.value())
                .errors(Collections.singletonList(middlewareError))
                .build();
    }
    
    @Builder
    @Getter
    @ToString
    public static class MiddlewareError {
        
        private final String internalMessage;
        private final String developerMessage;
    }
}
