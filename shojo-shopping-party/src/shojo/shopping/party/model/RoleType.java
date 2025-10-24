package shojo.shopping.party.model;

public enum RoleType {
    CLIENTE("Cliente"),
    PROVEEDOR("Proveedor"),
    ADMINISTRADOR("Administrador");

    private final String descripcion;

    RoleType(String descripcion) {
        this.descripcion = descripcion;
    }

    public String toString() {
        return descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}



