package BaseDatos;

import Objetos.Clientes;
import Objetos.Mascota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Lavados {
    
     Connection con;
    boolean estado;
    PreparedStatement pst;
    Statement s;
    ResultSet rs;
    Conexion conexion = new Conexion();
    Clientes clientes;
    Mascota mascotas;

    public Lavados() {
         con = conexion.conectar();
    }

    
    public ObservableList<String>  consultaMascotaCombo(String cedula){
       ObservableList<String> obs = FXCollections.observableArrayList();
        mascotas = new Mascota();
        //System.out.println("Aqui");
        try {
            String sql = "select perro.nombre_perro\n" +
"from cliente inner join perro \n" +
"on cliente.id_cliente = perro.id_cliente\n" +
"where cliente.id_cliente='"+cedula+"'";
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
    
    
}
