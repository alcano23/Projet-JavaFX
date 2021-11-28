/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.TypePrestation;
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
public class TypePrestationDao implements IDao<TypePrestation>{
    
    DataBase database= new DataBase();
    
    /*Request sql*/
    private final String SQL_INSERT="INSERT INTO `typeprestation` (`libelle`) VALUES (?)";
    private final String SQL_UPDATE="UPDATE `typeprestation` SET `libelle`=? WHERE `id_typePrestation`= ?";
    private final String SQL_DELETE="Delete from typeprestation where id_typePrestation=?";
    private final String SQL_ALL=" SELECT * FROM typeprestation";
    private final String SQL_BY_ID="SELECT * FROM `typeprestation` WHERE id_typePrestation=?";
    

    @Override
    public int insert(TypePrestation typePrestation) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, typePrestation.getLibelle());
            database.executeUpdate(SQL_INSERT);
            ResultSet rs=database.getPs().getGeneratedKeys();
            if(rs.next()){
            lastInsertId=rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return lastInsertId;
    }

    @Override
    public int update(TypePrestation ogj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<TypePrestation> findAll() {
        List<TypePrestation> typePrestations=new ArrayList<>();
        database.openConnexion();
        database.initPrepareStatement(SQL_ALL);
       
            ResultSet rs =database.executeSelect(SQL_ALL);
        
        try {
            while(rs.next()){
                try {
                    //Mapping relation vers objet
                    TypePrestation tp =new TypePrestation(rs.getInt("id_typePrestation"),rs.getString("libelle"));
                    typePrestations.add(tp);
                } catch (SQLException ex) {
                    Logger.getLogger(TypePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypePrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        return typePrestations;
    }

    @Override
    public TypePrestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
