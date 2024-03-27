package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

    private static final String FIND_BY_PSEUDO = "SELECT pseudo,mot_de_passe,administrateur FROM UTILISATEURS WHERE pseudo = :pseudo";

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Utilisateur read(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo",pseudo);
        return jdbcTemplate.queryForObject(FIND_BY_PSEUDO,namedParameters,new MembreRowMapper());
    }


    class MembreRowMapper implements RowMapper<Utilisateur> {

        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Utilisateur u = new Utilisateur();
            u.setPseudo(rs.getString("pseudo"));
            u.setEmail(rs.getString("email"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setMotDePasse(rs.getString("mot_de_passe"));
            u.setAdmin(rs.getBoolean("admin"));
            Adresse adresse = new Adresse();
            adresse.setId(rs.getLong("id"));
            u.setAdresse(adresse);
            return u;
        }
    }
}

