package shojo.shopping.party.view;
import shojo.shopping.party.model.RoleType;
import shojo.shopping.party.repository.PartyRepository;

import java.util.Scanner;

public class Main {
    public static void main(String []args){
        System.out.println("-- MENU SHOJO SHOPPING --");
        System.out.println("-- SELECCIONE SU ROL -- " +
                " 1 --> CLIENTE" +
                " 2 --> PROVEEDOR" +
                " 3 --> ADMIN");

        Scanner scanner = new Scanner(System.in);

        int eleccion = scanner.nextInt();
        RoleType rol;

        switch (eleccion) {
            case 1:
                rol = RoleType.CLIENTE;
                break;
            case 2:
                rol = RoleType.PROVEEDOR;
                break;

            case 3:
                rol = RoleType.ADMINISTRADOR;
                break;

            default:
                rol = RoleType.CLIENTE;

        }




    }


}


