package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.controller.CartePostaleController;
import fr.m2miage.miniRest.repository.CartePostaleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cartePostaleService")
public class CartePostaleService
{

    public static final Logger log = Logger.getLogger(CartePostaleService.class);

    @Autowired
    private CartePostaleRepository repo;

}
