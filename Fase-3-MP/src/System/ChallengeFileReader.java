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

public class ChallengeFileReader {
    private static final String CHALLENGE_FILE_PATH = "Fase-3-MP/src/Files/ChallengeRegister.txt";

    public List<Challenge> readChallenges() {
        List<Challenge> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CHALLENGE_FILE_PATH))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.equals("========== DESAFIO ==========")) {
                    continue;
                }

                Challenge c = new Challenge();

                // DESAFIANTE
                linea = br.readLine(); // DESAFIANTE <nombre>
                if (linea == null) break;
                Client desafiante = new Client();
                desafiante.setName(linea.split(" ", 2)[1]);

                linea = br.readLine(); // NICK <nick>
                desafiante.setNick(linea.split(" ", 2)[1]);

                linea = br.readLine(); // REGISTRO <registro>
                desafiante.setRegister(linea.split(" ", 2)[1]);

                linea = br.readLine(); // TIPO-PERSONAJE <tipo> (lo ignoramos aquí)
                c.setChallenger(desafiante);

                // CONTRINCANTE
                linea = br.readLine(); // CONTRINCANTE <nombre>
                Client rival = new Client();
                rival.setName(linea.split(" ", 2)[1]);

                linea = br.readLine(); // NICK <nick>
                rival.setNick(linea.split(" ", 2)[1]);

                linea = br.readLine(); // REGISTRO <registro>
                rival.setRegister(linea.split(" ", 2)[1]);

                linea = br.readLine(); // TIPO-PERSONAJE <tipo> (lo ignoramos aquí)
                c.setRival(rival);

                // INFORMACIÓN DEL DESAFÍO
                br.readLine(); // ==== INFORMACION DEL DESAFIO ====

                // ORO-APOSTADO
                linea = br.readLine(); // ORO-APOSTADO <n>
                c.setGold(Integer.parseInt(linea.split(" ", 2)[1]));

                // CANTIDAD_MODIFICADORES y lista
                linea = br.readLine();
                String[] textoSeparado = linea.split(" ");

                ArrayList<Modifier> modificadores = new ArrayList<>();
                int tope = Integer.parseInt(textoSeparado[1]);
                if (tope > 0){
                    for (int i = 0; i < tope; i++) {

                        Modifier modificador = new Modifier();

                        //NOMBRE
                        linea = br.readLine();
                        textoSeparado = linea.split(" ");
                        modificador.setName(textoSeparado[1]);

                        //VALOR
                        linea = br.readLine();
                        textoSeparado = linea.split(" ");
                        modificador.setValue((Integer.parseInt(textoSeparado[1])));

                        modificadores.add(modificador);
                    }
                }
                c.setModifiers(modificadores);

                // Posible línea vacía antes de fecha
                br.mark(100);
                linea = br.readLine();
                if (linea != null && linea.trim().isEmpty()) {
                    // es hueco, ok
                } else {
                    br.reset(); // no era vacía, volver atrás
                }

                // FECHA y HORA
                linea = br.readLine(); // FECHA dd-MM-yyyy HH:mm:ss
                String dateStr = linea.split(" ", 2)[1];
                // si viene en una sola línea como "FECHA 29-04-2025 22:32:53", parsea directamente:
                Date fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(dateStr);
                c.setDate(fecha);

                // VALIDADO
                linea = br.readLine(); // VALIDADO <true|false>
                c.setValidated(Boolean.parseBoolean(linea.split(" ", 2)[1]));

                // REGISTRO CHALLENGE
                linea = br.readLine(); // REGISTRO <id>
                c.setRegister(linea.split(" ", 2)[1]);

                // FIN DEL BLOQUE
                br.readLine(); // ========== FIN DESAFIO ==========

                lista.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

}//FIN
