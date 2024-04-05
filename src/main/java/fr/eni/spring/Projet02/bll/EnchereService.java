package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Enchere;

public interface EnchereService {
    Enchere read (long articleNumber);

    ArticleAVendre findArticleById(long id);

    void createEnchere(Enchere e);
}
