package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.repository.EditeurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("editeurService")
public class EditeurService
{

    @Autowired
    private EditeurRepository repo;

}
