package fr.m2miage.miniRest.services;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;

public class ImageConverter {
    public static void converter(String photoLocation) throws IOException {
        File file = new File(photoLocation);

        try {
            // Reading a Image file from file system
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            // Converting Image byte array into Base64 String
            String imageDataString = encodeImage(imageData);

            //Ecrit l'encodage dans un fichier text
            try(PrintWriter out = new PrintWriter( "/home/stage01/Images/test_1.txt" )){
                out.println( imageDataString );
            }

            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = decodeImage(imageDataString);

            // Write a image byte array into file system
            FileOutputStream imageOutFile = new FileOutputStream("/home/stage01/Images/test_1.png");

            imageOutFile.write(imageByteArray);

            imageInFile.close();
            imageOutFile.close();

            System.out.println("Image Successfully Manipulated!");
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }

    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }
}
