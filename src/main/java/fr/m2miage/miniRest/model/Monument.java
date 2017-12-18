package fr.m2miage.miniRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "monument")
public class Monument implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

    @ManyToOne
    private TypeMonument type;

	@Column(nullable = false)
	private String nom;

	@Column
	private Integer anneeConstruction;

	@ManyToOne
    private Commune commune;

    @Column
    private Float longitude;

    @Column
    private Float latitude;

    @Column
    private String divers;

    @JsonIgnore
	@ManyToMany
    @JoinTable(
            name="monumentCarte",
            joinColumns=@JoinColumn(name="monument", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="cartePostale", referencedColumnName="id"))
    private List<CartePostale> cartePostales;

    //region Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeMonument getType() {
        return type;
    }

    public void setType(TypeMonument type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getAnneeConstruction() {
        return anneeConstruction;
    }

    public void setAnneeConstruction(Integer anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getDivers() {
        return divers;
    }

    public void setDivers(String divers) {
        this.divers = divers;
    }

    public List<CartePostale> getCartePostales() {
        return cartePostales;
    }

    public void setCartePostales(List<CartePostale> cartePostales) {
        this.cartePostales = cartePostales;
    }

    //endregion

    public Monument() {
    }

    public Monument(String nom, Integer anneeConstruction, String divers, Float longitude, Float latitude, TypeMonument type) {
        this.nom = nom;
        this.anneeConstruction = anneeConstruction;
        this.divers = divers;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
    }

}