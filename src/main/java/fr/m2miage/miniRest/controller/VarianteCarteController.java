package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.decorator.VarianteCarteDeco;
import fr.m2miage.miniRest.model.CartePostale;
import fr.m2miage.miniRest.model.VarianteCarte;
import fr.m2miage.miniRest.model.VarianteCarteId;
import fr.m2miage.miniRest.model.VarianteCarteUpdate;
import fr.m2miage.miniRest.repository.CartePostaleRepository;
import fr.m2miage.miniRest.repository.CommuneRepository;
import fr.m2miage.miniRest.repository.EditeurRepository;
import fr.m2miage.miniRest.repository.VarianteCarteRepository;
import fr.m2miage.miniRest.services.VarianteCarteService;
import fr.m2miage.miniRest.util.CustomErrorType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
public class VarianteCarteController
{

    public static final Logger log = Logger.getLogger(VarianteCarteController.class);

    @Autowired
    private VarianteCarteService varianteCarteService;

    @Autowired
    private VarianteCarteRepository repo;

    @Autowired
    private CartePostaleRepository carteRepo;

    @Autowired
    private CommuneRepository communeRepository;

    @Autowired
    private EditeurRepository editeurRepository;

    //region Methodes GET

    /**
     * Recupere toutes les variantes de la base
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/", method = RequestMethod.GET)
    public ResponseEntity<List<VarianteCarteDeco>> listAllVarianteCarte(@RequestParam(value = "userId") Integer userId)
    {
        List<VarianteCarteDeco> list = varianteCarteService.getAllVariantes(userId);
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Recupere toutes les variantes de carte correspondant a ces parametres
     * @return
     */
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/", params = { "typemonument", "editeur","commune","legende" }, method = RequestMethod.GET)
    public ResponseEntity<List<VarianteCarteDeco>> getVcByCommuneName(@RequestParam(value = "typemonument", required = false) Integer typeMonument,
                                                                      @RequestParam(value = "editeur", required = false) Integer editeur,
                                                                      @RequestParam(value = "commune", required = false) String insee,
                                                                      @RequestParam(value = "legende", required = false) String legende,
                                                                      @RequestParam(value = "userId", required = false) Integer userId) {
        List<VarianteCarteDeco> list = varianteCarteService.getVariantesByFiltre(typeMonument,editeur,insee,legende, userId);
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    // -------------------Recupere toutes les variantes dont l'utilisateur possede ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/", params = "user_id", method= RequestMethod.GET)
    public ResponseEntity<List<VarianteCarteDeco>> getAllVcByUsername(@RequestParam(value = "user_id") Integer login)
    {
        List<VarianteCarteDeco> list = varianteCarteService.getVariantesByUsername(login);
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere toutes les légendes commence par le paramètre ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/legendes/", method= RequestMethod.GET)
    public ResponseEntity<List<String>> getAllLegendesBy()
    {
        List<String> list = varianteCarteService.getLegendes();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere toutes les légendes commence par le paramètre ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/legendes/", params = "legende", method= RequestMethod.GET)
    public ResponseEntity<List<String>> getAllLegendesBy(@RequestParam(value = "legende") String legende)
    {
        List<String> list = varianteCarteService.getLegendesBy(legende);
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    //endregion

    // -------------------Recupere un VarianteCarte------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/{carte}/{variante}", method = RequestMethod.GET)
    public ResponseEntity<?> getVarianteCarte(@PathVariable("carte") int carte, @PathVariable("variante") int variante)
    {
        CartePostale c = carteRepo.findOne(carte);
        VarianteCarteId vid = new VarianteCarteId(variante,c);

        String msg = String.format("Fetching VarianteCarte with id {%s}",vid);
        log.info(msg);

        VarianteCarteDeco current = varianteCarteService.getVarianteById(vid);
        if (current == null)
        {
            msg = String.format("VarianteCarte with id {%s} not found.",vid);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // -------------------Create a VarianteCarte-------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/varianteCarte/", method = RequestMethod.POST)
    public ResponseEntity<?> createVarianteCarte(@RequestBody VarianteCarte target, UriComponentsBuilder ucBuilder)
    {
        String msg = String.format("Creating VarianteCarte : {%s}", target);
        log.info(msg);
        VarianteCarte current = repo.findOne(target.getId());
        if (current != null)
        {
            msg = String.format("Unable to create. A VarianteCarte with id {%s} already exist", target.getId());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.CONFLICT);
        }
        repo.save(target);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/varianteCarte/{id}").buildAndExpand(target.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a VarianteCarte ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/varianteCarte/{carte}/{variante}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateVarianteCarte(@PathVariable("carte") int carte, @PathVariable("variante") int variante, @RequestBody VarianteCarte target)
    {

        CartePostale c = carteRepo.findOne(carte);
        VarianteCarteId vid = new VarianteCarteId(variante,c);

        String msg = String.format("Updating VarianteCarte with id {%s}",vid);
        log.info(msg);

        VarianteCarte current = repo.findOne(vid);
        if (current == null)
        {
            msg = String.format("Unable to update. VarianteCarte with id {%s} not found.",vid);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        current.setFace(target.getFace());
        current.setDos(target.getDos());
        current.setLegende(target.getLegende());
        repo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // ------------------- Delete a VarianteCarte-----------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/{carte}/{variante}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteVarianteCarte(@PathVariable("carte") int carte, @PathVariable("variante") int variante)
    {

        CartePostale c = carteRepo.findOne(carte);
        VarianteCarteId vid = new VarianteCarteId(variante,c);

        String msg = String.format("Fetching & Deleting VarianteCarte with id {%s}",vid);
        log.info(msg);

        VarianteCarte current = repo.findOne(vid);
        if (current == null)
        {
            msg = String.format("Unable to delete. VarianteCarte with id {%s} not found.",vid);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        repo.delete(vid);
        return new ResponseEntity<VarianteCarte>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All VarianteCarte-----------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/", method = RequestMethod.DELETE)
    public ResponseEntity<VarianteCarte> deleteAllVarianteCarte()
    {
        log.info("Deleting All VarianteCarte");
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // ------------------- Update coordonnees de a CartePostale ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/test/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateVariante(@PathVariable("id") Integer varId,  @RequestParam(value = "carteId") Integer carteId, @RequestBody VarianteCarteUpdate target)
    {

        String msg = String.format("Updating CartePostale with id {%s}",carteId);
        log.info(msg);

        System.out.println("-----------------------"+communeRepository.findOne(target.getCommuneId()).getLatitude());

        CartePostale cp = carteRepo.findOne(carteId);
        cp.setCommune(communeRepository.findOne(target.getCommuneId()));
        cp.setEditeur(editeurRepository.findOne(target.getEditeurId()));

        VarianteCarteId varianteCarteId = new VarianteCarteId();
        varianteCarteId.setCartePostale(carteRepo.findOne(carteId));
        varianteCarteId.setId(varId);

        VarianteCarte current = repo.findOne(varianteCarteId);
        current.setLegende(target.getLegende());

        if (current == null)
        {
            msg = String.format("Unable to update coordonnees. CartePostale with id {%s} not found.",carteId);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }

        //repo.save(current);
        //carteRepo.save(cp);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

}