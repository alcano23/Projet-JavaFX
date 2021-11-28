/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Rdv;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LENOVO
 */
public class RdvDao implements IDao<Rdv>{
    
    DataBase database = new DataBase();
    
    private final String SQL_INSERT = "INSERT INTO `rendezvous` "
            + " ( `status`, `typeMedecin`, `typePrestation`, `patient_id`) "
            + " VALUES ( ?, ?, ?, ? )";
    private final String SQL_UPDATE="UPDATE `rendezvous` SET `status`=? WHERE `id_rendezvous`= ?";
    private final String SQL_DELETE="Delete from rendezvous where id_rendezvous=?";
    private final String SQL_ALL=" SELECT * FROM rendezvous";
    private final String SQL_BY_ID="SELECT * FROM `rendezvous` WHERE id_rendezvous=?";

    @Override
    public int insert(Rdv rdv) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, rdv.getStatus());
            database.getPs().setString(2, rdv.getTypeMedecin() );
            database.getPs().setString(3, rdv.getTypePrestation());
            database.getPs().setInt(4, rdv.getPatient().getId() );
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return lastInsertId;
    }

    @Override
    public int update(Rdv rdv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rdv> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rdv findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
