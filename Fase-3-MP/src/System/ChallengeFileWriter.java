package System;

import Entities.*;
import Entities.Character;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ChallengeFileWriter {
    private final String CHALLENGE_REGISTER_PATH = "Fase-3-MP/src/Files/ChallengeRegister.txt";

    public void challengeRegister(Challenge Challenge){
            try {
                File file = new File(CHALLENGE_REGISTER_PATH);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write("========== CHALLENGE ==========");
                bw.newLine();
                bw.write("DESAFIANTE ");
                bw.write(Challenge.getChallenger().getName());
                bw.newLine();
                bw.write("NICK ");
                bw.write(Challenge.getChallenger().getNick());
                bw.newLine();
                bw.write("PASSWORD ");
                bw.write(Challenge.getChallenger().getPassword());
                bw.newLine();
                bw.write("REGISTRO ");
                bw.write(Challenge.getChallenger().getRegister());
                bw.newLine();
                String tipoCharacterDesafiante = Challenge.getChallenger().getCharacter().getType();
                if (tipoCharacterDesafiante == null) {
                    bw.write("TIPO-PERSONAJE null");
                    bw.newLine();
                } else if (tipoCharacterDesafiante.equals("VAMPIRO")) {
                    escrituraVampireDesafiante(Challenge, bw);
                } else if (tipoCharacterDesafiante.equals("LICANTROPO")) {
                    escrituraWerewolfDesafiante(Challenge, bw);
                } else if (tipoCharacterDesafiante.equals("CAZADOR")) {
                    escrituraHunterDesafiante(Challenge, bw);
                }

                bw.write("CONTRINCANTE ");
                bw.write(Challenge.getRival().getName());
                bw.newLine();

                bw.write("NICK ");
                bw.write(Challenge.getRival().getNick());
                bw.newLine();

                bw.write("PASSWORD ");
                bw.write(Challenge.getRival().getPassword());
                bw.newLine();

                bw.write("REGISTRO ");
                bw.write(Challenge.getRival().getRegister());
                bw.newLine();

                String tipoCharacterContrincante = Challenge.getRival().getCharacter().getType();
                if (tipoCharacterContrincante == null) {
                    bw.write("TIPO-PERSONAJE null");
                    bw.newLine();
                } else if (tipoCharacterContrincante.equals("VAMPIRO")) {
                    escrituraVampireContrincante(Challenge, bw);
                } else if (tipoCharacterContrincante.equals("LICANTROPO")) {
                    escrituraWerewolfContrincante(Challenge, bw);
                } else if (tipoCharacterContrincante.equals("CAZADOR")) {
                    escrituraHunterContrincante(Challenge, bw);
                }

                bw.write("ORO ");
                bw.write(String.valueOf(Challenge.getGold()));
                bw.newLine();

                bw.write("CANTIDAD-MODIFICADORES ");
                bw.write(String.valueOf(Challenge.getModifiers().size()));
                bw.newLine();
                for (int j = 0; j < (Challenge.getModifiers().size()); j++) {
                    Modifier Modifiers = Challenge.getModifiers().get(j);
                    bw.write("NOMBRE-MODIFICADORES ");
                    bw.write(Modifiers.getName());
                    bw.newLine();

                    bw.write("VALOR-MODIFICADORES ");
                    bw.write(String.valueOf(Modifiers.getValue()));
                    bw.newLine();
                }
                bw.newLine();

                String pattern = "dd-MM-yyyy HH:mm:ss";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                String date = simpleDateFormat.format(Challenge.getDate());
                bw.write("FECHA ");
                bw.write(date);
                bw.newLine();

                bw.write("VALIDADO ");
                if (Challenge.isValidated()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();

                bw.write("REGISTRO ");
                bw.write(Challenge.getRegister());
                bw.newLine();

                bw.write("========== FIN CHALLENGE ==========");
                bw.newLine();
                bw.close();

            } catch (Exception e) {
                mainSystem mainSystem = new mainSystem();
                mainSystem.selector();
                e.printStackTrace();
            }
        }

        public void reweiteChallengeFile(ArrayList<Challenge> listChallenge){

            try {
                File file = new File(CHALLENGE_REGISTER_PATH);
                FileWriter fw = new FileWriter(file); 
                BufferedWriter bw = new BufferedWriter(fw);

                //recorre la lista de clientes
                for (Challenge Challenge : listChallenge) {

                    bw.write("========== CHALLENGE ==========");
                    bw.newLine();

                    bw.write("DESAFIANTE ");
                    bw.write(Challenge.getChallenger().getName());
                    bw.newLine();

                    bw.write("NICK-DESAFIANTE ");
                    bw.write(Challenge.getChallenger().getNick());
                    bw.newLine();

                    bw.write("PASSWORD-DESAFIANTE ");
                    bw.write(Challenge.getChallenger().getPassword());
                    bw.newLine();

                    bw.write("REGISTRO-DESAFIANTE ");
                    bw.write(Challenge.getChallenger().getRegister());
                    bw.newLine();

                    String tipoCharacterDesafiante = Challenge.getChallenger().getCharacter().getType();
                    if (tipoCharacterDesafiante == null) {
                        bw.write("TIPO-Character null");
                        bw.newLine();
                    } else if (tipoCharacterDesafiante.equals("VAMPIRO")) {
                        escrituraVampireDesafiante(Challenge, bw);
                    } else if (tipoCharacterDesafiante.equals("LICANTROPO")) {
                        escrituraWerewolfDesafiante(Challenge, bw);
                    } else if (tipoCharacterDesafiante.equals("CAZADOR")) {
                        escrituraHunterDesafiante(Challenge, bw);
                    }

                    bw.write("CONTRINCANTE ");
                    bw.write(Challenge.getRival().getName());
                    bw.newLine();

                    bw.write("NICK ");
                    bw.write(Challenge.getRival().getNick());
                    bw.newLine();

                    bw.write("PASSWORD ");
                    bw.write(Challenge.getRival().getPassword());
                    bw.newLine();

                    bw.write("REGISTRO ");
                    bw.write(Challenge.getRival().getRegister());
                    bw.newLine();

                    String tipoCharacterContrincante = Challenge.getRival().getCharacter().getType();
                    if (tipoCharacterContrincante == null) {
                        bw.write("TIPO-PERSONAJE null");
                        bw.newLine();
                    } else if (tipoCharacterContrincante.equals("VAMPIRO")) {
                        escrituraVampireContrincante(Challenge, bw);
                    } else if (tipoCharacterContrincante.equals("LICANTROPO")) {
                        escrituraWerewolfContrincante(Challenge, bw);
                    } else if (tipoCharacterContrincante.equals("CAZADOR")) {
                        escrituraHunterContrincante(Challenge, bw);
                    }

                    bw.write("ORO ");
                    bw.write(String.valueOf(Challenge.getGold()));
                    bw.newLine();
                    

                    bw.write("NUM-MODIFICADORES ");
                    bw.write(String.valueOf(Challenge.getModifiers().size()));
                    bw.newLine();
                    for (int j = 0; j < (Challenge.getModifiers().size()); j++) {
                        Modifier Modifiers = Challenge.getModifiers().get(j);
                        bw.write("NOMBRE-MODIFICADORES ");
                        bw.write(Modifiers.getName());
                        bw.newLine();

                        bw.write("VALOR-MODIFICADORES ");
                        bw.write(String.valueOf(Modifiers.getValue()));
                        bw.newLine();
                    }
                    bw.newLine();

                    String pattern = "dd-MM-yyyy HH:mm:ss";
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                    String date = simpleDateFormat.format(Challenge.getDate());
                    bw.write("FECHA ");
                    bw.write(date);
                    bw.newLine();

                    bw.write("VALIDADO ");
                    if (Challenge.isValidated()) {
                        bw.write("true");
                    } else {
                        bw.write("false");
                    }
                    bw.newLine();

                    bw.write("REGISTRO ");
                    bw.write(Challenge.getRegister());
                    bw.newLine();

                    bw.write("========== FIN CHALLENGE ==========");
                    bw.newLine();

                }
                bw.close();
            } catch (Exception e) {
                mainSystem mainSystem = new mainSystem();
                mainSystem.selector();
                e.printStackTrace();
            }
        }

        private void escrituraVampireDesafiante(Challenge Challenge, BufferedWriter bw) throws IOException {

            Vampire Vampire = (Vampire) Challenge.getChallenger().getCharacter();
            Discipline Discipline = (Discipline) Vampire.getAbility();

            //TIPO Character
            bw.write("TIPO-PERSONAJE ");
            bw.write(Challenge.getChallenger().getCharacter().getType());
            bw.newLine();
            //NOMBRE Character
            bw.write("NOMBRE-PERSONAJE ");
            bw.write(Challenge.getChallenger().getCharacter().getName());
            bw.newLine();
            //PUNTOS DE SANGRE
            bw.write("SANGRE ");
            bw.write("0");
            bw.newLine();
            //NOMBRE DE HABILIDAD
            bw.write("NOMNRE-HABILIDAD ");
            bw.write(Discipline.getName());
            bw.newLine();

            //VALOR ATAQUE
            bw.write("VALOR-ATAQUE ");
            bw.write(String.valueOf(Discipline.getAttack()));
            bw.newLine();

            //VALOR DEFENSA
            bw.write("VALOR-DEFENSA ");
            bw.write(String.valueOf(Discipline.getDefense()));
            bw.newLine();

            //COSTE HABILIDAD
            bw.write("COSATE-HABILIDAD ");
            bw.write(String.valueOf(Discipline.getCost()));
            bw.newLine();

            //WeaponS
            bw.write("NUMERO-ARMAS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getWeapons().size()));
            bw.newLine();

            for (int variableWeapon = 0; variableWeapon < (Challenge.getChallenger().getCharacter().getWeapons().size()); variableWeapon++) {
                Weapon Weapon = Challenge.getChallenger().getCharacter().getWeapons().get(variableWeapon);
                bw.write("NOMBRE-ARMA ");
                bw.write(Weapon.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA ");
                bw.write(String.valueOf(Weapon.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA ");
                bw.write(String.valueOf(Weapon.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (Weapon.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();

            }
            bw.newLine();

            //NUMERO DE WeaponS ACTIVAS
            bw.write("NUMERO-ARMAS-ACTIVAS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getActiveWeapons().size()));
            bw.newLine();
            for (int variableWeaponActiva = 0; variableWeaponActiva < (Challenge.getChallenger().getCharacter().getActiveWeapons().size()); variableWeaponActiva++) {
                Weapon WeaponActiva = Vampire.getActiveWeapons().get(variableWeaponActiva);

                bw.write("NOMBRE-ARMAS-ACTIVAS ");
                bw.write(WeaponActiva.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (WeaponActiva.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();
            }
            bw.newLine();

            //ArmorS
            //NUMERO DE ArmorS
            bw.write("NUMERO-ARMADURAS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getArmors().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getChallenger().getCharacter().getArmors().size()); j++) {
                Armor Armor = Vampire.getArmors().get(j);
                bw.write("NOMBRE-ARMADURA ");
                bw.write(Armor.getName());
                bw.newLine();

                bw.write("DEFENSA-ARMADURA ");
                bw.write(String.valueOf(Armor.getDefenseModifier()));
                bw.newLine();

                bw.write("ATAQUE-ARMADURA ");
                bw.write(String.valueOf(Armor.getAttackModifier()));
                bw.newLine();
            }
            bw.newLine();

            //Armor ACTIVA
            Armor Armor = Vampire.getActiveArmor();
            bw.write("NOMBRE-ARMADURA ");
            bw.write(Armor.getName());
            bw.newLine();

            bw.write("DEFENSA-ARMADURA ");
            bw.write(String.valueOf(Armor.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE-ARMADURA ");
            bw.write(String.valueOf(Armor.getAttackModifier()));
            bw.newLine();
            Vampire.setActiveArmor(Armor);
            bw.newLine();

            //EDAD Vampire
            bw.write("EDAD-VAMPIRO ");
            bw.write(String.valueOf(Vampire.getAge()));
            bw.newLine();

            //ESBIRROS
            //NUMERO DE ESBIRROS
            bw.write("NUMERO-ESBIRROS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getMinions().size()));
            bw.newLine();

            //ESBIRROS
            escrituraEsbirrosDesafiante(Challenge, Vampire, bw);

            //CANTIDAD ORO
            bw.write("CANTIDAD-ORO ");
            bw.write(String.valueOf(Vampire.getGold()));
            bw.newLine();

            //CANTIDAD VIDA
            bw.write("CANTIDAD-VIDA ");
            bw.write(String.valueOf(Vampire.getHp()));
            bw.newLine();

            //PODER
            bw.write("CANTIDAD-PODER ");
            bw.write(String.valueOf(Vampire.getPower()));
            bw.newLine();

            //WeaknessES
            bw.write("NUMERO-DEBILIDADES ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getWeaknesses().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getChallenger().getCharacter().getWeaknesses().size()); j++) {
                Weakness Weakness = Vampire.getWeaknesses().get(j);
                bw.write("NOMBRE-DEBILIDAD ");
                bw.write(Weakness.getName());
                bw.newLine();

                bw.write("VALOR-DEBILIDAD ");
                bw.write(String.valueOf(Weakness.getValue()));
                bw.newLine();
            }
            bw.newLine();

            //StrengthS
            bw.write("NUMERO-FORTALEZAS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getStrengths().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getChallenger().getCharacter().getStrengths().size()); j++) {
                Strength Strength = Vampire.getStrengths().get(j);
                bw.write("NOMBRE-FORTALEZA ");
                bw.write(Strength.getName());
                bw.newLine();

                bw.write("VALOR-FORTALEZA ");
                bw.write(String.valueOf(Strength.getValue()));
                bw.newLine();
            }
            bw.newLine();


            bw.write("========== FIN USUARIO ==========");
            bw.newLine();
        }

        private void escrituraWerewolfDesafiante(Challenge Challenge, BufferedWriter bw) throws IOException {

            Werewolf Werewolf = (Werewolf) Challenge.getChallenger().getCharacter();

            //TIPO Character
            bw.write("TIPO-PERSONAJE ");
            bw.write(Challenge.getChallenger().getCharacter().getType());
            bw.newLine();
            //NOMBRE Character
            bw.write("NOMBRE-PERSONAJE ");
            bw.write(Challenge.getChallenger().getCharacter().getName());
            bw.newLine();

            //PUNTOS DE SANGRE
            bw.write("RABIA ");
            //bw.write("0");
            bw.newLine();

            //NUMERO WeaponS
            bw.write("NUMERO-ARMAS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getWeapons().size()));
            bw.newLine();

            for (int variableWeapon = 0; variableWeapon < (Challenge.getChallenger().getCharacter().getWeapons().size()); variableWeapon++) {
                Weapon Weapon = Challenge.getChallenger().getCharacter().getWeapons().get(variableWeapon);
                bw.write("NOMBRE-ARMA ");
                bw.write(Weapon.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA ");
                bw.write(String.valueOf(Weapon.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA ");
                bw.write(String.valueOf(Weapon.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (Weapon.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();

            }
            bw.newLine();

            //NUMERO DE WeaponS ACTIVAS
            bw.write("NUMERO-ARMAS-ACTIVAS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getActiveWeapons().size()));
            bw.newLine();
            for (int variableWeaponActiva = 0; variableWeaponActiva < (Challenge.getChallenger().getCharacter().getActiveWeapons().size()); variableWeaponActiva++) {
                Weapon WeaponActiva = Werewolf.getActiveWeapons().get(variableWeaponActiva);

                bw.write("NOMBRE-ARMAS-ACTIVAS ");
                bw.write(WeaponActiva.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (WeaponActiva.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();
            }
            bw.newLine();

            //ArmorS
            //NUMERO DE ArmorS
            bw.write("NUMERO-ARMADURAS ");
            bw.write(Challenge.getChallenger().getCharacter().getArmors().size());
            bw.newLine();
            for (int j = 0; j < (Challenge.getChallenger().getCharacter().getArmors().size()); j++) {
                Armor Armor = Werewolf.getArmors().get(j);
                bw.write("NOMBRE-ARMADURA ");
                bw.write(Armor.getName());
                bw.newLine();

                bw.write("DEFENSA-ARMADURA ");
                bw.write(String.valueOf(Armor.getDefenseModifier()));
                bw.newLine();

                bw.write("ATAQUE-ARMADURA ");
                bw.write(String.valueOf(Armor.getAttackModifier()));
                bw.newLine();
            }
            bw.newLine();

            //Armor ACTIVA / EQUIPADA
            bw.write("NOMBRE-ARMADURA-ACTIVA ");
            bw.write(Challenge.getChallenger().getCharacter().getActiveArmor().getName());
            bw.newLine();

            //DEFENSA Armor ACTIVA / EQUIPADA
            bw.write("DEFENSA-ARMADURA-ACTIVA ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getActiveArmor().getDefenseModifier()));
            bw.newLine();

            //ATAQUE Armor ACTIVA / EQUIPADA
            bw.write("ATAQUE-ARMADURA-ACTIVA ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getActiveArmor().getAttackModifier()));
            bw.newLine();

            //CANTIDAD ORO
            bw.write("ORO ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getGold()));
            bw.newLine();

            //DEBLIDADES
            //NUMERO DE DEBLIDADES
            bw.write("NUMERO-DEBILIDADES ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getWeaknesses().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getChallenger().getCharacter().getArmors().size()); j++) {
                Weakness Weakness = Werewolf.getWeaknesses().get(j);
                bw.write("NOMBRE-DEBILIADAD ");
                bw.write(Weakness.getName());
                bw.newLine();

                bw.write("VALOR-DEBILIADAD ");
                bw.write(String.valueOf(Weakness.getValue()));
                bw.newLine();
            }
            bw.newLine();

            //StrengthS
            //NUMERO DE StrengthS
            bw.write("NUMERO-FORTALEZAS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getStrengths().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getChallenger().getCharacter().getArmors().size()); j++) {
                Strength Strength = Werewolf.getStrengths().get(j);
                bw.write("NOMBRE-FORTALEZA ");
                bw.write(Strength.getName());
                bw.newLine();

                bw.write("VALOR-FORTALEZA ");
                bw.write(String.valueOf(Strength.getValue()));
                bw.newLine();
            }
            bw.newLine();

            //ESBIRROS
            //NUMERO DE ESBIRROS
            bw.write("NUMERO-ESBIRROS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getMinions().size()));
            bw.newLine();

            //ESBIRROS
            escrituraEsbirrosDesafiante(Challenge, Werewolf, bw);

            bw.write("========== FIN USUARIO ==========");
            bw.newLine();
        }

        private void escrituraHunterDesafiante(Challenge Challenge, BufferedWriter bw) throws IOException {

            Hunter Hunter = (Hunter) Challenge.getChallenger().getCharacter();

            //TIPO Character
            bw.write("TIPO-PERSONAJE ");
            bw.write(Challenge.getChallenger().getCharacter().getType());
            bw.newLine();
            //NOMBRE Character
            bw.write("NOMBRE-PERSONAJE ");
            bw.write(Challenge.getChallenger().getCharacter().getName());
            bw.newLine();

            //VOLUNTAD Hunter
            bw.write("RABIA ");
            //bw.write("0");
            bw.newLine();

            //NOMBRE HABILDIAD
            bw.write("NOMBRE-HABILIDAD ");
            bw.write(Hunter.getName());
            bw.newLine();

            //ATAQUE HABILIDAD
            bw.write("ATAQUE-HABILIDAD ");
            bw.write(String.valueOf(Hunter.getAbility().getAttack()));
            bw.newLine();

            //Weakness HABILIDAD
            bw.write("DEFENSA-HABILIDAD ");
            bw.write(String.valueOf(Hunter.getAbility().getDefense()));
            bw.newLine();

            //EDAD Hunter
            bw.write("EDAD-CAZADOR ");
            bw.write(String.valueOf(Hunter.getWillpower()));  //LA EDAD ES LA VOLUNTAD DEL Hunter
            bw.newLine();

            //WeaponS
            //NUMERO WeaponS
            bw.write("NUMERO-ARMAS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getWeapons().size()));
            bw.newLine();

            for (int variableWeapon = 0; variableWeapon < (Challenge.getChallenger().getCharacter().getWeapons().size()); variableWeapon++) {
                Weapon Weapon = Challenge.getChallenger().getCharacter().getWeapons().get(variableWeapon);
                bw.write("NOMBRE-ARMA ");
                bw.write(Weapon.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA ");
                bw.write(String.valueOf(Weapon.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA ");
                bw.write(String.valueOf(Weapon.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (Weapon.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();

            }
            bw.newLine();

            //WeaponS ACTIVAS
            //NUMERO DE WeaponS ACTIVAS
            bw.write("NUMERO-ARMAS-ACTIVAS ");
            bw.write(Challenge.getChallenger().getCharacter().getActiveWeapons().size());
            bw.newLine();
            for (int variableWeaponActiva = 0; variableWeaponActiva < (Challenge.getChallenger().getCharacter().getActiveWeapons().size()); variableWeaponActiva++) {
                Weapon WeaponActiva = Hunter.getActiveWeapons().get(variableWeaponActiva);

                bw.write("NOMBRE-ARMAS-ACTIVAS ");
                bw.write(WeaponActiva.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (WeaponActiva.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();
            }
            bw.newLine();

            //ArmorS
            //NUMERO DE ARMADURAS
            bw.write("NUMERO-ARMADURAS ");
            bw.write(Challenge.getChallenger().getCharacter().getArmors().size());
            bw.newLine();
            for (int j = 0; j < (Challenge.getChallenger().getCharacter().getArmors().size()); j++) {
                Armor Armor = Hunter.getArmors().get(j);
                bw.write("NOMBRE-ARMADURA ");
                bw.write(Armor.getName());
                bw.newLine();

                bw.write("DEFENSA-ARMADURA ");
                bw.write(String.valueOf(Armor.getDefenseModifier()));
                bw.newLine();

                bw.write("ATAQUE-ARMADURA ");
                bw.write(String.valueOf(Armor.getAttackModifier()));
                bw.newLine();
            }
            bw.newLine();

            //ArmorS EQUIPADA
            bw.write("NOMBRE-ARMADURA-ACTIVA ");
            bw.write(Challenge.getChallenger().getCharacter().getActiveArmor().getName());
            bw.newLine();

            bw.write("DEFENSA-ARMADURA-ACTIVA ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getActiveArmor().getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE-ARMADURA-ACTIVA ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getActiveArmor().getAttackModifier()));
            bw.newLine();

            bw.newLine();

            //CANTIDAD ORO
            bw.write("ORO ");
            bw.write(String.valueOf(Hunter.getGold()));
            bw.newLine();

            //CANTIDAD VDA
            bw.write("VIDA ");
            bw.write(String.valueOf(Hunter.getHp()));
            bw.newLine();

            //PODER
            bw.write("PODER ");
            bw.write(String.valueOf(Hunter.getPower()));
            bw.newLine();

            //StrengthS
            //NUMERO DE StrengthS
            bw.write("NUMERO-FORTALEZAS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getWeaknesses().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getChallenger().getCharacter().getArmors().size()); j++) {
                Strength Strength = Hunter.getStrengths().get(j);
                bw.write("NOMBRE-FORTALEZA ");
                bw.write(Strength.getName());
                bw.newLine();

                bw.write("VALOR-FORTALEZA ");
                bw.write(String.valueOf(Strength.getValue()));
                bw.newLine();
            }
            bw.newLine();

            //WeaknessES
            //NUMERO DE WeaknessES
            bw.write("NUMERO-DEBILIDADES ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getWeaknesses().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getChallenger().getCharacter().getArmors().size()); j++) {
                Weakness Weakness = Hunter.getWeaknesses().get(j);
                bw.write("NOMBRE-DEBILIDAD ");
                bw.write(Weakness.getName());
                bw.newLine();

                bw.write("VALOR-DEBILIDAD ");
                bw.write(String.valueOf(Weakness.getValue()));
                bw.newLine();
            }
            bw.newLine();



            //ESBIRROS
            //NUMERO DE ESBIRROS
            bw.write("NUMERO-ESBIRROS ");
            bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getMinions().size()));
            bw.newLine();

            escrituraEsbirrosDesafiante(Challenge, Hunter, bw);

            bw.write("========== FIN USUARIO ==========");
            bw.newLine();
        }

        private void escrituraEsbirrosDesafiante(Challenge Challenge, Character Character, BufferedWriter bw) throws IOException {
            for (int j = 0; j < (Challenge.getChallenger().getCharacter().getMinions().size()); j++) {
                if (Character.getMinions().get(j).getType().equals("HUMAN")) {
                    Human human = (Human) Character.getMinions().get(j);
                    //NUMERO DE ESBIRROS
                    bw.write("TIPO-ESBIRRO ");

                    bw.write(human.getType());
                    bw.newLine();

                    //NOMBRE DE ESBIRROS
                    bw.write("NOMBRE-ESBIRRO ");
                    bw.write(human.getName());
                    bw.newLine();

                    //VIDA DE ESBIRROS
                    bw.write("VIDA-ESBIRRO ");
                    bw.write(String.valueOf(human.getHp()));
                    bw.newLine();

                    //LEALTAD ESBIRRO Human
                    bw.write("LELTAD ");
                    if (human.getLoyalty() == Human.Loyalty.ALTA) {
                        bw.write("ALTA");
                    } else if (human.getLoyalty() == Human.Loyalty.MEDIA) {
                        bw.write("MEDIA");
                    } else if (human.getLoyalty() == Human.Loyalty.BAJA) {
                        bw.write("BAJA");
                    }
                    bw.newLine();

                } else if (Character.getMinions().get(j).getType().equals("GHOUL")) {
                    Ghoul ghoul = (Ghoul) Character.getMinions().get(j);
                    //NUMERO DE ESBIRRO
                    bw.write("TIPO-ESBIRRO ");
                    bw.write(ghoul.getType());
                    bw.newLine();

                    //NOMBRE DE ESBIRRO
                    bw.write("NOMBRE-ESBIRRO ");
                    bw.write(ghoul.getName());
                    bw.newLine();

                    //VIDA DE ESBIRRO
                    bw.write("VIDA-ESBIRRO ");
                    bw.write(String.valueOf(ghoul.getHp()));
                    bw.newLine();

                    //DEPENDENCIA ESBIRRO
                    bw.write("DEPENDENCIA ");
                    bw.write(String.valueOf(ghoul.getDependency()));
                    bw.newLine();

                } else if (Character.getMinions().get(j).getType().equals("DEMONIO")) {
                    Demon Demon = (Demon) Character.getMinions().get(j);
                    //TIPO DE ESBIRRO
                    bw.write("TIPO-ESBIRRO ");

                    bw.write(Demon.getType());
                    bw.newLine();

                    //NOMBRE DE ESBIRRO
                    bw.write("NOMBRE-ESBIRRO ");
                    bw.write(Demon.getName());
                    bw.newLine();

                    //VIDA ESBIRRO
                    bw.write("VIDA-ESBIRRO ");
                    bw.write(String.valueOf(Demon.getHp()));
                    bw.newLine();

                    //DESCRIPCION / PACTO
                    bw.write("DESCRIPCION ");
                    bw.write(Demon.getDescription());
                    bw.newLine();

                    //ESBIRROS EXTRA
                    //NUMERO DE ESBIRROS EXTRA
                    bw.write("NUMERO-ESBIRROS-EXTRA ");
                    bw.write(String.valueOf(Challenge.getChallenger().getCharacter().getMinions().size()));
                    bw.newLine();
                    escrituraEsbirrosDesafiante(Challenge, Character, bw);
                }
            }
        }

        private void escrituraVampireContrincante(Challenge Challenge, BufferedWriter bw) throws IOException {

            Vampire Vampire = (Vampire) Challenge.getRival().getCharacter();
            Discipline Discipline = (Discipline) Vampire.getAbility();

            //TIPO Character
            bw.write("TIPO-PERSONAJE ");
            bw.write(Challenge.getRival().getCharacter().getType());
            bw.newLine();
            //NOMBRE Character
            bw.write("NOMBRE-PERSONAJE ");
            bw.write(Challenge.getRival().getCharacter().getName());
            bw.newLine();
            //PUNTOS DE SANGRE
            bw.write("SANGRE ");
            bw.write("0");
            bw.newLine();
            //NOMBRE DE HABILIDAD
            bw.write("NOMNRE-HABILIDAD ");
            bw.write(Discipline.getName());
            bw.newLine();

            //VALOR ATAQUE
            bw.write("VALOR-ATAQUE ");
            bw.write(String.valueOf(Discipline.getAttack()));
            bw.newLine();

            //VALOR DEFENSA
            bw.write("VALOR-DEFENSA ");
            bw.write(String.valueOf(Discipline.getDefense()));
            bw.newLine();

            //COSTE HABILIDAD
            bw.write("COSTE-HABILIDAD ");
            bw.write(String.valueOf(Discipline.getCost()));
            bw.newLine();

            //WeaponS
            bw.write("NUMERO-ARMAS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getWeapons().size()));
            bw.newLine();

            for (int variableWeapon = 0; variableWeapon < (Challenge.getRival().getCharacter().getWeapons().size()); variableWeapon++) {
                Weapon Weapon = Challenge.getRival().getCharacter().getWeapons().get(variableWeapon);
                bw.write("NOMBRE-ARMA ");
                bw.write(Weapon.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA ");
                bw.write(String.valueOf(Weapon.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA ");
                bw.write(String.valueOf(Weapon.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (Weapon.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();

            }
            bw.newLine();

            //NUMERO DE WeaponS ACTIVAS
            bw.write("NUMERO-ARMAS-ACTIVAS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getActiveWeapons().size()));
            bw.newLine();
            for (int variableWeaponActiva = 0; variableWeaponActiva < (Challenge.getRival().getCharacter().getActiveWeapons().size()); variableWeaponActiva++) {
                Weapon WeaponActiva = (Weapon) Vampire.getActiveWeapons().get(variableWeaponActiva);

                bw.write("NOMBRE-ARMAS-ACTIVAS ");
                bw.write(WeaponActiva.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (WeaponActiva.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();
            }
            bw.newLine();

            //ArmorS
            //NUMERO DE ArmorS
            bw.write("NUMERO-ARMADURAS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getArmors().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getRival().getCharacter().getArmors().size()); j++) {
                Armor Armor = (Armor) Vampire.getArmors().get(j);
                bw.write("NOMBRE-ARMADURA ");
                bw.write(Armor.getName());
                bw.newLine();

                bw.write("DEFENSA-ARMADURA ");
                bw.write(String.valueOf(Armor.getDefenseModifier()));
                bw.newLine();

                bw.write("ATAQUE-ARMADURA ");
                bw.write(String.valueOf(Armor.getAttackModifier()));
                bw.newLine();
            }
            bw.newLine();
            Armor Armor = Vampire.getActiveArmor();
            bw.write("NOMBRE-ARMADURA ");
            bw.write(Armor.getName());
            bw.newLine();

            bw.write("DEFENSA-ARMADURA ");
            bw.write(String.valueOf(Armor.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE-ARMADURA ");
            bw.write(String.valueOf(Armor.getAttackModifier()));
            bw.newLine();
            Vampire.setActiveArmor(Armor);
            bw.newLine();

            //EDAD Vampire
            bw.write("EDAD-VAMPIRO ");
            bw.write(String.valueOf(Vampire.getAge()));
            bw.newLine();

            //ESBIRROS
            //NUMERO DE ESBIRROS
            bw.write("NUMERO-ESBIRROS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getMinions().size()));
            bw.newLine();

            //ESBIRROS
            escrituraEsbirrosContrincante(Challenge, Vampire, bw);

            //CANTIDAD ORO
            bw.write("CANTIDAD-ORO ");
            bw.write(String.valueOf(Vampire.getGold()));
            bw.newLine();

            //CANTIDAD VIDA
            bw.write("CANTIDAD-VIDA ");
            bw.write(String.valueOf(Vampire.getHp()));
            bw.newLine();

            //PODER
            bw.write("CANTIDAD-PODER ");
            bw.write(String.valueOf(Vampire.getPower()));
            bw.newLine();

            //WeaknessES
            bw.write("NUMERO-DEBILIDADES ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getWeaknesses().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getRival().getCharacter().getWeaknesses().size()); j++) {
                Weakness Weakness = Vampire.getWeaknesses().get(j);
                bw.write("NOMBRE-DEBILIDAD ");
                bw.write(Weakness.getName());
                bw.newLine();

                bw.write("VALOR-DEBILIDAD ");
                bw.write(String.valueOf(Weakness.getValue()));
                bw.newLine();
            }
            bw.newLine();

            //StrengthS
            bw.write("NUMERO-StrengthS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getStrengths().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getRival().getCharacter().getStrengths().size()); j++) {
                Strength Strength = Vampire.getStrengths().get(j);
                bw.write("NOMBRE-FORTALEZA ");
                bw.write(Strength.getName());
                bw.newLine();

                bw.write("VALOR-FORTALEZA ");
                bw.write(String.valueOf(Strength.getValue()));
                bw.newLine();
            }
            bw.newLine();

            bw.write("========== FIN USUARIO ==========");
            bw.newLine();
        }

        private void escrituraWerewolfContrincante(Challenge Challenge, BufferedWriter bw) throws IOException {

            Werewolf Werewolf = (Werewolf) Challenge.getRival().getCharacter();

            //TIPO Character
            bw.write("TIPO-PERSONAJE ");
            bw.write(Challenge.getRival().getCharacter().getType());
            bw.newLine();
            //NOMBRE Character
            bw.write("NOMBRE-PERSONAJE ");
            bw.write(Challenge.getRival().getCharacter().getName());
            bw.newLine();

            //PUNTOS DE SANGRE
            bw.write("RABIA ");
            bw.write("0");
            bw.newLine();

            //NUMERO WeaponS
            bw.write("NUMERO-ARMAS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getWeapons().size()));
            bw.newLine();

            for (int variableWeapon = 0; variableWeapon < (Challenge.getRival().getCharacter().getWeapons().size()); variableWeapon++) {
                Weapon Weapon = Challenge.getRival().getCharacter().getWeapons().get(variableWeapon);
                bw.write("NOMBRE-ARMA ");
                bw.write(Weapon.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA ");
                bw.write(String.valueOf(Weapon.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA ");
                bw.write(String.valueOf(Weapon.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (Weapon.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();

            }
            bw.newLine();

            //NUMERO DE ARMAS ACTIVAS
            bw.write("NUMERO-ARMAS-ACTIVAS ");
            bw.write(Challenge.getRival().getCharacter().getActiveWeapons().size());
            bw.newLine();
            for (int variableWeaponActiva = 0; variableWeaponActiva < (Challenge.getRival().getCharacter().getActiveWeapons().size()); variableWeaponActiva++) {
                Weapon WeaponActiva = (Weapon) Werewolf.getActiveWeapons().get(variableWeaponActiva);

                bw.write("NOMBRE-ARMAS-ACTIVAS ");
                bw.write(WeaponActiva.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (WeaponActiva.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();
            }
            bw.newLine();

            //ArmorS
            //NUMERO DE ArmorS
            bw.write("NUMERO-ARMADURAS ");
            bw.write(Challenge.getRival().getCharacter().getArmors().size());
            bw.newLine();
            for (int j = 0; j < (Challenge.getRival().getCharacter().getArmors().size()); j++) {
                Armor Armor = (Armor) Werewolf.getArmors().get(j);
                bw.write("NOMBRE-ARMADURA ");
                bw.write(Armor.getName());
                bw.newLine();

                bw.write("DEFENSA-ARMADURA ");
                bw.write(String.valueOf(Armor.getDefenseModifier()));
                bw.newLine();

                bw.write("ATAQUE-ARMADURA ");
                bw.write(String.valueOf(Armor.getAttackModifier()));
                bw.newLine();
            }
            bw.newLine();

            //Armor ACTIVA / EQUIPADA
            bw.write("NOMBRE-ARMADURA-ACTIVA ");
            bw.write(Challenge.getRival().getCharacter().getActiveArmor().getName());
            bw.newLine();

            //DEFENSA Armor ACTIVA / EQUIPADA
            bw.write("DEFENSA-ARMADURA-ACTIVA ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getActiveArmor().getDefenseModifier()));
            bw.newLine();

            //ATAQUE Armor ACTIVA / EQUIPADA
            bw.write("ATAQUE-ARMADURA-ACTIVA ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getActiveArmor().getAttackModifier()));
            bw.newLine();

            //CANTIDAD ORO
            bw.write("ORO ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getGold()));
            bw.newLine();

            //DEBLIDADES
            //NUMERO DE DEBLIDADES
            bw.write("NUMERO-DEBILIDADES ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getWeaknesses().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getRival().getCharacter().getArmors().size()); j++) {
                Weakness Weakness = Werewolf.getWeaknesses().get(j);
                bw.write("NOMBRE-DEBILIADAD ");
                bw.write(Weakness.getName());
                bw.newLine();

                bw.write("VALOR-DEBILIADAD ");
                bw.write(String.valueOf(Weakness.getValue()));
                bw.newLine();
            }
            bw.newLine();

            //StrengthS
            //NUMERO DE StrengthS
            bw.write("NUMERO-FORTALEZAS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getStrengths().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getRival().getCharacter().getArmors().size()); j++) {
                Strength Strength = Werewolf.getStrengths().get(j);
                bw.write("NOMBRE-FORTALEZA ");
                bw.write(Strength.getName());
                bw.newLine();

                bw.write("VALOR-FORTALEZA ");
                bw.write(String.valueOf(Strength.getValue()));
                bw.newLine();
            }
            bw.newLine();

            //ESBIRROS
            //NUMERO DE ESBIRROS
            bw.write("NUMERO-ESBIRROS ");
            bw.write(Challenge.getRival().getCharacter().getMinions().size());
            bw.newLine();

            //ESBIRROS
            escrituraEsbirrosContrincante(Challenge, Werewolf, bw);

            bw.write("========== FIN USUARIO ==========");
            bw.newLine();
        }

        private void escrituraHunterContrincante(Challenge Challenge, BufferedWriter bw) throws IOException {

            Hunter Hunter = (Hunter) Challenge.getRival().getCharacter();

            //TIPO Character
            bw.write("TIPO-PERSONAJE ");
            bw.write(Challenge.getRival().getCharacter().getType());
            bw.newLine();
            //NOMBRE Character
            bw.write("NOMBRE-PERSONAJE ");
            bw.write(Challenge.getRival().getCharacter().getName());
            bw.newLine();

            //NOMBRE HABILDIAD
            bw.write("NOMBRE-HABILIDAD ");
            bw.write(Hunter.getName());
            bw.newLine();

            //ATAQUE HABILIDAD
            bw.write("ATAQUE-HABILIDAD ");
            bw.write(String.valueOf(Hunter.getAbility().getAttack()));
            bw.newLine();

            //Weakness HABILIDAD
            bw.write("DEFENSA-HABILIDAD ");
            bw.write(String.valueOf(Hunter.getAbility().getDefense()));
            bw.newLine();

            //EDAD Hunter
            bw.write("EDAD-CAZADOR ");
            bw.write(String.valueOf(Hunter.getWillpower()));  //LA EDAD ES LA VOLUNTAD DEL Hunter
            bw.newLine();

            //WeaponS
            //NUMERO WeaponS
            bw.write("NUMERO-ARMAS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getWeapons().size()));
            bw.newLine();

            for (int variableWeapon = 0; variableWeapon < (Challenge.getRival().getCharacter().getWeapons().size()); variableWeapon++) {
                Weapon Weapon = Challenge.getRival().getCharacter().getWeapons().get(variableWeapon);
                bw.write("NOMBRE-ARMA ");
                bw.write(Weapon.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA ");
                bw.write(String.valueOf(Weapon.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA ");
                bw.write(String.valueOf(Weapon.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (Weapon.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();

            }
            bw.newLine();

            //WeaponS ACTIVAS
            //NUMERO DE ARMAS ACTIVAS
            bw.write("NUMERO-ARMAS-ACTIVAS ");
            bw.write(Challenge.getRival().getCharacter().getActiveWeapons().size());
            bw.newLine();
            for (int variableWeaponActiva = 0; variableWeaponActiva < (Challenge.getRival().getCharacter().getActiveWeapons().size()); variableWeaponActiva++) {
                Weapon WeaponActiva = (Weapon) Hunter.getActiveWeapons().get(variableWeaponActiva);

                bw.write("NOMBRE-ARMAS-ACTIVAS ");
                bw.write(WeaponActiva.getName());
                bw.newLine();

                bw.write("ATAQUE-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getAttackModifier()));
                bw.newLine();

                bw.write("DEFENSA-ARMA-ACTIVAS ");
                bw.write(String.valueOf(WeaponActiva.getDefenseModifier()));
                bw.newLine();

                //si es true es de 1 mano, si es false es de dos manos
                bw.write("EMPUÑADURA ");
                if (WeaponActiva.isSingleHand()) {
                    bw.write("true");
                } else {
                    bw.write("false");
                }
                bw.newLine();
            }
            bw.newLine();

            //ArmorS
            //NUMERO DE ArmorS
            bw.write("NUMERO-ARMADURAS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getArmors().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getRival().getCharacter().getArmors().size()); j++) {
                Armor Armor = Hunter.getArmors().get(j);
                bw.write("NOMBRE-ARMADURA ");
                bw.write(Armor.getName());
                bw.newLine();

                bw.write("DEFENSA-ARMADURA ");
                bw.write(String.valueOf(Armor.getDefenseModifier()));
                bw.newLine();

                bw.write("ATAQUE-ARMADURA ");
                bw.write(String.valueOf(Armor.getAttackModifier()));
                bw.newLine();
            }
            bw.newLine();

            //ArmorS EQUIPADA
            bw.write("NOMBRE-ARMADURA-ACTIVA ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getActiveArmor().getName()));
            bw.newLine();

            bw.write("DEFENSA-ARMADURA-ACTIVA ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getActiveArmor().getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE-ARMADURA-ACTIVA ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getActiveArmor().getAttackModifier()));
            bw.newLine();

            bw.newLine();

            //CANTIDAD ORO
            bw.write("ORO ");
            bw.write(String.valueOf(Hunter.getGold()));
            bw.newLine();

            //CANTIDAD VDA
            bw.write("VIDA ");
            bw.write(String.valueOf(Hunter.getHp()));
            bw.newLine();

            //PODER
            bw.write("PODER ");
            bw.write(String.valueOf(Hunter.getPower()));
            bw.newLine();

            //WeaknessES
            //NUMERO DE WeaknessES
            bw.write("NUMERO-DEBILIDADES ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getWeaknesses().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getRival().getCharacter().getArmors().size()); j++) {
                Weakness Weakness = Hunter.getWeaknesses().get(j);
                bw.write("NOMBRE-DEBILIDAD ");
                bw.write(Weakness.getName());
                bw.newLine();

                bw.write("VALOR-DEBILIDAD ");
                bw.write(String.valueOf(Weakness.getValue()));
                bw.newLine();
            }
            bw.newLine();

            //StrengthS
            //NUMERO DE StrengthS
            bw.write("NUMERO-FORTALEZAS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getWeaknesses().size()));
            bw.newLine();
            for (int j = 0; j < (Challenge.getRival().getCharacter().getArmors().size()); j++) {
                Strength Strength = Hunter.getStrengths().get(j);
                bw.write("NOMBRE-FORTALEZA ");
                bw.write(Strength.getName());
                bw.newLine();

                bw.write("VALOR-FORTALEZA ");
                bw.write(String.valueOf(Strength.getValue()));
                bw.newLine();
            }
            bw.newLine();

            //ESBIRROS
            //NUMERO DE ESBIRROS
            bw.write("NUMERO-ESBIRROS ");
            bw.write(String.valueOf(Challenge.getRival().getCharacter().getMinions().size()));
            bw.newLine();

            escrituraEsbirrosContrincante(Challenge, Hunter, bw);

            bw.write("========== FIN USUARIO ==========");
            bw.newLine();
        }

        private void escrituraEsbirrosContrincante(Challenge Challenge, Character Character, BufferedWriter bw) throws IOException {
            for (int j = 0; j < (Challenge.getRival().getCharacter().getMinions().size()); j++) {
                if (Character.getMinions().get(j).getType().equals("Human")) {
                    Human human = (Human) Character.getMinions().get(j);
                    //NUMERO DE ESBIRROS
                    bw.write("TIPO-ESBIRRO ");

                    bw.write(human.getType());
                    bw.newLine();

                    //NOMBRE DE ESBIRROS
                    bw.write("NOMBRE-ESBIRRO ");
                    bw.write(human.getName());
                    bw.newLine();

                    //VIDA DE ESBIRROS
                    bw.write("VIDA-ESBIRRO ");
                    bw.write(String.valueOf(human.getHp()));
                    bw.newLine();

                    //LEALTAD ESBIRRO Human
                    bw.write("LELTAD ");
                    if (human.getLoyalty() == Human.Loyalty.ALTA) {
                        bw.write("ALTA");
                    } else if (human.getLoyalty() == Human.Loyalty.MEDIA) {
                        bw.write("MEDIA");
                    } else if (human.getLoyalty() == Human.Loyalty.BAJA) {
                        bw.write("BAJA");
                    }
                    bw.newLine();

                } else if (Character.getMinions().get(j).getType().equals("GHOUL")) {
                    Ghoul ghoul = (Ghoul) Character.getMinions().get(j);
                    //NUMERO DE ESBIRRO
                    bw.write("TIPO-ESBIRRO ");
                    bw.write(ghoul.getType());
                    bw.newLine();

                    //NOMBRE DE ESBIRRO
                    bw.write("NOMBRE-ESBIRRO ");
                    bw.write(ghoul.getName());
                    bw.newLine();

                    //VIDA DE ESBIRRO
                    bw.write("VIDA-ESBIRRO ");
                    bw.write(String.valueOf(ghoul.getHp()));
                    bw.newLine();

                    //DEPENDENCIA ESBIRRO
                    bw.write("DEPENDENCIA ");
                    bw.write(String.valueOf(ghoul.getDependency()));
                    bw.newLine();

                } else if (Character.getMinions().get(j).getType().equals("Demon")) {
                    Demon Demon = (Demon) Character.getMinions().get(j);
                    //TIPO DE ESBIRRO
                    bw.write("TIPO-ESBIRRO ");

                    bw.write(Demon.getType());
                    bw.newLine();

                    //NOMBRE DE ESBIRRO
                    bw.write("NOMBRE-ESBIRRO ");
                    bw.write(Demon.getName());
                    bw.newLine();

                    //VIDA ESBIRRO
                    bw.write("VIDA-ESBIRRO ");
                    bw.write(String.valueOf(Demon.getHp()));
                    bw.newLine();

                    //DESCRIPCION / PACTO
                    bw.write("DESCRIPCION ");
                    bw.write(Demon.getDescription());
                    bw.newLine();

                    //ESBIRROS EXTRA
                    //NUMERO DE ESBIRROS EXTRA
                    bw.write("NUMERO-ESBIRROS-EXTRA ");
                    bw.write(String.valueOf(Challenge.getRival().getCharacter().getMinions().size()));
                    bw.newLine();
                    escrituraEsbirrosContrincante(Challenge, Character, bw);
                }
            }
        }

    }
