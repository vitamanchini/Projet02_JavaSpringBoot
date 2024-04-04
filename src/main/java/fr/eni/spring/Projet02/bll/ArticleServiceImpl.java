package fr.eni.spring.Projet02.bll;

import ch.qos.logback.classic.Logger;
import fr.eni.spring.Projet02.bo.*;
import fr.eni.spring.Projet02.dal.AdresseDAO;
import fr.eni.spring.Projet02.dal.ArticleAVendreDAO;
import fr.eni.spring.Projet02.dal.CategorieDAO;
import fr.eni.spring.Projet02.dal.UtilisateurDAO;
import fr.eni.spring.Projet02.exceptions.BusinessCode;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleAVendreDAO articleAVendreDAO;
    private UtilisateurDAO utilisateurDAO;
    private CategorieDAO categorieDAO;
    private AdresseDAO adresseDAO;

    public ArticleServiceImpl(ArticleAVendreDAO articleAVendreDAO, UtilisateurDAO utilisateurDAO, CategorieDAO categorieDAO, AdresseDAO adresseDAO) {
        this.articleAVendreDAO = articleAVendreDAO;
        this.utilisateurDAO = utilisateurDAO;
        this.categorieDAO = categorieDAO;
        this.adresseDAO = adresseDAO;
    }

    @Override
    public List<ArticleAVendre> consulterArticleEnVente() {
        List<ArticleAVendre> encheres = articleAVendreDAO.readAll();
        if(encheres != null){
            encheres.forEach(this::chargerArticleAvendre);
        }
        return encheres;
    }

    private void chargerArticleAvendre(ArticleAVendre articleAVendre) {
        Utilisateur vendeur = utilisateurDAO.read(articleAVendre.getVendeur().getPseudo());
        ArticleAVendre article = articleAVendreDAO.read(articleAVendre.getId());
        Adresse adresse = adresseDAO.findById(articleAVendre.getRetrait().getId());
    }

    @Override
    @Transactional
    public void creerArticle(ArticleAVendre a) {
        BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validerArticle(a, a.getId(), be);
        isValid &= validerNom(a.getNom(),be);
        isValid &= validerDescription(a.getDescription(),be);
        isValid &= validerDateDebut(a.getDateDebutEncheres(),be);
        isValid &= validerDateFin(a.getDateFinEncheres(), a.getDateDebutEncheres(),be);
        isValid &= validerPrixInitial(a.getPrixInitial(),be);
        if (isValid){
            articleAVendreDAO.create(a);
        }else {
            throw be;
        }

    }

    private boolean validerArticle(ArticleAVendre a, long id, BusinessException be){
        if (a == null){
            be.add(BusinessCode.VALIDATION_ARTICLE_NULL);
            return false;
        }
        try {
            boolean articleExiste = true;
            if (articleAVendreDAO.read(id)==null){
                articleExiste = false;
            }
            if (articleExiste){
                be.add(BusinessCode.VALIDATION_ARTICLE_UNIQUE); //COUNT
                return false;
            }
        }catch (DataAccessException de){
            be.add(BusinessCode.VALIDATION_ARTICLE_UNIQUE);
            return false;
        }
        return true;
    }

    private boolean validerNom(String nom, BusinessException be){
        if (nom ==null||nom.isBlank()){
            be.add(BusinessCode.VALIDATION_ARTICLE_NOM_BLANK);
            return false;
        }
        return true;
    }

    private boolean validerDescription(String description, BusinessException be){
        if (description ==null||description.isBlank()){
            be.add(BusinessCode.VALIDATION_ARTICLE_DESCRIPTION_BLANK);
            return false;
        }
        return true;
    }

    private boolean validerDateDebut(LocalDate dateDebut, BusinessException be){
        LocalDate today = LocalDate.now();
        if (dateDebut == null){
            be.add(BusinessCode.VALIDATION_ARTICLE_DATE_DEBUT_BLANK);
            return false;
        }
        boolean isBefore = today.isBefore(dateDebut);
        if (isBefore){
            be.add(BusinessCode.VALIDATION_ARTICLE_DATE_DEBUT_BEFORE);
            return false;
        }
        return true;
    }
    private boolean validerDateFin(LocalDate dateFin, LocalDate dateDebut, BusinessException be){
        if (dateFin == null){
            be.add(BusinessCode.VALIDATION_ARTICLE_DATE_FIN_BLANK);
            return false;
        }
        boolean isBefore = dateDebut.isBefore(dateFin);
        if (isBefore){
            be.add(BusinessCode.VALIDATION_ARTICLE_DATE_FIN_BEFORE);
            return false;
        }
        return true;
    }

    private boolean validerPrixInitial(int montant, BusinessException be){
        if(montant==0){
            be.add(BusinessCode.VALIDATION_ARTICLE_PRIX_NULL);
            return false;
        }
        if (montant<0){
            be.add(BusinessCode.VALIDATION_ARTICLE_PRIX_NEGATIF);
            return false;
        }
        return true;
    }

//    private boolean validerRetrait(long id, BusinessException be){
//        boolean adresseExiste = true;
//        if (adresseDAO.findById(id)==null){
//            adresseExiste = false;
//        }
//        if (!adresseExiste){
//            be.add(BusinessCode.VALIDATION_ARTICLE_ADRESSE);
//            return false;
//        }
//        return true;
//    }

//    private boolean validerCategorie(Categorie id, BusinessException be){
//        boolean categorieExiste = true;
//        if (categorieDAO.read(id.getId())==null){
//            categorieExiste=false;
//        }
//        if(!categorieExiste){
//            be.add(BusinessCode.VALIDATION_ARTICLE_CATEGORIE);
//            return false;
//        }
//        return true;
//    }


    @Override
    public ArticleAVendre consulterArticleParId(long id) {
        return articleAVendreDAO.read(id);
    }

    @Override
    public Utilisateur consulterVendeurParId(Principal p) {
        return utilisateurDAO.read(p.getName());
    }


    public Categorie consulterCategorieParId(long id) {
        return categorieDAO.read(id);
    }

    public Categorie consulterAllCategorie(){
        return (Categorie) categorieDAO.findAll();
    }

    @Override
    public Adresse consulterAdresseParId(long id) {
        return adresseDAO.findById(id);
    }

    @Override
    public LocalDate verifierDateDebut() {
            LocalDate dateDebut = LocalDate.now();
            return dateDebut;
    }

    @Override
    public LocalDate verifierDateFin() {
        LocalDate dateFin = LocalDate.now().plusDays(1);
        return dateFin;
    }
}
