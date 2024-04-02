package fr.eni.spring.Projet02.controller.security;

import fr.eni.spring.Projet02.bll.contexte.ContexteService;
import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller

public class LoginController {

    private ContexteService service;

    public LoginController(ContexteService service) {
        this.service = service;
    }

    @GetMapping("/login")
    String login(){
        return "login";
    }


    @GetMapping("/session")
    public String chargerUtilisateurEnSession(Principal p) {
        if (p != null) {
            p.getName();
        }
        System.out.println(p);
        return "redirect:/index";
    }
}
