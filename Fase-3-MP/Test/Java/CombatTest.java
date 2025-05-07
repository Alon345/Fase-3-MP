
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.Combat;
import Entities.Client;
import Entities.Modifier;
import Entities.Round;
import Entities.Ability;

import java.util.ArrayList;
import java.util.Date;

public class CombatTest {

    private Combat combat;
    private Client challenger;
    private Client rival;

    @BeforeEach
    public void setUp() {
        combat = new Combat();
        challenger = new Client();
        rival = new Client();

        challenger.setNick("Challenger");
        rival.setNick("Rival");
    }

    @Test
    public void testInitializeCombat() {
        Date date = new Date();
        ArrayList<Modifier> modifiers = new ArrayList<>();
        String register = "12345";

        combat.initializeCombat(challenger, rival, date, 100, modifiers, register);

        assertEquals(challenger, combat.getChallenger(), "El challenger debe ser el establecido");
        assertEquals(rival, combat.getRival(), "El rival debe ser el establecido");
        assertEquals(date, combat.getDate(), "La fecha debe ser la establecida");
        assertEquals(100, combat.getGoldBet(), "El oro apostado debe ser el establecido");
        assertEquals(modifiers, combat.getModifiers(), "Los modificadores deben ser los establecidos");
        assertEquals(register, combat.getRegister(), "El registro debe ser el establecido");
        assertFalse(combat.isSeen(), "El combate no debe estar marcado como visto inicialmente");
    }

    @Test
    public void testSetGetRounds() {
        ArrayList<Round> rounds = new ArrayList<>();
        combat.setRounds(rounds);
        assertEquals(rounds, combat.getRounds(), "El getter de rounds debe devolver la lista establecida");
    }

    @Test
    public void testSetGetWinner() {
        combat.setWinner(challenger);
        assertEquals(challenger, combat.getWinner(), "El ganador debe ser el challenger establecido");
    }

    @Test
    public void testSetGetGold() {
        combat.setGold(200);
        assertEquals(200, combat.getGold(), "El oro debe ser el valor establecido");
    }

    @Test
    public void testSetGetStatus() {
        combat.setStatus("En progreso");
        assertEquals("En progreso", combat.getStatus(), "El estado debe ser el establecido");
    }

}