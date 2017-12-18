package fr.m2miage.miniRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "commune")
public class Commune implements Serializable
{

	@Id
	private String insee;

	@Column
	private String article;

	@Column(nullable = false)
    private String nom;

	@Column
    private Float longitude;

	@Column
    private Float latitude;

	@ManyToOne(optional = false)
    private Departement departement;

    @ManyToOne
    private Commune fusion;

    //region accès au liste d'objets liés

    @OneToMany(mappedBy = "fusion")
    private List<Commune> ancienneCommunes;

    @JsonIgnore
    @OneToMany(mappedBy = "id.commune")
    private List<AncienNom> ancienNoms;

    @JsonIgnore
    @ManyToMany(mappedBy="communes")
    private List<Editeur> editeurs;

    @JsonIgnore
    @OneToMany(mappedBy = "commune")
    private List<Monument> monuments;

    @JsonIgnore
    @OneToMany(mappedBy = "commune")
    private List<CartePostale> cartePostales;

    public List<Commune> getAncienneCommunes() {
        return ancienneCommunes;
    }

    public void setAncienneCommunes(List<Commune> ancienneCommunes) {
        this.ancienneCommunes = ancienneCommunes;
    }

    public List<AncienNom> getAncienNoms() {
        return ancienNoms;
    }

    public void setAncienNoms(List<AncienNom> ancienNoms) {
        this.ancienNoms = ancienNoms;
    }

    public List<Editeur> getEditeurs() {
        return editeurs;
    }

    public void setEditeurs(List<Editeur> editeurs) {
        this.editeurs = editeurs;
    }

    public List<Monument> getMonuments() {
        return monuments;
    }

    public void setMonuments(List<Monument> monuments) {
        this.monuments = monuments;
    }

    public List<CartePostale> getCartePostales() {
        return cartePostales;
    }

    public void setCartePostales(List<CartePostale> cartePostales) {
        this.cartePostales = cartePostales;
    }

//endregion

    //region Getters et Setters

    public String getInsee() {
        return insee;
    }

    public void setInsee(String insee) {
        this.insee = insee;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Commune getFusion() {
        return fusion;
    }

    public void setFusion(Commune fusion) {
        this.fusion = fusion;
    }

    //endregion

    public Commune() {
    }

    public Commune(String insee, String article, String nom, float longitude, float latitude, Departement departement, Commune ancienneCommune) {
        this.insee = insee;
        this.article = article;
        this.nom = nom;
        this.longitude = longitude;
        this.latitude = latitude;
        this.departement = departement;
        this.fusion = ancienneCommune;
    }

}