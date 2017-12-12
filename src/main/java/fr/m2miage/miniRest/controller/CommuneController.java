package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.model.Commune;
import fr.m2miage.miniRest.repository.CommuneRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommuneController {
    public static final Logger log = Logger.getLogger(CommuneController.class);

    @Autowired
    private CommuneRepository repo;

    // -------------------Recupere tous les Communes---------------------------------------------

    @RequestMapping(value = "/commune/", method = RequestMethod.GET)
    public ResponseEntity<List<Commune>> listAllCommunes()
    {
        List<Commune> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
