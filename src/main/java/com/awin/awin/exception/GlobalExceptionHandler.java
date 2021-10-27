package com.awin.awin.exception;

import com.awin.awin.dto.ErrorResponse;
import com.awin.awin.exception.custom.InvalidConvertingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorException("Parameter validation error");
        errorResponse.setErrorDetails(e.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorException("JSON parse error: Unexpected character");
        errorResponse.setErrorDetails(e.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InvalidConvertingException.class})
    public ResponseEntity<ErrorResponse> handleInvalidConvertingException(InvalidConvertingException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorException("Converting Error");
        errorResponse.setErrorDetails(e.toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
