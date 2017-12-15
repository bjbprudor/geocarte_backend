package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.decorater.VarianteCarteDeco;
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

    static final String serverPhotoFolderLocation = "C:\\source\\mtil\\";

    @Autowired
    private VarianteCarteRepository repo;

    @Autowired
    private CartePostaleRepository carteRepo;

    public List<VarianteCarteDeco> getVariantesByCommuneName(String nom)
    {
        List<VarianteCarteDeco> result = new ArrayList<>();
        try
        {
            List<VarianteCarte> trans = repo.findAllByCommuneName(nom);
            result = this.getVarianteDecoList(trans);
        }
        catch (Exception ex)
        {

        }
        return result;
    }

    public List<VarianteCarteDeco> getVariantesByLegende(String nom)
    {
        List<VarianteCarteDeco> result = new ArrayList<>();
        try
        {
            List<VarianteCarte> trans = repo.findAllByLegende(nom);
            result = this.getVarianteDecoList(trans);
        }
        catch (Exception ex)
        {

        }
        return result;
    }

    public List<VarianteCarteDeco> getVariantesByEditeurName(String nom)
    {
        List<VarianteCarteDeco> result = new ArrayList<>();
        try
        {
            List<VarianteCarte> trans = repo.findAllByEditeurName(nom);
            result = this.getVarianteDecoList(trans);
        }
        catch (Exception ex)
        {

        }
        return result;
    }

    public List<VarianteCarteDeco> getVariantesByUsername(Integer id)
    {
        List<VarianteCarteDeco> result = new ArrayList<>();
        try
        {
            List<VarianteCarte> trans = repo.findAllByUsername(id);
            result = this.getVarianteDecoList(trans);
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
        }
        log.info(result.size());
        return result;
    }

    public List<VarianteCarteDeco> getVariantesByTypeMonument(String nom)
    {
        List<VarianteCarteDeco> result = new ArrayList<>();
        try
        {
            List<VarianteCarte> trans = repo.findAllByTypeMonument(nom);
            result = this.getVarianteDecoList(trans);
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
        }
        log.info(result.size());
        return result;
    }

    public List<String> getLegendes()
    {
        List<String> result = null;
        try
        {
            result = repo.findAllLegendes();
        }
        catch (Exception ex)
        {

        }
        return result;
    }

    public List<String> getLegendesBy(String legende)
    {
        List<String> result = null;
        try
        {
            result = repo.findAllLegendesBy(legende);
        }
        catch (Exception ex)
        {

        }
        return result;
    }

    private List<VarianteCarteDeco> getVarianteDecoList(List<VarianteCarte> varianteCartes){
        List<VarianteCarteDeco> rtn = new ArrayList<>();
        for(VarianteCarte varCarte : varianteCartes){
            VarianteCarteDeco varCarteDeco = new VarianteCarteDeco();
            varCarteDeco.setVarianteCarte(varCarte);
            if(varCarte.getFace() != null){
                String fileName = serverPhotoFolderLocation+varCarte.getFace();
                if(ImageConverter.isExistingFile(fileName)){
                    String base64 = ImageConverter.imageToBase64(fileName);
                    varCarteDeco.setBase64Photo(base64);
                }
            }
            rtn.add(varCarteDeco);
        }
        return rtn;
    }

}
