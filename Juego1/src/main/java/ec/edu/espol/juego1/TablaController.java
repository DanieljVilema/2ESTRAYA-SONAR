/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.juego1;

import ec.edu.espol.juego1.ModeloController;
import ec.edu.espol.juego1.ComputadoraIA;
import ec.edu.espol.juego1.Jugador;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author naomi
 */
public class TablaController implements Initializable,Runnable{
    Jugador jugador1,jugador2;
    ComputadoraIA computadora;
    boolean jugando, terminado;
    public final int HOMBREvsHOMBRE = 1;
    public final int HOMBREvsCOMPUTADORA = 2;
    public final int COMPUTADORAvsCOMPUTADORA = 3;
    public final int JUGADOR1 = 1;
    public final int JUGADOR2 = 2;
    public boolean PENSANDO = false;
    @FXML
    private AnchorPane lblPerdidos;
    @FXML
    private Label lblEstado;
    @FXML
    private ImageView f1;
    @FXML
    private ImageView f2;
    @FXML
    private ImageView f3;
    @FXML
    private ImageView f4;
    @FXML
    private ImageView f5;
    @FXML
    private ImageView f6;
    @FXML
    private ImageView f7;
    @FXML
    private ImageView f8;
    @FXML
    private ImageView f9;
    @FXML
    private Label lblGanados;
    @FXML
    private Label lblEmpatados;
    @FXML
    private Label lblIcono;
    @FXML
    private Label lblGanados2;
    @FXML
    private Label lblPerdidos2;
    @FXML
    private Label lblEmpatados2;
    @FXML
    private Label lblIcono2;
    @FXML
    private Label lblPlayer;
    @FXML
    private Label lblPlayer2;
    @FXML
    private MenuItem mnuIniciar;
    @FXML
    private MenuItem mnuSuspender;
    @FXML
    private MenuItem mnuEstadisticas;
    @FXML
    private Label blPerdidos;
   
    ImageView[] fichas = new ImageView[9];
    int turno = 0;
    int turnoGeneral = 0;
    ModeloController modelo = ModeloController.getInstancia();
    int primerTurno = modelo.primerTurno;

    /*Matriz que representa el juego.*/
    int[] tablero = new int[9];

    @FXML
    private MenuButton mnuJuego;
    @FXML
    private Button btnconfigurar;
        

   public TablaController() {
    
   }
   
   public ModeloController getModeloController() {
        return modelo;
   }
    
    public void setModeloController(ModeloController modeloController) {
        this.modelo = modeloController;
        System.out.println("ModeloController recibido en TablaController");
    }
 
    public void someMethod() {
        if (modelo != null) {
            int tipo_juego = modelo.tipo_juego;
            String nombre1 = modelo.nombre1;         
            String nombre2 = modelo.nombre2;
            System.out.println("Datos de ModeloController en TablaController: " + tipo_juego + ", " + nombre1 + ", " + nombre2);
        } else {
            System.out.println("ModeloController no ha sido establecido en TablaController");
        }
    }
    
    public void run(){
        
    }
    
     public void movimiento( ImageView ficha ){
        /*Colocamos la ficha.*/
        if ( jugando ){    
            if( !PENSANDO )
                ponerFicha( ficha );
            if ( this.modelo.tipo_juego == HOMBREvsCOMPUTADORA && this.turno == JUGADOR2 ){
                PENSANDO = true;
                ponerFichaCPU(computadora.movimiento( this.tablero )); //TRABAJAR
                PENSANDO = false;                
            }
        }
        /*Si se va a comenzar un juego nuevo*/
        if( terminado ){
            reiniciarJuego(); //TRABAJAR
            return;
        }
        
        /*Preguntamos si el juego termino o alguien ganó.*/
        if ( terminado() != 0){  
            
            /*Asignamos resultados.*/
            if ( terminado() == 1 ){
                jugador1.gano();    
                jugador2.perdio();   
                mensaje(jugador1.nombre + " ganó!");  
            }else{
                jugador2.gano(); 
                jugador1.perdio();  
                mensaje(jugador2.nombre + " ganó!");  
            }
            
            /*Mostramos la información.*/
            mostrarInformacion();  
            
            /*Detenemos el juego actual.*/
            jugando = false;
            terminado = true;
            
            
        } else if ( lleno() ){   //TRABAJAR
            /*Asignamos resultados.*/
            jugador1.empato();
            jugador2.empato();
            mensaje("Empate!");  //TRABAJAR
            
            /*Mostramos la información.*/
            mostrarInformacion();   //TRABAJAR
            
            /*Detenemos el juego actual.*/
            jugando = false;
            terminado = true;
        }
        
        /*Movemos el foco al otro jugador.*/
        cambiarFoco();  //TRABAJAR
    }
     
     /*Método que pone una ficha por la computadora.*/
     public void ponerFichaCPU( int indice ){
        
        if( indice == -1 ) return;
        
        switch ( indice ){
            case 0: this.f1.setImage(jugador2.obtenFicha().getImage()); break;
            case 1: this.f2.setImage(jugador2.obtenFicha().getImage()); break;
            case 2: this.f3.setImage(jugador2.obtenFicha().getImage()); break;
            case 3: this.f4.setImage(jugador2.obtenFicha().getImage()); break;
            case 4: this.f5.setImage(jugador2.obtenFicha().getImage()); break;
            case 5: this.f6.setImage(jugador2.obtenFicha().getImage()); break;
            case 6: this.f7.setImage(jugador2.obtenFicha().getImage()); break;
            case 7: this.f8.setImage(jugador2.obtenFicha().getImage()); break;
            case 8: this.f9.setImage(jugador2.obtenFicha().getImage()); break;       
        }
        
        this.tablero[indice] = 2;
        
        /*Cambiamos el turno.*/
        turno = ( turno == JUGADOR1 ) ? JUGADOR2 : JUGADOR1;            
    } 
     
    /*Método que "pone una ficha" en el tablero.*/
    public void ponerFicha( ImageView ficha ){
        /*Obtenemos la casilla.*/
        int casilla = Integer.parseInt(""+ficha.getId().charAt(1)) - 1;
        /*Comprobamos si la casilla no estaba ocupada.*/
        if ( estaOcupada(casilla ) )
            return;       
        /*Elegimos la ficha según el turno*/
        if ( turno == JUGADOR1 )
            ficha.setImage(jugador1.obtenFicha().getImage());
        else
            ficha.setImage(jugador2.obtenFicha().getImage());
        
        /*Guardamos la representación en el tablero*/
        tablero[casilla] = turno;
        
        /*Cambiamos el turno.*/
        turno = ( turno == JUGADOR1 ) ? JUGADOR2 : JUGADOR1;
        
    }
    
     /*Método que dice si el juego está terminado.*/
    /*Regresa 0 si nadie gana, 1 si gana jugador 1 y 2 si gana jugador 2*/
    public int terminado(){
        /*Comprobamos si el juego terminó.*/
        /*Filas*/
        if ( tablero[0] == tablero[1] && tablero[0] == tablero[2] && tablero[0] != 0 )
            return tablero[0];
        else if ( tablero[3] == tablero[4] && tablero[3] == tablero[5]  && tablero[3] != 0  )
            return tablero[3];
        else if ( tablero[6] == tablero[7] && tablero[6]== tablero[8]  && tablero[6] != 0 )
            return tablero[6];
        /*Columnas*/
        else if( tablero[0] == tablero[3] && tablero[0] == tablero[6]  && tablero[0] != 0 )
            return tablero[0];
        else if ( tablero[1] == tablero[4] && tablero[1] == tablero[7]  && tablero[1] != 0  )
            return tablero[1];
        else if ( tablero[2] == tablero[5] && tablero[2] == tablero[8]  && tablero[2] != 0 )
            return tablero[2];
        /*Diagonales*/
        else if ( tablero[0] == tablero[4] && tablero[0] == tablero[8] && tablero[0] !=0 )
            return tablero[0];
        else if ( tablero[2] == tablero[4] && tablero[2] == tablero[6] && tablero[2] != 0 )
            return tablero[2];
        
        return 0;
        
    }
    
     /*Método que nos dice si el tablero se llenó.*/
    public boolean lleno(){
        boolean res = true;
        for ( int i = 0; i < tablero.length; i ++ )
            if ( tablero[i] == 0 )
                res = false;
        
        return res;
    }
    
    /*Método que nos dice si una casilla está ocupada.*/
    public boolean estaOcupada( int casilla ){
        return ( tablero[casilla] != 0 );
    }
       
    /*Método que inicia los componentes */
    private void iniciarComponentes(){  
        /*Referenciamos todas las etiquetas.*/
        fichas = new ImageView[9];
        fichas[0] = f1; fichas[1] = f2; fichas[2] = f3;
        fichas[3] = f4; fichas[4] = f5; fichas[5] = f6;
        fichas[6] = f7; fichas[7] = f8; fichas[8] = f9;
        
        
        /*Cursor para los componentes.*/
        for ( int i = 0; i < 9; i ++ )
       
        fichas[i].setCursor(Cursor.HAND);
    }
    
    public void mensaje(String mensaje){
        this.lblEstado.setText(mensaje);
    }
    
    public void cambiarFoco(){       
        /*Si estamos jugando.*/
        if ( !jugando )
            return;
    
        /*Si es turno del primer jugador..*/
        if ( turno == JUGADOR1 ){
            mensaje("Turno de " + jugador1.nombre );           
        } else {
           mensaje("Turno de " + jugador2.nombre );
        }       
    }

    /*Método que inicia el juego una vez obtenido el modelo.*/
    public void iniciarJuego(){  
        /*Creamos los jugadores según el tipo de juego.*/
        System.out.println("El turno seleccionado es: "  + primerTurno);
        if ( modelo.tipo_juego == HOMBREvsHOMBRE ){
            this.jugador1 = new Jugador( modelo.nombre1,modelo.imagen11 );
            this.jugador2 = new Jugador( modelo.nombre2,modelo.imagen22 );          
            switch (primerTurno) {
                case 1:
                    this.turno = 1;
                    this.turnoGeneral = JUGADOR1;
                    break;
                case 2:
                    this.turno = 2;
                    this.turnoGeneral = JUGADOR2;
                    break;
            }      
            mostrarInformacion();
        } else {
            this.jugador1 = new Jugador( modelo.nombre1, modelo.imagen11);
            this.jugador2 = new Jugador ( "Computadora", modelo.imagen22);
            this.lblPlayer2.setVisible(true);           
            /*Creamos la instancia para la computadora.*/
            computadora = new ComputadoraIA();
                 
            switch (primerTurno) {
                case 1:
                    this.turno = 1;
                    this.turnoGeneral = JUGADOR1;                    
                    break;                   
                default:
                    this.turno = 2;
                    this.turnoGeneral = JUGADOR2;
                    lanzaPc1();
                    break;                
            }          
            mostrarInformacion();
        }
        jugando = true;
        terminado = false;
        
        /*Deshabilitamos el menú nuevo juego.*/
        this.mnuIniciar.setDisable(false);
        this.mnuSuspender.setDisable(false);
       
        
        /*Movemos el foco.*/
        cambiarFoco();
        
    }
    
    public void mostrarInformacion(){ 
        lblPlayer.setText(jugador1.nombre );
        lblPlayer2.setText( jugador2.nombre );
        
        this.lblGanados.setText("" + jugador1.GANADOS );
        this.blPerdidos.setText("" + jugador1.PERDIDOS );
        this.lblEmpatados.setText("" + jugador1.EMPATADOS );
        this.lblIcono.setGraphic(jugador1.obtenFicha() );
        
        this.lblGanados2.setText("" + jugador2.GANADOS );
        this.lblPerdidos2.setText("" + jugador2.PERDIDOS );
        this.lblEmpatados2.setText("" + jugador2.EMPATADOS );
        this.lblIcono2.setGraphic(jugador2.obtenFicha() );
        
        this.lblPlayer.setVisible(true); 
        this.lblPlayer.setVisible(true);
      
    }
    
    /*Método que inicia un nuevo juego.*/
    public void reiniciarJuego(){  //NO SE VE MUY IMPORTANTE PERO HAY QUE TRABAJARLO SI ES QUE SIRVE
        
        //Llenamos el tablero con 0s*/
        Arrays.fill(tablero,0);
        
        /*Borramos los iconos.*/
        for ( int i = 0; i < 9; i ++ )
            fichas[i].setImage(null);
        
       
        /*Cambiamos el turno General.*/
        if ( this.modelo.tipo_juego == HOMBREvsCOMPUTADORA )
            turnoGeneral = JUGADOR1;
        else
            turnoGeneral = ( turnoGeneral == JUGADOR1 ) ? JUGADOR2 : JUGADOR1;
        
        turno = turnoGeneral;
        
        /*Jugando.*/
        if ( turno == JUGADOR1 )
            mensaje( "Turno de " +jugador1.nombre);
        else
            mensaje( "Turno de " +jugador2.nombre);
        
        /*Mostramos su información, asignamos los nombres de jugador al panel.*/
        mostrarInformacion();
        
        jugando = true;
        terminado = false;
    }
    
    /*Método que suspende un juego.*/
    public void suspenderJuego(){ 
        System.out.println("Cerrando juego");
//                
//        //Llenamos el tablero con 0s*/
//        Arrays.fill(tablero,0);
//        
//        /*Borramos los iconos.*/
//        for ( int i = 0; i < 9; i ++ )
//            fichas[i].setImage(null);
//        
//        /*Reinciamos el turno.*/
//        turno = 1;
//        jugando = false;
//        terminado = false;
//        
//        /*Borramos jugadores.*/
//        jugador1 = null;
//        jugador2 = null;
//        
//        /*Habilitamos los menús.*/
//        this.mnuIniciar.setDisable(false); 
//        this.mnuSuspender.setDisable(false);
//        this.lblEstado.setText("Juega al gato!");
//        
//        /*Quitamos los paneles.*/
//        this.lblPlayer.setVisible(false);
//        this.lblPlayer2.setVisible(false);
//        
//        
        
    }
    
    private void initComponents() {
        f1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) { f1MouseClicked(); 
            }
        });
        f2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                f2MouseClicked(); 
            }
        });
        f3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                f3MouseClicked(); 
            }
        });
        f4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                f4MouseClicked(); 
            }
        });
        
        f5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {f5MouseClicked(); 
            }
        });
        f6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                f6MouseClicked(); 
            }
        });
        f7.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                f7MouseClicked(); 
            }
        });
        f8.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                f8MouseClicked(); 
            }
        });
        f9.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                f9MouseClicked(); 
            }
        });  
        lblEstado.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                f8MouseClicked(); 
            }
        });
         
        lblEmpatados2.setText("Empatados: 0");

        lblPerdidos2.setText("Perdidos: 0");

        lblGanados2.setText("Ganados: 0");
        
        lblGanados.setText("Ganados: 0");

        blPerdidos.setText("Perdidos: 0");

        lblEmpatados.setText("Empatados: 0");
        
         mnuIniciar.setOnAction(event -> {
            try {
                mnuIniciarActionPerformed();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        });
         
         mnuSuspender.setOnAction(event -> suspenderJuego());
         
         
         
         btnconfigurar.setOnAction(event -> configurarActionPerformed());
    }
    
    private void configurarActionPerformed() {   
        System.out.println("Configurar");
    } 
    
   private void mnuIniciarActionPerformed() throws IOException {                                           
    ModeloController n = new ModeloController();
    n.setTablaController(this);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Modelo.fxml"));
    Parent root = loader.load();
    this.setModeloController(modelo);
    Stage modeloStage = new Stage();
    Scene scene = new Scene(root, 700, 500);
    modeloStage.setScene(scene);
    modeloStage.show();   
}
     

    private void f9MouseClicked() {                                
        movimiento(f9);
    }                               

    private void f8MouseClicked() {                                
        movimiento(f8);
    }                               

    private void f7MouseClicked() {                                
        movimiento(f7);
    }                               

    private void f6MouseClicked() {                                
        movimiento(f6);
    }                               

    private void f5MouseClicked() {                                
        movimiento(f5);
    }                               

    private void f4MouseClicked() {                                
        movimiento(f4);
    }                               

    private void f3MouseClicked() {                                
        movimiento(f3);
    }                               

    private void f2MouseClicked() {                                
        movimiento(f2);
    }                               

    private void f1MouseClicked() {                                
        movimiento(f1);
    }   
     
    private int aleatoriopc(){
        Random random = new Random();
        int NR = random.nextInt(8) + 1;
        return NR;
    }
    
    private void lanzaPc1(){
        switch (aleatoriopc()) {
                case 1:
                    ponerFichaCPU(1 );
                    break;
                    case 2:
                    ponerFichaCPU(2);
                    break;
                    case 3:
                    ponerFichaCPU(3 );
                    break;
                    case 4:
                    ponerFichaCPU(4 );
                    break;
                    case 5:
                    ponerFichaCPU(5 );
                    break;
                    case 6:
                    ponerFichaCPU(6);
                    break;
                    case 7:
                    ponerFichaCPU(7 );
                    break;
                    case 8:
                    ponerFichaCPU(8 );
                    break;
                    case 9:
                    ponerFichaCPU(9 );
                    break;
    }
    }
    
    public void initialize(URL url, ResourceBundle rb) {
    Arrays.fill(tablero, 0);
    iniciarComponentes();
    initComponents();
        
}
    
}
