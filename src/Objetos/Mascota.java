
package Objetos;

public class Mascota {
    private int id;
    private String nombreDueño;
    private int dueño;
    private String nombreMascota;
    private String descripcion;
    private String fecha;
    private boolean estado;
    

    public Mascota() {
    }

    public Mascota(int id, String nombreDueño,String nombreMascota, String descripcion, String fecha) {
        this.id = id;
        this.nombreDueño = nombreDueño;
        this.nombreMascota = nombreMascota;
        this.descripcion = descripcion;
        this.fecha = fecha;
        
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Mascota(int id, String nombreDueño, String nombreMascota, String descripcion, String fecha, boolean estado) {
        this.id = id;
        this.nombreDueño = nombreDueño;
        this.nombreMascota = nombreMascota;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.estado = estado;
    }

    public Mascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public Mascota(String nombreDueño, String nombreMascota) {
        this.nombreDueño = nombreDueño;
        this.nombreMascota = nombreMascota;
    }

  

   

    public Mascota(int id, int dueño, String nombreMascota, String descripcion, String fecha) {
        this.id = id;
        this.dueño = dueño;
        this.nombreMascota = nombreMascota;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreDueño() {
        return nombreDueño;
    }

    public void setNombreDueño(String nombreDueño) {
        this.nombreDueño = nombreDueño;
    }

    public int getDueño() {
        return dueño;
    }

    public void setDueño(int dueño) {
        this.dueño = dueño;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
