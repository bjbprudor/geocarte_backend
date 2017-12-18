package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.controller.AncienNomController;
import fr.m2miage.miniRest.model.AncienNom;
import fr.m2miage.miniRest.model.AncienNomId;
import fr.m2miage.miniRest.model.Commune;
import fr.m2miage.miniRest.repository.AncienNomRepository;
import fr.m2miage.miniRest.repository.CommuneRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ancienNomService")
public class AncienNomService
{

    public static final Logger log = Logger.getLogger(AncienNomController.class);

    @Autowired
    private AncienNomRepository repo;

    @Autowired
    private CommuneRepository communeRepo;

    public List<AncienNom> getAll()
    {
        List<AncienNom> lst = null;
        try
        {
            lst = repo.findAll();
        }
        catch (Exception ex)
        {

        }
        return lst;
    }

    public List<AncienNom> getAllFromInsee(String insee)
    {
        List<AncienNom> lst = null;
        try
        {
            lst = repo.findAllByIdCommuneInsee(insee);
        }
        catch (Exception ex)
        {

        }
        return lst;
    }

    public AncienNom getOne(AncienNomId id)
    {
        AncienNom result = null;
        try
        {
            result = repo.findOne(id);
        }
        catch (Exception ex)
        {

        }
        return result;
    }

    public AncienNom getOne(Integer id, String insee)
    {
        AncienNom result = null;
        try
        {
            Commune commune = communeRepo.findOne(insee);
            AncienNomId aid = new AncienNomId(id,commune);
            result = repo.findOne(aid);
        }
        catch (Exception ex)
        {

        }
        return result;
    }

    public void create(AncienNom obj)
    {
        AncienNomId id = obj.getId();
        if(id.getId() == 0)
        {
            int last = repo.findLastId(id.getCommune());
            id.setId(last+1);
            obj.setId(id);
        }
        repo.save(obj);
    }

    public void update(AncienNom obj)
    {
        repo.save(obj);
    }

    public void delete() {
        repo.deleteAll();
    }

    public boolean delete(Integer id, String insee) {

        try
        {
            Commune commune = communeRepo.getOne(insee);
            if(commune == null) { return false; }
            AncienNomId aid = new AncienNomId(id,commune);
            repo.delete(aid);
        }
        catch (Exception ex)
        {
            return false;
        }
        return true;
    }

}
