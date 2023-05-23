package com.project.ewdj.restController;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ewdj.entity.Author;
import com.project.ewdj.entity.Book;
import com.project.ewdj.service.AuthorService;
import com.project.ewdj.service.BookService;

@RestController
@RequestMapping(value = "/rest")
public class BookRestController {

    private final BookService service;
    private final AuthorService aService;

    @Autowired
    public BookRestController(BookService service, AuthorService aService) {
        this.service = service;
        this.aService = aService;
    }

    @GetMapping(value = "/book/isbn/{isbnCode}")
    public Book getBookByISBN(@PathVariable("isbnCode") String isbnCode) {
        return service.getBookByISBN(isbnCode);
    }

    @GetMapping("/author/{id}/books")
    public java.util.List<Book> getBooksByAuthor(@PathVariable("id") Long authorId) {
        Author author = aService.getAuthorById(authorId);
        // if (author == null) {
        // throw new ResourceNotFoundException("Author not found with id: " + authorId);
        // }
        return author.getBooks();
    }

    @GetMapping("/author/{name}/name/books")
    public java.util.List<Book> getBookByAuthorName(@PathVariable("name") String name) {
        Author author = aService.getAuthorByName(name);
        return author.getBooks();
    }
}
