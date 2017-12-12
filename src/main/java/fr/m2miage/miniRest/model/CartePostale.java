package fr.m2miage.miniRest.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cartePostale")
public class CartePostale implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
    private long codeEditeur;

	@ManyToOne(optional = false)
    private Editeur editeur;

    @ManyToMany(mappedBy = "cartePostales")
    private List<Monument> monuments;

	@ManyToOne
    private Commune commune;

	@OneToMany(mappedBy = "id.cartePostale")
    private List<VarianteCarte> variantes;

    public int getId() {
        return id;
    }

    public long getCodeEditeur() {
        return codeEditeur;
    }

    public void setCodeEditeur(long codeEditeur) {
        this.codeEditeur = codeEditeur;
    }

    public Editeur getEditeur() {
        return editeur;
    }

    public void setEditeur(Editeur editeur) {
        this.editeur = editeur;
    }

    public List<Monument> getMonuments() {
        return monuments;
    }

    public void setMonuments(List<Monument> monuments) {
        this.monuments = monuments;
    }

    public Commune getCommune() {
        return commune;
    }

    public void setCommune(Commune commune) {
        this.commune = commune;
    }

    public CartePostale() {
    }

    public CartePostale(long codeEditeur, Editeur editeur) {
        this.codeEditeur = codeEditeur;
        this.editeur = editeur;
    }

}