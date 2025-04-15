package System;

import Entities.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Entities.Character;

public class UserFileWriter {

    private static final String USER_FILE_PATH = "Fase-3-MP/src/Files/UserRegister.txt";

    /**A continuación se definen las operaciones de escrituras**/
    public void userRegister(Client client) {
        try {
            File file = new File(USER_FILE_PATH);
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
            bw.write("REGISTRO ");
            bw.write(client.getRegister());
            bw.newLine();
            bw.write("TIPO DE PERSONAJE ");
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
            File file = new File(USER_FILE_PATH);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            //recorre la lista de usuarios
            for (int i = 0; i < clientArrayList.size(); i++) {
                bw.write("========== USUARIO ==========");
                bw.newLine();
                bw.write("NOMBRE ");
                bw.write(clientArrayList.get(i).getName());
                bw.newLine();
                bw.write("NICK ");
                bw.write(clientArrayList.get(i).getNick());
                bw.newLine();
                bw.write("PASSWORD ");
                bw.write(clientArrayList.get(i).getPassword());
                bw.newLine();
                bw.write("REGISTRO ");
                bw.write(clientArrayList.get(i).getRegister());
                bw.newLine();
                if (clientArrayList.get(i).getCharacter() == null) {
                    bw.write("TIPO PERSONAJE  null");
                    bw.newLine();
                    bw.write("========== FIN USUARIO ==========");
                    bw.newLine();
                } else {
                    String characterType = clientArrayList.get(i).getCharacter().getType();
                    switch (characterType) {
                       case "VAMPIRO" -> vampireWriter(clientArrayList, i, bw); //escribimos y guardamos los atributos de los vampiros
                       case "LICANTROPO" -> licantropWriter(clientArrayList, i, bw); //idem.
                       case "CAZADOR" -> hunterWriter(clientArrayList, i, bw); //idem.

                    }
                }
            }
            bw.close();
        } catch (Exception exception) {
            mainSystem system = new mainSystem();
            system.selector();
            exception.printStackTrace();
        }
    }

    private void vampireWriter(ArrayList<Client> clientArrayList, int i, BufferedWriter bw) throws IOException {

        Vampire vampire = (Vampire) clientArrayList.get(i).getCharacter();
        Discipline discipline = (Discipline) vampire.getAbility();
        bw.write("TIPO DE PERSONAJE ");
        bw.write(clientArrayList.get(i).getCharacter().getType());
        bw.newLine();
        bw.write("NOMBRE DE PERSONAJE ");
        bw.write(clientArrayList.get(i).getCharacter().getName());
        bw.newLine();
        bw.write("SANGRE ");
        bw.write(((Vampire) clientArrayList.get(i).getCharacter()).getBlood());
        bw.newLine();
        bw.write("NOMBRE DE LA HABILIDAD ");
        bw.write(discipline.getName());
        bw.newLine();
        bw.write("VALOR DE ATAQUE ");
        bw.write(String.valueOf(discipline.getAttack()));
        bw.newLine();
        bw.write("VALOR DE DEFENSA ");
        bw.write(String.valueOf(discipline.getDefense()));
        bw.newLine();
        bw.write("COSTE DE LA HABILIDAD ");
        bw.write(String.valueOf(discipline.getCost()));
        bw.newLine();
        bw.write("NUMERO DE ARMAS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getWeapons().size()));
        bw.newLine();
        for (int weaponVar = 0; weaponVar < (clientArrayList.get(i).getCharacter().getWeapons().size()); weaponVar++) {
            Weapon weapon = vampire.getWeapons().get(weaponVar);
            bw.write("NOMBRE DEL ARMA ");
            bw.write(weapon.getName());
            bw.newLine();
            bw.write("ATAQUE DEL ARMA ");
            bw.write(String.valueOf(weapon.getAttackModifier()));
            bw.newLine();
            bw.write("DEFENSA DEL ARMA ");
            bw.write(String.valueOf(weapon.getDefenseModifier()));
            bw.newLine();
            bw.write("EMPUÑADURA ");
            if (weapon.isSingleHand()) {
                bw.write("true"); //True 1 mano
            } else {
                bw.write("false"); //False 2 manos
            }
            bw.newLine();
        }
        bw.newLine();
        bw.write("NUMERO DE ARMAS ACTIVAS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getActiveWeapons().size()));
        bw.newLine();
        for (int activeWeaponVar = 0; activeWeaponVar < (clientArrayList.get(i).getCharacter().getActiveWeapons().size()); activeWeaponVar++) {
            Weapon activeWeapon = vampire.getActiveWeapons().get(activeWeaponVar);
            bw.write("NOMBRE DE LAS ARMAS ACTIVAS ");
            bw.write(activeWeapon.getName());
            bw.newLine();
            bw.write("ATAQUE DE LAS ARMA ACTIVAS ");
            bw.write(String.valueOf(activeWeapon.getAttackModifier()));
            bw.newLine();
            bw.write("DEFENSA DEL ARMA ACTIVAS ");
            bw.write(String.valueOf(activeWeapon.getDefenseModifier()));
            bw.newLine();
            bw.write("EMPUÑADURA ");
            if (activeWeapon.isSingleHand()) {
                bw.write("true"); //True 1 mano
            } else {
                bw.write("false"); //False 2 manos
            }
            bw.newLine();
        }
        bw.newLine();
        bw.write("NUMERO DE ARMADURAS  ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getArmors().size()));
        bw.newLine();
        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getArmors().size()); j++) {
            Armor armours = (Armor) vampire.getArmors().get(j);
            bw.write("NOMBRE DE LA ARMADURA ");
            bw.write(armours.getName());
            bw.newLine();
            bw.write("DEFENSA DE LA ARMADURA ");
            bw.write(String.valueOf(armours.getDefenseModifier()));
            bw.newLine();
            bw.write("ATAQUE DE LA ARMADURA ");
            bw.write(String.valueOf(armours.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();
        bw.write("NOMBRE DE LA ARMADURA ACTIVA ");
        bw.write(vampire.getActiveArmor().getName());
        bw.newLine();
        bw.write("DEFENSA DE LA ARMADURA ACTIVA ");
        bw.write(String.valueOf(vampire.getActiveArmor().getDefenseModifier()));
        bw.newLine();
        bw.write("ATAQUE DE LA ARMADURA ACTIVA ");
        bw.write(String.valueOf(vampire.getActiveArmor().getAttackModifier()));
        bw.newLine();
        bw.newLine();
        bw.write("ORO DISPONIBLE ");
        bw.write(String.valueOf(vampire.getGold()));
        bw.newLine();
        bw.write("EDAD DEL VAMPIRO ");
        bw.write(String.valueOf(vampire.getAge()));
        bw.newLine();
        bw.write("HP ");
        bw.write(String.valueOf(vampire.getHp()));
        bw.newLine();
        bw.write("PODER ");
        bw.write(String.valueOf(vampire.getPower()));
        bw.newLine();
        bw.write("NUMERO DE FORTALEZAS ");
        bw.write(String.valueOf(vampire.getStrengths().size()));
        bw.newLine();


        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getStrengths().size()); j++) {
            Strength strength = clientArrayList.get(i).getCharacter().getStrengths().get(j);
            bw.write("  -NOMBRE DE LA FORTALEZA ");
            bw.write(strength.getName());
            bw.newLine();
            bw.write("  -VALOR DE LA FORTALEZA ");
            bw.write(String.valueOf(strength.getValue()));
            bw.newLine();
        }


        bw.write("NUMERO DE DEBILIDADES ");
        bw.write(String.valueOf(vampire.getWeaknesses().size()));
        bw.newLine();


        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getWeaknesses().size()); j++) {
            Weakness weakness = clientArrayList.get(i).getCharacter().getWeaknesses().get(j);
            bw.write("NOMBRE DE LA DEBILIDAD ");
            bw.write(weakness.getName());
            bw.newLine();
            bw.write("VALOR DE LA DEBILIDAD ");
            bw.write(String.valueOf(weakness.getValue()));
            bw.newLine();
        }
        bw.write("NUMERO DE ESBIRROS  ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getMinions().size()));
        bw.newLine();
        minionsWriter(clientArrayList, i, vampire, bw);
        bw.write("========== FIN USUARIO ==========");
        bw.newLine();
    }

    private void licantropWriter(ArrayList<Client> clientArrayList, int i, BufferedWriter bw) throws IOException {

        Werewolf licantrop= (Werewolf) clientArrayList.get(i).getCharacter();
        Don don = (Don) licantrop.getAbility();
        //TIPO PERSONAJE
        bw.write("------ TIPO DE PERSONAJE ------");
        bw.write(clientArrayList.get(i).getCharacter().getType());
        bw.newLine();
        //NOMBRE PERSONAJE
        bw.write("NOMBRE DEL PERSONAJE ");
        bw.write(clientArrayList.get(i).getCharacter().getName());
        bw.newLine();
        //NOMBRE HABILIDAD
        bw.write("NOMBRE DE LA HABILIDAD ");
        bw.write(clientArrayList.get(i).getCharacter().getAbility().getName());
        bw.newLine();
        //PUNTOS DE RABIA
        bw.write("RABIA ");
        //bw.write("0");
        bw.newLine();
        //ARMAS
        bw.write("NUMERO DE ARMAS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getWeapons().size()));
        bw.newLine();


        for (int weaponVar = 0; weaponVar < (clientArrayList.get(i).getCharacter().getWeapons().size()); weaponVar++) {
            Weapon weapon = licantrop.getWeapons().get(i);
            bw.write("NOMBRE DEL ARMA ");
            bw.write(weapon.getName());
            bw.newLine();
            bw.write("ATAQUE DEL ARMA ");
            bw.write(String.valueOf(weapon.getAttackModifier()));
            bw.newLine();
            bw.write("DEFENSA DEL ARMA ");
            bw.write(String.valueOf(weapon.getDefenseModifier()));
            bw.newLine();
            bw.write("EMPUÑADURA ");
            if (weapon.isSingleHand()) {
                bw.write("true"); //True una mano
            } else {
                bw.write("false"); //False dos manos
            }
            bw.newLine();
        }
        bw.newLine();
        //NUMERO DE ARMAS ACTIVAS
        bw.write("NUMERO DE ARMAS ACTIVAS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getActiveWeapons().size()));
        bw.newLine();

        for (int activeWeaponVar = 0; activeWeaponVar < (clientArrayList.get(i).getCharacter().getActiveWeapons().size()); activeWeaponVar++) {
            Weapon activeWeapon = licantrop.getActiveWeapons().get(activeWeaponVar);
            bw.write("NOMBRE DE LAS ARMAS ACTIVAS ");
            bw.write(activeWeapon.getName());
            bw.newLine();
            bw.write("ATAQUE DE LAS ARMA ACTIVAS ");
            bw.write(String.valueOf(activeWeapon.getAttackModifier()));
            bw.newLine();
            bw.write("DEFENSA DE LAS ARMAS ACTIVAS ");
            bw.write(String.valueOf(activeWeapon.getDefenseModifier()));
            bw.newLine();
            bw.write("EMPUÑADURA ");
            if (activeWeapon.isSingleHand()) {
                bw.write("true");//True una mano
            } else {
                bw.write("false");//False dos manos
            }
            bw.newLine();
        }
        bw.newLine();
        //ARMADURAS
        bw.write("NUMERO DE ARMADURAS  ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getWeapons().size()));
        bw.newLine();
        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getArmors().size()); j++) {
            Armor armours = (Armor) licantrop.getArmors().get(j);
            bw.write("NOMBRE DE LA ARMADURA  ");
            bw.write(armours.getName());
            bw.newLine();
            bw.write("DEFENSA DE LA ARMADURA  ");
            bw.write(String.valueOf(armours.getDefenseModifier()));
            bw.newLine();
            bw.write("ATAQUE DE LA ARMADURA  ");
            bw.write(String.valueOf(armours.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();
        //ARMADURA ACTIVA / EQUIPADA
        bw.write("NOMBRE DE LA RMADURA ACTIVA ");
        bw.write(clientArrayList.get(i).getCharacter().getActiveArmor().getName());
        bw.newLine();
        //DEFENSA ARMADURA ACTIVA / EQUIPADA
        bw.write("DEFENSA DE LA ARMADURA ACTIVA ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getActiveArmor().getDefenseModifier()));
        bw.newLine();
        //ATAQUE ARMADURA ACTIVA / EQUIPADA
        bw.write("ATAQUE DE LA ARMADURA ACTIVA ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getActiveArmor().getAttackModifier()));
        bw.newLine();
        //CANTIDAD ORO
        bw.write("ORO ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getGold()));
        bw.newLine();
        //CANTIDAD HP
        bw.write("HP ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getHp()));
        bw.newLine();
        //PODER
        bw.write("PODER ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getPower()));
        bw.newLine();
        //DEBLIDADES
        bw.write("NUMERO DE DEBILIDADES ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getArmors().size()); j++) {
            Weakness weakness = (Weakness) licantrop.getWeaknesses().get(j);
            bw.write("NOMBRE DE LA DEBILIADAD ");
            bw.write(weakness.getName());
            bw.newLine();
            bw.write("VALOR DE LA DEBILIADAD ");
            bw.write(String.valueOf(weakness.getValue()));
            bw.newLine();
        }
        bw.newLine();
        //FORTALEZAS
        bw.write("NUMERO DE FORTALEZAS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getStrengths().size()));
        bw.newLine();
        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getArmors().size()); j++) {
            Strength strength = (Strength) licantrop.getStrengths().get(j);
            bw.write("NOMBRE DE LA FORTALEZA ");
            bw.write(strength.getName());
            bw.newLine();
            bw.write("VALOR DE LA FORTALEZA");
            bw.write(String.valueOf(strength.getValue()));
            bw.newLine();
        }
        bw.newLine();
        //ESBIRROS
        bw.write("NUMERO DE ESBIRROS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getMinions().size()));
        bw.newLine();
       // minionsWriter(clientArrayList, i, licantrop, bw); Faltaría un mimions Writer
        bw.write("========== FIN USUARIO ==========");
        bw.newLine();
    }

    private void hunterWriter(ArrayList<Client> clientArrayList, int i, BufferedWriter bw) throws IOException {
        Hunter hunter = (Hunter) clientArrayList.get(i).getCharacter();
        Talent talent = (Talent) hunter.getAbility();
        //TIPO PERSONAJE
        bw.write("TIPO DE PERSONAJE ");
        bw.write(clientArrayList.get(i).getCharacter().getType());
        bw.newLine();
        //NOMBRE PERSONAJE
        bw.write("NOMBRE DE PERSONAJE ");
        bw.write(clientArrayList.get(i).getCharacter().getName());
        bw.newLine();
        //NOMBRE HABILDIAD
        bw.write("NOMBRE DE LA HABILIDAD ");
        bw.write(hunter.getAbility().getName());
        bw.newLine();
        //VOLUNTAD CAZADOR
        bw.write("VOLUNTAD ");
        bw.write("3");
        bw.newLine();
        //ATAQUE HABILIDAD
        bw.write("ATAQUE DE ABILIDAD ");
        bw.write(String.valueOf(hunter.getAbility().getAttack()));
        bw.newLine();
        //DEBILIDAD HABILIDAD
        bw.write("DEFENSA DE LA HABILIDAD ");
        bw.write(String.valueOf(hunter.getAbility().getDefense()));
        bw.newLine();
        //EDAD CAZADOR
        bw.write("EDAD DEL CAZADOR ");
        bw.write(String.valueOf(talent.getAge()));
        bw.newLine();
        //ARMAS
        bw.write("NUMERO DE ARMAS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getWeapons().size()));
        bw.newLine();


        for (int weaponVar = 0; weaponVar < (clientArrayList.get(i).getCharacter().getWeapons().size()); weaponVar++) {
            Weapon weapon = hunter.getWeapons().get(weaponVar);
            bw.write("NOMBRE DEL ARMA ");
            bw.write(weapon.getName());
            bw.newLine();
            bw.write("ATAQUE DEL ARMA ");
            bw.write(String.valueOf(weapon.getAttackModifier()));
            bw.newLine();
            bw.write("DEFENSA DEL ARMA ");
            bw.write(String.valueOf(weapon.getDefenseModifier()));
            bw.newLine();
            bw.write("EMPUÑADURA ");
            if (weapon.isSingleHand()) {
                bw.write("true");//True una mano
            } else {
                bw.write("false");//False dos manos
            }
            bw.newLine();
        }
        bw.newLine();
        //ARMAS ACTIVAS
        bw.write("NUMERO DE ARMAS ACTIVAS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getActiveWeapons().size()));
        bw.newLine();
        for (int activeWeaponVar = 0; activeWeaponVar < (clientArrayList.get(i).getCharacter().getActiveWeapons().size()); activeWeaponVar++) {
            Weapon activeWeapon = hunter.getActiveWeapons().get(activeWeaponVar);


            bw.write("NOMBRE DE ARMAS ACTIVAS ");
            bw.write(activeWeapon.getName());
            bw.newLine();
            bw.write("ATAQUE DEL ARMA ACTIVAS ");
            bw.write(String.valueOf(activeWeapon.getAttackModifier()));
            bw.newLine();
            bw.write("DEFENSA DEL ARMA ACTIVAS ");
            bw.write(String.valueOf(activeWeapon.getDefenseModifier()));
            bw.newLine();
            bw.write("EMPUÑADURA ");
            if (activeWeapon.isSingleHand()) {
                bw.write("true"); //True 1 mano
            } else {
                bw.write("false"); //False 2 manos
            }
            bw.newLine();
        }
        bw.newLine();
        //ARMADURAS
        bw.write("NUMERO DE ARMADURAS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getArmors().size()));
        bw.newLine();
        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getArmors().size()); j++) {
            Armor armours = hunter.getArmors().get(j);
            bw.write("NOMBRE DE LA ARMADURA ");
            bw.write(armours.getName());
            bw.newLine();
            bw.write("DEFENSA DE LA ARMADURA ");
            bw.write(String.valueOf(armours.getDefenseModifier()));
            bw.newLine();
            bw.write("ATAQUE DE LA ARMADURA ");
            bw.write(String.valueOf(armours.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();
        //ARMADURAS EQUIPADA
        bw.write("NOMBRE DE LA ARMADURA ACTIVA ");
        bw.write(clientArrayList.get(i).getCharacter().getActiveArmor().getName());
        bw.newLine();
        bw.write("DEFENSA DE LA ARMADURA ACTIVA ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getActiveArmor().getDefenseModifier()));
        bw.newLine();
        bw.write("ATAQUE DE LA ARMADURA_ACTIVA ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getActiveArmor().getAttackModifier()));
        bw.newLine();
        bw.newLine();
        //CANTIDAD ORO
        bw.write("ORO ");
        bw.write(String.valueOf(hunter.getGold()));
        bw.newLine();
        //CANTIDAD DE VIDA
        bw.write("VIDA ");
        bw.write(String.valueOf(hunter.getHp()));
        bw.newLine();
        //PODER
        bw.write("PODER ");
        bw.write(String.valueOf(hunter.getPower()));
        bw.newLine();
        //FORTALEZAS
        bw.write("NUMERO DE FORTALEZAS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getStrengths().size()));
        bw.newLine();
        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getArmors().size()); j++) {
            Strength strength = hunter.getStrengths().get(j);
            bw.write("NOMBRE DE LA FORTALEZA ");
            bw.write(strength.getName());
            bw.newLine();
            bw.write("VALOR DE LA FORTALEZA ");
            bw.write(String.valueOf(strength.getValue()));
            bw.newLine();
        }
        bw.newLine();
        //DEBILIDADES
        bw.write("NUMERO DE DEBILIDADES ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getArmors().size()); j++) {
            Weakness weakness = hunter.getWeaknesses().get(j);
            bw.write("NOMBRE DE LA DEBILIDAD ");
            bw.write(weakness.getName());
            bw.newLine();
            bw.write("VALOR DE DEBILIDAD ");
            bw.write(String.valueOf(weakness.getValue()));
            bw.newLine();
        }
        bw.newLine();
        //ESBIRROS
        bw.write("NUMERO DE ESBIRROS ");
        bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getMinions().size()));
        bw.newLine();
        clientArrayList.get(i).getCharacter().getType();
        minionsWriter(clientArrayList, i, hunter, bw);


        bw.write("========== FIN USUARIO ==========");
        bw.newLine();
        bw.close();
    }

    private void minionsWriter(ArrayList<Client> clientArrayList, int i, Character normalPerson, BufferedWriter bw) throws IOException {
        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getMinions().size()); j++) {
            switch (normalPerson.getMinions().get(j).getType()) {
                case "HUMANO" -> {
                    Human human = (Human) normalPerson.getMinions().get(j);
                    //NUMERO DE ESBIRROS
                    bw.write("TIPO DE ESBIRRO ");
                    bw.write(human.getType());
                    bw.newLine();
                    //NOMBRE DE ESBIRROS
                    bw.write("NOMBRE DEL ESBIRRO ");
                    bw.write(human.getName());
                    bw.newLine();
                    //VIDA DE ESBIRROS
                    bw.write("VIDA DEL ESBIRRO ");
                    bw.write(String.valueOf(human.getHp()));
                    bw.newLine();
                    //LEALTAD ESBIRRO HUMANO
                    bw.write("LELTAD ");
                    if (human.getLoyalty() == Human.Loyalty.ALTA) {
                        bw.write("ALTA");
                    } else if (human.getLoyalty() == Human.Loyalty.MEDIA) {
                        bw.write("MEDIA");
                    } else if (human.getLoyalty() == Human.Loyalty.BAJA) {
                        bw.write("BAJA");
                    }
                    bw.newLine();
                }
                case "GHOUL" -> {
                    Ghoul ghoul = (Ghoul) normalPerson.getMinions().get(j);
                    //NUMERO DE ESBIRRO
                    bw.write("TIPO DE ESBIRRO ");
                    bw.write(ghoul.getType());
                    bw.newLine();
                    //NOMBRE DE ESBIRRO
                    bw.write("NOMBRE DEL ESBIRRO ");
                    bw.write(ghoul.getName());
                    bw.newLine();
                    //VIDA DE ESBIRRO
                    bw.write("VIDA DEL ESBIRRO ");
                    bw.write(String.valueOf(ghoul.getHp()));
                    bw.newLine();
                    //DEPENDENCIA ESBIRRO
                    bw.write("DEPENDENCIA ");
                    bw.write(String.valueOf(ghoul.getDependency()));
                    bw.newLine();
                }
                case "DEMONIO" -> {
                    Demon demon = (Demon) normalPerson.getMinions().get(j);
                    //TIPO DE ESBIRRO
                    bw.write("TIPO DE  ESBIRRO ");
                    bw.write(demon.getType());
                    bw.newLine();
                    //NOMBRE DE ESBIRRO
                    bw.write("NOMBRE ESBIRRO ");
                    bw.write(demon.getName());
                    bw.newLine();
                    //VIDA ESBIRRO
                    bw.write("VIDA DEL ESBIRRO ");
                    bw.write(String.valueOf(demon.getHp()));
                    bw.newLine();
                    //DESCRIPCION / PACTO
                    bw.write("DESCRIPCION ");
                    bw.write(demon.getDescription());
                    bw.newLine();
                    //ESBIRROS EXTRA
                    bw.write("NUMERO DE ESBIRROS EXTRA ");
                    bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getMinions().size()));
                    bw.newLine();
                    minionsWriter(clientArrayList, i, normalPerson, bw);
                }
            }
        }
    }
} //FIN