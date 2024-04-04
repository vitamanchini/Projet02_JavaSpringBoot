package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Enchere;
import org.springframework.stereotype.Repository;

@Repository
public class EncheresDAOImpl implements EncheresDAO {

    private static final String INSERT = "INSERT INTO ENCHERES (id_utilisateur,no_article,date_enchere,montant_enchere) " +
            "VALUES (:id_utilisateur, :no_article, :date_enchere, :montant_enchere)";
    private static final String FIND_BID = "SELECT id_utilisateur,no_article,date_enchere,montant_enchere FROM ENCHERES " +
            "WHERE no_article= :no_article";

    @Override
    public Enchere read(long articleNumber) {
        return null; // TODO enchereDao read
    }

    @Override
    public void createEnchere(Enchere e) {
// TODO enchereDao create
    }
}
