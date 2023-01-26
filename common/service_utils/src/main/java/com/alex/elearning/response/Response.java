package com.alex.elearning.response;

import lombok.Data;

@Data
public class Response<T> {
    private Integer code;
    private String message;
    private T data;

    public Response() {}

    public static<T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setCode(200);
        response.setMessage("Success");
        return response;
    }

    public static<T> Response<T> ok(T data) {
        Response<T> response = new Response<>();
        response.setCode(200);
        response.setMessage("Success");
        if (data != null) response.setData(data);
        return response;
    }

    public static<T> Response<T> fail() {
        Response<T> response = new Response<>();
        response.setCode(500);
        response.setMessage("Fail");
        return response;
    }

    public static<T> Response<T> fail(T data) {
        Response<T> response = new Response<>();
        response.setCode(500);
        response.setMessage("Fail");
        if (data != null) response.setData(data);
        return response;
    }

    public Response<T> message(String message) {
        this.setMessage(message);
        return this;
    }

    public Response<T> code(Integer code) {
        this.setCode(code);
        return this;
    }
}
