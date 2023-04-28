package com.project.ewdj.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.ewdj.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

}