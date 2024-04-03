package fr.eni.spring.Projet02.bll;


import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.bo.Utilisateur;

import java.security.Principal;
import java.util.List;

public interface AccueilService {
    List<ArticleAVendre> findAll();
    List<ArticleAVendre> filter();
    List<Categorie> findAllCategories();

    List<ArticleAVendre> findAllFinish();

    List<ArticleAVendre> findAllNotStarted();

    List<ArticleAVendre> findAllMesEncheresEnCours(Principal p, Utilisateur u);

    List<ArticleAVendre> findAllMesEncheresFinies(Principal p, Utilisateur u);

    List<ArticleAVendre> findAllStarted();

}
