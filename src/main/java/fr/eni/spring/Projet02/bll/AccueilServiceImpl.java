package fr.eni.spring.Projet02.bll;


import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.dal.AccueilDAO;
import fr.eni.spring.Projet02.dal.CategorieDAO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("Accueil")
public class AccueilServiceImpl implements AccueilService{

    private AccueilDAO accueilDAO;
    private CategorieDAO categorieDAO;

    public AccueilServiceImpl(@Qualifier("accDAO") AccueilDAO accueilDAO,
                              @Qualifier("CatDao") CategorieDAO categorieDAO) {
        this.accueilDAO = accueilDAO;
        this.categorieDAO = categorieDAO;
    }

    @Override
    public List<ArticleAVendre> findAll() {
        return accueilDAO.findAll();
    }

    @Override
    public List<ArticleAVendre> filter() {
        return null;
    }

    @Override
    public List<Categorie> findAllCategories() {
        return categorieDAO.findAll();
    }
}