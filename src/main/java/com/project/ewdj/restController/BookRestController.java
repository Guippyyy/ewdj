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
import com.project.ewdj.util.HomeListItem;

@RestController
@RequestMapping(value = "/rest")
public class BookRestController {

    @Autowired
    public BookService service;

    @Autowired
    public AuthorService aService;

    @GetMapping(value = "/emp/dummy")
    public Book getDummyBook() {
        return service.getDummyBook();
    }

    @GetMapping(value = "/emp/{id}")
    public Book getBook(@PathVariable("id") Long id) {
        return service.getBookById(id);
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
