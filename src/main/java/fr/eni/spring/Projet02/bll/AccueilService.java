package fr.eni.spring.Projet02.bll;


import fr.eni.spring.Projet02.bo.ArticleAVendre;

import java.util.List;

public interface AccueilService {
    List<ArticleAVendre> findAll();
    List<ArticleAVendre> filter();
}
