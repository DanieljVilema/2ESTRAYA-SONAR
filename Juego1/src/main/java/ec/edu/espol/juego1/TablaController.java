/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.juego1;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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
    ModeloController modelo;
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
    private Label Tablero;
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
    private Label Fondo;
    @FXML
    private Label blPerdidos;
    /**
     * Initializes the controller class.
     */
     /*Turno de jugador.*/
    ConfigurarController config = new ConfigurarController();
    Estadisticas estadisticas = new Estadisticas();
    ImageView[] fichas = new ImageView[9];
    int turno = 0;
    int turnoGeneral = 0;
    
    /*Matriz que representa el juego.*/
    int[] tablero = new int[9];

    /*Tablero en componentes.*/
   // Label fichas[]; //PREGUNTAR ESTO
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

            // Realiza acciones con los datos
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
                ponerFicha( ficha );//TRABAJAR
            
            
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
        if ( terminado() != 0){  //TRABAJAR
            
            /*Asignamos resultados.*/
            if ( terminado() == 1 ){
                jugador1.gano();    //TRABAJAR
                jugador2.perdio();   //TRABAJAR
                mensaje(jugador1.nombre + " ganó!");  //TRABAJAR
            }else{
                jugador2.gano(); //TRABAJAR
                jugador1.perdio();  //TRABAJAR
                mensaje(jugador2.nombre + " ganó!");  //TRABAJAR
            }
            
            /*Mostramos la información.*/
            mostrarInformacion();  //TRABAJAR
            
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
        
        switch ( indice ){//editado el seticon por el setgrafic
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
        int casilla = Integer.parseInt(""+ficha.getId().charAt(1)) - 1;// VERIFICAR ESE CAMBIO
        
        /*Comprobamos si la casilla no estaba ocupada.*/
        if ( estaOcupada(casilla ) )
            return;
        
        /*Elegimos la ficha según el turno*/
        if ( turno == JUGADOR1 )
            ficha.setImage(jugador1.obtenFicha().getImage());//editado el seticon
        else
            ficha.setImage(jugador2.obtenFicha().getImage());//editado el seticon
        
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
    
    
    /*Método que inicia los componentes del Gato.*/
    private void iniciarComponentes(){
        /*Icono del formulario.*/
        //this.setIconImage( Image );
        
        /*Hacemos visible el formulario.*/   //aun no se como arreglar esto 
        //this.setVisible(true);//TRABAJAR descubir de donde sale esto
        
        
        /*Referenciamos todas las etiquetas.*/
        fichas = new ImageView[9];
        fichas[0] = f1; fichas[1] = f2; fichas[2] = f3;
        fichas[3] = f4; fichas[4] = f5; fichas[5] = f6;
        fichas[6] = f7; fichas[7] = f8; fichas[8] = f9;
        
        /*Mostramos el panel con estadisticas.*/
        //this.mostrarEstadisticas();
        
        /*Cursor para los componentes.*/
        for ( int i = 0; i < 9; i ++ )
       
        fichas[i].setCursor(Cursor.HAND);//trabajr
    }
    
     /*Método que recupera la información del jugador y la usa en el panel de usuario.*/
    
    
    /*Método que muestra las estadisticas.*/
    public void mostrarEstadisticas(){ //TRABAJARLOCAL!!
        /*
        this.estadisticas.guardarJugador( new Jugador("","") );
        String esta = this.estadisticas.leerDatos();
        this.txtEsta.setText(esta);
        this.panelEstadisticas.setVisible(true);
        */
    }
    
     public void mensaje(String mensaje){
        this.lblEstado.setText(mensaje);
    }
    
     
      public void cambiarFoco(){ //TRABAJARLOCAL!!
        
        /*Si estamos jugando.*/
        if ( !jugando )
            return;
        
        /*Si es turno del primer jugador..*/
        if ( turno == JUGADOR1 ){
            /*Seleccionamos su ventana.*/
           // try{this.panelJ1.setSelected(true);
           // this.panelJ2.setSelected(false);}
           // catch(Exception ex){}
            
            mensaje("Turno de " + jugador1.nombre );
            
        } else {
           // try{this.panelJ1.setSelected(false);
            //this.panelJ2.setSelected(true);}
           // catch(Exception ex){}
            
            mensaje("Turno de " + jugador2.nombre );
        }
        
            
    }
      
       /*Método que recoje el modelo y nos avisa que está listo.*/
    public void recojerModelo(){
        /*Iniciamos los componentes del juego.*/
        iniciarJuego();
        
    }
    
    /*Método que inicia el juego una vez obtenido el modelo.*/
    public void iniciarJuego(){  //TRABAJARLOCAL ------------ARREGLAR LO DE LA FICHA PREDETERMINADA-------------
        /*Creamos los jugadores según el tipo de juego.*/
        System.out.println("estoo sale "+modelo.tipo_juego);
        System.out.println(HOMBREvsHOMBRE);
        if ( modelo.tipo_juego == HOMBREvsHOMBRE ){
            System.out.println(" pasa el primer filtro");
            this.jugador1 = new Jugador( modelo.nombre1,new ImageView( new Image(getClass().getResourceAsStream("/images/circulo3.png"))) );  //cambiar a pasarle la ficha directamente sin tener la opcion de lelegir la imagen
            this.jugador2 = new Jugador( modelo.nombre2, new ImageView( new Image(getClass().getResourceAsStream("/images/cruz.png"))) ); //x2
            
            /*Mostramos su información, asignamos los nombres de jugador al panel.*/
           
            mostrarInformacion();
        } else {
            /*Jugadores*/
            this.jugador1 = new Jugador( modelo.nombre1, new ImageView( new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
            this.jugador2 = new Jugador ( "Computadora", new ImageView( new Image(getClass().getResourceAsStream("/images/cruz.png"))) );
            this.lblPlayer2.setVisible(false);//verificar
            
            /*Creamos la instancia para la computadora.*/
            computadora = new ComputadoraIA();
            
            /*Mostramos su información, asignamos los nombres de jugador al panel.*/
            mostrarInformacion();
        }
        
        /*Iniciamos el turno en jugador 1*/
        this.turno = 1;
        this.turnoGeneral = JUGADOR1;
        
        /*Variables de juego.*/
        jugando = true;
        terminado = false;
        
        /*Deshabilitamos el menú nuevo juego.*/
        this.mnuIniciar.setDisable(false);
        this.mnuSuspender.setDisable(true);
       // this.panelEstadisticas.setVisible(false);
        
        /*Movemos el foco.*/
        cambiarFoco();
        
    }
    public void mostrarInformacion(){ 
        
        /*Establecemos el título.*/
        lblPlayer.setText(jugador1.nombre );
        lblPlayer2.setText( jugador2.nombre );
        
        /*Establecemos las estadísticas del jugador.*/
        this.lblGanados.setText("Ganados: " + jugador1.GANADOS );
        this.blPerdidos.setText("Perdidos: " + jugador1.PERDIDOS );
        this.lblEmpatados.setText("Empatados: " + jugador1.EMPATADOS );
        this.lblIcono.setGraphic(jugador1.obtenFicha() );
        
        this.lblGanados2.setText("Ganados: " + jugador2.GANADOS );
        this.lblPerdidos2.setText("Perdidos: " + jugador2.PERDIDOS );
        this.lblEmpatados2.setText("Empatados: " + jugador2.EMPATADOS );
        this.lblIcono2.setGraphic(jugador2.obtenFicha() );
        
        this.lblPlayer.setVisible(true); //es la ventada del jugaddor pero por ahora solo vamos a ocultara o mostrarel nombre
        this.lblPlayer.setVisible(true);
       // if( this.modelo.tipo_juego != HOMBREvsCOMPUTADORA)
           // this.lblPlayer2.setVisible(true);// ververver
    }
    /*Método que inicia un nuevo juego.*/
    public void reiniciarJuego(){  //NO SE VE MUY IMPORTANTE PERO HAY QUE TRABAJARLO SI ES QUE SIRVE
        
        //Llenamos el tablero con 0s*/
        Arrays.fill(tablero,0);
        
        /*Borramos los iconos.*/
        for ( int i = 0; i < 9; i ++ )
            fichas[i].setImage(null);
        
        /*Quitamos selecciones.*/  //aun no se como arreglar esto 
//        try{this.panelJ1.setSelected(false);
//        this.panelJ2.setSelected(true);}
//        catch(Exception ex){}
        
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
    public void suspenderJuego(){ ///TRABAJAR LOCAL!!
                
        //Llenamos el tablero con 0s*/
        Arrays.fill(tablero,0);
        
        /*Borramos los iconos.*/
        for ( int i = 0; i < 9; i ++ )
            fichas[i].setImage(null);// cambio realizado a setgraphic
        
        /*Quitamos selecciones.*/ //aun no se como arreglar esto 
//        try{this.panelJ1.setSelected(false);
//        this.panelJ2.setSelected(true);}
//        catch(Exception ex){}
        
        /*Reinciamos el turno.*/
        turno = 1;
        jugando = false;
        terminado = false;
        
        /*Borramos jugadores.*/
        jugador1 = null;
        jugador2 = null;
        
        /*Habilitamos los menús.*/
        this.mnuIniciar.setDisable(true); //cambio realizado disable
        this.mnuSuspender.setDisable(false);//cambio realizado disable
        this.lblEstado.setText("Juega al gato!");
        
        /*Quitamos los paneles.*/
        this.lblPlayer.setVisible(false);
        this.lblPlayer2.setVisible(false);
        
        
        
    }
    
    private void initComponents() {
        f1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                // Agregar una imagen al label cuando se hace clic
//                Image image = new Image("ruta_de_tu_imagen.png");
//                ImageView imageView = new ImageView(image);
//f1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
                f1MouseClicked(); 
            }
        });
        f2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                // Agregar una imagen al label cuando se hace clic
//                Image image = new Image("ruta_de_tu_imagen.png");
//                ImageView imageView = new ImageView(image);
//f2.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
                f2MouseClicked(); 
            }
        });
        f3.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                // Agregar una imagen al label cuando se hace clic
//                Image image = new Image("ruta_de_tu_imagen.png");
//                ImageView imageView = new ImageView(image);
//f3.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
                f3MouseClicked(); 
            }
        });
        f4.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                // Agregar una imagen al label cuando se hace clic
//                Image image = new Image("ruta_de_tu_imagen.png");
//                ImageView imageView = new ImageView(image);
//f1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
                f4MouseClicked(); 
            }
        });
        
        f5.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                // Agregar una imagen al label cuando se hace clic
//                Image image = new Image("ruta_de_tu_imagen.png");
//                ImageView imageView = new ImageView(image);
//f5.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
                f5MouseClicked(); 
            }
        });
        f6.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                // Agregar una imagen al label cuando se hace clic
//                Image image = new Image("ruta_de_tu_imagen.png");
//                ImageView imageView = new ImageView(image);
//f6.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
                f6MouseClicked(); 
            }
        });
        f7.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                // Agregar una imagen al label cuando se hace clic
//                Image image = new Image("ruta_de_tu_imagen.png");
//                ImageView imageView = new ImageView(image);
//f7.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
                f7MouseClicked(); 
            }
        });
        f8.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                // Agregar una imagen al label cuando se hace clic
//                Image image = new Image("ruta_de_tu_imagen.png");
//                ImageView imageView = new ImageView(image);
//f8.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
                f8MouseClicked(); 
            }
        });
        f9.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                // Agregar una imagen al label cuando se hace clic
//                Image image = new Image("ruta_de_tu_imagen.png");
//                ImageView imageView = new ImageView(image);
//f9.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/circulo3.png"))));
                f9MouseClicked(); 
            }
        });
//        f1.setOnMouseClicked((MouseEvent event) -> {
//            f1MouseClicked();  
//        });
//        f2.setOnMouseClicked((MouseEvent event) -> {
//            f2MouseClicked();  
//        });
//        f3.setOnMouseClicked((MouseEvent event) -> {
//            f3MouseClicked();  
//        });
//        f4.setOnMouseClicked((MouseEvent event) -> {
//            f4MouseClicked(); 
//        });
//        f5.setOnMouseClicked((MouseEvent event) -> {
//            f5MouseClicked();  
//        });
//        f6.setOnMouseClicked((MouseEvent event) -> {
//            f6MouseClicked();  
//        });
//        f7.setOnMouseClicked((MouseEvent event) -> {
//            f7MouseClicked();  
//        });
//        f8.setOnMouseClicked((MouseEvent event) -> {
//            f8MouseClicked();  
//        });
//        f9.setOnMouseClicked((MouseEvent event) -> {
//            f9MouseClicked();  
//        });
        //Tablero.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/tablero.png"))));
        
        
        
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
         
         mnuSuspender.setOnAction(event -> mnuSuspenderActionPerformed());
         
         mnuEstadisticas.setOnAction(event -> mnuEstadisticasActionPerformed());
         
         btnconfigurar.setOnAction(event -> configurarActionPerformed());
    }
    private void configurarActionPerformed() {   
        System.out.println("Configurar");
    } 
    private void mnuEstadisticasActionPerformed() {                                                
        this.mostrarEstadisticas();
        System.out.println("Estadisticas");
    } 
    
    private void mnuSuspenderActionPerformed() {       //TRABAJAR                                       
        /*Guardamos records.*/
//        estadisticas.guardarJugador(jugador2);
//        estadisticas.refrescar();
//        estadisticas.guardarJugador(jugador1);
        
        /*Suspendemos.*/
        suspenderJuego();
        System.out.println("susoender");
    } 
    
   private void mnuIniciarActionPerformed() throws IOException {                                           
    // Crear el ModeloController y configurar bidireccionalmente la relación
    ModeloController n = new ModeloController();
    n.setTablaController(this);

    // Cargar el FXML y obtener el controlador asociado
    FXMLLoader loader = new FXMLLoader(getClass().getResource("Modelo.fxml"));
    Parent root = loader.load();

    // Configurar bidireccionalmente
    this.setModeloController(modelo);

    // Crear un nuevo Stage para la ventana del ModeloController
    Stage modeloStage = new Stage();
    Scene scene = new Scene(root, 700, 500);

    // Configurar el Stage con la nueva Scene
    modeloStage.setScene(scene);

    // Mostrar la nueva ventana
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
    public void initialize(URL url, ResourceBundle rb) {
    /*LLenamos nuestro tablero de 0, vacío.*/
    Arrays.fill(tablero, 0);

    /*Iniciamos los componentes de nuestra ventana*/
    iniciarComponentes();
    initComponents();
}
    
}
