package System;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class BanFileReader {
        public ArrayList<String> bannedReader(){

            FileReader fr = null;
            ArrayList<String> listBans = new ArrayList<>();
            try {
                File archivo = new File("Fase-3-MP/src/Files/BanRegister.txt");
                if (!archivo.exists()) {
                    archivo.createNewFile();
                }
                fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String line;
                line = br.readLine();
                while (line != null) {
                    listBans.add(line);
                    line = br.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {// En el finally cerramos el fichero
                try {
                    if (null != fr) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            return listBans;
        }
    }//FIN
