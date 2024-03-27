package fr.eni.spring.Projet02.controller.security;

import fr.eni.spring.Projet02.bll.contexte.ContexteService;
import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@SessionAttributes({ "utilisateurEnSession" })
public class LoginController {

    private ContexteService service;

    public LoginController(ContexteService service) {
        this.service = service;
    }

    @GetMapping("/login")
    String login(){
        return "login";
    }

    @ModelAttribute("utilisateurEnSession")
    public Utilisateur utilisateurEnSession() {
        System.out.println("Add Attribut Session");
        return new Utilisateur();
    }

    @GetMapping("/session")
    public String chargerUtilisateurEnSession(@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession,
                                         Principal principal) {
        String pseudo = principal.getName();
        Utilisateur aCharger = service.charger(pseudo);
        if (aCharger != null) {
            utilisateurEnSession.setNom(aCharger.getNom());
            utilisateurEnSession.setPrenom(aCharger.getPrenom());
            utilisateurEnSession.setPseudo(aCharger.getPseudo());
            utilisateurEnSession.setAdmin(aCharger.isAdmin());

        } else {
            utilisateurEnSession.setNom(null);
            utilisateurEnSession.setPrenom(null);
            utilisateurEnSession.setPseudo(null);
            utilisateurEnSession.setAdmin(false);

        }
        System.out.println(utilisateurEnSession);

        return "redirect:/index";
    }
}
