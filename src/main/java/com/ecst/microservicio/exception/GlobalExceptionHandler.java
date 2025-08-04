package com.ecst.microservicio.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        // Puedes personalizar el mensaje de error aquí
        return new ResponseEntity<>("Ha ocurrido un error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
