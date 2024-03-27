package fr.eni.spring.Projet02.bll;


import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.bo.Utilisateur;

import java.util.List;

public interface AccueilService {
    List<ArticleAVendre> findAll();
    List<ArticleAVendre> filter();
    List<Categorie> findAllCategories();

    Utilisateur test(String pseudo);
}
