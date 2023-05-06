package com.project.ewdj.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.ewdj.entity.Book;
import com.project.ewdj.entity.Favorite;
import com.project.ewdj.service.BookService;
import com.project.ewdj.service.DetailsService;
import com.project.ewdj.service.FavoriteService;
import com.project.ewdj.service.UserService;
import com.project.ewdj.util.HomeListItem;

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
        Set<Book> favorites = fService.getUserFavorites();

        List<HomeListItem> items = new ArrayList<>();
        for (var book : list) {
            items.add(new HomeListItem(book, favorites.contains(book)));
        }

        return new ModelAndView("home", "items", items);
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

    // this should change
    @GetMapping("/favorites")
    public String getAllFavoriteBooks(Model model, Model m2) {

        Set<Book> list = fService.getUserFavorites();
        model.addAttribute("book", list);
        // m2.addAttribute(null, list)
        return "favoriteBookList";
    }

    @RequestMapping("/favorites/{id}")
    public String getFavorites(@PathVariable("id") Long id, Model m2, RedirectAttributes redirAttrs) {
        Book b = service.getBookById(id);
        fService.saveAsFavorite(b);
        redirAttrs.addFlashAttribute("message", "Book " + b.getBookName() + " added!");

        return "redirect:/favorites?success";
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

}
