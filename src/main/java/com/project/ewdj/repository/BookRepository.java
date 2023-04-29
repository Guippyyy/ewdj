package com.project.ewdj.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.ewdj.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByBookName(String bookName);
    // Book findByISBN(String isbn_nummer);
}