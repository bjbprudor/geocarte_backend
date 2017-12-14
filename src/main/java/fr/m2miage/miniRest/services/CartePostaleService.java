package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.controller.CartePostaleController;
import fr.m2miage.miniRest.repository.CartePostaleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartePostaleService")
public class CartePostaleService
{

    public static final Logger log = Logger.getLogger(CartePostaleService.class);

    @Autowired
    private CartePostaleRepository repo;

    public List<String> getCommunesBy(String nom)
    {
        List<String> result = null;
        try
        {
            result = repo.findAllCommuneBy(nom);
        }
        catch (Exception ex)
        {

        }
        return result;
    }

}
