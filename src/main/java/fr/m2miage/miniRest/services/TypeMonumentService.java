package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.repository.TypeMonumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("typeMonumentService")
public class TypeMonumentService
{

    @Autowired
    private TypeMonumentRepository repo;


}
