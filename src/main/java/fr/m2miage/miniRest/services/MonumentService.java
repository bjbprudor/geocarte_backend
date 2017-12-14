package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.repository.MonumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("monumentService")
public class MonumentService
{

    @Autowired
    private MonumentRepository repo;

}
