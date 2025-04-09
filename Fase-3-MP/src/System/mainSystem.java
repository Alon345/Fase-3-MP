package System;

import Entities.Challenge;
import Entities.Client;
import java.util.ArrayList;
import java.io.Console;
import java.util.Scanner;
import Entities.Administrator;
import Entities.Combat;

public class mainSystem {
    public static Console out;
    private static String in;

    /**A continuación se definen las operaciones**/
    public void selector(){
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);
        terminal.showStart();
        int option = sc.nextInt();
        switch (option) {
            case 1: {
                //REGISTRO DE USUARIO
                terminal.userRegistrerMenu();
                int user = sc.nextInt();
                registerUser(user);
            }
            case 2: {
                // LOGIN AS CLIENT
                Client client = new Client();
                client = loginClient(client);
                if (client != null) {
                    Menu menu = new Menu();

                    CombatFileReader combatFileReader = new CombatFileReader();
                    ArrayList<Combat> combatList = combatFileReader.readCombatFile();
                    for (Combat combat : combatList) {
                        if (!combat.isSeen() && combat.getChallenger().getNick().equals(client.getNick())) {
                            NotificationManager notificationManager = new NotificationManager();
                            notificationManager.notifyCombat(combat);
                            combat.setSeen(true);
                        }
                    }
                    CombatFileWriter combatFileWriter = new CombatFileWriter();
                    combatFileWriter.overwriteCombatFile(combatList);

                    ChallengeFileReader challengeFileReader = new ChallengeFileReader();
                    ArrayList<Challenge> challengeList = challengeFileReader.readChallengeFile();
                    for (int i = 0; i < challengeList.size(); i++) {
                        if (challengeList.get(i).isValidated() && challengeList.get(i).getRival().getNick().equals(client.getNick())) {
                            NotificationManager notificationManager = new NotificationManager();
                            notificationManager.notifyChallenge(client, terminal, challengeList, i);
                            i--;
                        }
                    }
                    menu.selector(client, this);
                }
            }
            case 3: {
                // LOGIN AS ADMIN
                Administrator operator = new Administrator();
                operator = loginOperator(operator);
                if (operator != null) {
                    Menu menu = new Menu();
                    menu.operatorSelector(operator, this);
                }
            }
            default: terminal.error();
        }
    }
    public void registerUser(int option) {
        Scanner sc = new Scanner(System.in);
        Terminal terminal = new Terminal();
        UserFileReader userFileReader = new UserFileReader();
        ArrayList<Client> clientList = userFileReader.readUserFile();
        switch (option) {
            case 1: { //opcion de modo jugador
                Client client = new Client();
                terminal.askNameUser();
                String name = sc.nextLine();
                client.setName(name);
                terminal.askNick();
                String nick = sc.nextLine();
                boolean found = false;
                if (!clientList.isEmpty()) {
                    for (Client value : clientList) {
                        if ((value.getNick().equals(nick))) {
                            terminal.nickExists();
                            found = true;
                            break;
                        }
                    }
                }
                if (!found) {
                    client.setNick(nick);
                    terminal.askPassword();
                    String password = sc.next();
                    sc.nextLine(); // Limpiar el salto de línea del buffer
                    terminal.confirmPassword();
                    String confirm = sc.nextLine();
                    if (!password.equals(confirm)) { //comprobamos la contraseña
                        terminal.error();
                        break;
                    }
                    client.setPassword(password);
                    terminal.confirmNewUser(name);
                    String register1 = client.generateRegisterNumber();
                    client.setRegister(register1);
                    client.setCharacter(null);
                    UserFileWriter userFileWriter = new UserFileWriter();
                    userFileWriter.userRegister(client);
                }
            }
            case 2: {
                Administrator operator = new Administrator();
                AdministratorFileReader operatorFileReader = new AdministratorFileReader();
                ArrayList<Administrator> list = operatorFileReader.readOperatorFile();
                terminal.askNick();
                String name = sc.nextLine();
                operator.setName(name);
                terminal.askNick();
                String nick = sc.nextLine();
                for (Administrator value : list) {
                    if ((value.getNick().equals(nick))) {
                        terminal.nickExists();
                        break;
                    }
                }
                operator.setNick(nick);
                terminal.askPassword();
                String password = sc.nextLine();
                terminal.confirmPassword();
                String confirmPassword = sc.next(); //volvemos a leer la contraseña
                if (!password.equals(confirmPassword)) { // Verificamos que la contraseña esta bien
                    //si password != confirmPassword
                    terminal.error();
                    break;
                }
                operator.setPassword(password);
                AdministratorFileWriter operatorFileWriter = new AdministratorFileWriter();
                operatorFileWriter.registerOperator(operator);
            }
            case 3: {
            }
            default: terminal.error();
        }
    }

    public Client loginClient(Client client) {
        Scanner sc = new Scanner(System.in);
        Terminal terminal = new Terminal();
        int aux = -1;
        UserFileReader userFileReader = new UserFileReader();
        ArrayList<Client> list = userFileReader.readUserFile();
        terminal.askNick();
        String nick = sc.nextLine();
        boolean found = false;
        //comparar nick con lista clientes en el fichero
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNick().equals(nick)) {
                found = true;
                aux = i;
                i = list.size();
            }
        }
        if (!found) {
            return null;
        }
        BanFileReader banFileReader = new BanFileReader();
        ArrayList<String> bannedClients = banFileReader.readBannedUsersFile();
        if (!bannedClients.isEmpty()) {
            for (String bannedClient : bannedClients) {
                if (nick.equals(bannedClient)) {
                    NotificationManager notificationManager = new NotificationManager();
                    notificationManager.notifyBan();
                    return null;
                }
            }
        }
        boolean passCorrect;
        do {
            terminal.askPassword();
            String password = sc.nextLine();
            //comparar contraseña para el nick
            passCorrect = list.get(aux).getPassword().equals(password);
            if (!passCorrect) {
                terminal.errorPassword();
            }
        } while (!passCorrect);
        client = list.get(aux);
        return client;
    }
    public Administrator loginOperator(Administrator operator) {
        Scanner sc = new Scanner(System.in);
        Terminal terminal = new Terminal();
        AdministratorFileReader operatorFileReader = new AdministratorFileReader();
        ArrayList<Administrator> list = operatorFileReader.readOperatorFile();
        terminal.askNick();
        String nick = sc.nextLine();
        boolean found = false;
        int index = -1;

        for (int i = 0; i < list.size(); i++) { // Cambiar por while??
            if (list.get(i).getNick().equals(nick)) {
                found = true;
                index = i;
                i = list.size();
            }
        }
        if (!found) {
            return null;
        }
        boolean passCorrect = false;
        do {
            terminal.askPassword();
            String password = sc.nextLine();
            passCorrect = list.get(index).getPassword().equals(password);
            if (!passCorrect) {
                terminal.errorPassword();
            }
        } while (!passCorrect);

        return operator;
    }


}
