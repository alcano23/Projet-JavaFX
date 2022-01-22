/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Consultation;
import entities.Patient;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.Service;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DoConsultationController implements Initializable {
    
    private Patient patientSearch;    
    ObservableList<Consultation> obConsults;
    private Consultation consultSelected;
    
    Service service = new Service();

    @FXML
    private TableView<Consultation> tblvConsultations;
    @FXML
    private TableColumn<Consultation, String> tblcDate;
    @FXML
    private TableColumn<Consultation, String> tblcStatut;
    @FXML
    private TextArea txtAntecedents;
    @FXML
    private TextField txtNomComplet;
    @FXML
    private TextArea txtOrdonnance;
    @FXML
    private TextField txtPrestation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Integer id = ConnexionController.getCtrl().getUser().getId();
        List<Consultation> consultations = service.searchConsultationOfToday(id);
        loadTableView(consultations);
        disableFields(true);
    }    

    private void loadTableView( List<Consultation> listConsultations){
        
        // 1 - conversion de la liste en observableList
        obConsults = FXCollections.
                observableArrayList(listConsultations);
        // 2 construction des colums
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        
        // 3 l'abonnement de la tableView a obv
        tblvConsultations.setItems(obConsults); 
    }
    
    @FXML
    private void handleSelectedConsultation(MouseEvent event) {
        consultSelected = tblvConsultations.getSelectionModel().getSelectedItem();
        patientSearch = service.findPatientById(consultSelected.getPatient());

        
        txtNomComplet.setText(patientSearch.getNomComplet());
        txtAntecedents.setText(patientSearch.getAntecedents());
        
    }
    
    private void disableFields(boolean value ){
          txtNomComplet.setDisable(value);
          txtAntecedents.setDisable(value);    
    }

    @FXML
    private void handleTerminerConsultation(ActionEvent event) {
        consultSelected.setOrdonnance(txtOrdonnance.getText());
        consultSelected.setPrestation(txtPrestation.getText());
        consultSelected.setStatut("TERMINEE");        
        
        if(service.updateConsultation(consultSelected)){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Consultation");
            alert.setContentText("Consultation terminée avec succes");
            alert.show();            
            obConsults.remove(searchConsultation(consultSelected));
        }
    }
    
    private int searchConsultation(Consultation consultation){
        int pos=-1;
        for(Consultation consult:obConsults){
            pos++;
            if(consult.getId()==consultation.getId()){
                return pos;
            }
        }
        return pos;
    }

    @FXML
    private void handleClearFields(ActionEvent event) {
        txtOrdonnance.clear();
        txtPrestation.clear();
    }
}
