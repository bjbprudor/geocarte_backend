package fr.m2miage.miniRest.util;

import com.google.common.base.Charsets;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class CipherUtil
{

    public static final org.apache.log4j.Logger log = Logger.getLogger(CipherUtil.class);

    public static final String CIPHER_ALGORITHM = "SHA1";

    public static String hash(String input)
    {
        try
        {
            MessageDigest mDigest = MessageDigest.getInstance(CIPHER_ALGORITHM);
            byte[] result = mDigest.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++)
            {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        }
        catch (NoSuchAlgorithmException ex)
        {
            log.error(ex.getMessage());
            return null;
        }
    }

}
