package fr.m2miage.miniRest.requestobjects;

public class VarianteCartePut {
    String communeId;
    Integer editeurId;
    String legende;

    public VarianteCartePut() {
    }

    public String getCommuneId() {
        return communeId;
    }

    public void setCommuneId(String communeId) {
        this.communeId = communeId;
    }

    public Integer getEditeurId() {
        return editeurId;
    }

    public void setEditeurId(Integer editeurId) {
        this.editeurId = editeurId;
    }

    public String getLegende() {
        return legende;
    }

    public void setLegende(String legende) {
        this.legende = legende;
    }
}
