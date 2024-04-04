package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    private static final String FIND_ALL = "SELECT no_categorie,libelle FROM CATEGORIES";
    private static final String FIND_BY_ID = "SELECT no_categorie,libelle FROM CATEGORIES "+"WHERE no_categorie= :id";
    @Override
    public Categorie read(long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, parameterSource, new BeanPropertyRowMapper<>(Categorie.class));
    }

    @Override
    public List<Categorie> findAll() {

        return jdbcTemplate.query(FIND_ALL, new BeanPropertyRowMapper<>(Categorie.class));
    }
}
