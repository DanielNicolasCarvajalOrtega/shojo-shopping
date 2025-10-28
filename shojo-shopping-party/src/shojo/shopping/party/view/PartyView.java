package shojo.shopping.party.view;
import java.util.List;
import shojo.shopping.party.model.*;
import shojo.shopping.party.controller.PartyManager;

import java.util.Optional;
import java.util.Scanner;


public class PartyView {
    private Scanner scanner;
    private PartyManager manager;

    public PartyView() {
        this.scanner = new Scanner(System.in);
        this.manager = new PartyManager();
    }

    public void iniciar() {
        mostrarBienvenida();
        mostarMenu();
    }

    private void mostrarBienvenida() {
        System.out.println("-- BIENVENIDO A TU TIENDA SHOJI SHOPPING");
    }

    private void mostarMenu() {
        int opcion = 0;
        System.out.println(" -- MENU PRINCIPAL -- " +
                "1 --> Registrar Personas" +
                "2 --> Registrar Organizacion" +
                "3 --> Listar Participantes" +
                "4 --> Buscar por ID" +
                "5 --> Buscar por Nombre" +
                "0 --> Salir");

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

        } catch (NumberFormatException error) {
            System.out.println("DEBE INGRESAR UN NUMERO VALIDO ..");
        } catch (Exception error) {
            System.out.println("Error " + error.getMessage());
        }
        while (opcion != 0) ;

    }

    private void registrarPersona() {
        System.out.println(" -- REGISTRAR PERSONA -- ");

        System.out.println(" -- INGRESA NOMBRE -- ");
        String firstName = scanner.nextLine().trim();

        if (firstName.isEmpty()) {
            System.out.println("LE NOMBRE NO PUEDE QUEDAR EN BLANCO");
            return;
        }

        System.out.println("-- INGRESA APELLIDO -- ");
        String lastName = scanner.nextLine().trim();

        if (lastName.isEmpty()) {
            System.out.println("EL APELLIDO NO PUEDE QUEDAR EN BLANCO");
            return;
        }

        Optional <RoleType> rolOptional = seleccionarRol();

        Party party = manager.registrarPersona(firstName, lastName, rolOptional.get());
        System.out.println("PERSONA REGISTRADA CORRECTAMENTE... ");
        System.out.println("-------------------------");
        System.out.println("------>" + party);
    }

    private void registrarOrganizacion() {
        System.out.println("-- REGISTRAR ORGANIZACION --");
        System.out.println("INGRESE NOMBRE DE LA ORGANIZACION");
        String organizacionName = scanner.nextLine().trim();

        if (organizacionName.isEmpty()) {
            System.out.println("DEBES INGRESAR UN NOMBRE DE ORGANIZACION");
            return;
        }

        Optional <RoleType> rolOptional = seleccionarRol();

        Party party = manager.registrarOrganizacion(organizacionName, rolOptional.get());

        System.out.println("ORGANIZACION REGISTRADA DE FORMA CORRECTA");
        System.out.println("-------------------------");
        System.out.println("------>" + party);
    }

    private void listarParticipantes() {
        System.out.println(" -- LISTA DE PARTICIPANTES REGISTRADOS --");

        List<Party> participantes = manager.obtenerListaSegura(manager.listarTodos());
        if (participantes.isEmpty()) {
            System.out.println("NO HAY PARTICIPANTES REGISTRADOS EN SISTEMA" +
                    "DEBE REGISTRAR UN PARTICIPANTE AL MENOS..");
            return;
        }

        System.out.println();
        for (Party party: participantes) {
            System.out.println(party);
        }
        System.out.println("-- TOTAL PARTICIPANTES ---");
        System.out.println(participantes.size());

    }

    void buscarPorId(){
        System.out.println("-- BUSCAR POR ID --");
        System.out.println("INGRESE EL ID DEL PARTICIPANTE :  ");
        try {
            int id = Integer.parseInt(scanner.nextLine().trim());
            Optional <Party> party = manager.buscarPorId(id);
            if (party != null) {
                System.out.println("PARTICIPANTE ENCONTRADO : ");
                System.out.println("------------------------------");
                System.out.println("--- > " + party);

            }else {
                System.out.println("NO SE ENCONTRO NINGUN PARTICIPANTE CON ESE ID " + id );
            }


        }catch (NumberFormatException err )  {
            System.out.println("ERROR, EL IDENTIFICADOR DEBE SER UN NUMERO VALIDO" + err);
        }

    }

    void buscarPorNombre() {
        System.out.println("--- BUSCAR PARTICIPANTE POR NOMBRE ---");
        String nombre = scanner.nextLine().trim();
        if (nombre.isEmpty()){
            System.out.println("INGRESA UN PARTICIPANTE EXISTENTE");
            return;
        }
        List <Party> resultados = manager.obtenerListaSegura(manager.buscarPorNombre(nombre));

        if (resultados.isEmpty()){
            System.out.println("NO SE ENCONTRARON PARTICIPANTES CON EL NOMBRE " + nombre);

        }
        else {
            System.out.println("PARTICIPANTE ENCONTRADO ");
            System.out.println("SE ENCONTRARON " + resultados.size() + "RESULTADOS --");
            System.out.println("---------------------------");
            for (Party party : resultados){
                System.out.println("--->" + party);
            }
        }
    }

    private Optional <RoleType> seleccionarRol(){
        System.out.println("-- SELECCIONE UN ROL -- " +
                "1 --> CLIENTE" +
                "2 --> PROVEEDOR" +
                "3 --> ADMINISTRADOR" +
                "0 --> SALIR ");
        System.out.println("--> Opcion ");

        try {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()){
                System.out.println("ERROR.... ESTA VACIO");
                return Optional.empty();
            }

            int opcion = Integer.parseInt(scanner.nextLine().trim());

            switch (opcion) {
                case 1:
                    return Optional.of(RoleType.CLIENTE);
                case 2:
                    return Optional.of(RoleType.PROVEEDOR);
                case 3:
                    return Optional.of(RoleType.ADMINISTRADOR);
                case 0:
                    System.out.println(".... VOLVIENDO ATRAS");
                    break;
                default:
                    System.out.println("OPCION INVALIDA SE ASINARA CLIENTE POR DEFECTO...");
                    return Optional.of(RoleType.CLIENTE);
            }
        }
        catch(NumberFormatException err){
            System.out.println("OPCION INVALIDA.. VOLVIENDO ATRAS...");}
            return Optional.empty();

    }



}

