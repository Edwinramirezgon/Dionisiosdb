package Backend;

/*Autores
1.Edwin Ramirez Gonzalez 
2.Robinson Dionisio 
3.Robinson Taborda 
4.Juan David Velasquez 
5.El Mocho Bailarin
 */
public class ClsGeneral {

    private int lista;
 private String Dni;
    private String Nombre;
    private String apellido;
    private String correo;
    private String direccion;
    private String telefono;
    private String fecha;

    public ClsGeneral() {
        Dni ="";
        lista=0;
        Nombre="";
        apellido="";
        correo="";
        direccion="";
        telefono="";
        fecha="";
    }

    public ClsGeneral(int lista, String Dni, String Nombre, String apellido, String correo, String direccion, String telefono, String fecha) {
        this.lista = lista;
        this.Dni = Dni;
        this.Nombre = Nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha = fecha;
    }

    
    //Constructor clientes

    public int getLista() {
        return lista;
    }

    public void setLista(int lista) {
        this.lista = lista;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String Dni) {
        this.Dni = Dni;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
}