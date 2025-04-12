package System;

import Entities.Administrator;
import Entities.Client;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class AdministratorFileWriter {
    public void adminRegister(Administrator client) {
        try {
            String ruta = "Fase-3-MP/src/Files/AdminRegister.txt"; //ruta relativa
            File file = new File(ruta);
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
            String ruta = "Fase-3-MP/src/Files/UserRegister.txt"; //ruta relativa
            File file = new File(ruta);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            //recorre la lista de clientes
            for (int i = 0; i < clientArrayList.size(); i++) {

                bw.write("=========== USUARIO ===========");
                bw.newLine();
                bw.write("  NOMBRE: ");
                bw.write(clientArrayList.get(i).getName());
                bw.newLine();
                bw.write("  NICK ");
                bw.write(clientArrayList.get(i).getNick());
                bw.newLine();
                bw.write("  PASSWORD ");
                bw.write(clientArrayList.get(i).getPassword());
                bw.newLine();
                bw.write("========== FIN USUARIO ==========");
                bw.newLine();
            }
            bw.close();
        } catch (Exception exception) {
            mainSystem system = new mainSystem();
            system.selector();
            exception.printStackTrace();
        }
    }
}//FIN
