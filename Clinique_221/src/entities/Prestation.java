/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class Prestation {
    private int id;
    private String libelle;
    private String statut;
    private String resultat;
    private Date date;
    
    private int patient;

    public Prestation() {
    }

    public Prestation(int id, String statut, String resultat, Date date, int patient, String libelle) {
        this.libelle = libelle;
        this.id = id;
        this.statut = statut;
        this.resultat = resultat;
        this.date = date;
        this.patient = patient;
    }

    public Prestation(String statut, String resultat, Date date, int patient, String libelle) {
        this.libelle = libelle;
        this.statut = statut;
        this.resultat = resultat;
        this.date = date;
        this.patient = patient;
    }
    
    public Prestation(String statut, Date date, int patient, String libelle) {
        this.libelle = libelle;
        this.statut = statut;
        this.date = date;
        this.patient = patient;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
        
}
