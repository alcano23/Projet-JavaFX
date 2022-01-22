/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Consultation;
import entities.Medecin;
import entities.Patient;
import entities.Prestation;
import entities.Rdv;
import entities.TypeMedecin;
import entities.TypePrestation;
import entities.User;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface IService {
    /*
    Ajout,modification,suppression boolean
        true ajout correct
        false Erreur
    */
//RP    
    /*Gerer classe
    public int addPrestation(Prestation prestation);
    public boolean updatePrestation(Prestation prestation);
    public boolean deletePrestation(int id);
    public Prestation searchOnePrestation(int id);
    
      
    /*Affecter Proffesseur 
    public ProfesseurDTO searchProfeseurByNci(String nci, String annee);
    public int addAffectation(ProfesseurDTO prof,String annee);
    */
   
     
    /*Lister prof
    public List<Medecin> showAllMedecin();
    public List<Consultation> searchConsultationByMedecin(String nci,String annee);
 
    
    //AC
       
    /*Affecter etu 
    public Etudiant searchEtudiantByMatricule(String matricule);
    public int addInscription(Etudiant etu,Classe classe,String annee);
    public Etudiant searchInscriptionByEtudiantAndYear(int id,String matricule);
     
    /*Lister les inscrits
    public List<EtudiantDTO> searchInscription(String annee);
    public List<EtudiantDTO> searchInscription(String annee,Classe classe);
    */
      
      
/*Se connecter */
    public User login(String login,String password);
    
/*Creer compte */
    public int addUser(User u);
    
    public int addPatient(Patient pat);
 
/*Demander rdv */
    public List<TypePrestation> searchAllTypePrestation();
    public List<TypeMedecin> searchAllTypeMedecin();
    public int addRdv(Rdv rdv);
    public List<Rdv> searchRdv(int id);
    
/*Valider rdv */
    public List<Rdv> showAllRdv();
    public List<Medecin> searchAllMedecinType(String specialite);
    public boolean updateRdv(Rdv rdv);
    public int addConsultation(Consultation consultation);
    public int addPrestation(Prestation prestation);
    public int countConsultation(int idMedecin, Date date);
    public int countPrestation(Date date);
    
/*Medecin */    
    public List<Consultation> showAllConsultation();
    public List<Consultation> searchConsultation(int id);
    public boolean updateConsultation(Consultation consultation);
    public List<Consultation> searchConsultationByDate(int idMedecin, Date date);
    public List<Consultation> searchConsultationOfToday(int idMedecin);
    public User findUserById(int id);
    public Patient findPatientById(int id);
    public List<Consultation> searchConsultationByPatient(int id);
    public List<Prestation> searchPrestationByPatient(int id);
 
/*RP*/
    public List<Prestation> searchPrestation();
    public List<Prestation> searchPrestationByDate(Date date);
    public boolean updatePrestation(Prestation prestation);
    public List<Prestation> searchPrestationPassed();
    public List<Patient> searchAllPatients();
}
