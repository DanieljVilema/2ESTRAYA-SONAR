/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.juego1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author naomi
 */
public class ModeloController implements Initializable {

    @FXML
    private AnchorPane opcionDos;
    @FXML
    private Label opcionUno;
    @FXML
    private TextField txtJugador1;
    @FXML
    private TextField txtJugador2;
    @FXML
    private Label jugador1;
    @FXML
    private Label jugador2;
    @FXML
    private Button btnAceptar;
    @FXML
    private Button btnCancelar;
    @FXML
    private RadioButton hvpc;
    @FXML
    private RadioButton pcvpc;
    @FXML
    private RadioButton hvsh;
    @FXML
    private Label img1;
    @FXML
    private Label img2;
    @FXML
    private Label img3;
    @FXML
    private RadioButton inip1;
    @FXML
    private RadioButton inipc1;
    @FXML
    private RadioButton inip2;
    @FXML
    private RadioButton inipc2;
    @FXML
    private Label opcionTres;
    @FXML
    private Label opciondos;

    /**
     * Initializes the controller class.
     */
    private TablaController tabla;
    
    public final int HOMBREvsHOMBRE = 1;
    public final int HOMBREvsCOMPUTADORA = 2;
    public final int COMPUTADORAvsCOMPUTADORA = 3;
    public String nombre1, nombre2;
    public int tipo_juego = 0;
    
    /** Crea un nuevo Modelo */
//    public ModeloController( TablaController tabla ) {
//        /*Iniciamos componentes visuales.*/
//        //initComponents();
//        ///setVisible(true);
//        //mensaje = new JOptionPane();
//        
//        /*Asignamos el gato.*/
//        this.tabla = tabla;
//        
//    }
    public void asignacion(TablaController tabla ){
        this.tabla = tabla;
    }
    /*Método que recoje los datos.*/
    public boolean recojer(){

        /*Comprobamos que los campos estén llenos.*/
        if( this.pcvpc.isSelected() ){
            return true;   
        }
        if( this.txtJugador1.getText().equals("")&& this.hvpc.isSelected() ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Llene el nombre del jugador 1 por favor.");
            alert.show();
            return false;   
        }
        if( this.txtJugador1.getText().equals("") && this.txtJugador2.getText().equals("") && this.hvsh.isSelected() ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Llene el nombre de los jugadores por favor.");
            alert.show();
            return false;   
           
        }
        if( this.txtJugador2.getText().equals("") && this.hvsh.isSelected() ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Llene el nombre del jugador 2 por favor.");
            alert.show();
            return false;   
           
        }
        if( this.txtJugador1.getText().equals("") && this.hvsh.isSelected() ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Llene el nombre del jugador 1 por favor.");
            alert.show();
            return false;   
           
        }
        if( this.txtJugador1.getText().equals( this.txtJugador2.getText() )){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Escriba nombres diferentes para los jugadores.");
            alert.show();
            return false; 
            
        }
        
        /*Recojemos los valores.*/
        this.tipo_juego = (this.hvsh.isSelected()) ? HOMBREvsHOMBRE :
                  (this.pcvpc.isSelected()) ? COMPUTADORAvsCOMPUTADORA :
                  HOMBREvsCOMPUTADORA;
        this.nombre1 = this.txtJugador1.getText();
        this.nombre2 = this.txtJugador2.getText();
        return true;
    }
    
    
    public boolean quienempieza(){

        /*Comprobamos que los campos estén llenos.*/
        if( (!this.inip1.isSelected() && !this.inipc1.isSelected() )&&  this.hvpc.isSelected() ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Selecione quien inicia primero por favor.");
            alert.show();
            return false;   
        }
        if( (!this.inip1.isSelected() && !this.inip2.isSelected() )&&  this.hvsh.isSelected() ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Selecione quien inicia primero por favor.");
            alert.show();
            return false;   
        }
        if( (!this.inipc1.isSelected() && !this.inipc2.isSelected() )&&  this.pcvpc.isSelected() ){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Selecione quien inicia primero por favor.");
            alert.show();
            return false;   
        }
        return true;
    }
    /*Método que envía los datos ( modelo ) al gato.*/
    public void enviarModelo(){
        tabla.recojerModelo();
    } 
    
    private void initComponents() {
 
          hvsh.setOnAction(event -> {
            if (hvsh.isSelected()) {
                 hvshActionPerformed();  
               System.out.println("PERSONAVSPERSONA");
            }
        });
           pcvpc.setOnAction(event -> {
            if (pcvpc.isSelected()) {
                pcvpcActionPerformed();  
                System.out.println("PCVSPC");
            }
        });
           hvpc.setOnAction(event -> {
            if (hvpc.isSelected()) {
                hvpcActionPerformed();  
                System.out.println("PERSONAVSPC");
            }
        });
          btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica a ejecutar cuando se hace clic en el botón
                btnAceptarActionPerformed();  
                
            }
        });
          btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Lógica a ejecutar cuando se hace clic en el botón
                btnCancelarActionPerformed();  
                System.out.println("5");
            }
        });
         img1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/persona.png"))));
         img2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/pvsp.png")))); 
         img3.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/pvspc.png")))); 
         opcionUno.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/rating_star.png")))); 
         opciondos.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/rating_star.png")))); 
         opcionTres.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/rating_star.png")))); 
    }
    private void btnCancelarActionPerformed() {                                            
       // this.dispose();SE CIERRA LA VENTANA
    } 
    private void btnAceptarActionPerformed() {                                           
        /*Recojemos los datos de los campos.*/
        System.out.println("4");
        if( recojer()&&quienempieza() ){
            System.out.println("funciona bien");
            /*Los enviamos al gato.*/
            enviarModelo();
            /*Cerramos esta ventana.*/
            //dispose();  CAMBIAR A METODO PARA CAMBIAR LA VENTANA E IR AL JUEGO
        }
    }  
   
    private void hvshActionPerformed() {   
        txtJugador2.setDisable(false);
        jugador2.setDisable(false);
        txtJugador1.setDisable(false);
        jugador1.setDisable(false);
        inip2.setDisable(false);
        inipc2.setDisable(true);
        inip1.setDisable(false);
        inipc1.setDisable(true);
       
    } 
    
    private void hvpcActionPerformed() {                                     
        txtJugador2.setDisable(true);
        jugador2.setDisable(true);
        txtJugador1.setDisable(false);
        jugador1.setDisable(false);
        inip2.setDisable(true);
        inipc2.setDisable(true);
        inip1.setDisable(false);
        inipc1.setDisable(false);
    }  
     
     private void pcvpcActionPerformed() {                                     
        txtJugador2.setDisable(true);
        jugador2.setDisable(true);
        txtJugador1.setDisable(true);
        jugador1.setDisable(true);
        inip2.setDisable(true);
        inipc2.setDisable(false);
        inip1.setDisable(true);
        inipc1.setDisable(false);
    }  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          /** Crea un nuevo Modelo */
    
        asignacion( tabla );
        
    
         initComponents();
    }    
    
}
