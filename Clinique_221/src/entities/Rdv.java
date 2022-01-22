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
public class Rdv {
    private int id;
    private String statut;
    private String service;
    private Typemp typemp;
    private Date date;    
    
    private int patient;

    public Rdv() {
    }

    public Rdv(String statut) {
        this.statut = statut;
    }
    
    public Rdv(String service, Typemp typemp, Date date, int patient) {
        this.service = service;
        this.typemp = typemp;
        this.date = date;
        this.patient = patient;
    }

    public Rdv(int id, String statut, String service, Typemp typemp, Date date, int patient) {
        this.id = id;
        this.statut = statut;
        this.service = service;
        this.typemp = typemp;
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

    public Typemp getTypemp() {
        return typemp;
    }

    public void setTypemp(Typemp typemp) {
        this.typemp = typemp;
    }
    
    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
}
