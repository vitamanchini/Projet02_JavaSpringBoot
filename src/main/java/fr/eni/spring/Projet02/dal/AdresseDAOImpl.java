package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdresseDAOImpl implements AdresseDAO{

    private static final String FIND_BY_ID = "SELECT no_adresse,complement,rue,code_postal,ville FROM ADRESSES WHERE no_adresse = :id";

    @Override
    public Adresse findById(long id) {
        return null;
    }

    @Override
    public void create(Adresse a) {

    }

    @Override
    public long findAddress(Adresse a) {
        return 0;
    }

    @Override
    public Adresse update(Adresse a) {
        return null;
    }

    class adresseRowMapper implements RowMapper<Adresse> {

        @Override
        public Adresse mapRow(ResultSet rs, int rowNum) throws SQLException {
            Adresse a = new Adresse();
            a.setComplement(rs.getString("complement"));
            a.setRue(rs.getString("rue"));
            a.setCodePostal(rs.getString("code_postal"));
            a.setVille(rs.getString("ville"));
            return a;
        }
    }
}
