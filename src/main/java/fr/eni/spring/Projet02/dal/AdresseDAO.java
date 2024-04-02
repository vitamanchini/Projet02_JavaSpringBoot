package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Adresse;

public interface AdresseDAO {
    Adresse findById(long id);
    void create(Adresse a);

    long findAddress(Adresse a);

    Adresse update(Adresse a);
}
