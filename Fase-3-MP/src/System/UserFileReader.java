package System;

import Entities.*;

import java.io.*;
import java.util.ArrayList;

public class UserFileReader {
    public ArrayList<Client> userFileReader() {
        ArrayList<Client> listaClient = new ArrayList<>();
        FileReader fr = null;
        try {
            File archivo = new File("Fase-3-MP/src/Files/UserRegister.txt");
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
                            Vampire vampiro = lecturaVampire(br);
                            cliente.setCharacter(vampiro);
                            break;
                        }
                        case "LICANTROPO": {
                            Werewolf licantropo = lecturaWerewolf(br);
                            cliente.setCharacter(licantropo);
                            break;
                        }
                        case "CAZADOR": {
                            Hunter cazador = lecturaHunter(br);
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

    private Vampire lecturaVampire(BufferedReader br) {
        Vampire Vampire = new Vampire();
        Discipline Discipline = new Discipline();

        FileReader fr = null;
        try {
            // Lectura del fichero
            String linea;
            linea = br.readLine();

            while (!linea.equals("FIN_USUARIO")) {
                //NOMBRE Vampire
                String[] spaceBtwText = linea.split(" "  );
                Vampire.setType("Vampire");
                Vampire.setName(spaceBtwText[1]);

                //SANGRE Vampire
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Vampire.setBlood(Integer.parseInt(spaceBtwText[1]));

                //NOMBRE HABILIDAD
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Discipline.setName(spaceBtwText[1]);

                //VALOR ATAQUE
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Discipline.setAttack(Integer.parseInt(spaceBtwText[1]));

                //VALOR DEFENSA
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Discipline.setDefense(Integer.parseInt(spaceBtwText[1]));

                //COSTE HABILIDAD
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Discipline.setCost(Integer.parseInt(spaceBtwText[1]));

                Vampire.setAbility(Discipline);

                //NUMERO DE WeaponS
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                ArrayList<Weapon> Weapons = new ArrayList<>();
                int tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon Weapon = new Weapon();

                    //NOMBRE Weapon
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setName(spaceBtwText[1]);

                    //NIVEL ATAQUE Weapon
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setAttackModifier((Integer.parseInt(spaceBtwText[1])));

                    //NIVEL DEFENSA Weapon
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));

                    //EMPUÑADURA DE Weapon: si es de 1 o 2 manos
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setSingleHand(spaceBtwText[1].equals("true"));

                    Weapons.add(Weapon);
                }
                Vampire.setWeapons(Weapons);

                ArrayList<Weapon> WeaponsActivas = new ArrayList<>();
                //NUMERO DE WeaponS ACTIVAS
                br.readLine();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon Weapon = new Weapon();

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setName(spaceBtwText[1]);

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setAttackModifier((Integer.parseInt(spaceBtwText[1])));

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setSingleHand(spaceBtwText[1].equals("true"));
                    WeaponsActivas.add(Weapon);
                }
                Vampire.setActiveWeapons(WeaponsActivas);

                //ArmorS
                br.readLine();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                ArrayList<Armor> Armors = new ArrayList<>();
                tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {

                    Armor Armor = new Armor();

                    //NOMBRE Weapon
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Armor.setName(spaceBtwText[1]);

                    //NIVEL DEFENSA Weapon
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Armor.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));

                    //NIVEL ATAQUE Weapon
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Armor.setAttackModifier((Integer.parseInt(spaceBtwText[1])));

                    Armors.add(Armor);
                }
                Vampire.setArmors(Armors);

                br.readLine();
                Armor Armor = new Armor();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Armor.setName(spaceBtwText[1]);
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Armor.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Armor.setAttackModifier((Integer.parseInt(spaceBtwText[1])));

                Vampire.setActiveArmor(Armor);

                br.readLine();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Vampire.setGold(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Vampire.setAge(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Vampire.setHp(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Vampire.setPower(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                ArrayList<Strength> Strengths = new ArrayList<>();
                for (int i = 0; i < tope; i++) {

                    Strength Strength = new Strength();
                    
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Strength.setName(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Strength.setValue((Integer.parseInt(spaceBtwText[1])));

                    Strengths.add(Strength);
                }
                Vampire.setStrengths(Strengths);

                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                ArrayList<Weakness> Weaknesses = new ArrayList<>();
                for (int i = 0; i < tope; i++) {

                    Weakness Weakness = new Weakness();
                    
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weakness.setName((spaceBtwText[1]));
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weakness.setValue((Integer.parseInt(spaceBtwText[1])));

                    Weaknesses.add(Weakness);
                }
                Vampire.setWeaknesses(Weaknesses);
                ArrayList<MinionsComposit> listaEsbirros = new ArrayList<>();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {
                    MinionsComposit esbirro = minionsFile(linea, br, spaceBtwText);
                    listaEsbirros.add(esbirro);
                }
                Vampire.setMinions(listaEsbirros);
                linea = "FIN_USUARIO";
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
        return Vampire;
    }

    private Hunter lecturaHunter(BufferedReader br) {
        Hunter Hunter = new Hunter();
        Talent Talent = new Talent();

        FileReader fr = null;
        ArrayList<Client> listaHunter = new ArrayList<>();
        try {
            String linea;
            linea = br.readLine();

            while (!linea.equals("FIN_USUARIO")) {
                String[] spaceBtwText = linea.split(" "  );
                Hunter.setType("Hunter");
                Hunter.setName(spaceBtwText[1]);
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Talent.setName(spaceBtwText[1]);
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Hunter.setWillpower(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Talent.setAttack(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Talent.setDefense(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Talent.setAge(Integer.parseInt(spaceBtwText[1]));

                Hunter.setAbility(Talent);
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                ArrayList<Weapon> listaWeapons = new ArrayList<>();
                int tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon Weapon = new Weapon();
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setName(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setAttackModifier((Integer.parseInt(spaceBtwText[1])));
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));

                    //EMPUÑADURA de arma: si es de 1 o 2 manos
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setSingleHand(spaceBtwText[1].equals("true"));

                    listaWeapons.add(Weapon);
                }

                Hunter.setWeapons(listaWeapons);

                ArrayList<Weapon> WeaponsActivas = new ArrayList<>();
                br.readLine();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon Weapon = new Weapon();

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setName(spaceBtwText[1]);

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setAttackModifier((Integer.parseInt(spaceBtwText[1])));

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setSingleHand(spaceBtwText[1].equals("true"));
                    WeaponsActivas.add(Weapon);
                }
                Hunter.setActiveWeapons(WeaponsActivas);
                br.readLine();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                ArrayList<Armor> Armors = new ArrayList<>();
                tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {

                    Armor Armor = new Armor();
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Armor.setName(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Armor.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Armor.setAttackModifier((Integer.parseInt(spaceBtwText[1])));

                    Armors.add(Armor);
                }
                Hunter.setArmors(Armors);
                br.readLine();
                Armor Armor = new Armor();

                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Armor.setName(spaceBtwText[1]);
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Armor.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Armor.setAttackModifier((Integer.parseInt(spaceBtwText[1])));
                br.readLine();
                Hunter.setActiveArmor(Armor);

                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Hunter.setGold(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Hunter.setHp(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Hunter.setPower(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                ArrayList<Strength> listaStrengths = new ArrayList<>();
                for (int i = 0; i < tope; i++) {

                    Strength Strength = new Strength();

                    //NOMBRE Strength
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Strength.setName(spaceBtwText[1]);

                    //VALOR Strength
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Strength.setValue((Integer.parseInt(spaceBtwText[1])));

                    listaStrengths.add(Strength);
                }
                Hunter.setStrengths(listaStrengths);

                br.readLine();
                // NUMERO DE WeaknessES
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                ArrayList<Weakness> listaWeaknesses = new ArrayList<>();
                for (int i = 0; i < tope; i++) {

                    Weakness Weakness = new Weakness();

                    //NOMBRE DE DEBILIADAD
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weakness.setName((spaceBtwText[1]));

                    //VALOR Weakness
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weakness.setValue((Integer.parseInt(spaceBtwText[1])));

                    listaWeaknesses.add(Weakness);
                }
                Hunter.setWeaknesses(listaWeaknesses);

                br.readLine();

                //METODO ESBIRRO
                ArrayList<MinionsComposit> listaEsbirros = new ArrayList<>();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {
                    MinionsComposit esbirro = minionsFile(linea, br, spaceBtwText);
                    listaEsbirros.add(esbirro);
                }
                Hunter.setMinions(listaEsbirros);
                linea = "FIN_USUARIO";
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
        return Hunter;
    }

    private Werewolf lecturaWerewolf(BufferedReader br) {
        Werewolf Werewolf = new Werewolf();
        Don don = new Don();

        FileReader fr = null;
        ArrayList<Client> listaVampire = new ArrayList<>();
        try {
            String linea;

            linea = br.readLine();

            while (!linea.equals("FIN_USUARIO")) {

                linea = br.readLine();
                System.out.println(linea);
                String[] spaceBtwText = linea.split(" "  );
                Werewolf.setType(spaceBtwText[1]);
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Werewolf.setName(spaceBtwText[1]);
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                don.setName(spaceBtwText[1]);
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Werewolf.setRage(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                ArrayList<Weapon> listaWeapons = new ArrayList<>();
                int tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon Weapon = new Weapon();

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setName(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setAttackModifier((Integer.parseInt(spaceBtwText[1])));
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setSingleHand(spaceBtwText[1].equals("true"));

                    listaWeapons.add(Weapon);
                }
                Werewolf.setWeapons(listaWeapons);

                ArrayList<Weapon> WeaponsActivas = new ArrayList<>();
                br.readLine();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {

                    Weapon Weapon = new Weapon();

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setName(spaceBtwText[1]);

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setAttackModifier((Integer.parseInt(spaceBtwText[1])));

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weapon.setSingleHand(spaceBtwText[1].equals("true"));
                    WeaponsActivas.add(Weapon);
                }
                Werewolf.setActiveWeapons(WeaponsActivas);

                br.readLine();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                ArrayList<Armor> Armors = new ArrayList<>();
                tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {

                    Armor Armor = new Armor();

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Armor.setName(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Armor.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Armor.setAttackModifier((Integer.parseInt(spaceBtwText[1])));

                    Armors.add(Armor);
                }
                Werewolf.setArmors(Armors);
                
                br.readLine();
                Armor Armor = new Armor();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Armor.setName(spaceBtwText[1]);
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Armor.setDefenseModifier((Integer.parseInt(spaceBtwText[1])));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Armor.setAttackModifier((Integer.parseInt(spaceBtwText[1])));
                Werewolf.setActiveArmor(Armor);
                br.readLine();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Werewolf.setGold(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Werewolf.setHp(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                Werewolf.setPower(Integer.parseInt(spaceBtwText[1]));
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                ArrayList<Weakness> Weaknesses = new ArrayList<>();
                for (int i = 0; i < tope; i++) {

                    Weakness Weakness = new Weakness();

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weakness.setName((spaceBtwText[1]));
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Weakness.setValue((Integer.parseInt(spaceBtwText[1])));

                    Weaknesses.add(Weakness);
                }
                Werewolf.setWeaknesses(Weaknesses);

                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                ArrayList<Strength> Strengths = new ArrayList<>();
                for (int i = 0; i < tope; i++) {

                    Strength Strength = new Strength();

                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Strength.setName(spaceBtwText[1]);
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    Strength.setValue((Integer.parseInt(spaceBtwText[1])));

                    Strengths.add(Strength);
                }
                Werewolf.setStrengths(Strengths);

                ArrayList<MinionsComposit> listaEsbirros = new ArrayList<>();
                linea = br.readLine();
                spaceBtwText = linea.split(" "  );
                tope = Integer.parseInt(spaceBtwText[1]);
                for (int i = 0; i < tope; i++) {
                    MinionsComposit esbirro = minionsFile(linea, br, spaceBtwText);
                    listaEsbirros.add(esbirro);
                }
                Werewolf.setMinions(listaEsbirros);
                linea = "FIN_USUARIO";
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
        return Werewolf;
    }

    private MinionsComposit minionsFile(String linea, BufferedReader br, String[] spaceBtwText) throws NumberFormatException, IOException {
        int tope = Integer.parseInt(spaceBtwText[1]);
        for (int i = 0; i < tope; i++) {
            linea = br.readLine();
            spaceBtwText = linea.split(" "  );
            switch (spaceBtwText[1]) {
                case "HUMANO" -> {
                    Human human = new Human();
                    //TIPO ESBIRRO
                    human.setType(spaceBtwText[1]);

                    //NOMBRE ESBIRRO
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    human.setName(spaceBtwText[1]);

                    //VIDA ESBIRRO
                    linea = br.readLine();
                    spaceBtwText = linea.split(" "  );
                    human.setHp((Integer.parseInt(spaceBtwText[1])));

                    //VALOR LEALTAD     TIPOS: ALTA, MEDIO, BAJO que coresponde a 0,1,2
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
