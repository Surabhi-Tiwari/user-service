package com.example.payload;

import org.springframework.http.HttpStatus;

public class ApiResponse {

    private String message;
    private boolean success;
    private HttpStatus status;

    public ApiResponse() {
    }

    public ApiResponse(String message, boolean success, HttpStatus status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }

    
    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    
    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
