package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.model.CarteUtilisateurId;
import fr.m2miage.miniRest.repository.CartePostaleRepository;
import fr.m2miage.miniRest.repository.CarteUtilisateurRepository;
import fr.m2miage.miniRest.repository.UtilisateurRepository;
import fr.m2miage.miniRest.repository.VarianteCarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    public List<Integer> getUsersIdByCp(Integer cpId){
        List<Integer> result = null;
        try
        {
            result = repo.findUsersIdByCp(cpId);
        }
        catch (Exception ex)
        {

        }
        return result;
    }
}
