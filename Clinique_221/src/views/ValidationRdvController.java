/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Consultation;
import entities.Prestation;
import entities.Rdv;
import entities.User;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.Service;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ValidationRdvController implements Initializable {
    
    ObservableList<Rdv> obRdvs;
    ObservableList<User> obMedecins;
    private Rdv rdvSelected;
    
    Service service = new Service();
    
    private Consultation consult;
    private Prestation prest;

    @FXML
    private TableView<Rdv> tblvRdv;
    @FXML
    private TableColumn<Rdv, String> tblcService;
    @FXML
    private TableColumn<Rdv, String> tblcType;
    @FXML
    private TableColumn<Rdv, String> tblcDate;
    @FXML
    private TableColumn<Rdv, String> tblcStatut;
    @FXML
    private ComboBox<User> cboMedecins;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Rdv> rdvs = service.showAllRdv();
        loadTableView(rdvs);
        disableCombo(true);
    }

    private void loadTableView( List<Rdv> listRdvs){
        
        // 1 - conversion de la liste en observableList
        obRdvs = FXCollections.
                observableArrayList(listRdvs);
        // 2 construction des colums
        tblcService.setCellValueFactory(new PropertyValueFactory<>("service"));
        tblcType.setCellValueFactory(new PropertyValueFactory<>("typemp"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        
        // 3 l'abonnement de la tableView a obv
        tblvRdv.setItems(obRdvs); 
    }
    
    private void loadComboBoxMedecin(ComboBox<User> cbo)
    {
        String specialite = rdvSelected.getTypemp().toString();
        
       /* 1. Permet d'ajouter la liste des classes dans le observableArrayList */
      obMedecins = FXCollections.observableArrayList(service.searchAllMedecinType(specialite));
      
      /* 2. Permet a la liste classe de s'abonner a l'observable*/
      cbo.setItems(obMedecins);
    }
    
    public int getCount(){
        Date date1 = rdvSelected.getDate();        
        int countp = service.countPrestation(date1);
        
        if("Consultation".equals(rdvSelected.getService())){
            int idMedecin = cboMedecins.getSelectionModel().getSelectedItem().getId();
            int count = service.countConsultation(idMedecin, date1);            
            return count;
        }else{
            return countp;
        }
        
    }
    @FXML
    private void handleValidationRdv(ActionEvent event) {
        
        if(getCount()<1){
            rdvSelected.setStatut("VALIDE");
        
            if(service.updateRdv(rdvSelected)){
                Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rendez-vous");
                alert.setContentText("Rendez-vous mis a jour avec succes");
                alert.show();            
                obRdvs.remove(searchRdv(rdvSelected));

            }
            if("Consultation".equals(rdvSelected.getService())){
                disableCombo(false);
                Integer patient= rdvSelected.getPatient();
                Integer medecin= cboMedecins.getSelectionModel().getSelectedItem().getId();
                Date date = rdvSelected.getDate();
                String libelle = rdvSelected.getTypemp().toString();
                consult = new Consultation(patient, medecin, null, null, date, libelle, "A FAIRE");
                service.addConsultation(consult);
            }else{
                
                Date date = rdvSelected.getDate();
                Integer patient= rdvSelected.getPatient();
                String libelle = rdvSelected.getTypemp().toString();
                prest = new Prestation("A FAIRE", date, patient, libelle);
                service.addPrestation(prest);
            }
        
        }else{
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Rendez-vous");
                alert.setContentText("Le Medecin ou la prestation selectionnée n'est plus disponible");
                alert.show();
        }
        
        
    }

    @FXML
    private void handleSelectRdv(MouseEvent event) {
        rdvSelected = tblvRdv.getSelectionModel().getSelectedItem();
        if("Consultation".equals(rdvSelected.getService())){
            disableCombo(false);
            String specialite = rdvSelected.getTypemp().toString();
            loadComboBoxMedecin(cboMedecins);
        }else{
           disableCombo(true); 
        }

        
    }
    
    private int searchRdv(Rdv rdv){
        int pos=-1;
        for(Rdv rd:obRdvs){
            pos++;
            if(rd.getId()==rdv.getId()){
                return pos;
            }
        }
        return pos;
    }

    @FXML
    private void handleRefusRdv(ActionEvent event) {
        rdvSelected.setStatut("REFUSE");
        if(service.updateRdv(rdvSelected)){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Rendez-vous");
            alert.setContentText("Rendez-vous mis a jour avec succes");
            alert.show();            
            obRdvs.remove(searchRdv(rdvSelected));
        }
    }
    
    public void disableCombo(boolean param) {
        //txtfNci.setDisable(param);
        cboMedecins.setDisable(param);
    }
    
}
