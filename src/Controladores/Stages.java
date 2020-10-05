
package Controladores;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Stages {

    private Stage stage;
    
    public Stage nuevoStage(Parent root1,String titulo,String imgURL){
        stage = new Stage();
        stage.setScene(new Scene(root1));  
                   stage.setTitle(titulo);
                   stage.getIcons().add(new Image(imgURL));
                   stage.initModality(Modality.WINDOW_MODAL); 
                   stage.setResizable(false);
                   
        return stage;
        
        
        
    }
}
