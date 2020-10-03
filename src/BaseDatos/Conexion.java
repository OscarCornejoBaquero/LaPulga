package BaseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:BDProyecto";
    private static final String USUARIO = "system";
    private static final String CLAVE = "Proyecto-2020";
   
    public Connection conectar(){
        Connection conn = null;
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conn = DriverManager.getConnection(URL,USUARIO,CLAVE);  
            //System.out.println("Todo OK");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            //System.out.println("Fqallo");
        }
        return conn;
}
    
}
