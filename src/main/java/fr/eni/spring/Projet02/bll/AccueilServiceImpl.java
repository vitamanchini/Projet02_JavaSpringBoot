package fr.eni.spring.Projet02.bll;


import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.bo.Utilisateur;
import fr.eni.spring.Projet02.dal.ArticleAVendreDAO;
import fr.eni.spring.Projet02.dal.CategorieDAO;
import fr.eni.spring.Projet02.dal.UtilisateurDAO;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
        return articleAVendreDAO.findAll();
    }

    @Override
    public List<ArticleAVendre> filter() {
        return null;
    }

    @Override
    public List<Categorie> findAllCategories() {
        return categorieDAO.findAll();
    }
    @Override
    public Utilisateur test(String pseudo){
        if (pseudo !=)
        return utilisateurDAO.read(pseudo);
    }

    public void createNewUser(Utilisateur u){
        BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validateUser(u, be);
        isValid &= validatePseudo(u.getPseudo(), be);
        isValid &= validatePseudoUnicity(u, be);
        isValid &= validateNom(u.getNom(), be);
        isValid &= validatePrenom(u.getPrenom(), be);
        isValid &= validateEmail(u.getEmail(), be);
        isValid &= validateEmailUnicity(u.getEmail(), be);
        isValid &= validateTelephone(u.getTelephone(), be);
        isValid &= validatePassword(u.getMotDePasse(), be);
        isValid &= validateAdresse(u.getAdresse(), be);

        if(isValid){
            utilisateurDAO.create(u);
        } else {
            throw be;
        }

    }

    private boolean validateUser(Utilisateur u, BusinessException be) {
        if (u == null){
            be.add(BusinessException."grrrr");
        }
    }

    private boolean validatePseudo(String pseudo, BusinessException be) {
    }

    private boolean validatePseudoUnicity(Utilisateur u, BusinessException be) {
    }

    private boolean validateNom(String nom, BusinessException be) {
    }

    private boolean validatePrenom(String prenom, BusinessException be) {
    }

    private boolean validateEmail(String email, BusinessException be) {
    }

    private boolean validateEmailUnicity(String email, BusinessException be) {
    }

    private boolean validateTelephone(String telephone, BusinessException be) {
    }

    private boolean validatePassword(String motDePasse, BusinessException be) {
    }

    private boolean validateAdresse(Adresse adresse, BusinessException be) {
    }
}
