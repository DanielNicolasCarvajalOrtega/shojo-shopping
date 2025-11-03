package party.controller;
import party.model.*;
import party.repository.PartyRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class PartyManager {
    // ATRIBUTO DE LA CLASE
    private PartyRepository repository;
    public PartyManager() {
        // CONSTRUCTOR
        this.repository = new PartyRepository();
    }

    public Party registrarPersona(String firstName, String lastName, RoleType rol) {
        if (firstName == null || firstName.isEmpty()){
            throw new IllegalArgumentException("EL NOMBRE NO PUEDE ESTAR VACIO");
        }
        if (lastName == null || lastName.isEmpty()){
            throw new IllegalArgumentException("EL APELLIDO NO PUEDE ESTAR VACIO");
        }
        if (rol == null ){
            throw new IllegalArgumentException("EL ROL NO PUEDE SER NULO");
        }

        Person person = new Person(firstName, lastName, rol);
        repository.registrarParty(person);
        return person;
    }

    public Party registrarOrganizacion(String firstName, RoleType rol) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException("EL NOMBRE DE LA ORGANIZACION NO PUEDE QUEDAR VACIO");
        }
        if (rol == null){
            throw new IllegalArgumentException("EL ROL NO PUEDE QUEDAR EN NULO");
        }
        Organization organ = new Organization(firstName, rol);
        repository.registrarParty(organ);
        return organ;
    }

    public Optional<Party> buscarPorId(int id) {
        return repository.buscarPorId(id);
    }

    public List<Party> listarTodos() {
        return new ArrayList<>();
    }

    public List<Party> buscarPorNombre(String nombre) {
        return repository.buscarPartyNombres(nombre);
    }

    public boolean eliminarPorId(int id){
        return repository.eliminarPorId(id);
    }

    public List<Party> obtenerTotalParty() {
        return repository.obtenerPartysMutable();
    }

    public List<Party> obtenerListaSegura(List <Party> lista){
        if (lista == null){
            return Collections.emptyList();
        }
        return lista;
    }
       /*
       * OBTENEMOS UNA LISTA VACIA DESDE LOS NULL QUE MANEJA MEJOR
       * LOS NULL-POINTER-EXCEPTIONS
       * */
    public List<Party> obtenerListaSeguraOptional(List <Party> lista){
        return Optional.ofNullable(lista).orElse(Collections.emptyList());
    }
}
