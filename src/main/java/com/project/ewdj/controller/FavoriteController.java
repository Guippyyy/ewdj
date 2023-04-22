package com.project.ewdj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.ewdj.service.FavoriteService;

@Controller
public class FavoriteController {

    @Autowired
    private FavoriteService fService;

    @RequestMapping("/deleteMyList/{id}")
    public String deleteMyList(@PathVariable("id") int id) {
        fService.deleteById(id);
        return "redirect:/";
    }

}