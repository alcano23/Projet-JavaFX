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
    private String matricule;
    private String antecedents;
    private final String ROLE="ROLE_PATIENT";

    public Patient() {
        //super();
        this.role=ROLE;
    }
    
    // Update Patient
    public Patient(int id, String matricule, String antecedents, String nomComplet) {
        super(id, nomComplet);
        this.matricule = matricule;
        this.antecedents = antecedents;
    }
    
    // Insert Patient
    public Patient(String matricule, String antecedents, String nomComplet) {
        super(nomComplet);
        this.matricule = matricule;
        this.antecedents = antecedents;
        this.role=ROLE;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
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
    
}
