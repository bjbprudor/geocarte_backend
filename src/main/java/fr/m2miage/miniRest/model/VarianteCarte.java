package fr.m2miage.miniRest.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "varianteCarte")
public class VarianteCarte implements Serializable {

    @EmbeddedId
    private VarianteCarteId id;

    @Column(nullable = false)
    private String legende;

    @Column(nullable = false)
    private String face;

    @Column(nullable = false)
    private String dos;

    @OneToMany(mappedBy = "id.varianteCarte")
    private List<CarteUtilisateur> carteUtilisateurs;

    public VarianteCarteId getId() {
        return id;
    }

    public void setId(VarianteCarteId id) {
        this.id = id;
    }

    public String getLegende() {
        return legende;
    }

    public void setLegende(String legende) {
        this.legende = legende;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public List<CarteUtilisateur> getCarteUtilisateurs() {
        return carteUtilisateurs;
    }

    public void setCarteUtilisateurs(List<CarteUtilisateur> carteUtilisateurs) {
        this.carteUtilisateurs = carteUtilisateurs;
    }

    public VarianteCarte() {
    }

    public VarianteCarte(VarianteCarteId id, String legende, String face, String dos)
    {
        this.id = id;
        this.legende = legende;
        this.face = face;
        this.dos = dos;
    }

}