package System;

import Entities.Client;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class BanFileWriter {

    private static final String BAN_FILE_PATH = "Fase-3-MP/src/Files/BanRegister.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void banUser(Client client, String motivo, int duracionHoras) {
        try {
            FileWriter fw = new FileWriter(BAN_FILE_PATH, true);
            BufferedWriter bw = new BufferedWriter(fw);

            String linea = client.getNick() + "|" + motivo + "|" + LocalDateTime.now() + "|" + duracionHoras;
            bw.write(linea);
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
    public void rewriteBanFile(ArrayList<String> bannedClients) {
        try {
            File file = ensureFileExists(); // Asegurarse de que el archivo existe
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), false); // Sobrescribir el archivo (false para no añadir, sino sobrescribir)
            BufferedWriter bw = new BufferedWriter(fw);

            // Escribir los usuarios baneados en el archivo
            for (String bannedClient : bannedClients) {
                bw.write(bannedClient);
                bw.newLine(); // Escribir una nueva línea por cada usuario baneado
            }

            bw.close(); // Cerrar el BufferedWriter
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}//FIN


