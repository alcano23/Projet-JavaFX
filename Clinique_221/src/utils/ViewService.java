/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import entities.TypeMedecin;
import entities.TypePrestation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

/**
 *
 * @author LENOVO
 */
public class ViewService {
    
    public static void loadComboBoxService(ComboBox<String> cboService){
        cboService.getItems().add("Consultation");
        cboService.getItems().add("Prestation");
        cboService.getSelectionModel().selectFirst();
    } 
    
}
