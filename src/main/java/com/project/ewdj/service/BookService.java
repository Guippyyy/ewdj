package com.project.ewdj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ewdj.entity.Book;
import com.project.ewdj.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bRepo;

    public void save(Book b) {
        bRepo.save(b);
    }

    public List<Book> getAllBooks() {
        return bRepo.findAll();
    }

    public Book getBookByISBN(int id) {
        return bRepo.findById(id).get();
    }
}