import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import System.*;
import Entities.*;

import static org.junit.jupiter.api.Assertions.*;

class UserFileReaderTest {

    private static final String TEST_FILE_PATH = "Fase-3-MP/src/Files/UserRegister.txt";
    private UserFileReader reader;

    @Test
    void testUserFileReaderParsesValidFile() throws IOException {
        // Crear un archivo de prueba con datos correctos
        reader = new UserFileReader();

        List<Client> clients = reader.userFileReader();

        assertNotNull(clients);
        assertEquals(3, clients.size()); // Esperamos 3 usuarios
        assertEquals("Raul", clients.get(0).getName());
        assertEquals("user_2025", clients.get(0).getNick());
        assertEquals("v65ju", clients.get(0).getRegister());
        assertNotNull(clients.get(0).getCharacter()); // Debe tener un personaje (vampiro)
        assertEquals("Ramon", clients.get(1).getName()); // Usuario 2 (licántropo)
        assertEquals("Ruben", clients.get(2).getName()); // Usuario 3 (cazador)
    }
    @Test
    void testGoldReaderForRankingParsesCorrectly() throws IOException {
        List<Map.Entry<String, Integer>> ranking = UserFileReader.goldReaderForRanking(TEST_FILE_PATH);

        // Comprobaciones
        assertEquals(3, ranking.size());  // Solo Raul y Lucia son válidos

        assertEquals("Raul", ranking.get(0).getKey());
        assertEquals(150, ranking.get(0).getValue());

        assertEquals("Ramon", ranking.get(1).getKey());
        assertEquals(200, ranking.get(1).getValue());

        assertEquals("Ruben", ranking.get(2).getKey());
        assertEquals(100, ranking.get(2).getValue());
    }
    @Test
    void testVampireReaderParsesCorrectly() throws IOException {
        String vampireData = """
        NOMBRE Dracula
        SANGRE 100
        HABILIDAD DisciplinaOscura
        ATAQUE 50
        DEFENSA 30
        HBILIDAD-COSTE 10
        ARMAS 1
        NOMBRE Espada
        ATAQUE 20
        DEFENSA 10
        EMPUÑADURA true
        
        ACTIVAS 1
        NOMBRE Espada
        ATAQUE 20
        DEFENSA 10
        EMPUÑADURA true
        
        ARMADURAS 1
        NOMBRE ArmaduraOscura
        DEFENSA 40
        ATAQUE 5
        
        NOMBRE ArmaduraOscura
        DEFENSA 40
        ATAQUE 5
        
        ORO 200
        EDAD 500
        VIDA 1000
        PODER 80
        FORTALEZAS 1
        NOMBRE Inmortalidad
        VALOR 100
        DEBILIDADES 1
        NOMBRE LuzSolar
        VALOR 50
        ESBIRROS 0
        ========== FIN USUARIO ==========
        """;

        BufferedReader reader = new BufferedReader(new StringReader(vampireData));
        UserFileReader userReader = new UserFileReader();
        Vampire vampire = userReader.vampireReader(reader);

        // Verificaciones básicas
        assertEquals("VAMPIRO", vampire.getType());
        assertEquals("Dracula", vampire.getName());
        assertEquals(100, vampire.getBlood());
        assertEquals(500, vampire.getAge());
        assertEquals(1000, vampire.getHealth());
        assertEquals(80, vampire.getPower());

        // Disciplina
        Ability a = vampire.getAbility();
        assertNotNull(a);
        assertEquals("DisciplinaOscura", a.getName());
        assertEquals(50, a.getAttack());
        assertEquals(30, a.getDefense());

        // Armas
        assertEquals(1, vampire.getWeapons().size());
        Weapon w = vampire.getWeapons().get(0);
        assertEquals("Espada", w.getName());
        assertEquals(20, w.getAttackModifier());
        assertEquals(10, w.getDefenseModifier());
        assertTrue(w.isSingleHand());

        // Armas activas
        assertEquals(1, vampire.getActiveWeapons().size());

        // Armaduras
        assertEquals(1, vampire.getArmors().size());
        Armor armor = vampire.getArmors().get(0);
        assertEquals("ArmaduraOscura", armor.getName());
        assertEquals(40, armor.getDefenseModifier());
        assertEquals(5, armor.getAttackModifier());

        // Armadura activa
        assertEquals("ArmaduraOscura", vampire.getActiveArmor().getName());

        // Fortalezas y debilidades
        assertEquals(1, vampire.getStrengths().size());
        assertEquals("Inmortalidad", vampire.getStrengths().get(0).getName());

        assertEquals(1, vampire.getWeaknesses().size());
        assertEquals("LuzSolar", vampire.getWeaknesses().get(0).getName());

        // Esbirros
        assertTrue(vampire.getMinions().isEmpty());
    }
    @Test
    void StestHunterReaderParsesCorrectly() throws IOException {
        String hunterData = """
    NOMBRE VanHelsing
    VOLUNTAD 85
    TALENTO Exorcismo
    ATAQUE 40
    DEFENSA 25
    EDAD 45
    ARMAS 1
    NOMBRE Ballesta
    ATAQUE 30
    DEFENSA 10
    EMPUÑADURA true

    ARMAS_ACTIVAS 1
    NOMBRE Ballesta
    ATAQUE 30
    DEFENSA 10
    EMPUÑADURA true

    ARMADURAS 1
    NOMBRE CotaMalla
    DEFENSA 20
    ATAQUE 5

    NOMBRE CotaMalla
    DEFENSA 20
    ATAQUE 5

    ORO 150
    VIDA 900
    PODER 70
    FORTALEZAS 1
    NOMBRE Determinación
    VALOR 40
    DEBILIDADES 1
    NOMBRE Orgullo
    VALOR 25
    ESBIRROS 0
    ========== FIN USUARIO ==========
    """;

        BufferedReader reader = new BufferedReader(new StringReader(hunterData));
        UserFileReader userReader = new UserFileReader();
        Hunter hunter = userReader.hunterReader(reader);

        assertEquals("CAZADOR", hunter.getType());
        assertEquals("VanHelsing", hunter.getName());
        assertEquals(85, hunter.getWillpower());
        assertEquals(150, hunter.getGold());
        assertEquals(900, hunter.getHealth());
        assertEquals(70, hunter.getPower());

        // Talento
        Talent t = (Talent) hunter.getAbility();
        assertNotNull(t);
        assertEquals("Exorcismo", t.getName());
        assertEquals(40, t.getAttack());
        assertEquals(25, t.getDefense());
        assertEquals(45, t.getAge());

        // Armas
        assertEquals(1, hunter.getWeapons().size());
        Weapon w = hunter.getWeapons().get(0);
        assertEquals("Ballesta", w.getName());
        assertEquals(30, w.getAttackModifier());
        assertEquals(10, w.getDefenseModifier());
        assertTrue(w.isSingleHand());

        // Armas activas
        assertEquals(1, hunter.getActiveWeapons().size());
        Weapon wActiva = hunter.getActiveWeapons().get(0);
        assertEquals("Ballesta", wActiva.getName());

        // Armaduras
        assertEquals(1, hunter.getArmors().size());
        Armor armor = hunter.getArmors().get(0);
        assertEquals("CotaMalla", armor.getName());
        assertEquals(20, armor.getDefenseModifier());
        assertEquals(5, armor.getAttackModifier());

        // Armadura activa
        Armor aActiva = hunter.getActiveArmor();
        assertEquals("CotaMalla", aActiva.getName());
        assertEquals(20, aActiva.getDefenseModifier());
        assertEquals(5, aActiva.getAttackModifier());

        // Fortalezas y debilidades
        assertEquals(1, hunter.getStrengths().size());
        assertEquals("Determinación", hunter.getStrengths().get(0).getName());
        assertEquals(40, hunter.getStrengths().get(0).getValue());

        assertEquals(1, hunter.getWeaknesses().size());
        assertEquals("Orgullo", hunter.getWeaknesses().get(0).getName());
        assertEquals(25, hunter.getWeaknesses().get(0).getValue());

        // Esbirros
        assertTrue(hunter.getMinions().isEmpty());
    }
    @Test
    public void testWerewolfReader() {
        String input = """
                NOMBRE HombreLobo
                DON RabiaFuria
                RABIA 7
                ATAQUE 5
                DEFENSA 3
                ARMAS 1
                NOMBRE Espada
                ATAQUE 10
                DEFENSA 2
                UNA_MANO true
                -
                ARMAS_ACTIVAS 1
                NOMBRE Garra
                ATAQUE 8
                DEFENSA 1
                UNA_MANO true
                -
                ARMADURAS 1
                NOMBRE PielGruesa
                DEFENSA 6
                ATAQUE 0
                -
                ARMADURA_ACTIVA PielGruesa
                DEFENSA 6
                ATAQUE 0
                -
                ORO 100
                SALUD 200
                PODER 20
                DEBILIDADES 1
                NOMBRE Plata
                VALOR 5
                FORTALEZAS 1
                NOMBRE Instinto
                VALOR 4
                ESBIRROS 0
                ========== FIN USUARIO ==========
                """;

        BufferedReader br = new BufferedReader(new StringReader(input));
        UserFileReader reader = new UserFileReader();

        Werewolf werewolf = reader.werewolfReader(br);

        assertNotNull(werewolf);
        assertEquals("LICANTROPO", werewolf.getType());
        assertEquals("HombreLobo", werewolf.getName());
        assertEquals(7, werewolf.getRage());
        assertEquals(100, werewolf.getGold());
        assertEquals(200, werewolf.getHealth());
        assertEquals(20, werewolf.getPower());

        assertNotNull(werewolf.getAbility());
        assertEquals("RabiaFuria", werewolf.getAbility().getName());
        assertEquals(5, werewolf.getAbility().getAttack());
        assertEquals(3, werewolf.getAbility().getDefense());

        List<Weapon> weapons = werewolf.getWeapons();
        assertEquals(1, weapons.size());
        assertEquals("Espada", weapons.get(0).getName());
        assertTrue(weapons.get(0).isSingleHand());

        List<Weapon> activeWeapons = werewolf.getActiveWeapons();
        assertEquals(1, activeWeapons.size());
        assertEquals("Garra", activeWeapons.get(0).getName());

        List<Armor> armors = werewolf.getArmors();
        assertEquals(1, armors.size());
        assertEquals("PielGruesa", armors.get(0).getName());

        assertNotNull(werewolf.getActiveArmor());
        assertEquals("PielGruesa", werewolf.getActiveArmor().getName());

        List<Weakness> weaknesses = werewolf.getWeaknesses();
        assertEquals(1, weaknesses.size());
        assertEquals("Plata", weaknesses.get(0).getName());

        List<Strength> strengths = werewolf.getStrengths();
        assertEquals(1, strengths.size());
        assertEquals("Instinto", strengths.get(0).getName());

        assertTrue(werewolf.getMinions().isEmpty());
    }
    @Test
    public void testMinionsFile_Human() throws Exception {
        String mockInput = """
                NUM-ESBIRROS 1
                TIPO HUMANO
                Nombre Juan
                HP 100
                LEALTAD ALTA
                """;

        BufferedReader br = new BufferedReader(new StringReader(mockInput));
        String linea = br.readLine(); // "1"
        String[] spaceBtwText = linea.split(" ");

        UserFileReader reader = new UserFileReader(); // o la clase que contiene minionsFile
        MinionsComposit minion = reader.minionsFile(linea, br, spaceBtwText);

        assertTrue(minion instanceof Human);
        Human human = (Human) minion;
        assertEquals("HUMANO", human.getType());
        assertEquals("Juan", human.getName());
        assertEquals(100, human.getHp());
        assertEquals(Human.Loyalty.ALTA, human.getLoyalty());
    }
}
