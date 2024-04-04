package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Utilisateur;

import java.security.Principal;

public interface UtilisateurDAO {

    Utilisateur read(String pseudo);
    Utilisateur read(Principal p);

    Utilisateur findByPseudo(String pseudo);

    Utilisateur findByPseudoForMainPage(String pseudo);

    void create(Utilisateur utilisateur);

    void update(Utilisateur u);

    boolean findPseudo(String ps);
    boolean findEmail(String e);

}
