package fr.m2miage.miniRest.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class AncienNomId implements Serializable
{

    @Column(name = "id")
    private int id;

    @ManyToOne(optional = false)
    private Commune commune;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public AncienNomId() {
    }

    public AncienNomId(int id, Commune commune)
    {
        this.id = id;
        this.commune = commune;
    }

}