package com.project.ewdj.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.ewdj.entity.Book;
import com.project.ewdj.repository.FavoriteRepository;
import com.project.ewdj.service.BookService;
import com.project.ewdj.service.FavoriteService;
import com.project.ewdj.util.HomeListItem;

import jakarta.transaction.Transactional;

@Controller
public class FavoriteController {

    @Autowired
    private FavoriteService fService;

    @Autowired
    private FavoriteRepository fRepo;

    @Autowired
    private BookService service;

    @Transactional
    @RequestMapping("/deleteMyList/{id}")
    public String deleteMyList(@PathVariable("id") Long id, RedirectAttributes redirAtt) {
        Book b = service.getBookById(id);
        fRepo.deleteByBookId(b.getId());
        // Book b = service.getBookById(id);
        redirAtt.addFlashAttribute("message", "Book " + b.getBookName() + " removed ");
        return "redirect:/?success";
    }

}