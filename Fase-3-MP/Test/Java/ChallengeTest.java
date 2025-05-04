package Test.Java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.Challenge;
import Entities.Client;
import Entities.Character;
import Entities.Modifier;

import java.util.ArrayList;
import java.util.Date;

public class ChallengeTest {

    private Challenge challenge;
    private Client mockClient;

    @BeforeEach
    public void setUp() {
        // Crear un cliente de prueba
        mockClient = new Client();
        mockClient.setNick("tester");
        Character character = new Character();
        character.setGold(100);
        mockClient.setCharacter(character);

        challenge = new Challenge();
    }

    @Test
    public void testSetGetRival() {
        Client rival = new Client();
        rival.setNick("rival");
        challenge.setRival(rival);
        assertEquals(rival, challenge.getRival(), "El getter de rival debe devolver el valor establecido");
    }

    @Test
    public void testSetGetChallenger() {
        challenge.setChallenger(mockClient);
        assertEquals(mockClient, challenge.getChallenger(), "El getter de challenger debe devolver el valor establecido");
    }

    @Test
    public void testSetGetGold() {
        challenge.setGold(50);
        assertEquals(50, challenge.getGold(), "El getter de gold debe devolver el valor establecido");
    }

    @Test
    public void testSetGetModifiers() {
        ArrayList<Modifier> mods = new ArrayList<>();
        Modifier modifier = new Modifier();
        modifier.setName("M1");
        mods.add(modifier);
        challenge.setModifiers(mods);
        assertEquals(mods, challenge.getModifiers(), "El getter de modifiers debe devolver la lista establecida");
    }

    @Test
    public void testSetIsValidated() {
        challenge.setValidated(true);
        assertTrue(challenge.isValidated(), "El método isValidated debe reflejar el estado establecido");
    }

    @Test
    public void testSetGetRegisterAndDate() {
        String reg = "12345";
        Date now = new Date();
        challenge.setRegister(reg);
        challenge.setDate(now);

        assertEquals(reg, challenge.getRegister(), "El getter de register debe devolver el valor establecido");
        assertEquals(now, challenge.getDate(), "El getter de date debe devolver la fecha establecida");
    }

    @Test
    public void testGenerateRegisterNumberFormat() {
        String reg1 = challenge.generateRegisterNumber();
        String reg2 = challenge.generateRegisterNumber();
        assertNotNull(reg1, "El número de registro no debe ser null");
        assertTrue(reg1.matches("\\d+"), "El número de registro debe contener solo dígitos");
        // Comprobar que dos invocaciones consecutivas son diferentes
        assertNotEquals(reg1, reg2, "Cada registro generado debe ser único");
    }

    // Nota: createChallenge() interactúa con entradas de usuario y sistema de archivos,
    // por lo que se recomienda refactorizar o usar mocks para probar ese método.
}