package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.decorator.VarianteCarteDeco;
import fr.m2miage.miniRest.model.CartePostale;
import fr.m2miage.miniRest.model.VarianteCarte;
import fr.m2miage.miniRest.model.VarianteCarteId;
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

    static final String serverPhotoFolderLocation = "src/img/mtil/";

    @Autowired
    private VarianteCarteRepository repo;

    @Autowired
    private CartePostaleRepository carteRepo;

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

    public List<VarianteCarteDeco> getVariantesByFiltre(Integer typeMonument, Integer editeur, String insee, String legende)
    {
        List<VarianteCarte> result = new ArrayList<>();
        try
        {
            boolean m = typeMonument != null && typeMonument != 0;
            boolean e = editeur != null && editeur != 0;
            boolean c = insee != null && insee != "";
            boolean l = legende != null && legende != "";

            if (!m & !e & !c & !l) { result = repo.findAll(); }
            if (!m & !e & !c & l) { result = repo.findAllByLegendeLike(legende); }
            if(!m & !e & c & !l) { result = repo.findAllByIdCartePostaleCommuneInsee(insee);}
            if(!m & !e & c & l) { result = repo.findAllByLegendeLikeAndIdCartePostaleCommuneInsee(legende,insee);}
            if(!m & e & !c & !l) { result = repo.findAllByIdCartePostaleEditeurId(editeur);}
            if(!m & e & !c & l) { result = repo.findAllByLegendeLikeAndIdCartePostaleEditeurId(legende,editeur);}
            if(!m & e & c & !l) { result = repo.findAllByIdCartePostaleCommuneInseeAndAndIdCartePostaleEditeurId(insee,editeur);}
            if(!m & e & c & l) { result = repo.findAllByLegendeLikeAndIdCartePostaleCommuneInseeAndIdCartePostaleEditeurId(legende,insee,editeur);}
            if(m & !e & !c & !l){ result = repo.findAllByTypeMonument(typeMonument);}
            if(m & !e & !c & l) { result = repo.findAllByTypeMonumentAndLegende(typeMonument,legende);}
            if(m & !e & c & !l) { result = repo.findAllByTypeMonumentAndCommune(typeMonument,insee);}
            if(m & !e & c & l) { result = repo.findAllByTypeMonumentAndCommuneAndLegende(typeMonument,insee,legende);}
            if(m & e & !c & l) { result = repo.findAllByTypeMonumentAndEditeur(typeMonument,editeur);}
            if(m & e & !c & l) { result = repo.findAllByTypeMonumentAndEditeurAndLegende(typeMonument,editeur,legende);}
            if(m & e & c & !l) { result = repo.findAllByTypeMonumentAndEditeurAndCommune(typeMonument,editeur,insee);}
            if(m & e & c & l) { result = repo.findAllByTypeMonumentAndEditeurAndCommuneAndLegende(typeMonument,editeur,insee,legende);}

        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
        }
        return getVarianteDecoList(result);
    }

    public VarianteCarteDeco getVarianteById(VarianteCarteId varCarteId) {
        VarianteCarte varCarte = repo.findOneByCarteIdAndVarId(varCarteId);

        VarianteCarteDeco varCarteDeco = new VarianteCarteDeco();
        varCarteDeco.setVarianteCarte(varCarte);
        if(varCarte.getFace() != null){
            String fileName = serverPhotoFolderLocation+varCarte.getFace();
            if(ImageConverter.isExistingFile(fileName)){
                String base64 = ImageConverter.imageToBase64(fileName);
                varCarteDeco.setBase64Photo(base64);
            }
        }

        return varCarteDeco;
    }

    private List<VarianteCarteDeco> getVarianteDecoList(List<VarianteCarte> varianteCartes)
    {
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
