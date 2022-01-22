/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Prestation;
import java.sql.Date;
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
public class PrestationDao implements IDao<Prestation>{
    
    private DataBase dataBase = new DataBase();
    private final String SQL_INSERT = "INSERT INTO `prestation` "
            + " ( `libelle`, `date_prestation`, `statut`, `resultat`, `patient_id`) "
            + " VALUES ( ?, ?, ?, ?, ? )";
    private final String SQL_SELECT_CONSULTATION_DONE = 
            "SELECT * FROM prestation WHERE date_prestation <= CURDATE() "
            +" AND statut= 'A FAIRE' ";
    private final String SQL_UPDATE="UPDATE `prestation` SET `statut`=?, resultat = ? WHERE `id_prestation`= ?";
    private final String SQL_SELECT_PRESTATION_BY_DATE = 
            "SELECT * FROM prestation WHERE date_prestation LIKE ? "
            +" AND statut= 'A FAIRE' ";
    private final String SQL_COUNT_PRESTATION = "SELECT COUNT(*) FROM prestation p "
            +" WHERE p.date_prestation LIKE ?";
    private final String SQL_ALL=" SELECT * FROM prestation "
            +" WHERE statut= 'A FAIRE' ";
    private final String SQL_ALL_BY_PATIENT_ID=" SELECT * FROM prestation "
            +" WHERE patient_id = ? "
            +" AND statut= 'TERMINEE' ";

    @Override
    public int insert(Prestation prestation) {
        int id = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setString(1, prestation.getLibelle() );
            dataBase.getPs().setDate(2, prestation.getDate() );
            dataBase.getPs().setString(3, prestation.getStatut() );
            dataBase.getPs().setString(4, prestation.getResultat() );
            dataBase.getPs().setInt(5, prestation.getPatient() );
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
    public int update(Prestation prestation) {
        int nbrLigne=0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_UPDATE);
        try {
            dataBase.getPs().setString(1, prestation.getStatut());
            dataBase.getPs().setString(2, prestation.getResultat());
            dataBase.getPs().setInt(3, prestation.getId());

            
            nbrLigne=dataBase.executeUpdate(SQL_UPDATE);         
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataBase.closeConnexion();
        return nbrLigne;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Prestation> findAll() {
        List<Prestation> prestations = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_ALL);
        try {
            ResultSet rs = dataBase.executeSelect(SQL_ALL);
            
            while(rs.next())
            {
                Prestation prestation = new Prestation();
                prestation.setId(rs.getInt("id_prestation"));
                prestation.setLibelle(rs.getString("libelle"));
                prestation.setDate(rs.getDate("date_prestation"));
                prestation.setStatut(rs.getString("statut"));
                prestation.setResultat(rs.getString("resultat"));
                prestation.setPatient(rs.getInt("patient_id"));
                //
                

                prestations.add(prestation);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return prestations;
    }

    @Override
    public Prestation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int count(Date date){
        int count = 0;
        try {  
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_COUNT_PRESTATION);
            dataBase.getPs().setDate(1, date);
            ResultSet rs = dataBase.executeSelect(SQL_COUNT_PRESTATION);
            if(rs.next())
            {
                count = rs.getInt("COUNT(*)");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
          dataBase.closeConnexion();  
        }
        return count;
    }
    
    public List<Prestation> findAll(int id) {
        List<Prestation> prestations = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_ALL);
        try {
            dataBase.getPs().setInt(1, id);
            ResultSet rs = dataBase.executeSelect(SQL_ALL);
            
            while(rs.next())
            {
                Prestation prestation = new Prestation();
                prestation.setId(rs.getInt("id_prestation"));
                prestation.setLibelle(rs.getString("libelle"));
                prestation.setDate(rs.getDate("date_prestation"));
                prestation.setStatut(rs.getString("statut"));
                prestation.setResultat(rs.getString("resultat"));
                prestation.setPatient(rs.getInt("patient_id"));
                //
                

                prestations.add(prestation);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return prestations;
    }
    
    public List<Prestation> findByDate(Date date){
        List<Prestation> prestations = new ArrayList();
        /*1 ouvrir la connexion*/
        dataBase.openConnexion();
        
        /*2 executer la requete */
        dataBase.initPrepareStatement(SQL_SELECT_PRESTATION_BY_DATE);
        try {
            /*3 injection des param */
            dataBase.getPs().setDate(1, date);
           ResultSet rs = dataBase.executeSelect(SQL_SELECT_PRESTATION_BY_DATE);
           while(rs.next())
           {
             Prestation presta = new Prestation();
             presta.setId(rs.getInt("id_prestation"));
             presta.setLibelle(rs.getString("libelle"));
            presta.setDate(rs.getDate("date_prestation"));
            presta.setResultat(rs.getString("resultat"));
            presta.setStatut(rs.getString("statut"));
            presta.setPatient(rs.getInt("patient_id"));
            
            prestations.add(presta);
           }
        } catch (SQLException ex) {
            Logger.getLogger(PrestationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* fermer la connexion*/
        dataBase.closeConnexion();
       
        return  prestations;  
    }
    
    public List<Prestation> findAllPassed() {
        List<Prestation> prestations = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_SELECT_CONSULTATION_DONE);
        try {
            ResultSet rs = dataBase.executeSelect(SQL_SELECT_CONSULTATION_DONE);
            
            while(rs.next())
            {
                Prestation prestation = new Prestation();
                prestation.setId(rs.getInt("id_prestation"));
                prestation.setLibelle(rs.getString("libelle"));
                prestation.setDate(rs.getDate("date_prestation"));
                prestation.setStatut(rs.getString("statut"));
                prestation.setResultat(rs.getString("resultat"));
                prestation.setPatient(rs.getInt("patient_id"));
                //
                

                prestations.add(prestation);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return prestations;
    }
    
    public List<Prestation> findAllByPatient(int id) {
        List<Prestation> prestations = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_ALL_BY_PATIENT_ID);
        try {
            dataBase.getPs().setInt(1, id);
            ResultSet rs = dataBase.executeSelect(SQL_ALL_BY_PATIENT_ID);
            
            while(rs.next())
            {
                Prestation prestation = new Prestation();
                prestation.setId(rs.getInt("id_prestation"));
                prestation.setLibelle(rs.getString("libelle"));
                prestation.setDate(rs.getDate("date_prestation"));
                prestation.setStatut(rs.getString("statut"));
                prestation.setResultat(rs.getString("resultat"));
                prestation.setPatient(rs.getInt("patient_id"));
                //
                

                prestations.add(prestation);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return prestations;
    }
}
