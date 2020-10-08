
package BaseDatos;

import Objetos.Clientes;
import Objetos.Mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmpleadosBD {
    Connection con;
    boolean estado;
    PreparedStatement pst;
    Statement s;
    ResultSet rs;
    Conexion conexion = new Conexion();
    Clientes clientes;
    Mascota mascotas;

    public EmpleadosBD() {
          con = conexion.conectar();
    }
    
    public boolean insertarCliente(String cedula, String nombre, String apellido, String convencional, String celular,
            String direccion, String email) {
        if (consutaExistencia(cedula, "Empleado") == true) {
            String queryInsertar = ("insert into Empleado (id_empleado,nombre_empleado,email_empleado,Telf_Convencional,Telf_Celular,Direccion,Email) values (?,?,?,?,?,?,?)");
            try {
                System.out.println("Intentnado");
                pst = con.prepareStatement(queryInsertar);
                System.out.println("Intentnado2");
                pst.setString(1, cedula);
                pst.setString(2, nombre);
                pst.setString(3, apellido);
                pst.setString(4, convencional);
                pst.setString(5, celular);
                pst.setString(6, direccion);
                pst.setString(7, email);

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
    
}
