package com.afkl.assignment.fare.common;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.afkl.assignment.fare.domain.ErrorResponse;

/**
 * controller advisor class to handle exception
 * @author x087721
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    /**
     * method to handle application runtime exception
     * @param ex
     * @return responseEntity
     */
    @ExceptionHandler(ApplicationRuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(ApplicationRuntimeException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                                                   .errorMessage(ex.getMessage())
                                                   .build();
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }
}
