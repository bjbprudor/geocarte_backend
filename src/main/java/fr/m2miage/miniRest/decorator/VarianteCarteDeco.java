package fr.m2miage.miniRest.decorator;

import fr.m2miage.miniRest.model.CarteUtilisateur;
import fr.m2miage.miniRest.model.Utilisateur;
import fr.m2miage.miniRest.model.VarianteCarte;

import java.util.ArrayList;
import java.util.List;

public class VarianteCarteDeco
{

    private VarianteCarte varianteCarte;
    private String base64Photo;
    private Boolean isOwned;
    private Integer carteId;

    public VarianteCarte getVarianteCarte() {
        return varianteCarte;
    }

    public void setVarianteCarte(VarianteCarte varianteCarte)
    {
        this.varianteCarte = varianteCarte;
        this.base64Photo = "";
        this.isOwned = false;
    }

    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }

    public VarianteCarteDeco()
    {
    }

    public Boolean getOwned() {
        return isOwned;
    }

    public void setOwned(Boolean owned) {
        isOwned = owned;
    }

    public VarianteCarteDeco(VarianteCarte varianteCarte)
    {
        this.varianteCarte = varianteCarte;
        this.base64Photo = "";
        this.isOwned = false;
        this.carteId = 0;
    }

    public Integer getCarteId() {
        return carteId;
    }

    public void setCarteId(Integer carteId) {
        this.carteId = carteId;
    }
}
