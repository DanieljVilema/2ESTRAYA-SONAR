/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.juego1;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class MenuController {
    
    @FXML
    private void switchToJugar(ActionEvent event) throws IOException {
        App.redimentionScenes(event, "Modelo", 600, 500);
    }
    
}
    