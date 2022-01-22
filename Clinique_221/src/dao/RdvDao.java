/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Rdv;
import entities.Typemp;
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
public class RdvDao implements IDao<Rdv>{
    
    DataBase database = new DataBase();
    
    private final String SQL_INSERT = "INSERT INTO `rendezvous` "
            + " ( `statut`, `service`, `typemp_id`, `date_rdv`, `patient_id`) "
            + " VALUES ( 'EN COURS', ?, ?, ?, ? )";
    private final String SQL_UPDATE="UPDATE `rendezvous` SET `statut`=? WHERE `id_rendezvous`= ?";
    private final String SQL_DELETE="Delete from rendezvous where id_rendezvous=?";
    private final String SQL_ALL=" SELECT * FROM rendezvous r, typemp t "
            +" WHERE r.typemp_id = t.id_typemp "
            +" AND r.statut= 'EN COURS' ";
    private final String SQL_BY_ID=" SELECT * FROM rendezvous r, typemp t "
            +" WHERE r.typemp_id = t.id_typemp "
            +" AND r.patient_id LIKE ?";

    @Override
    public int insert(Rdv rdv) {
        int lastInsertId=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_INSERT);
        try {
            database.getPs().setString(1, rdv.getService());
            database.getPs().setInt(2, rdv.getTypemp().getId() );
            database.getPs().setString(3, rdv.getDate().toString() );
            database.getPs().setInt(4, rdv.getPatient() );
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
        int nbrLigne=0;
        database.openConnexion();
        database.initPrepareStatement(SQL_UPDATE);
        try {
            database.getPs().setString(1, rdv.getStatut());
            database.getPs().setInt(2, rdv.getId());
            nbrLigne=database.executeUpdate(SQL_UPDATE);         
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        database.closeConnexion();
        return nbrLigne;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Rdv> findAll() {
        List<Rdv> rdvs = new ArrayList(); 
        database.openConnexion();
        database.initPrepareStatement(SQL_ALL);
        try {
            ResultSet rs = database.executeSelect(SQL_ALL);
            
            while(rs.next())
            {
                Rdv rdv = new Rdv();
                rdv.setId(rs.getInt("id_rendezvous"));
                rdv.setService(rs.getString("service"));                
                rdv.setDate(rs.getDate("date_rdv"));
                rdv.setStatut(rs.getString("statut"));
                rdv.setPatient(rs.getInt("patient_id"));
                //
                Typemp type = new Typemp();
                type.setId(rs.getInt("typemp_id"));
                type.setLibelle(rs.getString("libelle"));
                                
                rdv.setTypemp(type);
                rdvs.add(rdv);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        
        return rdvs;
    }
    
    public List<Rdv> findAll(int id) {
        List<Rdv> rdvs = new ArrayList(); 
        database.openConnexion();
        database.initPrepareStatement(SQL_BY_ID);
        try {
            database.getPs().setInt(1, id);
            ResultSet rs = database.executeSelect(SQL_BY_ID);
            
            while(rs.next())
            {
                Rdv rdv = new Rdv();
                rdv.setId(rs.getInt("id_rendezvous"));
                rdv.setService(rs.getString("service"));                
                rdv.setDate(rs.getDate("date_rdv"));
                rdv.setStatut(rs.getString("statut"));
                //
                Typemp type = new Typemp();
                type.setId(rs.getInt("typemp_id"));
                type.setLibelle(rs.getString("libelle"));
                                
                rdv.setTypemp(type);
                rdvs.add(rdv);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RdvDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        database.closeConnexion();
        
        return rdvs;
    }

    @Override
    public Rdv findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
