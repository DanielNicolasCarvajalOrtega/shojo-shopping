package shojo.shopping.party.controller;
import shojo.shopping.party.model.*;

import java.util.ArrayList;
import java.util.List;

public class PartyManager {
    // ATRIBUTO DE LA CLASE
    private List<Party> participantes;

    public PartyManager(){
        // CONSTRUCTOR
        this.participantes = new ArrayList<>();

    }
    public Party registrarParty(String firstName, String lastName, RoleType rol){
        Person person = new Person(firstName, lastName, rol);
        participantes.add(person);
        return person;
    }

    public Party registrarOrganizacion(String firstName, String lastName, RoleType rol){
        Person organ = new Person(firstName, lastName,rol);
        participantes.add(organ);
        return organ;

    }
    public Party buscarPorId(int id){
        for (Party party: participantes){
            if (party.getId() == id){
                return party;
            }
        }
        return null;
    }

    public List<Party> listarTodos(){
        return new ArrayList<>(participantes);
    }

    public Party buscarPorNombre(String nombre){
        if (nombre == null || nombre.isEmpty()){
            return null;
        }

        String nombreBusqueda = nombre.toLowerCase();
        for (Party party: participantes){
            if (party.getNombre().contains(nombreBusqueda)){
                return party;
            }
        }
        return null;
    }

    public int obtenerTotalParty(){
        return participantes.size();
    }




}
