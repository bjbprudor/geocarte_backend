package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.model.VarianteCarte;
import fr.m2miage.miniRest.repository.CartePostaleRepository;
import fr.m2miage.miniRest.repository.VarianteCarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("varianteCarteService")
public class VarianteCarteService
{


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

    public List<VarianteCarte> getVariantesByTypeMonument(String nom)
    {
        List<VarianteCarte> result = null;
        try
        {
            result = repo.findAllByTypeMonument(nom);
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

    public List<VarianteCarte> getVariantesByUsername(String nom)
    {
        List<VarianteCarte> result = null;
        try
        {
            result = repo.findAllByUsername(nom);
        }
        catch (Exception ex)
        {

        }
        return result;
    }


}
