/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Consultation;
import entities.Prestation;
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
public class PrestationController implements Initializable {
    
    private Prestation prestationSearch;
    ObservableList<Prestation> obPrestas;
    private Prestation prestaSelected;
    
    Service service = new Service();

    @FXML
    private TableView<Prestation> tblvPrestations;
    @FXML
    private TableColumn<Prestation, String> tblcPrestation;
    @FXML
    private TableColumn<Prestation, String> tblcDate;
    @FXML
    private TableColumn<Prestation, String> tblcStatut;
    @FXML
    private DatePicker txtdDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Integer id = ConnexionController.getCtrl().getUser().getId();
        List<Prestation> prestations = service.searchPrestation();
        loadTableView(prestations);
    }    

    private void loadTableView( List<Prestation> listPrestations){
        
        // 1 - conversion de la liste en observableList
        obPrestas = FXCollections.
                observableArrayList(listPrestations);
        // 2 construction des colums
        tblcPrestation.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        tblcDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tblcStatut.setCellValueFactory(new PropertyValueFactory<>("statut"));
        
        // 3 l'abonnement de la tableView a obv
        tblvPrestations.setItems(obPrestas); 
    }
    
    private int searchPrestation(Prestation prestation){
        int pos=-1;
        for(Prestation presta:obPrestas){
            pos++;
            if(presta.getId()==prestation.getId()){
                return pos;
            }
        }
        return pos;
    }
    
    @FXML
    private void handleSelectedPrestation(MouseEvent event) {
        prestaSelected = tblvPrestations.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleRefusePrestation(ActionEvent event) {
        prestaSelected.setStatut("ANNULE");
        if(service.updatePrestation(prestaSelected)){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prestation");
            alert.setContentText("Prestation annul??e avec succes");
            alert.show();            
            obPrestas.remove(searchPrestation(prestaSelected));
        }
    }

    @FXML
    private void handleSearchPrestation(MouseEvent event) {
        Date date = java.sql.Date.valueOf(txtdDate.getValue());
        List<Prestation> prestations = service.searchPrestationByDate(date);
        loadTableView(prestations);
    }
    
}
