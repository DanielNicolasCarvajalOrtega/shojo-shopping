package shojo.shopping.party.view;
import java.sql.SQLOutput;
import java.util.List;
import shojo.shopping.party.model.*;
import shojo.shopping.party.controller.PartyManager;

import javax.management.relation.Role;
import java.util.Scanner;


public class PartyView {

    private Scanner scanner;
    private PartyManager manager;

    public PartyView(){
        this.scanner = new Scanner(System.in);
        this.manager = new PartyManager();
    }

    public void iniciar(){
        mostrarBienvenida();
        mostarMenu();
    }

    private void mostrarBienvenida(){
        System.out.println("-- BIENVENIDO A TU TIENDA SHOJI SHOPPING");
    }

    private void mostarMenu() {
        int opcion;
        do {
            System.out.println("\n-- MENU PRINCIPAL --");
            System.out.println("1 --> Registrar Persona");
            System.out.println("2 --> Registrar Organizacion");
            System.out.println("3 --> Listar Participantes");
            System.out.println("4 --> Buscar por ID");
            System.out.println("5 --> Buscar por Nombre");
            System.out.println("0 --> Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
                switch (opcion) {
                    case 1:
                        registrarPersona();
                        break;

                    case 2:
                        registrarOrganizacion();
                        break;
                    case 3:
                        listarParticipantes();
                        break;
                    case 4:
                        buscarPorId();
                        break;
                    case 5:
                        buscarPorNombre();
                        break;
                    case 0:
                        System.out.println("---EN SHOJI SHOPPING SIEMPRE TIENES UN ESPACIO PARA TI ---");
                        break;
                    default:
                        System.out.println("OPCION INCORRECTA");
                }

            }
            catch (NumberFormatException error) {
                System.out.println("DEBE INGRESAR UN NUMERO VALIDO ..");
                opcion = -1;
            }
            catch (Exception error) {
                System.out.println("Error: " + error.getMessage());
                opcion = -1;
            }
        } while (opcion != 0);

    }

    private void registrarPersona(){
        System.out.println("\n-- REGISTRAR PERSONA --");

        System.out.print("Ingresa nombre: ");
        String firstName = scanner.nextLine().trim();

        if (firstName.isEmpty()){
            System.out.println("El nombre no puede quedar en blanco");
            return;
        }

        System.out.print("Ingresa apellido: ");
        String lastName = scanner.nextLine().trim();

        if (lastName.isEmpty()) {
            System.out.println("El apellido no puede quedar en blanco");
            return;
        }

        RoleType rol = seleccionarRol();
        if (rol == null) {
            return;
        }

        Party persona = manager.registrarParty(firstName, lastName, rol);
        System.out.println("Participante " + persona.getId() + " creado: " + 
                          persona.getNombre() + " (Persona, " + rol.getDescripcion() + ")");
    }

    private RoleType seleccionarRol(){
        System.out.println("\n-- SELECCIONE UN ROL --");
        System.out.println("1 --> CLIENTE");
        System.out.println("2 --> PROVEEDOR");
        System.out.println("3 --> ADMINISTRADOR");
        System.out.println("0 --> SALIR");
        System.out.print("Opcion: ");

        try {
            int opcion = Integer.parseInt(scanner.nextLine().trim());

            switch (opcion) {
                case 1:
                    return RoleType.CLIENTE;
                case 2:
                    return RoleType.PROVEEDOR;
                case 3:
                    return RoleType.ADMINISTRADOR;
                case 0:
                    System.out.println(".... VOLVIENDO ATRAS");
                    return null;
                default:
                    System.out.println("OPCION INVALIDA SE ASIGNARA CLIENTE POR DEFECTO...");
                    return RoleType.CLIENTE;
            }
        }
        catch(NumberFormatException err){
            System.out.println("OPCION INVALIDA.. VOLVIENDO ATRAS...");
            return null;
        }

    }

    private void registrarOrganizacion(){
        System.out.println("\n-- REGISTRAR ORGANIZACION --");

        System.out.print("Ingresa nombre de la organizacion: ");
        String organizationName = scanner.nextLine().trim();

        if (organizationName.isEmpty()){
            System.out.println("El nombre no puede quedar en blanco");
            return;
        }

        RoleType rol = seleccionarRol();
        if (rol == null) {
            return;
        }

        Party organizacion = manager.registrarOrganizacion(organizationName, rol);
        System.out.println("Participante " + organizacion.getId() + " creado: " + 
                          organizacion.getNombre() + " (Organizacion, " + rol.getDescripcion() + ")");
    }

    private void listarParticipantes(){
        System.out.println("\n-- LISTAR PARTICIPANTES --");
        List<Party> participantes = manager.listarTodos();
        
        if (participantes.isEmpty()) {
            System.out.println("No hay participantes registrados");
            return;
        }

        System.out.println("\nTotal de participantes: " + participantes.size());
        System.out.println("=".repeat(100));
        for (Party party : participantes) {
            System.out.println(party);
        }
        System.out.println("=".repeat(100));
    }

    private void buscarPorId(){
        System.out.println("\n-- BUSCAR POR ID --");
        System.out.print("Ingresa el ID a buscar: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Party party = manager.buscarPorId(id);
            
            if (party == null) {
                System.out.println("No se encontro participante con ID " + id);
            } else {
                System.out.println("\nParticipante encontrado:");
                System.out.println(party);
            }
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero valido");
        }
    }

    private void buscarPorNombre() {
        System.out.println("\n-- BUSCAR POR NOMBRE --");
        System.out.print("Ingresa el nombre a buscar: ");
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacio");
            return;
        }

        List<Party> resultados = manager.buscarPorNombre(nombre);
        
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron participantes");
        } else {
            System.out.println("\nSe encontraron " + resultados.size() + " participante(s):");
            System.out.println("=".repeat(100));
            for (Party party : resultados) {
                System.out.println(party);
            }
            System.out.println("=".repeat(100));
        }
    }
}

