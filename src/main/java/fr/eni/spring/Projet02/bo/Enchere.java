package fr.eni.spring.Projet02.bo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Enchere implements Serializable {
    private static final long serialVersionUID = 1L;
//    @NotNull
    private LocalDateTime date;

    private int montant;
//    @NotNull
    private ArticleAVendre articleAVendre;
//    @NotNull
    private Utilisateur acquereur;

    public Enchere() {
    }

    public Enchere(LocalDateTime date, int montant, ArticleAVendre articleAVendre, Utilisateur acquereur) {
        this.date = date;
        this.montant = montant;
        this.articleAVendre = articleAVendre;
        this.acquereur = acquereur;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public ArticleAVendre getArticleAVendre() {
        return articleAVendre;
    }

    public void setArticleAVendre(ArticleAVendre articleAVendre) {
        this.articleAVendre = articleAVendre;
    }

    public Utilisateur getAcquereur() {
        return acquereur;
    }

    public void setAcquereur(Utilisateur acquereur) {
        this.acquereur = acquereur;
    }

    @Override
    public String toString() {
        return "Enchere{" +
                "date=" + date +
                ", montant=" + montant +
                ", articleAVendre=" + articleAVendre +
                ", acquereur=" + acquereur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enchere enchere = (Enchere) o;
        return Objects.equals(date, enchere.date)&& Objects.equals(acquereur, enchere.acquereur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(montant, acquereur);
    }
}
