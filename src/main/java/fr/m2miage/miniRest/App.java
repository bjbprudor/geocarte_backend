package fr.m2miage.miniRest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"fr.m2miage.miniRest"})
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }
}