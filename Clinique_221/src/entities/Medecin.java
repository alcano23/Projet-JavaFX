/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author LENOVO
 */
public class Medecin extends User {
    private String nci;
    private String specialite;
    private final String ROLE="ROLE_MEDECIN";

    public Medecin() {
        this.role=ROLE;
    }

    public Medecin(String nci, String specialite, int id, String nomComplet) {
        super(id, nomComplet);
        this.nci = nci;
        this.specialite = specialite;
        this.role=ROLE;
    }

    public Medecin(String nci, String specialite, String nomComplet) {
        super(nomComplet);
        this.nci = nci;
        this.specialite = specialite;
        this.role=ROLE;
    }

    public String getNci() {
        return nci;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getROLE() {
        return ROLE;
    }

    public String getRole() {
        return role;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public void setDomaine(String specialite) {
        this.specialite = specialite;
    }

   

    public void setRole(String role) {
        this.role = role;
    }
}
