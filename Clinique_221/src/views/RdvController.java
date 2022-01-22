/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Patient;
import entities.Rdv;
import entities.Typemp;
import entities.TypeMedecin;
import entities.TypePrestation;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.Service;
import utils.ViewService;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class RdvController implements Initializable {
    
    private String serviceChoisi;
    ObservableList<Typemp> obTypes;
    ObservableList<Rdv> obRdvs;
    
    Service service = new Service();
    
    @FXML
    private ComboBox<String> cboService;
    @FXML
    private ComboBox<Typemp> cboType;
    @FXML
    private DatePicker txtdDate;
    
    private int patient;
    private Rdv rdv;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ViewService.loadComboBoxService(cboService);
        Integer id = ConnexionController.getCtrl().getUser().getId();
        List<Rdv> rdvs = service.searchRdv(id);
        loadComboBoxTypeMedecin(cboType);
        loadTableView(rdvs);
        dateHandler(txtdDate);
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

    private void loadComboBoxTypeMedecin(ComboBox<Typemp> cbo)
    {
       /* 1. Permet d'ajouter la liste des classes dans le observableArrayList */
      obTypes = FXCollections.observableArrayList(service.searchAllTypeMedecin());
      
      /* 2. Permet a la liste classe de s'abonner a l'observable*/
      cbo.setItems(obTypes);
    }
    
    private void loadComboBoxTypePrestation(ComboBox<Typemp> cbo)
    {
       /* 1. Permet d'ajouter la liste des classes dans le observableArrayList */
      obTypes = FXCollections.observableArrayList(service.searchAllTypePrestation());
      
      /* 2. Permet a la liste classe de s'abonner a l'observable*/
      cbo.setItems(obTypes);
    }

    @FXML
    private void handleChangeService(ActionEvent event) {
        /*Recuperation d'une valeur via la view*/
        serviceChoisi = cboService.getSelectionModel().getSelectedItem();
        if("Consultation".equals(serviceChoisi)){
            loadComboBoxTypeMedecin(cboType);
        }
        else{
            loadComboBoxTypePrestation(cboType);
        }
    }
    
    public String getServiceChoisi(){
        return serviceChoisi;
    }

    
    private void dateHandler(DatePicker dp){
         dp.setDayCellFactory(picker -> new DateCell() {
                    @Override
                    public void updateItem(LocalDate date, boolean empty) {
                        super.updateItem(date, empty);
                        LocalDate today = LocalDate.now();

                        setDisable(empty || date.compareTo(today) < 0 );
                    }
                    });
    }

    @FXML
    private void handleDate(ActionEvent event) {
    }

    @FXML
    private void handleAddRdv(ActionEvent event) {
        //valider les champs

        Typemp type = cboType.getSelectionModel().getSelectedItem();
        String serviceChoisit = cboService.getSelectionModel().getSelectedItem();
        Date date = java.sql.Date.valueOf(txtdDate.getValue());
        patient = ConnexionController.getCtrl().getUser().getId();

        
        rdv = new Rdv(serviceChoisit, type, date, patient);
        service.addRdv(rdv);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Rendez-vous");
          alert.setContentText("Rendez-vous ajouté avec succès! ");
          alert.show();
          
        //Mise a jour de la tbale view des inscriptions
        Rdv rdv = new Rdv();
        rdv.setService(serviceChoisit);
        rdv.setDate(date);
        rdv.setStatut("EN COURS");
        rdv.setTypemp(type);
        obRdvs.add(rdv);
    }
    
}
