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
    