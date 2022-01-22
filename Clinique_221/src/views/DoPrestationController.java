/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Prestation;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.Service;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class DoPrestationController implements Initializable {
    
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
    private TextArea txtResultats;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Integer id = ConnexionController.getCtrl().getUser().getId();
        List<Prestation> prestations = service.searchPrestationPassed();
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
    
    @FXML
    private void handleSelectPrestation(MouseEvent event) {
        prestaSelected = tblvPrestations.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void handleDoPrestation(ActionEvent event) {
        prestaSelected.setResultat(txtResultats.getText());
        prestaSelected.setStatut("TERMINEE");        
        
        if(service.updatePrestation(prestaSelected)){
            Alert alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Prestation");
            alert.setContentText("Resultats envoyés avec succes");
            alert.show();            
            obPrestas.remove(searchPrestation(prestaSelected));
        }
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
    
}
