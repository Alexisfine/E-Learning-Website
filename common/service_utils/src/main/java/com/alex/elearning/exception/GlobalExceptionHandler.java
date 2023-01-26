package com.alex.elearning.exception;

import com.alex.elearning.response.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response error(Exception ex) {
        ex.printStackTrace();
        return Response.fail().message(ex.getMessage());
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Response error(BizException ex) {
        ex.printStackTrace();
        return Response.fail().code(ex.getCode()).message(ex.getMessage());
    }
}
