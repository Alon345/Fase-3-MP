package Test.Java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import Entities.Client;
import Entities.Modifier;
import Entities.Round;
import Entities.Character;
import Entities.Vampire;
import Entities.Werewolf;

public class RoundTest {

    @Test
    public void testStartRound() {
        // Configurar datos de prueba
        Client challenger = new Client();
        Client rival = new Client();

        Entities.Character vampire = new Vampire();
        vampire.setType("VAMPIRO");
        vampire.setPower(10);
        vampire.setHealth(100);

        Entities.Character werewolf = new Werewolf();
        werewolf.setType("LICANTROPO");
        werewolf.setPower(8);
        werewolf.setHealth(100);

        challenger.setCharacter(vampire);
        rival.setCharacter(werewolf);

        ArrayList<Modifier> modifiers = new ArrayList<>();

        Round round = new Round();
        int hpChallenger = 100;
        int hpRival = 100;

        // Ejecutar el método
        boolean result = round.startRound(hpChallenger, hpRival, challenger, rival, modifiers);

        // Verificar resultados
        assertTrue(result || round.getHpChallengerEnd() > 0 || round.getHpRivalEnd() > 0,
                "La ronda debe finalizar correctamente con HP actualizado");
        assertNotEquals(hpChallenger, round.getHpChallengerEnd(), "El HP del desafiante debe cambiar tras la ronda");
        assertNotEquals(hpRival, round.getHpRivalEnd(), "El HP del rival debe cambiar tras la ronda");
    }

    @Test
    public void testGetHp() {
        // Configurar datos de prueba
        Client challenger = new Client();
        Client rival = new Client();

        challenger.setCharacter(new Entities.Character());
        rival.setCharacter(new Entities.Character());

        Round round = new Round();
        int ataque = 50;
        int defensa = 30;
        int hp = 100;

        // Ejecutar el método
        int result = round.getHp(ataque, defensa, hp, challenger, rival);

        // Verificar resultados
        assertEquals(80, result, "El HP debe reducirse correctamente según el ataque y la defensa");
    }
}