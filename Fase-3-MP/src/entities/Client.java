package Entities;
import System.mainSystem;
import System.Terminal;
import java.util.ArrayList;
import java.util.Scanner;
import System.UserFileReader;

public class Client extends User {

    /**A continuación se definen atributos**/
    private String register;
    private Character character;

    /**A continuación se definen Getters y Setters**/
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

    public char getLetter() {
        return (char) (Math.random() * 26 + 'a');
    }
    public char getNumber() {
        return (char) (Math.random() * 10 + '0');
    }

    /**A continuación se definen operaciones**/
    public String generateRegisterNumber() {
        UserFileReader userFileReader = new UserFileReader();
        ArrayList<Client> list = userFileReader.readUserFile();
        String strBuilder = null;
        boolean valid = false;

        while (!valid) {
            strBuilder = String.valueOf(getLetter()) +
                    getNumber() +
                    getNumber() +
                    getLetter() +
                    getLetter() +
                    getLetter() +
                    getLetter();

            valid = true; // asumimos que es válido hasta que se demuestre lo contrario

            for (Client client : list) {
                if (client.getRegister().equals(strBuilder)) {
                    valid = false; // se encontró un duplicado, no es válido
                }
            }
        }

        return strBuilder;
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
    public void challenge(Client client) {}

    public void deleteAccount(Client client, mainSystem system) {}
}//FIN
