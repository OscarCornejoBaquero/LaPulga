
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class AdministracionController implements Initializable {

    @FXML
    private TextField txtCedulaEmpleado;
    @FXML
    private TextField txtNombreEmpleado;
    @FXML
    private TextField txtCorreoEmpleado;
    @FXML
    private TextField txtTelefonoEmpleado;
    @FXML
    private Button btnGuardar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void guardarEmpleado(ActionEvent event) {
    }
    
}
