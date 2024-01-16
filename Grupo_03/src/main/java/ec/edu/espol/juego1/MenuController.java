/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.juego1;

/**
 *
 * @author danie
 */

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MenuController {
    
    @FXML
    private Button btnCancelar;
    
    @FXML
    private void switchToModos(ActionEvent event) throws IOException {
        App.redimentionScenes(event, "Modos", 615, 348);
    }
    
    @FXML
    private void btnCancelarActionPerformed() {                                            
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    } 
    
}
    