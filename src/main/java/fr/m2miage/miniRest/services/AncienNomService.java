package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.controller.AncienNomController;
import fr.m2miage.miniRest.model.AncienNom;
import fr.m2miage.miniRest.model.AncienNomId;
import fr.m2miage.miniRest.model.Commune;
import fr.m2miage.miniRest.repository.AncienNomRepository;
import fr.m2miage.miniRest.repository.CommuneRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(obj.getId().getId() == 0)
        {

        }
        repo.save(obj);
    }

    public void update()
    {

    }

    public void delete()
    {

    }

    public void delete(Integer id, String insee)
    {

    }

}
