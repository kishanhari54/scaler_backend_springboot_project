package com.harshet.productcatalog.dtos;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDTO {

    private HttpStatus code;
    private String message;

    public ExceptionDTO(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }
}