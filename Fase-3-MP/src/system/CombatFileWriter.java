package System;

import Entities.Combat;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CombatFileWriter {

    public void overwriteCombatFile(List<Combat> combatList, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Combat combat : combatList) {
                bw.write("========== CHALLENGE ==========");
                bw.newLine();
                bw.write("DESAFIANTE " + combat.getChallenger().getName());
                bw.newLine();
                bw.write("NICK " + combat.getChallenger().getNick());
                bw.newLine();
                bw.write("CANTIDAD-ORO " + combat.getChallenger().getGold());
                bw.newLine();
                bw.write("CANTIDAD-VIDA " + combat.getChallenger().getHp());
                bw.newLine();
                bw.write("========== FIN USUARIO ==========");
                bw.newLine();
                bw.write("CONTRINCANTE " + combat.getRival().getName());
                bw.newLine();
                bw.write("NICK " + combat.getRival().getNick());
                bw.newLine();
                bw.write("CANTIDAD-ORO " + combat.getRival().getGold());
                bw.newLine();
                bw.write("CANTIDAD-VIDA " + combat.getRival().getHp());
                bw.newLine();
                bw.write("========== FIN CHALLENGE ==========");
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al sobrescribir el archivo: " + e.getMessage());
        }
    }
}
