package ec.edu.espol.juego1;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        scene = new Scene(loadFXML("Modelo").load(), 700, 500);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }

    public static void setScene(Scene sc) {
        primaryStage.setScene(sc);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static FXMLLoader loadFXML(String fxml) {
        return new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    }

    public static void main(String[] args) {
        launch();
    }
}
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import static javafx.application.Application.launch;
//
///**
// * JavaFX App
// */
//public class App extends Application {
//
//    private static Scene scene;
//    private static Stage st;
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        st = stage;
//        scene = new Scene(loadFXML("Tabla").load(),700,500);
//        stage.setResizable(false);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml).load());
//    }
//    
//    public static void setScene(Scene sc) throws IOException {
//        st.setScene(sc);
//    }
//    
//    public static Stage getStage(){
//        return st;
//    }
//
//    public static FXMLLoader loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader;
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//
//}