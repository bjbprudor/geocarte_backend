package fr.m2miage.miniRest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false)
	private String nom;

	@Column(nullable = false)
    private String email;

	@Column(nullable = false)
    private String motdepasse;

    @JsonIgnore
	@OneToMany(mappedBy = "id.utilisateur")
    private List<CarteUtilisateur> carteUtilisateurs;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public List<CarteUtilisateur> getCarteUtilisateurs() {
        return carteUtilisateurs;
    }

    public void setCarteUtilisateurs(List<CarteUtilisateur> carteUtilisateurs) {
        this.carteUtilisateurs = carteUtilisateurs;
    }

    public Utilisateur() {
    }

    public Utilisateur(String nom, String email, String motdepasse) {
        this.nom = nom;
        this.email = email;
        this.motdepasse = motdepasse;
    }

}