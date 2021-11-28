/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.TypeMedecin;
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
public class TypeMedecinDao implements IDao<TypeMedecin>{
    
    DataBase database= new DataBase();
    
    /*Request sql*/
    private final String SQL_INSERT="INSERT INTO `typemedecin` (`libelle`) VALUES (?)";
    private final String SQL_UPDATE="UPDATE `typemedecin` SET `libelle`=? WHERE `id_typeMedecin`= ?";
    private final String SQL_DELETE="Delete from typemedecin where id_typeMedecin=?";
    private final String SQL_ALL=" SELECT * FROM typemedecin";
    private final String SQL_BY_ID="SELECT * FROM `typemedecin` WHERE id_typeMedecin=?";
    
    @Override
    public int insert(TypeMedecin typeMedecin) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, typeMedecin.getLibelle());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeMedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return lastInsertId;
    }

    @Override
    public int update(TypeMedecin ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TypeMedecin> findAll() {
        List<TypeMedecin> typeMedecins=new ArrayList<>();
        database.openConnexion();
        database.initPrepareStatement(SQL_ALL);
       
            ResultSet rs =database.executeSelect(SQL_ALL);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    TypeMedecin tm =new TypeMedecin(rs.getInt("id_typeMedecin"),rs.getString("libelle"));
                    typeMedecins.add(tm);
                } catch (SQLException ex) {
                    Logger.getLogger(TypeMedecinDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypeMedecinDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return typeMedecins;
    }

    @Override
    public TypeMedecin findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
