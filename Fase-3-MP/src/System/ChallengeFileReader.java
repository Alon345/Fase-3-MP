package System;

import Entities.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChallengeFileReader {
    private static final String CHALLENGE_FILE_PATH = "Fase-3-MP/src/Files/ChallengeRegister.txt";

    public ArrayList<Challenge> readChallengeFile() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        ArrayList<Challenge> listaChallenge = new ArrayList<>();

        try {
            archivo = new File(CHALLENGE_FILE_PATH);
            if (!archivo.exists()) {
                archivo.createNewFile();
            }
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea = br.readLine();
            while (linea != null) {
                Challenge challenge = new Challenge();
                Client client = new Client();

                // NOMBRE
                if (linea == null) break;
                String[] textoSeparado = linea.split(" ");
                client.setName(textoSeparado[1]);

                // NICK
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                client.setNick(textoSeparado[1]);

                // PASSWORD
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                client.setPassword(textoSeparado[1]);

                // NUMERO-REGISTRO
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                client.setRegister(textoSeparado[1]);

                // PERSONAJE
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                if (!textoSeparado[1].equals("null")) {
                    switch (textoSeparado[1]) {
                        case "VAMPIRO"   -> client.setCharacter(vampireChaReader(br));
                        case "LICANTROPO"-> client.setCharacter(werewolfChaReader(br));
                        case "CAZADOR"   -> client.setCharacter(hunterChaReader(br));
                    }
                }

                challenge.setChallenger(client);

                // RIVAL
                client = new Client();
                do {
                    linea = br.readLine();
                    if (linea == null) break;
                    textoSeparado = linea.split(" ");
                } while (!textoSeparado[0].equals("RIVAL"));
                if (linea == null) break;
                client.setName(textoSeparado[1]);

                // NICK
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                client.setNick(textoSeparado[1]);

                // PASSWORD
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                client.setPassword(textoSeparado[1]);

                // NUMERO-REGISTRO
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                client.setRegister(textoSeparado[1]);

                // PERSONAJE
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                if (!textoSeparado[1].equals("null")) {
                    switch (textoSeparado[1]) {
                        case "VAMPIRO"   -> client.setCharacter(vampireChaReader(br));
                        case "LICANTROPO"-> client.setCharacter(werewolfChaReader(br));
                        case "CAZADOR"   -> client.setCharacter(hunterChaReader(br));
                    }
                }

                challenge.setRival(client);

                // ORO
                do {
                    linea = br.readLine();
                    if (linea == null) break;
                    textoSeparado = linea.split(" ");
                } while (!textoSeparado[0].equals("ORO"));
                if (linea == null) break;
                challenge.setGold(Integer.parseInt(textoSeparado[1]));

                // Modifier
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                ArrayList<Modifier> modifiers = new ArrayList<>();
                int tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    Modifier mod = new Modifier();

                    // NOMBRE
                    linea = br.readLine();
                    if (linea == null) break;
                    textoSeparado = linea.split(" ");
                    mod.setName(textoSeparado[1]);

                    // VALOR
                    linea = br.readLine();
                    if (linea == null) break;
                    textoSeparado = linea.split(" ");
                    mod.setValue(Integer.parseInt(textoSeparado[1]));

                    modifiers.add(mod);
                }
                challenge.setModifiers(modifiers);

                // Saltamos línea en blanco si hay
                br.readLine();

                // FECHA
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                Date fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(textoSeparado[1]);
                challenge.setDate(fecha);

                // VALIDADO
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                challenge.setValidated(textoSeparado[1].equals("true"));

                // REGISTRO
                linea = br.readLine();
                if (linea == null) break;
                textoSeparado = linea.split(" ");
                challenge.setRegister(textoSeparado[1]);

                listaChallenge.add(challenge);

                // Saltamos separadores finales
                br.readLine();
                br.readLine();
                linea = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fr != null) fr.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return listaChallenge;
    }


    private Vampire vampireChaReader(BufferedReader br) {
        Vampire Vampire = new Vampire();
        Discipline Discipline = new Discipline();

        FileReader fr = null;
        try {
            // Lectura del fichero
            String linea;
            linea = br.readLine();

            while (!linea.equals("========== FIN USUARIO ==========")) {

                //TIPO USUARIO
                String[] textoSeparado = linea.split(" ");
                Vampire.setType("VAMPIRO");

                //NOMBRE Vampire
                Vampire.setName(textoSeparado[1]);

                //SANGRE
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Vampire.setBlood(Integer.parseInt(textoSeparado[1]));

                //NOMBRE HABILIDAD
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Discipline.setName(textoSeparado[1]);

                //VALOR ATAQUE
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Discipline.setAttack(Integer.parseInt(textoSeparado[1]));

                //VALOR DEFENSA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Discipline.setDefense(Integer.parseInt(textoSeparado[1]));

                //COSTE HABILIDAD
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Discipline.setCost(Integer.parseInt(textoSeparado[1]));

                Vampire.setAbility(Discipline);

                //NUMERO DE WeaponS
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                ArrayList<Weapon> Weapons = new ArrayList<>();
                int tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon Weapon = new Weapon();

                    //NOMBRE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setName(textoSeparado[1]);

                    //NIVEL ATAQUE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    //NIVEL DEFENSA Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    //EMPUÑADURA DE Weapon: si es de 1 o 2 manos
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setSingleHand(textoSeparado[1].equals("true"));
                    Weapons.add(Weapon);
                }
                Vampire.setWeapons(Weapons);
                br.readLine();
                linea = br.readLine();
                textoSeparado = linea.split(" ");

                //NUMERO DE WeaponS ACTIVAS
                ArrayList<Weapon> WeaponsActiva = new ArrayList<>();
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon Weapon = new Weapon();

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setName(textoSeparado[1]);

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setSingleHand(textoSeparado[1].equals("true"));
                    WeaponsActiva.add(Weapon);
                }
                Vampire.setActiveWeapons(WeaponsActiva);

                //ArmorS
                br.readLine();
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                ArrayList<Armor> Armors = new ArrayList<>();
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {

                    Armor Armor = new Armor();

                    //NOMBRE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Armor.setName(textoSeparado[1]);

                    //NIVEL DEFENSA Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Armor.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    //NIVEL ATAQUE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Armor.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    Armors.add(Armor);
                }
                Vampire.setArmors(Armors);

                //Armor ACTIVA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor Armor = new Armor();

                //NOMBRE Armor
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor.setName(textoSeparado[1]);

                //NIVEL DEFENSA Armor
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                //NIVEL ATAQUE Armor
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                Vampire.setActiveArmor(Armor);
                br.readLine();

                //EDAD Vampire
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Vampire.setAge(Integer.parseInt(textoSeparado[1]));

                //METODO ESBIRRO
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                ArrayList<MinionsComposit> listaEsbirros = new ArrayList<>();
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    MinionsComposit esbirro = minionsFile(linea, br, textoSeparado);
                    listaEsbirros.add(esbirro);
                }
                Vampire.setMinions(listaEsbirros);

                //CANTIDAD ORO
                do {
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                } while (!textoSeparado[0].equals("CANTIDAD-ORO"));
                Vampire.setGold(Integer.parseInt(textoSeparado[1]));

                //CANTIDAD VIDA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Vampire.setHp(Integer.parseInt(textoSeparado[1]));

                //PODER
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Vampire.setPower(Integer.parseInt(textoSeparado[1]));

                // NUMERO DE WeaknessES
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                ArrayList<Weakness> Weaknesses = new ArrayList<>();
                for (int i = 0; i < tope; i++) {

                    Weakness Weakness = new Weakness();

                    //NOMBRE DE DEBILIADAD
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weakness.setName((textoSeparado[1]));

                    //VALOR Weakness
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weakness.setValue((Integer.parseInt(textoSeparado[1])));

                    Weaknesses.add(Weakness);
                }
                Vampire.setWeaknesses(Weaknesses);
                br.readLine();
                // StrengthS
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                ArrayList<Strength> Strengths = new ArrayList<>();
                for (int i = 0; i < tope; i++) {
                    Strength Strength = new Strength();

                    //NOMBRE Strength
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Strength.setName(textoSeparado[1]);

                    //VALOR Strength
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Strength.setValue((Integer.parseInt(textoSeparado[1])));

                    Strengths.add(Strength);
                }
                Vampire.setStrengths(Strengths);
                br.readLine();
                linea = br.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return Vampire;
    }

    private Hunter hunterChaReader(BufferedReader br) {
        Hunter Hunter = new Hunter();
        Talent Talent = new Talent();

        FileReader fr = null;
        ArrayList<Client> listaHunter = new ArrayList<>();
        try {
            // Lectura del fichero
            String linea;
            linea = br.readLine();

            while (!linea.equals("========== FIN USUARIO ==========")) {

                //TIPO PERSONAJE
                String[] textoSeparado = linea.split(" ");
                Hunter.setType(textoSeparado[1]);

                //NOMBRE PERSONAJE
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Hunter.setName(textoSeparado[1]);

                //VOLUNTAD Hunter
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Hunter.setName(textoSeparado[1]);

                //NOMBRE HABILIDAD
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Talent.setName(textoSeparado[1]);

                //VALOR ATAQUE
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Talent.setAttack(Integer.parseInt(textoSeparado[1]));

                //VALOR DEFENSA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Talent.setDefense(Integer.parseInt(textoSeparado[1]));

                //EDAD HABILIDAD
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Talent.setAge(Integer.parseInt(textoSeparado[1]));

                Hunter.setAbility(Talent);

                //NUMERO DE WeaponS
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                int tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon Weapon = new Weapon();

                    //NOMBRE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setName(textoSeparado[1]);

                    //NIVEL ATAQUE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    //NIVEL DEFENSA Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    //EMPUÑADURA DE Weapon: si es de 1 o 2 manos
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setSingleHand(textoSeparado[1].equals("true"));

                    Hunter.getWeapons().add(Weapon);
                }

                //NUMERO DE WeaponS ACTIVAS
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    Weapon Weapon = new Weapon();

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setName(textoSeparado[1]);

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setSingleHand(textoSeparado[1].equals("true"));
                    Hunter.getActiveWeapons().add(Weapon);
                }

                //ArmorS
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    Armor Armor = new Armor();

                    //NOMBRE Armor
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Armor.setName(textoSeparado[1]);

                    //NIVEL DEFENSA Armor
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Armor.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    //NIVEL ATAQUE Armor
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Armor.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    Hunter.getArmors().add(Armor);
                }

                //Armor ACTIVA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor Armor = new Armor();

                //NOMBRE Armor
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor.setName(textoSeparado[1]);

                //NIVEL DEFENSA Weapon
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                //NIVEL ATAQUE Weapon
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                Hunter.setActiveArmor(Armor);

                //CANTIDAD ORO
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Hunter.setGold(Integer.parseInt(textoSeparado[1]));

                //CANTIDAD VIDA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Hunter.setHp(Integer.parseInt(textoSeparado[1]));

                //PODER
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Hunter.setPower(Integer.parseInt(textoSeparado[1]));

                // NUMERO DE WeaknessES
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    Weakness Weakness = new Weakness();

                    //NOMBRE DE DEBILIADAD
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weakness.setName((textoSeparado[1]));

                    //VALOR Weakness
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weakness.setValue((Integer.parseInt(textoSeparado[1])));

                    Hunter.getWeaknesses().add(Weakness);
                }

                // StrengthS
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    Strength Strength = new Strength();

                    //NOMBRE Strength
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Strength.setName(textoSeparado[1]);

                    //VALOR Strength
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Strength.setValue((Integer.parseInt(textoSeparado[1])));

                    Hunter.getStrengths().add(Strength);
                }

                //METODO ESBIRRO
                ArrayList<MinionsComposit> listaEsbirros = new ArrayList<>();

                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    MinionsComposit esbirro = minionsFile(linea, br, textoSeparado);
                    listaEsbirros.add(esbirro);
                }
                Hunter.setMinions(listaEsbirros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return Hunter;
    }

    private Werewolf werewolfChaReader(BufferedReader br) {
        Werewolf Werewolf = new Werewolf();
        Don don = new Don();

        FileReader fr = null;
        ArrayList<Client> listaVampire = new ArrayList<>();
        try {
            // Lectura del fichero
            String linea;

            linea = br.readLine();

            while (!linea.equals("========== FIN USUARIO ==========")) {

                //TIPO PERSONAJE
                String[] textoSeparado = linea.split(" ");
                Werewolf.setType(textoSeparado[1]);

                //NOMBRE PERSONAJE
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Werewolf.setName(textoSeparado[1]);

                //RABIA Werewolf
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Werewolf.setRage(Integer.parseInt(textoSeparado[1]));

                //NOMBRE HABILIDAD
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                don.setName(textoSeparado[1]);

                //VALOR ATAQUE
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                don.setAttack(Integer.parseInt(textoSeparado[1]));

                //VALOR DEFENSA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                don.setDefense(Integer.parseInt(textoSeparado[1]));

                //NUMERO DE WeaponS
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                int tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    Weapon Weapon = new Weapon();

                    //NOMBRE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setName(textoSeparado[1]);

                    //NIVEL ATAQUE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    //NIVEL DEFENSA Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    //EMPUÑADURA DE Weapon: si es de 1 o 2 manos
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setSingleHand(textoSeparado[1].equals("true"));

                    Werewolf.getWeapons().add(Weapon);
                }

                //NUMERO DE WeaponS ACTIVAS
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    Weapon Weapon = new Weapon();
                    //NOMBRE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setName(textoSeparado[1]);
                    //VALOR ATAQUE
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setAttackModifier((Integer.parseInt(textoSeparado[1])));
                    //VALOR DEFENSA
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setDefenseModifier((Integer.parseInt(textoSeparado[1])));
                    //EMPULADURA DE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weapon.setSingleHand(textoSeparado[1].equals("true"));
                    Werewolf.getActiveWeapons().add(Weapon);
                }

                //ArmorS
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    Armor Armor = new Armor();

                    //NOMBRE Armor
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Armor.setName(textoSeparado[1]);

                    //NIVEL DEFENSA Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Armor.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                    //NIVEL ATAQUE Weapon
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Armor.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                    Werewolf.getArmors().add(Armor);
                }

                //Armor ACTIVA
                Armor Armor = new Armor();
                linea = br.readLine();
                textoSeparado = linea.split(" ");

                //NOMBRE Armor      *********MIRAR ESTO***** POSIBLEMENTE SE ME HAYA COLADO Y HAY QUE QUITARLO
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor.setName(textoSeparado[1]);

                //NIVEL DEFENSA Weapon
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor.setDefenseModifier((Integer.parseInt(textoSeparado[1])));

                //NIVEL ATAQUE Weapon
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Armor.setAttackModifier((Integer.parseInt(textoSeparado[1])));

                Werewolf.setActiveArmor(Armor);

                //CANTIDAD ORO
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Werewolf.setGold(Integer.parseInt(textoSeparado[1]));

                //CANTIDAD VIDA
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Werewolf.setHp(Integer.parseInt(textoSeparado[1]));

                //PODER
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                Werewolf.setPower(Integer.parseInt(textoSeparado[1]));

                // NUMERO DE WeaknessES
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    Weakness Weakness = new Weakness();

                    //NOMBRE DE DEBILIADAD
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weakness.setName((textoSeparado[1]));

                    //VALOR Weakness
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Weakness.setValue((Integer.parseInt(textoSeparado[1])));

                    Werewolf.getWeaknesses().add(Weakness);
                }

                // StrengthS
                linea = br.readLine();
                textoSeparado = linea.split(" ");
                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    Strength Strength = new Strength();

                    //NOMBRE Strength
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Strength.setName(textoSeparado[1]);

                    //VALOR Strength
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Strength.setValue((Integer.parseInt(textoSeparado[1])));

                    Werewolf.getStrengths().add(Strength);
                }

                //METODO ESBIRRO
                ArrayList<MinionsComposit> listaEsbirros = new ArrayList<>();

                tope = Integer.parseInt(textoSeparado[1]);
                for (int i = 0; i < tope; i++) {
                    MinionsComposit esbirro = minionsFile(linea, br, textoSeparado);
                    listaEsbirros.add(esbirro);
                }
                Werewolf.setMinions(listaEsbirros);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return Werewolf;
    }

    private MinionsComposit minionsFile(String linea, BufferedReader br, String[] textoSeparado) throws IOException {
        // ESBIRROS
        int tope = Integer.parseInt(textoSeparado[1]);
        for (int i = 0; i < tope; i++) {
            linea = br.readLine();
            textoSeparado = linea.split(" ");
            switch (textoSeparado[1]) {
                case "HUMANO" -> {  //BORRAR PARA VampireS

                    Human human = new Human();

                    //TIPO ESBIRRO
                    human.setType(textoSeparado[1]);

                    //NOMBRE ESBIRRO
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    human.setName(textoSeparado[1]);

                    //VIDA ESBIRRO
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    human.setHp((Integer.parseInt(textoSeparado[1])));

                    //VALOR LEALTAD     TIPOS: ALTA, MEDIO, BAJO que coresponde a 0,1,2
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    if (textoSeparado[1].equals("ALTA")) {
                        human.setLoyalty(Human.Loyalty.ALTA);
                    } else if (textoSeparado[1].equals("MEDIA")) {
                        human.setLoyalty(Human.Loyalty.MEDIA);
                    } else {
                        human.setLoyalty(Human.Loyalty.BAJA);
                    }
                    return human;
                }
                case "GHOUL" -> {
                    Ghoul ghoul = new Ghoul();

                    //TIPO ESBIRRO
                    ghoul.setType(textoSeparado[1]);

                    //NOMBRE ESBIRRO
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    ghoul.setName(textoSeparado[1]);

                    //VIDA ESBIRRO
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    ghoul.setHp((Integer.parseInt(textoSeparado[1])));

                    //DEPENDENCIA
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    ghoul.setDependency((Integer.parseInt(textoSeparado[1])));
                    return ghoul;
                }
                case "DEMONIOS" -> {
                    Demon Demon = new Demon();

                    //TIPO ESBIRRO
                    Demon.setType(textoSeparado[1]);

                    //NOMBRE ESBIRRO
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Demon.setName(textoSeparado[1]);

                    //VIDA ESBIRRO
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Demon.setHp((Integer.parseInt(textoSeparado[1])));

                    //DESCRIPCION
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    Demon.setDescripcion(textoSeparado[1]);
                    linea = br.readLine();
                    textoSeparado = linea.split(" ");
                    ArrayList<MinionsComposit> esbirrosDemon = new ArrayList<>();
                    tope = Integer.parseInt(textoSeparado[1]);
                    for (int j = 0; j < tope; j++) {
                        MinionsComposit esbirro = minionsFile(linea, br, textoSeparado);
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
