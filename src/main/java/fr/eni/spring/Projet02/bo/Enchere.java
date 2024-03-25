package fr.eni.spring.Projet02.bo;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Enchere implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotNull
    private LocalDateTime date;
    @NotNull
    private int montant;

    public Enchere(LocalDateTime date, int montant) {
        this.date = date;
        this.montant = montant;
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

    @Override
    public String toString() {
        return "Enchere{" +
                "date=" + date +
                ", montant=" + montant +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enchere enchere = (Enchere) o;
        return montant == enchere.montant && Objects.equals(date, enchere.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, montant);
    }
}
