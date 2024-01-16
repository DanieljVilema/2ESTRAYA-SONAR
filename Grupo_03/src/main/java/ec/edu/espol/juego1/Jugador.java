/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.juego1;

/**
 *
 * @author danie
 * @author Dalay20
 * @author RaulLeon
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Jugador {
    String nombre;
    public int GANADOS, PERDIDOS, EMPATADOS;
    private ImageView ficha; 
    
    public Jugador(String nombre, String ruta) {
        this.nombre = nombre;
        GANADOS = 0;
        PERDIDOS = 0;
        EMPATADOS = 0;
        miFicha( ruta ); 
    }
    
    public Jugador(String nombre, ImageView imagen ) {
        this.nombre = nombre;
        GANADOS = 0;
        PERDIDOS = 0;
        EMPATADOS = 0;
        this.ficha = imagen;
    }
    public void gano(){
        GANADOS ++;
    }
    public void perdio(){
        PERDIDOS ++;
    }
    public void empato(){
        EMPATADOS ++;
    }
    public ImageView obtenFicha(){
        return ficha;
    }
    public void miFicha(String ruta){
       this.ficha=new ImageView(new Image(getClass().getResourceAsStream(ruta)));
    }
}
