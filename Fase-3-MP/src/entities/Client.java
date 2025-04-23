package Entities;
import Factories.VampireFactory;
import System.mainSystem;
import System.Terminal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import System.UserFileReader;
import System.UserFileWriter;

public class Client extends User {

    /**A continuación se definen atributos**/
    private String register;
    private Character character;

    /**A continuación se definen Getters y Setters**/
    public Character getCharacter() {
        return character;
    }
    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getRegister() {
        return register;
    }
    public void setRegister(String register) {
        this.register = register;
    }

    public char getLetter() {
        return (char) (Math.random() * 26 + 'a');
    }
    public char getNumber() {
        return (char) (Math.random() * 10 + '0');
    }

    /**A continuación se definen operaciones**/
    public String generateRegisterNumber() {
        UserFileReader userFileReader = new UserFileReader();
        ArrayList<Client> list = userFileReader.userFileReader();
        String strBuilder = null;
        boolean valid = false;

        while (!valid) { //FORMATO LNNLL
            strBuilder = String.valueOf(getLetter()) +
                    getNumber() + getNumber() + getLetter() + getLetter();
            valid = true; // asumimos que es válido hasta que se demuestre lo contrario

            for (Client client : list) {
                if (client.getRegister().equals(strBuilder)) {
                    valid = false; // se encontró un duplicado, no es válido
                }
            }
        }
        return strBuilder;
    }
    public void toChallenge(Client cliente) { //desafiar -> toChallenge
        Challenge challenge = new Challenge();
        challenge.createChallenge(cliente);
    }

    public void deleteCharacter(Client client) {
        Terminal terminal = new Terminal();
        terminal.confirmDeleteCharacter();
        Scanner sc = new Scanner(System.in);
        boolean delete = sc.nextInt() == 1;
        if (delete) {
            client.setCharacter(null);
            terminal.deletedCharacter();
        }
    }

    public void selectTeam(Client client) {
        // A implementar
    }
    public void challenge(Client client) {}

    /**
     * Creación de los Vampiros
     * @return vampire
     */
    public Vampire createVampire(Client client) {
        boolean[] aux1 = new boolean[]{true, true};
        boolean[] aux2 = new boolean[]{true, false};
        boolean rightValue;
        VampireFactory vampireFactory = new VampireFactory();
        Terminal terminal = new Terminal();
        Vampire vampire = new Vampire();
        Discipline discipline = new Discipline();
        ArrayList<Weapon> armas = new ArrayList<>();
        ArrayList<Weapon> armasActivas = new ArrayList<>();
        ArrayList<Armor> armaduras = new ArrayList<>();
        Weakness weakness = new Weakness();
        Strength strength = new Strength();
        ArrayList<Weakness> debilidades = new ArrayList<>();
        ArrayList<Strength> fortalezas = new ArrayList<>();
        Armor armor = new Armor();
        ArrayList<MinionsComposit> minionsComposits = new ArrayList<>();

        setNameNDAbilityVampire(vampireFactory, terminal, vampire, discipline);
        setAllWeaponsVampire(aux1, aux2, vampireFactory, terminal, vampire, armas, armasActivas);
        setAllArmorsVampire(vampireFactory, terminal, vampire, armaduras, armor);
        setGoldPowerHPVampire(vampireFactory, terminal, vampire);
        setVampireModifiers(vampireFactory, terminal, vampire, weakness, strength, debilidades, fortalezas);
        terminal.askVampireAge();
        vampireFactory.setAge(vampire);
       do {
            terminal.askVampireBlood();
            rightValue = vampireFactory.initializeBlood(vampire);
        } while (!rightValue);

        setVampireMinions(vampireFactory, terminal, vampire, minionsComposits);
        vampire.setType("VAMPIRO");
        client.setCharacter(vampire);
        return vampire;
    }
    private void setVampireMinions(VampireFactory vampireFactory, Terminal terminal, Vampire vampire, ArrayList<MinionsComposit> minionsComposits) {
        terminal.askForMinionsNum();
        int minionsNum = vampireFactory.askNumber();
        for (int i = 1; i <= minionsNum; i++) {
            MinionsComposit minion = new MinionsComposit();
            minion = minion.createMinion(true);
            minionsComposits.add(minion);
        }
        vampire.setMinions(minionsComposits);
    }

    private void setVampireModifiers(VampireFactory vampireFactory, Terminal terminal, Vampire vampire, Weakness weakness, Strength strength, ArrayList<Weakness> weaknesses, ArrayList<Strength> strengths) {
        terminal.askNumWeakness();
        int weaknessNum = vampireFactory.askNumber();
        for (int i = 1; i <= weaknessNum; i++) {
            terminal.askWeaknessName();
            vampireFactory.initializeWeaknessName(weakness);
            terminal.askWeaknessValue();
            vampireFactory.initializeWeaknessValue(weakness);
            vampireFactory.addWeakness(weaknesses, weakness);
        }
        vampireFactory.setWeaknesses(vampire, weaknesses);
        terminal.askNumStrengths();
        int numFortalezas = vampireFactory.askNumber();
        for (int iterator = 1; iterator <= numFortalezas; iterator++) {
            terminal.askStrengthName();
            vampireFactory.initializeStrengthName(strength);
            terminal.askStrengthValue();
            vampireFactory.initializeStrengthValue(strength);
            vampireFactory.addStrength(strengths, strength);
        }
        vampireFactory.setStrengths(vampire, strengths);
    }

    private void setGoldPowerHPVampire(VampireFactory vampireFactory, Terminal terminal, Vampire vampire) {
        boolean rightValue;
        do {
            terminal.askGold();
            rightValue = vampireFactory.initializeGold(vampire);
        } while (!rightValue);
        do {
            terminal.askForHp();
            rightValue = vampireFactory.initializeHP(vampire);
        } while (!rightValue);
        do {
            terminal.askPower();
            rightValue = vampireFactory.initializePower(vampire);
        } while (!rightValue);
    }

    private void setAllArmorsVampire(VampireFactory vampireFactory, Terminal terminal, Vampire vampire, ArrayList<Armor> armors, Armor armor) {
        boolean rightValue;
        int numArmors;
        do {
            terminal.askNumArmors();
            numArmors = vampireFactory.askNumber();
        } while (numArmors < 1);
        for (int i = 1; i <= numArmors; i++) {
            armor = new Armor();
            terminal.askNameArmors();
            vampireFactory.initializeArmorName(armor);
            do {
                terminal.askForDefenceArmor();
                rightValue = vampireFactory.initializeArmorDefense(armor);
            } while (!rightValue);
            do {
                terminal.askForAttackeArmor();
                rightValue = vampireFactory.initializeArmorAttack(armor);
            } while (!rightValue);
            vampireFactory.addArmor(armor, armors);
        }
        vampireFactory.setArmors(vampire, armors);
        do {
            terminal.showArmors(armors);
            rightValue = vampireFactory.addActiveArmor(vampire, armor, armors);
        } while (!rightValue);
    }

    private void setAllWeaponsVampire(boolean[] aux1, boolean[] aux2, VampireFactory vampireFactory, Terminal terminal, Vampire vampire, ArrayList<Weapon> weapons, ArrayList<Weapon> activeWeapons) {
        boolean[] rightWeapon;
        boolean rightValue;
        int weaponNum;
        do {
            terminal.askNumWeapons();
            weaponNum = vampireFactory.askNumber();
        } while (weaponNum < 1);
        for (int i = 1; i <= weaponNum; i++) {
            Weapon weapon = new Weapon();
            terminal.askWeapName();
            vampireFactory.initializeWeaponName(weapon);
            do {
                terminal.askWeapAttack();
                rightValue = vampireFactory.initializeWeaponAttack(weapon);
            } while (!rightValue);
            do {
                terminal.askWeapDefence();
                rightValue = vampireFactory.initializeWeaponDefense(weapon);
            } while (!rightValue);
            do {
                terminal.isWeaponSingleHanded();
                rightValue = vampireFactory.initializeWeaponSingleHand(weapon);
            } while (!rightValue);
            vampireFactory.addWeapon(weapons, weapon);
        }
        vampireFactory.setWeapons(vampire, weapons);
        do {
            terminal.showWeapons(weapons);
            rightWeapon = vampireFactory.addActiveWeapon(weapons, activeWeapons);
        } while (!Arrays.equals(rightWeapon, aux1) && !Arrays.equals(rightWeapon, aux2));
        if (Arrays.equals(rightWeapon, aux1)) {
            do {
                terminal.anotherWeapon(weapons, activeWeapons.getFirst());
                rightValue = vampireFactory.addActiveWeapon2(weapons, activeWeapons);
                if (!rightValue) {
                    terminal.noCorrectNumSelecction();
                }
            } while (!rightValue);
        }
        vampireFactory.setActiveWeapons(vampire, activeWeapons);
    }

    private void setNameNDAbilityVampire(VampireFactory vampireFactory, Terminal terminal, Vampire vampire, Discipline discipline) {
        boolean rightValue;
        terminal.askVampireName();
        vampireFactory.initializeName(vampire);
        terminal.askAbilityName();
        vampireFactory.initializeAbilityName(discipline);
        do {
            terminal.askAbilityAttack();
            rightValue = vampireFactory.initializeAbilityAttack(discipline);
        } while (!rightValue);
        do {
            terminal.askAbilityDefence();
            rightValue = vampireFactory.initializeAbilityDefense(discipline);
        } while (!rightValue);
        do {
            terminal.askCostAbility();
            rightValue = vampireFactory.initializeAbilityCost(discipline);
        } while (!rightValue);
        vampireFactory.setAbility(vampire, discipline);
    }

    public Hunter createHunter() {
        return new Hunter();
    }

    public Werewolf createWerewolf() {
        return new Werewolf();
    }

        /**
         * Elimina permanentemente una cuenta del sistema
         * @param client Usuario logueado (puede ser Client o Administrator)
         * @param system Referencia al sistema principal
         */
    public void deleteAccount(Client client, mainSystem system) {
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);

        // Mostrar advertencia y solicitar confirmación
        terminal.advertency();
        terminal.writeConfirm();
        // Leer confirmación
        String confirmation = sc.nextLine().trim();

        if (confirmation.equalsIgnoreCase("ELIMINAR")) {
            try {
                // Leer lista actual de clientes
                UserFileReader userFileReader = new UserFileReader();
                ArrayList<Client> clientList = userFileReader.userFileReader();

                // Buscar y eliminar cliente
                boolean removed = clientList.removeIf(c -> c.getRegister().equals(client.getRegister()));

                if (removed) {
                    // Guardar lista actualizada
                    UserFileWriter userFileWriter = new UserFileWriter();
                    userFileWriter.rewriteUserFile(clientList);

                    // Cerrar sesión
                    terminal.deletedAccountOK();
                    terminal.logout();
                    system.selector();
                } else {
                    terminal.noAccountAvaliable();
                }
            } catch (Exception e) {
                terminal.error();
                e.getMessage();
            }
        } else {
            terminal.cancelOperation();
            terminal.closedSesion4Security();
            system.selector();
        }
    }

    public void globalRanking(Client client) {
        Terminal terminal = new Terminal();
        terminal.rankingMessage();

        // Leemos todos los usuarios desde archivo
        UserFileReader userFileReader = new UserFileReader();
        ArrayList<Client> lista = userFileReader.userFileReader();

        // Verificamos que cada cliente tenga personaje y oro válido (debug opcional)
        for (Client c : lista) {
            if (c.getCharacter() != null) {
                System.out.println("Usuario: " + c.getName() + " - Oro: " + c.getCharacter().getGold());
            } else {
                System.out.println("Usuario: " + c.getName() + " - Sin personaje");
            }
        }

        // Copiamos la lista original y la ordenamos por cantidad de oro (de mayor a menor)
        ArrayList<Client> listaAux = new ArrayList<>(lista);
        listaAux.sort((c1, c2) -> {
            int gold1 = c1.getCharacter() != null ? c1.getCharacter().getGold() : 0;
            int gold2 = c2.getCharacter() != null ? c2.getCharacter().getGold() : 0;
            return Integer.compare(gold2, gold1); // orden descendente
        });

        // Mostramos el ranking
        terminal.showGoldRanking(listaAux);
    }

}//FIN
