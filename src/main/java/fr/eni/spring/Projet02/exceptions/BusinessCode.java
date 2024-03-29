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


    public static final String VALIDATION_USER_NULL = "validation.user.null";
    public static final String VALIDATION_USER_PSEUDO_BLANK = "validation.user.pseudo.blank";
    public static final String VALIDATION_USER_PSEUDO_LENGTH = "validation.user.pseudo.length";
    public static final String VALIDATION_USER_PSEUDO_UNICITY = "validation.user.pseudo.unicity";
    public static final String VALIDATION_USER_SURNAME_LENGTH = "validation.user.surname.length";
    public static final String VALIDATION_USER_NAME_LENGTH = "validation.user.name.length";
    public static final String VALIDATION_USER_SURNAME_BLANK = "validation.user.surname.blank";
    public static final String VALIDATION_USER_NAME_BLANK = "validation.user.name.blank";
    public static final String VALIDATION_USER_EMAIL_BLANK = "validation.user.email.blank";
    public static final String VALIDATION_USER_PASSWORD = "validation.user.password";
    public static final String VALIDATION_USER_PASSWORD_CONFIRM = "validation.user.password.confirm";
    public static final String VALIDATION_USER_EMAIL_UNICITY = "validation.user.email.unicity";
    public static final String VALIDATION_USER_PHONE = "validation.user.phone";
    public static final String VALIDATION_USER_ADRESSE_NULL = "validation.user.adresse.null";
    public static final String VALIDATION_USER_ADRESSE_UNKNOWN = "validation.user.adresse.unknown";


    public static final String ERROR_ARTICLES_LOAD = "error.loading.articles";
    public static final String ERROR_CATEGORIES_LOAD = "error.loading.categories";
}
