package fr.m2miage.miniRest.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "monument")
public class Monument implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nom;

	@Column(nullable = false)
	private int anneeConstruction;

	@Column
	private String divers;

	@Column(nullable = false)
    private float longitude;

	@Column(nullable = false)
    private float latitude;

	@ManyToOne
    private Commune commune;

	@ManyToOne
    private TypeMonument type;

	@ManyToMany
    @JoinTable(
            name="monumentCarte",
            joinColumns=@JoinColumn(name="monument", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="cartePostale", referencedColumnName="id"))
    private List<CartePostale> cartePostales;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnneeConstruction() {
        return anneeConstruction;
    }

    public void setAnneeConstruction(int anneeConstruction) {
        this.anneeConstruction = anneeConstruction;
    }

    public String getDivers() {
        return divers;
    }

    public void setDivers(String divers) {
        this.divers = divers;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
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

    public Monument(String nom, int anneeConstruction, String divers, float longitude, float latitude, TypeMonument type) {
        this.nom = nom;
        this.anneeConstruction = anneeConstruction;
        this.divers = divers;
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
    }

}