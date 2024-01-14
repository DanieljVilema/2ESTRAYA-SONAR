/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.juego1;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class ModosController {
    
    private static ModosController instancia;
    
    private boolean pvp;
    private boolean pvi;
    private boolean ivi;
    
    public static ModosController getInstancia() {
        if (instancia == null) {
            instancia = new ModosController();
        }
        return instancia;
    }
    
    public boolean isPvp() {
        return pvp;
    }

    public boolean isPvi() {
        return pvi;
    }

    public boolean isIvi() {
        return ivi;
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
    }

    public void setPvi(boolean pvi) {
        this.pvi = pvi;
    }

    public void setIvi(boolean ivi) {
        this.ivi = ivi;
    }
    
    
    public ModosController() {
    }
    
    
    @FXML
    private void btnPVP(ActionEvent event) throws IOException {
        ModosController modo = ModosController.getInstancia();
        modo.setPvp(true);
        App.redimentionScenes(event, "Modelo", 600, 500);
    }
    
    @FXML
    private void btnPVI(ActionEvent event) throws IOException {
        ModosController modo = ModosController.getInstancia();
        modo.setPvi(true);
        App.redimentionScenes(event, "Modelo", 600, 500);
    }
    
    @FXML
    private void btnIAvsIA(ActionEvent event) throws IOException {
        ModosController modo = ModosController.getInstancia();
        modo.setIvi(true);
        App.redimentionScenes(event, "Modelo", 600, 500);
    }
    
}
    