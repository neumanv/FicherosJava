package Actividad;

public class Persona {

    int id_persona;
    String nombre, apellidos;

    public Persona(int id_persona, String nombre, String apellidos) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
