package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.UserService;
import fr.eni.spring.Projet02.bll.contexte.ContexteService;
import fr.eni.spring.Projet02.bo.Utilisateur;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/users")
@SessionAttributes({"utilisateurEnSession", "ListCategories"})
public class UserController {

    private ContexteService contexteService;
    private UserService userService;

    public UserController(ContexteService contexteService, UserService userService) {
        this.contexteService = contexteService;
        this.userService = userService;
    }

    @GetMapping("/signin")
    public String signin(Principal p, Model model) {
        if (p != null && p.getName() != null) {
            return "redirect:/accueil";
        } else {
            model.addAttribute("user", new Utilisateur());
            return "page-new-user";
        }
    }

    @PostMapping("/signin")
    public String createUser(Principal p, @Valid @ModelAttribute("user") Utilisateur u, BindingResult bindingResult) {
        if (p != null && p.getName() != null) {
            return "redirect:/accueil";

        } else {
            if (!bindingResult.hasErrors()) {
                try {
                    userService.createNewUser(u);
                    return "redirect:/accueil";
                } catch (BusinessException be) {
                    be.getClefsExternalisations().forEach(key -> {
                        ObjectError objectError = new ObjectError("globalError", key);
                        bindingResult.addError(objectError);
                    });
                }
            }
//            return "redirect:/users/signin";
            return "page-new-user";

        }
    }
}