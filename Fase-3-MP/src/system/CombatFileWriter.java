package System;

import Entities.*;
import Entities.Character;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CombatFileWriter {
    private static final String COMBAT_FILE_PATH = "Fase-3-MP/src/Files/CombatRegister.txt";

    public void combatFileWriter(Combat Combat) {
        try {
            File file = new File(COMBAT_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("========== COMBATE ==========");
            bw.newLine();
            bw.write("DESAFIANTE ");
            bw.write(Combat.getChallenger().getName());
            bw.newLine();
            bw.write("NICK ");
            bw.write(Combat.getChallenger().getNick());
            bw.newLine();
            bw.write("REGISTRO ");
            bw.write(Combat.getChallenger().getRegister());
            bw.newLine();
            bw.write("TIPO-PERSONAJE ");
            bw.write(Combat.getChallenger().getCharacter().getType());
            bw.newLine();
            bw.write("ESBIRROS-CON-VIDA ");
            //bw.write(Challenge.getChallenger().getCharacter().getMinions());
            bw.newLine();
            //RIVAL
            bw.write("CONTRINCANTE ");
            bw.write(Combat.getRival().getName());
            bw.newLine();
            bw.write("NICK ");
            bw.write(Combat.getRival().getNick());
            bw.newLine();
            bw.write("REGISTRO ");
            bw.write(Combat.getRival().getRegister());
            bw.newLine();
            bw.write("TIPO-PERSONAJE ");
            bw.write(Combat.getChallenger().getCharacter().getType());
            bw.newLine();
            bw.write("ESBIRROS-CON-VIDA ");
            //bw.write(Challenge.getChallenger().getCharacter().getMinions());
            bw.newLine();
            //INFORMACION DEL DESAFIO
            bw.write("==== INFORMACION DEL COMBATE ====");
            bw.newLine();
            bw.write("ORO-APOSTADO ");
            bw.write(String.valueOf(Combat.getGold()));
            bw.newLine();

            bw.write("CANTIDAD_MODIFICADORES ");
            bw.write(String.valueOf(Combat.getModifiers().size()));
            bw.newLine();
            for (int j = 0; j < (Combat.getModifiers().size()); j++) {
                Modifier modificador = Combat.getModifiers().get(j);
                bw.write("NOMBRE_MODIFICADOR ");
                bw.write(modificador.getName());
                bw.newLine();

                bw.write("VALOR_MODIFICADOR ");
                bw.write(String.valueOf(modificador.getValue()));
                bw.newLine();
            }
            bw.newLine();

            String pattern = "dd-MM-yyyy HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(Combat.getDate());

            bw.write("FECHA ");
            bw.write(date);
            bw.newLine();

            //REGISTRO
            bw.write("REGISTRO ");
            bw.write(Combat.getRegister());
            bw.newLine();
            bw.write("ESTADO-COMBATE FINALIZADO");
            bw.newLine();
            bw.write("========== FIN COMBATE ==========");
            bw.close();

        } catch (Exception e) {
            mainSystem mainSystem = new mainSystem();
            mainSystem.selector();
            e.printStackTrace();
        }
    }


    public void rewriteCombatFile(ArrayList<Combat> Combat){
        try {
            File file = new File(COMBAT_FILE_PATH);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            UserFileReader userFileReader = new UserFileReader();
            ArrayList<Client> clients = userFileReader.userFileReader();
            //recorre la lista de desafios
            for (Combat Combates : Combat) {
                bw.write("========== COMBATE ==========");
                bw.newLine();
                bw.write("DESAFIANTE ");
                bw.write(Combates.getChallenger().getName());
                bw.newLine();
                bw.write("NICK ");
                bw.write(Combates.getChallenger().getNick());
                bw.newLine();
                bw.write("REGISTRO ");
                bw.write(Combates.getChallenger().getRegister());
                bw.newLine();
                bw.newLine();
                bw.write("ESBIRROS-CON-VIDA ");
                //bw.write(Challenge.getChallenger().getCharacter().getMinions());
                //RIVAL
                bw.write("CONTRINCANTE ");
                bw.write(Combates.getRival().getName());
                bw.newLine();

                bw.write("NICK ");
                bw.write(Combates.getRival().getNick());
                bw.newLine();

                bw.write("REGISTRO ");
                bw.write(Combates.getRival().getRegister());
                bw.newLine();
                bw.newLine();
                bw.write("ESBIRROS-CON-VIDA ");
                //bw.write(Challenge.getRival().getCharacter().getMinions());

                //INFORMACION DEL DESAFIO
                bw.write("==== INFORMACION DEL COMBATE ====");
                bw.newLine();
                bw.write("ORO-APOSTADO ");
                bw.write(String.valueOf(Combates.getGold()));
                bw.newLine();

                bw.write("CANTIDAD_MODIFICADORES ");
                bw.write(String.valueOf(Combates.getModifiers().size()));
                bw.newLine();
                for (int j = 0; j < (Combates.getModifiers().size()); j++) {
                    Modifier modificador = Combates.getModifiers().get(j);
                    bw.write("NOMBRE_MODIFICADOR ");
                    bw.write(modificador.getName());
                    bw.newLine();

                    bw.write("VALOR_MODIFICADOR ");
                    bw.write(String.valueOf(modificador.getValue()));
                    bw.newLine();
                }
                bw.newLine();

                String pattern = "dd-MM-yyyy HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(Combates.getDate());
                bw.write("FECHA ");
                bw.write(date);
                bw.newLine();

                //REGISTRO
                bw.write("REGISTRO ");
                bw.write(Combates.getRegister());
                bw.newLine();
                bw.write("ESTADO-COMBATE FINALIZADO");
                bw.newLine();
                bw.write("========== FIN COMBATE ==========");
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            mainSystem mainSystem = new mainSystem();
            mainSystem.selector();
            e.printStackTrace();
        }
    }
}
