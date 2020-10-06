
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class LavadosController implements Initializable {

    @FXML
    private TextField txtCodigoLavado;
    @FXML
    private TextField txtCedulaCliente;
    @FXML
    private ComboBox<?> cmbMascota;
    @FXML
    private DatePicker fechaLavado;
    @FXML
    private ComboBox<String> cmbHora;
    @FXML
    private TextField codigoBatea;
    @FXML
    private CheckBox cheJabon;
    @FXML
    private CheckBox cheShampoo;
    @FXML
    private CheckBox chePerfume;
    @FXML
    private CheckBox cheTratamiento;
    @FXML
    private CheckBox cheAniparasitarios;
    @FXML
    private CheckBox cheAcondicionador;
    @FXML
    private ComboBox<?> cmbEmpleado;
    @FXML
    private Button btnAgendar;
    @FXML
    private Button btnNuevoLavado;
    @FXML
    private TableView<?> twAgendamientoLavado;
    @FXML
    private TableColumn coCodLavado;
    @FXML
    private TableColumn coCliente;
    @FXML
    private TableColumn coMascota;
    @FXML
    private TableColumn coFechaLavado;
    @FXML
    private TableColumn coHoraLavado;
    @FXML
    private TableColumn coProductos;
    @FXML
    private TableColumn coEmpleado;
    @FXML
    private Button btnEliminarLavado;
    @FXML
    private Button btnModificarLavado;
    @FXML
    private TextField txtCodigoCLiente;
    @FXML
    private DatePicker fechaDesde;
    @FXML
    private DatePicker fechaHasta;
    @FXML
    private Button btnConsultarLavadoFecha;
    @FXML
    private TableView<?> twLavadosmascotas;
    @FXML
    private TableColumn coLavadoGeneral;
    @FXML
    private TableColumn coCLienteGeneral;
    @FXML
    private TableColumn coMascotaGeneral;
    @FXML
    private TableColumn coFechaGeneral;
    @FXML
    private TableColumn coHoraGeneral;
    @FXML
    private TableColumn coProductosGeneral;
    @FXML
    private TableColumn coEmpleadosGeneral;

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void consultaCliente(ActionEvent event) {
    }

    @FXML
    private void agendarLavado(ActionEvent event) {
    }

    @FXML
    private void nuevoLavado(ActionEvent event) {
    }

    @FXML
    private void eliminarLavado(ActionEvent event) {
    }

    @FXML
    private void modificarLavado(ActionEvent event) {
    }

    @FXML
    private void consultaLavadorCliente(ActionEvent event) {
    }
    
}
