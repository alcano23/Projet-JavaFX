/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Consultation;
import entities.Typemp;
import entities.User;
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
public class ConsultationDao implements IDao<Consultation>{
    
    private DataBase dataBase = new DataBase();
    private final String SQL_SELECT_CONSULTATION_BY_DATE = 
            "SELECT * FROM consultation WHERE date_consultation LIKE ? "
            +" AND statut= 'A FAIRE' "
            +" AND medecin_id= ? ";
    private final String SQL_SELECT_CONSULTATION_OF_TODAY = 
            "SELECT * FROM consultation WHERE date_consultation = CURDATE() "
            +" AND statut= 'A FAIRE' "
            +" AND medecin_id= ? ";
    private final String SQL_INSERT = "INSERT INTO `consultation` "
            + " ( `date_consultation`, `patient_id`, `medecin_id`, `libelle`, `statut`) "
            + " VALUES ( ?, ?, ?, ?, ? )";
    private final String SQL_UPDATE="UPDATE `consultation` SET `statut`=?, ordonnance = ?, prestation_lib=? WHERE `id_consultation`= ?";
    private final String SQL_COUNT_MEDECIN = "SELECT COUNT(*) FROM consultation c "
            +" WHERE c.medecin_id = ? "
            +" AND c.date_consultation LIKE ?";
    private final String SQL_ALL_BY_ID=" SELECT * FROM consultation c, user p "
            +" WHERE c.medecin_id = ? "
            +" AND c.patient_id = p.id "
            +" AND c.statut= 'A FAIRE' ";
    private final String SQL_ALL_BY_PATIENT_ID=" SELECT * FROM consultation c "
            +" WHERE c.patient_id = ? "
            +" AND c.statut= 'TERMINEE' ";

    @Override
    public int insert(Consultation consultation) {
        int id = 0;
        try {
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_INSERT);
            dataBase.getPs().setDate(1, consultation.getDate() );
            dataBase.getPs().setInt(2, consultation.getPatient() );
            dataBase.getPs().setInt(3, consultation.getMedecin() );
            dataBase.getPs().setString(4, consultation.getLibelle());
            dataBase.getPs().setString(5, consultation.getStatut());
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
    public int update(Consultation consultation) {
        int nbrLigne=0;
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_UPDATE);
        try {
            dataBase.getPs().setString(1, consultation.getStatut());
            dataBase.getPs().setString(2, consultation.getOrdonnance());
            dataBase.getPs().setString(3, consultation.getPrestation());
            dataBase.getPs().setInt(4, consultation.getId());

            
            nbrLigne=dataBase.executeUpdate(SQL_UPDATE);         
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dataBase.closeConnexion();
        return nbrLigne;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Consultation> findAll() {
        List<Consultation> consultations = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_ALL_BY_ID);
        try {
            ResultSet rs = dataBase.executeSelect(SQL_ALL_BY_ID);
            
            while(rs.next())
            {
                Consultation consultation = new Consultation();
                consultation.setId(rs.getInt("id_consultation"));
                consultation.setLibelle(rs.getString("libelle"));
                consultation.setMedecin(rs.getInt("medecin_id"));
                consultation.setDate(rs.getDate("date_consultation"));
                consultation.setStatut(rs.getString("statut"));
                consultation.setPatient(rs.getInt("patient_id"));
                //

                consultations.add(consultation);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return consultations;
    }
    
    public List<Consultation> findAll(int id) {
        List<Consultation> consultations = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_ALL_BY_ID);
        try {
            dataBase.getPs().setInt(1, id);
            ResultSet rs = dataBase.executeSelect(SQL_ALL_BY_ID);
            
            while(rs.next())
            {
                Consultation consultation = new Consultation();
                consultation.setId(rs.getInt("id_consultation"));
                consultation.setLibelle(rs.getString("libelle"));
                consultation.setMedecin(rs.getInt("medecin_id"));
                consultation.setDate(rs.getDate("date_consultation"));
                consultation.setStatut(rs.getString("statut"));
                consultation.setPatient(rs.getInt("patient_id"));
                //
                

                consultations.add(consultation);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return consultations;
    }

    @Override
    public Consultation findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int count(int idMedecin, Date date){
        int count = 0;
        try {  
            dataBase.openConnexion();
            dataBase.initPrepareStatement(SQL_COUNT_MEDECIN);
            dataBase.getPs().setInt(1, idMedecin);
            dataBase.getPs().setDate(2, date);            
            ResultSet rs = dataBase.executeSelect(SQL_COUNT_MEDECIN);
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
    
    public List<Consultation> findByDate(int idMedecin, Date date){
        List<Consultation> consultations = new ArrayList();
        /*1 ouvrir la connexion*/
        dataBase.openConnexion();
        
        /*2 executer la requete */
        dataBase.initPrepareStatement(SQL_SELECT_CONSULTATION_BY_DATE);
        try {
            /*3 injection des param */
            dataBase.getPs().setInt(1, idMedecin);
            dataBase.getPs().setDate(2, date);
           ResultSet rs = dataBase.executeSelect(SQL_SELECT_CONSULTATION_BY_DATE);
           while(rs.next())
           {
             Consultation consult = new Consultation();
             consult.setId(rs.getInt("id_consultation"));
             consult.setLibelle(rs.getString("libelle"));
             consult.setMedecin(rs.getInt("medecin_id"));
             consult.setOrdonnance(rs.getString("ordonnance"));
            consult.setDate(rs.getDate("date_consultation"));
            consult.setPrestation(rs.getString("prestation_lib"));
            consult.setStatut(rs.getString("statut"));
            consult.setPatient(rs.getInt("patient_id"));
            
            consultations.add(consult);
           }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* fermer la connexion*/
        dataBase.closeConnexion();
       
        return  consultations;  
    }
    
    public List<Consultation> findByToday(int idMedecin){
        List<Consultation> consultations = new ArrayList();
        /*1 ouvrir la connexion*/
        dataBase.openConnexion();
        
        /*2 executer la requete */
        dataBase.initPrepareStatement(SQL_SELECT_CONSULTATION_OF_TODAY);
        try {
            /*3 injection des param */
            dataBase.getPs().setInt(1, idMedecin);
           ResultSet rs = dataBase.executeSelect(SQL_SELECT_CONSULTATION_OF_TODAY);
           while(rs.next())
           {
             Consultation consult = new Consultation();
             consult.setId(rs.getInt("id_consultation"));
             consult.setLibelle(rs.getString("libelle"));
             consult.setMedecin(rs.getInt("medecin_id"));
            consult.setDate(rs.getDate("date_consultation"));
            consult.setStatut(rs.getString("statut"));
            consult.setPatient(rs.getInt("patient_id"));
            
            consultations.add(consult);
           }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* fermer la connexion*/
        dataBase.closeConnexion();
       
        return  consultations;  
    }
    
    public List<Consultation> findAllByPatient(int id) {
        List<Consultation> consultations = new ArrayList(); 
        dataBase.openConnexion();
        dataBase.initPrepareStatement(SQL_ALL_BY_PATIENT_ID);
        try {
            dataBase.getPs().setInt(1, id);
            ResultSet rs = dataBase.executeSelect(SQL_ALL_BY_PATIENT_ID);
            
            while(rs.next())
            {
                Consultation consultation = new Consultation();
                consultation.setId(rs.getInt("id_consultation"));
                consultation.setLibelle(rs.getString("libelle"));
                consultation.setMedecin(rs.getInt("medecin_id"));
                consultation.setDate(rs.getDate("date_consultation"));
                consultation.setOrdonnance(rs.getString("ordonnance"));
                consultation.setStatut(rs.getString("statut"));
                consultation.setPatient(rs.getInt("patient_id"));
                //
                

                consultations.add(consultation);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataBase.closeConnexion();
        
        return consultations;
    }
    
}
