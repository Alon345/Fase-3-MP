
import Entities.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
//import org.junit.Before;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Files;


public class ClientTest {
    //Tests que comprueba que Name, Nick y Password se establecen correctamente.
    @Test
    public void setNameTest() {
        String name = "person_name_test";
        Client person = new Client();
        person.setName(name);
        assertEquals(name, person.getName(), "El nombre debe ser el establecido");
    }

    @Test
    public void setNickTest() {
        String nick = "person_nick_test";
        Client person = new Client();
        person.setNick(nick);
        assertEquals(nick, person.getNick(), "El nick debe ser el establecido");
    }

    @Test
    public void setPasswordTest() {
        String password = "My_Password";
        Client person = new Client();
        person.setPassword(password);
        assertEquals(password, person.getPassword(), "La contraseña debe ser el establecida");
    }

    @Test
    public void setRegisterTest() {
        String register = "L11NJ";
        Client person = new Client();
        person.setRegister(register);
        assertEquals(register, person.getRegister(), "El registro debe ser el establecido");
    }

    //Tests que comprueba que Name, Nick y Password se obtienen correctamente.
    @Test
    public void getNameTest() {
        String name = "person_name_test";
        Client person = new Client();
        person.setName(name);
        assertEquals(name, person.getName(), "El nombre no coincide con el establecido");
    }

    @Test
    public void getNickTest() {
        String nick = "person_nick_test";
        Client person = new Client();
        person.setNick(nick);
        assertEquals(nick, person.getNick(), "El nick debe ser el establecido");
    }

    @Test
    public void getPasswordTest() {
        String password = "My_Password";
        Client person = new Client();
        person.setPassword(password);
        assertEquals(password, person.getPassword(), "La contraseña debe ser el establecida");
    }

    @Test
    public void getRegisterTest() {
        String register = "L11NJ";
        Client person = new Client();
        person.setRegister(register);
        assertEquals(register, person.getRegister(), "El registro debe ser el establecido");
    }

    @Test
    public void setCharacterVampireTest() {
        String type = "Vampire";
        Vampire vampire = new Vampire();
        Client person = new Client();
        person.setCharacter(vampire);
        assertEquals(type, person.getCharacter().getType(), "El tipo de personaje debe ser el establecido");
    }

    @Test
    public void setCharacterHunterTest() {
        String type = "CAZADOR";
        Hunter hunter = new Hunter();
        Client person = new Client();
        person.setCharacter(hunter);
        assertEquals(type, person.getCharacter().getType(), "El tipo de personaje debe ser el establecido");
    }

    @Test
    public void setCharacterWerewolfTest() {
        String type = "LICANTROPO";
        Werewolf werewolf = new Werewolf();
        Client person = new Client();
        person.setCharacter(werewolf);
        assertEquals(type, person.getCharacter().getType(), "El tipo de personaje debe ser el establecido");
    }

    @Test
    public void setCharacterNullTest() {
        Client person = new Client();

        // Ahora esperamos que setCharacter(null) lance IllegalArgumentException
        assertThrows(
                IllegalArgumentException.class,
                () -> person.setCharacter(null),
                "No debe permitirse asignar un personaje null"
        );
    }

    //Test Ranking
    private static final String USER_FILE_PATH = "Fase-3-MP/src/Files/UserRegister.txt";
    private PrintStream originalOut;
    private ByteArrayOutputStream capturedOut;

    @BeforeEach
    void setUp() throws Exception {
        // 1) crear fichero de prueba con tres usuarios y sus “oro”
        Path p = Paths.get(USER_FILE_PATH);
        Files.deleteIfExists(p);
        Files.write(p, Arrays.asList(
                   "alice 10", "bob 5", "charlie 20"));
        // 2) apuntar la ruta usada por Client.globalRanking()
        //Client.USER_FILE_PATH = USER_FILE_PATH;

        // 3) preparar captura de consola
        originalOut = System.out;
        capturedOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturedOut));
    }

    @AfterEach
    void tearDown() throws Exception {
        // restaurar consola y borrar fichero
        System.setOut(originalOut);
        Files.deleteIfExists(Paths.get(USER_FILE_PATH));
    }

    @Test
    void globalRanking_ordersByGoldDescending() {
        // ejecutamos el método bajo prueba
        new Client().globalRanking();

        String out = capturedOut.toString().toLowerCase();

        // debe mostrar el mensaje de ranking
        assertTrue(out.contains("ranking"), "Debe mostrar el mensaje de ranking");

        // comprobamos que “charlie” (20) aparece antes que “alice” (10),
        // y “alice” antes que “bob” (5)
        int iCharlie = out.indexOf("charlie");
        int iAlice   = out.indexOf("alice");
        int iBob     = out.indexOf("bob");

        assertTrue(iCharlie < iAlice && iAlice < iBob,
                "El ranking debe mostrarse por oro en orden descendente (mayor a menor)");
    }
    
    //Test eliminar personaje
    Client clientWithoutCharacter, clientWithVampire;

    @Test
    public void aplyCharacters() {
        //Cliente sin personaje
        clientWithoutCharacter.setName("client_without_character");
        clientWithoutCharacter.setNick("nick_without_character");
        clientWithoutCharacter.setRegister("b00bb");
        clientWithoutCharacter.setPassword("pass_without_character");
        clientWithoutCharacter.setCharacter(null);

        //Cliente con Vampire
        clientWithVampire.setName("name_with_vampire");
        clientWithVampire.setNick("nick_with_vampire");
        clientWithVampire.setRegister("f99ff");
        clientWithVampire.setPassword("pass_with_vampire");
        Vampire Vampire = new Vampire();
        Vampire.setBlood(0);
        Vampire.setType("VAMPIRO");
        Vampire.setAge(10);
        Vampire.setPower(5);
        Vampire.setHealth(5);
        Vampire.setGold(1000);
        Vampire.setName("vampire_name");
        ArrayList<Weapon> armas = new ArrayList<>();
        Weapon arma1 = new Weapon();
        arma1.setName("weapon_name_1");
        arma1.setAttackModifier(3);
        arma1.setDefenseModifier(1);
        arma1.setSingleHand(false);
        armas.add(arma1);
        Vampire.setWeapons(armas);
        Vampire.setActiveWeapons(armas);
        ArrayList<Armor> Armors = new ArrayList<>();
        Armor Armor1 = new Armor();
        Armor1.setName("armor_name_1");
        Armor1.setAttackModifier(1);
        Armor1.setDefenseModifier(3);
        Armors.add(Armor1);
        Vampire.setArmors(Armors);
        Vampire.setActiveArmor(Armor1);
        ArrayList<Strength> fortalezas = new ArrayList<>();
        Strength fortaleza1 = new Strength();
        fortaleza1.setName("strength_name_1");
        fortaleza1.setValue(2);
        fortalezas.add(fortaleza1);
        Vampire.setStrengths(fortalezas);
        ArrayList<Weakness> debilidades = new ArrayList<>();
        Weakness debilidad = new Weakness();
        debilidad.setName("weakness_name_1");
        debilidad.setValue(1);
        debilidades.add(debilidad);
        Vampire.setWeaknesses(debilidades);
        ArrayList<MinionsComposit> esbirros = new ArrayList<>();
        Ghoul ghoul = new Ghoul();
        ghoul.setName("ghoul_name");
        ghoul.setType("GHOUL");
        ghoul.setDependency(5);
        ghoul.setHp(3);
        esbirros.add(ghoul);
        Vampire.setMinions(esbirros);
        Discipline disciplina = new Discipline();
        disciplina.setName("discipline_name_1");
        disciplina.setCost(3);
        disciplina.setAttack(2);
        disciplina.setDefense(1);
        Vampire.setAbility(disciplina);
        clientWithVampire.setCharacter(Vampire);
    }
    Client client = new Client();
    @Test
    public void deleteCharacterTest() {
        client.deleteCharacter(clientWithVampire);

        assertNull(clientWithVampire.getCharacter());
    }

    @Test
    public void selectTeamTest(){
        client.selectTeam(clientWithVampire);
        assertNotNull(clientWithVampire.getCharacter().getActiveWeapons());
        assertNotNull(clientWithVampire.getCharacter().getArmors());
    }
}
