package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.model.Utilisateur;
import fr.m2miage.miniRest.repository.UtilisateurRepository;
import fr.m2miage.miniRest.util.CipherUtil;
import fr.m2miage.miniRest.util.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public Utilisateur update(Integer id, Utilisateur target)
    {
        Utilisateur current = repo.findOne(id);
        if (current == null)
        {
            return null;
        }
        current.setNom(target.getNom());
        current.setEmail(target.getEmail());
        String mdpChiffre = CipherUtil.encrypt(target.getMotdepasse());
        current.setMotdepasse(mdpChiffre);
        repo.save(current);
        return current;
    }

}
