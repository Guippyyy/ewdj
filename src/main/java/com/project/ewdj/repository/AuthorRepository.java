package com.project.ewdj.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.ewdj.entity.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

    Author findByname(String name);
}
