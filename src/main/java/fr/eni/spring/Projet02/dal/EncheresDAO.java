package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Enchere;

public interface EncheresDAO {
    Enchere read (long articleNumber);
    void createEnchere(Enchere e);
}
