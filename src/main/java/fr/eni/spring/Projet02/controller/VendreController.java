package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.AdresseService;
import fr.eni.spring.Projet02.bll.ArticleService;
import fr.eni.spring.Projet02.bll.UserService;
import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/vendre")
@SessionAttributes({"ListCategories"})
public class VendreController {
    private ArticleService articleService;
    private AdresseService adresseService;
    private UserService userService;

    public VendreController(ArticleService articleService, AdresseService adresseService, UserService userService) {
        this.articleService = articleService;
        this.adresseService = adresseService;
        this.userService = userService;
    }

    @GetMapping
    public String vendre(Principal p,
                         @ModelAttribute("ListCategories") List<Categorie> categories,
                         Model model) {

        if(p == null || p.getName() == null){
            return "redirect:/accueil";
        } else {
            Utilisateur u = userService.read(p.getName());
            List<Adresse> l = adresseService.consulterAdressesVendeur(u.getPseudo());

            model.addAttribute("article", new ArticleAVendre())
                    .addAttribute("categories", categories)
                    .addAttribute("adresses",l);


//            p.getName();
//            ArticleAVendre articleAVendre = new ArticleAVendre();
//            articleAVendre.setDateDebutEncheres();
//            model.addAttribute("aVendre",articleAVendre);
            return "vente-article";
        }
    }


}