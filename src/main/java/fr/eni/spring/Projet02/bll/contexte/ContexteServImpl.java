package fr.eni.spring.Projet02.bll.contexte;

import fr.eni.spring.Projet02.bo.Utilisateur;
import fr.eni.spring.Projet02.dal.UtilisateurDAO;
import org.springframework.stereotype.Service;

@Service
public class ContexteServImpl implements ContexteService {

    private UtilisateurDAO utilisateurDAO;

    public ContexteServImpl(UtilisateurDAO utilisateurDAO) {this.utilisateurDAO=utilisateurDAO;}
    @Override
    public Utilisateur charger(String pseudo) {
        return utilisateurDAO.read(pseudo);
    }
}
