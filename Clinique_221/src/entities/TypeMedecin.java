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
public class TypeMedecin extends Typemp {
    private int id;
    private String libelle;

    public TypeMedecin() {
    }

    public TypeMedecin(String libelle) {
        this.libelle = libelle;
    }

    public TypeMedecin(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getLibelle() {
        return libelle;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return  libelle ;
    }
}
