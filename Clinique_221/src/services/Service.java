/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.PatientDao;
import dao.UserDao;
import entities.Patient;
import entities.User;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Service implements IService {
    UserDao daoUser=new UserDao();
    //Dependances avec la couche DAO
    PatientDao daoPat=new PatientDao();
    
    /*
    @Override
    public boolean updateCLasse(Classe classe) {
        return daoClasse.update(classe)!=0;
    }

    @Override
    public boolean deleteCLasse(int id) {
        return daoClasse.delete(id)!=0; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Classe> searchAllClasse() {
    return daoClasse.findAll();
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
    public List<Professeur> showAllProfesseur() {
        return daoProf.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Etudiant searchEtudiantByMatricule(String matricule) {
        return daoEtu.findByMatricule(matricule); 
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
    public List<EtudiantDTO> searchInscription(String annee) {
        return daoEtu.findAll(annee); 

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
    public User addUser(User u) {
        if(u.getId()==0)
        {
            int id= daoPat.insert(u);
            u.setId(id);
        }
        User u = new User(u);
        return u.getId() ;
    }

    @Override
    public User addPatient(Patient pat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
