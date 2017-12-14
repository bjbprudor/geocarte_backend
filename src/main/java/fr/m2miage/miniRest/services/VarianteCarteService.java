package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.model.CarteUtilisateur;
import fr.m2miage.miniRest.model.Monument;
import fr.m2miage.miniRest.model.VarianteCarte;
import fr.m2miage.miniRest.repository.CartePostaleRepository;
import fr.m2miage.miniRest.repository.VarianteCarteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("varianteCarteService")
public class VarianteCarteService
{

    public static final Logger log = Logger.getLogger(VarianteCarteService.class);


    @Autowired
    private VarianteCarteRepository repo;

    @Autowired
    private CartePostaleRepository carteRepo;

    public List<VarianteCarte> getVariantesByCommuneName(String nom)
    {
        List<VarianteCarte> result = null;
        try
        {
            result = repo.findAllByCommuneName(nom);
        }
        catch (Exception ex)
        {

        }
        return result;
    }

    public List<VarianteCarte> getVariantesByLegende(String nom)
    {
        List<VarianteCarte> result = null;
        try
        {
            result = repo.findAllByLegende(nom);
        }
        catch (Exception ex)
        {

        }
        return result;
    }

    public List<VarianteCarte> getVariantesByEditeurName(String nom)
    {
        List<VarianteCarte> result = null;
        try
        {
            result = repo.findAllByEditeurName(nom);
        }
        catch (Exception ex)
        {

        }
        return result;
    }

    public List<VarianteCarte> getVariantesByUsername(Integer id)
    {
        List<VarianteCarte> result = null;
        try
        {
            //result = new ArrayList<>();
            result = repo.findAllByUsername(id);
            /*for (VarianteCarte vc : repo.findAll())
            {
                List<CarteUtilisateur> lu = vc.getCarteUtilisateurs();
                int i = 0;
                boolean b = false;
                while (i < lu.size() && b == false)
                {
                    b = lu.get(i).getId().getUtilisateur().getId() == id;
                    i++;
                }
                if(b) { result.add(vc); }
            }*/
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
        }
        log.info(result.size());
        return result;
    }

    public List<VarianteCarte> getVariantesByTypeMonument(String nom)
    {
        List<VarianteCarte> result = null;
        try
        {
            result = repo.findAllByTypeMonument(nom);
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
        }
        log.info(result.size());
        return result;
    }

}
