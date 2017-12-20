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
    private List<Utilisateur> utilisateurs;

    public VarianteCarte getVarianteCarte() {
        return varianteCarte;
    }

    public void setVarianteCarte(VarianteCarte varianteCarte)
    {
        this.varianteCarte = varianteCarte;
        this.base64Photo = "";
        utilisateurs = new ArrayList<>();
        for (CarteUtilisateur cu : varianteCarte.getCarteUtilisateurs())
        {
            utilisateurs.add(cu.getId().getUtilisateur());
        }
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }

    public VarianteCarteDeco()
    {
        utilisateurs = new ArrayList<>();
    }

    public VarianteCarteDeco(VarianteCarte varianteCarte)
    {
        this.varianteCarte = varianteCarte;
        base64Photo = "";
        for (CarteUtilisateur cu : varianteCarte.getCarteUtilisateurs())
        {
            utilisateurs.add(cu.getId().getUtilisateur());
        }
    }

}
