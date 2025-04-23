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
            bw.write("TIPO DE PERSONAJE null");
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
            FileWriter fw = new FileWriter(file, true); //<-- sin append borra toda la bd de user
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
                    bw.write("TIPO PERSONAJE null");
                    bw.newLine();
                } else {
                    String characterType = clientArrayList.get(i).getCharacter().getType();
                    bw.write("TIPO PERSONAJE ");
                    bw.write(characterType);
                    bw.newLine();
                    switch (characterType) {
                       case "VAMPIRO" -> vampireWriter(clientArrayList, i, bw,clientArrayList.get(i)); //escribimos y guardamos los atributos de los vampiros
                       case "LICANTROPO" -> licantropWriter(clientArrayList, i, bw,clientArrayList.get(i)); //idem.
                       case "CAZADOR" -> hunterWriter(clientArrayList, i, bw,clientArrayList.get(i)); //idem.

                    }
                }
            }
            bw.write("========== FIN USUARIO ==========");
            bw.close();
        } catch (Exception exception) {
            mainSystem system = new mainSystem();
            system.selector();
            exception.printStackTrace();
        }
    }

    public void rewriteUserFileForDeleteCharacter(ArrayList<Client> clientArrayList) {
        try {
            File file = new File(USER_FILE_PATH);
            FileWriter fw = new FileWriter(file); //<-- sin append borra toda la bd de user
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
                    bw.write("TIPO PERSONAJE null");
                    bw.newLine();
                } else {
                    String characterType = clientArrayList.get(i).getCharacter().getType();
                    bw.write("TIPO PERSONAJE ");
                    bw.write(characterType);
                    bw.newLine();
                    switch (characterType) {
                        case "VAMPIRO" -> vampireWriter(clientArrayList, i, bw,clientArrayList.get(i)); //escribimos y guardamos los atributos de los vampiros
                        case "LICANTROPO" -> licantropWriter(clientArrayList, i, bw,clientArrayList.get(i)); //idem.
                        case "CAZADOR" -> hunterWriter(clientArrayList, i, bw,clientArrayList.get(i)); //idem.

                    }
                }
            }
            bw.write("========== FIN USUARIO ==========");
            bw.close();
        } catch (Exception exception) {
            mainSystem system = new mainSystem();
            system.selector();
            exception.printStackTrace();
        }
    }

    //ESCRITURA DE PERSONAJES
    private void writeLine(BufferedWriter bw, String label, String value) throws IOException {
        bw.write(label);
        bw.write(value);
        bw.newLine();
    }
    public void vampireWriter(ArrayList<Client> clientArrayList, int i,BufferedWriter bw, Client client) throws IOException {

        Character character = clientArrayList.get(i).getCharacter();
        Vampire vampire = (Vampire) character;
        Discipline discipline = (Discipline) vampire.getAbility();

            writeLine(bw, "NOMBRE DE PERSONAJE ", character.getName());
            writeLine(bw, "SANGRE ", Integer.toString(vampire.getBlood()));
            writeLine(bw, "NOMBRE DE LA HABILIDAD ", discipline.getName());
            writeLine(bw, "VALOR DE ATAQUE ", String.valueOf(discipline.getAttack()));
            writeLine(bw, "VALOR DE DEFENSA ", String.valueOf(discipline.getDefense()));
            writeLine(bw, "COSTE DE LA HABILIDAD ", String.valueOf(discipline.getCost()));

            writeLine(bw, "NUMERO DE ARMAS ", String.valueOf(character.getWeapons().size()));
            for (Weapon weapon : vampire.getWeapons()) {
                writeLine(bw, "NOMBRE DEL ARMA ", weapon.getName());
                writeLine(bw, "ATAQUE DEL ARMA ", String.valueOf(weapon.getAttackModifier()));
                writeLine(bw, "DEFENSA DEL ARMA ", String.valueOf(weapon.getDefenseModifier()));
                writeLine(bw, "EMPUÑADURA ", weapon.isSingleHand() ? "true" : "false");
            }

            writeLine(bw, "", ""); // Línea en blanco
            writeLine(bw, "NUMERO DE ARMAS ACTIVAS ", String.valueOf(character.getActiveWeapons().size()));
            for (Weapon weapon : vampire.getActiveWeapons()) {
                writeLine(bw, "NOMBRE DE LAS ARMAS ACTIVAS ", weapon.getName());
                writeLine(bw, "ATAQUE DE LAS ARMA ACTIVAS ", String.valueOf(weapon.getAttackModifier()));
                writeLine(bw, "DEFENSA DEL ARMA ACTIVAS ", String.valueOf(weapon.getDefenseModifier()));
                writeLine(bw, "EMPUÑADURA ", weapon.isSingleHand() ? "true" : "false");
            }

            writeLine(bw, "NUMERO DE ARMADURAS  ", String.valueOf(character.getArmors().size()));
            for (Armor armor : vampire.getArmors()) {
                writeLine(bw, "NOMBRE DE LA ARMADURA ", armor.getName());
                writeLine(bw, "DEFENSA DE LA ARMADURA ", String.valueOf(armor.getDefenseModifier()));
                writeLine(bw, "ATAQUE DE LA ARMADURA ", String.valueOf(armor.getAttackModifier()));
            }

            Armor activeArmor = vampire.getActiveArmor();
            writeLine(bw, "NOMBRE DE LA ARMADURA ACTIVA ", activeArmor.getName());
            writeLine(bw, "DEFENSA DE LA ARMADURA ACTIVA ", String.valueOf(activeArmor.getDefenseModifier()));
            writeLine(bw, "ATAQUE DE LA ARMADURA ACTIVA ", String.valueOf(activeArmor.getAttackModifier()));

            writeLine(bw, "ORO ", String.valueOf(vampire.getGold()));
            writeLine(bw, "EDAD DEL VAMPIRO ", String.valueOf(vampire.getAge()));
            writeLine(bw, "HP ", String.valueOf(vampire.getHp()));
            writeLine(bw, "PODER ", String.valueOf(vampire.getPower()));

            writeLine(bw, "NUMERO DE FORTALEZAS ", String.valueOf(vampire.getStrengths().size()));
            for (Strength s : character.getStrengths()) {
                writeLine(bw, "  -NOMBRE DE LA FORTALEZA ", s.getName());
                writeLine(bw, "  -VALOR DE LA FORTALEZA ", String.valueOf(s.getValue()));
            }

            writeLine(bw, "NUMERO DE DEBILIDADES ", String.valueOf(vampire.getWeaknesses().size()));
            for (Weakness w : character.getWeaknesses()) {
                writeLine(bw, "NOMBRE DE LA DEBILIDAD ", w.getName());
                writeLine(bw, "VALOR DE LA DEBILIDAD ", String.valueOf(w.getValue()));
            }

            writeLine(bw, "NUMERO DE ESBIRROS  ", String.valueOf(character.getMinions().size()));
            minionsWriter(clientArrayList, i, vampire, bw);
            client.setCharacter(vampire);
    }

    private void writeWeapon(BufferedWriter bw, Weapon weapon, String tipo) throws IOException {
        bw.write("NOMBRE DEL " + tipo + " ");
        bw.write(weapon.getName());
        bw.newLine();

        bw.write("ATAQUE DEL " + tipo + " ");
        bw.write(String.valueOf(weapon.getAttackModifier()));
        bw.newLine();

        bw.write("DEFENSA DEL " + tipo + " ");
        bw.write(String.valueOf(weapon.getDefenseModifier()));
        bw.newLine();

        bw.write("EMPUÑADURA ");
        bw.write(weapon.isSingleHand() ? "true" : "false");
        bw.newLine();
    }

    private void writeArmor(BufferedWriter bw, Armor armor, String tipo) throws IOException {
        bw.write("NOMBRE DE LA " + tipo + " ");
        bw.write(armor.getName());
        bw.newLine();

        bw.write("DEFENSA DE LA " + tipo + " ");
        bw.write(String.valueOf(armor.getDefenseModifier()));
        bw.newLine();

        bw.write("ATAQUE DE LA " + tipo + " ");
        bw.write(String.valueOf(armor.getAttackModifier()));
        bw.newLine();
    }

    private void writeWeakness(BufferedWriter bw, Weakness w) throws IOException {
        bw.write("NOMBRE DE LA DEBILIDAD ");
        bw.write(w.getName());
        bw.newLine();
        bw.write("VALOR DE LA DEBILIDAD ");
        bw.write(String.valueOf(w.getValue()));
        bw.newLine();
    }

    private void writeStrength(BufferedWriter bw, Strength s) throws IOException {
        bw.write("NOMBRE DE LA FORTALEZA ");
        bw.write(s.getName());
        bw.newLine();
        bw.write("VALOR DE LA FORTALEZA ");
        bw.write(String.valueOf(s.getValue()));
        bw.newLine();
    }

    public void licantropWriter(ArrayList<Client> clientArrayList, int i, BufferedWriter bw,Client client) throws IOException {
        Werewolf licantrop = (Werewolf) clientArrayList.get(i).getCharacter();

            writeLine(bw, "REGISTRO_USUARIO ", client.getRegister()); //para saber de qué usuario es
            bw.write("------ TIPO DE PERSONAJE ------");
            bw.write(licantrop.getType());
            bw.newLine();
            bw.write("NOMBRE DEL PERSONAJE "); bw.write(licantrop.getName()); bw.newLine();
            bw.write("NOMBRE DE LA HABILIDAD "); bw.write(licantrop.getAbility().getName()); bw.newLine();
            bw.write("RABIA "); bw.newLine();

            bw.write("NUMERO DE ARMAS "); bw.write(String.valueOf(licantrop.getWeapons().size())); bw.newLine();
            for (Weapon w : licantrop.getWeapons()) writeWeapon(bw, w, "ARMA");

            bw.write("NUMERO DE ARMAS ACTIVAS "); bw.write(String.valueOf(licantrop.getActiveWeapons().size())); bw.newLine();
            for (Weapon w : licantrop.getActiveWeapons()) writeWeapon(bw, w, "ARMA ACTIVA");

            bw.write("NUMERO DE ARMADURAS "); bw.write(String.valueOf(licantrop.getArmors().size())); bw.newLine();
            for (Armor a : licantrop.getArmors()) writeArmor(bw, a, "ARMADURA");

            writeArmor(bw, licantrop.getActiveArmor(), "ARMADURA ACTIVA");

            bw.write("ORO "); bw.write(String.valueOf(licantrop.getGold())); bw.newLine();
            bw.write("HP "); bw.write(String.valueOf(licantrop.getHp())); bw.newLine();
            bw.write("PODER "); bw.write(String.valueOf(licantrop.getPower())); bw.newLine();

            bw.write("NUMERO DE DEBILIDADES "); bw.write(String.valueOf(licantrop.getWeaknesses().size())); bw.newLine();
            for (Weakness w : licantrop.getWeaknesses()) writeWeakness(bw, w);

            bw.write("NUMERO DE FORTALEZAS "); bw.write(String.valueOf(licantrop.getStrengths().size())); bw.newLine();
            for (Strength s : licantrop.getStrengths()) writeStrength(bw, s);

            bw.write("NUMERO DE ESBIRROS "); bw.write(String.valueOf(licantrop.getMinions().size())); bw.newLine();

            minionsWriter(clientArrayList, i, licantrop, bw);
    }

    public void hunterWriter(ArrayList<Client> clientArrayList, int i, BufferedWriter bw,Client client) throws IOException {
        Hunter hunter = (Hunter) clientArrayList.get(i).getCharacter();
        Talent talent = (Talent) hunter.getAbility();

            writeLine(bw, "REGISTRO_USUARIO ", client.getRegister()); //para saber de qué usuario es
            bw.write("TIPO DE PERSONAJE "); bw.write(hunter.getType()); bw.newLine();
            bw.write("NOMBRE DE PERSONAJE "); bw.write(hunter.getName()); bw.newLine();
            bw.write("NOMBRE DE LA HABILIDAD "); bw.write(hunter.getAbility().getName()); bw.newLine();
            bw.write("VOLUNTAD "); bw.write("3"); bw.newLine();
            bw.write("ATAQUE DE HABILIDAD "); bw.write(String.valueOf(hunter.getAbility().getAttack())); bw.newLine();
            bw.write("DEFENSA DE LA HABILIDAD "); bw.write(String.valueOf(hunter.getAbility().getDefense())); bw.newLine();
            bw.write("EDAD DEL CAZADOR "); bw.write(String.valueOf(talent.getAge())); bw.newLine();

            bw.write("NUMERO DE ARMAS "); bw.write(String.valueOf(hunter.getWeapons().size())); bw.newLine();
            for (Weapon w : hunter.getWeapons()) writeWeapon(bw, w, "ARMA");

            bw.write("NUMERO DE ARMAS ACTIVAS "); bw.write(String.valueOf(hunter.getActiveWeapons().size())); bw.newLine();
            for (Weapon w : hunter.getActiveWeapons()) writeWeapon(bw, w, "ARMA ACTIVA");

            bw.write("NUMERO DE ARMADURAS "); bw.write(String.valueOf(hunter.getArmors().size())); bw.newLine();
            for (Armor a : hunter.getArmors()) writeArmor(bw, a, "ARMADURA");

            writeArmor(bw, hunter.getActiveArmor(), "ARMADURA ACTIVA");

            bw.write("ORO "); bw.write(String.valueOf(hunter.getGold())); bw.newLine();
            bw.write("HP "); bw.write(String.valueOf(hunter.getHp())); bw.newLine();
            bw.write("PODER "); bw.write(String.valueOf(hunter.getPower())); bw.newLine();

            bw.write("NUMERO DE DEBILIDADES "); bw.write(String.valueOf(hunter.getWeaknesses().size())); bw.newLine();
            for (Weakness w : hunter.getWeaknesses()) writeWeakness(bw, w);

            bw.write("NUMERO DE FORTALEZAS "); bw.write(String.valueOf(hunter.getStrengths().size())); bw.newLine();
            for (Strength s : hunter.getStrengths()) writeStrength(bw, s);

            bw.write("NUMERO DE ESBIRROS "); bw.write(String.valueOf(hunter.getMinions().size())); bw.newLine();

            minionsWriter(clientArrayList, i, hunter, bw);

    }

    private void minionsWriter(ArrayList<Client> clientArrayList, int i, Character normalPerson, BufferedWriter bw) throws IOException {
        List<MinionsComposit> minions = normalPerson.getMinions();

        for (MinionsComposit minion : minions) {
            String tipo = minion.getType();
            bw.write("TIPO DE ESBIRRO " + tipo);
            bw.newLine();
            bw.write("NOMBRE DEL ESBIRRO " + minion.getName());
            bw.newLine();
            bw.write("VIDA DEL ESBIRRO " + minion.getHp());
            bw.newLine();

            switch (tipo) {
                case "HUMANO" -> {
                    Human human = (Human) minion;
                    bw.write("LEALTAD " + human.getLoyalty());
                    bw.newLine();
                }
                case "GHOUL" -> {
                    Ghoul ghoul = (Ghoul) minion;
                    bw.write("DEPENDENCIA " + ghoul.getDependency());
                    bw.newLine();
                }
                case "DEMONIO" -> {
                    Demon demon = (Demon) minion;
                    bw.write("DESCRIPCION " + demon.getDescription());
                    bw.newLine();
                    bw.write("NUMERO DE ESBIRROS EXTRA " + clientArrayList.get(i).getCharacter().getMinions().size());
                    bw.newLine();
                    minionsWriter(clientArrayList, i, normalPerson, bw); // llamada recursiva
                }
            }
        }
    }
} //FIN