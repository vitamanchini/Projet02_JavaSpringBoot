package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("accDAO")
public class AccueilDAOImpl implements AccueilDAO{

    private static final String FIND_ALL = "SELECT no_article,nom_article,date_fin_encheres,prix_vente, u.nom, u.prenom" +
            " FROM ARTICLES_A_VENDRE a INNER JOIN UTILISATEURS u ON a.id_utilisateur=u.pseudo ";

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
@Override
    public List<ArticleAVendre> findAll() {
        MapSqlParameterSource msps = new MapSqlParameterSource();
    System.out.println("DAL");
        return namedParameterJdbcTemplate.query(FIND_ALL, new MiniEnchereRowMapper());
    }


    class MiniEnchereRowMapper implements RowMapper<ArticleAVendre>{

        @Override
        public ArticleAVendre mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArticleAVendre lot = new ArticleAVendre();
            lot.setId(rs.getInt("no_article"));
            lot.setNom(rs.getString("nom_article"));
            lot.setPrixVente(rs.getInt("prix_vente"));
            lot.setDateFinEncheres(rs.getDate("date_fin_encheres").toLocalDate());

            Utilisateur vendeur = new Utilisateur();
            vendeur.setNom(rs.getString("nom"));
            vendeur.setPrenom(rs.getString("prenom"));
            lot.setVendeur(vendeur);
            return lot;
        }
    }
}
