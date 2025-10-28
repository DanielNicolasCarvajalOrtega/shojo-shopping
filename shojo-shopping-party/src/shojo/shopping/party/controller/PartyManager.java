package shojo.shopping.party.controller;
import shojo.shopping.party.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class PartyManager {
    // ATRIBUTO DE LA CLASE
    private List<Party> participantes;

    public PartyManager() {
        // CONSTRUCTOR
        this.participantes = new ArrayList<>();

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
        participantes.add(person);
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
        participantes.add(organ);
        return organ;
    }

    public Party buscarPorId(int id){
        for (Party party: participantes){
            if (party.getId() == id){
                return party;


    public Optional<Party> buscarPorId(int id) {
        for (Party party : participantes) {
            if (party.getID() == id) {
                return Optional.of(party);
            }
        }
        return Optional.empty();
    }

    public List<Party> listarTodos() {
        return new ArrayList<>(participantes);
    }

    public  List<Party> buscarPorNombre(String nombre) {
        List<Party> resultados = new ArrayList<>();

        if (nombre == null || nombre.isEmpty()) {
            return resultados;
        }

        String nombreBusqueda = nombre.toLowerCase();
        for (Party party : participantes) {
            if (party.getNombre().contains(nombreBusqueda)) {
                resultados.add(party);
            }
        }
        return resultados;
    }

    public int obtenerTotalParty() {
        return participantes.size();
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
