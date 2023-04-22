package com.project.ewdj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.ewdj.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}