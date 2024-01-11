/**********************************************************************/
/* Autor: Francisco I. Leyva
 * P�gina web: http://www.panchosoft.com
 * Correo electr�nico: yagami_2@hotmail.com
 *
 * Programa que permite jugar al tres en raya, gato, o tic tac toe contra otra
 * persona o contra la m�quina. Implementando el algoritmo minimax, �rboles,
 * recursi�n, etc.
 *
/**********************************************************************/
package gato;
import class2.Gato;
import javax.swing.*;
public class Main {

    public Main() {
    }
    
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        
        /*Creamos una nueva instancia de nuestro gato.*/
        new Gato();
    }
    
}
