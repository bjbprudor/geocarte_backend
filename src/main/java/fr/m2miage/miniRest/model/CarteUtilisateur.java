package fr.m2miage.miniRest.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "carteUtilisateur")
public class CarteUtilisateur implements Serializable
{

    @EmbeddedId
    private CarteUtilisateurId id;

    @Column(nullable = false)
    private Integer nombreExemplaires;

    public CarteUtilisateurId getId() {
        return id;
    }

    public void setId(CarteUtilisateurId id) {
        this.id = id;
    }

    public Integer getNombreExemplaires() {
        return nombreExemplaires;
    }

    public void setNombreExemplaires(Integer nombreExemplaires) {
        this.nombreExemplaires = nombreExemplaires;
    }

    public CarteUtilisateur() {
    }

    public CarteUtilisateur(CarteUtilisateurId id, Integer nombreExemplaires) {
        this.id = id;
        this.nombreExemplaires = nombreExemplaires;
    }

}