package System;

import Entities.Challenge;
import Entities.Client;
import Entities.Combat;

import java.util.ArrayList;
import java.util.Scanner;

public class NotificationManager {
    public void notifyBan(){

    }
    public void notifyCombat(Combat combat){

    }
    public void notifyDesafio(Client client, Terminal terminal, ArrayList<Challenge> challenges, int challengeNumber) {
        int opcion;
        do {
            terminal.askChallenge(challenges.get(challengeNumber));
            opcion = askNum();
        } while (opcion < 1 || opcion > 2);
        if (opcion == 1) {
            //combat(client, terminal, challenges, challengeNumber);
        } else {
            //doNotAcceptCombat(client, challenges, challengeNumber); //10% oro
        }
        //unsubscribeDesafio(challenges.get(challengeNumber), challenges);
    }

    public int askNum() {
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public void notifyChallenge(Challenge challenge) {
        ChallengeFileWriter ChallengeFileWriter = new ChallengeFileWriter();
        ChallengeFileWriter.challengeRegister(challenge);
    }
}
