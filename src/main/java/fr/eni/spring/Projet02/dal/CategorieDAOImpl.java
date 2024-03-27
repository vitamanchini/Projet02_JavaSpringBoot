package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Categorie;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("CatDao")
public class CategorieDAOImpl implements CategorieDAO {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final String FIND_ALL = "SELECT no_categorie,libelle FROM CATEGORIES";
    private static final String FIND_BY_ID = "SELECT no_categorie,libelle FROM CATEGORIES "+"WHERE no_categorie= :id";
    @Override
    public Categorie read(int id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, parameterSource, new BeanPropertyRowMapper<>(Categorie.class));
    }

    @Override
    public List<Categorie> findAll() {
        return null;
//        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Categorie.class));
    }
}
