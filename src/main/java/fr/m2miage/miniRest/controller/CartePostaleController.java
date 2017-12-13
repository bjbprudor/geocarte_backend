package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.model.CartePostale;
import fr.m2miage.miniRest.repository.CartePostaleRepository;
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
public class CartePostaleController
{

    public static final Logger log = Logger.getLogger(CartePostaleController.class);

    @Autowired
    private CartePostaleRepository repo;

    // -------------------Recupere tous les CartePostales---------------------------------------------

    @RequestMapping(value = "/cartePostale/", method = RequestMethod.GET)
    public ResponseEntity<List<CartePostale>> listAllCartePostale()
    {
        List<CartePostale> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere un CartePostale------------------------------------------

    @RequestMapping(value = "/cartePostale/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCartePostale(@PathVariable("id") int id)
    {
        String msg = String.format("Fetching CartePostale with id {%s}", id);
        log.info(msg);
        CartePostale current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("CartePostale with id {%s} not found.", id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // -------------------Create a CartePostale-------------------------------------------

    @RequestMapping(value = "/cartePostale/", method = RequestMethod.POST)
    public ResponseEntity<?> createCartePostale(@RequestBody CartePostale target, UriComponentsBuilder ucBuilder)
    {
        String msg = String.format("Creating CartePostale : {%s}", target);
        log.info(msg);
        CartePostale current = repo.findOne(target.getId());
        if (current != null)
        {
            msg = String.format("Unable to create. A CartePostale with id {%s} already exist", target.getId());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.CONFLICT);
        }
        repo.save(target);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/cartePostale/{id}").buildAndExpand(target.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a CartePostale ------------------------------------------------

    @RequestMapping(value = "/cartePostale/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCartePostale(@PathVariable("id") int id, @RequestBody CartePostale target)
    {
        String msg = String.format("Updating CartePostale with id {%s}",id);
        log.info(msg);
        CartePostale current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to update. CartePostale with id {%s} not found.",id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        current.setCodeEditeur(target.getCodeEditeur());
        current.setEditeur(target.getEditeur());
        current.setCommune(target.getCommune());
        repo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // ------------------- Delete a CartePostale-----------------------------------------

    @RequestMapping(value = "/cartePostale/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCartePostale(@PathVariable("id") int id)
    {
        String msg = String.format("Fetching & Deleting CartePostale with id {%s}", id);
        log.info(msg);
        CartePostale current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to delete. CartePostale with id {%s} not found.", id);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        repo.delete(id);
        return new ResponseEntity<CartePostale>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All CartePostale-----------------------------

    @RequestMapping(value = "/cartePostale/", method = RequestMethod.DELETE)
    public ResponseEntity<CartePostale> deleteAllCartePostale()
    {
        log.info("Deleting All CartePostale");
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}