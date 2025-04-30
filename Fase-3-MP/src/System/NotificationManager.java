package System;

import Entities.Challenge;
import Entities.Client;
import Entities.Combat;
import java.util.ArrayList;
import java.util.Scanner;

public class NotificationManager {

    public void notifyChallenge(Client client, Terminal terminal, ArrayList<Challenge> challenges, int challengeNumber, Client desafiante, String regNumber) {
        int opcion;
        do {
            terminal.askChallenge(challenges.get(challengeNumber));
            opcion = askNum();
        } while (opcion < 1 || opcion > 2);
        if (opcion == 1) { //aceptar
            doCombat(client, terminal, challenges, challengeNumber);
        } else { // 2 = NO ACEPTAR
            doNotAcceptCombat(client, challenges, challengeNumber); //10% oro
            terminal.restandoOro();
        }
        unsubscribeDesafio(challenges.get(challengeNumber), challenges);
    }

    public int askNum() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    private void unsubscribeDesafio(Challenge challenge, ArrayList<Challenge> challenges) {
        for (int numDesafio = 0; numDesafio < challenges.size(); numDesafio++) {
            if (challenge.getRegister().equals(challenges.get(numDesafio).getRegister())) {
                challenges.remove(numDesafio);
                ChallengeFileWriter challengeFileWriter = new ChallengeFileWriter();
                challengeFileWriter.rewriteChallengeFile(challenges);
                break;
            }
        }
    }
    private void doCombat(Client client, Terminal terminal, ArrayList<Challenge> challenges, int numDesafio) {
        int cambioEquipo;
        do {
            terminal.changeTeam();
            cambioEquipo = askNum();
        } while (cambioEquipo < 1 || cambioEquipo > 2);

        if (cambioEquipo == 1) {
            client.selectTeam(client);
        }

        Combat combate = new Combat();
        combate = combate.initializeCombat(
                challenges.get(numDesafio).getChallenger(),
                client,
                challenges.get(numDesafio).getDate(),
                challenges.get(numDesafio).getGold(),
                challenges.get(numDesafio).getModifiers(),
                challenges.get(numDesafio).getRegister()
        );

        combate = combate.startCombatFromFile();

        // Si alguien ganó
        if (combate.getWinner() != null) {
            if (combate.getWinner().getNick().equals(client.getNick())) {
                // Cliente gana
                client.getCharacter().setGold(client.getCharacter().getGold() + challenges.get(numDesafio).getGold());
                UserFileReader userFileReader = new UserFileReader();
                ArrayList<Client> clients = userFileReader.userFileReader();

                for (int numCliente = 0; numCliente < clients.size(); numCliente++) {
                    if (client.getNick().equals(clients.get(numCliente).getNick())) {
                        clients.set(numCliente, client);
                        break;
                    }
                }

                UserFileWriter userFileWriter = new UserFileWriter();
                userFileWriter.rewriteUserFile(clients);

            } else {
                // Cliente pierde
                UserFileReader userFileReader = new UserFileReader();
                ArrayList<Client> clients = userFileReader.userFileReader();

                for (Client clientList : clients) {
                    if (clientList.getNick().equals(challenges.get(numDesafio).getChallenger().getNick())) {
                        clientList.getCharacter().setGold(clientList.getCharacter().getGold() + challenges.get(numDesafio).getGold() * 2);
                        break;
                    }
                }

                client.getCharacter().setGold(client.getCharacter().getGold() - challenges.get(numDesafio).getGold());
                if (client.getCharacter().getGold() < 0) {
                    client.getCharacter().setGold(0);
                }

                for (int numCliente = 0; numCliente < clients.size(); numCliente++) {
                    if (client.getNick().equals(clients.get(numCliente).getNick())) {
                        clients.set(numCliente, client);
                        break;
                    }
                }

                UserFileWriter userFileWriter = new UserFileWriter();
                userFileWriter.rewriteUserFile(clients);
            }
        } else {
            // Empate
            UserFileReader userFileReader = new UserFileReader();
            ArrayList<Client> clients = userFileReader.userFileReader();

            for (Client clientList : clients) {
                if (clientList.getNick().equals(challenges.get(numDesafio).getChallenger().getNick())) {
                    clientList.getCharacter().setGold(clientList.getCharacter().getGold() + challenges.get(numDesafio).getGold());
                    break;
                }
            }
            UserFileWriter userFileWriter = new UserFileWriter();
            userFileWriter.rewriteUserFile(clients);
        }
        CombatFileWriter combatFileWriter = new CombatFileWriter();
        combatFileWriter.combatFileWriter(combate);
        terminal.showClashAnimation();
    }


    private void doNotAcceptCombat(Client cliente, ArrayList<Challenge> challenges, int numDesafio) {
        int penalizacion = challenges.get(numDesafio).getGold() / 10;
        int oroActual = cliente.getCharacter().getGold();
        // Comprobamos que no se quede con oro negativo
        if (oroActual >= penalizacion) {
            cliente.getCharacter().setGold(oroActual - penalizacion);
        } else {
            cliente.getCharacter().setGold(0); // Si no tiene suficiente, se deja en 0
        }
        // Recompensar al desafiante con lo apostado + 10%
        UserFileReader userFileReader = new UserFileReader();
        ArrayList<Client> clients = userFileReader.userFileReader();
        for (Client listaCliente : clients) {
            if (listaCliente.getNick().equals(challenges.get(numDesafio).getChallenger().getNick())) {
                int recompensa = challenges.get(numDesafio).getGold() + penalizacion;
                listaCliente.getCharacter().setGold(listaCliente.getCharacter().getGold() + recompensa);
                break;
            }
        }
        // Actualizar al cliente rechazador en la lista
        for (int numCliente = 0; numCliente < clients.size(); numCliente++) {
            if (cliente.getNick().equals(clients.get(numCliente).getNick())) {
                clients.set(numCliente, cliente); // reemplazar en lugar de remove+add
                break;
            }
        }
        // Guardar los cambios
        UserFileWriter userFileWriter = new UserFileWriter();
        userFileWriter.rewriteUserFile(clients);
    }
}//FIN
