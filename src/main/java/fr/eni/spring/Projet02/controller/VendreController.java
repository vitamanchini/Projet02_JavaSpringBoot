package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.AccueilService;
import fr.eni.spring.Projet02.bll.contexte.ContexteService;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequestMapping("/vendre")
@SessionAttributes({ "utilisateurEnSession"})
public class VendreController {

    private ContexteService contexteService;

    public VendreController(ContexteService contexteService) {
        this.contexteService = contexteService;
    }
}