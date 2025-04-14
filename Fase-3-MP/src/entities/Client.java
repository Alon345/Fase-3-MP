package Entities;
import Factories.VampireFactory;
import System.mainSystem;
import System.Terminal;
import java.util.ArrayList;
import java.util.Scanner;
import System.UserFileReader;
import System.UserFileWriter;

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
        ArrayList<Client> list = userFileReader.userFileReader();
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
    public void toChallenge(Client cliente) { //desafiar -> toChallenge
        Challenge challenge = new Challenge();
        challenge.createChallenge(cliente);
    }

    public void deleteCharacter(Client client) {
        Terminal terminal = new Terminal();
        terminal.confirmDeleteCharacter();
        Scanner sc = new Scanner(System.in);
        boolean delete = sc.nextInt() == 1;
        if (delete) {
            client.setCharacter(null);
            terminal.deletedCharacter();
        }
    }
    public void selectTeam(Client client) {
        // A implementar
    }
    public void challenge(Client client) {}

    public Vampire createVampire(){return new Vampire();}
    public Hunter createHunter() {
        return new Hunter();
    }

    public Werewolf createWerewolf() {
        return new Werewolf();
    }

        /**
         * Elimina permanentemente una cuenta del sistema
         * @param client Usuario logueado (puede ser Client o Administrator)
         * @param system Referencia al sistema principal
         */
    public void deleteAccount(Client client, mainSystem system) {
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);

        // Mostrar advertencia y solicitar confirmación
        terminal.advertency();
        terminal.writeConfirm();
        // Leer confirmación
        String confirmation = sc.nextLine().trim();

        if (confirmation.equalsIgnoreCase("ELIMINAR")) {
            try {
                // Leer lista actual de clientes
                UserFileReader userFileReader = new UserFileReader();
                ArrayList<Client> clientList = userFileReader.userFileReader();

                // Buscar y eliminar cliente
                boolean removed = clientList.removeIf(c -> c.getRegister().equals(client.getRegister()));

                if (removed) {
                    // Guardar lista actualizada
                    UserFileWriter userFileWriter = new UserFileWriter();
                    userFileWriter.rewriteUserFile(clientList);

                    // Cerrar sesión
                    terminal.deletedAccountOK();
                    terminal.logout();
                    system.selector();
                } else {
                    terminal.noAccountAvaliable();
                }
            } catch (Exception e) {
                terminal.error();
                e.getMessage();
            }
        } else {
            terminal.cancelOperation();
            terminal.closedSesion4Security();
            system.selector();
        }
    }

    public void globalRanking() {
        Terminal terminal = new Terminal();
        terminal.rankingMessage();
        UserFileReader userFileReader = new UserFileReader();
        ArrayList<Client> lista = userFileReader.userFileReader();

        // --- Operaciones  ---
        ArrayList<Client> listaAux = new ArrayList<>(lista); // Copia la lista original
        terminal.showGoldRanking(listaAux);
    }
}//FIN
