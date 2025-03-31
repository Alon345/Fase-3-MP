package entities;
import system.mainSystem;
import system.Terminal;
import java.util.ArrayList;
import java.util.Scanner;
import system.userFileReader;

public class Client extends User {

    private String register;
    private Character character;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public String generateRegisterNumber() {
        boolean valid = false;
        userFileReader userFileReader = new userFileReader();
        ArrayList<Client> list = userFileReader.readUserFile();
        String strBuilder = null;
        // Creamos el fichero lista de clientes para comparar si esta ya
        while (!valid) {
            strBuilder = String.valueOf(getLetter()) +
                    getNumber() +
                    getNumber() +
                    getLetter() +
                    getLetter() +
                    getLetter() +
                    getLetter();
            if (!list.isEmpty()) {
                for (Client value : list) {
                    if (!(value.getRegister().equals(strBuilder))) {
                        valid = true;
                    } else {
                        strBuilder = null;
                    }
                }
            } else {
                valid = true;
            }
        }
        return strBuilder;
    }
    public char getLetter() {
        return (char) (Math.random() * 26 + 'a');
    }

    public char getNumber() {
        return (char) (Math.random() * 10 + '0');
    }

    public void deleteCharacter(Client client) {
        Terminal terminal = new Terminal();
        terminal.confirmDeleteCharacter();
        Scanner sc = new Scanner(System.in);
        boolean delete = sc.nextInt() == 1;
        if (delete) {
            client.setCharacter(null);
            terminal.deltedCharacter();
        }
    }
    public void selectTeam(Client client) {
        // A implementar
    }
    public void challenge(Client client) {

    }
    public void deleteAccount(Client client, mainSystem system) {

    }
}
