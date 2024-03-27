package fr.eni.spring.Projet02.bll;


import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.bo.Utilisateur;
import fr.eni.spring.Projet02.dal.AdresseDAO;
import fr.eni.spring.Projet02.dal.ArticleAVendreDAO;
import fr.eni.spring.Projet02.dal.CategorieDAO;
import fr.eni.spring.Projet02.dal.UtilisateurDAO;
import fr.eni.spring.Projet02.exceptions.BusinessCode;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccueilServiceImpl implements AccueilService{

    private ArticleAVendreDAO articleAVendreDAO;
    private CategorieDAO categorieDAO;
    private UtilisateurDAO utilisateurDAO;
    private AdresseDAO adresseDAO;

    public AccueilServiceImpl(ArticleAVendreDAO articleAVendreDAO,
                              CategorieDAO categorieDAO,
                              UtilisateurDAO utilisateurDAO,
                              AdresseDAO adresseDAO) {
        this.articleAVendreDAO = articleAVendreDAO;
        this.categorieDAO = categorieDAO;
        this.utilisateurDAO = utilisateurDAO;
        this.adresseDAO = adresseDAO;
    }

    @Override
    public List<ArticleAVendre> findAll() {
        List<ArticleAVendre> articles = articleAVendreDAO.readAll();
        if (articles != null) {
            articles.forEach(this::loadSeller);

        }
            return articles;

    }

    private void loadSeller(ArticleAVendre av){
        Utilisateur u = utilisateurDAO.findByPseudoForMainPage(av.getVendeur().getPseudo());
        av.setVendeur(u);
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
            be.add(BusinessCode.VALIDATION_USER_NULL);
            return false;
        } else
            return true;
    }

    private boolean validatePseudo(String pseudo, BusinessException be) {

        if (pseudo.isBlank() || pseudo == null){
            be.add(BusinessCode.VALIDATION_USER_PSEUDO_BLANK);
            return false;
        }
        return true;
    }

    private boolean validatePseudoUnicity(Utilisateur u, BusinessException be) {

        if(!utilisateurDAO.findPseudo(u.getPseudo())){
            be.add(BusinessCode.VALIDATION_USER_PSEUDO_UNICITY);
            return false;
        }
        return true;

    }

    private boolean validateNom(String nom, BusinessException be) {

        if (nom.isBlank() || nom == null){
            be.add(BusinessCode.VALIDATION_USER_SURNAME_BLANK);
            return false;
        }
        if (nom.length() > 40) {
            be.add(BusinessCode.VALIDATION_USER_SURNAME_LENGTH);
            return false;
        }
        return true;
    }

    private boolean validatePrenom(String prenom, BusinessException be) {

        if (prenom.isBlank() || prenom == null){
            be.add(BusinessCode.VALIDATION_USER_NAME_BLANK);
            return false;
        }
        if (prenom.length() > 50) {
            be.add(BusinessCode.VALIDATION_USER_NAME_LENGTH);
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email, BusinessException be) {
        if (email.isBlank() || email == null){
            be.add(BusinessCode.VALIDATION_USER_EMAIL_BLANK);
            return false;
        }
        return true;
    }

    private boolean validateEmailUnicity(String email, BusinessException be) {
        if(!utilisateurDAO.findEmail(email)){
            be.add(BusinessCode.VALIDATION_USER_EMAIL_UNICITY);
            return false;
        }
        return true;
    }

    private boolean validateTelephone(String t, BusinessException be) {
        if (t.isBlank() || t == null){
            be.add(BusinessCode.VALIDATION_USER_PHONE);
            return false;
        }
        if (t.length() > 50) {
            be.add(BusinessCode.VALIDATION_USER_PHONE);
            return false;
        }
        return true;
    }

    private boolean validatePassword(String motDePasse, BusinessException be) {
        if (motDePasse.isBlank() || motDePasse == null){
            be.add(BusinessCode.VALIDATION_USER_PASSWORD);
            return false;
        }
        return true;
    }

    private boolean validateAdresse(Adresse adresse, BusinessException be) {
        if (adresse == null) {
            be.add(BusinessCode.VALIDATION_USER_ADRESSE_NULL);
            return false;
        }
        if (adresse.getId() < 1) {
            be.add(BusinessCode.VALIDATION_USER_ADRESSE_UNKNOWN);
            return false;
        }
        try {
            Adresse temp = adresseDAO.findById(adresse.getId());
            if (temp == null) {
                be.add(BusinessCode.VALIDATION_USER_ADRESSE_UNKNOWN);
                return false;
            }
        } catch (DataAccessException e) {
            be.add(BusinessCode.VALIDATION_USER_ADRESSE_UNKNOWN);
            return false;
        }
        return true;
    }
}
