/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.juego1;

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
    