package party.repository;
import party.model.Party;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class PartyRepository {
    private Set<Party> partys;

    public PartyRepository() {
        // CONSTRUCTOR
        this.partys = new LinkedHashSet<>();
    }

    public boolean registrarParty(Party party){
        if (party == null){
            return false;
        }
        if (existePorNombre(party.getNombre())){
            return false; // SI YA EXISTE CON ESE NOMBRE
        }
        return partys.add(party);
    }

    public boolean existePorNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return false;
        }

        String nombreBusqueda = nombre.trim();

        for (Party party : partys) {
            if (party.getNombre() != null &&
                    party.getNombre().equals(nombreBusqueda)) {
                return true;
            }
        }
        return false;
    }

    private boolean existeDuplicado(Party party) {
        String nombreBusqueda = party.getNombre();

        for (Party p : partys) {
            boolean mismoNombre = p.getNombre().equals(nombreBusqueda);
            boolean mismoTipo = p.getClass().equals(party.getClass());

            if (mismoNombre && mismoTipo) {
                return true;
            }
        }

        return false;
    }

    public List<Party> obtenerTodosMutables(){
        return new ArrayList<>(partys);
    }

    // DEVUELVE LISTA MUTABLE
    public List<Party> obtenerPartysMutable() {
        return new ArrayList <>(partys);
    }

    public boolean existePorId(int id){
        return buscarPorId(id).isPresent();
    }

    // BUSCAR PARTICIPANTE POR IDENTIFICADOR ...
    public Optional<Party> buscarPorId(int id) {
        for (Party party : partys) {
            if (party.getId() == id) {
                return Optional.of(party);
            }
        }
        return Optional.empty();
    }

    public boolean eliminarPorId(int id){
        Optional <Party> partyOptional = buscarPorId(id);
        return partyOptional.map(partys::remove).orElse(false);
    }

    /*
    public void eliminarTodos() {
        partys.clear();
    }

    public boolean eliminarParty(Party party){
        return partys.remove(party);
    }
    */

    // BUSCAR PARTICIPANTES POR NOMBRE
    public List<Party> buscarPartyNombres(String nombre) {
        List<Party> resultados = new ArrayList<>();

        if (nombre == null || nombre.isEmpty()) {
            return resultados;
        }

        String criterio = nombre;

        for (Party party : partys) {
            if (party.getNombre() != null &&
                    party.getNombre().contains(criterio)) {
                resultados.add(party);
            }
        }
        return resultados;
    }
    /*
    // ACTUALIZA POR EL NOMBRE DEL PARTICIPANTE
    public boolean actualizarNombreParty(int id, String nuevoNombre){
        Optional<Party> optionalParty = buscarPorId(id);
        if (optionalParty.isPresent()){
            Party party = optionalParty.get();
            party.setNombre(nuevoNombre);
            return true;
        }
        return false;
    }
    */
}