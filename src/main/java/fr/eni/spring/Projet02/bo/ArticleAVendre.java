package fr.eni.spring.Projet02.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ArticleAVendre implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    private long id;
    @NotBlank
    private String nom;
    private String description;
    private String photo;
    @NotNull
    private LocalDate dateDebutEncheres;
    @NotNull
    private LocalDate dateFinEncheres;
    private int statu;
    @NotNull
    private int prixInitial;
    @NotNull
    private int prixVente;
    @NotBlank
    private Adresse retrait;
    @NotBlank
    private Categorie categorie;
    @NotBlank
    private Utilisateur vendeur;

    public ArticleAVendre() {}

    public ArticleAVendre(long id, String nom, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int prixInitial, int prixVente, Adresse retrait, Categorie categorie, Utilisateur vendeur) {
        this.id = id;
        this.nom = nom;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.retrait = retrait;
        this.categorie = categorie;
        this.vendeur = vendeur;
    }

    public ArticleAVendre(long id, String nom, String description, String photo, LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int statu, int prixInitial, int prixVente, Adresse retrait, Categorie categorie, Utilisateur vendeur) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.photo = photo;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.statu = statu;
        this.prixInitial = prixInitial;
        this.prixVente = prixVente;
        this.retrait = retrait;
        this.categorie = categorie;
        this.vendeur = vendeur;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public LocalDate getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public LocalDate getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDate dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public int getPrixInitial() {
        return prixInitial;
    }

    public void setPrixInitial(int prixInitial) {
        this.prixInitial = prixInitial;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public Adresse getRetrait() {
        return retrait;
    }

    public void setRetrait(Adresse retrait) {
        this.retrait = retrait;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Utilisateur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }

    @Override
    public String toString() {
        return "ArticleAVendre{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", photo='" + photo + '\'' +
                ", dateDebutEncheres=" + dateDebutEncheres +
                ", dateFinEncheres=" + dateFinEncheres +
                ", statu=" + statu +
                ", prixInitial=" + prixInitial +
                ", prixVente=" + prixVente +
                ", retrait=" + retrait +
                ", categorie=" + categorie +
                ", vendeur=" + vendeur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticleAVendre that = (ArticleAVendre) o;
        return id == that.id && statu == that.statu && prixInitial == that.prixInitial && prixVente == that.prixVente && Objects.equals(nom, that.nom) && Objects.equals(description, that.description) && Objects.equals(photo, that.photo) && Objects.equals(dateDebutEncheres, that.dateDebutEncheres) && Objects.equals(dateFinEncheres, that.dateFinEncheres) && Objects.equals(retrait, that.retrait) && Objects.equals(categorie, that.categorie) && Objects.equals(vendeur, that.vendeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, description, photo, dateDebutEncheres, dateFinEncheres, statu, prixInitial, prixVente, retrait, categorie, vendeur);
    }
}