package shojo.shopping.party.model;

public abstract class Party {
    private static int contadorId = 1;
    private int id;
    private String nombre;
    private RoleType rol;

    // CONSTRUCTOR PARAMETRIZADO
    public Party(String nombre, RoleType rol){
        this.id = contadorId++;
        this.nombre = nombre;
        this.rol = rol;

    }
    // METODOS GETTER Y SEETTERS
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
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










