package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.Utilisateur;
import fr.eni.spring.Projet02.dal.AdresseDAO;
import fr.eni.spring.Projet02.dal.ArticleAVendreDAO;
import fr.eni.spring.Projet02.dal.CategorieDAO;
import fr.eni.spring.Projet02.dal.UtilisateurDAO;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class AdresseServiceImpl implements AdresseService {

    private AdresseDAO adresseDAO;

    public AdresseServiceImpl(AdresseDAO adresseDAO) {
        this.adresseDAO = adresseDAO;
    }

    @Override
    public List<Adresse> consulterAdressesVendeur(String pseudo) {
        return adresseDAO.findByAddresses(pseudo);
    }

    public Adresse consulterUneAdresse(long id){
        return adresseDAO.findById(id);
    }
}
