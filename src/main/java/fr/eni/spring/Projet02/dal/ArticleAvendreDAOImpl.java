package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Categorie;
import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class ArticleAvendreDAOImpl implements ArticleAVendreDAO {
    private static final String INSERT = "INSERT INTO ARTICLES_A_VENDRE (no_article, nom_article, description, photo, date_debut_encheres, date_fin_encheres, prix_initial, id_utilisateur, no_categorie, no_adresse_retrait) VALUES (:id, :nom, :dateDebut, :dateFin, :prixInitial, :pseudo,:categorie,:adresse)";
    private static final String FIND_BY_ID = "SELECT no_article, nom_article, description, photo, date_debut_encheres, date_fin_encheres, prix_initial, id_utilisateur, no_categorie, no_adresse_retrait FROM ARTICLES_A_VENDRE WHERE no_article= :id";
    private static final String FIND_ALL = "SELECT no_article, nom_article, description, photo, date_debut_encheres, date_fin_encheres, prix_initial, id_utilisateur, no_categorie, no_adresse_retrait FROM ARTICLES_A_VENDRE";
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void create(ArticleAVendre articleAVendre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_article",articleAVendre.getId());
        namedParameters.addValue("nom_article",articleAVendre.getNom());
        namedParameters.addValue("description",articleAVendre.getDescription());
        namedParameters.addValue("photo",articleAVendre.getPhoto());
        namedParameters.addValue("date_debut_encheres",articleAVendre.getDateDebutEncheres());
        namedParameters.addValue("date_fin_encheres",articleAVendre.getDateFinEncheres());
        namedParameters.addValue("prix_initial",articleAVendre.getPrixInitial());
        namedParameters.addValue("id_utilisateur",articleAVendre.getVendeur().getPseudo());
        namedParameters.addValue("no_categorie",articleAVendre.getCategorie().getId());
        namedParameters.addValue("no_adresse_retrait",articleAVendre.getVendeur().getAdresse().getId());


        jdbcTemplate.update(INSERT,namedParameters,keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null){
            articleAVendre.setId(keyHolder.getKey().longValue());
        }
    }

    @Override
    public ArticleAVendre read(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("no_article", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID,namedParameters,new FilmRowMapper());
    }

    @Override
    public List<ArticleAVendre> readAll() {
        return jdbcTemplate.query(FIND_ALL,new FilmRowMapper());
    }


    class FilmRowMapper implements RowMapper<ArticleAVendre> {

        @Override
        public ArticleAVendre mapRow(ResultSet rs, int rowNum) throws SQLException {
            ArticleAVendre a = new ArticleAVendre();
            a.setId(rs.getLong("no_article"));
            a.setNom(rs.getString("nom_article"));
            a.setDescription(rs.getString("description"));
            a.setPhoto(rs.getString("photo"));
            a.setDateDebutEncheres(LocalDate.ofEpochDay(rs.getLong("date_debut_encheres")));
            a.setDateFinEncheres(LocalDate.ofEpochDay(rs.getLong("date_fin_encheres")));
            a.setPrixInitial(rs.getInt("prix_initial"));
            Utilisateur utilisateur = new Utilisateur();
            utilisateur.setPseudo(rs.getString("id_utilisateur"));
            a.setVendeur(utilisateur);
            Categorie categorie = new Categorie();
            categorie.setId(rs.getLong("no_categorie"));
            a.setCategorie(categorie);
            Adresse adresse = new Adresse();

            return a;
        }
    }
}