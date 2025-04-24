package com.mpos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<Error> handleInsufficientStock(InsufficientStockException insuffEx, WebRequest web){
        Error error = new Error(LocalDateTime.now(),insuffEx.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Error> handleInsufficientStock(OrderNotFoundException ex, WebRequest web){
        Error error = new Error(LocalDateTime.now(),ex.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Error> handleOrderNotFound(ProductNotFoundException ex, WebRequest web){
        Error error = new Error(LocalDateTime.now(),ex.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> validatedException(MethodArgumentNotValidException validEx, WebRequest web){
        Error error = new Error(LocalDateTime.now(),validEx.getMessage(),validEx.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> noHandler(NoHandlerFoundException nohandler, WebRequest web){
        Error error = new Error(LocalDateTime.now(),nohandler.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> exception(Exception e,WebRequest web){
        Error error = new Error(LocalDateTime.now(),e.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.EXPECTATION_FAILED);
    }
}
