package fr.m2miage.miniRest.model;

import javax.persistence.*;

@Entity
@Table(name = "token")
public class Token
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private TypeToken type;

    @ManyToOne
    private Utilisateur utilisateur;

    @Column(nullable = false)
    private String token;

    //region Getters et Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeToken getType() {
        return type;
    }

    public void setType(TypeToken type) {
        this.type = type;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    //endregion

    public Token() {

    }

    public Token(TypeToken type, Utilisateur utilisateur, String token) {
        this.type = type;
        this.utilisateur = utilisateur;
        this.token = token;
    }
}
