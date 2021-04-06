package com.regmi.userdetail.exception;

import java.time.LocalTime;

public class ErrorResponse {
    private int status;
    private String message;
    private LocalTime timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(int status, String message, LocalTime timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }
}
