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

        }
        catch (NumberFormatException error) {
            System.out.println("DEBE INGRESAR UN NUMERO VALIDO ..");
        }
        catch (Exception error) {
            System.out.println("Error " + error.getMessage());
        }
        while (opcion !=0);

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

    }

    private RoleType seleccionarRol(){
        System.out.println("-- SELECCIONE UN ROL -- " +
                "1 --> CLIENTE" +
                "2 --> PROVEEDOR" +
                "3 --> ADMINISTRADOR" +
                "0 --> SALIR ");
        System.out.println("--> Opcion ");

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
        System.out.println("COMING SOON");
    }

    void listarParticipantes(){
        System.out.println("COMING SOON");
    }

    void buscarPorId(){
        System.out.println("COMING SOON");
    }

    void buscarPorNombre() {
        System.out.println("COMING SOON");
    }
}

