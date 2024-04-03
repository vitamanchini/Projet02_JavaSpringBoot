package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.AdresseService;
import fr.eni.spring.Projet02.bll.ArticleService;
import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static fr.eni.spring.Projet02.exceptions.BusinessCode.VALIDATION_ERROR_DISCONNECTED;

@RequestMapping("/toto")
@Controller
public class VendreController2 {
    @GetMapping("/toto")
    public String toto(){
        return "vente-article";
    }
    private ArticleService articleService;
    private AdresseService adresseService;

    public VendreController2(ArticleService articleService, AdresseService adresseService) {
        this.articleService = articleService;
        this.adresseService=adresseService;
    }

    @GetMapping()
    public String Vendre(Principal p, Model model) {
        if(p == null){
            return "redirect:/accueil";
        } else {
            ArticleAVendre articleAVendre=new ArticleAVendre();
            model.addAttribute("",articleAVendre);
            return "vente-article";
        }
    }

    @PostMapping()
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

    @ModelAttribute(name="adresses")
    public List<Adresse> afficherAdresses(long idAdresse){
        List<Adresse> l = adresseService.consulterAdressesVendeur(idAdresse);
        return l;
    }

    @ModelAttribute(name="dateDebut")
    public LocalDate dateDebutMin(LocalDate dateDebut){
        dateDebut = LocalDate.now();
        return dateDebut;
    }

    @ModelAttribute(name="dateFin")
    public LocalDate dateFinMin(LocalDate dateFin){
        dateFin = LocalDate.now().plusDays(1);
        return dateFin;
    }

    @ModelAttribute(name="categorie")
    public Categorie afficherCategories(){
        return articleService.consulterAllCategorie();
    }
}
