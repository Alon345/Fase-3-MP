package System;

import Entities.Client;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class BanFileWriter {

    private static final String BAN_FILE_PATH = "Fase-3-MP/src/Files/BanRegister.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void banUser(Client client, String motivo, int duracionHoras) {
        try {
            FileWriter fw = new FileWriter(BAN_FILE_PATH, true);
            BufferedWriter bw = new BufferedWriter(fw);
            LocalDateTime banStart = LocalDateTime.now();
            LocalDateTime banEnd = banStart.plusHours(duracionHoras);  // <- Aquí está la suma
            bw.newLine();
            bw.write("========== USUARIO BANEADO ==========");
            bw.newLine();
            bw.write("FECHA Y HORA DEL BANEO: " + banStart.format(formatter));
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
            bw.write("TIPO DE PERSONAJE ");
            bw.newLine();
            bw.write("MOTIVO DEL BANEO: "+motivo);
            bw.newLine();
            bw.write("DURACION HORAS DEL BANEO: "+duracionHoras);
            bw.newLine();
            bw.write("FIN DEL BANEO: "+banEnd.format(formatter));
            bw.newLine();
            bw.write("========== FIN USUARIO BANEADO ==========");
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // (Opcional) Verifica si un usuario sigue baneado
    public boolean isStillBanned(String nick) {
        try (Scanner scanner = new Scanner(new File(BAN_FILE_PATH))) {
            boolean userFound = false;
            String desbanDateStr = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("NICK ") && line.substring(5).equals(nick)) {
                    userFound = true;
                }
                if (userFound && line.startsWith("FECHA DE DESBANEO ")) {
                    desbanDateStr = line.substring("FECHA DE DESBANEO ".length());
                    break;
                }
            }

            if (desbanDateStr != null) {
                LocalDateTime desbanTime = LocalDateTime.parse(desbanDateStr, formatter);
                return LocalDateTime.now().isBefore(desbanTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // No está baneado o ya se le pasó el castigo
    }
    private File ensureFileExists() throws IOException {
        File file = new File(BAN_FILE_PATH);
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            file.createNewFile();
        }
        return file;
    }
    // Método para reescribir el archivo de baneos
    public void rewriteBanFile(List<Client> bannedClients, String motivo, int duracionHoras) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BAN_FILE_PATH))) {
            LocalDateTime banStart = LocalDateTime.now();
            LocalDateTime banEnd = banStart.plusHours(duracionHoras);  // <- Aquí está la suma
            for (Client client: bannedClients) {
                bw.newLine();
                bw.write("========== USUARIO BANEADO ==========");
                bw.newLine();
                bw.write("FECHA Y HORA DEL BANEO: " + banStart.format(formatter));
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
                bw.write("TIPO DE PERSONAJE ");
                bw.newLine();
                bw.write("MOTIVO DEL BANEO: " + motivo);
                bw.newLine();
                bw.write("DURACION HORAS DEL BANEO: " + duracionHoras);
                bw.newLine();
                bw.write("FIN DEL BANEO: "+banEnd.format(formatter));
                bw.newLine();
                bw.write("========== FIN USUARIO BANEADO ==========");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}//FIN


