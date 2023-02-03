package com.example.demo.exception;

public class StandardError {

    private Integer status;
    private Long timeStamp;
    private String message;

    public StandardError(Integer status, Long timeStamp, String message) {
        this.status = status;
        this.timeStamp = timeStamp;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
