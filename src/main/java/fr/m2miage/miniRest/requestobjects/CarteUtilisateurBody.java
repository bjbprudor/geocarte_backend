package fr.m2miage.miniRest.requestobjects;

public class CarteUtilisateurBody
{

    private Integer idVariante;
    private Integer idCarte;
    private Integer idUtilisateur;
    private Integer nombreExemplaire;

    public Integer getIdVariante() {
        return idVariante;
    }

    public void setIdVariante(Integer idVariante) {
        this.idVariante = idVariante;
    }

    public Integer getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(Integer idCarte) {
        this.idCarte = idCarte;
    }

    public Integer getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Integer idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Integer getNombreExemplaire() {
        return nombreExemplaire;
    }

    public void setNombreExemplaire(Integer nombreExemplaire) {
        this.nombreExemplaire = nombreExemplaire;
    }

    public CarteUtilisateurBody() {
    }

    public CarteUtilisateurBody(Integer idVariante, Integer idCarte, Integer idUtilisateur, Integer nombreExemplaire) {
        this.idVariante = idVariante;
        this.idCarte = idCarte;
        this.idUtilisateur = idUtilisateur;
        this.nombreExemplaire = nombreExemplaire;
    }
}
