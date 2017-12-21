package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.model.Token;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service("emailService")
public class EmailService
{

    @Autowired
    private Environment env;

    @Autowired
    private JavaMailSender sender;

    public static final Logger log = Logger.getLogger(EmailService.class);

    private boolean sendMail(String desti, String sujet, String texte)
    {
        boolean res = true;
        try
        {
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setTo(desti);
            helper.setSubject(sujet);
            helper.setText(texte);
            sender.send(message);
        }
        catch (Exception ex)
        {
            log.error(ex.getMessage());
            res = false;
        }
        return res;
    }

    public boolean sendActivationEmail(Token token)
    {
        boolean res = true;
        String destinaire = token.getUtilisateur().getEmail();
        String sujet = "Activation de votre compte geocarte";
        String texte = "Bienvenue dans l'application geocarte ! \n " +
                "il vous reste une derniere étape à realiser \n" +
                "veuillez suivre ce lien pour activer votre compte : \n";
        String lien =  env.getProperty("server.ip") + "/geocarte/?activation=" + token.getToken();
        res = sendMail(destinaire,sujet,texte+lien);
        return res;
    }

    public boolean sendResetPasswordEmail(Token token)
    {
        boolean res = true;
        String destinaire = token.getUtilisateur().getEmail();
        String sujet = "Reinitialisation de votre mot de passe";
        String texte = "Vous avez demander à reinitialiser votre mot de passe. \n " +
                "veuillez suivre ce lien pour recréer un mot de passe : \n";
        String lien = env.getProperty("server.ip") + "/geocarte/?newMdp=" + token.getToken();
        res = sendMail(destinaire,sujet,texte+lien);
        return res;
    }

}
