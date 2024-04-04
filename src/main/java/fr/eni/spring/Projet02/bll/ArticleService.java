package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

public interface ArticleService {

    List<ArticleAVendre> consulterArticleEnVente();

    void creerArticle(ArticleAVendre article);

    ArticleAVendre consulterArticleParId(long id);

    Utilisateur consulterVendeurParId(Principal p);

    Categorie consulterCategorieParId(long id);

    Categorie consulterAllCategorie();
    Adresse consulterAdresseParId(long id);

    LocalDate verifierDateDebut();

    LocalDate verifierDateFin();
}
