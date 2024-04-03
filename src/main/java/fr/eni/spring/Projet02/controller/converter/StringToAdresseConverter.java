package fr.eni.spring.Projet02.controller.converter;

import fr.eni.spring.Projet02.bll.AdresseService;
import fr.eni.spring.Projet02.bll.ArticleService;
import fr.eni.spring.Projet02.bo.Adresse;
import fr.eni.spring.Projet02.bo.ArticleAVendre;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToAdresseConverter implements Converter<String, Adresse> {
    private AdresseService adresseService;

    public StringToAdresseConverter(AdresseService service){
        this.adresseService=adresseService;
    }

    @Override
    public Adresse convert(String id){
        long theId = Long.parseLong(id);
        return adresseService.consulterUneAdresse(theId);
    }
}
