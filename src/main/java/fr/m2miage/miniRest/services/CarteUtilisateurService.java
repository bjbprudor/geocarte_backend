package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.model.*;
import fr.m2miage.miniRest.repository.CartePostaleRepository;
import fr.m2miage.miniRest.repository.CarteUtilisateurRepository;
import fr.m2miage.miniRest.repository.UtilisateurRepository;
import fr.m2miage.miniRest.repository.VarianteCarteRepository;
import fr.m2miage.miniRest.requestobjects.CarteUtilisateurBody;
import fr.m2miage.miniRest.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public CarteUtilisateur create(CarteUtilisateurBody cu)
    {
        CartePostale cp = cpRepo.findOne(cu.getIdCarte());
        VarianteCarteId vid = new VarianteCarteId(cu.getIdVariante(),cp);
        VarianteCarte vc = varRepo.findOne(vid);
        Utilisateur u = userRepo.getOne(cu.getIdUtilisateur());
        CarteUtilisateurId cuid = new CarteUtilisateurId(vc,u);
        CarteUtilisateur current = repo.findOne(cuid);
        if (current == null)
        {
            CarteUtilisateur cut = new CarteUtilisateur(cuid,cu.getNombreExemplaire());
            repo.save(cut);
            return cut;
            /*
            msg = String.format("Unable to create. A CarteUtilisateur with id {%s} already exist", target.getId());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.CONFLICT);
            */
        }

        return null;
    }

    public CarteUtilisateur update(CarteUtilisateurBody cu)
    {
        CartePostale cp = cpRepo.findOne(cu.getIdCarte());
        VarianteCarteId vid = new VarianteCarteId(cu.getIdVariante(),cp);
        VarianteCarte vc = varRepo.findOne(vid);
        Utilisateur u = userRepo.getOne(cu.getIdUtilisateur());
        CarteUtilisateurId cuid = new CarteUtilisateurId(vc,u);
        CarteUtilisateur current = repo.findOne(cuid);
        if(current != null)
        {
            current.setNombreExemplaires(cu.getNombreExemplaire());
            repo.save(current);
            return current;
        }
        current = new CarteUtilisateur(cuid,cu.getNombreExemplaire());
        repo.save(current);
        return current;
    }

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


    public boolean isOwned(Integer carteId, Integer userId){
        return !repo.isCartOwnedByUser(carteId, userId).isEmpty();
    }
}
