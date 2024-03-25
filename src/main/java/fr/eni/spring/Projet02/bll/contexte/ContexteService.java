package fr.eni.spring.Projet02.bll.contexte;

import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.stereotype.Service;

@Service
public interface ContexteService {
    Utilisateur charger (String pseudo);
}
