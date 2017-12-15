package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.decorater.VarianteCarteDeco;
import fr.m2miage.miniRest.model.CartePostale;
import fr.m2miage.miniRest.model.VarianteCarte;
import fr.m2miage.miniRest.model.VarianteCarteId;
import fr.m2miage.miniRest.repository.CartePostaleRepository;
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

    // -------------------Recupere tous les VarianteCartes---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/", method = RequestMethod.GET)
    public ResponseEntity<List<VarianteCarte>> listAllVarianteCarte()
    {
        List<VarianteCarte> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere tous les VarianteCartes en fonction du nom de Commune---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/", params = "communeName", method = RequestMethod.GET)
    public ResponseEntity<List<VarianteCarteDeco>> getVcByCommuneName(@RequestParam(value = "communeName") String communeName)
    {
        List<VarianteCarteDeco> list = varianteCarteService.getVariantesByCommuneName(communeName);
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere tous les CartePostales en fonction du type de monument ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/",params = "monumentType",  method = RequestMethod.GET)
    public ResponseEntity<List<VarianteCarteDeco>> getVcByMonumentType(@RequestParam(value = "monumentType") String monumentType)
    {
        List<VarianteCarteDeco> list = varianteCarteService.getVariantesByTypeMonument(monumentType);
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere tous les CartePostales en fonction du nom d'un éditeur ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/", params = "editeurName", method = RequestMethod.GET)
    public ResponseEntity<List<VarianteCarteDeco>> getVcByEditeurName(@RequestParam(value = "editeurName") String editeurName)
    {
        List<VarianteCarteDeco> list = varianteCarteService.getVariantesByEditeurName(editeurName);
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere toutes les variantes dont la légende commence par le paramètre ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/", params = "legende", method= RequestMethod.GET)
    public ResponseEntity<List<VarianteCarteDeco>> getAllVarianteCarteStartingByParam(@RequestParam(value = "legende") String legende)
    {
        List<VarianteCarteDeco> list = varianteCarteService.getVariantesByLegende(legende);
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere toutes les variantes dont la légende commence par le paramètre ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/varianteCarte/", params = "login", method= RequestMethod.GET)
    public ResponseEntity<List<VarianteCarteDeco>> getAllVcByUsername(@RequestParam(value = "login") Integer login)
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

    // -------------------Recupere un VarianteCarte------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/varianteCarte/{carte}/{variante}", method = RequestMethod.GET)
    public ResponseEntity<?> getVarianteCarte(@PathVariable("carte") int carte, @PathVariable("variante") int variante)
    {
        CartePostale c = carteRepo.findOne(carte);
        VarianteCarteId vid = new VarianteCarteId(variante,c);

        String msg = String.format("Fetching VarianteCarte with id {%s}",vid);
        log.info(msg);

        VarianteCarte current = repo.findOne(vid);
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


}