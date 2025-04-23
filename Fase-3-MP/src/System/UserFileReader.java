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
                archivo.createNewFile();  // Si el archivo no existe, lo crea vacío.
            }
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            // Leer hasta el fin del archivo
            while ((linea = br.readLine()) != null) {
                // Limpiar la línea de espacios adicionales
                linea = linea.trim();

                // Verificar si la línea está vacía o si no es el separador esperado
                if (linea.isEmpty() || !linea.equals("========== USUARIO ==========")) {
                    continue;  // Saltar líneas vacías o no relevantes
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
                String characterType = (charArr.length > 2) ? charArr[2] : "null";  // Corregido aquí

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

    //LECTURA PERSONAJES
    public Vampire lecturaVampire(BufferedReader br) {
        Vampire vampire = new Vampire();
        Discipline discipline = new Discipline();
        FileReader fr = null;

        try {
            String linea;

            // Leer hasta encontrar el inicio de la sección de CHARACTER
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();  // Limpiar espacios al inicio y al final

                // Saltar líneas vacías
                if (linea.isEmpty()) {
                    continue;
                }

                // Si encontramos el separador de inicio de character, comenzamos a leer el personaje
                if (linea.equals("========== CHARACTER ==========")) {
                    // Continuamos leyendo la información del personaje

                    // Leer Registro de Usuario
                    linea = br.readLine();
                    String[] spaceBtwText = linea.split(" ");
                    String registroUsuario = spaceBtwText[1];  // Usamos el valor de registro, si lo necesitas

                    // Leer Tipo de Personaje
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    String tipoPersonaje = spaceBtwText[3];  // Usamos el tipo de personaje (VAMPIRO, etc.)
                    vampire.setType(tipoPersonaje);

                    // Leer Nombre de Personaje
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    vampire.setName(spaceBtwText[3]);

                    // Leer Sangre
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    vampire.setBlood(Integer.parseInt(spaceBtwText[2]));

                    // Leer Habilidad
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    discipline.setName(spaceBtwText[4]);

                    // Valor de Ataque
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    discipline.setAttack(Integer.parseInt(spaceBtwText[3]));

                    // Valor de Defensa
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    discipline.setDefense(Integer.parseInt(spaceBtwText[3]));

                    // Coste de la habilidad
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    discipline.setCost(Integer.parseInt(spaceBtwText[4]));

                    vampire.setAbility(discipline);

                    // Leer número de armas
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    int numWeapons = Integer.parseInt(spaceBtwText[3]);
                    ArrayList<Weapon> weapons = new ArrayList<>();
                    for (int i = 0; i < numWeapons; i++) {
                        Weapon weapon = new Weapon();

                        // Leer nombre del arma
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        weapon.setName(spaceBtwText[3]);

                        // Leer ataque del arma
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        weapon.setAttackModifier(Integer.parseInt(spaceBtwText[4]));

                        // Leer defensa del arma
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        weapon.setDefenseModifier(Integer.parseInt(spaceBtwText[3]));

                        // Leer empuñadura del arma
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        weapon.setSingleHand(spaceBtwText[2].equals("true"));

                        weapons.add(weapon);
                    }

                    vampire.setWeapons(weapons);

                    // Leer número de armas activas
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    int numActiveWeapons = Integer.parseInt(spaceBtwText[4]);
                    ArrayList<Weapon> activeWeapons = new ArrayList<>();
                    for (int i = 0; i < numActiveWeapons; i++) {
                        Weapon activeWeapon = new Weapon();

                        // Leer nombre del arma activa
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        activeWeapon.setName(spaceBtwText[4]);

                        // Leer ataque del arma activa
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        activeWeapon.setAttackModifier(Integer.parseInt(spaceBtwText[4]));

                        // Leer defensa del arma activa
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        activeWeapon.setDefenseModifier(Integer.parseInt(spaceBtwText[4]));

                        // Leer empuñadura del arma activa
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        activeWeapon.setSingleHand(spaceBtwText[2].equals("true"));

                        activeWeapons.add(activeWeapon);
                    }

                    vampire.setActiveWeapons(activeWeapons);

                    // Leer número de armaduras
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    int numArmors = Integer.parseInt(spaceBtwText[3]);
                    ArrayList<Armor> armors = new ArrayList<>();
                    for (int i = 0; i < numArmors; i++) {
                        Armor armor = new Armor();

                        // Leer nombre de la armadura
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        armor.setName(spaceBtwText[3]);

                        // Leer defensa de la armadura
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        armor.setDefenseModifier(Integer.parseInt(spaceBtwText[4]));

                        // Leer ataque de la armadura
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        armor.setAttackModifier(Integer.parseInt(spaceBtwText[4]));

                        armors.add(armor);
                    }

                    vampire.setArmors(armors);

                    // Leer armadura activa
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    Armor activeArmor = new Armor();
                    activeArmor.setName(spaceBtwText[4]);

                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    activeArmor.setDefenseModifier(Integer.parseInt(spaceBtwText[4]));

                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    activeArmor.setAttackModifier(Integer.parseInt(spaceBtwText[4]));

                    vampire.setActiveArmor(activeArmor);

                    // Leer oro disponible
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    vampire.setGold(Integer.parseInt(spaceBtwText[3]));

                    // Leer edad del vampiro
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    vampire.setAge(Integer.parseInt(spaceBtwText[3]));

                    // Leer HP
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    vampire.setHp(Integer.parseInt(spaceBtwText[1]));

                    // Leer poder
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    vampire.setPower(Integer.parseInt(spaceBtwText[2]));

                    // Leer fortalezas
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    int numStrengths = Integer.parseInt(spaceBtwText[3]);
                    ArrayList<Strength> strengths = new ArrayList<>();
                    for (int i = 0; i < numStrengths; i++) {
                        Strength strength = new Strength();
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        strength.setName(spaceBtwText[3]);

                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        strength.setValue(Integer.parseInt(spaceBtwText[3]));

                        strengths.add(strength);
                    }

                    vampire.setStrengths(strengths);

                    // Leer debilidades
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    int numWeaknesses = Integer.parseInt(spaceBtwText[3]);
                    ArrayList<Weakness> weaknesses = new ArrayList<>();
                    for (int i = 0; i < numWeaknesses; i++) {
                        Weakness weakness = new Weakness();
                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        weakness.setName(spaceBtwText[3]);

                        linea = br.readLine();
                        spaceBtwText = linea.split(" ");
                        weakness.setValue(Integer.parseInt(spaceBtwText[3]));

                        weaknesses.add(weakness);
                    }

                    vampire.setWeaknesses(weaknesses);

                    // Leer número de esbirros
                    linea = br.readLine();
                    spaceBtwText = linea.split(" ");
                    int numMinions = Integer.parseInt(spaceBtwText[3]);
                    ArrayList<MinionsComposit> minions = new ArrayList<>();
                    for (int i = 0; i < numMinions; i++) {
                        MinionsComposit minion = minionsFile(linea, br, spaceBtwText);
                        minions.add(minion);
                    }
                    vampire.setMinions(minions);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Si algo falla durante la lectura, imprimimos el error
        } finally {
            try {
                if (fr != null) {
                    fr.close(); // Cerramos el archivo si fue abierto
                }
            } catch (Exception e2) {
                e2.printStackTrace(); // En caso de que falle el cierre, imprimimos el error
            }
        }
        return vampire; // Devolvemos el objeto Vampire con todos los datos leídos
    }

    public Hunter lecturaHunter(BufferedReader br) {
        Hunter Hunter = new Hunter();
        Talent Talent = new Talent();

        FileReader fr = null;
        ArrayList<Client> listaHunter = new ArrayList<>();
        try {
            String linea;
            linea = br.readLine();

            while (!linea.equals("========== FIN CHARACTER ==========")) {
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
                linea = "========== FIN CHARACTER ==========";
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

    public Werewolf lecturaWerewolf(BufferedReader br) {
        Werewolf Werewolf = new Werewolf();
        Don don = new Don();

        FileReader fr = null;
        ArrayList<Client> listaVampire = new ArrayList<>();
        try {
            String linea;

            linea = br.readLine();

            while (!linea.equals("========== FIN CHARACTER ==========")) {

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
                linea = "========== FIN CHARACTER ==========";
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
