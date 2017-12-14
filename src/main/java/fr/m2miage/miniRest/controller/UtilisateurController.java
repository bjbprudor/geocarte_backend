package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.model.Utilisateur;
import fr.m2miage.miniRest.repository.UtilisateurRepository;
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
public class UtilisateurController
{

    public static final Logger log = Logger.getLogger(UtilisateurController.class);

    @Autowired
    private UtilisateurRepository repo;


    // -------------------Recupere tous les Utilisateur---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/utilisateur/", method = RequestMethod.GET)
    public ResponseEntity<List<Utilisateur>> listAllUtilisateurs()
    {
        List<Utilisateur> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere un utilisateur en fonction de son login et mdp ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/utilisateur/{login}",params = "pwd", method = RequestMethod.GET)
    public ResponseEntity<Utilisateur> getUserByLoginAndPwd(@PathVariable(value="login") String login,
                                                            @RequestParam(value = "pwd") String password)
    {
        Utilisateur user;
        //= repo.findAll();
        if (user.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // -------------------Recupere un Utilisateur------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/utilisateur/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUtilisateur(@PathVariable("id") int id)
    {
        String msg = String.format("Fetching Utilisateur with id {%s}", id);
        log.info(msg);
        Utilisateur current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("utilisateur with id {%s} not found.", id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // -------------------Create a Utilisateur-------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/utilisateur/", method = RequestMethod.POST)
    public ResponseEntity<?> createUtilisateur(@RequestBody Utilisateur target, UriComponentsBuilder ucBuilder)
    {
        String msg = String.format("Creating Utilisateur : {%s}", target);
        log.info(msg);
        Utilisateur current = repo.findOne(target.getId());
        if (current != null)
        {
            msg = String.format("Unable to create. A Utilisateur with id {%s} already exist", target.getId());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.CONFLICT);
        }
        repo.save(target);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/utilisateur/{id}").buildAndExpand(target.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Utilisateur ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/utilisateur/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUtilisateur(@PathVariable("id") int id, @RequestBody Utilisateur target)
    {
        String msg = String.format("Updating Utilisateur with id {%s}",id);
        log.info(msg);
        Utilisateur current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to update. Utilisateur with id {%s} not found.",id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        current.setNom(target.getNom());
        current.setEmail(target.getEmail());
        current.setMotdepasse(target.getMotdepasse());
        repo.save(current);
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // ------------------- Delete a Utilisateur-----------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/utilisateur/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUtilisateur(@PathVariable("id") int id)
    {
        String msg = String.format("Fetching & Deleting Utilisateur with id {%s}", id);
        log.info(msg);
        Utilisateur current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Unable to delete. Utilisateur with id {%s} not found.", id);
            return new ResponseEntity(new CustomErrorType(msg), HttpStatus.NOT_FOUND);
        }
        repo.delete(id);
        return new ResponseEntity<Utilisateur>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Utilisateur-----------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/utilisateur/", method = RequestMethod.DELETE)
    public ResponseEntity<Utilisateur> deleteAllUtilisateur()
    {
        log.info("Deleting All Utilisateur");
        repo.deleteAll();
        return new ResponseEntity<Utilisateur>(HttpStatus.NO_CONTENT);
    }


}