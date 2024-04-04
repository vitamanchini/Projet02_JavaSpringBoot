package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.AdresseService;
import fr.eni.spring.Projet02.bll.ArticleService;
import fr.eni.spring.Projet02.bll.UserService;
import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.bo.Utilisateur;
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
            LocalDate dateDebut = articleService.verifierDateDebut();
            LocalDate dateFin = articleService.verifierDateFin();

            model.addAttribute("article", new ArticleAVendre())
                    .addAttribute("categories", categories)
                    .addAttribute("adresses",l)
                    .addAttribute("dateDebut",dateDebut)
                    .addAttribute("dateFin",dateFin);
            return "vente-article";
        }
    }

    @PostMapping
    public String createArticle(Principal p, @Valid @ModelAttribute("article") ArticleAVendre a, BindingResult bindingResult) {
        Utilisateur u = userService.read(p.getName());
        if (!bindingResult.hasErrors()) {
            try {
                a.setVendeur(u);
                articleService.creerArticle(a);
                return "redirect:/accueil";
            } catch (BusinessException be) {
                be.getClefsExternalisations().forEach(key -> {
                    ObjectError objectError = new ObjectError("globalError", key);
                    bindingResult.addError(objectError);
                });
            }
        }
        return "vente-article";
    }
}