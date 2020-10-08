package BaseDatos;

import Controladores.ClientesController;
import Objetos.Clientes;
import Objetos.Mascota;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PerroBD {

    Connection con;
    boolean estado;
    PreparedStatement pst;
    Statement s;
    ResultSet rs;
    Conexion conexion = new Conexion();
    Clientes clientes;
    Mascota mascotas;

    public PerroBD() {
        con = conexion.conectar();
    }

    public boolean insertarPerro(String cliente, String nombre, String descripcion, String fechaNacimiento) {
        String queryInsertar = ("insert into Perro(id_cliente,nombre_perro,descr_perro,fecha_nac)VALUES (?,?,?,?)");
        try {
            System.out.println("Intentnado");
            pst = con.prepareStatement(queryInsertar);
            System.out.println("Intentnado2");
            pst.setString(1, cliente);
            pst.setString(2, nombre);
            pst.setString(3, descripcion);
            pst.setDate(4, java.sql.Date.valueOf(fechaNacimiento));

            pst.executeUpdate();
            estado = true;

        } catch (SQLException e) {
            estado = false;
            System.out.println(e);
        }

        return estado;
    }

    public ObservableList<Mascota> consultaMascotas() {
        ObservableList<Mascota> obs = FXCollections.observableArrayList();
        mascotas = new Mascota();
        try {

            String sql = "SELECT perro.id_perro, cliente.nombre, cliente.apellido,perro.nombre_perro,perro.descr_perro,perro.fecha_nac\n"
                    + "FROM cliente\n"
                    + "INNER JOIN perro\n"
                    + "ON cliente.id_cliente = perro.id_cliente";

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int idPerro = rs.getInt(1);
                String idCliente = rs.getString(2);
                String nombre = rs.getString(3);
                String nombreMascota = rs.getString(4);
                String descripcion = rs.getString(5);
                String fecha = rs.getString(6);

                mascotas = new Mascota(idPerro, idCliente + " " + nombre, nombreMascota, descripcion, fecha);
                obs.add(mascotas);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obs;
    }

    public ObservableList<Mascota> consultaMascotasIndividual(String cedula) {
        ObservableList<Mascota> obs = FXCollections.observableArrayList();
        mascotas = new Mascota();
        try {

            String sql = "SELECT perro.id_perro,cliente.nombre, cliente.apellido,perro.nombre_perro,perro.descr_perro,perro.fecha_nac\n"
                    + "FROM cliente\n"
                    + "INNER JOIN perro\n"
                    + "ON cliente.id_cliente = perro.id_cliente\n"
                    + "where cliente.id_cliente='" + cedula + "'";

            Statement s = con.createStatement();

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {

                int idPerro = rs.getInt(1);
                String idCliente = rs.getString(2);
                String apeCliente = rs.getString(3);
                //System.out.println(""+idCliente+" "+ apeCliente);
                String nombre = rs.getString(4);
                String descripcion = rs.getString(5);
                String fecha = rs.getString(6);

                mascotas = new Mascota(idPerro, idCliente + " " + apeCliente, nombre, descripcion, fecha);
                obs.add(mascotas);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obs;
    }

    public boolean eliminarCliente(int id) {
        try {
            PreparedStatement stmt;
            stmt = con.prepareStatement("DELETE FROM perro WHERE id_perro='" + id + "'");
            stmt.executeUpdate();
            estado = true;
        } catch (SQLException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
            estado = false;
        }
        return estado;
    }

    public boolean modificarPerro(int id, String nombre, String descriptiocn) {
        String queryModificar = ("UPDATE perro SET id_perro='" + id + "',nombre_perro = '" + nombre + "',descr_perro='" + descriptiocn + "' WHERE id_perro='" + id + "'");
        try {
            //System.out.println("Intentnado");
            pst = con.prepareStatement(queryModificar);
            //System.out.println("Intentnado2");
            pst.executeUpdate();
            pst = con.prepareStatement("commit");
            pst.executeUpdate();
            estado = true;

        } catch (SQLException e) {
            System.out.println(e);
            estado = false;
        }
        return estado;
    }

    
    public Mascota consulta1(String nombre){
        mascotas = new Mascota();
        try {
            String sql = "SELECT perro.id_perro, cliente.nombre, cliente.apellido,perro.nombre_perro,perro.descr_perro,perro.fecha_nac\n"
                    + "FROM cliente\n"
                    + "INNER JOIN perro\n"
                    + "ON cliente.id_cliente = perro.id_cliente\n"
                    + "where perro.nombre_perro='"+nombre+"'";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int idPerro = rs.getInt(1);
                String idCliente = rs.getString(2);
                String apeCliente = rs.getString(3);
                System.out.println(""+idCliente+" "+ apeCliente);
                String nombre2 = rs.getString(4);
                String descripcion = rs.getString(5);
                String fecha = rs.getString(6);
                mascotas = new Mascota(idPerro, idCliente + " " + apeCliente, nombre2, descripcion, fecha,true);
            }
            } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mascotas;
    }
    
    public Mascota consulta2(String nombre, String descripcion){
        try {
            String sql = "SELECT perro.id_perro, cliente.nombre, cliente.apellido,perro.nombre_perro,perro.descr_perro,perro.fecha_nac\n"
                    + "FROM cliente\n"
                    + "INNER JOIN perro\n"
                    + "ON cliente.id_cliente = perro.id_cliente\n"
                    + "where perro.nombre_perro='"+nombre+"'";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int idPerro = rs.getInt(1);
                String idCliente = rs.getString(2);
                String apeCliente = rs.getString(3);
                System.out.println(""+idCliente+" "+ apeCliente);
                String nombre2 = rs.getString(4);
                String descripcion2 = rs.getString(5);
                String fecha = rs.getString(6);
                mascotas = new Mascota(idPerro, idCliente + " " + apeCliente, nombre2, descripcion2, fecha,true);
            }
            } catch (SQLException ex) {
            System.out.println(ex);
        }
        return mascotas;
    }
    
    public Mascota consulta3(String nombre, String anio){
        
        return mascotas;
    }
    
    public Mascota consulta4(String descripcion){
        
        return mascotas;
    }
    
    public Mascota consulta5(String anio){
        
        return mascotas;
    }
    
    public Mascota consulta6(String anio, String descripcion){
        
        return mascotas;
    }
}

