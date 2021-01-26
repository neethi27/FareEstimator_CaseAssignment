package com.afkl.assignment.fare.common;

import com.afkl.assignment.fare.domain.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * Helper class to handle application exception
 * @author x087721
 */
@Component
public class ExceptionHandlerHelper {

    /**
     * method to check error code to throw exception
     * @param statusCode
     */
    public void handleException(HttpStatus statusCode) {
        switch (statusCode.value()) {
            case 401:
                throw new ApplicationRuntimeException(ErrorCode.UNAUTHORIZED.toString(), statusCode);
            case 400:
                throw new ApplicationRuntimeException(ErrorCode.NOT_FOUND.toString(), statusCode);
            case 404:
                throw new ApplicationRuntimeException(ErrorCode.BAD_REQUEST.toString(), statusCode);
            case 500:
                throw new ApplicationRuntimeException(ErrorCode.SERVER_ERROR.toString(), statusCode);
        }
    }
}
