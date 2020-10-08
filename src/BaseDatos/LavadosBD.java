package BaseDatos;

import Objetos.Clientes;
import Objetos.Empleado;
import Objetos.Lavados;
import Objetos.Mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LavadosBD {

    Connection con;
    boolean estado;
    PreparedStatement pst;
    Statement s;
    ResultSet rs;
    Conexion conexion = new Conexion();
    Clientes clientes;
    Mascota mascotas;
    Lavados lavados;

    public LavadosBD() {
        con = conexion.conectar();
    }

    public ObservableList<String> consultaMascotaCombo(String cedula) {
        ObservableList<String> obs = FXCollections.observableArrayList();
        mascotas = new Mascota();
        //System.out.println("Aqui");
        try {
            String sql = "select perro.nombre_perro\n"
                    + "from cliente inner join perro \n"
                    + "on cliente.id_cliente = perro.id_cliente\n"
                    + "where cliente.id_cliente='" + cedula + "'";
            //      System.out.println("Aca");

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            //System.out.println("hasta aquio");
            while (rs.next()) {

                String nombrePerro = rs.getString(1);
                // System.out.println(""+nombrePerro);
//                String idCLiente = rs.getString(2);
                //mascotas = new Mascota(nombrePerro);
                //System.out.println(""+mascotas.toString());
                obs.add(nombrePerro);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obs;
    }

    public ObservableList<String> consultaEmpleadoCombo() {
        ObservableList<String> obs = FXCollections.observableArrayList();
        mascotas = new Mascota();
        //System.out.println("Aqui");
        try {
            String sql = "select empleado.id_empleado,empleado.nombre_empleado\n"
                    + "from empleado";
            //      System.out.println("Aca");

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);

            //System.out.println("hasta aquio");
            while (rs.next()) {

                String nombrePerro = rs.getString(1);
                // System.out.println(""+nombrePerro);
                String idCLiente = rs.getString(2);
                //mascotas = new Mascota(nombrePerro);
                //System.out.println(""+mascotas.toString());
                obs.add(nombrePerro);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return obs;
    }
    
    public int codigoMascota(String mascota){
        int codi=0;
        try {
            String sql = "select perro.id_perro from perro where perro.nombre_perro='"+mascota+"'";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                    codi = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return codi;
    }
        public String codigoEmpleado(String nombre){
        String codi="";
        try {
            String sql = "select perro.id_perro from perro where perro.nombre_perro='"+nombre+"'";
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                    codi = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return codi;
    }
    
    
    
    
    
    public boolean insertarLavados(String cedula, int mascota, String fecha, String hora, String productos,String empleado){
        
        if (consutaExistencia(cedula, "lavados") == true) {
            String queryInsertar = ("insert into lavados (cliente,id_perro,fecha_lavado,hora_lavado,productosextras,empleado) values (?,?,?,?,?,?)");
            try {
                System.out.println("Intentnado");
                pst = con.prepareStatement(queryInsertar);
                System.out.println("Intentnado2");
                pst.setString(1, cedula);
                pst.setInt(2, mascota);
                pst.setDate(3, java.sql.Date.valueOf(fecha));
                pst.setString(4, hora);
                pst.setString(5, productos);
                pst.setString(6, empleado);
                

                pst.executeUpdate();
                estado = true;

            } catch (SQLException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("aqui estoy"
                    + "");
            estado = false;
        }
        
    return estado;
        
    }
    public boolean consutaExistencia(String id, String tabla) {
        String sql = "SELECT * FROM " + tabla;
         con = conexion.conectar();
        boolean resultado = true;
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                //	System.out.println(rs.getInt(1));
                if (rs.getString(1) == id) {
                    resultado = false;
                } else {
                    resultado = true;
                }
            }
         
        } catch (SQLException e) {
            System.out.println("fallo aqui");
            e.printStackTrace();
        }
        return resultado;
    }
 
public ObservableList<Lavados> consultaLavados(){
         ObservableList<Lavados> obs = FXCollections.observableArrayList();
        lavados= new Lavados();
         try {
            
            
            String sql = "SELECT * FROM Lavados WHERE ROWNUM <= 30";
           
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                String nombre = rs.getString(2);
                String mascota = rs.getString(3);
                String fecha = rs.getString(4);
                String hora = rs.getString(5);
                String productos = rs.getString(6);
                String empleado = rs.getString(7);
                
                
                lavados= new Lavados(id, nombre, mascota, fecha, hora,productos,empleado);
                obs.add(lavados);
            }
           
             } catch (SQLException ex) {
                 System.out.println(ex);
        }
        return obs;
    }
    

}