package shojo.shopping.party.repository;
import java.util.List;
import java.util.Collections;
import shojo.shopping.party.model.Party;
import java.util.ArrayList;

public class PartyRepository {
    // ATRIBUTO , SE MANTIENE ENTRE LLAMADAS
    private List<Party> partys;

    // CONSTRUCTOR -> INICIA UNA VEZ LA LISTA
    public PartyRepository() {
        this.partys = new ArrayList<>();

    }

    // RETORNA TRUE SI SE AGREA O FALSE SI NO SE AGREGA
    public boolean registrarParty(Party party) {
        if (party == null) {
            return false;
        }
        return partys.add(party); // true
    }

    // DEVUELVE UNA LISTA INMUTABLE
    public List<Party> obtenerPartys() {
        return Collections.unmodifiableList(partys);
    }

    // DEVUELVE LISTA MUTABLE
    public List<Party> obtenerPartyMutable() {
        return new ArrayList<>(partys);
    }

    // BUSCAR PARTICIPANTE POR IDENTIFICADOR ...
    public Party buscarPartyId(int id) {
        for (Party party : partys) {
            if (party.getID() == id) {
                return party;
            }
        }
        return null;
    }

    // BUSCAR PARTICIPANTES POR NOMBRE
    public Party buscarPartyNombres(String nombre) {
        // EVALUAMOS EL CONTENIDO PARA EVITAR CON GUARD CLAUSES...
        if (nombre == null || nombre.isEmpty()){
            return null;
        }

        String nombresPartys = nombre.toLowerCase();

        for (Party party : partys) {
            if (party.getNombre().toLowerCase().contains(nombresPartys)) {
                return party;
            }
        }
        return null;
    }

    public boolean actualziarParty(Party party) {
        if (party == null) {
            return false;
        }
        // BUSCA Y SETTEA EL IDENTIFICADOR DEL PARTY
        for (int i = 0; i < partys.size(); i++) {
            if (partys.get(i).getID() == party.getID()) {
                partys.set(i, party);
                return true; // si se actualizo
            }
        }
        return false; // si no se actualizo
    }

    // ACTUALIZA POR EL NOMBRE DEL PARTICIPANTE
    public boolean actualizarNombreParty(int id, String nuevoNombre){
        Party party = buscarPartyId(id);
        if (party != null){
            party.setNombre(nuevoNombre);
            return true;
        }
        return false;
    }





}







