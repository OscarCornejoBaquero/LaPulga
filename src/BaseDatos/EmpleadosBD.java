
package BaseDatos;

import Objetos.Clientes;
import Objetos.Empleado;
import Objetos.Mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmpleadosBD {
    Connection con;
    boolean estado;
    PreparedStatement pst;
    Statement s;
    ResultSet rs;
    Conexion conexion = new Conexion();
    Clientes clientes;
    Mascota mascotas;
    Empleado empleado;
    
    public EmpleadosBD() {
          con = conexion.conectar();
    }
    
    public boolean insertarEmpleado(String cedula, String nombre, String correo, String estadoEmpleado, String celular) {
        if (consutaExistencia(cedula, "Empleado") == true) {
            String queryInsertar = ("insert into Empleado (id_empleado,nombre_empleado,email_empleado,estado,telefono) values (?,?,?,?,?)");
            try {
                System.out.println("Intentnado");
                pst = con.prepareStatement(queryInsertar);
                System.out.println("Intentnado2");
                pst.setString(1, cedula);
                pst.setString(2, nombre);
                pst.setString(3, correo);
                pst.setString(4, estadoEmpleado);
                pst.setString(5, celular);
                

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
    public ObservableList<Empleado> consulta(){
         ObservableList<Empleado> obs = FXCollections.observableArrayList();
        empleado= new Empleado();
         try {
            
            
            String sql = "SELECT * FROM Empleado WHERE ROWNUM <= 30";
           
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String nombre = rs.getString(2);
                String correo = rs.getString(3);
                String estado = rs.getString(4);
                String celular = rs.getString(6);
                
                
                empleado= new Empleado(id, nombre, correo, estado, celular);
                obs.add(empleado);
            }
           
             } catch (SQLException ex) {
                 System.out.println(ex);
        }
        return obs;
    }
}
