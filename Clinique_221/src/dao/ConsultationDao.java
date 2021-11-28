/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Consultation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class ConsultationDao implements IDao<Consultation>{
    
    private DataBase dataBase = new DataBase();
    private final String SQL_INSERT = "INSERT INTO `consultation` "
            + " ( `date`, `patient_id`, `medecin_id`) "
            + " VALUES ( ?, ?, ? )";

    @Override
    public int insert(Consultation consultation) {
        int id = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setDate(1, consultation.getDate() );
            dataBase.getPs().setInt(2, consultation.getPatient().getId() );
            dataBase.getPs().setInt(3, consultation.getMedecin().getId() );
            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs = dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);   
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            dataBase.closeConnexion();   
        }
        return id;
    }

    @Override
    public int update(Consultation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Consultation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
