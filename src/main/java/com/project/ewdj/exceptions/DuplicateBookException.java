package com.project.ewdj.exceptions;

public class DuplicateBookException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DuplicateBookException(Integer id) {
        super(String.format("duplicate book name %s", id));
    }
}
