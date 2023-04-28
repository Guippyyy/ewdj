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
import com.project.ewdj.entity.FavoriteBook;
import com.project.ewdj.service.BookService;
import com.project.ewdj.service.DetailsService;
import com.project.ewdj.service.FavoriteService;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private FavoriteService fService;

    @Autowired
    private DetailsService dService;

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
        List<FavoriteBook> list = fService.getAllFavorites();
        model.addAttribute("book", list);
        return "favoriteBookList";
    }

    @GetMapping("/detais")
    public String details(Model model) {
        return "bookDetails";
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        service.save(b);
        return "redirect:/";

    }

    @RequestMapping("/details/{id}")
    public String showDetails(@PathVariable("id") int id, Model model) {
        Book b = service.getBookById(id);
        dService.save(b);
        model.addAttribute("book", b);
        return "bookDetails";

    }

    @RequestMapping("/favorite/{id}")
    public String getFavorites(@PathVariable("id") int id) {
        Book b = service.getBookById(id);
        FavoriteBook f = new FavoriteBook(b.getId(), b.getBookName(), null, b.getPrice(), b.getIsbn_nummer());
        fService.saveAsFavorite(f);
        return "redirect:/favorites";
    }
}
