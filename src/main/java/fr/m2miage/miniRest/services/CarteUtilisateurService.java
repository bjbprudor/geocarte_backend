package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.repository.CartePostaleRepository;
import fr.m2miage.miniRest.repository.CarteUtilisateurRepository;
import fr.m2miage.miniRest.repository.UtilisateurRepository;
import fr.m2miage.miniRest.repository.VarianteCarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("carteUtilisateurService")
public class CarteUtilisateurService
{

    @Autowired
    private CarteUtilisateurRepository repo;

    @Autowired
    private UtilisateurRepository userRepo;

    @Autowired
    private VarianteCarteRepository varRepo;

    @Autowired
    private CartePostaleRepository cpRepo;


}
