/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Consultation;
import entities.Prestation;
import entities.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Service;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DossierMedicalController implements Initializable {
    
    ObservableList<Prestation> obPrestas;
    ObservableList<Consultation> obConsults;
    ObservableList<User> obPatients;
    
    Service service = new Service();

    @FXML
    private TableView<Consultation> tblvConsultations;
    @FXML
    private TableColumn<Consultation, String> tblcTypeMedecin;
    @FXML
    private TableColumn<Consultation, String> tblcDate;
    @FXML
    private TableColumn<Consultation, String> tblcOrdonnance;
    @FXML
    private TableView<Prestation> tblvPrestations;
    @FXML
    private TableColumn<Prestation, String> tblcLibelle;
    @FXML
    private TableColumn<Prestation, String> tblcDate2;
    @FXML
    private TableColumn<Prestation, String> tblcResultats;
    @FXML
    private ComboBox<User> cboPatients;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadComboBoxPatients(cboPatients);
    }
    
    private void loadComboBoxPatients(ComboBox<User> cbo)
    {
       /* 1. Permet d'ajouter la liste des classes dans le observableArrayList */
      obPatients = FXCollections.observableArrayList(service.searchAllPatients());
      
      /* 2. Permet a la liste classe de s'abonner a l'observable*/
      cbo.setItems(obPatients);
    }

    private void loadTableView( List<Prestation> listPrestations){
        
        // 1 - conversion de la liste en observableList
        obPrestas = FXCollections.
                observableArrayList(listPrestations);
        // 2 construction des colums
        tblcLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblcDate2.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcResultats.setCellValueFactory(new PropertyValueFactory<>("resultat"));
        
        // 3 l'abonnement de la tableView a obv
        tblvPrestations.setItems(obPrestas); 
    }
    
    private void loadTableView2( List<Consultation> listConsultations){
        
        // 1 - conversion de la liste en observableList
        obConsults = FXCollections.
                observableArrayList(listConsultations);
        // 2 construction des colums
        tblcTypeMedecin.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcOrdonnance.setCellValueFactory(new PropertyValueFactory<>("ordonnance"));
        
        // 3 l'abonnement de la tableView a obv
        tblvConsultations.setItems(obConsults); 
    }

    @FXML
    private void handleDossierMedical(ActionEvent event) {
        Integer id = cboPatients.getSelectionModel().getSelectedItem().getId();
        List<Prestation> prestations = service.searchPrestationByPatient(id);
        loadTableView(prestations);
        List<Consultation> consultations = service.searchConsultationByPatient(id);
        loadTableView2(consultations);
    }
    
}
