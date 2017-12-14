package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.repository.CommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("communeService")
public class CommuneService
{

    @Autowired
    private CommuneRepository repo;


}
