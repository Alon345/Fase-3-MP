package System;

import Entities.Client;
import Entities.Operator;

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
                    } else
                        terminal.error();
                    break;
                case 3:
                    client.selectTeam(client);
                    break;
                case 4:
                    // Call to read the file of all clients
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

    }
    public void checkRanking(Client client) {

    }
    public void selectFactory(Client client) {

    }
    public void operatorSelector(Operator operator, mainSystem system) {

    }
}
