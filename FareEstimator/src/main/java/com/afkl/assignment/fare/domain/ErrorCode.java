package com.afkl.assignment.fare.domain;

/**
 * Enum class to handle error messages
 * @author x087721 
 */
public enum ErrorCode {
    UNAUTHORIZED("unauthorized to call api"),
    NOT_FOUND("api end point not found"),
    BAD_REQUEST("bad request to api"),
    SERVER_ERROR("internal server error");

    private final String name;

    ErrorCode(String name){
        this.name = name;
    }
}
