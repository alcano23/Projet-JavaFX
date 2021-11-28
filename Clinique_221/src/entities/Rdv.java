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
public class Rdv {
    private int id;
    private String statut;
    private String typeMedecin;
    private String typePrestation;
    
    private Patient patient;

    public Rdv() {
    }

    public Rdv(String statut) {
        this.statut = statut;
    }

    public Rdv(int id, String statut, String typeMedecin, String typePrestation, Patient patient) {
        this.id = id;
        this.statut = statut;
        this.typeMedecin = typeMedecin;
        this.typePrestation = typePrestation;
        this.patient = patient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return statut;
    }

    public void setStatus(String statut) {
        this.statut = statut;
    }

    public String getTypeMedecin() {
        return typeMedecin;
    }

    public void setTypeMedecin(String typeMedecin) {
        this.typeMedecin = typeMedecin;
    }

    public String getTypePrestation() {
        return typePrestation;
    }

    public void setTypePrestation(String typePrestation) {
        this.typePrestation = typePrestation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
}
