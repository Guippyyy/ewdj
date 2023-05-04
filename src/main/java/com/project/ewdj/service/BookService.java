package com.project.ewdj.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ewdj.entity.Author;
import com.project.ewdj.entity.Book;
import com.project.ewdj.repository.AuthorRepository;
import com.project.ewdj.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bRepo;

    @Autowired
    private AuthorRepository aRepo;

    public void save(Book b) {
        bRepo.save(b);
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bRepo.findAll();
    }

    public Book getBookById(Long id) {
        return bRepo.findById(id).get();
    }

    public Book assignAuthorToBook(Long book_id, Long author_id) {
        List<Author> authors = null;
        Book book = bRepo.findById(book_id).get();
        Author author = aRepo.findById(author_id).get();
        authors = book.getAuthors();
        authors.add(author);
        book.setAuthors(authors);
        return bRepo.save(book);
    }
}