package fr.eni.spring.Projet02.exceptions;

import java.util.ArrayList;
import java.util.List;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    // Liste des clefs d'externalisation
    private List<String> clefsExternalisations;
    public BusinessException() {
        super();
    }
    public BusinessException(Throwable cause) {
        super(cause);
    }
    public List<String> getClefsExternalisations() {
        return clefsExternalisations;
    }

    public void add(String clef) {
        if (clefsExternalisations == null) {
            clefsExternalisations = new ArrayList<>();
        }
        clefsExternalisations.add(clef);
    }

    public boolean isValid() {
        return clefsExternalisations == null || clefsExternalisations.isEmpty();
    }
}