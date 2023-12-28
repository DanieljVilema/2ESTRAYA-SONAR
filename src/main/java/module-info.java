module ec.edu.espol.estructura {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.estructura to javafx.fxml;
    exports ec.edu.espol.estructura;
}
