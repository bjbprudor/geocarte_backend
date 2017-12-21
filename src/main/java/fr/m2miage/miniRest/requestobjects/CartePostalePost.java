package fr.m2miage.miniRest.requestobjects;

public class CartePostalePost {
    Integer codeEditeur;
    String insee;
    Integer editeurId;
    Float latitude;
    Float longitude;
    String base64;
    String legende;

    public CartePostalePost() {
    }

    public Integer getCodeEditeur() {
        return codeEditeur;
    }

    public void setCodeEditeur(Integer codeEditeur) {
        this.codeEditeur = codeEditeur;
    }

    public String getInsee() {
        return insee;
    }

    public void setInsee(String insee) {
        this.insee = insee;
    }

    public Integer getEditeurId() {
        return editeurId;
    }

    public void setEditeurId(Integer editeurId) {
        this.editeurId = editeurId;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getLegende() {
        return legende;
    }

    public void setLegende(String legende) {
        this.legende = legende;
    }
}
