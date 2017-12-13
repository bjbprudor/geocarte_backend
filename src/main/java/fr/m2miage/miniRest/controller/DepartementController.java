package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.model.Departement;
import fr.m2miage.miniRest.repository.DepartementRepository;
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
public class DepartementController
{

    public static final Logger log = Logger.getLogger(DepartementController.class);

    @Autowired
    private DepartementRepository repo;


    // -------------------Recupere tous les Departements---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/departement/", method = RequestMethod.GET)
    public ResponseEntity<List<Departement>> listAllDepartement()
    {
        List<Departement> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere un Departement------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/departement/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getDepartement(@PathVariable("id") String id)
    {
        String msg = String.format("Fetching Departement with id {%s}", id);
        log.info(msg);
        Departement current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Departement with id {%s} not found.", id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // -------------------Create a Departement-------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/departement/", method = RequestMethod.POST)
    public ResponseEntity<?> createDepartement(@RequestBody Departement target, UriComponentsBuilder ucBuilder)
    {
        String msg = String.format("Creating Departement : {%s}", target);
        log.info(msg);

        Departement current = repo.findOne(target.getNumero());
        if (current != null)
        {
            msg = String.format("Unable to create. A Departement with id {%s} already exist", target.getNumero());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.CONFLICT);
        }
        repo.save(target);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/departement/{id}").buildAndExpand(target.getNumero()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Departement ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/departement/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateDepartement(@PathVariable("id") String id, @RequestBody Departement target)
    {
        String msg = String.format("Updating Departement with id {%s}",id);
        log.info(msg);
        Departement current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to update. Departement with id {%s} not found.",id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        current.setNumero(target.getNumero());
        current.setNom(target.getNom());
        repo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // ------------------- Delete a Departement-----------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/departement/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteDepartement(@PathVariable("id") String id)
    {
        String msg = String.format("Fetching & Deleting Departement with id {%s}", id);
        log.info(msg);
        Departement current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to delete. Departement with id {%s} not found.", id);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        repo.delete(id);
        return new ResponseEntity<Departement>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Departement-----------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/departement/", method = RequestMethod.DELETE)
    public ResponseEntity<Departement> deleteAllDepartement()
    {
        log.info("Deleting All Departement");
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}