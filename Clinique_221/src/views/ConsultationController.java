/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Consultation;
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
import javafx.scene.control.DatePicker;
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
public class ConsultationController implements Initializable {
    
    private Consultation consultationSearch;
    ObservableList<Consultation> obConsults;
    private Consultation consultSelected;
    
    Service service = new Service();

    @FXML
    private TableView<Consultation> tblvConsultations;
    @FXML
    private TableColumn<Consultation, String> tblcPatient;
    @FXML
    private TableColumn<Consultation, String> tblcDate;
    @FXML
    private TableColumn<Consultation, String> tblcStatut;
    @FXML
    private DatePicker txtdDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Integer id = ConnexionController.getCtrl().getUser().getId();
        List<Consultation> consultations = service.searchConsultation(id);
        loadTableView(consultations);
    }    
    
    private void loadTableView( List<Consultation> listConsultations){
        
        // 1 - conversion de la liste en observableList
        obConsults = FXCollections.
                observableArrayList(listConsultations);
        // 2 construction des colums
        tblcPatient.setCellValueFactory(new PropertyValueFactory<>("patient"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        
        // 3 l'abonnement de la tableView a obv
        tblvConsultations.setItems(obConsults); 
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
    private void handleAnnulerConsultation(ActionEvent event) {
        consultSelected.setStatut("ANNULE");
        if(service.updateConsultation(consultSelected)){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Consultation");
            alert.setContentText("Consultation annul??e avec succes");
            alert.show();            
            obConsults.remove(searchConsultation(consultSelected));
        }
    }

    @FXML
    private void handleSearchConsultaionByDate(MouseEvent event) {
        Integer idMedecin = ConnexionController.getCtrl().getUser().getId();
        Date date = java.sql.Date.valueOf(txtdDate.getValue());
        List<Consultation> consultations = service.searchConsultationByDate(idMedecin ,date);
        loadTableView(consultations);
        
    }

    @FXML
    private void handleSelectConsultation(MouseEvent event) {
        consultSelected = tblvConsultations.getSelectionModel().getSelectedItem();
    }
    
}
