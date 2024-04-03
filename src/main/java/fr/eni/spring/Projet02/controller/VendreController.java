package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.AdresseService;
import fr.eni.spring.Projet02.bll.ArticleService;
import fr.eni.spring.Projet02.bll.contexte.ContexteService;
import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.bo.Utilisateur;
import fr.eni.spring.Projet02.exceptions.BusinessCode;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static fr.eni.spring.Projet02.exceptions.BusinessCode.VALIDATION_ERROR_DISCONNECTED;

@Controller
@RequestMapping({"/vendre"})
public class VendreController {
    private ArticleService articleService;
    private AdresseService adresseService;
    public VendreController(ArticleService articleService, AdresseService adresseService) {
        this.articleService = articleService;
        this.adresseService= adresseService;
    }

    @GetMapping()
    public String vendre(Principal p, Model model) {

        System.out.println("Point d'arrêt");
        if(p == null){
            return "redirect:/accueil";
        } else {
            p.getName();
            List<Adresse> l = adresseService.consulterAdressesVendeur(p);
            model.addAttribute("adresses",l);
            ArticleAVendre articleAVendre = new ArticleAVendre();
            articleAVendre.setDateDebutEncheres();
            model.addAttribute("aVendre",articleAVendre);
            return "vente-article";
        }
    }


}