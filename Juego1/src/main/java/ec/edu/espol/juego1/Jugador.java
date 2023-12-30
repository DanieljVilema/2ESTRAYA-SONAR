/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.juego1;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author naomi
 */
public class Jugador {
    /*Atributos del jugador.*/
    String nombre;
    public int GANADOS, PERDIDOS, EMPATADOS;
    
    /*Imagen de la ficha del jugador.*/
    private ImageView ficha; //la orginal tiene ImagenIcon esta aun no se si es image o imageview
    
    /** Crea un nuevo Jugador. */
    public Jugador(String nombre, String ruta) {
        
        /*Nombre del jugador.*/
        this.nombre = nombre;
        GANADOS = 0;
        PERDIDOS = 0;
        EMPATADOS = 0;
        
        /*Imagen por defecto.*/
        miFicha( ruta ); //TRABAJAR
    }
    
        
    /** Crea un nuevo Jugador. */
    public Jugador(String nombre, ImageView imagen ) {
        
        /*Nombre del jugador.*/
        this.nombre = nombre;
        GANADOS = 0;
        PERDIDOS = 0;
        EMPATADOS = 0;
        
        /*Imagen por defecto.*/
        this.ficha = imagen;
    }
    
    /*Método que incrementa los juegos ganados.*/
    public void gano(){
        GANADOS ++;
    }
    public void perdio(){
        PERDIDOS ++;
    }
    public void empato(){
        EMPATADOS ++;
    }
    
    /*Método que devuelve la imagen de la ficha.*/
    public ImageView obtenFicha(){
        return ficha;
    }
    /*Método que establace la ficha.*/
    public void miFicha(String ruta){
       this.ficha=new ImageView(new Image(getClass().getResourceAsStream(ruta)));
    }
}
