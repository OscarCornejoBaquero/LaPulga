package Controladores;

import BaseDatos.Autentificacion;
import BaseDatos.ClienteBD;
import BaseDatos.PerroBD;
import Objetos.Clientes;
import Objetos.Mascota;
import Objetos.Usuarios;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private ObservableList<String> tipoConsulta;
    private ObservableList<Clientes> clientesOL;
    private ObservableList<Mascota> MascotaOL;
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
    PerroBD perroBD;
    @FXML
    private TableView<Mascota> tblMascotas;
    @FXML
    private TableColumn coIdMascota;
    @FXML
    private TableColumn coIdCliente;
    @FXML
    private TableColumn coNombreMascota;
    @FXML
    private TableColumn coDescripcion;
    @FXML
    private TableColumn coFechaMascota;
    @FXML
    private ComboBox<String> cmbTipoConsulta;
    @FXML
    private Button btnConsultaGeneral;
    int idMascota;
    Mascota mascotaPersonalizada= new Mascota();
    @FXML
    private Button btnAgregarMascota;
    private String cedulaAModificar;
    @FXML
    private Button btnConsutltaPersonalizad;
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
        inicializarTablaMascotas();
        seleccionarItemTablaClientes();
        comboTipoConsulta();
        txtCedula.setOnKeyTyped(event -> SoloNumerosEnteros(event));
        txtTelefono.setOnKeyTyped(event -> SoloNumerosEnteros(event));
        txtTelefonoConvencional.setOnKeyTyped(event -> SoloNumerosEnteros(event));
        txtCedulaConsulta.setOnKeyTyped(event -> SoloNumerosEnteros(event));

    }
    //Para Agregar la imagenen a la paleta
private void comboTipoConsulta() {
        tipoConsulta = FXCollections.observableArrayList();
        tipoConsulta.add(" ");
        tipoConsulta.add("NOMBRE");
        tipoConsulta.add("NOMBRE Y DESCRIPCION");
        tipoConsulta.add("NOMBRE Y AÑO DE NACIMIENTO");
        tipoConsulta.add("DESCRIPCION");
        tipoConsulta.add("AÑO DE NACIMIENTO");
        tipoConsulta.add("DESCRIPCION Y AÑO DE NACIMIENTO");
        
               
        this.cmbTipoConsulta.setItems(tipoConsulta);
    }

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
        reutilizarControles(true, true, true, true, true, true, true, true, true, true);
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
        try {
            char key = keyEvent.getCharacter().charAt(0);

            if (!Character.isDigit(key)) {
                keyEvent.consume();
            }

        } catch (Exception ex) {
        }
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

            reutilizarControles(false, true, true, true, true, true, true, true, true, false);
            btnNuevaMascota.setDisable(false);

        }
        if (cmbEstado.getSelectionModel().getSelectedIndex() == 2) {

            reutilizarControles(false, false, false, false, false, false, true, false, false, true);
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
        boolean resp;
        resp=clienteBD.modificarCliente(txtCedula.getText(), txtNombre.getText(), txtApellido.getText(),
                txtTelefonoConvencional.getText(), txtTelefono.getText(), txtDireccion.getText(), txtCorreo.getText());
        if (resp) {

            alert = alerta.alerta("Cliente ", null, "Cliente Modificado Correctamente", "INFORMATION");
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
        boolean resp;
        resp=clienteBD.eliminarCliente(txtCedula.getText());
        if(resp){
        alert = alerta.alerta("Cliente ", null, "Cliente Eliminado Correctamente", "INFORMATION");
        alert.getDialogPane().getScene().getWindow();
        ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
        alert.showAndWait();
        rellenarTabla();
        }
        else{
            alert = alerta.alerta("Cliente ", null, "Error al Eliminar el Cliente Tiene mascoras Agregadas  ||", "ERROR");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();
        }
    }

    @FXML
    private void agregarMascotaTAB(ActionEvent event) {
        tabGeneral.getSelectionModel().select(rMascotas);
        txtCedulaConsulta.setText(txtCedula.getText());
       rellenarTabla2();

    }
private void rellenarTabla2() {
        ObservableList<Mascota> obs = null;
        perroBD = new PerroBD();
        obs = perroBD.consultaMascotasIndividual(txtCedulaConsulta.getText());
        this.tblMascotas.setItems(obs);
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
        cedulaAModificar=txtCedula.getText();
        txtCedula.setDisable(true);
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
        seleccionarItemTablaMascota();
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
       boolean resp;
       resp= perroBD.modificarPerro(idMascota, txtNombreMascota.getText(), txtDescripcionMascota.getText());
       if(resp){
           alert = alerta.alerta("Mascotas ", null, "Mascota ACTUALIZADA Correctamente", "INFORMATION");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();
       }else {
            alert = alerta.alerta("Mascota ", null, "Error al ACTUALIZAR Mascota", "ERROR");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();

        }
         rellenarTablaPerro();
    }

    @FXML
    private void nuevaMascota(ActionEvent event) {
        boolean resp;
        perroBD = new PerroBD();
        resp = perroBD.insertarPerro(txtCedulaConsulta.getText(), txtNombreMascota.getText(), txtDescripcionMascota.getText(), txtAnio.getValue().toString());
        if (resp) {

            alert = alerta.alerta("Mascotas ", null, "Mascota Agregada Correctamente", "INFORMATION");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();

        } else {
            alert = alerta.alerta("Mascota ", null, "Error al Guardar Mascota NOmbre de Mascota Duplicado / No Existe Registro", "ERROR");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();

        }
rellenarTabla2();
    }

    @FXML
    private void eliminarMascota(ActionEvent event) {
        boolean resp;
        resp=perroBD.eliminarCliente(idMascota);
            if(resp){
           alert = alerta.alerta("Mascotas ", null, "Mascota ACTUALIZADA Correctamente", "INFORMATION");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();
       }else {
            alert = alerta.alerta("Mascota ", null, "Error al ACTUALIZAR Mascota", "ERROR");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();

        }
        
        rellenarTablaPerro();
    }

    @FXML
    private void limpiarVentanaMascotas(ActionEvent event) {
        txtCedulaConsulta.setText("");
        txtNombreMascota.setText("");
        txtDescripcionMascota.setText("");
        cmbTipoConsulta.setDisable(false);
        btnConsutltaPersonalizad.setDisable(true);
        btnConsultaGeneral.setDisable(false);
        btnNuevaMascota.setDisable(true);
        btnAgregarMascota.setDisable(false);
        txtCedulaConsulta.setDisable(true);
        txtNombreMascota.setDisable(true);
        txtDescripcionMascota.setDisable(true);
        txtAnio.setDisable(true);
        
    }

    private void rellenarTablaPerro() {
        ObservableList<Mascota> obs = null;
        perroBD = new PerroBD();
        obs = perroBD.consultaMascotas();
        this.tblMascotas.setItems(obs);
    }

    private void inicializarTablaMascotas() {
        MascotaOL = FXCollections.observableArrayList();
        this.coIdMascota.setCellValueFactory(new PropertyValueFactory<Mascota, Integer>("id"));
        this.coIdCliente.setCellValueFactory(new PropertyValueFactory<Mascota, String>("nombreDueño"));
        this.coNombreMascota.setCellValueFactory(new PropertyValueFactory<Mascota, String>("nombreMascota"));
        this.coDescripcion.setCellValueFactory(new PropertyValueFactory<Mascota, String>("descripcion"));
        this.coFechaMascota.setCellValueFactory(new PropertyValueFactory<Mascota, String>("fecha"));

        this.tblMascotas.setItems(MascotaOL);
    }

    public void seleccionarItemTablaMascota() {
        tblMascotas.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (tblMascotas.getSelectionModel().getSelectedItem() != null) {
                    Mascota mascota = tblMascotas.getSelectionModel().getSelectedItem();
                    txtCedulaConsulta.setDisable(true);
                    idMascota=mascota.getId();
                    txtNombreMascota.setText(mascota.getNombreMascota());
                    txtDescripcionMascota.setText(mascota.getDescripcion());

                    btnNuevaMascota.setDisable(false);
                    btnActualizarMascota.setDisable(false);
                    reutilizarControles(true, true, true, true, true, true, true, true, true, false);
                    txtNombreMascota.setDisable(false);
                    txtDescripcionMascota.setDisable(false);
                }
            }
        });
    }

    @FXML
    private void consultaMascotasGeneral(ActionEvent event) {
        rellenarTablaPerro();
        
    }

    @FXML
    private void agregarMascota(ActionEvent event) {
        txtNombreMascota.setDisable(false);
        txtDescripcionMascota.setDisable(false);
        txtAnio.setDisable(false);
        txtCedulaConsulta.setDisable(false);
        btnActualizarMascota.setDisable(true);
        btnNuevaMascota.setDisable(false);
        btnConsultaGeneral.setDisable(true);
        cmbTipoConsulta.setDisable(true);
        btnConsutltaPersonalizad.setDisable(true);
    }

    private void alertaCorrecta(){
        alert = alerta.alerta("Mascotas ", null, "Mascota Consulta realizada con Éxito", "INFORMATION");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();
    }
    private void alertaIncorrecta(){
         alert = alerta.alerta("Mascota ", null, "Error al consultar verifque el campo", "ERROR");
            alert.getDialogPane().getScene().getWindow();
            ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);
            alert.showAndWait();
    }
    @FXML
    private void consultaPersonalida(ActionEvent event) {
        boolean resp;
         ObservableList<Mascota> obs = null;
          ObservableList<Mascota> obs2 = FXCollections.observableArrayList();
          
         switch (cmbTipoConsulta.getSelectionModel().getSelectedIndex()){
            case 1 :
               perroBD = new PerroBD();
                mascotaPersonalizada=perroBD.consulta1(txtNombreMascota.getText());
                
                obs2.add(mascotaPersonalizada);
                //obs = perroBD.consulta1(txtNombreMascota.getText());
                this.tblMascotas.setItems(obs2);

                System.out.println(""+perroBD.consulta1(txtNombreMascota.getText()));
                resp=mascotaPersonalizada.isEstado();
                System.out.println(""+resp);
                if(resp==true){
                    alertaCorrecta();
                }else{
                     alertaIncorrecta();
                }
                break;
            case 2 :
                 mascotaPersonalizada=perroBD.consulta2(txtNombreMascota.getText(), txtDescripcionMascota.getText());
                 MascotaOL.add(mascotaPersonalizada);
                resp=mascotaPersonalizada.isEstado();
                 if(resp){
                    alertaCorrecta();
                }else{
                       alertaIncorrecta();
                }
                break;
            case 3 : 
                 mascotaPersonalizada=perroBD.consulta3(txtNombreMascota.getText(),txtAnio.getValue().toString() );
                 MascotaOL.add(mascotaPersonalizada);
                resp=mascotaPersonalizada.isEstado();
                 if(resp){
                    alertaCorrecta();
                }else{
                   alertaIncorrecta();
                }
                break;
            case 4 :
                 mascotaPersonalizada=perroBD.consulta4(txtDescripcionMascota.getText());
                 MascotaOL.add(mascotaPersonalizada);
                resp=mascotaPersonalizada.isEstado();
                 if(resp){
                    alertaCorrecta();
                }else{
                   alertaIncorrecta();
                }
                break;
            case 5: 
                 mascotaPersonalizada=perroBD.consulta5(txtAnio.getValue().toString());
                 MascotaOL.add(mascotaPersonalizada);
                resp=mascotaPersonalizada.isEstado();
                 if(resp){
                    alertaCorrecta();
                }else{
                   alertaIncorrecta();
                }
                break; 
            case 6: 
                 mascotaPersonalizada=perroBD.consulta6(txtAnio.getValue().toString(),txtDescripcionMascota.getText());
                 MascotaOL.add(mascotaPersonalizada);
                resp=mascotaPersonalizada.isEstado();
                 if(resp){
                    alertaCorrecta();
                }else{
                   alertaIncorrecta();
                }
                break; 
            
             
        }
    }

    @FXML
    private void seleccionTipoConsulta(ActionEvent event) {
        switch (cmbTipoConsulta.getSelectionModel().getSelectedIndex()){
            case 1 :
                System.out.println(""+cmbTipoConsulta.getSelectionModel().getSelectedItem());
                txtNombreMascota.setDisable(false);
                txtDescripcionMascota.setDisable(true);
                txtAnio.setDisable(true);
                btnConsultaGeneral.setDisable(true);
                btnConsutltaPersonalizad.setDisable(false);
                break;
            case 2 :
                System.out.println(""+cmbTipoConsulta.getSelectionModel().getSelectedItem());
                txtNombreMascota.setDisable(false);
                txtDescripcionMascota.setDisable(false);
                txtAnio.setDisable(true);
                btnConsultaGeneral.setDisable(true);
                btnConsutltaPersonalizad.setDisable(false);
                break;
            case 3 : 
                System.out.println(""+cmbTipoConsulta.getSelectionModel().getSelectedItem());
                txtNombreMascota.setDisable(false);
                txtDescripcionMascota.setDisable(true);
                txtAnio.setDisable(false);
                btnConsultaGeneral.setDisable(true);
                btnConsutltaPersonalizad.setDisable(false);
                break;
            case 4 :
                System.out.println(""+cmbTipoConsulta.getSelectionModel().getSelectedItem());
                txtNombreMascota.setDisable(true);
                txtDescripcionMascota.setDisable(false);
                txtAnio.setDisable(true);
                btnConsultaGeneral.setDisable(true);
                btnConsutltaPersonalizad.setDisable(false);
                break;
            case 5: 
                System.out.println(""+cmbTipoConsulta.getSelectionModel().getSelectedItem());
                txtNombreMascota.setDisable(true);
                txtDescripcionMascota.setDisable(true);
                txtAnio.setDisable(false);
                btnConsultaGeneral.setDisable(true);
                btnConsutltaPersonalizad.setDisable(false);
                break; 
            case 6: 
                System.out.println(""+cmbTipoConsulta.getSelectionModel().getSelectedItem());
                txtNombreMascota.setDisable(true);
                txtDescripcionMascota.setDisable(false);
                txtAnio.setDisable(false);
                btnConsultaGeneral.setDisable(true);
                btnConsutltaPersonalizad.setDisable(false);
                break; 
            
             
        }
    }

}
