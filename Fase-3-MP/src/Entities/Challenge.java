package Entities;

import java.util.*;

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
        int rivalNum = -1;

        terminal.searchingRivals();
        String myNick = client.getNick();
        Iterator<Client> iterator = clientsList.iterator();

        while (iterator.hasNext()) {
            Client current = iterator.next();
            if (current.getNick().equals(myNick) || current.getCharacter() == null) {
                iterator.remove();
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
                    rivalNum = askForNumber();
                } catch (NumberFormatException e) {
                    terminal.invalidInput();
                }
            } while (rivalNum < 0 || rivalNum > clientsList.size() || clientsList.get(rivalNum - 1).getCharacter() == null);

            setRival(clientsList.get(rivalNum - 1));
            boolean validInput = false;

            terminal.askForGoldBet(client);
            do {
                try {
                    goldAmount = askForNumber();
                    if (goldAmount <= 0) {
                        terminal.lessThanZero();
                    } else if (goldAmount > client.getCharacter().getGold()) {
                        terminal.moreThanMyGold(client.getCharacter().getGold());
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    terminal.invalidInput();
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
            terminal.showClashAnimation();
            terminal.challengeCreated();

            NotificationManager notificationManager = new NotificationManager();
            notificationManager.notifyChallenge(this);

            startCombat();
        }
    }

    public void startCombat() {
        Terminal terminal = new Terminal();
        Character challengerCharacter = challenger.getCharacter();
        Character rivalCharacter = rival.getCharacter();

        terminal.startCombatMessage(challengerCharacter, rivalCharacter);

        int challengerPower = challengerCharacter.getAttack() - rivalCharacter.getDefense();
        int rivalPower = rivalCharacter.getAttack() - challengerCharacter.getDefense();

        String result;
        if (challengerPower > rivalPower) {
            result = "Victoria del desafiante";
            terminal.combatWinner(challenger.getNick(), challengerPower, rivalPower);
            challengerCharacter.setGold(challengerCharacter.getGold() + gold);
            rivalCharacter.setGold(rivalCharacter.getGold() - gold);
        } else if (rivalPower > challengerPower) {
            result = "Victoria del rival";
            terminal.combatWinner(rival.getNick(), rivalPower, challengerPower);
            rivalCharacter.setGold(rivalCharacter.getGold() + gold);
            challengerCharacter.setGold(challengerCharacter.getGold() - gold);
        } else {
            result = "Empate";
            terminal.combatDraw(challengerPower, rivalPower);
        }

        terminal.combatDetails(challenger.getNick(), challengerCharacter.getAttack(), challengerCharacter.getDefense(),
                rival.getNick(), rivalCharacter.getAttack(), rivalCharacter.getDefense());

        // Registrar el combate en el archivo
        registerCombat(result);

        UserFileWriter userFileWriter = new UserFileWriter();
        ArrayList<Client> clientsList = new UserFileReader().userFileReader();
        userFileWriter.rewriteUserFile(clientsList);

        terminal.combatEnd();
    }

    public void registerCombat(String result) {
        String combatLog = "Fecha: " + new Date() + "\n" +
                "Desafiante: " + challenger.getNick() + "\n" +
                "Rival: " + rival.getNick() + "\n" +
                "Resultado: " + result + "\n" +
                "Oro apostado: " + gold + "\n" +
                "-----------------------------------";
        CombatLogger.logCombat(combatLog);
    }

    public int askForNumber() {
        Scanner sc = new Scanner(System.in);
        int number = -1;
        boolean valid = false;

        while (!valid) {
            try {
                System.out.println("Introduce un número válido:");
                number = sc.nextInt();
                if (number > 0) {
                    valid = true;
                } else {
                    System.out.println("Por favor, ingrese un número mayor a 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                sc.nextLine(); // Limpiar el buffer
            } catch (NoSuchElementException e) {
                System.out.println("Error: No se recibió ninguna entrada.");
                break; // Salir del bucle si no hay más entradas
            }
        }
        return number;
    }

    public String generateRegisterNumber() {
        return "CHALLENGE-" + System.currentTimeMillis();
    }

}//FIN
