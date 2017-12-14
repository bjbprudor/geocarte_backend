package fr.m2miage.miniRest.decorater;

import fr.m2miage.miniRest.model.VarianteCarte;

public class VarianteCarteDeco {
    VarianteCarte varianteCarte;
    String base64Photo;

    public VarianteCarteDeco() {
    }

    public VarianteCarte getVarianteCarte() {
        return varianteCarte;
    }

    public void setVarianteCarte(VarianteCarte varianteCarte) {
        this.varianteCarte = varianteCarte;
    }

    public String getBase64Photo() {
        return base64Photo;
    }

    public void setBase64Photo(String base64Photo) {
        this.base64Photo = base64Photo;
    }
}
