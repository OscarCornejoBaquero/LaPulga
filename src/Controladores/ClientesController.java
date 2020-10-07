package Controladores;

import BaseDatos.Autentificacion;
import BaseDatos.ClienteBD;
import Objetos.Clientes;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ClientesController implements Initializable {

    //Codigo General para todas las pestañas 
    Autentificacion autentificacion = new Autentificacion();
    Usuarios usuarios;
    private Alert alert = new Alert(Alert.AlertType.NONE);
    Alertas alerta = new Alertas();
    Stage stage = new Stage();
    Stages stages = new Stages();

    //Codigo para Pestaña Clientes
    private ClienteBD clienteBD = new ClienteBD();
    Clientes clientes;
    private ObservableList<String> tipoClientes;
    private ObservableList<Clientes> clientesOL;
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
    private TableView<Clientes> tblClientes;
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
     @FXML
    private Button btnActivarTxt;

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
    @FXML
    private TabPane tabGeneral;
    @FXML
    private Button btnNuevoMascota;
    @FXML
    private TextField txtTelefonoConvencional;
    @FXML
    private TableColumn colConvencional;
   
    //Inicio de la ventana
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarios = autentificacion.datosInicio();
        rClientes.setGraphic(buildImage("/Imagenes/btnClientes.png"));
        rMascotas.setGraphic(buildImage("/Imagenes/iconoLaPulga.png"));
        comboCliente();
        inicioControles();
        inicializarTablaClientes();
        rellenarTabla();
        inicioMascotas();
        seleccionarItemTablaClientes();
        txtCedula.setOnKeyTyped(event -> SoloNumerosEnteros(event));
        txtTelefono.setOnKeyTyped(event -> SoloNumerosEnteros(event));
        txtTelefonoConvencional.setOnKeyTyped(event -> SoloNumerosEnteros(event));
        txtCedulaConsulta.setOnKeyTyped(event -> SoloNumerosEnteros(event));
        
        
    }
    //Para Agregar la imagenen a la paleta

    private static ImageView buildImage(String imgPatch) {
        Image i = new Image(imgPatch);
        ImageView imageView = new ImageView();
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setImage(i);
        return imageView;
    }
    //Seccion para Iniciar controles de Clientes

    private void inicioControles() {
        reutilizarControles(true, true, true, true, true, true, true, true, true,true);
    }

    private void reutilizarControles(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean g, boolean h, boolean i, boolean j) {
        txtCedula.setDisable(a);
        txtNombre.setDisable(b);
        txtApellido.setDisable(c);
        txtTelefono.setDisable(d);
        txtDireccion.setDisable(e);
        txtCorreo.setDisable(f);
        btnActualizarCliente.setDisable(g);
        btnNuevoCliente.setDisable(h);
        txtTelefonoConvencional.setDisable(i);
        btnActivarTxt.setDisable(j);
    }

    private void rellenarTabla() {
        ObservableList<Clientes> obs = null;
        clienteBD = new ClienteBD();
        obs = clienteBD.consulta();
        this.tblClientes.setItems(obs);
    }
    
public void SoloNumerosEnteros(KeyEvent keyEvent) {
    try{
        char key = keyEvent.getCharacter().charAt(0);

        if (!Character.isDigit(key))
            keyEvent.consume();

    } catch (Exception ex){ }
}



    private void inicializarTablaClientes() {
        clientesOL = FXCollections.observableArrayList();
        this.colCedula.setCellValueFactory(new PropertyValueFactory<Clientes, String>("cedula"));
        this.colNombres.setCellValueFactory(new PropertyValueFactory<Clientes, String>("nombre"));
        this.colApellidos.setCellValueFactory(new PropertyValueFactory<Clientes, String>("apellido"));
        this.colDireccion.setCellValueFactory(new PropertyValueFactory<Clientes, String>("domicilio"));
        this.colTelefono.setCellValueFactory(new PropertyValueFactory<Clientes, String>("telefono"));
        this.colConvencional.setCellValueFactory(new PropertyValueFactory<Clientes, String>("convencional"));
        this.colCorreo.setCellValueFactory(new PropertyValueFactory<Clientes, String>("correo"));
        this.tblClientes.setItems(clientesOL);
    }
    public void seleccionarItemTablaClientes() {
        tblClientes.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (tblClientes.getSelectionModel().getSelectedItem() != null) {
                    Clientes clientes = tblClientes.getSelectionModel().getSelectedItem();
                    txtCedula.setDisable(true);
                    txtCedula.setText(clientes.getCedula());
                    txtNombre.setText(clientes.getNombre());
                    txtApellido.setText(clientes.getApellido());
                    txtTelefono.setText(clientes.getTelefono());
                    txtTelefonoConvencional.setText(clientes.getConvencional());
                    txtDireccion.setText(clientes.getDomicilio());
                    txtCorreo.setText(clientes.getCorreo());
                    
                    btnNuevaMascota.setDisable(false); 
                    reutilizarControles(true, true, true, true, true, true, true, true, true, false);
                }
            }
        });
    }

    private void comboCliente() {
        tipoClientes = FXCollections.observableArrayList();
        tipoClientes.add(" ");
        tipoClientes.add("Cliente Existente");
        tipoClientes.add("Cliente Nuevo");
        this.cmbEstado.setItems(tipoClientes);
    }

    //Acciones de botones y de textos
    @FXML
    private void seleccionarTipo(ActionEvent event) {
        if (cmbEstado.getSelectionModel().getSelectedIndex() == 1) {

            reutilizarControles(false, true, true, true, true, true, true, true, true,false);
            btnNuevaMascota.setDisable(false);

        }
        if (cmbEstado.getSelectionModel().getSelectedIndex() == 2) {

            reutilizarControles(false, false, false, false, false, false, true, false, false,true);
           btnNuevaMascota.setDisable(false);
        }
        if (cmbEstado.getSelectionModel().getSelectedIndex() == 0) {
            inicioControles();
        }
    }

    @FXML
    private void consultarCliente(ActionEvent event) {
        clientes = clienteBD.consultarCliente(txtCedula.getText());
        System.out.println("" + clientes.getNombre());
        txtNombre.setText(clientes.getNombre());
        txtApellido.setText(clientes.getApellido());
        txtTelefonoConvencional.setText(clientes.getConvencional());
        txtTelefono.setText(clientes.getTelefono());
        txtDireccion.setText(clientes.getDomicilio());
        txtCorreo.setText(clientes.getCorreo());
    }

    @FXML
    private void actualizarCliente(ActionEvent event) {
        
        clienteBD.modificarCliente(txtCedula.getText(), txtNombre.getText(), txtApellido.getText(),
                txtTelefonoConvencional.getText(), txtTelefono.getText(), txtDireccion.getText(), txtCorreo.getText());
          rellenarTabla();
    }

    @FXML
    private void agregarCliente(ActionEvent event) {
        boolean resp;
        resp = clienteBD.insertarCliente(txtCedula.getText(), txtNombre.getText(), txtApellido.getText(),
                txtTelefonoConvencional.getText(), txtTelefono.getText(), txtDireccion.getText(), txtCorreo.getText());
        if (resp) {
            
            alert = alerta.alerta("Cliente ", null, "Cliente Agregado Correctamente", "INFORMATION");
                alert.getDialogPane().getScene().getWindow();
                ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
                alert.showAndWait();
            
            
        } else {
            alert = alerta.alerta("Cliente ", null, "Error al Guardar Cliente", "ERROR");
                alert.getDialogPane().getScene().getWindow();
                ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
                alert.showAndWait();
            
        }
            rellenarTabla();
    }

    @FXML
    private void eliminarCliente(ActionEvent event) {
        clienteBD.eliminarCliente(txtCedula.getText());
        alert = alerta.alerta("Cliente ", null, "Cliente Eliminado Correctamente", "INFORMATION");
                alert.getDialogPane().getScene().getWindow();
                ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
                alert.showAndWait();
        rellenarTabla();
        
    }

    @FXML
    private void agregarMascotaTAB(ActionEvent event) {
        tabGeneral.getSelectionModel().select(rMascotas);
        txtCedulaConsulta.setText(txtCedula.getText());
     
    }

    @FXML
    private void limpiarPantalla(ActionEvent event) {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        txtTelefonoConvencional.setText("");
        
    }
    
      @FXML
    private void editarCliente(ActionEvent event) {
        
                  alert = alerta.alerta("Cliente ", null, "Se va a Proceder a Editar el Cliente", "CONFIRMATION");
                alert.getDialogPane().getScene().getWindow();
                ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
                alert.showAndWait();
          reutilizarControles(false, false, false, false, false, false, false, true, false, false);
    }

    /*
    Esta seccion de aqui en adelante es para la pestaña de mascotas
    
     */
    private void inicioMascotas() {
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
