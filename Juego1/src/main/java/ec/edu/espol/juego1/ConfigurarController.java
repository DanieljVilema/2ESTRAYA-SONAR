/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.juego1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author naomi
 */
public class ConfigurarController implements Initializable {

    @FXML
    private Label lblInstrucciones;
    @FXML
    private ComboBox<String> cmbElegir;
    @FXML
    private Label lblFicha1;
    @FXML
    private Label lblFicha2;
    @FXML
    private Button jButton1;

    /**
     * Initializes the controller class.
     */
    /*Creamos los vectores con las rutas a las imagenes.*/
    
    /*Predeterminado.*/
    public Image[] fichas = new Image[2];
    public Image[] fichas1 = new Image[2];
    public Image[] fichas2 = new Image[2];
    public Image[] fichas3 = new Image[2];
    public Image[] fichas4 = new Image[2];
    
    
     private void initComponents() {
         lblFicha1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
         lblFicha2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/cruz.png")))); 
         
          ObservableList<String> opciones = FXCollections.observableArrayList(
                "Predeterminado",
                "Radioactivo",
                "Discos",
                "Cielo"
        ); 

        // Asignar la lista al ComboBox
        cmbElegir.setItems(opciones);
        cmbElegir.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica para manejar la selección
                cmbElegirActionPerformed(event);
            }
        });
        jButton1.setOnAction(e -> {
            // Lógica para manejar el clic del botón
            jButton1ActionPerformed();
        });

     }
    private void jButton1ActionPerformed() {                                         
        //Stage.close();
        System.out.println("CERRANDO");
    }     
    private void cmbElegirActionPerformed(ActionEvent event) {                                          
        
        int indice = ((ComboBox) event.getSource()).getSelectionModel().getSelectedIndex();
        if ( indice == 0 ){
            fichas[0] = fichas1[0];
            fichas[1] = fichas1[1];
        }else if ( indice == 1 ){
            fichas[0] = fichas2[0];
            fichas[1] = fichas2[1];
        }else if ( indice == 2 ){
            fichas[0] = fichas3[0];
            fichas[1] = fichas3[1];
        }else if ( indice == 3 ){
            fichas[0] = fichas4[0];
            fichas[1] = fichas4[1];
        }
        
        lblFicha1.setGraphic(new ImageView(fichas[0] ));
        lblFicha2.setGraphic(new ImageView( fichas[1] ));
    }    
     
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initComponents();
        fichas1[0] = new Image(getClass().getResourceAsStream("/images/circulo3.png"));
        fichas1[1] = new Image(getClass().getResourceAsStream("/images/cruz.png"));
        
        fichas2[0] =new Image(getClass().getResourceAsStream("/images/radio1.png"));
        fichas2[1] = new Image(getClass().getResourceAsStream("/images/radio2.png"));

        fichas3[0] = new Image(getClass().getResourceAsStream("/images/disco1.png"));
        fichas3[1] =new Image(getClass().getResourceAsStream("/images/disco2.png"));

        fichas4[0] = new Image(getClass().getResourceAsStream("/images/cielo1.png"));
        fichas4[1] = new Image(getClass().getResourceAsStream("/images/cielo2.png"));
        
        fichas[0] = fichas1[0];
        fichas[1] = fichas1[1];
    }    
    
}
