/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import BaseDatos.Autentificacion;
import Objetos.Usuarios;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {

    @FXML
    private Label hora;
    Autentificacion autentificacion = new Autentificacion();
    Usuarios usuarios;
    private Alert alert = new Alert(Alert.AlertType.NONE);
    Alertas alerta = new Alertas();
    Stage stage = new Stage();
    Stages stages = new Stages();
    @FXML
    private Label email;
    @FXML
    private ImageView btnClientes;
    @FXML
    private ImageView btnLavados;
    @FXML
    private ImageView btnFactura;
    @FXML
    private ImageView btnCotizacion;
    @FXML
    private ImageView btnReportes;
    @FXML
    private ImageView btnAdministracion;
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        hora.setText(dateFormat.format(date));
        usuarios = autentificacion.datosInicio();
        email.setText(usuarios.getCorreo());
    }    

    @FXML
    private void accionClientes(MouseEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Vistas/Clientes.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = stages.nuevoStage(root1, "Lavadero de Perros ->La Pulga Limpia<- Usuario Activo: "+usuarios.getNombre()+" "+usuarios.getApellido(), "/Imagenes/iconoLaPulga.png");
            stage.initOwner(((Node)event.getSource()).getScene().getWindow());
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void accionLavados(MouseEvent event) {
    }

    @FXML
    private void accionFacturas(MouseEvent event) {
    }

    @FXML
    private void accionCotizacion(MouseEvent event) {
    }

    @FXML
    private void accionReportes(MouseEvent event) {
    }

    @FXML
    private void accionAdministracion(MouseEvent event) {
    }
    
    
}
