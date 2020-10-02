
package Controladores;

import javafx.scene.control.Alert;


public class Alertas {
    
     private  Alert alert;
     
    public Alert alerta(String titulo, String cabecera,String contenido,String tipo){
        String error=tipo;
        alert = new Alert(Alert.AlertType.valueOf(error));
        alert.setTitle(titulo);
        alert.setHeaderText(cabecera);
        alert.setContentText(contenido);
        return alert;
    }
}
