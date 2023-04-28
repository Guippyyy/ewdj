package com.project.ewdj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ewdj.entity.BookAuthor;
import com.project.ewdj.repository.BookAuthorRepository;

@Service
public class BookAuthorService {

    @Autowired
    private BookAuthorRepository baRepo;

    public void save(BookAuthor ba) {
        baRepo.save(ba);
    }

    public List<BookAuthor> getAllBookAuthors() {
        return (List<BookAuthor>) baRepo.findAll();
    }
}
