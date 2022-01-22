/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.ConsultationDao;
import dao.MedecinDao;
import dao.PatientDao;
import dao.PrestationDao;
import dao.RdvDao;
import dao.TypeMedecinDao;
import dao.TypePrestationDao;
import dao.UserDao;
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
public class Service implements IService {
    
    //Dependances avec la couche DAO
    UserDao daoUser=new UserDao();
    PatientDao daoPat=new PatientDao();
    TypeMedecinDao daoTyme= new TypeMedecinDao();
    TypePrestationDao daoTypr=new TypePrestationDao();
    RdvDao daoRdv = new RdvDao();
    MedecinDao daoMed = new MedecinDao();
    ConsultationDao daoConsult = new ConsultationDao();
    PrestationDao daoPrest = new PrestationDao();
    
    /*
    

    @Override
    public boolean deleteCLasse(int id) {
        return daoClasse.delete(id)!=0; //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public Classe searchOneCLasse(int id) {
        return daoClasse.findById(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProfesseurDTO searchProfeseurByNci(String nci, String annee) {
        ProfesseurDTO prof = daoProf.findByNci(nci) ; 
        if(prof != null ){
            List<Classe> classes = daoClasse.findClasseByProfesseur(nci, annee);
            prof.setClasses(classes);  
            
        }
        return prof;
    }

    @Override
    public int addAffectation(ProfesseurDTO profDTO,String annee) {
       //   Nouveau
       int id = 0;
       Professeur prof = null;
        if(profDTO.getId()==0){
            prof = new Professeur();
            prof.toProfesseur(profDTO);
            id=daoProf.insert(prof);
            prof.setId(id);
        }
        List<Classe> classes = profDTO.getClasses();
        for(Classe classe:classes)
        {
            Affectation affect=new Affectation(annee,classe,prof);
            daoAff.insert(affect);   
        }

        return id;
    }

    @Override
    public List<Classe> searchClasseByProffesseur(String nci, String annee) {
       return daoClasse.findClasseByProfesseur(nci, annee); //To change body of generated methods, choose Tools | Templates.
    }

    

    

    @Override
    public int addInscription(Etudiant etu, Classe classe, String annee) {
       
        if(etu.getId()==0)
        {
            int id= daoEtu.insert(etu);
            etu.setId(id);
        }
        Inscription ins = new Inscription(classe,etu,annee);
        return etu.getId() ;
    }

    @Override
    public Etudiant searchInscriptionByEtudiantAndYear(int id, String matricule) {
        return daoEtu.findByIdAndAnnee(id, matricule); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public List<EtudiantDTO> searchInscription(String annee, Classe classe) {
        return daoEtu.findAll(annee, classe); 
    }
    /*/
    @Override
    public User login(String login, String password) {
        return daoUser.findUserLoginAndPassword(login, password);
         
    }

    @Override
    public int addUser(User u) {
        return daoUser.insert(u);
      
    }

    @Override
    public int addPatient(Patient patient) {
        return daoPat.insert(patient);
    }
    
    @Override
    public List<Patient> searchAllPatients() {
        return daoPat.findAll();
    }
    
    @Override
    public List<TypePrestation> searchAllTypePrestation() {
        return daoTypr.findAll();
    }

    @Override
    public List<TypeMedecin> searchAllTypeMedecin() {
        return daoTyme.findAll();
    }

    @Override
    public int addRdv(Rdv rdv) {
        return daoRdv.insert(rdv);
    }
    
    @Override
    public List<Rdv> searchRdv(int id) {
        return daoRdv.findAll(id);
    }
    
    @Override
    public List<Rdv> showAllRdv() {
        return daoRdv.findAll(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Medecin> searchAllMedecinType(String specialite) {
        return daoMed.findBySpecialite(specialite);
    }
    
    @Override
    public boolean updateRdv(Rdv rdv) {
        return daoRdv.update(rdv)!=0;
    }
    
    @Override
    public boolean updateConsultation(Consultation consultation) {
        return daoConsult.update(consultation)!=0;
    }
    
    @Override
    public boolean updatePrestation(Prestation prestation) {
        return daoPrest.update(prestation)!=0;
    }
    
    @Override
    public int addConsultation(Consultation consultation) {
        return daoConsult.insert(consultation);
    }
    
    @Override
    public int addPrestation(Prestation prestation) {
        return daoPrest.insert(prestation);
    }
    @Override
    public int countConsultation(int idMedecin, Date date) {
        return daoConsult.count(idMedecin, date);
    }
    
    @Override
    public int countPrestation(Date date) {
        return daoPrest.count(date);
    }
    @Override
    public List<Consultation> searchConsultation(int id) {
        return daoConsult.findAll(id);
    } 
    @Override
    public List<Consultation> showAllConsultation() {
        return daoConsult.findAll(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public List<Consultation> searchConsultationByDate(int idMedecin, Date date) {
        return daoConsult.findByDate(idMedecin, date); 
    }
    
    @Override
    public List<Consultation> searchConsultationOfToday(int idMedecin) {
        return daoConsult.findByToday(idMedecin); 
    }
    
    @Override
    public User findUserById(int id) {
        return daoUser.findById(id);         
    }
    
    @Override
    public Patient findPatientById(int id) {
        return daoPat.findById(id);         
    }
    
    @Override
    public List<Prestation> searchPrestation() {
        return daoPrest.findAll();
    }
    
    @Override
    public List<Prestation> searchPrestationPassed() {
        return daoPrest.findAllPassed();
    }
    
    @Override
    public List<Prestation> searchPrestationByDate(Date date){
        return daoPrest.findByDate(date);
    }
    
    @Override
    public List<Prestation> searchPrestationByPatient(int id) {
        return daoPrest.findAllByPatient(id);
    }
    
    @Override
    public List<Consultation> searchConsultationByPatient(int id) {
        return daoConsult.findAllByPatient(id);
    }
}
