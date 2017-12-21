package fr.m2miage.miniRest.model;

import javax.persistence.*;

@Entity
@Table(name = "typetoken")
public class TypeToken
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nom;

    //region Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    //endregion

    public TypeToken() {

    }

    public TypeToken(String nom) {
        this.nom = nom;
    }

}
