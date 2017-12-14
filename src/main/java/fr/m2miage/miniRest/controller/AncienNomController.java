package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.model.AncienNom;
import fr.m2miage.miniRest.model.AncienNomId;
import fr.m2miage.miniRest.model.Commune;
import fr.m2miage.miniRest.repository.AncienNomRepository;
import fr.m2miage.miniRest.repository.CommuneRepository;
import fr.m2miage.miniRest.services.AncienNomService;
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
@RequestMapping("/")
public class AncienNomController
{
    public static final Logger log = Logger.getLogger(AncienNomController.class);

    @Autowired
    private AncienNomService ancienNomService;

    // -------------------Recupere tous les AncienNoms---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/ancienNom/", method = RequestMethod.GET)
    public ResponseEntity<List<AncienNom>> listAllAncienNom()
    {
        List<AncienNom> list = ancienNomService.getAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere un AncienNom------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/ancienNom/id={id}insee={insee}", method = RequestMethod.GET)
    public ResponseEntity<?> getAncienNom(@PathVariable("id") int id, @PathVariable("insee") String insee)
    {

        String msg = String.format("Fetching AncienNom with id {%s} insee {%s}", id, insee);
        log.info(msg);

        AncienNom current = ancienNomService.getOne(id,insee);
        if (current == null)
        {
            msg = String.format("AncienNom with id {%s} insee {%s} not found.", id, insee);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // -------------------Create a AncienNom-------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/ancienNom/", method = RequestMethod.POST)
    public ResponseEntity<?> createAncienNom(@RequestBody AncienNom target, UriComponentsBuilder ucBuilder)
    {
        String msg = String.format("Creating AncienNom : {%s}", target);
        log.info(msg);

        AncienNom current = ancienNomService.getOne(target.getId());
        if (current != null)
        {
            msg = String.format("Unable to create. A AncienNom with id {%s} already exist", target.getId());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.CONFLICT);
        }

        ancienNomService.create(target);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/ancienNom/id={id}insee={insee}").buildAndExpand(target.getId().getId(),target.getId().getCommune().getInsee()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a AncienNom ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/ancienNom/{id}/{insee}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateAncienNom(@PathVariable("id") int id, @PathVariable("insee") String insee,@RequestBody AncienNom target)
    {

        //String msg = String.format("Updating AncienNom with id {%s}",aid);
        //log.info(msg);

        AncienNom current = ancienNomService.getOne(id,insee);
        if (current == null)
        {
            //msg = String.format("Unable to update. AncienNom with id {%s} not found.",aid);
            //log.error(msg);
            //return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        current.setArticle(target.getArticle());
        current.setNom(target.getNom());
        //repo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // ------------------- Delete a AncienNom-----------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/ancienNom/{id}/{insee}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAncienNom(@PathVariable("id") int id, @PathVariable("insee") String insee)
    {

        //String msg = String.format("Fetching & Deleting AncienNom with id {%s}", aid);
        //log.info(msg);

        AncienNom current = ancienNomService.getOne(id,insee);
        if (current == null)
        {
            //msg = String.format("Unable to delete. AncienNom with id {%s} not found.", aid);
            //return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        //repo.delete(aid);
        return new ResponseEntity<AncienNom>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All AncienNom-----------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/ancienNom/", method = RequestMethod.DELETE)
    public ResponseEntity<AncienNom> deleteAllAncienNom()
    {
        log.info("Deleting All AncienNom");
        //repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}