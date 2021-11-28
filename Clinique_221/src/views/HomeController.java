/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane anchorContent;
    @FXML
    private Text txtProfil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtProfil.setText("Profil, " + ConnexionController.getCtrl().getUser().getRole());
    }    
    
}
