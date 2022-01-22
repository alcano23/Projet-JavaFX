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
public class Consultation {
    private int id;
    private String libelle;
    private int patient;
    private int medecin;
    private Date date;
    private String ordonnance;
    private String prestation;
    private String statut;

    public Consultation() {
    }

    public Consultation(int id, int patient, int medecin, String ordonnance, String prestation, Date date, String libelle, String statut) {
        this.statut = statut;
        this.libelle = libelle;
        this.id = id;
        this.patient = patient;
        this.medecin = medecin;
        this.date = date;
        this.ordonnance = ordonnance;
        this.prestation = prestation;
    }

    public Consultation(int patient, int medecin, String ordonnance, String prestation, Date date, String libelle, String statut) {
        this.statut = statut;
        this.libelle = libelle;
        this.patient = patient;
        this.medecin = medecin;
        this.date = date;
        this.ordonnance = ordonnance;
        this.prestation = prestation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public int getMedecin() {
        return medecin;
    }

    public void setMedecin(int medecin) {
        this.medecin = medecin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(String ordonnance) {
        this.ordonnance = ordonnance;
    }

    public String getPrestation() {
        return prestation;
    }

    public void setPrestation(String prestation) {
        this.prestation = prestation;
    }
    
    
    
}
