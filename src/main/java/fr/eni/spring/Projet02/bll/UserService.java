package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.Utilisateur;

public interface UserService {

    void createNewUser(Utilisateur u);

    Utilisateur read(String pseudo);

    void updateUser(Utilisateur u);
}
