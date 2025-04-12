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
                    ArrayList<Combat> listCombats = combatFileReader.combatFileReader();
                    for (Combat listCombat : listCombats) {
                        if (!listCombat.isSeen() && listCombat.getChallenger().getNick().equals(client.getNick())) {
                            NotificationManager notificationManager = new NotificationManager();
                            notificationManager.notifyCombat(listCombat);
                            listCombat.setSeen(true);
                        }
                    }
                    CombatFileWriter combatFileWriter = new CombatFileWriter();
                    combatFileWriter.overwriteCombatFile(listCombats);

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
                admin = loginAdmin(admin);
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

                    // Validación nombre completo
                    String name;
                    do {
                        terminal.askNameUser();
                        name = sc.nextLine().trim();
                        if(name.isEmpty()) {
                            terminal.emptyName();
                        }
                    } while(name.isEmpty());
                    client.setName(name);

                    // Validación nick (único y no vacío)
                    String nick;
                    boolean nickEncontrado;
                    do {
                        nickEncontrado = false;
                        do {
                            terminal.askNick();
                            nick = sc.nextLine().trim();
                            if(nick.isEmpty()) {
                                terminal.emptyNick();
                            }
                        } while(nick.isEmpty());

                        // Verificar unicidad
                        for (Client c : listClient) {
                            if (c.getNick().equalsIgnoreCase(nick)) {
                                terminal.nickExists();
                                nickEncontrado = true;
                                break;
                            }
                        }
                    } while (nickEncontrado);
                    client.setNick(nick);

                    // Validación contraseña (no vacía y coincidente)
                    String password, confirm;
                    do {
                        do {
                            terminal.askPassword();
                            password = sc.nextLine().trim();
                            if(password.isEmpty()) {
                                terminal.emptyPassword();
                            }
                        } while(password.isEmpty());

                        terminal.confirmPassword();
                        confirm = sc.nextLine().trim();

                        if (!password.equals(confirm)) {
                            terminal.errorPassword();
                        }
                    } while (!password.equals(confirm));
                    client.setPassword(password);

                    // Generación y asignación de datos adicionales
                    client.setRegister(client.generateRegisterNumber());
                    client.setCharacter(null);

                    // Guardar el nuevo usuario
                    new UserFileWriter().userRegister(client);
                    terminal.confirmNewUser(name);
                }

                case 2 -> { // Modo Admin
                        Administrator client = new Administrator();
                        AdministratorFileReader administratorFileReader = new AdministratorFileReader();
                        ArrayList<Administrator> listClient = administratorFileReader.adminFileReader();

                        // Validación nombre (no vacío)
                        String name;
                        do {
                            terminal.askNameUser();
                            name = sc.nextLine().trim();
                            if(name.isEmpty()) {
                                terminal.emptyName(); // Mensaje específico para nombre vacío
                            }
                        } while(name.isEmpty());
                        client.setName(name);

                        // Validación nick (único y no vacío)
                        String nick;
                        boolean nickExists;
                        do {
                            nickExists = false;
                            do {
                                terminal.askNick();
                                nick = sc.nextLine().trim();
                                if(nick.isEmpty()) {
                                    terminal.emptyNick(); // Mensaje específico para nick vacío
                                }
                            } while(nick.isEmpty());

                            // Verificar unicidad (case sensitive)
                            for (Administrator admin : listClient) {
                                if (admin.getNick().equals(nick)) {
                                    terminal.nickExists();
                                    nickExists = true;
                                    break;
                                }
                            }
                        } while (nickExists);
                        client.setNick(nick);

                        // Validación contraseña (no vacía y coincidente)
                        String password, confirm;
                        do {
                            do {
                                terminal.askPassword();
                                password = sc.nextLine().trim();
                                if(password.isEmpty()) {
                                    terminal.emptyPassword(); // Mensaje específico para contraseña vacía
                                }
                            } while(password.isEmpty());

                            terminal.confirmPassword();
                            confirm = sc.nextLine().trim();

                            if (!password.equals(confirm)) {
                                terminal.errorPassword();
                            }
                        } while (!password.equals(confirm));
                        client.setPassword(password);

                        // Registro del administrador
                        new AdministratorFileWriter().adminRegister(client);
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
        if (list.isEmpty()) { //en caso de no haber usuarios en la BD llama de nuevo a sleector
            terminal.noUsersError();
            mainSystem mainSystem = new mainSystem();
            mainSystem.selector();
            return null;
        }
        terminal.askNick();
        String nick = sc.nextLine();
        boolean found = false;
        //Comparar nick con lista clientes ficheros
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNick().equals(nick)) {
                found = true;
                aux = i;
                i = list.size();
            }
        }
        if (!found) {
            terminal.nickNotFoundError(); // Muestra mensaje de error
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

        if (client != null && passCorrect && found) {
            String username = client.getName();
            terminal.hiAgainUser(username);
        }

        return client;
    }

    public Administrator loginAdmin(Administrator admin) {
        Scanner sc = new Scanner(System.in);
        Terminal terminal = new Terminal();
        AdministratorFileReader operatorFileReader = new AdministratorFileReader();
        ArrayList<Administrator> list = operatorFileReader.adminFileReader();
        if (list.isEmpty()) { //en caso de no haber usuarios en la BD llama de nuevo a sleector
            terminal.noUsersError();
            mainSystem mainSystem = new mainSystem();
            mainSystem.selector();
            return null;
        }
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
            terminal.nickNotFoundError(); // Muestra mensaje de error
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
        if (admin != null && passCorrect && found) {
            String username = admin.getName();
            terminal.hiAgainUser(username);
        }
        return admin;
    }
}//FIN
