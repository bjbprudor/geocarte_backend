package fr.m2miage.miniRest.decorator;

import fr.m2miage.miniRest.model.Utilisateur;
import fr.m2miage.miniRest.util.CipherUtil;

public class UtilisateurDeco
{

    private Utilisateur utilisateur;

    private String unencryptedPassword;

    /**
     * @return renvoie l'utilisateur avec son mot de passe crypté
     */
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    /**
     * attention n'encrypte pas le mot de passe
     * @param utilisateur un utilisateur
     */
    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     * @return le mot de passe decrytpé
     */
    public String getUnencryptedPassword() {
        if(unencryptedPassword == null || unencryptedPassword == "")
        {
            if(utilisateur != null)
            {
                unencryptedPassword = CipherUtil.decrypt(utilisateur.getMotdepasse());
            }
            else
            {
                // error
            }
        }
        return unencryptedPassword;
    }

    /**
     * met à jour le mot de passe utilisateur
     * @param motdepasse un mot de passe non encrypté
     */
    public void SetPassword(String motdepasse)
    {
        if(utilisateur != null) {
            unencryptedPassword = motdepasse;
            String crypted = CipherUtil.encrypt(unencryptedPassword);
            utilisateur.setMotdepasse(crypted);
        }
        else
        {
            //error
        }
    }

    /**
     * Construit un utilisateurDeco vide
     */
    public UtilisateurDeco() {
    }

    /**
     * Construit un utilisateur avec un utilisateur (n'encrypte pas le mot de passe)
     * @param utilisateur
     */
    public UtilisateurDeco(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    /**
     * Construit un utilisateur a partir des parametres suivants et encrypte le mot de passe
     * @param nom nom de l'utilisateur
     * @param mail email de l'utilisateur
     * @param motdepasse mot de passe en clair
     */
    public UtilisateurDeco(String nom, String mail, String motdepasse) {
        this.unencryptedPassword = motdepasse;
        String cryptedpass = CipherUtil.encrypt(this.unencryptedPassword);
        this.utilisateur = new Utilisateur(nom,mail,cryptedpass);
    }

}
