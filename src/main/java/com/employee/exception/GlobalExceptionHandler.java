package com.employee.exception;

import com.employee.payload.response.ExceptionResponse;
import org.aspectj.weaver.bcel.ExceptionRange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> ExceptionHandler(Exception exception , WebRequest request){
        ExceptionResponse exceptionResponse=new ExceptionResponse(
                exception.getMessage(),
                request.getDescription(false), LocalDateTime.now());
        return ResponseEntity.ok(exceptionResponse);
        }
}
