package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:BDProyecto";
    private static final String USUARIO = "system";
    private static final String CLAVE = "Proyecto-2020";

    public Connection conectar() {
        Connection conn = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection(URL, USUARIO, CLAVE);
            //System.out.println("Todo OK");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Fqallo");
        }
        return conn;
    }

    public boolean tablaUsuarios() {
        boolean resp = true;
        Connection conn = conectar();
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement("create table UsuariosPrueba(id_Usuario number,usuario varchar2(50),pass varchar2(50),nombre varchar2(50),apellido varchar2(50),correo varchar2(50),fecha_nacimiento date)");
            stmt.execute();
            stmt = conn.prepareStatement("alter table UsuariosPrueba add constraint Usuarios_PK primary key (id_Usuario)");
            stmt.execute();
            stmt = conn.prepareStatement(" insert all into UsuariosPrueba (id_Usuario, usuario, pass, nombre, apellido, correo, fecha_nacimiento)\n"
                    + "       values (1,'oscar','123456','Oscar Leonardo','Cornejo Baquero','oscar.cornejob@ug.edu.ec',to_date('07/08/1989', 'DD/MM/YYYY'))\n"
                    + "       \n"
                    + "       into UsuariosPrueba (id_Usuario, usuario, pass, nombre, apellido, correo, fecha_nacimiento)\n"
                    + "       values (2,'salvador','123456','Salvador Isai','Ordoñez Loor','salvador.ordoñezb@ug.edu.ec',to_date('04/04/2000', 'DD/MM/YYYY'))\n"
                    + "       \n"
                    + "        into UsuariosPrueba (id_Usuario, usuario, pass, nombre, apellido, correo, fecha_nacimiento)\n"
                    + "       values (3,'michael','123456','Michael Andres','Goya Medina','michael.goyam@ug.edu.ec',to_date('07/09/1994', 'DD/MM/YYYY'))\n"
                    + "select * from dual");
            stmt.execute();
            stmt = conn.prepareStatement("commit");
            stmt.execute();
            stmt = conn.prepareStatement("create table usuarioActivo( id_Usuario number,nombre varchar2(75),apellido varchar2(75),correo varchar2(50),fechaIngreso date)");
            stmt.execute();
            System.out.println("20-4");
            stmt = conn.prepareStatement("alter table UsuarioActivo add constraint Usuario_PK primary key (id_Usuario)");
            stmt.execute();
            System.out.println("aqui");
            stmt = conn.prepareStatement("insert all into usuarioActivo(id_Usuario,Nombre,Apellido,Correo,Fechaingreso)VALUES (1,'','','',to_date('07/09/1994', 'DD/MM/YYYY')) select *from dual ");
            stmt.execute();
            System.out.println("Si");
            stmt = conn.prepareStatement("commit");
            stmt.execute();
            System.out.println("posit");

            resp = true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            resp = false;
        }

        return resp;
    }
}
