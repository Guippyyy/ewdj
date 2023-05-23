package com.project.ewdj.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.ewdj.dto.FormDto;
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
import com.project.ewdj.util.isbnValid;

import jakarta.validation.Valid;

@Controller
public class BookController {

    @Autowired
    private BookService service;

    @Autowired
    private DetailsService dService;

    @Autowired
    private FavoriteService fService;

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

    @GetMapping("/admin/add_book")
    public String addBook(Model model) {
        FormDto form = new FormDto();
        model.addAttribute("form", form);
        return "add_book";
    }

    @PostMapping("/admin/add_book")
    public String addBook(@ModelAttribute Book book, Model model, @Valid @ModelAttribute("form") FormDto formDto,
            BindingResult result, RedirectAttributes redirAtt) {

        int tel = 0;
        for (Author author : book.getAuthors()) {

            if (tel == 0) {
                author.setName(formDto.getAuthor());
            }
            System.out.println(author.getName());
            aService.save(author);
            tel++;
        }

        Book existingBook = service.getBookByISBN(formDto.getIsbnCode());
        if (existingBook != null) {
            result.rejectValue("isbnCode", null, "ISBN already registered");
        }

        if (isValidISBN(formDto.getIsbnCode())) {
            System.out.println("Valid ISBN");
        } else {
            System.out.println("Invalid ISBN");
            result.rejectValue("isbnCode", null, "INVALID ISBN");
        }

        if (result.hasErrors()) {

            System.out.println(result.getErrorCount());

            for (ObjectError error : result.getAllErrors()) {
                System.out.println("Error: " + error.getDefaultMessage());
            }

            model.addAttribute("form", formDto);
            System.out.println("im here");
            return "add_book";
        }

        // Save the book
        service.save(book);
        // Save the locations
        int tel2 = 0;
        for (Location location : book.getLocations()) {

            if (tel2 == 0) {
                location.setPlaceCode1(formDto.getPc1());
                location.setPlaceCode2(formDto.getPc2());
                location.setPlaceName(formDto.getLocation());
                location.setBook(book);
            }
            if (tel2 == 0 || !location.getPlaceName().isEmpty()) {
                location.setBook(book);
                lService.save(location);
            }
            tel2++;
        }

        redirAtt.addFlashAttribute("message", "Book " + book.getBookName() + " added !  ");

        return "redirect:/admin/add_book?success";
    }

    // this should change
    @GetMapping("/favorites")
    public String getAllFavoriteBooks(Model model) {

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

    public boolean isValidISBN(String isbn) {
        // Remove any hyphens or spaces from the ISBN
        String cleanedISBN = isbn.replaceAll("[\\s-]", "");

        // Check if the cleaned ISBN has a length of 10 or 13 digits
        if (cleanedISBN.length() != 10 && cleanedISBN.length() != 13) {
            return false;
        }

        // Calculate the check digit based on the ISBN type
        int checkDigit;
        if (cleanedISBN.length() == 10) {
            checkDigit = calculateISBN10CheckDigit(cleanedISBN);
        } else {
            checkDigit = calculateISBN13CheckDigit(cleanedISBN);
        }

        // Compare the check digit with the last digit of the ISBN
        char lastDigit = cleanedISBN.charAt(cleanedISBN.length() - 1);
        int lastDigitValue;
        if (lastDigit == 'X' || lastDigit == 'x') {
            lastDigitValue = 10;
        } else {
            lastDigitValue = Character.getNumericValue(lastDigit);
        }

        return checkDigit == lastDigitValue;
    }

    private int calculateISBN10CheckDigit(String isbn) {
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i + 1) * digit;
        }

        int checkDigit = sum % 11;
        if (checkDigit == 10) {
            return 'X';
        } else {
            return checkDigit;
        }
    }

    private int calculateISBN13CheckDigit(String isbn) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Character.getNumericValue(isbn.charAt(i));
            sum += (i % 2 == 0) ? digit : digit * 3;
        }

        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit;
    }
}
