
package BaseDatos;
import Objetos.Clientes;
import Objetos.Mascota;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
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
                estado =false;
                System.out.println(e);
            }
       
        
        return estado;
    }
    
    public ObservableList<Mascota> consultaMascotas(){
         ObservableList<Mascota> obs = FXCollections.observableArrayList();
        mascotas= new Mascota();
         try {
            
            
            String sql = "SELECT * FROM Perro WHERE ROWNUM <= 30";
           
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int idPerro = rs.getInt(1);
                int idCliente = rs.getInt(2);
                String nombre = rs.getString(3);
                String descripcion = rs.getString(4);
                String fecha = rs.getString(5);
               
                
                mascotas= new Mascota(idPerro, idCliente, nombre, descripcion, fecha);
                obs.add(mascotas);
            }
           
             } catch (SQLException ex) {
                 System.out.println(ex);
        }
        return obs;
    }
    
    public ObservableList<Mascota> consultaMascotasIndividual(){
         ObservableList<Mascota> obs = FXCollections.observableArrayList();
        mascotas= new Mascota();
         try {
            
            
            String sql = "SELECT * FROM Perro WHERE id_perro=? ";
           
            Statement s = con.createStatement();
            
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                int idPerro = rs.getInt(1);
                int idCliente = rs.getInt(2);
                String nombre = rs.getString(3);
                String descripcion = rs.getString(4);
                String fecha = rs.getString(5);
               
                
                mascotas= new Mascota(idPerro, idCliente, nombre, descripcion, fecha);
                obs.add(mascotas);
            }
           
             } catch (SQLException ex) {
                 System.out.println(ex);
        }
        return obs;
    }
    
}
