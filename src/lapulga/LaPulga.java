/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapulga;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author oscar
 */
public class LaPulga extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
    stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(LaPulga.class.getResource("/Vistas/Inicio.fxml"));
            Pane ventana = (Pane) loader.load();
            Scene scene = new Scene(ventana);

            stage.setScene(scene);
            stage.getIcons().add(new Image("/Imagenes/iconoLaPulga.png"));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
