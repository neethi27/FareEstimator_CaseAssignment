package com.afkl.assignment.fare.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * class for application runtime exception
 */
@Getter
public class ApplicationRuntimeException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;

    /**
     * @param errorMessage
     * @param httpStatus
     */
    public ApplicationRuntimeException(String errorMessage,
                                       HttpStatus httpStatus){
        super();
        this.message = errorMessage;
        this.httpStatus = httpStatus;
    }
}
