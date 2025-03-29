package system;

import entities.Client;
import java.util.ArrayList;
import java.io.Console;
import java.util.Scanner;
import entities.Operator;

public class mainSystem {
    public static Console out;
    private static String in;

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
            case 3: {

            }
            default: terminal.error();
        }
    }
    public void registerUser(int option) {
        Scanner sc = new Scanner(System.in);
        Terminal terminal = new Terminal();
        userFileReader userFileReader = new userFileReader();
        ArrayList<Client> clientList = userFileReader.readUserFile();
        switch (option) {
            case 1: {
                Client client = new Client();
                terminal.askNameUser();
                String name = sc.next();
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
                    String password = sc.nextLine();
                    terminal.confirmPassword();
                    String confirm = sc.nextLine();
                    //comprobamos la contraseña;
                    if (!password.equals(confirm)) {
                        terminal.error();
                        break;
                    }
                    client.setPassword(password);
                    String register1 = client.generateRegisterNumber();
                    client.setRegister(register1);
                    client.setCharacter(null);
                    UserFileWriter userFileWriter = new UserFileWriter();
                    userFileWriter.registerUser(client);

                }
            }
            case 2: {
                Operator operator = new Operator();
                OperatorFileReader operatorFileReader = new OperatorFileReader();
                ArrayList<Operator> list = operatorFileReader.readOperatorFile();
                terminal.askNick();
                String name = sc.nextLine();
                operator.setName(name);
                terminal.askNick();
                String nick = sc.nextLine();
                for (Operator value : list) {
                    if ((value.getNick().equals(nick))) {
                        terminal.nickExists();
                        break;
                    }
                }
                operator.setNick(nick);
                terminal.askPassword();
                String password = sc.nextLine();
                terminal.confirmPassword();
                String confirm = sc.nextLine();
                // Verificamos que la contraseña esta bien
                if (!password.equals(confirm)) {
                    terminal.error();
                    break;
                }
                operator.setPassword(password);
                OperatorFileWriter operatorFileWriter = new OperatorFileWriter();
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
        userFileReader userFileReader = new userFileReader();
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

}
