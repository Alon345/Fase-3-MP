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
    public void selector() {
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);
        terminal.showStart();
        int opcion = sc.nextInt();
        switch (opcion) {
            case 1 -> {
                terminal.userRegistrerMenu();
                int user = sc.nextInt();
                registerUser(user);
            }
            case 2 -> {
                //INICIO DE SESION COMO CLIENTE
                Client client = new Client();
                client = loginClient(client);
                if (client != null) {
                    Menu menu = new Menu();
                    CombatFileReader  combatFileReader = new CombatFileReader();
                    ArrayList<Combat> listCombats = combatFileReader.readCombatFile();
                    for (Combat listCombat : listCombats) {
                        if (!listCombat.isSeen() && listCombat.getChallenger().getNick().equals(client.getNick())) {
                            NotificationManager notificationManager = new NotificationManager();
                            notificationManager.notifyCombat(listCombat);
                            listCombat.setSeen(true);
                        }
                    }
                    CombatFileWriter combatFileWriter = new CombatFileWriter();
                    //combatFileWriter.sobreescribirFicheroCombate(listCombats);

                    ChallengeFileReader challengeFileReader = new ChallengeFileReader();
                    ArrayList<Challenge> listaDesafios = challengeFileReader.readChallengeFile();

                    for (int i = 0; i < listaDesafios.size(); i++) {
                        if (listaDesafios.get(i).isValidated() && listaDesafios.get(i).getRival().getNick().equals(client.getNick())) {
                            NotificationManager notificationManager = new NotificationManager();
                            notificationManager.notifyChallenge(client, terminal, listaDesafios, i);
                            i--;
                        }
                    }
                    menu.selector(client, this);
                }
            }
            case 3 -> {
                //INICIO DE SESION COMO ADMIN
                Administrator admin = new Administrator();
                admin = loginOperator(admin);
                if (admin != null) {
                    Menu menu = new Menu();
                    menu.operatorSelector(admin, this);
                }
            }
            case 4 -> {
                terminal.logout();
                System.exit(0);
            }
            default -> terminal.error();
        }
    }

    public void registerUser(int option) {
            Scanner sc = new Scanner(System.in);
            Terminal terminal = new Terminal();
            switch (option) {
                case 1 -> { // Modo jugador
                    Client client = new Client();
                    UserFileReader userFileReader = new UserFileReader();
                    ArrayList<Client> listClient = userFileReader.userFileReader();

                    terminal.askNameUser();
                    String name = sc.nextLine();
                    client.setName(name);

                    // Solicitar el nick en bucle hasta que sea único.
                    String nick;
                    boolean encontrado;
                    do {
                        terminal.askNick();
                        nick = sc.nextLine();
                        encontrado = false;
                        if (!listClient.isEmpty()) {
                            for (Client value : listClient) {
                                if (value.getNick().equals(nick)) {
                                    terminal.nickExists(); // Notificar que el nick ya existe.
                                    encontrado = true;
                                    break;
                                }
                            }
                        }
                    } while (encontrado);
                    client.setNick(nick);

                    // Pedir la contraseña en bucle hasta que la confirmación sea correcta.
                    String password, confirm;
                    do {
                        terminal.askPassword();
                        password = sc.nextLine();
                        terminal.confirmPassword();
                        confirm = sc.nextLine();
                        if (!password.equals(confirm)) {
                            terminal.errorPassword();
                        }
                    } while (!password.equals(confirm));
                    client.setPassword(password);

                    String registro = client.generateRegisterNumber();
                    client.setRegister(registro);
                    client.setCharacter(null);

                    // Guardar el nuevo usuario
                    UserFileWriter userFileWriter = new UserFileWriter();
                    userFileWriter.userRegister(client);

                    terminal.confirmNewUser(name);
                }

                case 2 -> { // Modo jugador
                    Administrator client = new Administrator();
                    AdministratorFileReader administratorFileReader = new AdministratorFileReader();
                    ArrayList<Administrator> listClient = administratorFileReader.adminFileReader();

                    terminal.askNameUser();
                    String name = sc.nextLine();
                    client.setName(name);
                    // Solicitar el nick en bucle hasta que sea único.
                    String nick;
                    boolean encontrado;
                    do {
                        terminal.askNick();
                        nick = sc.nextLine();
                        encontrado = false;
                        if (!listClient.isEmpty()) {
                            for (Administrator value : listClient) {
                                if (value.getNick().equals(nick)) {
                                    terminal.nickExists(); // Notificar que el nick ya existe.
                                    encontrado = true;
                                    break;
                                }
                            }
                        }
                    } while (encontrado);
                    client.setNick(nick);

                    // Pedir la contraseña en bucle hasta que la confirmación sea correcta.
                    String password, confirm;
                    do {
                        terminal.askPassword();
                        password = sc.nextLine();
                        terminal.confirmPassword();
                        confirm = sc.nextLine();
                        if (!password.equals(confirm)) {
                            terminal.errorPassword();
                        }
                    } while (!password.equals(confirm));
                    client.setPassword(password);

                    // Guardar el nuevo usuario
                    AdministratorFileWriter adminFileWriter = new AdministratorFileWriter();
                    adminFileWriter.adminRegister(client);

                    terminal.confirmNewAdmin(name);
                }
                case 3 -> {
                }
                default -> terminal.error();
            }
        }

    public Client loginClient(Client client) {
        Scanner sc = new Scanner(System.in);
        Terminal terminal = new Terminal();
        int aux = -1;
        UserFileReader userFileReader = new UserFileReader();
        ArrayList<Client> list = userFileReader.userFileReader();
        terminal.askNick();
        String nick = sc.nextLine();
        boolean encontrado = false;
        //comparar nick con lista clientes ficheros
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNick().equals(nick)) {
                encontrado = true;
                aux = i;
                i = list.size();
            }
        }
        if (!encontrado) {
            return null;
        }
        BanFileReader banFileReader = new BanFileReader();
        ArrayList<String> bannedClients = banFileReader.bannedReader();
        if (!bannedClients.isEmpty()) {
            for (String clientBanned : bannedClients) {
                if (nick.equals(clientBanned)) {
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
        ArrayList<Administrator> list = operatorFileReader.adminFileReader();
        terminal.askNick();
        String nick = sc.nextLine();
        boolean found = false;
        int index = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNick().equals(nick)) {
                found = true;
                index = i;
                break;  // Salir del bucle en cuanto se encuentre
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

        // Retornar el administrador encontrado, no el 'operator' pasado como parámetro.
        return list.get(index);
    }


}//FIN
