package fr.eni.spring.Projet02.bll;

import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.Utilisateur;
import fr.eni.spring.Projet02.dal.AdresseDAO;
import fr.eni.spring.Projet02.dal.UtilisateurDAO;
import fr.eni.spring.Projet02.exceptions.BusinessCode;
import fr.eni.spring.Projet02.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
//    private PasswordEncoder passwordEncoder;
    private UtilisateurDAO utilisateurDAO;
    private AdresseDAO adresseDAO;

    public UserServiceImpl(UtilisateurDAO utilisateurDAO, AdresseDAO adresseDAO) {
        this.utilisateurDAO = utilisateurDAO;
        this.adresseDAO = adresseDAO;
    }

    @Override
    public Utilisateur read(String pseudo) {
        Utilisateur u =utilisateurDAO.read(pseudo);
        Adresse a = adresseDAO.findById(u.getAdresse().getId());
        u.getAdresse().setRue(a.getRue());
        u.getAdresse().setCodePostal(a.getCodePostal());
        u.getAdresse().setVille(a.getVille());
        return u;

    }

    @Override
    public void updateUser(Utilisateur u) {
        BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validateUser(u, be);
        isValid &= validateNom(u.getNom(), be);
        isValid &= validatePrenom(u.getPrenom(), be);
        isValid &= validateEmail(u.getEmail(), be);
        isValid &= validateEmailUnicity(u.getEmail(), be);
        isValid &= validateTelephone(u.getTelephone(), be);
        isValid &= validatePassword(u.getMotDePasse(), be);
//        isValid &= validateAdresse(u.getAdresse(), be);

        Adresse newAdresse = new Adresse();
        newAdresse.setRue(u.getAdresse().getRue());
        newAdresse.setCodePostal(u.getAdresse().getCodePostal());
        newAdresse.setVille(u.getAdresse().getVille());

        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        int temp = validateAddressExist(newAdresse);
        if (temp > 0 && isValid) {
            u.setAdmin(false);
            u.setCredit(10);
            u.setMotDePasse(passwordEncoder.encode(u.getMotDePasse()));

            Adresse adresse = new Adresse();
            adresse.setId(adresseDAO.findAddress(newAdresse));
            u.setAdresse(adresse);
            utilisateurDAO.create(u);
        } else if (isValid) {
            u.setAdmin(false);
            u.setCredit(10);
            u.setMotDePasse(passwordEncoder.encode(u.getMotDePasse()));

            adresseDAO.create(newAdresse);
            newAdresse.setId(adresseDAO.findAddress(newAdresse));
            u.setAdresse(newAdresse);
            utilisateurDAO.create(u);

        } else {
            throw be;
        }
    }

    @Override
    @Transactional
    public void createNewUser(Utilisateur u) {
        BusinessException be = new BusinessException();
        boolean isValid = true;
        isValid &= validateUser(u, be);
        isValid &= validatePseudo(u.getPseudo(), be);
        isValid &= validatePseudoUnicity(u, be);
        isValid &= validateNom(u.getNom(), be);
        isValid &= validatePrenom(u.getPrenom(), be);
        isValid &= validateEmail(u.getEmail(), be);
        isValid &= validateEmailUnicity(u.getEmail(), be);
        isValid &= validateTelephone(u.getTelephone(), be);
        isValid &= validatePassword(u.getMotDePasse(), be);
//        isValid &= validateAdresse(u.getAdresse(), be);

        Adresse newAdresse = new Adresse();
        newAdresse.setRue(u.getAdresse().getRue());
        newAdresse.setCodePostal(u.getAdresse().getCodePostal());
        newAdresse.setVille(u.getAdresse().getVille());

        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();

        int temp = validateAddressExist(newAdresse);
        if (temp > 0 && isValid) {
            u.setAdmin(false);
            u.setCredit(10);
            u.setMotDePasse(passwordEncoder.encode(u.getMotDePasse()));

            Adresse adresse = new Adresse();
            adresse.setId(adresseDAO.findAddress(newAdresse));
            u.setAdresse(adresse);
            utilisateurDAO.create(u);
        } else if (isValid) {
            u.setAdmin(false);
            u.setCredit(10);
            u.setMotDePasse(passwordEncoder.encode(u.getMotDePasse()));

            adresseDAO.create(newAdresse);
            newAdresse.setId(adresseDAO.findAddress(newAdresse));
            u.setAdresse(newAdresse);
            utilisateurDAO.create(u);

        } else {
            throw be;
        }

    }

    private boolean validateUser(Utilisateur u, BusinessException be) {
        if (u == null) {
            be.add(BusinessCode.VALIDATION_USER_NULL);
            return false;
        } else
            return true;
    }

    private boolean validatePseudo(String pseudo, BusinessException be) {

        if (pseudo.isBlank() || pseudo == null) {
            be.add(BusinessCode.VALIDATION_USER_PSEUDO_BLANK);
            return false;
        }
        return true;
    }

    private boolean validatePseudoUnicity(Utilisateur u, BusinessException be) {

        if (utilisateurDAO.findPseudo(u.getPseudo())) {
            be.add(BusinessCode.VALIDATION_USER_PSEUDO_UNICITY);
            return false;
        }
        return true;

    }

    private boolean validateNom(String nom, BusinessException be) {

        if (nom.isBlank() || nom == null) {
            be.add(BusinessCode.VALIDATION_USER_SURNAME_BLANK);
            return false;
        }
        if (nom.length() > 40) {
            be.add(BusinessCode.VALIDATION_USER_SURNAME_LENGTH);
            return false;
        }
        return true;
    }

    private boolean validatePrenom(String prenom, BusinessException be) {

        if (prenom.isBlank() || prenom == null) {
            be.add(BusinessCode.VALIDATION_USER_NAME_BLANK);
            return false;
        }
        if (prenom.length() > 50) {
            be.add(BusinessCode.VALIDATION_USER_NAME_LENGTH);
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email, BusinessException be) {
        if (email.isBlank() || email == null) {
            be.add(BusinessCode.VALIDATION_USER_EMAIL_BLANK);
            return false;
        }
        return true;
    }

    private boolean validateEmailUnicity(String email, BusinessException be) {
        if (utilisateurDAO.findEmail(email)) {
            be.add(BusinessCode.VALIDATION_USER_EMAIL_UNICITY);
            return false;
        }
        return true;
    }

    private boolean validateTelephone(String t, BusinessException be) {
        if (t.isBlank() || t == null) {
            be.add(BusinessCode.VALIDATION_USER_PHONE);
            return false;
        }
        if (t.length() > 50) {
            be.add(BusinessCode.VALIDATION_USER_PHONE);
            return false;
        }
        return true;
    }

    private boolean validatePassword(String motDePasse, BusinessException be) {
        if (motDePasse.isBlank() || motDePasse == null) {
            be.add(BusinessCode.VALIDATION_USER_PASSWORD);
            return false;
        }
        return true;
    }

    private boolean validateAdresse(Adresse adresse, BusinessException be) { //if exist - do not create new
        if (adresse == null) {
            be.add(BusinessCode.VALIDATION_USER_ADRESSE_NULL);
            return false;
        }
        if (adresse.getId() < 1) {
            be.add(BusinessCode.VALIDATION_USER_ADRESSE_UNKNOWN);
            return false;
        }
        try {
            Adresse temp = adresseDAO.findById(adresse.getId());
            if (temp == null) {
                be.add(BusinessCode.VALIDATION_USER_ADRESSE_UNKNOWN);
                return false;
            }
        } catch (DataAccessException e) {
            be.add(BusinessCode.VALIDATION_USER_ADRESSE_UNKNOWN);
            return false;
        }
        return true;
    }

    private int validateAddressExist(Adresse a) {
        int temp = 0;
        try {
            temp = adresseDAO.findAddressExists(a);
        } catch (NullPointerException npe) {

        }
        return temp;
    }
}
