package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.Adresse;

import java.util.List;

public interface AdresseService {
    List<Adresse> consulterAdressesVendeur(long id);
}
