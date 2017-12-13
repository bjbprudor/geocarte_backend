package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.model.Commune;
import fr.m2miage.miniRest.repository.CommuneRepository;
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
public class CommuneController
{

    public static final Logger log = Logger.getLogger(CommuneController.class);

    @Autowired
    private CommuneRepository repo;


    // -------------------Recupere tous les Communes---------------------------------------------

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/commune/", method = RequestMethod.GET)
    public ResponseEntity<List<Commune>> listAllCommune()
    {
        List<Commune> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere un Commune------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/commune/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCommune(@PathVariable("id") String id)
    {
        String msg = String.format("Fetching Commune with id {%s}", id);
        log.info(msg);
        Commune current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Commune with id {%s} not found.", id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // -------------------Create a Commune-------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/commune/", method = RequestMethod.POST)
    public ResponseEntity<?> createCommune(@RequestBody Commune target, UriComponentsBuilder ucBuilder)
    {
        String msg = String.format("Creating Commune : {%s}", target);
        log.info(msg);
        Commune current = repo.findOne(target.getInsee());
        if (current != null)
        {
            msg = String.format("Unable to create. A Commune with id {%s} already exist", target.getInsee());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.CONFLICT);
        }
        repo.save(target);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/commune/{id}").buildAndExpand(target.getInsee()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Commune ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/commune/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCommune(@PathVariable("id") String id, @RequestBody Commune target)
    {
        String msg = String.format("Updating Commune with id {%s}",id);
        log.info(msg);
        Commune current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to update. Commune with id {%s} not found.",id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        current.setNom(target.getNom());
        current.setDepartement(target.getDepartement());
        current.setArticle(target.getArticle());
        current.setLatitude(target.getLatitude());
        current.setLongitude(target.getLongitude());
        repo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // ------------------- Delete a Commune-----------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/commune/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCommune(@PathVariable("id") String id)
    {
        String msg = String.format("Fetching & Deleting Commune with id {%s}", id);
        log.info(msg);
        Commune current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to delete. Commune with id {%s} not found.", id);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        repo.delete(id);
        return new ResponseEntity<Commune>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Commune-----------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/commune/", method = RequestMethod.DELETE)
    public ResponseEntity<Commune> deleteAllCommune()
    {
        log.info("Deleting All Commune");
        repo.deleteAll();
        return new ResponseEntity<Commune>(HttpStatus.NO_CONTENT);
    }


}
