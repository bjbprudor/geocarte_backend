package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.model.Token;
import fr.m2miage.miniRest.model.TypeToken;
import fr.m2miage.miniRest.model.Utilisateur;
import fr.m2miage.miniRest.repository.TokenRepository;
import fr.m2miage.miniRest.repository.TypeTokenRepository;
import fr.m2miage.miniRest.repository.UtilisateurRepository;
import fr.m2miage.miniRest.requestobjects.UtilisateurBody;
import fr.m2miage.miniRest.services.EmailService;
import fr.m2miage.miniRest.services.UtilisateurService;
import fr.m2miage.miniRest.util.CipherUtil;
import fr.m2miage.miniRest.util.CustomErrorType;
import fr.m2miage.miniRest.util.TokenGenerator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
public class UtilisateurController
{

    public static final Logger log = Logger.getLogger(UtilisateurController.class);

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UtilisateurRepository repo;

    @Autowired
    private TypeTokenRepository repoTypeToken;

    @Autowired
    private TokenRepository repoToken;


    // -------------------Recupere tous les Utilisateur---------------------------------------------
    /*@CrossOrigin(origins = "http://localhost:4200")
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
    }*/

    // -------------------Recupere un utilisateur en fonction de son login et mdp ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/utilisateur/", method = RequestMethod.GET)
    public ResponseEntity<Utilisateur> getUserByLoginAndPwd(@RequestParam(value = "login") String login,
                                                            @RequestParam(value = "pwd") String password) throws UnsupportedEncodingException {

        String loginDecoded = URLDecoder.decode(login, "UTF-8");
        Utilisateur user = utilisateurService.getUserByLoginAndPwd(loginDecoded, password);
        if (user == null)
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
    @RequestMapping(value = "/inscription/", method = RequestMethod.POST)
    public ResponseEntity<?> createUtilisateur(@RequestBody UtilisateurBody target)
    {
        String msg = String.format("Creating Utilisateur : {%s}", target);
        log.info(msg);
        Utilisateur current = repo.getUtilisateurByEmail(target.getEmail());
        if (current != null)
        {
            msg = String.format("Unable to create. A Utilisateur with id {%s} already exist", target.getEmail());
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.CONFLICT);
        }
        Utilisateur newUser = new Utilisateur();
        newUser.setMotdepasse(CipherUtil.hash(target.getMdp()));
        newUser.setEmail(target.getEmail());
        newUser.setNom(target.getNom());
        newUser.setActive(false);
        Utilisateur savedUser = repo.save(newUser);

        TypeToken typeToken = repoTypeToken.findOne(1);

        Token token = new Token();
        token.setId(0);
        token.setType(typeToken);
        token.setUtilisateur(savedUser);
        token.setToken(TokenGenerator.generateToken());
        repoToken.save(token);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    // ------------------- Update a Utilisateur ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/utilisateur/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUtilisateur(@PathVariable("id") Integer id, @RequestBody Utilisateur target)
    {
        String msg = String.format("Updating Utilisateur with id {%s}",id);
        log.info(msg);

        Utilisateur current = utilisateurService.update(id,target);
        if (current == null)
        {
            msg = String.format("Unable to update. Utilisateur with id {%s} not found.",id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // ------------------- Update a Utilisateur ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/changePwd/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> changementPwd(@PathVariable("id") Integer id, @RequestBody UtilisateurBody target)
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
        current.setMotdepasse(CipherUtil.hash(target.getMdp()));
        repo.save(current);

        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/askingChngPwd/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> demandeChangementPwd(@PathVariable("id") Integer id)
    {
        String msg = String.format("Updating Utilisateur with id {%s}",id);
        log.info(msg);

        Utilisateur current = repo.findOne(id);
        if (current == null)
        {
            msg = String.format("Utilisateur with id {%s} not found.",id);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        TypeToken typeToken = repoTypeToken.findOne(2);

        Token token = new Token();
        token.setType(typeToken);
        token.setId(0);
        token.setToken(TokenGenerator.generateToken());
        token.setUtilisateur(current);
        repoToken.save(token);

        emailService.sendResetPasswordEmail(token);

        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/activation/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> activate(@PathVariable("id") Integer id)
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
        current.setActive(true);
        repo.save(current);

        TypeToken typeToken = repoTypeToken.findOne(1);

        Token token = new Token();
        token.setType(typeToken);
        token.setId(0);
        token.setToken(TokenGenerator.generateToken());
        token.setUtilisateur(current);
        repoToken.save(token);

        emailService.sendActivationEmail(token);

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