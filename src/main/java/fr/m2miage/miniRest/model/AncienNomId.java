package fr.m2miage.miniRest.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class AncienNomId implements Serializable
{

    @Column(nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    private Commune commune;

    //region Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    //endregion

    public AncienNomId() {
    }

    public AncienNomId(Integer id, Commune commune) {
        this.id = id;
        this.commune = commune;
    }

}