
package BaseDatos;

import Controladores.ClientesController;
import Objetos.Clientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ClienteBD {
    Connection con;
    boolean estado;
    PreparedStatement pst;
    Statement s;
    ResultSet rs;
    Conexion conexion = new Conexion();
    Clientes clientes;

    public ClienteBD() {
        con = conexion.conectar();
    }
    public boolean insertarCliente(String cedula, String nombre, String apellido, String convencional, String celular,
            String direccion, String email) {
        if (consutaExistencia(cedula, "Cliente") == true) {
            String queryInsertar = ("insert into Cliente (Id_Cliente,Nombre,Apellido,Telf_Convencional,Telf_Celular,Direccion,Email) values (?,?,?,?,?,?,?)");
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
    
    
    public boolean modificarCliente(String cedula, String nombre, String apellido, String convencional, String celular,
            String direccion, String email) {
            String queryModificar = ("UPDATE Cliente SET Id_Cliente='"+cedula+"',Nombre = '"+nombre+"',Apellido='"+apellido+"',Telf_Convencional='"+convencional+"',Telf_Celular='"+celular+"',Direccion='"+direccion+"',Email='"+email+"' WHERE id_cliente='"+cedula+"'");
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
                estado =false;
            }
            return estado;
    }
    
    
    public Clientes consultarCliente(String cedula) {
        clientes = new Clientes();
        try {
            //System.out.println("aqui si ");
            String queryConsultaCliente=("select Nombre,Apellido,Telf_Convencional,Telf_Celular,Direccion,Email from Cliente where id_cliente='"+cedula+"'");
            //System.out.println("aqui tambien ");
            s = con.createStatement();
            rs = s.executeQuery(queryConsultaCliente);
            //System.out.println("aqui tambien2 ");
             while (rs.next()) {
                //int id = rs.getInt(1);
                String nombre = rs.getString(1);
                String apellido = rs.getString(2);
                String convencional = rs.getString(3);
                String celular = rs.getString(4);
                String direccion = rs.getString(5);
                String email= rs.getString(6);
                clientes = new Clientes(cedula,nombre,apellido,convencional,celular,direccion,email);
                 System.out.println(""+clientes.toString());
              
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return clientes;
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
    
     public boolean eliminarCliente(String id){
        try {                      
            PreparedStatement stmt;
            stmt = con.prepareStatement("DELETE FROM Cliente WHERE id_cliente='" + id + "'");
            stmt.executeUpdate();
            estado=true;
             } catch (SQLException ex) {
            Logger.getLogger(ClientesController.class.getName()).log(Level.SEVERE, null, ex);
            estado=false;
        }
        return estado;
    }
    
    
    
    
    public ObservableList<Clientes> consulta(){
         ObservableList<Clientes> obs = FXCollections.observableArrayList();
        clientes= new Clientes();
         try {
            
            con = conexion.conectar();
            String sql = "SELECT * FROM Cliente WHERE ROWNUM <= 30";
           
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString(1);
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                String convencional = rs.getString(4);
                String celular = rs.getString(5);
                String direccion = rs.getString(6);
                String email= rs.getString(7);
               
                
                clientes= new Clientes(id,nombre,apellido,convencional,celular,direccion,email);
                obs.add(clientes);
            }
           
             } catch (SQLException ex) {
                 System.out.println(ex);
        }
        return obs;
    }
    
}
