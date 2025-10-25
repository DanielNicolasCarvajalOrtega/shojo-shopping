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
        int opcion = -1;
        
        do {
            System.out.println();
            System.out.println("========================================");
            System.out.println("        MENU PRINCIPAL");
            System.out.println("========================================");
            System.out.println("1 --> Registrar Persona");
            System.out.println("2 --> Registrar Organizacion");
            System.out.println("3 --> Listar Participantes");
            System.out.println("4 --> Buscar por ID");
            System.out.println("5 --> Buscar por Nombre");
            System.out.println("0 --> Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());
                System.out.println();
                
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
            }
            catch (Exception error) {
                System.out.println("Error " + error.getMessage());
            }
        } while (opcion != 0);

    }

    private void registrarPersona(){
        System.out.println(" -- REGISTRAR PERSONA -- ");

        System.out.println(" -- INGRESA NOMBRE -- ");
        String firstName = scanner.nextLine().trim();

        if (firstName.isEmpty()){
            System.out.println("LE NOMBRE NO PUEDE QUEDAR EN BLANCO");
            return;
        }

        System.out.println("-- INGRESA APELLIDO -- ");
        String lastName = scanner.nextLine().trim();

        if (lastName.isEmpty()) {
            System.out.println("EL APELLIDO NO PUEDE QUEDAR EN BLANCO");
            return;
        }

        RoleType rol = seleccionarRol();
        if (rol == null) {
            return;
        }

        Party persona = manager.registrarParty(firstName, lastName, rol);
        System.out.println(String.format("Participante %d creado: %s (%s, %s)", 
            persona.getID(), persona.getNombre(), persona.getTipo(), rol.getDescripcion()));
    }

    private RoleType seleccionarRol(){
        System.out.println("-- SELECCIONE UN ROL --");
        System.out.println("1 --> CLIENTE");
        System.out.println("2 --> PROVEEDOR");
        System.out.println("3 --> ADMINISTRADOR");
        System.out.println("0 --> SALIR");
        System.out.print("--> Opción: ");

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
                    break;
                default:
                    System.out.println("OPCION INVALIDA SE ASINARA CLIENTE POR DEFECTO...");
                    return RoleType.CLIENTE;
            }
        }
        catch(NumberFormatException err){
            System.out.println("OPCION INVALIDA.. VOLVIENDO ATRAS...");}
            return null;

    }

    void registrarOrganizacion(){
        System.out.println(" -- REGISTRAR ORGANIZACION -- ");

        System.out.println(" -- INGRESA NOMBRE DE LA ORGANIZACION -- ");
        String organizationName = scanner.nextLine().trim();

        if (organizationName.isEmpty()){
            System.out.println("EL NOMBRE NO PUEDE QUEDAR EN BLANCO");
            return;
        }

        RoleType rol = seleccionarRol();
        if (rol == null) {
            return;
        }

        Party organizacion = manager.registrarOrganizacion(organizationName, rol);
        System.out.println(String.format("Participante %d creado: %s (%s, %s)", 
            organizacion.getID(), organizacion.getNombre(), organizacion.getTipo(), rol.getDescripcion()));
    }

    void listarParticipantes(){
        System.out.println(" -- LISTAR PARTICIPANTES -- ");
        List<Party> participantes = manager.listarTodos();
        
        if (participantes.isEmpty()) {
            System.out.println("No hay participantes registrados.");
            return;
        }

        System.out.println("Total de participantes: " + participantes.size());
        System.out.println();
        for (Party party : participantes) {
            System.out.println(party.toString());
        }
    }

    void buscarPorId(){
        System.out.println(" -- BUSCAR POR ID -- ");
        System.out.print("Ingrese el ID: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Party party = manager.buscarPorId(id);
            
            if (party != null) {
                System.out.println("Participante encontrado:");
                System.out.println(party.toString());
            } else {
                System.out.println("No se encontró participante con ID " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un número válido.");
        }
    }

    void buscarPorNombre() {
        System.out.println(" -- BUSCAR POR NOMBRE -- ");
        System.out.print("Ingrese el nombre (o parte del nombre): ");
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            System.out.println("Debe ingresar un nombre.");
            return;
        }

        List<Party> resultados = manager.buscarPorNombre(nombre);
        
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron participantes");
        } else {
            System.out.println("Se encontraron " + resultados.size() + " participante(s):");
            System.out.println();
            for (Party party : resultados) {
                System.out.println(party.toString());
            }
        }
    }
}

