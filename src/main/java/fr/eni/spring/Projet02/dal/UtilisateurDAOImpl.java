package fr.eni.spring.Projet02.dal;

import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {

    private static final String FIND_BY_PSEUDO = "SELECT pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,no_adresse " +
            "FROM UTILISATEURS WHERE pseudo = :pseudo";
    private static final String FIND_BY_PSEUDO_MAINPAGE = "SELECT pseudo,nom,prenom FROM UTILISATEURS WHERE pseudo = :pseudo";
    private static final String INSERT = "INSERT INTO UTILISATEURS(pseudo,nom,prenom,email,telephone,mot_de_passe,credit,administrateur,no_adresse) " +
            "VALUES (:pseudo, :nom, :prenom, :email, :telephone, :mot_de_passe, :credit, :administrateur, :no_adresse)";
    private static final String FIND_BY_EMAIL = "SELECT COUNT(email) FROM UTILISATEURS WHERE email = :email";
    private static final String FIND_BY_PSEUDO_BOOL = "SELECT COUNT(pseudo) FROM UTILISATEURS WHERE pseudo = :pseudo" ;
    private static final String FIND_BY_ADDRESS = "SELECT no_adresse FROM ADRESSES WHERE no_adresse IN (1,2,3,4,5) OR no_adresse = ?";
    private static final String FIND_USER = "SELECT pseudo,nom,prenom,email,telephone,mot_de_passe,no_adresse FROM UTILISATEURS " +
            "WHERE pseudo = :pseudo";
    private static final String UPDATE = "UPDATE UTILISATEURS " +
            "SET nom=:nom,prenom= :prenom,email=:email,telephone=:telephone,no_adresse=:no_adresse " +
            "WHERE pseudo = :pseudo";
    private static final String UPDATE_PASSWORD = "UPDATE UTILISATEURS SET mot_de_passe=:mot_de_passe WHERE pseudo = :pseudo";


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Utilisateur read(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo",pseudo);
        return jdbcTemplate.queryForObject(FIND_BY_PSEUDO,namedParameters,new UtilisateurRowMapper());
    }

    @Override
    public Utilisateur read(Principal p) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo",p);
        return jdbcTemplate.queryForObject(FIND_BY_PSEUDO,namedParameters,new UtilisateurRowMapper());
    }
    @Override
    public Utilisateur findByPseudo(String pseudo) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo",pseudo);
        return jdbcTemplate.queryForObject(FIND_USER,namedParameters,new BeanPropertyRowMapper<>(Utilisateur.class));
    }
    @Override
    public Utilisateur findByPseudoForMainPage(String pseudo) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("pseudo",pseudo);
        return jdbcTemplate.queryForObject(FIND_BY_PSEUDO_MAINPAGE,parameterSource,new BeanPropertyRowMapper<>(Utilisateur.class));
    }

    @Override
    public void create(Utilisateur u) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("pseudo",u.getPseudo());
        parameterSource.addValue("nom", u.getNom());
        parameterSource.addValue("prenom", u.getPrenom());
        parameterSource.addValue("email", u.getEmail());
        parameterSource.addValue("telephone", u.getTelephone());
        parameterSource.addValue("mot_de_passe", u.getMotDePasse());
        parameterSource.addValue("credit", u.getCredit());
        parameterSource.addValue("administrateur", u.isAdmin());
        parameterSource.addValue("no_adresse", u.getAdresse().getId());

        jdbcTemplate.update(INSERT, parameterSource);

    }
    @Override
    public void update(Utilisateur u){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue("pseudo",u.getPseudo());
        parameterSource.addValue("nom", u.getNom());
        parameterSource.addValue("prenom", u.getPrenom());
        parameterSource.addValue("email", u.getEmail());
        parameterSource.addValue("telephone", u.getTelephone());
        parameterSource.addValue("mot_de_passe", u.getMotDePasse());
        parameterSource.addValue("no_adresse", u.getAdresse().getId());

        jdbcTemplate.update(UPDATE, parameterSource);
    }
    @Override
    public void updateUserPassword(Utilisateur u){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();

        parameterSource.addValue("mot_de_passe", u.getMotDePasse());

        jdbcTemplate.update(UPDATE_PASSWORD, parameterSource);
    }

    @Override
    public boolean findPseudo(String ps) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("pseudo",ps);
        if (jdbcTemplate.queryForObject(FIND_BY_PSEUDO_BOOL,namedParameters,
                Integer.class) > 0) {
            return true;
        } else return false;
    }

    @Override
    public boolean findEmail(String e) {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("email",e);
        if (jdbcTemplate.queryForObject(FIND_BY_EMAIL,namedParameters,
                Integer.class) > 0) {
            return true;
        } else return false;
    }

    class UtilisateurRowMapper implements RowMapper<Utilisateur> {

        @Override
        public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
            Utilisateur u = new Utilisateur();
            u.setPseudo(rs.getString("pseudo"));
            u.setEmail(rs.getString("email"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setTelephone(rs.getString("telephone"));
            u.setMotDePasse(rs.getString("mot_de_passe"));
            u.setCredit(rs.getInt("credit"));
            u.setAdmin(rs.getBoolean("administrateur"));
            Adresse adresse = new Adresse();
            adresse.setId(rs.getLong("no_adresse"));
            u.setAdresse(adresse);
            return u;
        }
    }
}

