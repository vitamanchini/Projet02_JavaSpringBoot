package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.AccueilService;
import fr.eni.spring.Projet02.bll.contexte.ContexteService;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/accueil")
@SessionAttributes({ "utilisateurEnSession"})
public class AccueilController {

    private ContexteService contexteService;
    private AccueilService accueilService;

    public AccueilController(ContexteService contexteService, @Qualifier("Accueil") AccueilService accueilService) {
        this.contexteService = contexteService;
        this.accueilService = accueilService;
    }
    @GetMapping
    public String accueilPage(@ModelAttribute("articleAVendre")ArticleAVendre lot, Model model){
        List<ArticleAVendre> lots= accueilService.findAll();
        model.addAttribute("articleAVendre", lots);
        return "index";
    }

    @GetMapping("/filter")
    public void filterArticles(){


    }

    public void test(){
        System.out.println(accueilService.findAll());
    }
}
