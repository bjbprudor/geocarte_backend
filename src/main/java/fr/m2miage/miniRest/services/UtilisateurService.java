package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.model.Utilisateur;
import fr.m2miage.miniRest.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("utilsateurService")
public class UtilisateurService
{
    @Autowired
    private UtilisateurRepository repo;

    public Utilisateur getUserByEmail(String email)
    {
        return repo.getUtilisateurByEmail(email);
    }

    public Utilisateur getUserByLoginAndPwd(String login, String password){
        return repo.getUserByLoginAndPwd(login, password);
    }

}
