package fr.eni.spring.Projet02.controller;

import fr.eni.spring.Projet02.bll.UserService;
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
            return "page-new-user";

        }
    }

    @GetMapping("/profile")
    public String userProfilePage(Principal p, Utilisateur u, Model model,
                                  BindingResult bindingResult) {
        if (p == null || p.getName() == null) {
            return "redirect:/accueil";
        } else {
            try {
                u = userService.read(p.getName());
                System.out.println(p);
                model.addAttribute("user", u);

                    return "page-user-profile";

            } catch (BusinessException e) {
                e.getClefsExternalisations().forEach(key -> {
                    ObjectError objectError = new ObjectError("globalError", key);
                    bindingResult.addError(objectError);
                });
            }
        }
            return "redirect:/accueil";
    }
    @GetMapping("/modify")
    public String modifyUserInfo(Principal p,
                                 Utilisateur u,
                                 Model model
    ){
        if (p == null || p.getName() == null) {
            return "redirect:/accueil";
        } else {
            Utilisateur newbee = new Utilisateur();
            Utilisateur userFromDB = userService.read(p.getName());
            newbee.setPseudo(userFromDB.getPseudo());

            model.addAttribute("user", newbee);

                return "page-modify-user-profile";
        }

    }
    @PostMapping("/modify")
    public String saveChangesUserProfile(Principal p,
            @Valid @ModelAttribute("user") Utilisateur u,
            BindingResult bindingResult
    ) {

        if (p == null || p.getName() == null) {
            return "redirect:/accueil";

        } else {
            try{
                    userService.updateUser(u);
                    return "redirect:/users/profile";
                } catch (BusinessException be) {
                    be.getClefsExternalisations().forEach(key -> {
                        ObjectError objectError = new ObjectError("globalError", key);
                        bindingResult.addError(objectError);
                    });
                }
            }
            return "redirect:/users/profile";
        }

}