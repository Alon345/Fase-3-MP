package System;

import Entities.*;

import java.io.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserFileReader {

    private static final String USER_FILE_PATH = "Fase-3-MP/src/Files/UserRegister.txt";

    public ArrayList<Client> userFileReader() {

        FileReader fr = null;
        ArrayList<Client> listaCliente = new ArrayList<>();

        try {
            File archivo = new File(USER_FILE_PATH);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            br.readLine();

            linea = br.readLine();

            while (linea != null) {
                Client cliente = new Client();

                //NOMBRE
                String[] textoSeparado = linea.split(" ");
                cliente.setName(textoSeparado[1]);

                //NICK
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                cliente.setNick(textoSeparado[1]);

                //PASSWORD
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                cliente.setPassword(textoSeparado[1]);

                //NUMERO_REGISTRO
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                cliente.setRegister(textoSeparado[1]);

                //PERSONAJE
                linea = br.readLine();
                textoSeparado = linea.split(" ");

                if (!textoSeparado[1].equals("null")) {
                    //LECTURA SI ES DE TIPO VAMPIRO
                    switch (textoSeparado[1]) {
                        case "VAMPIRO" -> {
                            Vampire vampire = vampireReader(br);
                            cliente.setCharacter(vampire);
                        }
                        //LECTURA SI ES DE TIPO LICANTROPO
                        case "LICANTROPO" -> {
                            Werewolf werewolf = werewolfReader(br);
                            cliente.setCharacter(werewolf);
                        }
                        //LECTURA SI ES DE TIPO CAZADOR
                        case "CAZADOR" -> {
                            Hunter hunter = hunterReader(br);
                            cliente.setCharacter(hunter);
                        }
                    }
                }
                listaCliente.add(cliente);
                br.readLine();
                linea = br.readLine();
                if (linea != null && linea.equals("========== USUARIO ==========")) {
                    linea = br.readLine();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return listaCliente;
    }

    public static List<Map.Entry<String, Integer>> goldReaderForRanking(String archivo) {
        List<Map.Entry<String, Integer>> clientes = new ArrayList<>();
        String nombre = null;
        Integer oro = null;
        boolean dentroUsuario = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();

                if (linea.equals("========== USUARIO ==========")) {
                    dentroUsuario = true;
                    nombre = null;
                    oro = null;
                } else if (linea.equals("========== FIN USUARIO ==========")) {
                    if (nombre != null) {
                        clientes.add(new AbstractMap.SimpleEntry<>(nombre, oro));
                    }
                    dentroUsuario = false;
                } else if (dentroUsuario) {
                    if (linea.startsWith("NOMBRE ") && nombre == null) {
                        nombre = linea.substring(7).trim();
                    } else if (linea.startsWith("ORO ")) {
                        try {
                            oro = Integer.parseInt(linea.substring(4).trim());
                        } catch (NumberFormatException e) {
                            oro = null; // por si oro no es un número válido
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    //LECTURA PERSONAJES
    public Vampire vampireReader(BufferedReader br) {
        Vampire vampiro = new Vampire();
        Discipline disciplina = new Discipline();

        FileReader fr = null;
        try {
            // Lectura del fichero
            String linea;
            linea = br.readLine();

            while (!linea.equals("========== FIN USUARIO ==========")) {
                //NOMBRE VAMPIRO
                String[] textoSeparado = linea.split(" ");
                vampiro.setType("VAMPIRO");
                vampiro.setName(textoSeparado[1]);

                //SANGRE VAMPIRO
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                vampiro.setBlood(Integer.parseInt(textoSeparado[1]));

                //NOMBRE HABILIDAD
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                disciplina.setName(textoSeparado[1]);

                //VALOR ATAQUE
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                disciplina.setAttack(Integer.parseInt(textoSeparado[1]));

                //VALOR DEFENSA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                disciplina.setDefense(Integer.parseInt(textoSeparado[1]));

                //COSTE HABILIDAD
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                disciplina.setCost(Integer.parseInt(textoSeparado[1]));

                vampiro.setAbility(disciplina);

                //NUMERO DE ARMAS
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                ArrayList<Weapon> armas = new ArrayList<>();
                int tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon arma = new Weapon();

                    //NOMBRE ARMA
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    arma.setName(textoSeparado[1]);

                    //NIVEL ATAQUE ARMA
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    arma.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    //NIVEL DEFENSA ARMA
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    arma.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    //EMPUÑADURA DE ARMA: si es de 1 o 2 manos
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    arma.setSingleHand(textoSeparado[1].equals("true"));

                    armas.add(arma);
                }
                vampiro.setWeapons(armas);

                ArrayList<Weapon> armasActivas = new ArrayList<>();
                //NUMERO DE ARMAS ACTIVAS
                br.readLine();
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon arma = new Weapon();

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    arma.setName(textoSeparado[1]);

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    arma.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    arma.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    arma.setSingleHand(textoSeparado[1].equals("true"));
                    armasActivas.add(arma);
                }
                vampiro.setActiveWeapons(armasActivas);

                //ARMADURAS
                br.readLine();
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                ArrayList<Armor> armaduras = new ArrayList<>();
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {

                    Armor armadura = new Armor();

                    //NOMBRE ARMA
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    armadura.setName(textoSeparado[1]);

                    //NIVEL DEFENSA ARMA
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    armadura.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    //NIVEL ATAQUE ARMA
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    armadura.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    armaduras.add(armadura);
                }
                vampiro.setArmors(armaduras);

                br.readLine();
                Armor armadura = new Armor();

                //NOMBRE ARMADURA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                armadura.setName(textoSeparado[1]);

                //DEFENSA ARMADURA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                armadura.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                //ATAQUE ARMADURA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                armadura.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                vampiro.setActiveArmor(armadura);

                br.readLine();
                //CANTIDAD ORO
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                vampiro.setGold(Integer.parseInt(textoSeparado[1]));

                //EDAD VAMPIRO
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                vampiro.setAge(Integer.parseInt(textoSeparado[1]));

                //CANTIDAD VIDA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                vampiro.setHp(Integer.parseInt(textoSeparado[1]));

                //PODER
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                vampiro.setPower(Integer.parseInt(textoSeparado[1]));

                // FORTALEZAS
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                ArrayList<Strength> fortalezas = new ArrayList<>();
                for (int i = 0; i < tope; i++) {

                    Strength fortaleza = new Strength();

                    //NOMBRE FORTALEZA
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    fortaleza.setName(textoSeparado[1]);

                    //VALOR FORTALEZA
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    fortaleza.setValue((Integer.parseInt(textoSeparado[1])));

                    fortalezas.add(fortaleza);
                }
                vampiro.setStrengths(fortalezas);

                // NUMERO DE DEBILIDADES
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                ArrayList<Weakness> debilidades = new ArrayList<>();
                for (int i = 0; i < tope; i++) {

                    Weakness debilidad = new Weakness();

                    //NOMBRE DE DEBILIADAD
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    debilidad.setName((textoSeparado[1]));

                    //VALOR DEBILIDAD
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    debilidad.setValue((Integer.parseInt(textoSeparado[1])));

                    debilidades.add(debilidad);
                }
                vampiro.setWeaknesses(debilidades);

                //METODO ESBIRRO
                ArrayList<MinionsComposit> listaEsbirros = new ArrayList<>();
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    MinionsComposit esbirro = minionsFile(linea, br, textoSeparado);
                    listaEsbirros.add(esbirro);
                }
                vampiro.setMinions(listaEsbirros);
                linea = "========== FIN USUARIO ==========";
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return vampiro;
    }

    public Hunter hunterReader(BufferedReader br) throws IOException {
        Hunter hunter = new Hunter();
        Talent talent = new Talent();
        String linea;

        // Saltamos hasta la primera línea de datos (evitamos encabezados o vacías)
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty() || linea.startsWith("==========")) {
                continue; // líneas en blanco o marcadores
            }
            break;
        }

        // Ya tenemos la línea "NOMBRE-PERSONAJE X"
        while (linea != null && !linea.equals("========== FIN USUARIO ==========")) {
            String[] partes = linea.split(" ");
            if (partes.length < 2) {
                // Línea inesperada: la saltamos
                linea = br.readLine();
                continue;
            }

            // 1) NOMBRE-PERSONAJE
            hunter.setType("CAZADOR");
            hunter.setName(partes[1]);

            // 2) NOMBRE-HABILIDAD / TALENTO
            linea = nextDataLine(br);
            partes = linea.split(" ");
            if (partes.length > 1) talent.setName(partes[1]);

            // 3) VOLUNTAD
            linea = nextDataLine(br);
            partes = linea.split(" ");
            if (partes.length > 1) hunter.setWillpower(Integer.parseInt(partes[1]));

            // 4) ATAQUE-HABILIDAD
            linea = nextDataLine(br);
            partes = linea.split(" ");
            if (partes.length > 1) talent.setAttack(Integer.parseInt(partes[1]));

            // 5) DEFENSA-HABILIDAD
            linea = nextDataLine(br);
            partes = linea.split(" ");
            if (partes.length > 1) talent.setDefense(Integer.parseInt(partes[1]));

            // 6) EDAD-CAZADOR
            linea = nextDataLine(br);
            partes = linea.split(" ");
            if (partes.length > 1) hunter.setAge(Integer.parseInt(partes[1]));

            hunter.setAbility(talent);

            // 7) ARMAS
            linea = nextDataLine(br);
            partes = linea.split(" ");
            int numArmas = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<Weapon> armas = new ArrayList<>();
            for (int i = 0; i < numArmas; i++) {
                Weapon w = new Weapon();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setName(partes[1]);
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setAttackModifier(Integer.parseInt(partes[1]));
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setDefenseModifier(Integer.parseInt(partes[1]));
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setSingleHand(Boolean.parseBoolean(partes[1]));
                armas.add(w);
            }
            hunter.setWeapons(armas);

            // 8) ARMAS ACTIVAS
            linea = nextDataLine(br);
            partes = linea.split(" ");
            int numAct = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<Weapon> armasAct = new ArrayList<>();
            for (int i = 0; i < numAct; i++) {
                Weapon w = new Weapon();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setName(partes[1]);
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setAttackModifier(Integer.parseInt(partes[1]));
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setDefenseModifier(Integer.parseInt(partes[1]));
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setSingleHand(Boolean.parseBoolean(partes[1]));
                armasAct.add(w);
            }
            hunter.setActiveWeapons(armasAct);

            // 9) ARMADURAS
            linea = nextDataLine(br);
            partes = linea.split(" ");
            int numArmaduras = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<Armor> armaduras = new ArrayList<>();
            for (int i = 0; i < numArmaduras; i++) {
                Armor a = new Armor();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) a.setName(partes[1]);
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) a.setDefenseModifier(Integer.parseInt(partes[1]));
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) a.setAttackModifier(Integer.parseInt(partes[1]));
                armaduras.add(a);
            }
            hunter.setArmors(armaduras);

            // 10) ARMADURA ACTIVA
            linea = nextDataLine(br); partes = linea.split(" ");
            Armor armAct = new Armor();
            if (partes.length > 1) armAct.setName(partes[1]);
            linea = nextDataLine(br); partes = linea.split(" ");
            if (partes.length > 1) armAct.setDefenseModifier(Integer.parseInt(partes[1]));
            linea = nextDataLine(br); partes = linea.split(" ");
            if (partes.length > 1) armAct.setAttackModifier(Integer.parseInt(partes[1]));
            hunter.setActiveArmor(armAct);

            // 11) ORO, VIDA, PODER
            linea = nextDataLine(br); partes = linea.split(" ");
            if (partes.length > 1) hunter.setGold(Integer.parseInt(partes[1]));
            linea = nextDataLine(br); partes = linea.split(" ");
            if (partes.length > 1) hunter.setHp(Integer.parseInt(partes[1]));
            linea = nextDataLine(br); partes = linea.split(" ");
            if (partes.length > 1) hunter.setPower(Integer.parseInt(partes[1]));

            // 12) FORTALEZAS
            linea = nextDataLine(br); partes = linea.split(" ");
            int numF = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<Strength> strengths = new ArrayList<>();
            for (int i = 0; i < numF; i++) {
                Strength s = new Strength();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) s.setName(partes[1]);
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) s.setValue(Integer.parseInt(partes[1]));
                strengths.add(s);
            }
            hunter.setStrengths(strengths);

            // 13) DEBILIDADES
            linea = nextDataLine(br); partes = linea.split(" ");
            int numD = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<Weakness> weaknesses = new ArrayList<>();
            for (int i = 0; i < numD; i++) {
                Weakness w = new Weakness();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setName(partes[1]);
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setValue(Integer.parseInt(partes[1]));
                weaknesses.add(w);
            }
            hunter.setWeaknesses(weaknesses);

            // 14) ESBIRROS
            linea = nextDataLine(br); partes = linea.split(" ");
            int numE = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<MinionsComposit> esbirros = new ArrayList<>();
            for (int i = 0; i < numE; i++) {
                MinionsComposit m = new MinionsComposit();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) m.setName(partes[1]);
                esbirros.add(m);
            }
            hunter.setMinions(esbirros);
            // Sale del bucle; la siguiente lectura en el llamador consumirá "========== FIN USUARIO =========="
            break;
        }
        return hunter;
    }

    public Werewolf werewolfReader(BufferedReader br) throws IOException {
        Werewolf werewolf = new Werewolf();
        Don don = new Don();
        String linea;
        // Saltar líneas vacías y cabeceras
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty() || linea.startsWith("==========")) {
                continue;
            }
            break;
        }
        // Procesar datos hasta encontrar fin de usuario
        while (linea != null && !linea.equals("========== FIN USUARIO ==========")) {
            String[] partes = linea.split(" ");
            if (partes.length < 2) {
                linea = br.readLine();
                continue;
            }

            // 1) TIPO-PERSONAJE
            werewolf.setType("LICANTROPO");

            // 2) NOMBRE-PERSONAJE
            werewolf.setName(partes[1]);

            // 3) NOMBRE-HABILIDAD
            linea = nextDataLine(br);
            partes = linea.split(" ");
            if (partes.length > 1) don.setName(partes[1]);
            //werewolf.setDon(don);

            // 4) RABIA
            linea = nextDataLine(br);
            partes = linea.split(" ");
            if (partes.length > 1) werewolf.setRage(Integer.parseInt(partes[1]));

            // 5) ARMAS
            linea = nextDataLine(br);
            partes = linea.split(" ");
            int numArmas = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<Weapon> armas = new ArrayList<>();
            for (int i = 0; i < numArmas; i++) {
                Weapon w = new Weapon();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setName(partes[1]);
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setAttackModifier(Integer.parseInt(partes[1]));
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setDefenseModifier(Integer.parseInt(partes[1]));
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setSingleHand(Boolean.parseBoolean(partes[1]));
                armas.add(w);
            }
            werewolf.setWeapons(armas);

            // 6) ARMAS ACTIVAS
            linea = nextDataLine(br);
            partes = linea.split(" ");
            int numAct = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<Weapon> armasAct = new ArrayList<>();
            for (int i = 0; i < numAct; i++) {
                Weapon w = new Weapon();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setName(partes[1]);
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setAttackModifier(Integer.parseInt(partes[1]));
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setDefenseModifier(Integer.parseInt(partes[1]));
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setSingleHand(Boolean.parseBoolean(partes[1]));
                armasAct.add(w);
            }
            werewolf.setActiveWeapons(armasAct);

            // 7) ARMADURAS
            linea = nextDataLine(br);
            partes = linea.split(" ");
            int numArmaduras = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<Armor> armaduras = new ArrayList<>();
            for (int i = 0; i < numArmaduras; i++) {
                Armor a = new Armor();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) a.setName(partes[1]);
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) a.setDefenseModifier(Integer.parseInt(partes[1]));
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) a.setAttackModifier(Integer.parseInt(partes[1]));
                armaduras.add(a);
            }
            werewolf.setArmors(armaduras);

            // 8) ARMADURA ACTIVA
            linea = nextDataLine(br); partes = linea.split(" ");
            Armor armAct = new Armor();
            if (partes.length > 1) armAct.setName(partes[1]);
            linea = nextDataLine(br); partes = linea.split(" ");
            if (partes.length > 1) armAct.setDefenseModifier(Integer.parseInt(partes[1]));
            linea = nextDataLine(br); partes = linea.split(" ");
            if (partes.length > 1) armAct.setAttackModifier(Integer.parseInt(partes[1]));
            werewolf.setActiveArmor(armAct);

            // 9) ORO, VIDA, PODER
            linea = nextDataLine(br); partes = linea.split(" ");
            if (partes.length > 1) werewolf.setGold(Integer.parseInt(partes[1]));
            linea = nextDataLine(br); partes = linea.split(" ");
            if (partes.length > 1) werewolf.setHp(Integer.parseInt(partes[1]));
            linea = nextDataLine(br); partes = linea.split(" ");
            if (partes.length > 1) werewolf.setPower(Integer.parseInt(partes[1]));

            // 10) DEBILIDADES
            linea = nextDataLine(br); partes = linea.split(" ");
            int numD = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<Weakness> weaknesses = new ArrayList<>();
            for (int i = 0; i < numD; i++) {
                Weakness w = new Weakness();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes[0].startsWith("NOMBRE-DEBILIADAD")) w.setName(partes[1]);
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) w.setValue(Integer.parseInt(partes[1]));
                weaknesses.add(w);
            }
            werewolf.setWeaknesses(weaknesses);

            // 11) FORTALEZAS
            linea = nextDataLine(br); partes = linea.split(" ");
            int numF = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<Strength> strengths = new ArrayList<>();
            for (int i = 0; i < numF; i++) {
                Strength s = new Strength();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) s.setName(partes[1]);
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) s.setValue(Integer.parseInt(partes[1]));
                strengths.add(s);
            }
            werewolf.setStrengths(strengths);

            // 12) ESBIRROS
            linea = nextDataLine(br); partes = linea.split(" ");
            int numE = (partes.length > 1) ? Integer.parseInt(partes[1]) : 0;
            ArrayList<MinionsComposit> esbirros = new ArrayList<>();
            for (int i = 0; i < numE; i++) {
                MinionsComposit m = new MinionsComposit();
                linea = nextDataLine(br); partes = linea.split(" ");
                if (partes.length > 1) m.setName(partes[1]);
                esbirros.add(m);
            }
            werewolf.setMinions(esbirros);
            break;
        }
        return werewolf;
    }

    private String nextDataLine(BufferedReader br) throws IOException {
        String linea;
        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty() || linea.startsWith("==========")) continue;
            return linea;
        }
        return null;
    }

    private MinionsComposit minionsFile(String linea, BufferedReader br, String[] spaceBtwText) throws NumberFormatException, IOException {
        int tope = Integer.parseInt(spaceBtwText[1]);
        for (int i = 0; i < tope; i++) {
            linea = br.readLine();
            spaceBtwText = linea.split(" "  );
            switch (spaceBtwText[1]) {
                case "HUMANO" -> {
                    Human human = new Human();

                    human.setType(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    human.setName(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    human.setHp((Integer.parseInt(spaceBtwText[1])));
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    if (spaceBtwText[1].equals("ALTA")) {
                        human.setLoyalty(Human.Loyalty.ALTA);
                    } else if (spaceBtwText[1].equals("MEDIA")) {
                        human.setLoyalty(Human.Loyalty.MEDIA);
                    } else {
                        human.setLoyalty(Human.Loyalty.BAJA);
                    }
                    return human;
                }
                case "GHOUL" -> {
                    Ghoul ghoul = new Ghoul();
                    ghoul.setType(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    ghoul.setName(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    ghoul.setHp((Integer.parseInt(spaceBtwText[1])));
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    ghoul.setDependency((Integer.parseInt(spaceBtwText[1])));
                    return ghoul;
                }
                case "DEMONIO" -> {
                    Demon Demon = new Demon();

                    Demon.setType(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    Demon.setName(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    Demon.setHp((Integer.parseInt(spaceBtwText[1])));
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    Demon.setDescripcion(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    ArrayList<MinionsComposit> esbirrosDemon = new ArrayList<>();
                    for (int j = 0; j < (Integer.parseInt(spaceBtwText[1])); j++) {
                        MinionsComposit esbirro = minionsFile(linea, br, spaceBtwText);
                        esbirrosDemon.add(esbirro);
                    }
                    Demon.setMinionsComposites(esbirrosDemon);
                    return Demon;
                }
            }
        }
        return null;
    }
}//FIN
