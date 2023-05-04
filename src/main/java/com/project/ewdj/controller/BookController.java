package com.project.ewdj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.project.ewdj.entity.Book;
import com.project.ewdj.entity.Favorite;
import com.project.ewdj.service.BookService;
import com.project.ewdj.service.DetailsService;
import com.project.ewdj.service.FavoriteService;
import com.project.ewdj.service.UserService;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private DetailsService dService;

    @Autowired
    private FavoriteService fService;

    @Autowired
    private UserService uService;

    @GetMapping("/")
    public ModelAndView home() {
        List<Book> list = service.getAllBooks();
        // ModelAndView m = new ModelAndView();
        // m.setViewName("home");
        // m.addObject("book", list);
        return new ModelAndView("home", "book", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        service.save(b);
        return "redirect:/";

    }

    @GetMapping("/admin/add_book")
    public String addBook() {
        return "add_book";
    }

    @GetMapping("/favorites")
    public String getAllFavoriteBooks(Model model) {
        List<Favorite> list = fService.getAllFavorites();
        model.addAttribute("book", list);
        return "favoriteBookList";
    }

    @RequestMapping("/favorites/{id}")
    public String getFavorites(@PathVariable("id") Long id) {
        Book b = service.getBookById(id);
        Favorite f = new Favorite(b.getBookName(), uService.findAllUsers());
        fService.saveAsFavorite(f);
        return "redirect:/favorites";
    }

    @GetMapping("/details")
    public String details(Model model) {
        return "bookDetails";
    }

    @RequestMapping("/details/{id}")
    public String showDetails(@PathVariable("id") Long id, Model model) {
        Book b = service.getBookById(id);
        dService.save(b);
        model.addAttribute("book", b);
        return "bookDetails";

    }

    // @PutMapping("/{book_id}/author/{author_id}")
    // public Book assignAuthorToBook(@PathVariable Long book_id, @PathVariable Long
    // author_id) {
    // return service.assignAuthorToBook(book_id, author_id);
    // }

}
