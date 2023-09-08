package com.harshet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.harshet.productcatalog.dtos.ExceptionDTO;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException ex) {
        System.out.println("Error" + ex.getMessage());
        return new ResponseEntity(
                new ExceptionDTO(HttpStatus.NOT_FOUND, ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
