package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
        int goldAmount = -1;
        int rivalNum = -1; //será una especie de indice que busque al rival en la lista.

        //debemos eliminar de la lista el cliente que está creando el desafío
        String myNick = client.getNick();
        Iterator<Client> iterator = clientsList.iterator();
        while (iterator.hasNext()) {
            Client current = iterator.next();
            if (current.getNick().equals(myNick)) {
                iterator.remove();
                break; // Si solo quieres eliminarte a ti mismo, puedes salir del bucle
            }else if (current.getCharacter() == null){
                iterator.remove(); // Si no tiene personaje cualquiera, lo eliminamos de la lista
            }
        }

        if (clientsList.isEmpty()) {
            terminal.notAvaliableRival();
        } else {
            terminal.welcomeChallenge();
            terminal.showAvaliableRivals(clientsList);
            do {
                try {
                    terminal.validNumber();
                    rivalNum = askForNumber(); //return sc.nextInt();
                } catch (NumberFormatException e) {
                    terminal.invalidInput();
                }

            } while (rivalNum < 0 || rivalNum > clientsList.size() || clientsList.get(rivalNum - 1).getCharacter() == null);

            setRival(clientsList.get(rivalNum - 1));
            boolean validInput = false;

            terminal.askForGoldBet(client);

            do {
                try {
                    goldAmount = askForNumber(); // Este método debería lanzar NumberFormatException si hay fallo
                    if (goldAmount <= 0) {
                        terminal.lessThanZero();
                    } else if (goldAmount > client.getCharacter().getGold()) {
                        terminal.moreThanMyGold(client.getCharacter().getGold());
                    } else {
                        validInput = true; // cantidad válida
                    }
                } catch (NumberFormatException e) {
                    terminal.invalidInput(); // Mensaje personalizado que ya tienes
                }
            } while (!validInput);

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

            //UserFileWriter userFileWriter = new UserFileWriter();
            //userFileWriter.rewriteUserFile(clientsList);

            NotificationManager notificationManager = new NotificationManager();
            notificationManager.notifyChallenge(this);
        }
    }

    public int askForNumber() {
        Scanner sc = new Scanner(System.in); // Crea el scanner solo cuando sea necesario
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, ingrese un número válido.");
            sc.next(); // Descartar entrada inválida
        }
        return sc.nextInt();
    }

    public String generateRegisterNumber() {
        return "CHALLENGE-" + System.currentTimeMillis();
    }

}//FIN
