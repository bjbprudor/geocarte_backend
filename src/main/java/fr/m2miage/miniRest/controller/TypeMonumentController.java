package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.model.TypeMonument;
import fr.m2miage.miniRest.repository.TypeMonumentRepository;
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
public class TypeMonumentController
{

    public static final Logger log = Logger.getLogger(TypeMonumentController.class);

    @Autowired
    private TypeMonumentRepository repo;


    // -------------------Recupere tous les typeMonuments---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/typeMonument/", method = RequestMethod.GET)
    public ResponseEntity<List<TypeMonument>> listAllTypeMonument()
    {
        List<TypeMonument> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere un TypeMonument------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/typeMonument/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTypeMonument(@PathVariable("id") int id)
    {
        String msg = String.format("Fetching TypeMonument with id {%s}", id);
        log.info(msg);
        TypeMonument current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("TypeMonument with id {%s} not found.", id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // -------------------Create a TypeMonument-------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/typeMonument/", method = RequestMethod.POST)
    public ResponseEntity<?> createTypeMonument(@RequestBody TypeMonument target, UriComponentsBuilder ucBuilder)
    {

        String msg = String.format("Creating TypeMonument : {%s}", target);
        log.info(msg);
        TypeMonument current = repo.findOne(target.getId());
        if (current != null)
        {
            msg = String.format("Unable to create. A TypeMonument with id {%s} already exist", target.getId());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.CONFLICT);
        }
        repo.save(target);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/typeMonument/{id}").buildAndExpand(target.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a TypeMonument ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/typeMonument/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTypeMonument(@PathVariable("id") int id, @RequestBody TypeMonument target)
    {
        String msg = String.format("Updating TypeMonument with id {%s}", id);
        log.info(msg);
        TypeMonument current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to update. TypeMonument with id {%s} not found.", id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        current.setId(target.getId());
        current.setLibelle(target.getLibelle());
        repo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // ------------------- Delete a TypeMonument-----------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/typeMonument/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTypeMonument(@PathVariable("id") int id)
    {
        String msg = String.format("Fetching & Deleting TypeMonument with id {%s}", id);
        log.info(msg);
        TypeMonument current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to delete. TypeMonument with id {%s} not found.", id);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        repo.delete(id);
        return new ResponseEntity<TypeMonument>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All TypeMonument-----------------------------
    @CrossOrigin(origins = "http://localhost:4200")

    @RequestMapping(value = "/typeMonument/", method = RequestMethod.DELETE)
    public ResponseEntity<TypeMonument> deleteAllTypeMonument()
    {
        log.info("Deleting All TypeMonument");
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}