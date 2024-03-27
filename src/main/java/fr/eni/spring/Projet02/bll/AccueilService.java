package fr.eni.spring.Projet02.bll;


import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;

import java.util.List;

public interface AccueilService {
    List<ArticleAVendre> findAll();
    List<ArticleAVendre> filter();
    List<Categorie> findAllCategories();
}
