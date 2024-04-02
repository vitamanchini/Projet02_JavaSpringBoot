package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Adresse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AdresseDAOImpl implements AdresseDAO {

    private static final String FIND_BY_ID = "SELECT no_adresse,complement,rue,code_postal,ville FROM ADRESSES WHERE no_adresse = :id";
    private static final String FIND_ADDRESS = "SELECT no_adresse FROM ADRESSES " +
            "WHERE rue = :rue AND code_postal= :code_postal AND ville=:ville";
    private static final String FIND_ADDRESS_EXISTS = "SELECT COUNT(no_adresse) FROM ADRESSES " +
            "WHERE rue = :rue AND code_postal= :code_postal AND ville=:ville";
    private static final String UPDATE = "UPDATE complement,rue,code_postal,ville FROM ADRESSES WHERE no_adresse = :id";
    private static final String INSERT = "INSERT INTO ADRESSES (complement,rue,code_postal,ville) VALUES (:complement,:rue,:codePostal,:ville)";


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Adresse findById(long id) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id", id);
        return jdbcTemplate.queryForObject(FIND_BY_ID, namedParameters, new BeanPropertyRowMapper<>(Adresse.class));
    }

    @Override
    public void create(Adresse a) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("complement", a.getComplement());
        namedParameters.addValue("rue", a.getRue());
        namedParameters.addValue("codePostal", a.getCodePostal());
        namedParameters.addValue("ville", a.getVille());
        jdbcTemplate.update(INSERT, namedParameters, keyHolder);

        if (keyHolder != null && keyHolder.getKey() != null) {
            a.setId(keyHolder.getKey().longValue());
        }

    }

    @Override
    public long findAddress(Adresse a) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("rue", a.getRue());
        namedParameters.addValue("code_postal", a.getCodePostal());
        namedParameters.addValue("ville", a.getVille());
        return jdbcTemplate.queryForObject(FIND_ADDRESS, namedParameters, Long.class);
    }
    @Override
    public int findAddressExists(Adresse a) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("rue", a.getRue());
        namedParameters.addValue("code_postal", a.getCodePostal());
        namedParameters.addValue("ville", a.getVille());
        return jdbcTemplate.queryForObject(FIND_ADDRESS_EXISTS, namedParameters, Integer.class);
    }

    @Override
    public Adresse update(Adresse a) {
        return null;
    }

    class AdresseRowMapper implements RowMapper<Adresse> {

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
