package System;

import Entities.*;

import java.io.*;
import java.util.ArrayList;

public class UserFileReader {

    private static final String USER_FILE_PATH = "Fase-3-MP/src/Files/UserRegister.txt";

    public ArrayList<Client> userFileReader() {
        CharacterFileReader characterFileReader = new CharacterFileReader();
        ArrayList<Client> listaClient = new ArrayList<>();
        FileReader fr = null;
        try {
            File archivo = new File(USER_FILE_PATH);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            // Leer hasta el fin del archivo
            while ((linea = br.readLine()) != null) {
                // Buscamos el separador que indica el inicio de un registro
                if (!linea.trim().equals("========== USUARIO ==========")) {
                    continue;  // Saltar líneas hasta encontrar el separador
                }
                // Una vez encontrado "========== USUARIO ==========", se leen las siguientes líneas
                // para cada campo. Se verifica que la línea no sea null antes de procesarla.
                // Leer Nombre
                String nombreLine = br.readLine();
                if (nombreLine == null) break;
                String[] nombreArr = nombreLine.split(" ");
                String nombre = (nombreArr.length > 1) ? nombreArr[1] : "";

                // Leer Nick
                String nickLine = br.readLine();
                if (nickLine == null) break;
                String[] nickArr = nickLine.split(" ");
                String nick = (nickArr.length > 1) ? nickArr[1] : "";

                // Leer Password
                String passwordLine = br.readLine();
                if (passwordLine == null) break;
                String[] passArr = passwordLine.split(" ");
                String password = (passArr.length > 1) ? passArr[1] : "";

                // Leer Register
                String registerLine = br.readLine();
                if (registerLine == null) break;
                String[] regArr = registerLine.split(" ");
                String register = (regArr.length > 1) ? regArr[1] : "";

                // Leer Character
                String characterLine = br.readLine();
                if (characterLine == null) break;
                String[] charArr = characterLine.split(" ");
                String characterType = (charArr.length > 1) ? charArr[1] : "null";

                // Crear el objeto Client y asignarle los datos leídos
                Client cliente = new Client();
                cliente.setName(nombre);
                cliente.setNick(nick);
                cliente.setPassword(password);
                cliente.setRegister(register);
                cliente.setCharacter(null);  // Por defecto sin personaje

                // Si se ha asignado un tipo de personaje (no "null"), leerlo
                if (!characterType.equals("null")) {
                    switch (characterType) {
                        case "VAMPIRO": {
                            Vampire vampiro = characterFileReader.lecturaVampire(br);
                            cliente.setCharacter(vampiro);
                            break;
                        }
                        case "LICANTROPO": {
                            Werewolf licantropo = characterFileReader.lecturaWerewolf(br);
                            cliente.setCharacter(licantropo);
                            break;
                        }
                        case "CAZADOR": {
                            Hunter cazador = characterFileReader.lecturaHunter(br);
                            cliente.setCharacter(cazador);
                            break;
                        }
                    }
                }
                // Agregar el cliente a la lista
                listaClient.add(cliente);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return listaClient;
    }
}//FIN
