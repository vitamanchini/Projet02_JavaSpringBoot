package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.Enchere;
import fr.eni.spring.Projet02.dal.EncheresDAO;
import org.springframework.stereotype.Service;

@Service
public class EnchereServiceImpl implements EnchereService{
    private EncheresDAO encheresDAO;

    @Override
    public Enchere read(long articleNumber) {
        return encheresDAO.read(articleNumber);
    }

    @Override
    public void createEnchere(Enchere e) {
        // TODO validation encheres
        // TODO creation encheres
    }
}
