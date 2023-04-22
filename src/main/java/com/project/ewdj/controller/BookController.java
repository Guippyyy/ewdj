package com.project.ewdj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.ewdj.entity.Book;
import com.project.ewdj.entity.Favorite;
import com.project.ewdj.service.BookService;
import com.project.ewdj.service.FavoriteService;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private FavoriteService fService;

    @GetMapping("/")
    public ModelAndView home() {
        List<Book> list = service.getAllBooks();
        // ModelAndView m = new ModelAndView();
        // m.setViewName("home");
        // m.addObject("book", list);
        return new ModelAndView("home", "book", list);
    }

    @GetMapping("/add_book")
    public String addBook() {
        return "add_book";
    }

    @GetMapping("/favorites")
    public String getAllFavoriteBooks(Model model) {
        List<Favorite> list = fService.getAllFavorites();
        model.addAttribute("book", list);
        return "favoriteBookList";
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        service.save(b);
        return "redirect:/";

    }

    @RequestMapping("/favorite/{id}")
    public String getFavorites(@PathVariable("id") int id) {
        Book b = service.getBookByISBN(id);
        Favorite f = new Favorite(b.getId(), b.getBookName(), b.getAutor(), b.getPrice(), b.getIsbn_nummer());
        fService.saveAsFavorite(f);
        return "redirect:/favorites";
    }
}
