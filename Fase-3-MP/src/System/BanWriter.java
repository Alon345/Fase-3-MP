package System;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class BanWriter {
    public void banRegister(String nick) {
        try {
            String ruta = "Fase-3-MP/src/Files/BanRegister.txt";
            File file = new File(ruta);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(nick);
            bw.newLine();
            bw.close();

        } catch (Exception e) {
            mainSystem system = new mainSystem();
            system.selector();
            e.printStackTrace();
        }
    }

    public void rewriteBanFile(ArrayList<String> listaBaneados) {

        try {
            String ruta = "Fase-3-MP/src/Files/BanRegister.txt";
            File file = new File(ruta);
            FileWriter fw = new FileWriter(file); //opción append habilitada!
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listaBaneados.size(); i++) {
                bw.write(listaBaneados.get(i));
                bw.newLine();
                bw.close();
            }
        } catch (Exception e) {
            mainSystem system = new mainSystem();
            system.selector();
            e.printStackTrace();
        }
    }

}
