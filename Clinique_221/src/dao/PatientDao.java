/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Patient;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class PatientDao implements IDao<Patient>{
    
    private final String SQL_INSERT = "INSERT INTO `user` "
            + " ( `nom_complet`, `login`, `password`, `role`, `nci`, `antecedents`) "
            + " VALUES ( ?, ?, ?, 'ROLE_PATIENT', ?, ? )";
    private final String  SQL_SELECT = "SELECT * FROM user p, consultation c WHERE p.id = ?";
    private final String  SQL_ALL = "SELECT * FROM user WHERE role = 'ROLE_PATIENT' ";
    private DataBase dataBase = new DataBase();

    @Override
    public int insert(Patient patient) {
        int idPatient = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setString(1, patient.getNomComplet());
            dataBase.getPs().setString(2, patient.getLogin());
            dataBase.getPs().setString(3, patient.getPassword());
            dataBase.getPs().setString(4, patient.getNci());
            dataBase.getPs().setString(5, patient.getAntecedents());
            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs =dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                idPatient = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        } 
        return idPatient;
    }

    @Override
    public int update(Patient ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_ALL);
        try {
            ResultSet rs = dataBase.executeSelect(SQL_ALL);
            
            while(rs.next())
            {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setNomComplet(rs.getString("nom_complet"));
                patient.setNci(rs.getString("nci"));

                //

                patients.add(patient);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return patients;
    }

    @Override
    public Patient findById(int id) {
        Patient patient = null;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT);
        try {
            dataBase.getPs().setInt(1, id);
            ResultSet rs = dataBase.executeSelect(SQL_SELECT);
        
            if(rs.next())
            {
                    patient = new Patient(
                    rs.getInt("id"),
                    rs.getString("nom_complet"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("nci"),
                    rs.getString("antecedents") 
                    );
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patient;
    }
    
    
}
