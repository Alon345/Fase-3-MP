package System;

import Entities.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Entities.Character;

public class UserFileWriter {

    private static final String USER_FILE_PATH = "Fase-3-MP/src/Files/UserRegister.txt";

    /**A continuación se definen las operaciones de escrituras**/
    public void userRegister(Client client) {
        try {
            File file = new File(USER_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("========== USUARIO ==========");
            bw.newLine();
            bw.write("NOMBRE ");
            bw.write(client.getName());
            bw.newLine();
            bw.write("NICK ");
            bw.write(client.getNick());
            bw.newLine();
            bw.write("PASSWORD ");
            bw.write(client.getPassword());
            bw.newLine();
            bw.write("REGISTRO ");
            bw.write(client.getRegister());
            bw.newLine();
            bw.write("TIPO DE PERSONAJE null");
            bw.newLine();
            bw.write("========== FIN USUARIO ==========");
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            mainSystem system = new mainSystem();
            system.selector();
            e.printStackTrace();
        }
    }

    public void rewriteUserFile(ArrayList<Client> clientArrayList) {
        try {
            File file = new File(USER_FILE_PATH);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            CharacterFileWriter characterFileWriter = new CharacterFileWriter();
            //recorre la lista de usuarios
            for (int i = 0; i < clientArrayList.size(); i++) {
                bw.write("========== USUARIO ==========");
                bw.newLine();
                bw.write("NOMBRE ");
                bw.write(clientArrayList.get(i).getName());
                bw.newLine();
                bw.write("NICK ");
                bw.write(clientArrayList.get(i).getNick());
                bw.newLine();
                bw.write("PASSWORD ");
                bw.write(clientArrayList.get(i).getPassword());
                bw.newLine();
                bw.write("REGISTRO ");
                bw.write(clientArrayList.get(i).getRegister());
                bw.newLine();
                if (clientArrayList.get(i).getCharacter() == null) {
                    bw.write("TIPO PERSONAJE  null");
                    bw.newLine();
                    bw.write("========== FIN USUARIO ==========");
                    bw.newLine();
                } else {
                    String characterType = clientArrayList.get(i).getCharacter().getType();
                    switch (characterType) {
                       case "VAMPIRO" -> characterFileWriter.vampireWriter(clientArrayList, i,clientArrayList.get(i)); //escribimos y guardamos los atributos de los vampiros
                       case "LICANTROPO" -> characterFileWriter.licantropWriter(clientArrayList, i, clientArrayList.get(i)); //idem.
                       case "CAZADOR" -> characterFileWriter.hunterWriter(clientArrayList, i, clientArrayList.get(i)); //idem.

                    }
                }
            }
            bw.write("TIPO PERSONAJE OK");
            bw.newLine();
            bw.write("========== FIN USUARIO ==========");
            bw.close();
        } catch (Exception exception) {
            mainSystem system = new mainSystem();
            system.selector();
            exception.printStackTrace();
        }
    }
} //FIN