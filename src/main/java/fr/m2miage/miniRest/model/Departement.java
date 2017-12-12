package fr.m2miage.miniRest.model;

public class Departement
{

	private String numero;

    private String nom;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Departement()
    {
    }

    public Departement(String numero, String nom)
    {
        this.numero = numero;
        this.nom = nom;
    }

}