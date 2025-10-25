package shojo.shopping.party.controller;
import shojo.shopping.party.model.*;

import javax.management.relation.Role;
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

    public Party registrarOrganizacion(String organizationName, RoleType rol){
        Organization organ = new Organization(organizationName, rol);
        participantes.add(organ);
        return organ;

    }
    public Party buscarPorId(int id){
        for (Party party: participantes){
            if (party.getID() == id){
                return party;
            }
        }
        return null;
    }

    public List<Party> listarTodos(){
        return new ArrayList<>(participantes);
    }

    public List<Party> buscarPorNombre(String nombre){
        List<Party> resultados = new ArrayList<>();
        if (nombre == null || nombre.isEmpty()){
            return resultados;
        }

        String nombreBusqueda = nombre.toLowerCase();
        for (Party party: participantes){
            if (party.getNombre().toLowerCase().contains(nombreBusqueda)){
                resultados.add(party);
            }
        }
        return resultados;
    }

    public int obtenerTotalParty(){
        return participantes.size();
    }




}
