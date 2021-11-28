/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Prestation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class PrestationDao implements IDao<Prestation>{
    
    private DataBase dataBase = new DataBase();
    private final String SQL_INSERT = "INSERT INTO `prestation` "
            + " ( `date`, `statut`, `resultat`, 'patient') "
            + " VALUES ( ?, ?, ?, ? )";

    @Override
    public int insert(Prestation prestation) {
        int id = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setDate(1, prestation.getDate() );
            dataBase.getPs().setString(2, prestation.getStatut() );
            dataBase.getPs().setString(3, prestation.getResultat() );
            dataBase.getPs().setInt(4, prestation.getPatient().getId() );
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
    public int update(Prestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Prestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
