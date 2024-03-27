package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Categorie;

import java.util.List;

public interface CategorieDAO {
    Categorie read(int id);
    List<Categorie> findAll();
}
