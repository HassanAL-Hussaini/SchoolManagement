package org.example.schoolmanagement.API;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
