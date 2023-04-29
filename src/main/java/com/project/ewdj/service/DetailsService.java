package com.project.ewdj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ewdj.entity.Book;
import com.project.ewdj.repository.DetailsRepository;

@Service
public class DetailsService {

    @Autowired
    private DetailsRepository dRepo;

    public void save(Book b) {
        dRepo.save(b);
    }

    public Book getBookById(int id) {
        return dRepo.findById(id).get();
    }
}
