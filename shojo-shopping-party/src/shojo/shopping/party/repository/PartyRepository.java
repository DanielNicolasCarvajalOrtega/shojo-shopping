package shojo.shopping.party.repository;

import java.util.List;
import java.util.Collections;
import shojo.shopping.party.model.Party;
import java.util.ArrayList;
import java.util.Optional;


/**
 * Repositorio en memoria para objetos Party.
 *
 * - Mantiene una lista interna de parties.
 * - Provee operaciones básicas: crear, listar, buscar y actualizar.
 */
public class PartyRepository {

    private List<Party> partys;

    /**
     * Constructor: inicializa la lista interna vacía.
     */
    public PartyRepository() {
        this.partys = new ArrayList<>();
    }

    /**
     * Registra un Party en el repositorio.
     *
     * @param party el objeto Party a agregar
     * @return true si se agregó correctamente en caso contrario false
     */
    public boolean createParty(Party party) {
        if (party == null) {
            return false;
        }
        return partys.add(party);
    }


    /**
     * Devuelve una vista inmutable de todas los parties.
     *
     * @return lista inmutable de Party
     */
    public List<Party> findAllParty() {
        return Collections.unmodifiableList(partys);
    }

    /**
     * Busca un Party por su id numérico.
     * - Recorre la lista y compara getId() con el id solicitado.
     * - Devuelve null si no se encuentra.
     *
     * @param id identificador a buscar
     * @return el Party encontrado o null si no existe
     */
    public Party findPartyById(int id) {
        for (Party party : partys) {
            if (party.getId() == id) {
                return party;

    // DEVUELVE LISTA MUTABLE
    public List<Party> obtenerPartysMutable() {
        return new ArrayList<>(partys);
    }

    // BUSCAR PARTICIPANTE POR IDENTIFICADOR ...
    public Optional<Party> buscarPartyId(int id) {
        for (Party party : partys) {
            if (party.getID() == id) {
                return Optional.of(party);
            }
        }
        return Optional.empty();
    }

    /**
     * Busca el primer Party cuyo nombre contenga la cadena dada (case-insensitive).
     * - Si nombre es null o vacío devuelve null.
     * - Normaliza a minúsculas para comparación insensible a mayúsculas.
     *
     * @param nombre subcadena a buscar en el nombre del party
     * @return el primer Party coincidente o null si no hay coincidencias
     */
    public Party findPartyByName(String nombre) {

    // BUSCAR PARTICIPANTES POR NOMBRE
    public Optional<Party> buscarPartyNombres(String nombre) {
        // EVALUAMOS EL CONTENIDO PARA EVITAR CON GUARD CLAUSES...
        if (nombre == null || nombre.isEmpty()){
            return Optional.empty();
        }

        String nombresPartys = nombre.toLowerCase();

        for (Party party : partys) {
            if (party.getNombre().toLowerCase().contains(nombresPartys)) {
                return Optional.of(party);
            }
        }
        return Optional.empty();
    }


    /**
     * Actualiza (reemplaza) un Party existente por su id.
     * - No acepta null.
     * - Busca el índice del party con el mismo id y reemplaza el objeto.
     * - Devuelve true si se actualizó, false si no existía ese id.
     *
     * @param party objeto Party con el mismo id que se desea actualizar
     * @return true si se actualizó, false si el parámetro es null o no se encontró el id
     */
    public boolean updateParty(Party party) {
        if (party == null) {
            return false;
        }
        for (int i = 0; i < partys.size(); i++) {
            if (partys.get(i).getId() == party.getId()) {
                partys.set(i, party);
                return true; // si se actualizo
            }
        }
        return false; // si no se actualizo
    }

    // ACTUALIZA POR EL NOMBRE DEL PARTICIPANTE
    public boolean actualizarNombreParty(int id, String nuevoNombre){
        Optional <Party> optionalParty = buscarPartyId(id);
        if (optionalParty.isPresent()){
            Party party = optionalParty.get();
            party.setNombre(nuevoNombre);
            return true;
        }
        return false;
    }

}







