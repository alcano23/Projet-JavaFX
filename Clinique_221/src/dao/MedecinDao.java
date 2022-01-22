/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Medecin;
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
public class MedecinDao implements IDao<Medecin>{
    
    private DataBase dataBase = new DataBase();
    private final String SQL_SELECT_MEDECINS_BY_SPECIALITE =
            " SELECT * FROM user m WHERE m.specialite LIKE ? ";
    
    private final String SQL_SELECT_MED_BY_NCI = 
            " SELECT * FROM user m WHERE nci LIKE ? ";
    
    
    private final String SQL_SELECT_ALL = 
            " SELECT * FROM user m WHERE m.role LIKE 'ROLE_MEDECIN' ";
    
    private final String SQL_INSERT = "INSERT INTO `user` "
            + " ( `nom_complet`, `role`, `nci`, `specialite` ) "
            + " VALUES (?, 'ROLE_MEDECIN', ?, ? )";

    @Override
    public int insert(Medecin medecin) {
        int id = 0;
        try {  
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setString(1, medecin.getNomComplet());
            dataBase.getPs().setString(2, medecin.getNci());
            dataBase.getPs().setString(3, medecin.getSpecialite());
            dataBase.executeUpdate(SQL_INSERT);
            ResultSet rs = dataBase.getPs().getGeneratedKeys();
            if(rs.next())
            {
                id = rs.getInt(1);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          dataBase.closeConnexion();  
        }
        return id;
    }

    @Override
    public int update(Medecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Medecin> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Medecin findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Medecin> findBySpecialite(String specialite) {
        List<Medecin> medecins = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_MEDECINS_BY_SPECIALITE);
        try {
            dataBase.getPs().setString(1, specialite);
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_MEDECINS_BY_SPECIALITE);
            
            while(rs.next())
            {
                Medecin med = new Medecin();
                med.setId(rs.getInt("id"));
                med.setNomComplet(rs.getString("nom_complet"));
                med.setNci(rs.getString("nci"));
                med.setSpecialite(rs.getString("specialite"));
                
                medecins.add(med);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return medecins;
    }
    
}
