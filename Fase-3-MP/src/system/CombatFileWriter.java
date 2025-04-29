package System;

import Entities.*;
import Entities.Character;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CombatFileWriter {


    public void combatRegister (Combat combat) {


        try {
            String ruta = "./Fase-3-MP/src/Files/CombatRegister.txt";
            File file = new File(ruta);
            /** Si el archivo no existiese se crea **/
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("========== COMBAT ==========");
            bw.newLine();

            /**  INICIO DESAFIANTE **/
            bw.write("DESAFIANTE: ");
            bw.write(combat.getChallenger().getName());
            bw.newLine();

            bw.write("NICK: ");
            bw.write(combat.getChallenger().getNick());
            bw.newLine();

            bw.write("PASSWORD: ");
            bw.write(combat.getChallenger().getPassword());
            bw.newLine();

            bw.write("REGISTRO: ");
            bw.write(combat.getChallenger().getRegister());
            bw.newLine();

            String tipoCharacterDesafiante = combat.getChallenger().getCharacter().getType();
            if (tipoCharacterDesafiante == null) {
                bw.write("TIPO_PERSONAJE: null");
                bw.newLine();
            } else if (tipoCharacterDesafiante.equals("VAMPIRO")) {
                writeVampireChallenger(combat, bw);
            } else if (tipoCharacterDesafiante.equals("LICANTROPO")) {
                writeWerewolfChallenger(combat, bw);
            } else if (tipoCharacterDesafiante.equals("CAZADOR")) {
                writeHunterChallenger(combat, bw);
            }
            /** FIN DESAFIANTE **/

            /** INICIO CONTRINCANTE  **/
            bw.write("CONTRINCANTE: ");
            bw.write(combat.getRival().getName());
            bw.newLine();

            bw.write("NICK: ");
            bw.write(combat.getRival().getNick());
            bw.newLine();

            bw.write("PASSWORD: ");
            bw.write(combat.getRival().getPassword());
            bw.newLine();

            bw.write("REGISTRO: ");
            bw.write(combat.getRival().getRegister());
            bw.newLine();

            String tipoCharacterContrincante = combat.getRival().getCharacter().getType();
            if (tipoCharacterContrincante == null) {
                bw.write("TIPO_PERSONAJE: null");
                bw.newLine();
            } else if (tipoCharacterContrincante.equals("VAMPIRO")) {
                writeVampireRival(combat, bw);
            } else if (tipoCharacterContrincante.equals("LICANTROPO")) {
                writeWerewolfRival(combat, bw);
            } else if (tipoCharacterContrincante.equals("CAZADOR")) {
                writeHunterRival(combat, bw);
            }
            /** FIN CONTRINCANTE  **/

            //RONDAS
            bw.write("RONDAS: ");
            bw.write(String.valueOf(combat.getRounds().size()));
            bw.newLine();
            for (int j = 0; j < (combat.getRounds().size()); j++) {
                Round round = (Round) combat.getRounds().get(j);
                bw.write("VIDA_CONTRINCANTE: ");
                bw.write(String.valueOf(round.getHpRivalEnd()));
                bw.newLine();

                bw.write("VIDA_DESAFIANTE: ");
                bw.write(String.valueOf(round.getHpChallengerEnd()));
                bw.newLine();

            }
            bw.newLine();

            String pattern = "dd-MM-yyyy HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(combat.getDate());
            bw.write("FECHA: ");
            bw.write(date);
            bw.newLine();

            /** INICIO VENCEDOR  **/
            bw.write("VENCEDOR: ");
            if (combat.getWinner()!= null) {
                bw.write(combat.getWinner().getNick());
            } else {
                bw.write("null");
            }
            bw.newLine();
            /** FIN  VENCEDOR  **/
            //ESBIRRO DESAFIANTE
            bw.write("ESBIRRO_DESAFIANTE: ");
            if (combat.isChallengerMinion()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

            /** ESBIRRO CONTRINCANTE **/
            bw.write("ESBIRRO_CONTRINCANTE: ");
            if (combat.isRivalMinion()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

            bw.write("ORO: ");
            bw.write(String.valueOf(combat.getGold()));
            bw.newLine();

            /** MODIFICADOR **/
            bw.write("CANTIDAD_MODIFICADORES: ");
            bw.write(String.valueOf(combat.getModifiers().size()));
            bw.newLine();
            for (int j = 0; j < (combat.getModifiers().size()); j++) {
                Modifier modifier = combat.getModifiers().get(j);
                bw.write("NOMBRE_MODIFICADOR: ");
                bw.write(modifier.getName());
                bw.newLine();

                bw.write("VALOR_DEBILIDAD: ");
                bw.write(String.valueOf(modifier.getValue()));
                bw.newLine();

            }
            bw.newLine();
            /** FIN MODIFICADOR **/
            bw.write("REGISTRO: ");
            bw.write(combat.getRegister());
            bw.newLine();

            bw.write("VISTO: ");
            if (combat.isSeen()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

            bw.newLine();

            bw.write("FIN COMBATE");
            bw.newLine();
            bw.close();

        } catch (Exception e) {
            mainSystem system = new mainSystem();
            system.selector();
            e.printStackTrace();
        }
    }

    public void overwriteCombatFile(List<Combat> combatList, String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (Combat combat : combatList) {
                bw.write("========== CHALLENGE ==========");
                bw.newLine();
                bw.write("DESAFIANTE " + combat.getChallenger().getName());
                bw.newLine();
                bw.write("NICK " + combat.getChallenger().getNick());
                bw.newLine();
                bw.write("CANTIDAD-ORO " + combat.getChallenger().getGold());
                bw.newLine();
                bw.write("CANTIDAD-VIDA " + combat.getChallenger().getHp());
                bw.newLine();
                bw.write("========== FIN USUARIO ==========");
                bw.newLine();
                bw.write("CONTRINCANTE " + combat.getRival().getName());
                bw.newLine();
                bw.write("NICK " + combat.getRival().getNick());
                bw.newLine();
                bw.write("CANTIDAD-ORO " + combat.getRival().getGold());
                bw.newLine();
                bw.write("CANTIDAD-VIDA " + combat.getRival().getHp());
                bw.newLine();
                bw.write("========== FIN CHALLENGE ==========");
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al sobrescribir el archivo: " + e.getMessage());
        }
    }

    private void writeVampireChallenger(Combat combat, BufferedWriter bw) throws IOException {

        Vampire vampire = (Vampire) combat.getChallenger().getCharacter();
        Discipline discipline = (Discipline) vampire.getAbility();

        /** TIPO PERSONAJE **/
        bw.write("TIPO_PERSONAJE: ");
        bw.write(combat.getChallenger().getCharacter().getType());
        bw.newLine();
        /** NOMBRE PERSONAJE **/
        bw.write("NOMBRE_PERSONAJE: ");
        bw.write(combat.getChallenger().getCharacter().getName());
        bw.newLine();
        /** PUNTOS DE SANGRE **/
        bw.write("SANGRE: ");
        bw.write("0");
        bw.newLine();
        /** NOMBRE DE HABILIDAD **/
        bw.write("NOMNRE_HABILIDAD: ");
        bw.write(discipline.getName());
        bw.newLine();

        /** VALOR ATAQUE **/
        bw.write("VALOR_ATAQUE: ");
        bw.write(String.valueOf(discipline.getAttack()));
        bw.newLine();

        /** VALOR DEFENSA **/
        bw.write("VALOR_DEFENSA: ");
        bw.write(String.valueOf(discipline.getDefense()));
        bw.newLine();

        /** COSTE HABILIDAD **/
        bw.write("COSATE_HABILIDAD: ");
        bw.write(String.valueOf(discipline.getCost()));
        bw.newLine();

        /** ARMAS **/
        bw.write("NUMERO_ARMAS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getWeapons().size()));
        bw.newLine();

        for (int variableWeapon = 0; variableWeapon < (combat.getChallenger().getCharacter().getWeapons().size()); variableWeapon++) {
            Weapon weapon = combat.getChallenger().getCharacter().getWeapons().get(variableWeapon);
            bw.write("NOMBRE_ARMA: ");
            bw.write(weapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA: ");
            bw.write(String.valueOf(weapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA: ");
            bw.write(String.valueOf(weapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (weapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

        }
        bw.newLine();

        /** NUMERO DE ARMAS ACTIVAS **/
        bw.write("NUMERO_ARMAS_ACTIVAS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getActiveWeapons().size()));
        bw.newLine();
        for (int varActiveWeapon = 0; varActiveWeapon < (combat.getChallenger().getCharacter().getActiveWeapons().size()); varActiveWeapon++) {
            Weapon activeWeapon = vampire.getActiveWeapons().get(varActiveWeapon);

            bw.write("NOMBRE_ARMAS_ACTIVAS: ");
            bw.write(activeWeapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (activeWeapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();
        }
        bw.newLine();

        /** ARMADURAS **/
        /** NUMERO DE ARMADURAS **/
        bw.write("NUMERO_ARMADURAS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getArmors().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getChallenger().getCharacter().getArmors().size()); j++) {
            Armor armor = vampire.getArmors().get(j);
            bw.write("NOMBRE_ARMADURA: ");
            bw.write(armor.getName());
            bw.newLine();

            bw.write("DEFENSA_ARMADURA: ");
            bw.write(String.valueOf(armor.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE_ARMADURA: ");
            bw.write(String.valueOf(armor.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();

        /** ARMADURA ACTIVA **/
        Armor armor = vampire.getActiveArmor();
        bw.write("NOMBRE_ARMADURA: ");
        bw.write(armor.getName());
        bw.newLine();

        bw.write("DEFENSA_ARMADURA: ");
        bw.write(String.valueOf(armor.getDefenseModifier()));
        bw.newLine();

        bw.write("ATAQUE_ARMADURA: ");
        bw.write(String.valueOf(armor.getAttackModifier()));
        bw.newLine();
        vampire.setActiveArmor(armor);
        bw.newLine();

        /** EDAD VAMPIRO **/
        bw.write("EDAD_VAMPIRO: ");
        bw.write(String.valueOf(vampire.getAge()));
        bw.newLine();

        /** ESBIRROS **/
        /** NUMERO DE ESBIRROS **/
        bw.write("NUMERO_ESBIRROS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getMinions().size()));
        bw.newLine();

        /** ESBIRROS **/
        writeMinionsChallenge(combat, vampire, bw);

        /** CANTIDAD ORO **/
        bw.write("CANTIDAD_ORO: ");
        bw.write(String.valueOf(vampire.getGold()));
        bw.newLine();

        /** CANTIDAD VIDA **/
        bw.write("CANTIDAD_VIDA: ");
        bw.write(String.valueOf(vampire.getHp()));
        bw.newLine();

        /** PODER **/
        bw.write("CANTIDAD_PODER: ");
        bw.write(String.valueOf(vampire.getPower()));
        bw.newLine();

        /** DEBILIDADES **/
        bw.write("NUMERO_DEBILIDADES: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getChallenger().getCharacter().getWeaknesses().size()); j++) {
            Weakness weakness = vampire.getWeaknesses().get(j);
            bw.write("NOMBRE_DEBILIDAD: ");
            bw.write(weakness.getName());
            bw.newLine();

            bw.write("VALOR_DEBILIDAD: ");
            bw.write(String.valueOf(weakness.getValue()));
            bw.newLine();
        }
        bw.newLine();

        /** FORTALEZAS **/
        bw.write("NUMERO_FORTALEZAS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getStrengths().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getChallenger().getCharacter().getStrengths().size()); j++) {
            Strength strength = vampire.getStrengths().get(j);
            bw.write("NOMBRE_FORTALEZA: ");
            bw.write(strength.getName());
            bw.newLine();

            bw.write("VALOR_FORTALEZA: ");
            bw.write(String.valueOf(strength.getValue()));
            bw.newLine();
        }
        bw.newLine();


        bw.write("FIN_USUARIO");
        bw.newLine();
    }

    private void writeWerewolfChallenger(Combat combat, BufferedWriter bw) throws IOException {

        Werewolf werewolf = (Werewolf) combat.getChallenger().getCharacter();

        //TIPO PERSONAJE
        bw.write("TIPO_PERSONAJE: ");
        bw.write(combat.getChallenger().getCharacter().getType());
        bw.newLine();
        //NOMBRE PERSONAJE
        bw.write("NOMBRE_PERSONAJE: ");
        bw.write(combat.getChallenger().getCharacter().getName());
        bw.newLine();

        //NOMBRE HABILIDAD
        bw.write("NOMBRE_HABILIDAD: ");
        bw.write(combat.getChallenger().getCharacter().getAbility().getName());
        bw.newLine();

        //PUNTOS DE SANGRE
        bw.write("RABIA: ");
        bw.write("0");
        bw.newLine();

        //NUMERO ARMAS
        bw.write("NUMERO_ARMAS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getWeapons().size()));
        bw.newLine();

        for (int variableWeapon = 0; variableWeapon < (combat.getChallenger().getCharacter().getWeapons().size()); variableWeapon++) {
            Weapon weapon = combat.getChallenger().getCharacter().getWeapons().get(variableWeapon);
            bw.write("NOMBRE_ARMA: ");
            bw.write(weapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA: ");
            bw.write(String.valueOf(weapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA: ");
            bw.write(String.valueOf(weapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (weapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

        }
        bw.newLine();

        //NUMERO DE ARMAS ACTIVAS
        bw.write("NUMERO_ARMAS_ACTIVAS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getActiveWeapons().size()));
        bw.newLine();
        for (int varActiveWeapon = 0; varActiveWeapon < (combat.getChallenger().getCharacter().getActiveWeapons().size()); varActiveWeapon++) {
            Weapon activeWeapon = werewolf.getActiveWeapons().get(varActiveWeapon);

            bw.write("NOMBRE_ARMAS_ACTIVAS: ");
            bw.write(activeWeapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (activeWeapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();
        }
        bw.newLine();

        //ARMADURAS
        //NUMERO DE ARMADURAS
        bw.write("NUMERO_ARMADURAS: ");
        bw.write(combat.getChallenger().getCharacter().getArmors().size());
        bw.newLine();
        for (int j = 0; j < (combat.getChallenger().getCharacter().getArmors().size()); j++) {
            Armor armor = werewolf.getArmors().get(j);
            bw.write("NOMBRE_ARMADURA: ");
            bw.write(armor.getName());
            bw.newLine();

            bw.write("DEFENSA_ARMADURA: ");
            bw.write(String.valueOf(armor.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE_ARMADURA: ");
            bw.write(String.valueOf(armor.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();

        //ARMADURA ACTIVA / EQUIPADA
        bw.write("NOMBRE_ARMADURA_ACTIVA: ");
        bw.write(combat.getChallenger().getCharacter().getActiveArmor().getName());
        bw.newLine();

        //DEFENSA ARMADURA ACTIVA / EQUIPADA
        bw.write("DEFENSA_ARMADURA_ACTIVA: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getActiveArmor().getDefenseModifier()));
        bw.newLine();

        //ATAQUE ARMADURA ACTIVA / EQUIPADA
        bw.write("ATAQUE_ARMADURA_ACTIVA: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getActiveArmor().getAttackModifier()));
        bw.newLine();

        //CANTIDAD ORO
        bw.write("ORO: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getGold()));
        bw.newLine();

        //CANTIDAD ORO
        bw.write("HP: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getHp()));
        bw.newLine();

        //CANTIDAD ORO
        bw.write("PODER: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getPower()));
        bw.newLine();


        //DEBLIDADES
        //NUMERO DE DEBLIDADES
        bw.write("NUMERO_DEBILIDADES: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getChallenger().getCharacter().getArmors().size()); j++) {
            Weakness weakness = werewolf.getWeaknesses().get(j);
            bw.write("NOMBRE_DEBILIADAD: ");
            bw.write(weakness.getName());
            bw.newLine();

            bw.write("VALOR_DEBILIADAD: ");
            bw.write(String.valueOf(weakness.getValue()));
            bw.newLine();
        }
        bw.newLine();

        //FORTALEZAS
        //NUMERO DE FORTALEZAS
        bw.write("NUMERO_FORTALEZAS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getStrengths().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getChallenger().getCharacter().getArmors().size()); j++) {
            Strength strength = werewolf.getStrengths().get(j);
            bw.write("NOMBRE_FORTALEZA: ");
            bw.write(strength.getName());
            bw.newLine();

            bw.write("VALOR_FORTALEZA: ");
            bw.write(String.valueOf(strength.getValue()));
            bw.newLine();
        }
        bw.newLine();

        //ESBIRROS
        //NUMERO DE ESBIRROS
        bw.write("NUMERO_ESBIRROS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getMinions().size()));
        bw.newLine();

        //ESBIRROS
        writeMinionsChallenge(combat, werewolf, bw);

        bw.write("FIN_USUARIO");
        bw.newLine();
    }

    private void writeHunterChallenger(Combat combat, BufferedWriter bw) throws IOException  {

        Hunter hunter = (Hunter) combat.getChallenger().getCharacter();

        //TIPO PERSONAJE
        bw.write("TIPO_PERSONAJE: ");
        bw.write(combat.getChallenger().getCharacter().getType());
        bw.newLine();
        //NOMBRE PERSONAJE
        bw.write("NOMBRE_PERSONAJE: ");
        bw.write(combat.getChallenger().getCharacter().getName());
        bw.newLine();

        //VOLUNTAD CAZADOR
        bw.write("RABIA: ");
        bw.write("0");
        bw.newLine();

        //NOMBRE HABILDIAD
        bw.write("NOMBRE_HABILIDAD: ");
        bw.write(hunter.getAbility().getName());
        bw.newLine();

        //ATAQUE HABILIDAD
        bw.write("ATAQUE_HABILIDAD: ");
        bw.write(String.valueOf(hunter.getAbility().getAttack()));
        bw.newLine();

        //DEBILIDAD HABILIDAD
        bw.write("DEFENSA_HABILIDAD: ");
        bw.write(String.valueOf(hunter.getAbility().getDefense()));
        bw.newLine();

        //EDAD CAZADOR
        bw.write("EDAD_CAZADOR: ");
        bw.write(String.valueOf(hunter.getWillpower()));  //LA EDAD ES LA VOLUNTAD DEL CAZADOR
        bw.newLine();

        //ARMAS
        //NUMERO ARMAS
        bw.write("NUMERO_ARMAS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getWeapons().size()));
        bw.newLine();

        for (int variableWeapon = 0; variableWeapon < (combat.getChallenger().getCharacter().getWeapons().size()); variableWeapon++) {
            Weapon weapon = combat.getChallenger().getCharacter().getWeapons().get(variableWeapon);
            bw.write("NOMBRE_ARMA: ");
            bw.write(weapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA: ");
            bw.write(String.valueOf(weapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA: ");
            bw.write(String.valueOf(weapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (weapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

        }
        bw.newLine();

        //ARMAS ACTIVAS
        //NUMERO DE ARMAS ACTIVAS
        bw.write("NUMERO_ARMAS_ACTIVAS: ");
        bw.write(combat.getChallenger().getCharacter().getActiveWeapons().size());
        bw.newLine();
        for (int varActiveWeapon = 0; varActiveWeapon < (combat.getChallenger().getCharacter().getActiveWeapons().size()); varActiveWeapon++) {
            Weapon activeWeapon = hunter.getActiveWeapons().get(varActiveWeapon);

            bw.write("NOMBRE_ARMAS_ACTIVAS: ");
            bw.write(activeWeapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (activeWeapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();
        }
        bw.newLine();

        //ARMADURAS
        //NUMERO DE ARMADURAS
        bw.write("NUMERO_ARMADURAS: ");
        bw.write(combat.getChallenger().getCharacter().getArmors().size());
        bw.newLine();
        for (int j = 0; j < (combat.getChallenger().getCharacter().getArmors().size()); j++) {
            Armor armor = hunter.getArmors().get(j);
            bw.write("NOMBRE_ARMADURA: ");
            bw.write(armor.getName());
            bw.newLine();

            bw.write("DEFENSA_ARMADURA: ");
            bw.write(String.valueOf(armor.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE_ARMADURA: ");
            bw.write(String.valueOf(armor.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();

        //ARMADURAS EQUIPADA
        bw.write("NOMBRE_ARMADURA_ACTIVA: ");
        bw.write(combat.getChallenger().getCharacter().getActiveArmor().getName());
        bw.newLine();

        bw.write("DEFENSA_ARMADURA_ACTIVA: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getActiveArmor().getDefenseModifier()));
        bw.newLine();

        bw.write("ATAQUE_ARMADURA_ACTIVA: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getActiveArmor().getAttackModifier()));
        bw.newLine();

        bw.newLine();

        //CANTIDAD ORO
        bw.write("ORO: ");
        bw.write(String.valueOf(hunter.getGold()));
        bw.newLine();

        //CANTIDAD VDA
        bw.write("VIDA: ");
        bw.write(String.valueOf(hunter.getHp()));
        bw.newLine();

        //PODER
        bw.write("PODER: ");
        bw.write(String.valueOf(hunter.getPower()));
        bw.newLine();

        //FORTALEZAS
        //NUMERO DE FORTALEZAS
        bw.write("NUMERO_FORTALEZAS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getChallenger().getCharacter().getArmors().size()); j++) {
            Strength strength = hunter.getStrengths().get(j);
            bw.write("NOMBRE_FORTALEZA: ");
            bw.write(strength.getName());
            bw.newLine();

            bw.write("VALOR_FORTALEZA: ");
            bw.write(String.valueOf(strength.getValue()));
            bw.newLine();
        }
        bw.newLine();


        //DEBILIDADES
        //NUMERO DE DEBILIDADES
        bw.write("NUMERO_DEBILIDADES: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getChallenger().getCharacter().getArmors().size()); j++) {
            Weakness weakness = hunter.getWeaknesses().get(j);
            bw.write("NOMBRE_DEBILIDAD: ");
            bw.write(weakness.getName());
            bw.newLine();

            bw.write("VALOR_DEBILIDAD: ");
            bw.write(String.valueOf(weakness.getValue()));
            bw.newLine();
        }
        bw.newLine();



        //ESBIRROS
        //NUMERO DE ESBIRROS
        bw.write("NUMERO_ESBIRROS: ");
        bw.write(String.valueOf(combat.getChallenger().getCharacter().getMinions().size()));
        bw.newLine();

        writeMinionsChallenge(combat, hunter, bw);

        bw.write("FIN_USUARIO");
        bw.newLine();
    }

    private void writeMinionsChallenge(Combat combat, Character character, BufferedWriter bw) throws IOException {
        for (int j = 0; j < (combat.getChallenger().getCharacter().getMinions().size()); j++) {
            if (character.getMinions().get(j).getType().equals("HUMANO")) {
                Human human = (Human) character.getMinions().get(j);
                //NUMERO DE ESBIRROS
                bw.write("TIPO_ESBIRRO: ");

                bw.write(human.getType());
                bw.newLine();

                //NOMBRE DE ESBIRROS
                bw.write("NOMBRE_ESBIRRO: ");
                bw.write(human.getName());
                bw.newLine();

                //VIDA DE ESBIRROS
                bw.write("VIDA_ESBIRRO: ");
                bw.write(String.valueOf(human.getHp()));
                bw.newLine();

                //LEALTAD ESBIRRO HUMANO
                bw.write("LELTAD: ");
                if (human.getLoyalty() == Human.Loyalty.ALTA) {
                    bw.write("ALTA");
                } else if (human.getLoyalty() == Human.Loyalty.MEDIA) {
                    bw.write("MEDIA");
                } else if (human.getLoyalty() == Human.Loyalty.BAJA) {
                    bw.write("BAJA");
                }
                bw.newLine();

            } else if (character.getMinions().get(j).getType().equals("GHOUL")) {
                Ghoul ghoul = (Ghoul) character.getMinions().get(j);
                //NUMERO DE ESBIRRO
                bw.write("TIPO_ESBIRRO: ");
                bw.write(ghoul.getType());
                bw.newLine();

                //NOMBRE DE ESBIRRO
                bw.write("NOMBRE_ESBIRRO: ");
                bw.write(ghoul.getName());
                bw.newLine();

                //VIDA DE ESBIRRO
                bw.write("VIDA_ESBIRRO: ");
                bw.write(String.valueOf(ghoul.getHp()));
                bw.newLine();

                //DEPENDENCIA ESBIRRO
                bw.write("DEPENDENCIA: ");
                bw.write(String.valueOf(ghoul.getDependency()));
                bw.newLine();

            } else if (character.getMinions().get(j).getType().equals("DEMONIO")) {
                Demon demon = (Demon) character.getMinions().get(j);
                //TIPO DE ESBIRRO
                bw.write("TIPO_ESBIRRO: ");

                bw.write(demon.getType());
                bw.newLine();

                //NOMBRE DE ESBIRRO
                bw.write("NOMBRE_ESBIRRO: ");
                bw.write(demon.getName());
                bw.newLine();

                //VIDA ESBIRRO
                bw.write("VIDA_ESBIRRO: ");
                bw.write(String.valueOf(demon.getHp()));
                bw.newLine();

                //DESCRIPCION / PACTO
                bw.write("DESCRIPCION: ");
                bw.write(demon.getDescription());
                bw.newLine();

                //ESBIRROS EXTRA
                //NUMERO DE ESBIRROS EXTRA
                bw.write("NUMERO_ESBIRROS_EXTRA: ");
                bw.write(String.valueOf(combat.getChallenger().getCharacter().getMinions().size()));
                bw.newLine();
                writeMinionsChallenge(combat, character, bw);
            }
        }
    }

    private void writeVampireRival(Combat combat, BufferedWriter bw) throws IOException {

        Vampire vampire = (Vampire) combat.getRival().getCharacter();
        Discipline discipline = (Discipline) vampire.getAbility();

        //TIPO PERSONAJE
        bw.write("TIPO_PERSONAJE: ");
        bw.write(combat.getRival().getCharacter().getType());
        bw.newLine();
        //NOMBRE PERSONAJE
        bw.write("NOMBRE_PERSONAJE: ");
        bw.write(combat.getRival().getCharacter().getName());
        bw.newLine();
        //PUNTOS DE SANGRE
        bw.write("SANGRE: ");
        bw.write("0");
        bw.newLine();
        //NOMBRE DE HABILIDAD
        bw.write("NOMNRE_HABILIDAD: ");
        bw.write(discipline.getName());
        bw.newLine();

        //VALOR ATAQUE
        bw.write("VALOR_ATAQUE: ");
        bw.write(String.valueOf(discipline.getAttack()));
        bw.newLine();

        //VALOR DEFENSA
        bw.write("VALOR_DEFENSA: ");
        bw.write(String.valueOf(discipline.getDefense()));
        bw.newLine();

        //COSTE HABILIDAD
        bw.write("COSATE_HABILIDAD: ");
        bw.write(String.valueOf(discipline.getCost()));
        bw.newLine();

        //ARMAS
        bw.write("NUMERO_ARMAS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getWeapons().size()));
        bw.newLine();

        for (int variableWeapon = 0; variableWeapon < (combat.getRival().getCharacter().getWeapons().size()); variableWeapon++) {
            Weapon weapon = combat.getRival().getCharacter().getWeapons().get(variableWeapon);
            bw.write("NOMBRE_ARMA: ");
            bw.write(weapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA: ");
            bw.write(String.valueOf(weapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA: ");
            bw.write(String.valueOf(weapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (weapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

        }
        bw.newLine();

        //NUMERO DE ARMAS ACTIVAS
        bw.write("NUMERO_ARMAS_ACTIVAS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getActiveWeapons().size()));
        bw.newLine();
        for (int varActiveWeapon = 0; varActiveWeapon < (combat.getRival().getCharacter().getActiveWeapons().size()); varActiveWeapon++) {
            Weapon activeWeapon = (Weapon) vampire.getActiveWeapons().get(varActiveWeapon);

            bw.write("NOMBRE_ARMAS_ACTIVAS: ");
            bw.write(activeWeapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (activeWeapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();
        }
        bw.newLine();

        //ARMADURAS
        //NUMERO DE ARMADURAS
        bw.write("NUMERO_ARMADURAS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getArmors().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getRival().getCharacter().getArmors().size()); j++) {
            Armor armor = (Armor) vampire.getArmors().get(j);
            bw.write("NOMBRE_ARMADURA: ");
            bw.write(armor.getName());
            bw.newLine();

            bw.write("DEFENSA_ARMADURA: ");
            bw.write(String.valueOf(armor.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE_ARMADURA: ");
            bw.write(String.valueOf(armor.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();
        Armor armor = vampire.getActiveArmor();
        bw.write("NOMBRE_ARMADURA: ");
        bw.write(armor.getName());
        bw.newLine();

        bw.write("DEFENSA_ARMADURA: ");
        bw.write(String.valueOf(armor.getDefenseModifier()));
        bw.newLine();

        bw.write("ATAQUE_ARMADURA: ");
        bw.write(String.valueOf(armor.getAttackModifier()));
        bw.newLine();
        vampire.setActiveArmor(armor);
        bw.newLine();

        //EDAD VAMPIRO
        bw.write("EDAD_VAMPIRO: ");
        bw.write(String.valueOf(vampire.getAge()));
        bw.newLine();

        //ESBIRROS
        //NUMERO DE ESBIRROS
        bw.write("NUMERO_ESBIRROS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getMinions().size()));
        bw.newLine();

        //ESBIRROS
        writeMinionsRival(combat, vampire, bw);

        //CANTIDAD ORO
        bw.write("CANTIDAD_ORO: ");
        bw.write(String.valueOf(vampire.getGold()));
        bw.newLine();

        //CANTIDAD VIDA
        bw.write("CANTIDAD_VIDA: ");
        bw.write(String.valueOf(vampire.getHp()));
        bw.newLine();

        //PODER
        bw.write("CANTIDAD_PODER: ");
        bw.write(String.valueOf(vampire.getPower()));
        bw.newLine();

        //DEBILIDADES
        bw.write("NUMERO_DEBILIDADES: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getRival().getCharacter().getWeaknesses().size()); j++) {
            Weakness weakness = vampire.getWeaknesses().get(j);
            bw.write("NOMBRE_DEBILIDAD: ");
            bw.write(weakness.getName());
            bw.newLine();

            bw.write("VALOR_DEBILIDAD: ");
            bw.write(String.valueOf(weakness.getValue()));
            bw.newLine();
        }
        bw.newLine();

        //FORTALEZAS
        bw.write("NUMERO_FORTALEZAS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getStrengths().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getRival().getCharacter().getStrengths().size()); j++) {
            Strength strength = vampire.getStrengths().get(j);
            bw.write("NOMBRE_FORTALEZA: ");
            bw.write(strength.getName());
            bw.newLine();

            bw.write("VALOR_FORTALEZA: ");
            bw.write(String.valueOf(strength.getValue()));
            bw.newLine();
        }
        bw.newLine();

        bw.write("FIN_USUARIO");
        bw.newLine();
    }

    private void writeWerewolfRival(Combat combat, BufferedWriter bw) throws IOException {

        Werewolf werewolf = (Werewolf) combat.getRival().getCharacter();

        //TIPO PERSONAJE
        bw.write("TIPO_PERSONAJE: ");
        bw.write(combat.getRival().getCharacter().getType());
        bw.newLine();

        //NOMBRE PERSONAJE
        bw.write("NOMBRE_PERSONAJE: ");
        bw.write(combat.getRival().getCharacter().getName());
        bw.newLine();

        //NOMBRE HABILIDAD
        bw.write("NOMBRE_HABILIDAD: ");
        bw.write(combat.getRival().getCharacter().getAbility().getName());
        bw.newLine();

        //PUNTOS DE SANGRE
        bw.write("RABIA: ");
        bw.write("0");
        bw.newLine();

        //NUMERO ARMAS
        bw.write("NUMERO_ARMAS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getWeapons().size()));
        bw.newLine();

        for (int variableWeapon = 0; variableWeapon < (combat.getRival().getCharacter().getWeapons().size()); variableWeapon++) {
            Weapon weapon = combat.getRival().getCharacter().getWeapons().get(variableWeapon);
            bw.write("NOMBRE_ARMA: ");
            bw.write(weapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA: ");
            bw.write(String.valueOf(weapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA: ");
            bw.write(String.valueOf(weapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (weapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

        }
        bw.newLine();

        //NUMERO DE ARMAS ACTIVAS
        bw.write("NUMERO_ARMAS_ACTIVAS: ");
        bw.write(combat.getRival().getCharacter().getActiveWeapons().size());
        bw.newLine();
        for (int varActiveWeapon = 0; varActiveWeapon < (combat.getRival().getCharacter().getActiveWeapons().size()); varActiveWeapon++) {
            Weapon activeWeapon = (Weapon) werewolf.getActiveWeapons().get(varActiveWeapon);

            bw.write("NOMBRE_ARMAS_ACTIVAS: ");
            bw.write(activeWeapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (activeWeapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();
        }
        bw.newLine();

        //ARMADURAS
        //NUMERO DE ARMADURAS
        bw.write("NUMERO_ARMADURAS: ");
        bw.write(combat.getRival().getCharacter().getArmors().size());
        bw.newLine();
        for (int j = 0; j < (combat.getRival().getCharacter().getArmors().size()); j++) {
            Armor armor = (Armor) werewolf.getArmors().get(j);
            bw.write("NOMBRE_ARMADURA: ");
            bw.write(armor.getName());
            bw.newLine();

            bw.write("DEFENSA_ARMADURA: ");
            bw.write(String.valueOf(armor.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE_ARMADURA: ");
            bw.write(String.valueOf(armor.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();

        //ARMADURA ACTIVA / EQUIPADA
        bw.write("NOMBRE_ARMADURA_ACTIVA: ");
        bw.write(combat.getRival().getCharacter().getActiveArmor().getName());
        bw.newLine();

        //DEFENSA ARMADURA ACTIVA / EQUIPADA
        bw.write("DEFENSA_ARMADURA_ACTIVA: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getActiveArmor().getDefenseModifier()));
        bw.newLine();

        //ATAQUE ARMADURA ACTIVA / EQUIPADA
        bw.write("ATAQUE_ARMADURA_ACTIVA: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getActiveArmor().getAttackModifier()));
        bw.newLine();

        //CANTIDAD ORO
        bw.write("ORO: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getGold()));
        bw.newLine();

        //CANTIDAD HP
        bw.write("HP: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getHp()));
        bw.newLine();

        //CANTIDAD PODER
        bw.write("PODER: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getPower()));
        bw.newLine();

        //DEBLIDADES
        //NUMERO DE DEBLIDADES
        bw.write("NUMERO_DEBILIDADES: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getRival().getCharacter().getArmors().size()); j++) {
            Weakness weakness = werewolf.getWeaknesses().get(j);
            bw.write("NOMBRE_DEBILIADAD: ");
            bw.write(weakness.getName());
            bw.newLine();

            bw.write("VALOR_DEBILIADAD: ");
            bw.write(String.valueOf(weakness.getValue()));
            bw.newLine();
        }
        bw.newLine();

        //FORTALEZAS
        //NUMERO DE FORTALEZAS
        bw.write("NUMERO_FORTALEZAS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getStrengths().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getRival().getCharacter().getArmors().size()); j++) {
            Strength strength = werewolf.getStrengths().get(j);
            bw.write("NOMBRE_FORTALEZA: ");
            bw.write(strength.getName());
            bw.newLine();

            bw.write("VALOR_FORTALEZA: ");
            bw.write(String.valueOf(strength.getValue()));
            bw.newLine();
        }
        bw.newLine();

        //ESBIRROS
        //NUMERO DE ESBIRROS
        bw.write("NUMERO_ESBIRROS: ");
        bw.write(combat.getRival().getCharacter().getMinions().size());
        bw.newLine();

        //ESBIRROS
        writeMinionsRival(combat, werewolf, bw);

        bw.write("FIN_USUARIO");
        bw.newLine();
    }

    private void writeHunterRival(Combat combat, BufferedWriter bw) throws IOException {

        Hunter hunter = (Hunter) combat.getRival().getCharacter();

        //TIPO PERSONAJE
        bw.write("TIPO_PERSONAJE: ");
        bw.write(combat.getRival().getCharacter().getType());
        bw.newLine();
        //NOMBRE PERSONAJE
        bw.write("NOMBRE_PERSONAJE: ");
        bw.write(combat.getRival().getCharacter().getName());
        bw.newLine();

        //VOLUNTAD CAZADOR
        bw.write("VOLUNTAD: ");
        bw.write("0");
        bw.newLine();

        //NOMBRE HABILDIAD
        bw.write("NOMBRE_HABILIDAD: ");
        bw.write(hunter.getName());
        bw.newLine();

        //ATAQUE HABILIDAD
        bw.write("ATAQUE_HABILIDAD: ");
        bw.write(String.valueOf(hunter.getAbility().getAttack()));
        bw.newLine();

        //DEBILIDAD HABILIDAD
        bw.write("DEFENSA_HABILIDAD: ");
        bw.write(String.valueOf(hunter.getAbility().getDefense()));
        bw.newLine();

        //EDAD CAZADOR
        bw.write("EDAD_CAZADOR: ");
        bw.write(String.valueOf(hunter.getWillpower()));  //LA EDAD ES LA VOLUNTAD DEL CAZADOR
        bw.newLine();

        //ARMAS
        //NUMERO ARMAS
        bw.write("NUMERO_ARMAS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getWeapons().size()));
        bw.newLine();

        for (int variableWeapon = 0; variableWeapon < (combat.getRival().getCharacter().getWeapons().size()); variableWeapon++) {
            Weapon weapon = combat.getRival().getCharacter().getWeapons().get(variableWeapon);
            bw.write("NOMBRE_ARMA: ");
            bw.write(weapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA: ");
            bw.write(String.valueOf(weapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA: ");
            bw.write(String.valueOf(weapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (weapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

        }
        bw.newLine();

        //ARMAS ACTIVAS
        //NUMERO DE ARMAS ACTIVAS
        bw.write("NUMERO_ARMAS_ACTIVAS: ");
        bw.write(combat.getRival().getCharacter().getActiveWeapons().size());
        bw.newLine();
        for (int varActiveWeapon = 0; varActiveWeapon < (combat.getRival().getCharacter().getActiveWeapons().size()); varActiveWeapon++) {
            Weapon activeWeapon = (Weapon) hunter.getActiveWeapons().get(varActiveWeapon);

            bw.write("NOMBRE_ARMAS_ACTIVAS: ");
            bw.write(activeWeapon.getName());
            bw.newLine();

            bw.write("ATAQUE_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA_ARMA_ACTIVAS: ");
            bw.write(String.valueOf(activeWeapon.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA: ");
            if (activeWeapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();
        }
        bw.newLine();

        //ARMADURAS
        //NUMERO DE ARMADURAS
        bw.write("NUMERO_ARMADURAS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getArmors().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getRival().getCharacter().getArmors().size()); j++) {
            Armor armor = hunter.getArmors().get(j);
            bw.write("NOMBRE_ARMADURA: ");
            bw.write(armor.getName());
            bw.newLine();

            bw.write("DEFENSA_ARMADURA: ");
            bw.write(String.valueOf(armor.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE_ARMADURA: ");
            bw.write(String.valueOf(armor.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();

        //ARMADURAS EQUIPADA
        bw.write("NOMBRE_ARMADURA_ACTIVA: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getActiveArmor().getName()));
        bw.newLine();

        bw.write("DEFENSA_ARMADURA_ACTIVA: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getActiveArmor().getDefenseModifier()));
        bw.newLine();

        bw.write("ATAQUE_ARMADURA_ACTIVA: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getActiveArmor().getAttackModifier()));
        bw.newLine();

        bw.newLine();

        //CANTIDAD ORO
        bw.write("ORO: ");
        bw.write(String.valueOf(hunter.getGold()));
        bw.newLine();

        //CANTIDAD VDA
        bw.write("VIDA: ");
        bw.write(String.valueOf(hunter.getHp()));
        bw.newLine();

        //PODER
        bw.write("PODER: ");
        bw.write(String.valueOf(hunter.getPower()));
        bw.newLine();

        //FORTALEZAS
        //NUMERO DE FORTALEZAS
        bw.write("NUMERO_FORTALEZAS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getRival().getCharacter().getArmors().size()); j++) {
            Strength strength = hunter.getStrengths().get(j);
            bw.write("NOMBRE_FORTALEZA: ");
            bw.write(strength.getName());
            bw.newLine();

            bw.write("VALOR_FORTALEZA: ");
            bw.write(String.valueOf(strength.getValue()));
            bw.newLine();
        }
        bw.newLine();

        //DEBILIDADES
        //NUMERO DE DEBILIDADES
        bw.write("NUMERO_DEBILIDADES: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (combat.getRival().getCharacter().getArmors().size()); j++) {
            Weakness weakness = hunter.getWeaknesses().get(j);
            bw.write("NOMBRE_DEBILIDAD: ");
            bw.write(weakness.getName());
            bw.newLine();

            bw.write("VALOR_DEBILIDAD: ");
            bw.write(String.valueOf(weakness.getValue()));
            bw.newLine();
        }
        bw.newLine();



        //ESBIRROS
        //NUMERO DE ESBIRROS
        bw.write("NUMERO_ESBIRROS: ");
        bw.write(String.valueOf(combat.getRival().getCharacter().getMinions().size()));
        bw.newLine();

        writeMinionsRival(combat, hunter, bw);

        bw.write("FIN_USUARIO");
        bw.newLine();
    }

    private void writeMinionsRival(Combat combat, Character character, BufferedWriter bw) throws IOException {
        for (int j = 0; j < (combat.getRival().getCharacter().getMinions().size()); j++) {
            if (character.getMinions().get(j).getType().equals("HUMANO")) {
                Human human = (Human) character.getMinions().get(j);
                //NUMERO DE ESBIRROS
                bw.write("TIPO_ESBIRRO: ");

                bw.write(human.getType());
                bw.newLine();

                //NOMBRE DE ESBIRROS
                bw.write("NOMBRE_ESBIRRO: ");
                bw.write(human.getName());
                bw.newLine();

                //VIDA DE ESBIRROS
                bw.write("VIDA_ESBIRRO: ");
                bw.write(String.valueOf(human.getHp()));
                bw.newLine();

                //LEALTAD ESBIRRO HUMANO
                bw.write("LELTAD: ");
                if (human.getLoyalty() == Human.Loyalty.ALTA) {
                    bw.write("ALTA");
                } else if (human.getLoyalty() == Human.Loyalty.MEDIA) {
                    bw.write("MEDIA");
                } else if (human.getLoyalty() == Human.Loyalty.BAJA) {
                    bw.write("BAJA");
                }
                bw.newLine();

            } else if (character.getMinions().get(j).getType().equals("GHOUL")) {
                Ghoul ghoul = (Ghoul) character.getMinions().get(j);
                //NUMERO DE ESBIRRO
                bw.write("TIPO_ESBIRRO: ");
                bw.write(ghoul.getType());
                bw.newLine();

                //NOMBRE DE ESBIRRO
                bw.write("NOMBRE_ESBIRRO: ");
                bw.write(ghoul.getName());
                bw.newLine();

                //VIDA DE ESBIRRO
                bw.write("VIDA_ESBIRRO: ");
                bw.write(String.valueOf(ghoul.getHp()));
                bw.newLine();

                //DEPENDENCIA ESBIRRO
                bw.write("DEPENDENCIA: ");
                bw.write(String.valueOf(ghoul.getDependency()));
                bw.newLine();

            } else if (character.getMinions().get(j).getType().equals("DEMONIO")) {
                Demon demon = (Demon) character.getMinions().get(j);
                //TIPO DE ESBIRRO
                bw.write("TIPO_ESBIRRO: ");

                bw.write(demon.getType());
                bw.newLine();

                //NOMBRE DE ESBIRRO
                bw.write("NOMBRE_ESBIRRO: ");
                bw.write(demon.getName());
                bw.newLine();

                //VIDA ESBIRRO
                bw.write("VIDA_ESBIRRO: ");
                bw.write(String.valueOf(demon.getHp()));
                bw.newLine();

                //DESCRIPCION / PACTO
                bw.write("DESCRIPCION: ");
                bw.write(demon.getDescription());
                bw.newLine();

                //ESBIRROS EXTRA
                //NUMERO DE ESBIRROS EXTRA
                bw.write("NUMERO_ESBIRROS_EXTRA: ");
                bw.write(String.valueOf(combat.getRival().getCharacter().getMinions().size()));
                bw.newLine();
                writeMinionsRival(combat, character, bw);
            }
        }
    }

}
