package Controladores;

import BaseDatos.Autentificacion;
import Objetos.Usuarios;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ClientesController implements Initializable {

    Autentificacion autentificacion = new Autentificacion();
    Usuarios usuarios;
    private ObservableList<String> tipoClientes;
    @FXML
    private Tab rClientes;
    @FXML
    private Tab rMascotas;
    @FXML
    private ComboBox<String> cmbEstado;
    @FXML
    private TextField txtCedula;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtCorreo;
    @FXML
    private Button btnActualizarCliente;
    @FXML
    private Button btnNuevoCliente;
    @FXML
    private TableView<?> tblClientes;
    @FXML
    private TableColumn colCedula;
    @FXML
    private TableColumn colNombres;
    @FXML
    private TableColumn colApellidos;
    @FXML
    private TableColumn colDireccion;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;
//Seccion de la pestaña mascotas
    @FXML
    private TextField txtCedulaConsulta;
    @FXML
    private TextField txtNombreMascota;
    @FXML
    private TextField txtDescripcionMascota;
    @FXML
    private DatePicker txtAnio;
    @FXML
    private Button btnActualizarMascota;
    @FXML
    private Button btnEliminarMascota;
    @FXML
    private Button btnLimpiarPantalla2;
    @FXML
    private Button btnNuevaMascota;
     private Alert alert = new Alert(Alert.AlertType.NONE);
    Alertas alerta = new Alertas();
    Stage stage = new Stage();
    Stages stages = new Stages();
    
    //Inicio de la ventana
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarios = autentificacion.datosInicio();
        rClientes.setGraphic(buildImage("/Imagenes/btnClientes.png"));
        rMascotas.setGraphic(buildImage("/Imagenes/iconoLaPulga.png"));
        comboCliente();
        inicioControles();
        inicioMascotas();
    }

    private void inicioControles() {
        txtCedula.setDisable(true);
        txtNombre.setDisable(true);
        txtApellido.setDisable(true);
        txtTelefono.setDisable(true);
        txtDireccion.setDisable(true);
        txtCorreo.setDisable(true);
        btnActualizarCliente.setDisable(true);
        btnNuevoCliente.setDisable(true);
    }

    private void comboCliente() {
        tipoClientes = FXCollections.observableArrayList();
        tipoClientes.add(" ");
        tipoClientes.add("Cliente Existente");
        tipoClientes.add("Cliente Nuevo");
        this.cmbEstado.setItems(tipoClientes);
    }

    @FXML
    private void seleccionarTipo(ActionEvent event) {
        if (cmbEstado.getSelectionModel().getSelectedIndex() == 1) {
            txtCedula.setDisable(false);
            txtNombre.setDisable(true);
            txtApellido.setDisable(true);
            txtTelefono.setDisable(true);
            txtDireccion.setDisable(true);
            txtCorreo.setDisable(true);
            btnActualizarCliente.setDisable(false);
            btnNuevoCliente.setDisable(true);
        }
        if (cmbEstado.getSelectionModel().getSelectedIndex() == 2) {
            txtCedula.setDisable(false);
            txtNombre.setDisable(false);
            txtApellido.setDisable(false);
            txtTelefono.setDisable(false);
            txtDireccion.setDisable(false);
            txtCorreo.setDisable(false);
            btnActualizarCliente.setDisable(true);
            btnNuevoCliente.setDisable(false);
        } 
        if(cmbEstado.getSelectionModel().getSelectedIndex() == 0){
            inicioControles();
        }
    }

    //Para Agregar la imagenen a la paleta
    private static ImageView buildImage(String imgPatch) {
        Image i = new Image(imgPatch);
        ImageView imageView = new ImageView();
        //You can set width and height 
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setImage(i);
        return imageView;
    }

    @FXML
    private void consultarCliente(ActionEvent event) {
    }

    @FXML
    private void actualizarCliente(ActionEvent event) {
    }

    @FXML
    private void agregarCliente(ActionEvent event) {
    }

    @FXML
    private void eliminarCliente(ActionEvent event) {
    }

    @FXML
    private void limpiarPantalla(ActionEvent event) {
    }
    /*
    Esta seccion de aqui en adelante es para la pestaña de mascotas
    
    */
    private void inicioMascotas(){
        txtCedulaConsulta.setDisable(false);
        txtNombreMascota.setDisable(true);
        txtDescripcionMascota.setDisable(true);
        txtAnio.setDisable(true);
        btnActualizarMascota.setDisable(true);
        btnNuevaMascota.setDisable(true);
        
    }
    
    

    @FXML
    private void consultaMascotaClientes(ActionEvent event) {
        System.out.println(txtCedulaConsulta.getText());
        if (txtCedulaConsulta.getText().equals("1234")) {
            //Codigo de consulta de la base de datos

        } else {
            alert = alerta.alerta("MASCOTAS", "NO EXISTEN MASCOTAS PARA EL CLIENTE", "Por favor ingrese los datos de la nueva mascota", "INFORMATION");
            stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("Imagenes/llave.png"));
            alert.showAndWait();
            txtNombreMascota.setDisable(false);
            txtDescripcionMascota.setDisable(false);
            txtAnio.setDisable(false);
            btnActualizarMascota.setDisable(true);
            btnNuevaMascota.setDisable(false);
        }
    }

    @FXML
    private void actualizarDatosMascotas(ActionEvent event) {
    }

    @FXML
    private void nuevaMascota(ActionEvent event) {
    }

    @FXML
    private void eliminarMascota(ActionEvent event) {
    }

    @FXML
    private void limpiarVentanaMascotas(ActionEvent event) {
    }
    
    
    
}
