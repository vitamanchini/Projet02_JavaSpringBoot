package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.AccueilService;
import fr.eni.spring.Projet02.bll.UserService;
import fr.eni.spring.Projet02.bll.contexte.ContexteService;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/accueil")
@SessionAttributes({ "utilisateurEnSession", "ListCategories"})
public class AccueilController {

    private ContexteService contexteService;
    private AccueilService accueilService;
    private UserService userService;

    public AccueilController(ContexteService contexteService, AccueilService accueilService, UserService userService) {
        this.contexteService = contexteService;
        this.accueilService = accueilService;
        this.userService = userService;
    }
    @ModelAttribute("ListCategories")
    public List<Categorie> loadCategories(){
        return accueilService.findAllCategories();
    }
    
    @GetMapping
    public String accueilPage(@ModelAttribute("articleAVendre")ArticleAVendre lot, Model model){
        List<ArticleAVendre> lots= accueilService.findAll();
        model.addAttribute("articleAVendre", lots);
        return "index";
    }


    public String accueilMesEncheresEnCours(@ModelAttribute("mesEncheresEnCours")ArticleAVendre mesEncheresEnCours, Model model, Principal p, Utilisateur u){
        u = userService.read(mesEncheresEnCours.getId());
        if (p!=u){
            List<ArticleAVendre> mesEncheresEnCourss = accueilService.findAllMesEncheresEnCours(p,u);
            model.addAttribute("mesEncheresEnCours", mesEncheresEnCourss);
        }
        return "index";
    }


    public String accueilMesEncheresRemportees(@ModelAttribute("mesEncheresRemportees")ArticleAVendre mesEncheresRemportees, Model model, Principal p, Utilisateur u){
        u = userService.read(mesEncheresRemportees.getId());
        if (p!=u){
            List<ArticleAVendre> mesEncheresRemporteess = accueilService.findAllMesEncheresFinies(p,u);
            model.addAttribute("mesEncheresRemportees", mesEncheresRemporteess);
        }
        return "index";
    }


    public String accueilMesVentesNonDebutees(@ModelAttribute("mesVentesNonDebutees")ArticleAVendre mesVentesNonDebutees, Model model, Principal p, Utilisateur u){
        List<ArticleAVendre> mesVentesNonDebuteess = accueilService.findAllNotStarted();
        model.addAttribute("mesVentesNonDebutees", mesVentesNonDebuteess);
        return "index";
    }


    public String accueilMesVentesFinies(@ModelAttribute("mesVentesFinies")ArticleAVendre mesVentesFinies, Model model, Principal p, Utilisateur u){
        List<ArticleAVendre> mesVentesFiniess = accueilService.findAllFinish();
        model.addAttribute("mesVentesFinies", mesVentesFiniess);
        return "index";
    }


    public String accueilMesVentesEnCours(@ModelAttribute("mesVentesEnCours")ArticleAVendre mesVentesEnCours, Model model, Principal p, Utilisateur u){
        List<ArticleAVendre> mesVentesEnCourss = accueilService.findAllStarted();
        model.addAttribute("mesVentesEnCours", mesVentesEnCourss);
        return "index";
    }

    @GetMapping("/filter")
    public void filterArticles(@ModelAttribute("utilisateurEnSession") Utilisateur u){

    }

    @GetMapping("signin")
    public String signin(){
        return "page-new-user";
    }

}
