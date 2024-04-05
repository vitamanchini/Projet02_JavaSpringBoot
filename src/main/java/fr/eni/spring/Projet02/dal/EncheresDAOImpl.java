package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.ArticleAVendre;
import fr.eni.spring.Projet02.bo.Enchere;
import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Repository
public class EncheresDAOImpl implements EncheresDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private static final String INSERT = "INSERT INTO ENCHERES (id_utilisateur,no_article,date_enchere,montant_enchere) " +
            "VALUES (:id_utilisateur, :no_article, :date_enchere, :montant_enchere)";
    private static final String FIND_BID = "SELECT id_utilisateur,no_article,date_enchere,montant_enchere FROM ENCHERES " +
            "WHERE no_article= :no_article";

    private static final String FIND_MAX = "SELECT MAX(montant_enchere) FROM ENCHERES WHERE no_article = :no_article";

    private static final String FIND_LAST = "SELECT MAX(date_enchere) FROM ENCHERES WHERE no_article =:no_article";

    @Override
    public Enchere read(long articleNumber) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("no_article", articleNumber);
        return jdbcTemplate.queryForObject(FIND_BID, parameterSource, new EnchereRowMapper());
    }
    @Override
    public int findMax(long articleNumber) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("no_article", articleNumber);
        return jdbcTemplate.queryForObject(FIND_MAX, parameterSource, Integer.class);
    }
    @Override
    public LocalDateTime findLast(long articleNumber) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("no_article", articleNumber);
        return jdbcTemplate.queryForObject(FIND_LAST, parameterSource, LocalDateTime.class);
    }


    @Override
    public void createEnchere(Enchere e) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("no_article", e.getArticleAVendre().getId());
        parameterSource.addValue("id_utilisateur", e.getAcquereur().getPseudo());
        parameterSource.addValue("date_enchere", e.getDate());
        parameterSource.addValue("montant_enchere", e.getMontant());
        jdbcTemplate.update(INSERT, parameterSource);
    }

    class EnchereRowMapper implements RowMapper<Enchere> {

        @Override
        public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
            Enchere e = new Enchere();

            e.setDate(LocalDateTime.parse(rs.getString("date_enchere")));
            e.setMontant(rs.getInt("montant_enchere"));

            Utilisateur u = new Utilisateur();
            u.setPseudo(rs.getString("id_utilisateur"));
            e.setAcquereur(u);

            ArticleAVendre a = new ArticleAVendre();
            a.setId(rs.getLong("no_article"));
            e.setArticleAVendre(a);
            return e;
        }
    }
}
