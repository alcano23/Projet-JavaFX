/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Patient;
import entities.User;
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
    public List<Prestation> searchAllPrestation();
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
    public User addUser(User u);
    
    public User addPatient(Patient pat);
}
