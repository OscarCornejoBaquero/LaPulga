
package Controladores;

import BaseDatos.Autentificacion;
import BaseDatos.EmpleadosBD;
import BaseDatos.PerroBD;
import Objetos.Clientes;
import Objetos.Empleado;
import Objetos.Mascota;
import Objetos.Usuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class AdministracionController implements Initializable {
   Autentificacion autentificacion = new Autentificacion();
    Usuarios usuarios;
    private Alert alert = new Alert(Alert.AlertType.NONE);
    Alertas alerta = new Alertas();
    Stage stage = new Stage();
    Stages stages = new Stages();
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
 private ObservableList<Empleado> EmpleadosOL;
    EmpleadosBD empleadosBD = new EmpleadosBD();
    @FXML
    private TextField txtEstado;
    @FXML
    private TableView<Empleado> twEmpleados;
    private TableColumn coID;
    @FXML
    private TableColumn coCedula;
    @FXML
    private TableColumn coNombres;
    @FXML
    private TableColumn coEmail;
    @FXML
    private TableColumn coTelefono;
    @FXML
    private TableColumn coEstado;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        inicializarTablaEmpleado();
        seleccionarItemTablaEmpleados();
         rellenarTablaEmpleado();
    }    
private void inicializarTablaEmpleado() {
        EmpleadosOL = FXCollections.observableArrayList();
        
        this.coCedula.setCellValueFactory(new PropertyValueFactory<Empleado, String>("idEmpleado"));
        this.coNombres.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        this.coEmail.setCellValueFactory(new PropertyValueFactory<Empleado, String>("email"));
        this.coEstado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("estado"));
        this.coTelefono.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telefono"));
        
        this.twEmpleados.setItems(EmpleadosOL);
    }
public void seleccionarItemTablaEmpleados() {
        twEmpleados.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (twEmpleados.getSelectionModel().getSelectedItem() != null) {
                    Empleado empleado = twEmpleados.getSelectionModel().getSelectedItem();
                    txtCedulaEmpleado.setText(empleado.getIdEmpleado());
                    txtNombreEmpleado.setText(empleado.getNombre());
                    txtCorreoEmpleado.setText(empleado.getEmail());
                    txtEstado.setText(empleado.getEstado());
                    txtTelefonoEmpleado.setText(empleado.getTelefono());
                 }
            }
        });
    }
    @FXML
    private void guardarEmpleado(ActionEvent event) {
        boolean resp;
        resp=empleadosBD.insertarEmpleado(txtCedulaEmpleado.getText(), txtNombreEmpleado.getText(), txtCorreoEmpleado.getText(), txtEstado.getText(), txtTelefonoEmpleado.getText());
    
         if (resp) {

            alert = alerta.alerta("Cliente ", null, "Empleado Agregado Correctamente", "INFORMATION");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();

        } else {
            alert = alerta.alerta("Cliente ", null, "Error al Guardar Empleado", "ERROR");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();

        }
         rellenarTablaEmpleado();
        
    }
    private void rellenarTablaEmpleado() {
        ObservableList<Empleado> obs = null;
        empleadosBD = new EmpleadosBD();
        obs = empleadosBD.consulta();
        this.twEmpleados.setItems(obs);
    }
    
}
