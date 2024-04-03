package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.ArticleAVendre;

import java.util.List;

public interface ArticleAVendreDAO {
    void create(ArticleAVendre articleAVendre);
    ArticleAVendre read(long id);
    List<ArticleAVendre> readAll();

    List<ArticleAVendre> readAllFinish();

    List<ArticleAVendre> readAllNotStarted();

    List<ArticleAVendre> readAllCurrent();

    List<ArticleAVendre> readAllMesEncheresEnCours();

    List<ArticleAVendre> readAllMesEncheresFinies();
}
