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
public class Patient extends User {
    private String nci;
    private String antecedents;
    private final String ROLE="ROLE_PATIENT";

    public Patient() {
        //super();
        this.role=ROLE;
    }
    
    // Update Patient
    public Patient(int id, String nomComplet, String login, String password, String nci, String antecedents) {
        super(id, nomComplet, login, password);
        this.nci = nci;
        this.antecedents = antecedents;
    }
    
    // Insert Patient
    public Patient(String nomComplet, String login, String password, String nci, String antecedents) {
        super(nomComplet, login, password);
        this.nci = nci;
        this.antecedents = antecedents;
        this.role=ROLE;
    }

    public String getNci() {
        return nci;
    }

    public void setNci(String nci) {
        this.nci = nci;
    }

    public String getAntecedents() {
        return antecedents;
    }

    public void setAntecedentsMedic(String antecedents) {
        this.antecedents = antecedents;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    @Override
    public String toString() {
        return  nomComplet ;
    }
    
}
