package Controladores;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InicioController implements Initializable {
    //Declaracion de las diferentes variables de los stages
    
    @FXML
    private Button btnIniciar;
    @FXML
    private Button bntCancelar;
    @FXML
    private TextField txtUsuario;
    @FXML
    private PasswordField txtPass;
    private Alert alert = new Alert(Alert.AlertType.NONE);
    Alertas alerta = new Alertas();
    Stage stage = new Stage();
    Stages stages = new Stages();
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Prueba del GITHUB
        System.out.println("TodoBien");
        System.out.println("Prueba de Oscar");
    }    

    //Boton de Inicias Sesion @OscarCornejo
    @FXML
    private void iniciarSesion(ActionEvent event) {
        //Acciones del boton Inicio @OscarCornejo
        String usuario,pass;
        usuario=txtUsuario.getText();
        pass=txtPass.getText();
        
        //Condicion si el usuario y contraseña estan vacios @OscarCornejo
        if(usuario.equals("")&&pass.equals("")){
         alert = alerta.alerta("ERROR", "HA OCURRIDO UN ERROR CRITICO", "Informacion sobre el Error", "ERROR");
            stage = (Stage) alert.getDialogPane().getScene().getWindow();
            String error = "NO INGRESO DATOS EN USUARIO Y CONTRASEÑA";
            Label label2 = new Label(error);
            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label2, 0, 1);
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
            txtUsuario.requestFocus();
        }else{
        //Aqui se debe de declarar las opciones de coneccion de base de datos @OscarCornejo 
        
        
            if(usuario.equals(pass)){
               alert = alerta.alerta("LOGIN", "ACCESO CORRECTO", "Bienvenido: " + " " + " " + " ", "INFORMATION");
                    stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("Imagenes/llave.png"));
                    alert.showAndWait();
               
               
        //Caso contrario si el usuario y contraseña son incorrectos @OscarCornejo
           }else{
               alert = alerta.alerta("LOGIN", "ACCESO DENEGADO", "USUARIO O CONTRASEÑA INCORRECTOS", "ERROR");
                    stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("Imagenes/llave.png"));
                    alert.show();
           }
               
        }
    }

    @FXML
    private void cancelarInicio(ActionEvent event) {
         System.exit(0);
    }
    
}
