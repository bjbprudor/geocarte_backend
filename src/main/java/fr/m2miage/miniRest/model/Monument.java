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

	@Column(nullable = false)
	private String nom;

	@Column(nullable = false)
	private Integer anneeConstruction;

	@Column
	private String divers;

	@Column(nullable = false)
    private Float longitude;

	@Column(nullable = false)
    private Float latitude;

	@ManyToOne
    private Commune commune;

	@ManyToOne
    private TypeMonument type;

    @JsonIgnore
	@ManyToMany
    @JoinTable(
            name="monumentCarte",
            joinColumns=@JoinColumn(name="monument", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="cartePostale", referencedColumnName="id"))
    private List<CartePostale> cartePostales;

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

    public Integer getAnneeConstruction() {
        return anneeConstruction;
    }

    public void setAnneeConstruction(Integer anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
    }

    public String getDivers() {
        return divers;
    }

    public void setDivers(String divers) {
        this.divers = divers;
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

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public List<CartePostale> getCartePostales() {
        return cartePostales;
    }

    public void setCartePostales(List<CartePostale> cartePostales) {
        this.cartePostales = cartePostales;
    }

    public TypeMonument getType() {
        return type;
    }

    public void setType(TypeMonument type) {
        this.type = type;
    }

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