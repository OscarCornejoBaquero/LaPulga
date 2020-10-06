
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class CotizacionController implements Initializable {

    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombreCliente;
    @FXML
    private ComboBox<String> cmbTipoProducto;
    @FXML
    private TextField txtPrecioProducto;
    @FXML
    private Button btnAgregarTipo;
    @FXML
    private ComboBox<String> cmbAdicionales;
    @FXML
    private TextField txtPrecioAdicional;
    @FXML
    private Button btnAgregarAdicional;
    @FXML
    private TableView<?> twCotizacion;
    @FXML
    private TableColumn coCodigo;
    @FXML
    private TableColumn coDescripcion;
    @FXML
    private TableColumn coCantidad;
    @FXML
    private TableColumn coPrecioUnitario;
    @FXML
    private TableColumn coSubTotal;
    @FXML
    private TextField txtSubTotal;
    @FXML
    private TextField txtIva;
    @FXML
    private TextField txtDescuento;
    @FXML
    private TextField txtTotalPagar;
    @FXML
    private TextField txtCantidadDescuento;
    @FXML
    private Button btnImprimerCotizacion;
    @FXML
    private Button btnNuevaCotizacion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void consultaCliente(ActionEvent event) {
    }

    @FXML
    private void seleccionProducto(ActionEvent event) {
    }

    @FXML
    private void seleccionAdicional(ActionEvent event) {
    }

    @FXML
    private void generarDescuento(ActionEvent event) {
    }
    
}
