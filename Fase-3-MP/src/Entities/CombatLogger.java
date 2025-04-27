package Entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CombatLogger {
    private static final String FILE_PATH = "Fase-3-MP/src/Files/CombatLog.txt";

    public static void logCombat(String combatLog) {
        File file = new File(FILE_PATH);
        try {
            // Crear directorio y archivo si no existen
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                writer.write(combatLog);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al registrar el combate: " + e.getMessage());
        }
    }

    public static List<String> getCombatLogs() {
        List<String> logs = new ArrayList<>();
        File file = new File(FILE_PATH);
        try {
            // Crear archivo si no existe
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    logs.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer los combates: " + e.getMessage());
        }
        return logs;
    }
}
