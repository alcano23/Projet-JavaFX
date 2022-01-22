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

    public Medecin( int id, String nomComplet, String login, String password, String nci, String specialite) {
        super(id, nomComplet, login, password);
        this.nci = nci;
        this.specialite = specialite;
        this.role=ROLE;
    }

    public Medecin(String nomComplet, String login, String password, String nci, String specialite) {
        super(nomComplet, login, password);
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

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }   

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return  nomComplet ;
    }
    
}
