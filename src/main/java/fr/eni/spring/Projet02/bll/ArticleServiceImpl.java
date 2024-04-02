package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.*;
import fr.eni.spring.Projet02.dal.AdresseDAO;
import fr.eni.spring.Projet02.dal.ArticleAVendreDAO;
import fr.eni.spring.Projet02.dal.CategorieDAO;
import fr.eni.spring.Projet02.dal.UtilisateurDAO;
import fr.eni.spring.Projet02.exceptions.BusinessCode;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;
import java.util.List;

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
//        Adresse adresse = articleAVendreDAO.read(articleAVendre.getRetrait().getId());
    }

    @Override
    public void creerArticle(Enchere e) {
        BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validerArticle((List<Enchere>) e,e.getArticleAVendre().getId(),be);
        isValid &= validerNom(e.getArticleAVendre().getNom(),be);
        isValid &= validerDescription(e.getArticleAVendre().getDescription(),be);
        isValid &= validerDateDebut(e.getArticleAVendre().getDateDebutEncheres(),be);
        isValid &= validerDateFin(e.getArticleAVendre().getDateFinEncheres(), e.getArticleAVendre().getDateDebutEncheres(),be);
        isValid &= validerPrixInitial(e.getMontant(),be);
        isValid &= validerRetrait((List<Adresse>) e.getArticleAVendre().getRetrait(),e.getArticleAVendre().getRetrait().getId(),be);
//        isValid &= validerCategorie(e.getArticleAVendre().getCategorie(),be);
        isValid &= validerVendeur(e.getArticleAVendre().getVendeur(),be);
    }

    private boolean validerArticle(List<Enchere> e, long id, BusinessException be){
        if (e == null){
            be.add(BusinessCode.VALIDATION_ARTICLE_NULL);
            return false;
        }
        try {
            boolean articleExiste = true;
            if (articleAVendreDAO.read(id)==null){
                articleExiste = false;
            }
            if (articleExiste){
                be.add(BusinessCode.VALIDATION_ARTICLE_UNIQUE);
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
        Boolean isBefore = today.isBefore(dateDebut);
        if (dateDebut == null){
            be.add(BusinessCode.VALIDATION_ARTICLE_DATE_DEBUT_BLANK);
            return false;
        }
        if (isBefore){
            be.add(BusinessCode.VALIDATION_ARTICLE_DATE_DEBUT_BEFORE);
            return false;
        }
        return true;
    }
    private boolean validerDateFin(LocalDate dateFin, LocalDate dateDebut, BusinessException be){
        Boolean isBefore = dateDebut.isBefore(dateFin);
        if (dateFin == null){
            be.add(BusinessCode.VALIDATION_ARTICLE_DATE_FIN_BLANK);
            return false;
        }
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

    private boolean validerRetrait(List<Adresse> a, long id, BusinessException be){
        boolean adresseExiste = true;
        if (adresseDAO.findById(id)==null){
            adresseExiste = false;
        }
        if (!adresseExiste){
            be.add(BusinessCode.VALIDATION_ARTICLE_ADRESSE);
            return false;
        }
        return true;
    }

    private boolean validerCategorie(int id, BusinessException be){
        boolean categorieExiste = true;
        if (categorieDAO.read(id)==null){
            categorieExiste=false;
        }
        if(!categorieExiste){
            be.add(BusinessCode.VALIDATION_ARTICLE_CATEGORIE);
            return false;
        }
        return true;
    }

    private boolean validerVendeur(Utilisateur pseudo, BusinessException be){
        if (pseudo==null){
            be.add(BusinessCode.VALIDATION_ARTICLE_VENDEUR_NULL);
            return false;
        }
        try {
            Utilisateur utilisateur = utilisateurDAO.read(pseudo.getPseudo());
            if (pseudo!=utilisateur){
                be.add(BusinessCode.VALIDATION_ARTICLE_VENDEUR_INCONNU);
                return false;
            }
        }catch (DataAccessException de){
            be.add(BusinessCode.VALIDATION_ARTICLE_VENDEUR_INCONNU);
            return false;
        }
        return true;
    }

    @Override
    public ArticleAVendre consulterArticleParId(long id) {
        return articleAVendreDAO.read(id);
    }

    @Override
    public Utilisateur consulterVendeurParId(String id) {
        return utilisateurDAO.read(id);
    }


    public Categorie consulterCategorieParId(long id) {
        return categorieDAO.read(id);
    }

    @Override
    public Adresse consulterAdresseParId(long id) {
        return adresseDAO.findById(id);
    }
}
