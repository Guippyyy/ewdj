package com.project.ewdj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ewdj.entity.Author;
import com.project.ewdj.repository.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository aRepo;

    public void save(Author a) {
        aRepo.save(a);
    }

    public List<Author> getAllAuthors() {
        return (List<Author>) aRepo.findAll();
    }

    public Author getAuthorById(int id) {
        return aRepo.findById(id).get();
    }
}
