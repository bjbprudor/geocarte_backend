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

}
