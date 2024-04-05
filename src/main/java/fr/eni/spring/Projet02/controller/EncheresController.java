package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.ArticleService;
import fr.eni.spring.Projet02.bll.EnchereService;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Enchere;
import fr.eni.spring.Projet02.bo.Utilisateur;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("enchere")
public class EncheresController {

    private EnchereService enchereService;
    private ArticleService articleService;

    public EncheresController(EnchereService enchereService, ArticleService articleService) {
        this.enchereService = enchereService;
        this.articleService = articleService;
    }

    @GetMapping
    public String pageEnchere(@RequestParam("id") String id,
                              Model model
    ) {
        long idConvert = Long.parseLong(id);
        ArticleAVendre a = enchereService.findArticleById(idConvert);

        model.addAttribute("article", a);
        model.addAttribute("enchere", new Enchere());
        return "page-article";
    }

    @PostMapping
    public String createEnchere(Principal p,
                                @Valid @ModelAttribute("enchere") Enchere enchere,
//                                @ModelAttribute("article") ArticleAVendre a,
                                @RequestParam("id") String id,
                                BindingResult bindingResult) {

        if (p == null || p.getName() == null) {
            return "redirect:/accueil";
        } else {
            try {
                long idConvert = Long.parseLong(id);
                ArticleAVendre a = enchereService.findArticleById(idConvert);
                enchere.setArticleAVendre(a);
                Utilisateur user = new Utilisateur();
                user.setPseudo(p.getName());
                enchere.setAcquereur(user);

                enchere.setDate(LocalDateTime.now());
                enchere.setArticleAVendre(a);
                enchereService.createEnchere(enchere);
                return "redirect:/users/profile";
            } catch (BusinessException be) {
                be.getClefsExternalisations().forEach(key -> {
                    ObjectError objectError = new ObjectError("globalError", key);
                    bindingResult.addError(objectError);
                });
            }
        }
        return "redirect:/enchere"+"?id=" + id;
    }

}
