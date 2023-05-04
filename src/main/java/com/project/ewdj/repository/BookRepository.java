package com.project.ewdj.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.project.ewdj.entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findByBookName(String bookName);
    // Book findByISBN(String isbn_nummer);

    // public Book assignAuthorToBook(Long book_id, Long author_id);
}