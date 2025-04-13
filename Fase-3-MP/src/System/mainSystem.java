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

    /**
     * A continuación se definen las operaciones
     **/
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
                    CombatFileReader combatFileReader = new CombatFileReader();
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

                // Validación nombre (no vacío)
                String name;
                do {
                    terminal.askNameUser();
                    name = sc.nextLine().trim();
                    if (name.isEmpty()) {
                        terminal.emptyName(); // Mensaje específico para nombre vacío
                    }
                } while (name.isEmpty());
                client.setName(name);

                // Validación nick (único y no vacío)
                String nick;
                boolean nickExists;
                do {
                    nickExists = false;
                    do {
                        terminal.askNick();
                        nick = sc.nextLine().trim();
                        if (nick.isEmpty()) {
                            terminal.emptyNick(); // Mensaje específico para nick vacío
                        }
                    } while (nick.isEmpty());

                    // Verificar unicidad (case sensitive)
                    for (Client c : listClient) {
                        if (c.getNick().equals(nick)) {
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
                        if (password.isEmpty()) {
                            terminal.emptyPassword(); // Mensaje específico para contraseña vacía
                        }
                    } while (password.isEmpty());

                    terminal.confirmPassword();
                    confirm = sc.nextLine().trim();

                    if (!password.equals(confirm)) {
                        terminal.errorPassword();
                    }
                } while (!password.equals(confirm));
                client.setPassword(password);

                // Generación de datos adicionales
                client.setRegister(client.generateRegisterNumber());
                client.setCharacter(null);

                // Registro del usuario
                new UserFileWriter().userRegister(client);
                terminal.confirmNewUser(name);
            }

            case 2 -> { // Modo administrador
                Administrator client = new Administrator();
                AdministratorFileReader administratorFileReader = new AdministratorFileReader();
                ArrayList<Administrator> listClient = administratorFileReader.adminFileReader();

                // Validación nombre (no vacío)
                String name;
                do {
                    terminal.askNameUser();
                    name = sc.nextLine().trim();
                    if (name.isEmpty()) {
                        terminal.emptyName();
                    }
                } while (name.isEmpty());
                client.setName(name);

                // Validación nick (único y no vacío)
                String nick;
                boolean nickExists;
                do {
                    nickExists = false;
                    do {
                        terminal.askNick();
                        nick = sc.nextLine().trim();
                        if (nick.isEmpty()) {
                            terminal.emptyNick();
                        }
                    } while (nick.isEmpty());

                    // Verificar unicidad
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
                        if (password.isEmpty()) {
                            terminal.emptyPassword();
                        }
                    } while (password.isEmpty());

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
        if (list.isEmpty()) {
            terminal.noUsersError();
            return null;
        }
        terminal.askNick();
        String nick = sc.nextLine();
        boolean encontrado = false;

        // Comparar nick con lista de clientes en archivos
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNick().equals(nick)) {
                encontrado = true;
                aux = i;
                break;
            }
        }
        if (!encontrado) {
            terminal.nickNotFoundError();
            return null;
        }

        BanFileReader banFileReader = new BanFileReader();

        // Verificar si el usuario está baneado
        if (banFileReader.isBanned(nick)) {
            // Comprobar si el baneo ha expirado
            if (banFileReader.banHasExpired(nick)) {
                // El baneo ha expirado, desbaneamos al usuario
                banFileReader.removeBannedUser(nick); // Eliminarlo de BanRegister
                banFileReader.reinstateUser(nick); // Reinsertarlo en UserRegister

                terminal.notifyBanExpired();
            } else {
                // Si el baneo no ha expirado
                NotificationManager notificationManager = new NotificationManager();
                notificationManager.notifyBan();
                return null;
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
        if (passCorrect && encontrado) {
            String name = client.getName();
            terminal.hiAgainUser(name);
        }
        return client;
    }

    public Administrator loginOperator(Administrator admin) {
        Scanner sc = new Scanner(System.in);
        Terminal terminal = new Terminal();
        AdministratorFileReader operatorFileReader = new AdministratorFileReader();
        ArrayList<Administrator> list = operatorFileReader.adminFileReader();
        if (list.isEmpty()) {
            terminal.noUsersError();
            return null;
        }
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
            terminal.nickNotFoundError();
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
