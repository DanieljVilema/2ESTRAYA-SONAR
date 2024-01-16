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
public class ComputadoraIA {

    public static int tiradas = 0;

    class NodoG {

        int mejorMovimiento;
        NodoG nodos[];
        int tablero[];
        boolean miTurno = false;
        int indice;
        int utilidad;
        int ganador = 0;

        NodoG() {
            tablero = new int[9];
        }
    }

    NodoG arbol = new NodoG();
    int[] tablero;

    public final int miFICHA = 2;

    public int movDisponibles(int[] tablero) {
        int mov = 0;
        for (int i = 0; i < 9; i++) {
            if (tablero[i] == 0) {
                mov++;
            }
        }
        return mov;
    }

    public int[] posVacias(int[] tablero) {
        int[] indices = new int[movDisponibles(tablero)];
        int indice = 0;
        for (int i = 0; i < 9; i++) {
            if (tablero[i] == 0) {
                indices[indice] = i;
                indice++;
            }
        }
        return indices;
    }

    public int movimiento(int[] tablero) {
        this.tablero = tablero;
        tiradas++;
        for (int i = 0; i < 9; i++) {
            this.arbol.tablero[i] = this.tablero[i];
        }
        movComputadora(arbol, 0); 
        return arbol.mejorMovimiento;
    }

    public void movComputadora(NodoG raiz, int profundidad) {
        int movimientos = movDisponibles(raiz.tablero);
        int indices[] = posVacias(raiz.tablero);

        raiz.nodos = new NodoG[movimientos];

        int ganador = terminado(raiz.tablero);
        if (ganador == 1) {
            ganador = -1;
        } else if (ganador == 2) {
            ganador = 1;
        }

        if (ganador != 0 || movimientos == 0 || profundidad >= 2) {
            raiz.ganador = ganador;
            raiz.utilidad = calcularUtilidad(raiz.tablero);
        } else {
            for (int i = 0; i < movimientos; i++) {
                raiz.nodos[i] = new NodoG();
                for (int j = 0; j < 9; j++) {
                    raiz.nodos[i].tablero[j] = raiz.tablero[j];
                }

                raiz.nodos[i].tablero[indices[i]] = raiz.miTurno ? 1 : 2;
                raiz.nodos[i].miTurno = !raiz.miTurno;
                raiz.nodos[i].indice = indices[i];

                movComputadora(raiz.nodos[i], profundidad + 1);
            }

            if (!raiz.miTurno) {
                raiz.utilidad = Max(raiz);
            } else {
                raiz.utilidad = Min(raiz);
            }
        }

        if (profundidad == 0) {
            raiz.mejorMovimiento = raiz.nodos[obtenerIndiceMejorMovimiento(raiz)].indice;
        }
    }

    public int Max(NodoG raiz) {
        int Max = Integer.MIN_VALUE;
        for (int i = 0; i < raiz.nodos.length; i++) {
            int utilidadHijo = raiz.nodos[i].utilidad;
            if (utilidadHijo > Max) {
                Max = utilidadHijo;
            }
        }
        return Max;
    }

    public int Min(NodoG raiz) {
        int Min = Integer.MAX_VALUE;
        for (int i = 0; i < raiz.nodos.length; i++) {
            int utilidadHijo = raiz.nodos[i].utilidad;
            if (utilidadHijo < Min) {
                Min = utilidadHijo;
            }
        }
        return Min;
    }

    private int obtenerIndiceMejorMovimiento(NodoG raiz) {
        int mejorUtilidad = Integer.MIN_VALUE;
        int indiceMejorMovimiento = 0;

        for (int i = 0; i < raiz.nodos.length; i++) {
            int utilidadHijo = raiz.nodos[i].utilidad;
            if (utilidadHijo > mejorUtilidad) {
                mejorUtilidad = utilidadHijo;
                indiceMejorMovimiento = i;
            }
        }

        return indiceMejorMovimiento;
    }

    public int terminado(int[] tablero) {
        // para las filas
        if (tablero[0] == tablero[1] && tablero[0] == tablero[2] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[3] == tablero[4] && tablero[3] == tablero[5] && tablero[3] != 0) {
            return tablero[3];
        } else if (tablero[6] == tablero[7] && tablero[6] == tablero[8] && tablero[6] != 0) {
            return tablero[6];
        } //columnas
        else if (tablero[0] == tablero[3] && tablero[0] == tablero[6] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[1] == tablero[4] && tablero[1] == tablero[7] && tablero[1] != 0) {
            return tablero[1];
        } else if (tablero[2] == tablero[5] && tablero[2] == tablero[8] && tablero[2] != 0) {
            return tablero[2];
        } //diagonales
        else if (tablero[0] == tablero[4] && tablero[0] == tablero[8] && tablero[0] != 0) {
            return tablero[0];
        } else if (tablero[2] == tablero[4] && tablero[2] == tablero[6] && tablero[2] != 0) {
            return tablero[2];
        }

        if (movDisponibles(tablero) == 0) {
            return -1; // empate
        }

        return 0; // el juego no termina
    }

    private int calcularUtilidad(int[] tablero) {
        int Pjugador = contarJugadasGanadoras(tablero, miFICHA);
        int Poponente = contarJugadasGanadoras(tablero, miFICHA == 1 ? 2 : 1);
        return Pjugador - Poponente;
    }

    private int contarJugadasGanadoras(int[] tablero, int jugador) {
        int count = 0;

        // Filas
        count += (tablero[0] == jugador && tablero[1] == jugador && tablero[2] == jugador) ? 1 : 0;
        count += (tablero[3] == jugador && tablero[4] == jugador && tablero[5] == jugador) ? 1 : 0;
        count += (tablero[6] == jugador && tablero[7] == jugador && tablero[8] == jugador) ? 1 : 0;

        // Columnas
        count += (tablero[0] == jugador && tablero[3] == jugador && tablero[6] == jugador) ? 1 : 0;
        count += (tablero[1] == jugador && tablero[4] == jugador && tablero[7] == jugador) ? 1 : 0;
        count += (tablero[2] == jugador && tablero[5] == jugador && tablero[8] == jugador) ? 1 : 0;

        // Diagonales
        count += (tablero[0] == jugador && tablero[4] == jugador && tablero[8] == jugador) ? 1 : 0;
        count += (tablero[2] == jugador && tablero[4] == jugador && tablero[6] == jugador) ? 1 : 0;

        return count;
    }

    public boolean puedoGanar(int[] tablero) {
        return terminado(tablero) == 2;
    }

    public boolean puedoPerder(int[] tablero) {
        return terminado(tablero) == 1;
    }

    public void imprime(int[] vector) {
        for (int i = 0; i < 9; i++) {
            System.out.print(vector[i] + " ");
            if (i == 2 || i == 5) {
                System.out.println();
            }
        }
        System.out.println("\r\n");
    }
}
