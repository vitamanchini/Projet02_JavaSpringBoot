package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.Utilisateur;

import java.security.Principal;
import java.util.List;

public interface AdresseDAO {
    Adresse findById(long id);
    void create(Adresse a);

    long findAddress(Adresse a);

    Adresse update(Adresse a);


    List<Adresse> findByAddresses(Principal p);

    int findAddressExists(Adresse a);
}
