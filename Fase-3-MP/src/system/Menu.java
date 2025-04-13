package System;

import Entities.Administrator;
import Entities.Character;
import Entities.Client;

import java.util.ArrayList;
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
                        terminal.showTipesOfCharacters();
                        selectFactory(client);
                    } else {
                        terminal.deleteCharacToCreateAnother();
                    }
                    break;
                case 2: //borrar personaje
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
                    client.challenge(client);
                    break;
                case 5: //consulta de batallas
                    checkFights(client);
                    break;
                case 6: //consultar ranking global
                    client.globalRanking();
                    break;
                case 7: //salir
                    terminal.logout();
                    system.selector();
                    break;
                case 8: //borrar cuenta
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

    /**
     * Elección de tipo de personaje a crear
     * @param client Usuario que creará el personaje x
     */
    public void selectFactory(Client client) {
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);
        Character charac = null;
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1 -> charac = client.createVampire();
            case 2 -> charac = client.createWerewolf();
            case 3 -> charac = client.createHunter();
            default -> terminal.error();
        }
        client.setCharacter(charac);
        UserFileReader userFileReader = new UserFileReader();
        UserFileWriter userFileWriter = new UserFileWriter();
        ArrayList<Client> clientList = userFileReader.userFileReader();

        for (int userNum = 0; userNum < clientList.size(); userNum++){
            if (client.getNick().equals(clientList.get(userNum).getNick())){
                clientList.remove(userNum);
                clientList.add(client);
                userFileWriter.rewriteUserFile(clientList);
                break;
            }
        }
    }

    public void operatorSelector(Administrator admin, mainSystem system) {
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);
        Client client = new Client();
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
                    String cause = "";
                    admin.banUser(client);
                    break;
                case 4:
                    admin.unbanUser();
                    break;
                case 5:
                    terminal.logout();
                    system.selector();
                    break;
                case 6:
                    admin.deleteAdminAccount(admin, system);
            }
        } while (opcion != 4 && opcion != 5);
    }
}
