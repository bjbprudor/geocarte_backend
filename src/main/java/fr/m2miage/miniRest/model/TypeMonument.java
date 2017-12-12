package fr.m2miage.miniRest.model;

public class TypeMonument
{

    private int numero;

    private String libelle;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public TypeMonument() {
    }

    public TypeMonument(String libelle) {
        this.libelle = libelle;
    }

}