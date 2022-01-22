/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.Patient;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Service;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class NewcomptePatientController implements Initializable {

    @FXML
    private TextField txtnName;
    @FXML
    private TextField txtpPassword;
    @FXML
    private TextField txtlLogin;
    @FXML
    private Text txtError;
    @FXML
    private TextField txtnNci;
    @FXML
    private TextArea txtnAntecedents;
    
    private final Service service = new Service();
    
    private static NewcomptePatientController ctrl;
    
    private Patient patient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        ctrl = this;
    }    

    @FXML
    private void switchToConnexion(ActionEvent event) {
        this.txtError.getScene().getWindow().hide();
        AnchorPane root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/v_connexion.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleCreation(ActionEvent event) {
        String nomComplet = txtnName.getText().trim();
        String login = txtlLogin.getText().trim();
        String password = txtpPassword.getText().trim();
        String nci = txtnNci.getText().trim();
        String antecedents = txtnAntecedents.getText().trim();
        patient = new Patient(nomComplet, login, password, nci, antecedents );
        if(login.isEmpty() || password.isEmpty() || nomComplet.isEmpty())
        {
          txtError.setText("Les champs avec une etoile(*) sont obligatoires");
          txtError.setVisible(true);
        }
        else
        {
          service.addPatient(patient);
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Inscription");
          alert.setContentText("Compte créé avec succès! ");
          alert.show();
          clearFields();
        }
    }
    
    public void clearFields() {
        txtnName.clear();
        txtpPassword.clear();
        txtlLogin.clear();
        txtnNci.clear();
        txtnAntecedents.clear();
        
    }
}
