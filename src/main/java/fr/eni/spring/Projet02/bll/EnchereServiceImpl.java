package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.*;
import fr.eni.spring.Projet02.dal.*;
import fr.eni.spring.Projet02.exceptions.BusinessCode;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class EnchereServiceImpl implements EnchereService{
    private EncheresDAO encheresDAO;
    private ArticleAVendreDAO articleAVendreDAO;
    private CategorieDAO categorieDAO;
    private AdresseDAO adresseDAO;
    private UtilisateurDAO utilisateurDAO;

    public EnchereServiceImpl(EncheresDAO encheresDAO, ArticleAVendreDAO articleAVendreDAO, CategorieDAO categorieDAO, AdresseDAO adresseDAO, UtilisateurDAO utilisateurDAO) {
        this.encheresDAO = encheresDAO;
        this.articleAVendreDAO = articleAVendreDAO;
        this.categorieDAO = categorieDAO;
        this.adresseDAO = adresseDAO;
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public Enchere read(long articleNumber) {
        return encheresDAO.read(articleNumber);
    }
    @Override
    public ArticleAVendre findArticleById(long id){
        ArticleAVendre a = articleAVendreDAO.read(id);
        Utilisateur u = utilisateurDAO.findByPseudo(a.getVendeur().getPseudo());
        Categorie c = categorieDAO.read(a.getCategorie().getId());
        Adresse ad = adresseDAO.findById(a.getRetrait().getId());
        a.setVendeur(u);
        a.setRetrait(ad);
        a.setCategorie(c);
        return a;
    }

    @Override
    @Transactional
    public void createEnchere(Enchere e) {
        BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validateUser(e.getAcquereur(), be);
        isValid &= validatePseudo(e.getAcquereur().getPseudo(), be);
        isValid &= validerArticle(e.getArticleAVendre().getId(), be);
        isValid &= validerDate(e.getArticleAVendre().getId(),e.getDate(), be);
        isValid &= validerMontant(e.getArticleAVendre().getId(),e.getMontant(), be);

        if(isValid){
            encheresDAO.createEnchere(e);
        } else {
            throw be;
        }

    }
    private boolean validateUser(Utilisateur u, BusinessException be) {
        if (u == null) {
            be.add(BusinessCode.VALIDATION_USER_NULL);
            return false;
        } else
            return true;
    }
    private boolean validerDate(long idArt, LocalDateTime date, BusinessException be) {
        LocalDateTime temp = encheresDAO.findLast(idArt);
        if (date == null ){
            be.add(BusinessCode.VALIDATION_BID_TIME);
            return false;
        }
        if (date.isBefore(temp)){
            be.add(BusinessCode.VALIDATION_BID_TIME);
            return false;
        }
        return true;
    }

    private boolean validerMontant(long idArt, int montant, BusinessException be) {
        int temp = encheresDAO.findMax(idArt);
        if (montant == 0 ){
            be.add(BusinessCode.VALIDATION_BID_TIME);
            return false;
        }
        if (montant <=temp){
            be.add(BusinessCode.VALIDATION_BID_AMOUNT);
            return false;
        }
        return true;
    }

    private boolean validatePseudo(String pseudo, BusinessException be) {

        if (pseudo.isBlank() || pseudo == null) {
            be.add(BusinessCode.VALIDATION_USER_PSEUDO_BLANK);
            return false;
        }
        return true;
    }
    private boolean validerArticle(long id, BusinessException be){
        if (id == 0 || id < 0) {
            be.add(BusinessCode.VALIDATION_ARTICLE_NULL);
            return false;
        }
        return true;
    }
}
