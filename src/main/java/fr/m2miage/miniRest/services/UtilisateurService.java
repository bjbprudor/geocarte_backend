package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.model.Utilisateur;
import fr.m2miage.miniRest.repository.UtilisateurRepository;
import fr.m2miage.miniRest.util.CipherUtil;
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

    public Utilisateur getUserByLoginAndPwd(String login, String password)
    {
        String cipheredPass = CipherUtil.encrypt(password);
        return repo.getUserByLoginAndPwd(login, cipheredPass);
    }

}
