module ec.edu.espol.juego1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.logging;

    opens ec.edu.espol.juego1 to javafx.fxml;
    exports ec.edu.espol.juego1;
}
