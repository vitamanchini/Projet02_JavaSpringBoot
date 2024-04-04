package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.EnchereService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("enchere")
public class EncheresController {

    private EnchereService enchereService;

    @GetMapping
    public String createEnchere(){
        return "page-article";
    }
}
