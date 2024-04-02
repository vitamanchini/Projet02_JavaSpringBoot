package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Utilisateur;

public interface UtilisateurDAO {

    Utilisateur read(String pseudo);

    Utilisateur findByPseudo(String pseudo);

    Utilisateur findByPseudoForMainPage(String pseudo);

    void create(Utilisateur utilisateur);

    void update(Utilisateur u);

    boolean findPseudo(String ps);
    boolean findEmail(String e);
    Utilisateur findByAddress(long idAdresse);
}
