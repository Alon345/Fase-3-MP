package System;

import Entities.Client;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BanFileReader {
    private static final String BAN_FILE_PATH = "Fase-3-MP/src/Files/BanRegister.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Verifica si un nick está en la lista de baneados
    public boolean isUserBanned(String nick) {
        try (BufferedReader br = new BufferedReader(new FileReader(BAN_FILE_PATH))) {
            String line;
            boolean inBannedBlock = false;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("=========== USUARIO BANEADO ===========")) {
                    inBannedBlock = true;
                } else if (line.startsWith("========== FIN USUARIO BANEADO ==========")) {
                    inBannedBlock = false;
                } else if (inBannedBlock && line.startsWith("NICK: " + nick)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Comprueba si el baneo ha expirado
    public boolean isBanExpired(String nick) {
        try (BufferedReader br = new BufferedReader(new FileReader(BAN_FILE_PATH))) {
            String line;
            boolean userFound = false;
            LocalDateTime banEnd = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("NICK: " + nick)) {
                    userFound = true;
                }

                if (userFound && line.startsWith("FIN BANEO: ")) {
                    banEnd = LocalDateTime.parse(line.substring(11), formatter);
                    break;
                }
            }

            return banEnd != null && LocalDateTime.now().isAfter(banEnd);

        } catch (IOException | DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Elimina baneos expirados automáticamente
    public void removeExpiredBans() {
        List<String> lines = new ArrayList<>();
        boolean keepBlock = true;
        boolean inBlock = false;
        LocalDateTime currentBanEnd = null;

        try (BufferedReader br = new BufferedReader(new FileReader(BAN_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("=========== USUARIO BANEADO ===========")) {
                    inBlock = true;
                    keepBlock = true;
                    currentBanEnd = null;
                } else if (line.startsWith("FIN DEL BANEO: ")) {
                    currentBanEnd = LocalDateTime.parse(line.substring(11), formatter);
                    keepBlock = LocalDateTime.now().isBefore(currentBanEnd);
                } else if (line.startsWith("========== FIN USUARIO BANEADO ==========")) {
                    inBlock = false;
                    if (keepBlock) {
                        lines.add(line);
                    }
                    continue;
                }

                if (inBlock && !keepBlock) continue;
                lines.add(line);
            }
        } catch (IOException | DateTimeParseException e) {
            e.printStackTrace();
        }

        // Reescribir archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BAN_FILE_PATH))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Elimina un usuario específico de la lista de baneados
    public void removeBannedUser(String nick) {
        List<String> lines = new ArrayList<>();
        boolean skipBlock = false;

        try (BufferedReader br = new BufferedReader(new FileReader(BAN_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("NICK: " + nick)) {
                    skipBlock = true;
                }

                if (skipBlock && line.startsWith("========== FIN USUARIO BANEADO ==========")) {
                    skipBlock = false;
                    continue;
                }

                if (!skipBlock) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reescribir archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BAN_FILE_PATH))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Reinserta un usuario en UserRegister
    public void reinstateUser(String nick) {
        UserFileReader userReader = new UserFileReader();
        UserFileWriter userWriter = new UserFileWriter();
        Client client = new Client();

        try (BufferedReader br = new BufferedReader(new FileReader(BAN_FILE_PATH))) {
            String line;
            boolean inBlock = false;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("=========== USUARIO BANEADO ===========")) {
                    inBlock = true;
                } else if (line.startsWith("========== FIN USUARIO BANEADO ==========")) {
                    inBlock = false;
                }

                if (inBlock) {
                    if (line.startsWith("NICK: ")) {
                        client.setNick(line.substring(6).trim());
                    } else if (line.startsWith("NOMBRE: ")) {
                        client.setName(line.substring(8).trim());
                    } else if (line.startsWith("REGISTRO: ")) {
                        client.setRegister(line.substring(10).trim());
                    }
                }
            }

            // Añadir a UserRegister
            if (client.getNick() != null) {
                ArrayList<Client> users = userReader.userFileReader();
                users.add(client);
                userWriter.rewriteUserFile(users);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Client> readBannedUsers() {
        ArrayList<Client> bannedClients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BAN_FILE_PATH))) {
            String line;
            Client currentClient = null;
            boolean inBannedBlock = false;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("=========== USUARIO BANEADO ===========")) {
                    // Inicio de bloque de usuario baneado
                    currentClient = new Client();
                    inBannedBlock = true;
                }
                else if (line.startsWith("========== FIN USUARIO BANEADO ==========")) {
                    // Fin de bloque, añadir a la lista
                    if (currentClient != null && currentClient.getNick() != null) {
                        bannedClients.add(currentClient);
                    }
                    currentClient = null;
                    inBannedBlock = false;
                }
                else if (inBannedBlock && currentClient != null) {
                    // Parsear campos del usuario
                    if (line.startsWith("NICK: ")) {
                        currentClient.setNick(line.substring(6).trim());
                    }
                    else if (line.startsWith("NOMBRE: ")) {
                        currentClient.setName(line.substring(8).trim());
                    }
                    else if (line.startsWith("REGISTRO: ")) {
                        currentClient.setRegister(line.substring(10).trim());
                    }
                    // Añadir más campos según sea necesario
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return bannedClients;
    }
}