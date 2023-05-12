package com.project.ewdj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.project.ewdj.entity.Author;
import com.project.ewdj.entity.Book;
import com.project.ewdj.entity.Favorite;
import com.project.ewdj.entity.Location;
import com.project.ewdj.service.AuthorService;
import com.project.ewdj.service.BookService;
import com.project.ewdj.service.DetailsService;
import com.project.ewdj.service.FavoriteService;
import com.project.ewdj.service.LocationService;
import com.project.ewdj.service.UserService;
import com.project.ewdj.util.FavoriteUtils;
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

    @Autowired
    AuthorService aService;

    @Autowired
    private LocationService lService;

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
    public String addBook(@ModelAttribute Book b, @ModelAttribute Author a, @ModelAttribute Location l,
            @ModelAttribute Author av) {
        Author a1 = a;
        Author a2 = av;
        aService.save(a1);
        aService.save(a2);

        Book b1 = b;

        b1.getAuthors().add(a1);
        b1.getAuthors().add(a2);
        service.save(b1);

        Location l1 = l;
        l.setBook(b1);
        lService.save(l1);

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

        List<Favorite> Flist = fService.getFavorites();
        List<Location> Llist = lService.getLocations();
        List<Object[]> result = FavoriteUtils.countDuplicates(Flist);

        var star = 0;

        for (Object[] obj : result) {

            Long bookId = (Long) obj[0];
            String bookName = (String) obj[1];
            Integer count = (Integer) obj[2];
            if (bookId == id) {
                star = count;
            }
            System.out.println("Book ID: " + bookId + " | Book Name: " + bookName + " | Count: " + count);
        }

        List<Location> loc = new ArrayList<>();
        for (Location l : Llist) {

            if (l.getBook().getId() == id) {
                loc.add(l);
            }

        }

        model.addAttribute("loc", loc);
        model.addAttribute("book", b);
        model.addAttribute("star", star);
        return "bookDetails";

    }

    @GetMapping("/mostPop")
    public String mostPop(Model model) {

        List<Favorite> Flist = fService.getFavorites();
        List<Object[]> result = FavoriteUtils.countDuplicates(Flist);

        for (Object[] obj : result) {
            Long bookId = (Long) obj[0];
            String bookName = (String) obj[1];
            Integer count = (Integer) obj[2];

            System.out.println("Book ID: " + bookId + " | Book Name: " + bookName + " | Count: " + count);
        }
        model.addAttribute("items", result);

        return "mostPop";
    }

    public <T> Map<T, Long> countByForEachLoopWithGetOrDefault(List<T> inputList) {
        Map<T, Long> resultMap = new HashMap<>();
        inputList.forEach(e -> resultMap.put(e, resultMap.getOrDefault(e, 0L) + 1L));
        System.out.println(resultMap);
        return resultMap;
    }

    public <T> Map<T, Long> countByClassicalLoop(List<T> inputList) {
        Map<T, Long> resultMap = new HashMap<>();
        for (T element : inputList) {
            if (resultMap.containsKey(element)) {
                resultMap.put(element, resultMap.get(element) + 1L);
            } else {
                resultMap.put(element, 1L);
            }
        }
        return resultMap;
    }
}
