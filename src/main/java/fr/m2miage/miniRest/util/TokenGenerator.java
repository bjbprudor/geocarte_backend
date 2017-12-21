package fr.m2miage.miniRest.util;

import java.security.SecureRandom;

public class TokenGenerator
{

    public static String generateToken()
    {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        String token = bytes.toString();
        return token;
    }

}
