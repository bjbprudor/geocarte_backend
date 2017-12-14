package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("departementService")
public class DepartementService
{

    @Autowired
    private DepartementRepository repo;


}
