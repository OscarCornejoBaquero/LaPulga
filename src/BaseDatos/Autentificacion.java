package BaseDatos;

import Objetos.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Autentificacion {

    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Conexion conexion = new Conexion();
    Usuarios usuarios;

    public Autentificacion() {
        con = conexion.conectar();
    }
//Consulta de usuarios
    public Usuarios autentificar(String usuario, String pass) {
        boolean estado;
        try {
            pst = (PreparedStatement) con.prepareStatement("select usuario,pass,nombre,apellido,correo from UsuariosPrueba where usuario=? and pass=?");
            pst.setString(1, usuario);
            pst.setString(2, pass);
            rs = pst.executeQuery();
            if (rs.next()) {
                //int id = rs.getInt(1);
                String nombre = rs.getString(3);
                String apellido = rs.getString(4);
                String correo = rs.getString(5);
                estado = true;
                usuarios = new Usuarios(nombre, apellido, correo, estado);
            } else {
                estado = false;
                usuarios = new Usuarios(estado);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Autentificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public void usuarioActivo (String nombre, String apellido,String correo){
        try {
            String sql ="update usuarioActivo set nombre=?,apellido=?,CORREO=? where id_Usuario = 1";
            pst = con.prepareStatement(sql);
            pst.setString(1, nombre);
            pst.setString(2,apellido);
            pst.setString(3, correo);
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Autentificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Usuarios datosInicio(){
         try {
        pst = (PreparedStatement) con.prepareStatement("select *from usuarioActivo");
            rs = pst.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString(2);
                String apellido = rs.getString(3);
                String correo = rs.getString(4);
                usuarios = new Usuarios(nombre, apellido, correo);
            } 
        } catch (SQLException ex) {
            Logger.getLogger(Autentificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }
    
}
