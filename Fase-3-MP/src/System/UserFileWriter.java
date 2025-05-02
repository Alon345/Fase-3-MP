package System;

import Entities.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            bw.write("TIPO-PERSONAJE null");
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

            for (Client client : clientArrayList) {

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
                if (client.getCharacter() == null) {
                    bw.write("TIPO-PERSONAJE null");
                    bw.newLine();
                    bw.write("========== FIN USUARIO ==========");
                    bw.newLine();
                } else {
                    String characterType = client.getCharacter().getType();
                    switch (characterType) {
                       case "VAMPIRO" -> vampireWriter(clientArrayList, clientArrayList.indexOf(client), bw); //escribimos y guardamos los atributos de los vampiros
                       case "LICANTROPO" -> werewolfWriter(clientArrayList, clientArrayList.indexOf(client), bw); //idem.
                       case "CAZADOR" -> hunterWriter(clientArrayList, clientArrayList.indexOf(client), bw); //idem.
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

    public void vampireWriter(ArrayList<Client> listaClient, int i, BufferedWriter bw) throws IOException {

        Vampire vampiro = (Vampire) listaClient.get(i).getCharacter();
        Discipline disciplina = (Discipline) vampiro.getAbility();

        bw.write("TIPO-PERSONAJE ");
        bw.write(listaClient.get(i).getCharacter().getType());
        bw.newLine();
        bw.write("NOMBRE-PERSONAJE ");
        bw.write(listaClient.get(i).getCharacter().getName());
        bw.newLine();
        bw.write("SANGRE ");
        bw.write(String.valueOf(vampiro.getBlood()));
        bw.newLine();
        bw.write("NOMBRE-HABILIDAD ");
        bw.write(disciplina.getName());
        bw.newLine();

        bw.write("VALOR-ATAQUE ");
        bw.write(String.valueOf(disciplina.getAttack()));
        bw.newLine();

        bw.write("VALOR-DEFENSA ");
        bw.write(String.valueOf(disciplina.getDefense()));
        bw.newLine();

        bw.write("COSTE-HABILIDAD ");
        bw.write(String.valueOf(disciplina.getCost()));
        bw.newLine();

        bw.write("NUMERO-ARMA ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getWeapons().size()));
        bw.newLine();

        for (int variableWeapon = 0; variableWeapon < (listaClient.get(i).getCharacter().getWeapons().size()); variableWeapon++) {
            Weapon Weapon = vampiro.getWeapons().get(variableWeapon);
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

        bw.write("NUMERO-ARMA-ACTIVAS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getActiveWeapons().size()));
        bw.newLine();
        for (int variableWeaponActiva = 0; variableWeaponActiva < (listaClient.get(i).getCharacter().getActiveWeapons().size()); variableWeaponActiva++) {
            Weapon WeaponActiva = vampiro.getActiveWeapons().get(variableWeaponActiva);

            bw.write("NOMBRE-ARMA-ACTIVAS ");
            bw.write(WeaponActiva.getName());
            bw.newLine();

            bw.write("ATAQUE-ARMA-ACTIVAS ");
            bw.write(String.valueOf(WeaponActiva.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA-ARMA-ACTIVAS ");
            bw.write(String.valueOf(WeaponActiva.getDefenseModifier()));
            bw.newLine();

            bw.write("EMPUÑADURA ");
            if (WeaponActiva.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();
        }
        bw.newLine();
        
        bw.write("NUMERO-ARMADURAS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getArmors().size()));
        bw.newLine();
        for (int j = 0; j < (listaClient.get(i).getCharacter().getArmors().size()); j++) {
            Armor Weapondura = (Armor) vampiro.getArmors().get(j);
            bw.write("NOMBRE-ARMADURA ");
            bw.write(Weapondura.getName());
            bw.newLine();

            bw.write("DEFENSA-ARMADURA ");
            bw.write(String.valueOf(Weapondura.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE-ARMADURA ");
            bw.write(String.valueOf(Weapondura.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();

        bw.write("NOMBRE-ARMADURA-ACTIVA ");
        bw.write(vampiro.getActiveArmor().getName());
        bw.newLine();

        bw.write("DEFENSA-ARMADURA-ACTIVA ");
        bw.write(String.valueOf(vampiro.getActiveArmor().getDefenseModifier()));
        bw.newLine();

        bw.write("ATAQUE-ARMADURA-ACTIVA ");
        bw.write(String.valueOf(vampiro.getActiveArmor().getAttackModifier()));
        bw.newLine();

        bw.newLine();
        
        bw.write("ORO ");
        bw.write(String.valueOf(vampiro.getGold()));
        bw.newLine();

        bw.write("EDAD-VAMPIRO ");
        bw.write(String.valueOf(vampiro.getAge()));
        bw.newLine();

        bw.write("HP ");
        bw.write(String.valueOf(vampiro.getHp()));
        bw.newLine();

        bw.write("PODER ");
        bw.write(String.valueOf(vampiro.getPower()));
        bw.newLine();

        bw.write("NUMERO-FORTALEZAS ");
        bw.write(String.valueOf(vampiro.getStrengths().size()));
        bw.newLine();

        for (int j = 0; j < (listaClient.get(i).getCharacter().getStrengths().size()); j++) {
            Strength Strength = listaClient.get(i).getCharacter().getStrengths().get(j);
            bw.write("NOMBRE-FORTALEZA ");
            bw.write(Strength.getName());
            bw.newLine();

            bw.write("VALOR-FORTALEZA ");
            bw.write(String.valueOf(Strength.getValue()));
            bw.newLine();
        }

        bw.write("NUMERO-DEBILIDADES ");
        bw.write(String.valueOf(vampiro.getWeaknesses().size()));
        bw.newLine();

        for (int j = 0; j < (listaClient.get(i).getCharacter().getWeaknesses().size()); j++) {
            Weakness Weakness = listaClient.get(i).getCharacter().getWeaknesses().get(j);
            bw.write("NOMBRE-DEBILIDAD ");
            bw.write(Weakness.getName());
            bw.newLine();

            bw.write("VALOR-DEBILIDAD ");
            bw.write(String.valueOf(Weakness.getValue()));
            bw.newLine();
        }
        bw.write("NUMERO-ESBIRROS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getMinions().size()));
        bw.newLine();

        minionsWriter(listaClient, i, vampiro, bw);

        bw.write("========== FIN USUARIO ==========");
        bw.newLine();
    }
    
    public void werewolfWriter(ArrayList<Client> listaClient, int i, BufferedWriter bw) throws IOException {

        Werewolf Werewolf = (Werewolf) listaClient.get(i).getCharacter();
        Don don = (Don) Werewolf.getAbility();

        bw.write("TIPO-PERSONAJE ");
        bw.write(listaClient.get(i).getCharacter().getType());
        bw.newLine();
        bw.write("NOMBRE-PERSONAJE ");
        bw.write(listaClient.get(i).getCharacter().getName());
        bw.newLine();

        bw.write("NOMBRE-HABILIDAD ");
        bw.write(listaClient.get(i).getCharacter().getAbility().getName());
        bw.newLine();

        bw.write("RABIA ");
        bw.write("0");
        bw.newLine();
        bw.write("NUMERO-ARMAS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getWeapons().size()));
        bw.newLine();

        for (int variableWeapon = 0; variableWeapon < (listaClient.get(i).getCharacter().getWeapons().size()); variableWeapon++) {
            Weapon Weapon = Werewolf.getWeapons().get(i);
            bw.write("NOMBRE-ARMA ");
            bw.write(Weapon.getName());
            bw.newLine();

            bw.write("ATAQUE-ARMA ");
            bw.write(String.valueOf(Weapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA-ARMA ");
            bw.write(String.valueOf(Weapon.getDefenseModifier()));
            bw.newLine();

            bw.write("EMPUÑADURA ");
            if (Weapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

        }
        bw.newLine();

        bw.write("NUMERO-ARMAS-ACTIVAS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getActiveWeapons().size()));
        bw.newLine();

        for (int variableWeaponActiva = 0; variableWeaponActiva < (listaClient.get(i).getCharacter().getActiveWeapons().size()); variableWeaponActiva++) {
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
            bw.write("EMPUÑADURA ");
            if (WeaponActiva.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();
        }
        bw.newLine();

        bw.write("NUMERO-ARMADURAS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getArmors().size()));
        bw.newLine();
        for (int j = 0; j < (listaClient.get(i).getCharacter().getArmors().size()); j++) {
            Armor Weapondura = (Armor) Werewolf.getArmors().get(j);
            bw.write("NOMBRE-ARMADURA ");
            bw.write(Weapondura.getName());
            bw.newLine();

            bw.write("DEFENSA-ARMADURA ");
            bw.write(String.valueOf(Weapondura.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE-ARMADURA ");
            bw.write(String.valueOf(Weapondura.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();

        bw.write("NOMBRE-ARMADURA-ACTIVA ");
        bw.write(listaClient.get(i).getCharacter().getActiveArmor().getName());
        bw.newLine();
        bw.write("DEFENSA-ARMADURA-ACTIVA ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getActiveArmor().getDefenseModifier()));
        bw.newLine();
        bw.write("ATAQUE-ARMADURA-ACTIVA ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getActiveArmor().getAttackModifier()));
        bw.newLine();
        bw.write("ORO ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getGold()));
        bw.newLine();

        bw.write("HP ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getHp()));
        bw.newLine();

        bw.write("PODER ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getPower()));
        bw.newLine();

        bw.write("NUMERO-DEBILIDADES ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (listaClient.get(i).getCharacter().getArmors().size()); j++) {
            Weakness Weakness = (Weakness) Werewolf.getWeaknesses().get(j);
            bw.write("NOMBRE-DEBILIADAD ");
            bw.write(Weakness.getName());
            bw.newLine();

            bw.write("VALOR-DEBILIADAD ");
            bw.write(String.valueOf(Weakness.getValue()));
            bw.newLine();
        }
        bw.newLine();

        bw.write("NUMERO-FORTALEZAS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getStrengths().size()));
        bw.newLine();
        for (int j = 0; j < (listaClient.get(i).getCharacter().getArmors().size()); j++) {
            Strength Strength = (Strength) Werewolf.getStrengths().get(j);
            bw.write("NOMBRE-FORTALEZA ");
            bw.write(Strength.getName());
            bw.newLine();

            bw.write("VALOR-FORTALEZA ");
            bw.write(String.valueOf(Strength.getValue()));
            bw.newLine();
        }
        bw.newLine();

        bw.write("NUMERO-ESBIRROS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getMinions().size()));
        bw.newLine();

        minionsWriter(listaClient, i, Werewolf, bw);

        bw.write("========== FIN USUARIO ==========");
        bw.newLine();
    }

    public void hunterWriter(ArrayList<Client> listaClient, int i, BufferedWriter bw) throws IOException {

        Hunter cazador = (Hunter) listaClient.get(i).getCharacter();
        Talent talento = (Talent) cazador.getAbility();

        bw.write("TIPO-PERSONAJE ");
        bw.write(listaClient.get(i).getCharacter().getType());
        bw.newLine();
        bw.write("NOMBRE-PERSONAJE ");
        bw.write(listaClient.get(i).getCharacter().getName());
        bw.newLine();
        bw.write("NOMBRE-HABILIDAD ");
        bw.write(cazador.getAbility().getName());
        bw.newLine();
        bw.write("VOLUNTAD ");
        bw.write("3");
        bw.newLine();

        bw.write("ATAQUE-HABILIDAD ");
        bw.write(String.valueOf(cazador.getAbility().getAttack()));
        bw.newLine();
        bw.write("DEFENSA-HABILIDAD ");
        bw.write(String.valueOf(cazador.getAbility().getDefense()));
        bw.newLine();
        bw.write("EDAD-CAZADOR ");
        bw.write(String.valueOf(talento.getAge()));
        bw.newLine();

        bw.write("NUMERO-ARMA ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getWeapons().size()));
        bw.newLine();

        for (int variableWeapon = 0; variableWeapon < (listaClient.get(i).getCharacter().getWeapons().size()); variableWeapon++) {
            Weapon Weapon = cazador.getWeapons().get(variableWeapon);
            bw.write("NOMBRE-ARMA ");
            bw.write(Weapon.getName());
            bw.newLine();

            bw.write("ATAQUE-ARMA ");
            bw.write(String.valueOf(Weapon.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA-ARMA ");
            bw.write(String.valueOf(Weapon.getDefenseModifier()));
            bw.newLine();
            bw.write("EMPUÑADURA ");
            if (Weapon.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

        }
        bw.newLine();

        bw.write("NUMERO-ARMA-ACTIVAS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getActiveWeapons().size()));
        bw.newLine();
        for (int variableWeaponActiva = 0; variableWeaponActiva < (listaClient.get(i).getCharacter().getActiveWeapons().size()); variableWeaponActiva++) {
            Weapon WeaponActiva = cazador.getActiveWeapons().get(variableWeaponActiva);

            bw.write("NOMBRE-ARMA-ACTIVAS ");
            bw.write(WeaponActiva.getName());
            bw.newLine();

            bw.write("ATAQUE-ARMA-ACTIVAS ");
            bw.write(String.valueOf(WeaponActiva.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA-ARMA-ACTIVAS ");
            bw.write(String.valueOf(WeaponActiva.getDefenseModifier()));
            bw.newLine();
            bw.write("EMPUÑADURA ");
            if (WeaponActiva.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();
        }
        bw.newLine();
        bw.write("NUMERO-ARMADURAS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getArmors().size()));
        bw.newLine();
        for (int j = 0; j < (listaClient.get(i).getCharacter().getArmors().size()); j++) {
            Armor Weapondura = cazador.getArmors().get(j);
            bw.write("NOMBRE-ARMADURA ");
            bw.write(Weapondura.getName());
            bw.newLine();

            bw.write("DEFENSA-ARMADURA ");
            bw.write(String.valueOf(Weapondura.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE-ARMADURA ");
            bw.write(String.valueOf(Weapondura.getAttackModifier()));
            bw.newLine();
        }
        bw.newLine();

        bw.write("NOMBRE-ARMADURA-ACTIVA ");
        bw.write(listaClient.get(i).getCharacter().getActiveArmor().getName());
        bw.newLine();

        bw.write("DEFENSA-ARMADURA-ACTIVA ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getActiveArmor().getDefenseModifier()));
        bw.newLine();

        bw.write("ATAQUE-ARMADURA-ACTIVA ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getActiveArmor().getAttackModifier()));
        bw.newLine();

        bw.newLine();

        bw.write("ORO ");
        bw.write(String.valueOf(cazador.getGold()));
        bw.newLine();
        bw.write("VIDA ");
        bw.write(String.valueOf(cazador.getHp()));
        bw.newLine();
        bw.write("PODER ");
        bw.write(String.valueOf(cazador.getPower()));
        bw.newLine();

        bw.write("NUMERO-FORTALEZAS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getStrengths().size()));
        bw.newLine();
        for (int j = 0; j < (listaClient.get(i).getCharacter().getArmors().size()); j++) {
            Strength Strength = cazador.getStrengths().get(j);
            bw.write("NOMBRE-FORTALEZA ");
            bw.write(Strength.getName());
            bw.newLine();

            bw.write("VALOR-FORTALEZA ");
            bw.write(String.valueOf(Strength.getValue()));
            bw.newLine();
        }
        bw.newLine();
        bw.write("NUMERO-DEBILIDADES ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getWeaknesses().size()));
        bw.newLine();
        for (int j = 0; j < (listaClient.get(i).getCharacter().getArmors().size()); j++) {
            Weakness Weakness = cazador.getWeaknesses().get(j);
            bw.write("NOMBRE-DEBILIDAD ");
            bw.write(Weakness.getName());
            bw.newLine();

            bw.write("VALOR-DEBILIDAD ");
            bw.write(String.valueOf(Weakness.getValue()));
            bw.newLine();
        }
        bw.newLine();
        bw.write("NUMERO-ESBIRROS ");
        bw.write(String.valueOf(listaClient.get(i).getCharacter().getMinions().size()));
        bw.newLine();
        listaClient.get(i).getCharacter().getType();
        minionsWriter(listaClient, i, cazador, bw);

        bw.write("========== FIN USUARIO ==========");
        bw.newLine();
    }

    private void minionsWriter(ArrayList<Client> clientArrayList, int i, Character normalPerson, BufferedWriter bw) throws IOException {
        for (int j = 0; j < (clientArrayList.get(i).getCharacter().getMinions().size()); j++) {
            switch (normalPerson.getMinions().get(j).getType()) {
                case "HUMANO" -> {
                    Human humano = (Human) normalPerson.getMinions().get(j);

                    bw.write("TIPO-ESBIRRO ");
                    bw.write(humano.getType());
                    bw.newLine();

                    bw.write("NOMBRE-ESBIRRO ");
                    bw.write(humano.getName());
                    bw.newLine();

                    bw.write("VIDA-ESBIRRO ");
                    bw.write(String.valueOf(humano.getHp()));
                    bw.newLine();

                    bw.write("LELTAD ");
                    if (humano.getLoyalty() == Human.Loyalty.ALTA) {
                        bw.write("ALTA");
                    } else if (humano.getLoyalty() == Human.Loyalty.MEDIA) {
                        bw.write("MEDIA");
                    } else if (humano.getLoyalty() == Human.Loyalty.BAJA) {
                        bw.write("BAJA");
                    }
                    bw.newLine();
                }
                case "GHOUL" -> {
                    Ghoul ghoul = (Ghoul) normalPerson.getMinions().get(j);

                    bw.write("TIPO-SBIRRO ");
                    bw.write(ghoul.getType());
                    bw.newLine();
                    bw.write("NOMBRE-ESBIRRO ");
                    bw.write(ghoul.getName());
                    bw.newLine();
                    bw.write("VIDA-ESBIRRO ");
                    bw.write(String.valueOf(ghoul.getHp()));
                    bw.newLine();
                    bw.write("DEPENDENCIA ");
                    bw.write(String.valueOf(ghoul.getDependency()));
                    bw.newLine();
                }
                case "DEMONIO" -> {
                    Demon demonio = (Demon) normalPerson.getMinions().get(j);

                    bw.write("TIPO-ESBIRRO ");
                    bw.write(demonio.getType());
                    bw.newLine();
                    bw.write("NOMBRE-ESBIRRO ");
                    bw.write(demonio.getName());
                    bw.newLine();
                    bw.write("VIDA-ESBIRRO ");
                    bw.write(String.valueOf(demonio.getHp()));
                    bw.newLine();
                    bw.write("DESCRIPCION ");
                    bw.write(demonio.getDescription());
                    bw.newLine();

                    bw.write("NUMERO-ESBIRROS-EXTRA ");
                    bw.write(String.valueOf(clientArrayList.get(i).getCharacter().getMinions().size()));
                    bw.newLine();
                    minionsWriter(clientArrayList, i, normalPerson, bw);
                }
            }
        }
    }
} //FIN