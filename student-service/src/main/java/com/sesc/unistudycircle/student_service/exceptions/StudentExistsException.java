package com.sesc.unistudycircle.student_service.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class StudentExistsException {


    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleRunTimeException(HttpClientErrorException ex, HttpServletRequest request) {
        return ResponseEntity.badRequest().body(ex.getResponseBodyAsString());
    }
}
