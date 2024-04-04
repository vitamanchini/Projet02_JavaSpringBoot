package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.Utilisateur;

import java.security.Principal;
import java.util.List;

public interface AdresseService {
    List<Adresse> consulterAdressesVendeur(String pseudo);

    Adresse consulterUneAdresse(long id);
}
