/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entities.User;
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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Service;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ConnexionController implements Initializable {

    @FXML
    private TextField txtlLogin;
    @FXML
    private Text txtError;
    @FXML
    private TextField txtpPassword;

    private final Service service = new Service();
    
    private static ConnexionController ctrl;
    
    private User user;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtError.setVisible(false);
        ctrl = this;
    }    

    @FXML
    private void handleClear(ActionEvent event) {
        txtlLogin.clear();
        txtpPassword.clear();
        txtError.setVisible(false);
    }

    @FXML
    private void HandleConnexion(ActionEvent event) {
        String login = txtlLogin.getText().trim();
        String password = txtpPassword.getText().trim();
        if(login.isEmpty() || password.isEmpty())
        {
          txtError.setText("Login ou le mot de passe Obligatoire");
          txtError.setVisible(true);
        }
        else{
          user = service.login(login, password);
          if(user == null)
          {
               txtError.setText("Login ou le mot de passe Incorrect");
               txtError.setVisible(true);
          }
          else
          {
              //Cache la f??n??tre de connexion
              this.txtError.getScene().getWindow().hide();
              AnchorPane root = null;
              if("ROLE_MEDECIN".equals(user.getRole())){
                  try {
                  root = FXMLLoader.load(getClass().getResource("/views/v_homeMedecin.fxml"));
                  Scene scene = new Scene(root);
                  Stage stage = new Stage();
                  stage.setScene(scene);
                  stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                }
                  
              }else{
                  if("ROLE_SECRETAIRE".equals(user.getRole())){
                        try {
                        root = FXMLLoader.load(getClass().getResource("/views/v_homeSecretaire.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                      } catch (IOException ex) {
                          Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                      }

                   }else{
                      if("ROLE_PATIENT".equals(user.getRole())){
                            try {
                            root = FXMLLoader.load(getClass().getResource("/views/v_homePatient.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            stage.setScene(scene);
                            stage.show();
                          } catch (IOException ex) {
                              Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                          }
                  
                        }else{
                          if("ROLE_RP".equals(user.getRole())){
                                try {
                                root = FXMLLoader.load(getClass().getResource("/views/v_homeResponsablePrestation.fxml"));
                                Scene scene = new Scene(root);
                                Stage stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                              } catch (IOException ex) {
                                  Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
                              }

                            }
                      }
                  }
              }
              
          }
        }
    }

    public static ConnexionController getCtrl() {
        return ctrl;
    }

    public User getUser() {
        return user;
    }

    @FXML
    private void switchToNewcompte(MouseEvent event){
        this.txtError.getScene().getWindow().hide();
        AnchorPane root= null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/v_newcomptePatient.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ConnexionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
