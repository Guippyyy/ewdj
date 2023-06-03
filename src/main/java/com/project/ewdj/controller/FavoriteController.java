package com.project.ewdj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.project.ewdj.entity.Book;
import com.project.ewdj.repository.FavoriteRepository;
import com.project.ewdj.service.BookService;

import jakarta.transaction.Transactional;

@Controller
public class FavoriteController {

    @Autowired
    private FavoriteRepository fRepo;

    @Autowired
    private BookService service;

    @Transactional
    @RequestMapping("/deleteMyList/{id}")
    public String deleteMyList(@PathVariable("id") Long id, RedirectAttributes redirAtt) {
        Book b = service.getBookById(id);
        fRepo.deleteByBookId(b.getId());
        redirAtt.addFlashAttribute("message", "Book " + b.getBookName() + " removed ");
        return "redirect:/?success";
    }

}