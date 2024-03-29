package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.*;

import java.util.List;

public interface ArticleService {

    List<ArticleAVendre> consulterArticleEnVente();

    void creerArticle(Enchere auction);

    ArticleAVendre consulterArticleParId(long id);

    Utilisateur consulterVendeurParId(String id);

    Categorie consulterCategorieParId(long id);

    Adresse consulterAdresseParId(long id);
}
