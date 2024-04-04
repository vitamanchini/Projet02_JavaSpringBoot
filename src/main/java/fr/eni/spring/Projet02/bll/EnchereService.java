package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.Enchere;

public interface EnchereService {
    Enchere read (long articleNumber);
    void createEnchere(Enchere e);
}
