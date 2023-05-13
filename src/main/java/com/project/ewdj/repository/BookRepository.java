package com.project.ewdj.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.ewdj.entity.Author;
import com.project.ewdj.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByBookName(String bookName);

    Book findByisbnCode(String isbnCode);

    List<Book> findBooksByauthors(Author a);

}