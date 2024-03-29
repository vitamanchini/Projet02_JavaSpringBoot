package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.Utilisateur;

public interface UserService {
    Utilisateur read(long id);
    void createNewUser(Utilisateur u);
    void updateUser(Utilisateur u);
}
