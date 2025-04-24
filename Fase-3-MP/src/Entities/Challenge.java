package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import System.*;

public class Challenge {
    /** Atributos **/
    private Client rival;
    private Client challenger;
    private int gold;
    private ArrayList<Modifier> modifiers;
    private boolean validated;
    private String register;
    private Date date;

    /** Setters y Getters **/
    public void setRival(Client rival) { this.rival = rival; }
    public Client getRival() { return rival; }

    public void setChallenger(Client challenger) { this.challenger = challenger; }
    public Client getChallenger() { return challenger; }

    public void setGold(int gold) { this.gold = gold; }
    public int getGold() { return gold; }

    public void setModifiers(ArrayList<Modifier> modifiers) { this.modifiers = modifiers; }
    public ArrayList<Modifier> getModifiers() { return modifiers; }

    public void setValidated(boolean validated) { this.validated = validated; }
    public boolean isValidated() { return validated; }

    public void setDate(Date date) { this.date = date; }
    public Date getDate() { return date; }

    public void setRegister(String register) { this.register = register; }
    public String getRegister() { return register; }

    /** Operaciones **/
    public void createChallenge(Client client) {
        Terminal terminal = new Terminal();
        UserFileReader userFileReader = new UserFileReader();
        ArrayList<Client> clientsList = userFileReader.userFileReader();
        terminal.welcomeChallenge();
        int goldAmount = -1;
        int rivalNum = -1;

        if (clientsList.size() == 1) {
            terminal.notAvaliableRival();
        } else {
            terminal.showAvaliableRivals(clientsList);
            do {
                try {
                    terminal.validNumber();
                    rivalNum = askForNumber();
                } catch (NumberFormatException e) {
                    terminal.invalidInput();
                }
            } while (rivalNum < 0 || rivalNum > clientsList.size() || clientsList.get(rivalNum - 1).getCharacter() == null);

            setRival(clientsList.get(rivalNum - 1));
            terminal.askForGoldBet();
            do {
                try {
                    terminal.validNumber();
                    goldAmount = askForNumber();
                } catch (NumberFormatException e) {
                    terminal.invalidInput();
                }
            } while (goldAmount <= 0 || goldAmount > client.getCharacter().getGold());

            setGold(goldAmount);
            client.getCharacter().setGold(client.getCharacter().getGold() - goldAmount);

            for (int numCliente = 0; numCliente < clientsList.size(); numCliente++) {
                if (clientsList.get(numCliente).getNick().equals(client.getNick())) {
                    clientsList.remove(numCliente);
                    clientsList.add(client);
                    break;
                }
            }

            setChallenger(client);
            setValidated(false);
            String registro = generateRegisterNumber();
            setRegister(registro);
            setModifiers(new ArrayList<>());
            setDate(new Date());
            terminal.challengeCreated();

            UserFileWriter userFileWriter = new UserFileWriter();
            userFileWriter.rewriteUserFile(clientsList);

            NotificationManager notificationManager = new NotificationManager();
            notificationManager.notifyChallenge(this);
        }
    }

    public int askForNumber() {
        try (Scanner sc = new Scanner(System.in)) {
            while (!sc.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                sc.next(); // Descartar entrada inválida
            }
            return sc.nextInt();
        }
    }

    public String generateRegisterNumber() {
        return "CHALLENGE-" + System.currentTimeMillis();
    }
}//FIN
