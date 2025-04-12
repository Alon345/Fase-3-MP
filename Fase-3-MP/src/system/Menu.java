package System;

import Entities.Administrator;
import Entities.Client;
import java.util.Scanner;

public class Menu {

    public void selector(Client client, mainSystem system) {
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            terminal.showMenu();
            option = sc.nextInt();
            switch (option) {
                case 1:
                    if (client.getCharacter() == null) {
                        terminal.showFactories();
                        selectFactory(client);
                    } else {
                        terminal.deleteCharacter();
                    }
                    break;
                case 2:
                    if (client.getCharacter() != null) {
                        client.deleteCharacter(client);
                    } else {
                        terminal.error();
                    }
                    break;
                case 3:
                    client.selectTeam(client);
                    break;
                case 4:
                    // Llamada para leer el archivo de todos los clientes.
                    client.challenge(client);
                    break;
                case 5:
                    checkFights(client);
                    break;
                case 6:
                    checkRanking(client);
                    break;
                case 7:
                    terminal.logout();
                    system.selector();
                    break;
                case 8:
                    client.deleteAccount(client, system);
                    break;
                default:
                    terminal.error();
                    break;
            }
        } while (option != 7 && option != 8);
    }

    public void checkFights(Client client) {
        // Lógica para ver las peleas del cliente, si es necesario.
    }

    public void checkRanking(Client client) {
        // Lógica para ver el ranking del cliente, si es necesario.
    }

    public void selectFactory(Client client) {
        // Lógica para seleccionar la fábrica, si es necesario.
    }

    public void operatorSelector(Administrator admin, mainSystem system) {
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            terminal.adminMenu();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    admin.modifyCharacter();
                    break;
                case 2:
                    admin.validatingChallenge();
                    break;
                case 3:
                    admin.unbanUser();
                    break;
                case 4:
                    terminal.logout();
                    system.selector();
                    break;
                case 5:
                    terminal.confirmDeleteAccount();
                    int confirm = sc.nextInt();
                    if (confirm == 1) {
                        if (admin.getNick() == null || admin.getNick().isEmpty()) {
                            terminal.showMessage("El nick del administrador no puede ser nulo o vacío.");
                        } else {
                            AdministratorFileWriter adminFileWriter = new AdministratorFileWriter();
                            adminFileWriter.deleteAdmin(admin.getNick());
                            terminal.logout();
                            system.selector();
                        }
                    } else {
                        terminal.showMessage("Eliminación de cuenta cancelada.");
                    }
                    break;
                default:
                    terminal.error();
                    break;
            }
        } while (opcion != 4 && opcion != 5);
    }
}
