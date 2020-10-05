package Controladores;
import BaseDatos.Autentificacion;
import BaseDatos.Conexion;
import Objetos.Usuarios;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
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
    Conexion conexion = new Conexion();
    Usuarios usuarios;
    Autentificacion autentificacion = new Autentificacion();
    public String datos;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    //Boton de Inicias Sesion @OscarCornejo
    @FXML
    private void iniciarSesion(ActionEvent event) {
        //Acciones del boton Inicio @OscarCornejo
        String usuario, pass;
        usuario = txtUsuario.getText();
        pass = txtPass.getText();
        
        //Condicion si el usuario y contraseña estan vacios @OscarCornejo
        if (usuario.equals("") && pass.equals("")) {
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
            txtUsuario.setText("");
            txtPass.setText("");
        } else {
            //Aqui se debe de declarar las opciones de coneccion de base de datos @OscarCornejo 
            usuarios=autentificacion.autentificar(usuario, pass);
            if (usuarios.isEstado()) {
                try {
                    alert = alerta.alerta("LOGIN", "ACCESO CORRECTO", "Bienvenido: " +usuarios.getNombre() + " " +usuarios.getApellido(), "INFORMATION");
                    stage = (Stage) alert.getDialogPane().getScene().getWindow();
                    stage.getIcons().add(new Image("Imagenes/llave.png"));
                    alert.showAndWait();
                    txtUsuario.requestFocus();
                    txtUsuario.setText("");
                    txtPass.setText("");
                    datos = (usuarios.getNombre()+" "+ usuarios.getApellido());
                    autentificacion.usuarioActivo(usuarios.getNombre(), usuarios.getApellido(), usuarios.getCorreo());
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistas/Principal.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    stage = stages.nuevoStage(root1, "Lavadero de Perros ->La Pulga Limpia<- Usuario Activo: "+datos, "/Imagenes/iconoLaPulga.png");
                    stage.initOwner(((Node)event.getSource()).getScene().getWindow());
                    stage.show();
                    
                    System.out.println(datos);
                } catch (IOException ex) {
                    Logger.getLogger(InicioController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                //Caso contrario si el usuario y contraseña son incorrectos @OscarCornejo
                alert = alerta.alerta("LOGIN", "ACCESO DENEGADO", "USUARIO O CONTRASEÑA INCORRECTOS", "ERROR");
                stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("Imagenes/llave.png"));
                alert.show();
                txtUsuario.requestFocus();
                txtUsuario.setText("");
                txtPass.setText("");
            }

        }
    }

    @FXML
    private void cancelarInicio(ActionEvent event) {
         System.exit(0);
    }
 

    
}
