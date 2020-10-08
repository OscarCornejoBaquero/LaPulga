
package Objetos;

public class Lavados {
    
    
    private int codigoBatea;
    private String cliente;
    private int idPerro;
    private String nombrePerro;
    private String fecha;
    private String hora;
    private String productos;
    private String empleado;

    public Lavados() {
    }

    public Lavados(int codigoBatea, String cliente, String nombrePerro, String fecha, String hora, String productos, String empleado) {
        this.codigoBatea = codigoBatea;
        this.cliente = cliente;
        this.nombrePerro = nombrePerro;
        this.fecha = fecha;
        this.hora = hora;
        this.productos = productos;
        this.empleado = empleado;
    }

    public Lavados(int codigoBatea, String cliente, int idPerro, String fecha, String hora, String productos, String empleado) {
        this.codigoBatea = codigoBatea;
        this.cliente = cliente;
        this.idPerro = idPerro;
        this.fecha = fecha;
        this.hora = hora;
        this.productos = productos;
        this.empleado = empleado;
    }

    public int getCodigoBatea() {
        return codigoBatea;
    }

    public void setCodigoBatea(int codigoBatea) {
        this.codigoBatea = codigoBatea;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getIdPerro() {
        return idPerro;
    }

    public void setIdPerro(int idPerro) {
        this.idPerro = idPerro;
    }

    public String getNombrePerro() {
        return nombrePerro;
    }

    public void setNombrePerro(String nombrePerro) {
        this.nombrePerro = nombrePerro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
    
    
    
    
}


//create table lavados(
//codigo_batea number,
//cliente varchar2(30),
//id_perro number,
//fecha_lavado date,
//hora_lavado varchar2(30),
//productosExtras varchar2(150),
//empleado varchar2(50)
//);