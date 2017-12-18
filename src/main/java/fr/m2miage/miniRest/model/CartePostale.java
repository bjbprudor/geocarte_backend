package fr.m2miage.miniRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cartePostale")
public class CartePostale implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
    private Integer codeEditeur;

	@ManyToOne(optional = false)
    private Editeur editeur;

    @ManyToOne
    private Commune commune;

    @Column
    private Float longitude;

    @Column
    private Float latitude;

    @ManyToMany(mappedBy = "cartePostales")
    private List<Monument> monuments;

    @JsonIgnore
	@OneToMany(mappedBy = "id.cartePostale")
    private List<VarianteCarte> variantes;

    //region Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCodeEditeur() {
        return codeEditeur;
    }

    public void setCodeEditeur(Integer codeEditeur) {
        this.codeEditeur = codeEditeur;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
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

    public List<Monument> getMonuments() {
        return monuments;
    }

    public void setMonuments(List<Monument> monuments) {
        this.monuments = monuments;
    }

    public List<VarianteCarte> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<VarianteCarte> variantes) {
        this.variantes = variantes;
    }

    //endregion

    public CartePostale() {
    }

    public CartePostale(Integer codeEditeur, Editeur editeur) {
        this.codeEditeur = codeEditeur;
        this.editeur = editeur;
    }

}
