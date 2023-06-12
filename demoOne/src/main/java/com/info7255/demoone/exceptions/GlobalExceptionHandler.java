package com.info7255.demoone.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(JsonFormatException.class)
    @ResponseBody
    public String handleJsonFormatException(JsonFormatException jsonFormatException){
        return "{error: \"" + jsonFormatException.getMessage() + "\"}";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PlanNotFoundException.class)
    @ResponseBody
    public String handlePlanNotFoundException(PlanNotFoundException planNotFoundException){
        return "{error: \" Plan not found in database\"}";
    }

    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    @ExceptionHandler(PlanNotUpdatedException.class)
    public void handlePlanNotUpdatedException(PlanNotUpdatedException planNotUpdatedException) {
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 409
    @ExceptionHandler(RuntimeException.class)
    public void handleRuntimeException(RuntimeException runtimeException) {
        System.out.println(runtimeException.getMessage());

    }

}
