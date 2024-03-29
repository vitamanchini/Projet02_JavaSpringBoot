package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Utilisateur;

public interface UtilisateurDAO {
    static Utilisateur read(String pseudo);
}
