package com.studentdemo.studentdemo.util;

public class Response {

    private String status;

    public Response(String status, String message, Object resultObj) {
        this.status = status;
        this.message = message;
        this.resultObj = resultObj;
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    private String message;

    private Object resultObj;

    public Response() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResultObj() {
        return resultObj;
    }

    public void setResultObj(Object resultObj) {
        this.resultObj = resultObj;
    }
}
