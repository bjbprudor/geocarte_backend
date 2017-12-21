package fr.m2miage.miniRest.services;

import fr.m2miage.miniRest.model.Token;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service("emailService")
public class EmailService
{

    public static final Logger log = Logger.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender sender;


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

    private boolean sendActivationEmail(Token token)
    {
        boolean res = true;
        res = sendMail("","","");
        return res;
    }

    private void sendResetPasswordEmail(Token token)
    {

    }

}
