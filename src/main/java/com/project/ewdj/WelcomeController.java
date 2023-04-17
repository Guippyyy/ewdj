package com.project.ewdj;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import domein.AlbumBean;
import domein.ExpertBean;
import persistence.BoekRepository;

@Controller
@RequestMapping("welcome")
public class WelcomeController {

    @Autowired
    private ExpertBean expertBean;

    @Autowired
    private BoekRepository boekRepository;

    @ModelAttribute("albumList")
    public List<String> populateColors() {
        return (new AlbumBean()).getAlbumList();
    }

    @GetMapping
    public String showHomePage(Model model) {
        // //model.addAttribute("colorsList", (new ColorBean()).getColorsList());
        model.addAttribute("welcomeCommand", new WelcomeCommand());
        return "welcomePage";
    }

    @PostMapping
    public String onSubmit(WelcomeCommand welcomeCommand, Model model) {
        model.addAttribute("listSong", expertBean.getExpert(welcomeCommand.getAlbumSelected()));
        return "resultPage";
    }

}
