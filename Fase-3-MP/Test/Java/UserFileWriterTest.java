import Entities.Client;
import Entities.Vampire;
import Entities.Discipline;
import Entities.Weapon;
import Entities.Armor;
import Entities.Strength;
import Entities.Weakness;
import Entities.MinionsComposit;
import Entities.Human;
import System.UserFileWriter;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserFileWriterTest {

    private static final String TEST_FILE_PATH = "Fase-3-MP/src/Files/UserRegister.txt";
    private UserFileWriter userFileWriter;
    private ArrayList<Client> testClients;

    @BeforeEach
    void setUp() throws IOException {

        Path folder = Paths.get("Fase-3-MP/src/Files");
        if (!Files.exists(folder)) {
            Files.createDirectories(folder);
        }

        userFileWriter = new UserFileWriter();
        testClients = new ArrayList<>();

        // Configuración de datos de prueba para un Vampiro
        Client client = new Client();
        client.setName("John Doe");
        client.setNick("johnd");
        client.setPassword("pass1234");
        client.setRegister("A12BC");

        Vampire vampiro = new Vampire();
        vampiro.setName("Dracula");
        vampiro.setBlood(100);
        vampiro.setGold(50);
        vampiro.setHealth(150);
        vampiro.setPower(200);
        vampiro.setAge(500);

        Discipline disciplina = new Discipline();
        disciplina.setName("Sanguis");
        disciplina.setAttack(50);
        disciplina.setDefense(50);
        disciplina.setCost(100);
        vampiro.setAbility(disciplina);

        Weapon weapon = new Weapon();
        weapon.setName("Cuchillo");
        weapon.setAttackModifier(20);
        weapon.setDefenseModifier(10);
        weapon.setSingleHand(true);
        ArrayList<Weapon> weapons = new ArrayList<Weapon>();
        weapons.add(weapon);
        vampiro.setWeapons(weapons);
        vampiro.setActiveWeapons(weapons);

        Armor armor = new Armor();
        armor.setName("Capa Oscura");
        armor.setAttackModifier(30);
        armor.setDefenseModifier(40);
        ArrayList<Armor> armors = new ArrayList<Armor>();
        armors.add(armor);
        vampiro.setArmors(armors);
        vampiro.setActiveArmor(armor);

        Strength strength = new Strength();
        strength.setName("Velocidad");
        strength.setValue(60);
        ArrayList<Strength> strengths = new ArrayList<>();
        strengths.add(strength);
        vampiro.setStrengths(strengths);

        Weakness weakness = new Weakness();
        weakness.setName("Luz Solar");
        weakness.setValue(80);
        ArrayList<Weakness> weaknesses = new ArrayList<>();
        weaknesses.add(weakness);
        vampiro.setWeaknesses(weaknesses);

        Human human = new Human();
        human.setName("Zombie");
        human.setType("HUMANO");
        human.setHp(100);
        human.setLoyalty(Human.Loyalty.ALTA);
        ArrayList<MinionsComposit> minions = new ArrayList<>();
        minions.add(human);
        vampiro.setMinions(minions);

        client.setCharacter(vampiro);

        testClients.add(client);
    }

    @Test
    public void testUserRegister() throws IOException {
        // Configurar cliente de prueba
        Client client = new Client();
        client.setName("Ana López");
        client.setNick("analopez");
        client.setPassword("claveSegura");
        client.setRegister("2024-01-01");

        // Borrar archivo de prueba si existe
        Files.deleteIfExists(Paths.get(TEST_FILE_PATH));

        // Ejecutar método a probar
        UserFileWriter userService = new UserFileWriter();
        userService.userRegister(client);  // Asume que USER_FILE_PATH usa TEST_FILE_PATH

        // Verificar que el archivo existe
        assertTrue(Files.exists(Paths.get(TEST_FILE_PATH)));

        // Leer contenido del archivo
        List<String> lines = Files.readAllLines(Paths.get(TEST_FILE_PATH));

        // Verificar formato y datos
        String expected = String.join("\n",
                "========== USUARIO ==========",
                "NOMBRE Ana López",
                "NICK analopez",
                "PASSWORD claveSegura",
                "REGISTRO 2024-01-01",
                "TIPO-PERSONAJE null",
                "========== FIN USUARIO ==========",
                ""  // Última línea nueva
        );

    }

    @Test
    void testRewriteUserFile() throws IOException {
        // Ejecutar el
        userFileWriter.rewriteUserFile(testClients);

        // Leer el contenido del archivo generado
        String fileContent = Files.readString(Paths.get(TEST_FILE_PATH));

        // Verificar que el archivo contiene los datos correctos
        String expectedContent = """
                ========== USUARIO ==========
                NOMBRE John Doe
                NICK johnd
                PASSWORD pass1234
                REGISTRO A12BC
                TIPO-PERSONAJE null
                ========== FIN USUARIO ==========
                """;

        // Comprobar que el contenido del archivo coincide con el contenido esperado
        String[] lines = fileContent.split("\\R");  // Divide el contenido en líneas
        assertEquals("========== USUARIO ==========", lines[0].trim());
        assertEquals("NOMBRE John Doe", lines[1].trim());
        assertEquals("NICK johnd", lines[2].trim());
        assertEquals("PASSWORD pass1234", lines[3].trim());
        assertEquals("REGISTRO A12BC", lines[4].trim());
        assertEquals("TIPO-PERSONAJE null", lines[5].trim());
        assertEquals("========== FIN USUARIO ==========", lines[6].trim());
        // Limpiar el archivo después de la prueba
    }

    @Test
    void testVampireWriter() throws IOException {
        // Crear un archivo temporal para probar la escritura
        File tempFile = new File(TEST_FILE_PATH);
        tempFile.delete(); // Borrar archivo si ya existe

        // Ejecutar el método que escribe el personaje Vampiro en el archivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            userFileWriter.vampireWriter(testClients, 0, bw);
        }

        // Leer el contenido del archivo generado
        String fileContent = Files.readString(Paths.get(TEST_FILE_PATH));

        // Verificar que el contenido del archivo sea el esperado
        assertTrue(fileContent.contains("TIPO-PERSONAJE VAMPIRO"));
        assertTrue(fileContent.contains("NOMBRE-PERSONAJE Dracula"));
        assertTrue(fileContent.contains("SANGRE 100"));
        assertTrue(fileContent.contains("NOMNRE-HABILIDAD Sanguis"));
        assertTrue(fileContent.contains("VALOR-ATAQUE 50"));
        assertTrue(fileContent.contains("NUMERO-ARMAS 1"));
        assertTrue(fileContent.contains("NOMBRE-ARMA Cuchillo"));
        assertTrue(fileContent.contains("NUMERO-ARMAS-ACTIVAS 1"));
        assertTrue(fileContent.contains("NUMERO-ARMADURAS 1"));
        assertTrue(fileContent.contains("NOMBRE-ARMADURA Capa Oscura"));
        assertTrue(fileContent.contains("ORO 50"));
        assertTrue(fileContent.contains("EDAD-VAMPIRO 500"));
        assertTrue(fileContent.contains("HP 150"));
        assertTrue(fileContent.contains("PODER 200"));
        assertTrue(fileContent.contains("NUMERO-FORTALEZAS 1"));
        assertTrue(fileContent.contains("NOMBRE-FORTALEZA Velocidad"));
        assertTrue(fileContent.contains("NUMERO-DEBILIDADES 1"));
        assertTrue(fileContent.contains("NOMBRE-DEBILIDAD Luz Solar"));
        assertTrue(fileContent.contains("NUMERO-ESBIRROS 1"));
        assertTrue(fileContent.contains("NOMBRE-DEL-ESBIRRO Zombie"));

        // Limpiar archivo después de la prueba
        tempFile.delete();
    }
}