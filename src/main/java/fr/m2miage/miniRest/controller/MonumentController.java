package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.model.Monument;
import fr.m2miage.miniRest.repository.MonumentRepository;
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
public class MonumentController
{

    public static final Logger log = Logger.getLogger(MonumentController.class);

    @Autowired
    private MonumentRepository repo;

    // -------------------Recupere tous les Monuments---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/monument/", method = RequestMethod.GET)
    public ResponseEntity<List<Monument>> listAllMonument()
    {
        List<Monument> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere un Monument------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/monument/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getMonument(@PathVariable("id") int id)
    {
        String msg = String.format("Fetching Monument with id {%s}", id);
        log.info(msg);
        Monument current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Monument with id {%s} not found.", id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // -------------------Create a Monument-------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/monument/", method = RequestMethod.POST)
    public ResponseEntity<?> createMonument(@RequestBody Monument target, UriComponentsBuilder ucBuilder)
    {
        String msg = String.format("Creating Monument : {%s}", target);
        log.info(msg);
        Monument current = repo.findOne(target.getId());
        if (current != null)
        {
            msg = String.format("Unable to create. A Monument with id {%s} already exist", target.getId());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.CONFLICT);
        }
        repo.save(target);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/monument/{id}").buildAndExpand(target.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Monument ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/monument/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMonument(@PathVariable("id") int id, @RequestBody Monument target)
    {
        String msg = String.format("Updating Monument with id {%s}",id);
        log.info(msg);
        Monument current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to update. Monument with id {%s} not found.",id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        current.setNom(target.getNom());
        current.setAnneeConstruction(target.getAnneeConstruction());
        current.setCommune(target.getCommune());
        current.setDivers(target.getDivers());
        current.setLatitude(target.getLatitude());
        current.setLongitude(target.getLongitude());
        current.setType(target.getType());
        repo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // ------------------- Delete a Monument-----------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/monument/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMonument(@PathVariable("id") int id)
    {
        String msg = String.format("Fetching & Deleting Monument with id {%s}", id);
        log.info(msg);
        Monument current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to delete. Monument with id {%s} not found.", id);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        repo.delete(id);
        return new ResponseEntity<Monument>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Monument-----------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/monument/", method = RequestMethod.DELETE)
    public ResponseEntity<Monument> deleteAllMonument()
    {
        log.info("Deleting All Monument");
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}