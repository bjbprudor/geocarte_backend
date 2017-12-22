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

    public static final String serverAdress = "localhost:4200/";

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
        String sujet = "Activation de votre compte Geocarte";
        String html = "<html><body>" +
                "<h3>Bienvenu dans la communauté Geocarte !<h3>" +
                "<br/>" +
                "<p>Plus qu'une étape avant que vous puissiez faire parti de la communauté</p>" +
                "<br/>" +
                "<p>Suivez ce <a href={l}>lien</a> pour activer votre compte et profiter pleinement de Geocarte</p>" +
                "</body></html>";
        String lien = serverAdress + "?activation=" + token.getToken();
        //String lien =  env.getProperty("server.ip") + "/geocarte/?activation=" + token.getToken();
        html = html.replace("{l}",lien);
        res = sendMail(destinaire,sujet,html);
        return res;
    }

    public boolean sendResetPasswordEmail(Token token)
    {
        boolean res = true;
        String destinaire = token.getUtilisateur().getEmail();
        String sujet = "Reinitialisation de votre mot de passe";
        String html = "<html><body>" +
                "<p>Vous avez demandé à réinitialiser votre mot de passe ?</p>" +
                "<br/>" +
                "<p>Suivez ce <a href={l}>lien</a> pour changer votre mot de passe</p>" +
                "<br/>" +
                "<p>Si vous n'avez pas effectué cette demande veuillez contacter l'administrateur du serveur</p>" +
                "</body></html>";
        String lien = serverAdress + "?newMdp=" + token.getToken();
        //String lien =  env.getProperty("server.ip") + "/geocarte/?newMdp=" + token.getToken();
        html = html.replace("{l}",lien);
        res = sendMail(destinaire,sujet,html);
        return res;
    }

}
