package fr.m2miage.miniRest.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class VarianteCarteId implements Serializable
{

    @Column(nullable = false)
    private Integer id;

    @ManyToOne(optional = false)
    private CartePostale cartePostale;

    //region Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CartePostale getCartePostale() {
        return cartePostale;
    }

    public void setCartePostale(CartePostale cartePostale) {
        this.cartePostale = cartePostale;
    }

    //endregion

    public VarianteCarteId() {
    }

    public VarianteCarteId(Integer id, CartePostale cartePostale) {
        this.id = id;
        this.cartePostale = cartePostale;
    }

}