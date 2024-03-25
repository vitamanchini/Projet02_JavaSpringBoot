package fr.eni.spring.Projet02.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

public class Adresse implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    private long id;
    private String complement;
    @NotBlank
    private String rue;
    @Size(min=6,max=6)
    @NotBlank
    private String codePostal;
    @NotBlank
    private String ville;

    public Adresse(){

    }

    public Adresse(long id, String rue, String codePostal, String ville) {
        this.id = id;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Adresse(long id, String complement, String rue, String codePostal, String ville) {
        this.id = id;
        this.complement = complement;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "id = '" + id + '\'' +
                ", complement='" + complement + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adresse adresse = (Adresse) o;
        return id == adresse.id && Objects.equals(complement, adresse.complement) && Objects.equals(rue, adresse.rue) && Objects.equals(codePostal, adresse.codePostal) && Objects.equals(ville, adresse.ville);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, complement, rue, codePostal, ville);
    }
}
