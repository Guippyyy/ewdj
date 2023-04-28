package com.project.ewdj;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.ewdj.entity.Author;
import com.project.ewdj.entity.Book;
import com.project.ewdj.entity.BookAuthor;
import com.project.ewdj.entity.Location;
import com.project.ewdj.repository.AuthorRepository;
import com.project.ewdj.repository.BookAuthorRepository;
import com.project.ewdj.repository.BookRepository;
import com.project.ewdj.repository.FavoriteRepository;
import com.project.ewdj.repository.LocationRepository;
import com.project.ewdj.service.AuthorService;
import com.project.ewdj.service.BookAuthorService;
import com.project.ewdj.service.BookService;
import com.project.ewdj.service.LocationService;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository aRepository;

    @Autowired
    private BookAuthorRepository baRepository;

    @Autowired
    private BookAuthorService baService;

    @Autowired
    private LocationRepository lRepository;

    @Override
    public void run(String... args) throws Exception {

        Book book1 = new Book("Consider Phlebas", "2312865737457", new BigDecimal("32.0"));
        Book book2 = new Book("Fire Upon The Deep", "2312365477328", new BigDecimal("32.0"));
        Book book3 = new Book("Dune", "2312365460857", new BigDecimal("32.0"));
        Book book4 = new Book("Foundation", "2312365467874", new BigDecimal("32.0"));

        repository.save(book1);
        repository.save(book2);
        repository.save(book3);
        repository.save(book4);

        Author author1 = new Author("Ian Banks", baService.getAllBookAuthors());
        Author author2 = new Author("Venor Vinge", baService.getAllBookAuthors());
        Author author3 = new Author("Frank Herbert", baService.getAllBookAuthors());
        Author author4 = new Author("Isaac Asimov", baService.getAllBookAuthors());

        aRepository.save(author1);
        aRepository.save(author2);
        aRepository.save(author3);
        aRepository.save(author4);

        // BookAuthor bookAuthor1 = new BookAuthor(book1, author1);
        // BookAuthor bookAuthor2 = new BookAuthor(book2, author2);
        // BookAuthor bookAuthor3 = new BookAuthor(book3, author3);
        // BookAuthor bookAuthor4 = new BookAuthor(book4, author4);

        // baRepository.save(bookAuthor1);
        // baRepository.save(bookAuthor2);
        // baRepository.save(bookAuthor3);
        // baRepository.save(bookAuthor4);

        lRepository.save(new Location("12312", "1231", "Ronse"));
        lRepository.save(new Location("12312", "1231", "Gent"));
        lRepository.save(new Location("12312", "1231", "Oudenaarde"));
        lRepository.save(new Location("12312", "1231", "Brussel"));
    }
}