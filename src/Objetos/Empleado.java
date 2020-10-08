
package Objetos;

/**
 *
 * @author oscar
 */
public class Empleado {
    
    
    private String idEmpleado;
    private String nombre;
    private String email;
    private String estado;
    private String telefono;

    public Empleado() {
    }

    public Empleado(String idEmpleado, String nombre, String email, String estado, String telefono) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.email = email;
        this.estado = estado;
        this.telefono = telefono;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
}
