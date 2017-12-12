package fr.m2miage.miniRest.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "typeMonument")
public class TypeMonument implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String libelle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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