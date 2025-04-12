package System;

import Entities.Administrator;
import Entities.Client;
import java.io.*;
import java.util.ArrayList;

public class AdministratorFileWriter {

    // Registra un administrador en el fichero AdminRegister.txt
    public void adminRegister(Administrator admin) {
        try {
            String ruta = "Fase-3-MP/src/Files/AdminRegister.txt"; // Ruta relativa
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            // Se abre en modo adjuntar (true)
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("========== USUARIO ==========");
            bw.newLine();
            bw.write("NOMBRE: " + admin.getName());
            bw.newLine();
            bw.write("NICK: " + admin.getNick());
            bw.newLine();
            bw.write("PASSWORD: " + admin.getPassword());
            bw.newLine();
            bw.write("========== FIN USUARIO ==========");
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
            // En caso de error se puede volver al selector
            new mainSystem().selector();
        }
    }

    // Reescribe el fichero de usuarios (para clientes)
    public void rewriteUserFile(ArrayList<Client> clientArrayList) {
        try {
            String ruta = "Fase-3-MP/src/Files/UserRegister.txt"; // Ruta relativa
            File file = new File(ruta);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            // Recorre la lista de clientes
            for (Client client : clientArrayList) {
                bw.write("=========== USUARIO ===========");
                bw.newLine();
                bw.write("NOMBRE: " + client.getName());
                bw.newLine();
                bw.write("NICK: " + client.getNick());
                bw.newLine();
                bw.write("PASSWORD: " + client.getPassword());
                bw.newLine();
                bw.write("========== FIN USUARIO ==========");
                bw.newLine();
            }
            bw.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            new mainSystem().selector();
        }
    }

    // Elimina el bloque de un administrador cuyo nick coincida exactamente con "NICK: " + adminNick
    public void deleteAdmin(String adminNick) {
        if (adminNick == null || adminNick.isEmpty()) {
            System.out.println("El nick del administrador no puede ser nulo o vacío.");
            return;
        }

        try {
            String ruta = "Fase-3-MP/src/Files/AdminRegister.txt";
            File archivo = new File(ruta);

            if (!archivo.exists()) {
                System.out.println("El archivo no existe.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(archivo));
            ArrayList<String> lines = new ArrayList<>();
            String line;
            boolean adminFound = false;
            boolean insideBlockToDelete = false;
            int blockStartIndex = -1;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("========== USUARIO ==========")) {
                    // Inicio de un nuevo bloque
                    blockStartIndex = lines.size(); // Guardamos dónde empieza este bloque
                    insideBlockToDelete = false; // Reseteamos el flag
                    lines.add(line); // Añadimos la línea de inicio
                }
                else if (line.startsWith("NICK") && line.contains(adminNick)) {
                    // Encontramos el NICK a borrar
                    insideBlockToDelete = true;
                    adminFound = true;
                    // Eliminamos todas las líneas desde blockStartIndex
                    lines = new ArrayList<>(lines.subList(0, blockStartIndex));
                }
                else if (line.startsWith("========== FIN USUARIO ==========")) {
                    // Fin de un bloque
                    if (!insideBlockToDelete) {
                        lines.add(line); // Solo añadimos si no estamos borrando
                    } else {
                        insideBlockToDelete = false; // Terminamos de borrar
                    }
                }
                else {
                    if (!insideBlockToDelete) {
                        lines.add(line); // Añadimos solo si no estamos borrando
                    }
                }
            }
            br.close();

            if (!adminFound) {
                System.out.println("No se encontró el administrador con el nick especificado.");
                return;
            }

            // Reescribir el archivo
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
            bw.close();

            System.out.println("Administrador eliminado correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}


