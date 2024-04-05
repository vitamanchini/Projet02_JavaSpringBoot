package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Enchere;

import java.time.LocalDateTime;

public interface EncheresDAO {
    Enchere read (long articleNumber);

    int findMax(long articleNumber);

    LocalDateTime findLast(long articleNumber);

    void createEnchere(Enchere e);
}
