package com.project.ewdj.restController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.ewdj.exceptions.DuplicateBookException;

@RestControllerAdvice
public class BookErrorAdvice {

    @ResponseBody
    @ExceptionHandler(DuplicateBookException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String duplicateBookHandler(DuplicateBookException ex) {
        return ex.getMessage();
    }

}
