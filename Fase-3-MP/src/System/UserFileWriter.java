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
            bw.write("TIPO PERSONAJE null");
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
                       case "LICANTROPO" -> werewolfWriter(clientArrayList, clientArrayList.indexOf(client), bw,clientArrayList.get(0)); //idem.
                       case "CAZADOR" -> hunterWriter(clientArrayList, clientArrayList.indexOf(client), bw,clientArrayList.get(0)); //idem.

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

    //ESCRITURA DE PERSONAJES
    private void writeLine(BufferedWriter bw, String label, String value) throws IOException {
        bw.write(label);
        bw.write(value);
        bw.newLine();
    }
    public void vampireWriter(ArrayList<Client> listaCliente, int i, BufferedWriter bw) throws IOException {

        Vampire vampiro = (Vampire) listaCliente.get(i).getCharacter();
        Discipline disciplina = (Discipline) vampiro.getAbility();

        //TIPO PERSONAJE
        bw.write("TIPO-PERSONAJE ");
        bw.write(listaCliente.get(i).getCharacter().getType());
        bw.newLine();
        //NOMBRE PERSONAJE
        bw.write("NOMBRE-PERSONAJE ");
        bw.write(listaCliente.get(i).getCharacter().getName());
        bw.newLine();
        //PUNTOS DE SANGRE
        bw.write("SANGRE ");
        bw.write(String.valueOf(vampiro.getBlood()));
        bw.newLine();
        //NOMBRE DE HABILIDAD
        bw.write("NOMNRE-HABILIDAD ");
        bw.write(disciplina.getName());
        bw.newLine();

        //VALOR ATAQUE
        bw.write("VALOR-ATAQUE ");
        bw.write(String.valueOf(disciplina.getAttack()));
        bw.newLine();

        //VALOR DEFENSA
        bw.write("VALOR-DEFENSA ");
        bw.write(String.valueOf(disciplina.getDefense()));
        bw.newLine();

        //COSTE HABILIDAD
        bw.write("COSTE-HABILIDAD ");
        bw.write(String.valueOf(disciplina.getCost()));
        bw.newLine();

        //ARMAS
        bw.write("NUMERO-ARMAS ");
        bw.write(String.valueOf(listaCliente.get(i).getCharacter().getWeapons().size()));
        bw.newLine();

        for (int variableArma = 0; variableArma < (listaCliente.get(i).getCharacter().getWeapons().size()); variableArma++) {
            Weapon arma = vampiro.getWeapons().get(variableArma);
            bw.write("NOMBRE-ARMA ");
            bw.write(arma.getName());
            bw.newLine();

            bw.write("ATAQUE-ARMA ");
            bw.write(String.valueOf(arma.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA-ARMA ");
            bw.write(String.valueOf(arma.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA ");
            if (arma.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();

        }
        bw.newLine();

        //NUMERO DE ARMAS ACTIVAS
        bw.write("NUMERO-ARMAS-ACTIVAS ");
        bw.write(String.valueOf(listaCliente.get(i).getCharacter().getActiveWeapons().size()));
        bw.newLine();
        for (int variableArmaActiva = 0; variableArmaActiva < (listaCliente.get(i).getCharacter().getActiveWeapons().size()); variableArmaActiva++) {
            Weapon armaActiva = vampiro.getActiveWeapons().get(variableArmaActiva);

            bw.write("NOMBRE-ARMAS-ACTIVAS ");
            bw.write(armaActiva.getName());
            bw.newLine();

            bw.write("ATAQUE-ARMA-ACTIVAS ");
            bw.write(String.valueOf(armaActiva.getAttackModifier()));
            bw.newLine();

            bw.write("DEFENSA-ARMA-ACTIVAS ");
            bw.write(String.valueOf(armaActiva.getDefenseModifier()));
            bw.newLine();

            //si es true es de 1 mano, si es false es de dos manos
            bw.write("EMPUÑADURA ");
            if (armaActiva.isSingleHand()) {
                bw.write("true");
            } else {
                bw.write("false");
            }
            bw.newLine();
        }
        bw.newLine();

        //ARMADURAS
        //NUMERO DE ARMADURAS
        bw.write("NUMERO-ARMADURAS ");
        bw.write(String.valueOf(listaCliente.get(i).getCharacter().getArmors().size()));
        bw.newLine();
        for (int j = 0; j < (listaCliente.get(i).getCharacter().getArmors().size()); j++) {
            Armor armadura = (Armor) vampiro.getArmors().get(j);
            bw.write("NOMBRE-ARMADURA ");
            bw.write(armadura.getName());
            bw.newLine();

            bw.write("DEFENSA-ARMADURA ");
            bw.write(String.valueOf(armadura.getDefenseModifier()));
            bw.newLine();

            bw.write("ATAQUE-ARMADURA ");
            bw.write(String.valueOf(armadura.getAttackModifier()));
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

        //ORO
        bw.write("ORO ");
        bw.write(String.valueOf(vampiro.getGold()));
        bw.newLine();

        //EDAD VAMPIRO
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

        for (int j = 0; j < (listaCliente.get(i).getCharacter().getStrengths().size()); j++) {
            Strength fortaleza = listaCliente.get(i).getCharacter().getStrengths().get(j);
            bw.write("NOMBRE-FORTALEZA ");
            bw.write(fortaleza.getName());
            bw.newLine();

            bw.write("VALOR-FORTALEZA ");
            bw.write(String.valueOf(fortaleza.getValue()));
            bw.newLine();
        }

        bw.write("NUMERO-DEBILIDADES ");
        bw.write(String.valueOf(vampiro.getWeaknesses().size()));
        bw.newLine();

        for (int j = 0; j < (listaCliente.get(i).getCharacter().getWeaknesses().size()); j++) {
            Weakness debilidad = listaCliente.get(i).getCharacter().getWeaknesses().get(j);
            bw.write("NOMBRE-DEBILIDAD ");
            bw.write(debilidad.getName());
            bw.newLine();

            bw.write("VALOR-DEBILIDAD ");
            bw.write(String.valueOf(debilidad.getValue()));
            bw.newLine();
        }

        //ESBIRROS
        //NUMERO DE ESBIRROS
        bw.write("NUMERO-ESBIRROS ");
        bw.write(String.valueOf(listaCliente.get(i).getCharacter().getMinions().size()));
        bw.newLine();

        //ESBIRROS
        minionsWriter(listaCliente, i, vampiro, bw);

        bw.write("========== FIN USUARIO ==========");
        bw.newLine();
    }

    private void writeWeapon(BufferedWriter bw, Weapon weapon, String tipo) throws IOException {
        bw.write("NOMBRE-DEL-" + tipo + " ");
        bw.write(weapon.getName());
        bw.newLine();

        bw.write("ATAQUE-DEL-" + tipo + " ");
        bw.write(String.valueOf(weapon.getAttackModifier()));
        bw.newLine();

        bw.write("DEFENSA-DEL-" + tipo + " ");
        bw.write(String.valueOf(weapon.getDefenseModifier()));
        bw.newLine();

        bw.write("EMPUÑADURA ");
        bw.write(weapon.isSingleHand() ? "true": "false");
        bw.newLine();
    }

    private void writeArmor(BufferedWriter bw, Armor armor, String tipo) throws IOException {
        bw.write("NOMBRE-DE-LA-" + tipo + " ");
        bw.write(armor.getName());
        bw.newLine();

        bw.write("DEFENSA-DE-LA-" + tipo + " ");
        bw.write(String.valueOf(armor.getDefenseModifier()));
        bw.newLine();

        bw.write("ATAQUE-DE-LA-" + tipo + " ");
        bw.write(String.valueOf(armor.getAttackModifier()));
        bw.newLine();
    }

    private void writeWeakness(BufferedWriter bw, Weakness w) throws IOException {
        bw.write("NOMBRE-DE-LA-DEBILIDAD ");
        bw.write(w.getName());
        bw.newLine();
        bw.write("VALOR-DE-LA-DEBILIDAD ");
        bw.write(String.valueOf(w.getValue()));
        bw.newLine();
    }

    private void writeStrength(BufferedWriter bw, Strength s) throws IOException {
        bw.write("NOMBRE-DE-LA-FORTALEZA ");
        bw.write(s.getName());
        bw.newLine();
        bw.write("VALOR-DE-LA-FORTALEZA ");
        bw.write(String.valueOf(s.getValue()));
        bw.newLine();
    }

    public void werewolfWriter(ArrayList<Client> clientArrayList, int i, BufferedWriter bw,Client client) throws IOException {
        Werewolf licantrop = (Werewolf) clientArrayList.get(i).getCharacter();

            writeLine(bw, "REGISTRO-USUARIO ", client.getRegister()); //para saber de qué usuario es
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

            writeLine(bw, "REGISTRO-USUARIO ", client.getRegister()); //para saber de qué usuario es
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