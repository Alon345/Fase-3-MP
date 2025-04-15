package System;

import Entities.Challenge;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ChallengeFileWriter {

    public void challengeRegister(Challenge challenge) {
    }

    public void rewriteChallengeFile(ArrayList<Challenge> challenges) {
        try {
            String path = "Fase-3-MP/src/Files/ChallengeRegister.txt";
            FileWriter fw = new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Challenge challenge : challenges) {
                bw.write("========== CHALLENGE ==========");
                bw.newLine();
                bw.write("CHALLENGER " + challenge.getChallenger().getNick());
                bw.newLine();
                bw.write("RIVAL " + challenge.getRival().getNick());
                bw.newLine();
                bw.write("GOLD " + challenge.getGold());
                bw.newLine();
                bw.write("VALIDATED " + challenge.isValidated());
                bw.newLine();
                bw.write("========== FIN CHALLENGE ==========");
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            new Terminal().error();
        }
    }
}
