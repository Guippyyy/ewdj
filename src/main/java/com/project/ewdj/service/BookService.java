package com.project.ewdj.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.ewdj.entity.Book;
import com.project.ewdj.repository.BookRepository;
import com.project.ewdj.util.HomeListItem;

@Service
public class BookService {

    @Autowired
    private BookRepository bRepo;

    @Autowired
    private FavoriteService fRepo;

    public void save(Book b) {
        bRepo.save(b);
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bRepo.findAll();
    }

    public Book getBookById(Long id) {
        return bRepo.findById(id).get();
    }

    public Book getBookByISBN(String isbn) {
        return bRepo.findByisbnCode(isbn);
    }

    public List<HomeListItem> getBooksByAuthors() {
        List<Book> list = getAllBooks();
        Set<Book> favorites = fRepo.getUserFavorites();

        List<HomeListItem> items = new ArrayList<>();
        for (var book : list) {
            items.add(new HomeListItem(book, favorites.contains(book)));
        }
        return items;
    }

}