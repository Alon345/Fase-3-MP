package System;

import Entities.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CombatFileReader {
    private static final String COMBAT_FILE_PATH = "Fase-3-MP/src/Files/CombatRegister.txt";

    public List<Combat> readCombats() {
        List<Combat> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(COMBAT_FILE_PATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!"========== COMBATE ==========".equals(linea)) continue;

                Combat c = new Combat();

                // — DESAFIANTE —
                linea = br.readLine(); // "DESAFIANTE <nombre>"
                Client desafiante = new Client();
                if (linea != null && linea.contains(" ")) {
                    desafiante.setName(linea.split(" ", 2)[1]);
                }
                // NICK
                linea = br.readLine(); // "NICK <nick>"
                if (linea != null && linea.contains(" ")) {
                    desafiante.setNick(linea.split(" ", 2)[1]);
                }
                // REGISTRO
                linea = br.readLine(); // "REGISTRO <reg>"
                if (linea != null && linea.contains(" ")) {
                    desafiante.setRegister(linea.split(" ", 2)[1]);
                }

                // Posibles líneas TIPO-PERSONAJE y ESBIRROS-CON-VIDA
                br.mark(200);
                linea = br.readLine();
                if (linea == null ||
                        !(linea.startsWith("TIPO-PERSONAJE") || linea.startsWith("ESBIRROS-CON-VIDA"))) {
                    br.reset();
                }
                c.setChallenger(desafiante);

                // — CONTRINCANTE —
                linea = br.readLine(); // "CONTRINCANTE <nombre>"
                Client rival = new Client();
                if (linea != null && linea.contains(" ")) {
                    rival.setName(linea.split(" ", 2)[1]);
                }
                // NICK
                linea = br.readLine();
                if (linea != null && linea.contains(" ")) {
                    rival.setNick(linea.split(" ", 2)[1]);
                }
                // REGISTRO
                linea = br.readLine();
                if (linea != null && linea.contains(" ")) {
                    rival.setRegister(linea.split(" ", 2)[1]);
                }

                // Posibles líneas TIPO-PERSONAJE y ESBIRROS
                br.mark(200);
                linea = br.readLine();
                if (linea == null ||
                        !(linea.startsWith("TIPO-PERSONAJE") || linea.startsWith("ESBIRROS-CON-VIDA"))) {
                    br.reset();
                }
                c.setRival(rival);
                br.readLine();
                // — BLOQUE INFO —
                br.readLine(); // ==== INFORMACION DEL COMBATE ====

                // ORO-APOSTADO
                linea = br.readLine();
                int gold = 0;
                if (linea != null && linea.startsWith("ORO-APOSTADO")) {
                    String[] parts = linea.split(" ", 2);
                    if (parts.length == 2 && !parts[1].isBlank()) {
                        gold = Integer.parseInt(parts[1].trim());
                    }
                }
                c.setGold(gold);

                // CANTIDAD_MODIFICADORES
                linea = br.readLine();
                int tope = 0;
                if (linea != null && linea.startsWith("CANTIDAD_MODIFICADORES")) {
                    String[] parts = linea.split(" ", 2);
                    if (parts.length == 2 && !parts[1].isBlank()) {
                        tope = Integer.parseInt(parts[1].trim());
                    }
                }
                ArrayList<Modifier> mods = new ArrayList<>();
                for (int i = 0; i < tope; i++) {
                    Modifier m = new Modifier();
                    linea = br.readLine(); // "NOMBRE <n>"
                    if (linea != null && linea.contains(" ")) {
                        m.setName(linea.split(" ", 2)[1]);
                    }
                    linea = br.readLine(); // "VALOR <n>"
                    if (linea != null && linea.contains(" ")) {
                        m.setValue(Integer.parseInt(linea.split(" ", 2)[1].trim()));
                    }
                    mods.add(m);
                }
                c.setModifiers(mods);

                // Saltar vacíos hasta FECHA
                do {
                    br.mark(200);
                    linea = br.readLine();
                } while (linea != null && linea.isBlank());
                if (linea != null && linea.startsWith("FECHA")) {
                    String[] parts = linea.split(" ", 2);
                    if (parts.length == 2) {
                        Date fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(parts[1].trim());
                        c.setDate(fecha);
                    }
                }

                // REGISTRO final
                linea = br.readLine();
                if (linea != null && linea.startsWith("REGISTRO")) {
                    String[] parts = linea.split(" ", 2);
                    if (parts.length == 2) {
                        c.setRegister(parts[1].trim());
                    }
                }

                br.readLine(); //saltamos
                // Fin de bloque
                br.readLine(); // ========== FIN COMBATE ==========

                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }


}
