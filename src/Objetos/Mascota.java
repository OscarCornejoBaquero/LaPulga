
package Objetos;

public class Mascota {
    private int id;
    private int dueño;
    private String nombreMascota;
    private String descripcion;
    private String fecha;

    public Mascota() {
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
