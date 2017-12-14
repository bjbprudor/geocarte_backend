package fr.m2miage.miniRest.services;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.*;

public class ImageConverter {

    public static String imageToBase64(String photoLocation){
        File file = new File(photoLocation);
        String imageDataString = "";
        try {
            // Reading a Image file from file system
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            // Converting Image byte array into Base64 String
            imageDataString = encodeImage(imageData);

            imageInFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageDataString;
    }

    public static String base64ToImage(String fileLocation, String photoName, String base64ImageString){
        try {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = decodeImage(base64ImageString);

            // Write a image byte array into file system
            FileOutputStream imageOutFile = new FileOutputStream(fileLocation+"/"+photoName);

            imageOutFile.write(imageByteArray);

            imageOutFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileLocation+"/"+photoName;
    }

    public static String encodeImage(byte[] imageByteArray) {
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    public static byte[] decodeImage(String imageDataString) {
        return Base64.decodeBase64(imageDataString);
    }

    //TODO: à revoir, ne fonctionne pas correctement
    public static boolean isExistingFile(String fileLocation){
        File file = new File(fileLocation);
        return file != null;
    }

    //Code à inclure dans le main pour  tester
    /*String sourceFolder = "C:\\source";
        String destinationFolder = "C:\\destination";
        String imageName = "\\villard-0070-n1-1.jpg";

        String base64 = ImageConverter.imageToBase64(sourceFolder+imageName);
        String finalName = ImageConverter.base64ToImage(destinationFolder, imageName, base64);*/
}
