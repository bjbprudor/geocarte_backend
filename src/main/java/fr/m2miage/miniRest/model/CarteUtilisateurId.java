package fr.m2miage.miniRest.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class CarteUtilisateurId implements Serializable
{

    @ManyToOne(optional = false)
    private VarianteCarte varianteCarte;

    @ManyToOne(optional = false)
    private Utilisateur utilisateur;

    public VarianteCarte getVarianteCarte() {
        return varianteCarte;
    }

    public void setVarianteCarte(VarianteCarte varianteCarte) {
        this.varianteCarte = varianteCarte;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public CarteUtilisateurId() {
    }

    public CarteUtilisateurId(VarianteCarte varianteCarte, Utilisateur utilisateur)
    {
        this.varianteCarte = varianteCarte;
        this.utilisateur = utilisateur;
    }

}