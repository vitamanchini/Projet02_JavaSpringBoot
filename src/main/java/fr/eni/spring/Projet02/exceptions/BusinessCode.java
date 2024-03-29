package fr.eni.spring.Projet02.exceptions;

public class BusinessCode {

    //Login
    public static final String VALIDATION_LOGIN_PSEUDO_BLANK = "validation.login.pseudo.blank";
    public static final String VALIDATION_LOGIN_PSEUDO_INCONNU = "validation.login.pseudo.inconnu";
    public static final String VALIDATION_LOGIN_PASSWORD_BLANK = "validation.login.password.blank";
    public static final String VALIDATION_LOGIN_PASSWORD_INCONNU = "validation.login.password.inconnu";

    //CreationArticleAVendre
    public static final String VALIDATION_ARTICLE_NULL = "validation.article.null";
    public static final String VALIDATION_ARTICLE_UNIQUE = "validation.article.unique";
    public static final String VALIDATION_ARTICLE_NOM_BLANK = "validation.article.nom.blank";
    public static final String VALIDATION_ARTICLE_DESCRIPTION_BLANK = "validation.article.description.blank";
    public static final String VALIDATION_ARTICLE_DATE_DEBUT_BLANK = "validation.article.date.blank";
    public static final String VALIDATION_ARTICLE_DATE_DEBUT_BEFORE = "validation.article.date.before";
    public static final String VALIDATION_ARTICLE_DATE_FIN_BLANK = "validation.article.date.blank";
    public static final String VALIDATION_ARTICLE_DATE_FIN_BEFORE = "validation.article.date.fin.before";
    public static final String VALIDATION_ARTICLE_PRIX_NULL = "validation.article.prix.null";
    public static final String VALIDATION_ARTICLE_PRIX_NEGATIF = "validation.article.prix.negatif";
    public static final String VALIDATION_ARTICLE_ADRESSE = "validation.article.adresse";
    public static final String VALIDATION_ARTICLE_CATEGORIE = "validation.article.categorie";
    public static final String VALIDATION_ARTICLE_VENDEUR_NULL = "validation.article.vendeur.null";
    public static final String VALIDATION_ARTICLE_VENDEUR_INCONNU= "validation.article.vendeur.inconnu";
}
