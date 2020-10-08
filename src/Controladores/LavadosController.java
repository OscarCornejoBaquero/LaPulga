
package Controladores;

import BaseDatos.Autentificacion;
import BaseDatos.ClienteBD;
import BaseDatos.LavadosBD;
import BaseDatos.PerroBD;
import Objetos.Clientes;
import Objetos.Lavados;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class LavadosController implements Initializable {
   Autentificacion autentificacion = new Autentificacion();
    Usuarios usuarios;
    private Alert alert = new Alert(Alert.AlertType.NONE);
    Alertas alerta = new Alertas();
    Stage stage = new Stage();
    Stages stages = new Stages();
    @FXML
    private TextField txtCedulaCliente;
    @FXML
    private ComboBox<String> cmbMascota;
    @FXML
    private DatePicker fechaLavado;
    @FXML
    private ComboBox<String> cmbHora;
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
    private ComboBox<String> cmbEmpleado;
    @FXML
    private Button btnAgendar;
    @FXML
    private Button btnNuevoLavado;
    @FXML
    private TableView<Lavados> twAgendamientoLavado;
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
    @FXML
    private TextField txtCedulaCliente1;
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
    private TableColumn colConvencional;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private Tab tabAgendameinto;
    @FXML
    private Tab tabCliente;
    @FXML
    private Tab tabConsultaAgendameinto;
    @FXML
    private TabPane tabGeneral;
    @FXML
    private Button btnAgendarLavado;
    private LavadosBD lavados;
    @FXML
    private Button btnConsul;
   private ObservableList<Clientes> clientesOL;
   private ObservableList<Lavados> lavadosOL;
    private ObservableList<Mascota> MascotaOL;
     private ObservableList<String> Horario;
    private ClienteBD clienteBD;
    @FXML
    private TableColumn coCodBatea;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTablaClientes() ;
        seleccionarItemTablaClientes();
        rellenarTabla();
        comboHorario();
        rellenarEmpleado();
        inicializarTablaLavados();
        rellenarTabla2();
    }    

    private void rellenarEmpleado(){
        ObservableList<String> obs = null;
       lavados = new LavadosBD();
         obs=lavados.consultaEmpleadoCombo();
         this.cmbEmpleado.setItems(obs);
    }
    private void rellenarTabla() {
        ObservableList<Clientes> obs = null;
        clienteBD = new ClienteBD();
        obs = clienteBD.consulta();
        this.tblClientes.setItems(obs);
        
    }
    private void rellenarTabla2() {
        ObservableList<Lavados> obs = null;
        lavados = new LavadosBD();
        obs = lavados.consultaLavados();
        this.twAgendamientoLavado.setItems(obs);
    }
    
     private void comboHorario() {
        Horario = FXCollections.observableArrayList();
        Horario.add("09:00");
        Horario.add("09:30");
        Horario.add("10:00");
        Horario.add("10:30");
        Horario.add("11:00");
        Horario.add("11:30");
        Horario.add("12:00");
        Horario.add("14:00");
        Horario.add("14:30");
        Horario.add("15:00");
        this.cmbHora.setItems(Horario);
        
        
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
                    
                    txtCedulaCliente1.setText(clientes.getCedula());
                    
                }
            }
        });
    }

      private void inicializarTablaLavados() {
        lavadosOL = FXCollections.observableArrayList();
        this.coCodBatea.setCellValueFactory(new PropertyValueFactory<Lavados, Integer>("codigoBatea"));
        this.coCliente.setCellValueFactory(new PropertyValueFactory<Lavados, String>("cliente"));
        this.coMascota.setCellValueFactory(new PropertyValueFactory<Lavados, String>("nombrePerro"));
        this.coFechaLavado.setCellValueFactory(new PropertyValueFactory<Lavados, String>("fecha"));
        this.coHoraLavado.setCellValueFactory(new PropertyValueFactory<Lavados, String>("hora"));
        this.coProductos.setCellValueFactory(new PropertyValueFactory<Lavados, String>("productos"));
        this.coEmpleado.setCellValueFactory(new PropertyValueFactory<Lavados, String>("empleado"));
        this.twAgendamientoLavado.setItems(lavadosOL);
    }
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void consultaCliente(ActionEvent event) {
     
        
    }

  
    @FXML
    private void agendarLavado(ActionEvent event) {
        lavados = new LavadosBD();
        String productos="";
        if(cheJabon.isSelected()){ productos="Jabon,"; }
        if(cheShampoo.isSelected()){productos=productos+" Shampoo,"; }
        if(chePerfume.isSelected()){productos=productos+" Perfume,"; }
        if(cheTratamiento.isSelected()){productos=productos+" Tratamiento,"; }
        if(cheAniparasitarios.isSelected()){productos=productos+" Antiparacitario,"; }
        if(cheAcondicionador.isSelected()){productos=productos+" Spray Acondicionador"; }
        
        int codiPerro= lavados.codigoMascota(cmbMascota.getSelectionModel().getSelectedItem());
        boolean estado;
        
        estado=lavados.insertarLavados(txtCedulaCliente.getText(), codiPerro, fechaLavado.getValue().toString(), cmbHora.getSelectionModel().getSelectedItem(), productos, cmbEmpleado.getSelectionModel().getSelectedItem());
        if (estado) {

            alert = alerta.alerta("Mascotas ", null, "Lavado Agendado Correctamente", "INFORMATION");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();

        } else {
            alert = alerta.alerta("Mascota ", null, "Error al Guardar Lavado", "ERROR");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();

        }
        rellenarTabla2();
        
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


    @FXML
    private void agendarLavadoCliente(ActionEvent event) {
         tabGeneral.getSelectionModel().select(tabAgendameinto);
         txtCedulaCliente.setText(txtCedulaCliente1.getText());
        
        lavados = new LavadosBD();
         ObservableList<String> obs = null;
         obs=lavados.consultaMascotaCombo(txtCedulaCliente1.getText()); 
         this.cmbMascota.setItems(obs);
         
    }

    @FXML
    private void consultarClien(ActionEvent event) {
        tabGeneral.getSelectionModel().select(tabCliente);
        txtCedulaCliente1.setText("");
    }
    
     
    
}
