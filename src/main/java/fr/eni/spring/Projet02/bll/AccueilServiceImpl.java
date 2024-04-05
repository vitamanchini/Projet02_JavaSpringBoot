package fr.eni.spring.Projet02.bll;


import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.bo.Utilisateur;
import fr.eni.spring.Projet02.dal.ArticleAVendreDAO;
import fr.eni.spring.Projet02.dal.CategorieDAO;
import fr.eni.spring.Projet02.dal.UtilisateurDAO;
import fr.eni.spring.Projet02.exceptions.BusinessCode;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class AccueilServiceImpl implements AccueilService{

    private ArticleAVendreDAO articleAVendreDAO;
    private CategorieDAO categorieDAO;
    private UtilisateurDAO utilisateurDAO;

    public AccueilServiceImpl(ArticleAVendreDAO articleAVendreDAO,
                              CategorieDAO categorieDAO,
                              UtilisateurDAO utilisateurDAO) {
        this.articleAVendreDAO = articleAVendreDAO;
        this.categorieDAO = categorieDAO;
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public List<ArticleAVendre> findAll() {
        try {
            List<ArticleAVendre> articles = articleAVendreDAO.readAll();
            if (articles != null) {
                articles.forEach(this::loadSeller);
                return articles;
            }
        } catch (BusinessException be){
            be.add(BusinessCode.ERROR_ARTICLES_LOAD);
        }

        return null;
    }

    @Override
    public List<ArticleAVendre> findAllFinish() {
        try {
            List<ArticleAVendre> articles = articleAVendreDAO.readAllFinish();
            if (articles != null) {
                articles.forEach(this::loadSeller);
                return articles;
            }
        } catch (BusinessException be){
            be.add(BusinessCode.ERROR_ARTICLES_LOAD);
        }

        return null;
    }
    @Override
    public List<ArticleAVendre> findAllNotStarted() {
        try {
            List<ArticleAVendre> articles = articleAVendreDAO.readAllNotStarted();
            if (articles != null) {
                articles.forEach(this::loadSeller);
                return articles;
            }
        } catch (BusinessException be){
            be.add(BusinessCode.ERROR_ARTICLES_LOAD);
        }

        return null;
    }
    @Override
    public List<ArticleAVendre> findAllMesEncheresEnCours(Utilisateur u) {

            try {
                List<ArticleAVendre> articles = articleAVendreDAO.readAllMesEncheresEnCours();
                if (articles != null) {
                    articles.forEach(this::loadSeller);
                    return articles;
                }
            } catch (BusinessException be){
                be.add(BusinessCode.ERROR_ARTICLES_LOAD);
            }

        return null;
    }
    @Override
    public List<ArticleAVendre> findAllMesEncheresFinies(Utilisateur u) {

        try {
            List<ArticleAVendre> articles = articleAVendreDAO.readAllMesEncheresFinies();
            if (articles != null) {
                articles.forEach(this::loadSeller);
                return articles;
            }
        } catch (BusinessException be){
            be.add(BusinessCode.ERROR_ARTICLES_LOAD);
        }

        return null;
    }

    @Override
    public List<ArticleAVendre> findAllStarted() {
        try {
            List<ArticleAVendre> articles = articleAVendreDAO.readAllCurrent();
            if (articles != null) {
                articles.forEach(this::loadSeller);
                return articles;
            }
        } catch (BusinessException be){
            be.add(BusinessCode.ERROR_ARTICLES_LOAD);
        }

        return null;
    }

    private void loadSeller(ArticleAVendre av){
        try {
            Utilisateur u = utilisateurDAO.findByPseudoForMainPage(av.getVendeur().getPseudo());
            av.setVendeur(u);
        } catch (NullPointerException npe){}
    }

    @Override
    public List<ArticleAVendre> filter() {
        return null;
    }

    @Override
    public List<Categorie> findAllCategories() {
        List<Categorie> temp = categorieDAO.findAll();
        try{
            if (temp != null) return temp;
        } catch (BusinessException e) {
            e.add(BusinessCode.ERROR_CATEGORIES_LOAD);
        }
        return null;
    }


}
