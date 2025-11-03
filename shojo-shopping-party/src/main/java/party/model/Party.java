package party.model;
import java.util.Objects;

public abstract class Party {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private String apellido;
    private RoleType rol;

    // CONSTRUCTOR PARAMETRIZADO
    public Party(String nombre, String apellido, RoleType rol){
        this.id = contadorId++;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rol = rol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Party party = (Party) o;
        return id == party.id; // COMPARAMOS POR ID
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    // METODOS GETTER Y SEETTERS
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellido(){
        return apellido;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public RoleType getRol(){
        return rol;
    }
    public void setRol(RoleType rol){
        this.rol = rol;
    }
    public abstract String getTipo();

    // FORMATO DE LISTADO POR PANTALLA
    @Override
    public String toString () {
        return String.format(
                "Id --> %-7d | " +
                        "Nombre --> %-26s | "  +
                        "Rol --> %-20s | " +
                        "Tipo --> %-20s",
                id , nombre, rol.getDescripcion(),getTipo());
    }


}










