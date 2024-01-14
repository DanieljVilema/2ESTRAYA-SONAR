/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.juego1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    public ImageView imagen11;
    public ImageView imagen22;

    /**
     * Initializes the controller class.
     */
    private TablaController tabla;
    
    public final int HOMBREvsHOMBRE = 1;
    public final int HOMBREvsCOMPUTADORA = 2;
    public final int COMPUTADORAvsCOMPUTADORA = 3;
    public String nombre1, nombre2;
    public int tipo_juego = 0;
//    @FXML
//    private Button CONFICHA;
    
    ModosController modo = ModosController.getInstancia();
    boolean modoPVP = modo.isPvp();
    boolean modoPVI = modo.isPvi();
    boolean modoIVI = modo.isIvi();
    
    /** Crea un nuevo Modelo */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         System.out.println(modoPVP);
         System.out.println(modoPVI);
         System.out.println(modoIVI);
         initComponents();
    }  
    
    public ModeloController() {
        // Inicializaciones o lógica del constructor, si es necesario
    }
    public void setTablaController(TablaController TablaController) {
        this.tabla = TablaController;
        System.out.println("TABLAController recibido en MODELOController");
    }
  
    /*Método que recoje los datos.*/
    public boolean recojer(){

        /*Comprobamos que los campos estén llenos.*/
        
        //if(modoPVP = true){
            if( this.txtJugador1.getText().equals("") && this.txtJugador2.getText().equals("")&&modoPVP == true){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Llene el nombre de los jugadores por favor.");
                alert.show();
                return false;   
            }
        //}
        
       // if(modoPVI = true){
            if( this.txtJugador1.getText().equals("")&& modoPVP == true){
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Llene el nombre del jugador 1 por favor.");
                alert.show();
                return false;   
            } 
        //}
  
        /*Recojemos los valores.*/
        this.tipo_juego = (modoPVP == true) ? HOMBREvsHOMBRE :
                  (modoIVI == true) ? COMPUTADORAvsCOMPUTADORA:
                  HOMBREvsCOMPUTADORA;
        
        this.nombre1 = this.txtJugador1.getText();
        this.nombre2 = this.txtJugador2.getText();
        this.imagen11=new ImageView( new Image(getClass().getResourceAsStream("/images/circulo3.png")));
        this.imagen22=new ImageView( new Image(getClass().getResourceAsStream("/images/cruz.png")));
        System.out.println("modifcaa"+this.tipo_juego);
        return true;
    }
    
    
    public boolean quienempieza(){

        /*Comprobamos que los campos estén llenos.*/
        if( (!this.inip1.isSelected() && !this.inipc1.isSelected() )){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Selecione quien inicia primero por favor.");
            alert.show();
            return false;   
        }
        if( (!this.inip1.isSelected() && !this.inip2.isSelected() )){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Selecione quien inicia primero por favor.");
            alert.show();
            return false;   
        }
        if( (!this.inipc1.isSelected() && !this.inipc2.isSelected() )){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setContentText("Selecione quien inicia primero por favor.");
            alert.show();
            return false;   
        }
        return true;
    }
    
    
    private void initComponents() {
 
            if (modoPVP == true) {
                 hvshActionPerformed();  
               System.out.println("PERSONAVSPERSONA");
            }
          
            if (modoPVI == true) {
                hvpcActionPerformed();  
                System.out.println("PCVSPC");
            }
         
            if (modoIVI == true) {
                pcvpcActionPerformed();  
                System.out.println("PERSONAVSPC");
            }
       
          btnAceptar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    // Lógica a ejecutar cuando se hace clic en el botón
                    btnAceptarActionPerformed();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
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
    
    }
    private void btnCancelarActionPerformed() {                                            
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    } 
    
    private void btnAceptarActionPerformed() throws IOException {                                           
        if( recojer() ){
         enviarModelo();
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

   public void enviarModelo() {
    // Crear una instancia de TablaController y establecer el modelo
    TablaController tablaController = new TablaController();
    tablaController.setModeloController(this);

    // Abrir la ventana de TablaController
    abrirVentana(tablaController);

    // Cierra la ventana (Stage) asociada a este controlador
    Stage stage = (Stage) btnAceptar.getScene().getWindow();
    stage.close();
}

// Método para abrir la ventana de TablaController
    private void abrirVentana(TablaController tablaController) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Tabla.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            // Configurar el controlador de la tabla
            TablaController controller = loader.getController();
            controller.setModeloController(tablaController.getModeloController());

            controller.someMethod(); // O cualquier otra lógica que necesites
            controller.iniciarJuego();

            // Mostrar la ventana
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ModeloController getModeloController() {
        return this;
    }
}
