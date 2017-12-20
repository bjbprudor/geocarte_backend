package fr.m2miage.miniRest.controller;

import fr.m2miage.miniRest.model.*;
import fr.m2miage.miniRest.repository.CartePostaleRepository;
import fr.m2miage.miniRest.repository.CommuneRepository;
import fr.m2miage.miniRest.repository.EditeurRepository;
import fr.m2miage.miniRest.repository.VarianteCarteRepository;
import fr.m2miage.miniRest.services.CartePostaleService;
import fr.m2miage.miniRest.util.CustomErrorType;
import fr.m2miage.miniRest.util.ImageConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Random;

@RestController
public class CartePostaleController
{

    public static final Logger log = Logger.getLogger(CartePostaleController.class);

    @Autowired
    private CartePostaleService cartePostaleService;

    @Autowired
    private CartePostaleRepository repo;

    @Autowired
    private EditeurRepository editeurRepository;

    @Autowired
    private CommuneRepository communeRepository;

    @Autowired
    private VarianteCarteRepository varianteCarteRepository;

    private static String photoDestination = "src/img/destination/";

    // -------------------Recupere tous les CartePostales---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
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

    // -------------------Recupere tous les CartePostales en fonction du type de monument ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/cartePostale/",params = "monumentType",  method = RequestMethod.GET)
    public ResponseEntity<List<CartePostale>> getCpByMonumentType(@RequestParam(value = "monumentType") String monumentType)
    {
        List<CartePostale> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere tous les CartePostales en fonction du nom de commune ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/cartePostale/", params = "communeName", method = RequestMethod.GET)
    public ResponseEntity<List<CartePostale>> getCpByCommuneName(@RequestParam(value = "communeName") String communeName)
    {
        List<CartePostale> list = repo.findAll();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere tous les CartePostales en fonction du nom d'un éditeur ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/cartePostale/", params = "editeurName", method = RequestMethod.GET)
    public ResponseEntity<List<CartePostale>> getCpByEditeurName(@RequestParam(value = "editeurName") String editeurName)
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
    @CrossOrigin(origins = "http://localhost:4200")
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

    // -------------------Recupere tous les noms de communes ayant des cartes commence par le paramètre ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/communes/", method= RequestMethod.GET)
    public ResponseEntity<List<String>> getAllVcByUsername()
    {
        List<String> list = cartePostaleService.getCommunes();
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Recupere tous les noms de communes ayant des cartes commence par le paramètre ---------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/communes/", params = "nom", method= RequestMethod.GET)
    public ResponseEntity<List<String>> getAllVcByUsername(@RequestParam(value = "nom") String nom)
    {
        List<String> list = cartePostaleService.getCommunesBy(nom);
        if (list.isEmpty())
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // -------------------Create a CartePostale-------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/cartePostale/", method = RequestMethod.POST)
    public ResponseEntity<?> createCartePostale(@RequestBody CartePostalePost target)
    {
        String msg = String.format("Creating CartePostale : {%s}", target);
        log.info(msg);

        Editeur editeur = editeurRepository.findOne(target.getEditeurId());
        Commune commune = communeRepository.findOne(target.getInsee());
        CartePostale cp = new CartePostale();
        cp.setId(0);
        cp.setLatitude(target.getLatitude());
        cp.setLongitude(target.getLongitude());
        cp.setCommune(commune);
        cp.setEditeur(editeur);
        cp.setCodeEditeur(target.getCodeEditeur());
        CartePostale newCp = repo.save(cp);

        VarianteCarteId varId = new VarianteCarteId();
        varId.setId(1);
        varId.setCartePostale(newCp);
        VarianteCarte var = new VarianteCarte();
        var.setId(varId);
        var.setLegende(target.getLegende());

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        String photoName = editeur.getCode()+randomInt+".jpg";
        var.setFace(photoDestination+photoName);

        ImageConverter.base64ToImage(photoDestination, photoName,target.getBase64());

        varianteCarteRepository.save(var);
        return new ResponseEntity<>(newCp, HttpStatus.CREATED);
    }

    // ------------------- Update a CartePostale ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
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

    // ------------------- Update coordonnees de a CartePostale ------------------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/coordonneesCarte/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCoordonneesCartePostale(@PathVariable("id") int carteId,  @RequestBody CartePostale target)
    {
        String msg = String.format("Updating CartePostale with id {%s}",carteId);
        log.info(msg);
        CartePostale current = repo.findOne(carteId);
        if (current == null)
        {
            msg = String.format("Unable to update coordonnees. CartePostale with id {%s} not found.",carteId);
            log.error(msg);
            return new ResponseEntity(new CustomErrorType(msg),HttpStatus.NOT_FOUND);
        }
        if(target.getLatitude()!=null && target.getLongitude()!=null){
            current.setLatitude(target.getLatitude());
            current.setLongitude(target.getLongitude());
            repo.save(current);
        }
        return new ResponseEntity<>(current, HttpStatus.OK);
    }

    // ------------------- Delete a CartePostale-----------------------------------------
    @CrossOrigin(origins = "http://localhost:4200")
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
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/cartePostale/", method = RequestMethod.DELETE)
    public ResponseEntity<CartePostale> deleteAllCartePostale()
    {
        log.info("Deleting All CartePostale");
        repo.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}