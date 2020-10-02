/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class InicioController implements Initializable {

    @FXML
    private Button btnIniciar;
    @FXML
    private Button bntCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("TodoBien");
        System.out.println("Prueba de Oscar");
    }    

    @FXML
    private void iniciarSesion(ActionEvent event) {
    }

    @FXML
    private void cancelarInicio(ActionEvent event) {
         System.exit(0);
    }
    
}
