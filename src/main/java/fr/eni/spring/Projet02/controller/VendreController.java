package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.AdresseService;
import fr.eni.spring.Projet02.bll.ArticleService;
import fr.eni.spring.Projet02.bll.contexte.ContexteService;
import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
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

import static fr.eni.spring.Projet02.exceptions.BusinessCode.VALIDATION_ERROR_DISCONNECTED;

@Controller
@RequestMapping("/vendre")
public class VendreController {

    private ContexteService contexteService;
    private ArticleService articleService;
    private AdresseService adresseService;

    public VendreController(ContexteService contexteService, ArticleService articleService, AdresseService adresseService) {
        this.contexteService = contexteService;
        this.articleService = articleService;
        this.adresseService=adresseService;
    }

    @GetMapping("/vendre")
    public String Vendre(Principal p) {
        if(p == null){
            return "redirect:/accueil";
        } else {
            return "vente-article";
        }
    }




    @PostMapping("/vendre")
    public String creerArticleAVendre(@Valid @ModelAttribute("articleAVendre") ArticleAVendre articleAVendre, BindingResult bindingResult, Principal p){
        if(p!=null && p.getName()!=null){
            if(!bindingResult.hasErrors()){
                try {
                    System.out.println(articleAVendre);
                    articleService.creerArticle(articleAVendre);
                    return "redirect:/accueil";
                } catch (BusinessException e){
                    e.getClefsExternalisations().forEach(key ->{
                        ObjectError error = new ObjectError("globalError",key);
                        bindingResult.addError(error);
                    });
                }
            }
        }else {
            System.out.println("VALIDATION_ERROR_DISCONNECTED");
            ObjectError error = new ObjectError("globalError", VALIDATION_ERROR_DISCONNECTED);
            bindingResult.addError(error);
        }
        return "vente-article";
    }

    @ModelAttribute
    public Adresse afficherAdresses(@RequestParam(name = "no_adresse",required = true) int idAdresse){
        return articleService.consulterAdresseParId(idAdresse);
    }


}