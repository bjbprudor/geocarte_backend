package fr.m2miage.miniRest.util;

import com.google.common.base.Charsets;
import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@Component
public class CipherUtil
{

    public static final org.apache.log4j.Logger log = Logger.getLogger(CipherUtil.class);

    public static final String CIPHER_ALGORITHM = "AES";
    public static final String KEY_ALGORITHM = "AES";

    // exactly 16 bytes to not use JCE (Java Cryptography Extension)
    public static final byte[] SECRET_KEY = "AV14D456UB12GKEY".getBytes(Charsets.UTF_8);

    public static String decrypt(String encryptedInput) {
        try
        {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(SECRET_KEY, KEY_ALGORITHM));
            return new String(cipher.doFinal(Base64.decodeBase64(encryptedInput)), Charsets.UTF_8);

        }
        catch (Exception e)
        {
            log.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String str) {
        try
        {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(SECRET_KEY, KEY_ALGORITHM));
            return Base64.encodeBase64URLSafeString(cipher.doFinal(str.getBytes(Charsets.UTF_8)));

        }
        catch (Exception e)
        {
            log.warn(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

}
