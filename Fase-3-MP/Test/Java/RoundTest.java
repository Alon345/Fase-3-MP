import static org.junit.jupiter.api.Assertions.*;

import Entities.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import Entities.*;

public class RoundTest {

    private Client challenger;
    private Client rival;
    private Round round;

    @BeforeEach
    public void setUp() {
        round = new Round();
        challenger = new Client();
        rival = new Client();
    }

    @Test
    public void testStartRound_BasicCombat_VampireVsWerewolf() {
        // Configurar personajes
        Character vampire = new Vampire();
        vampire.setType("VAMPIRO");
        vampire.setPower(10);
        vampire.setHealth(100);
        challenger.setCharacter(vampire);

        Character werewolf = new Werewolf();
        werewolf.setType("LICANTROPO");
        werewolf.setPower(8);
        werewolf.setHealth(100);
        rival.setCharacter(werewolf);

        ArrayList<Modifier> modifiers = new ArrayList<>();

        // Ejecutar la ronda
        int hpChallengerStart = 100;
        int hpRivalStart = 100;
        boolean result = round.startRound(hpChallengerStart, hpRivalStart, challenger, rival, modifiers);

        // Verificar que el HP ha cambiado (indicando que se ha producido combate)
        assertNotEquals(hpChallengerStart, round.getHpChallengerEnd(),
                "El HP del challenger debe cambiar tras la ronda");
        assertNotEquals(hpRivalStart, round.getHpRivalEnd(),
                "El HP del rival debe cambiar tras la ronda");

        // Verificar que el método indica si alguien fue derrotado (HP = 0)
        assertEquals(result, round.getHpChallengerEnd() == 0 || round.getHpRivalEnd() == 0,
                "El booleano de retorno debe indicar si alguien ha sido derrotado");
    }

    @Test
    public void testStartRound_WithModifiersAffectingStrengthsWeaknesses() {
        // Preparar personajes con fortalezas y debilidades
        Character character1 = new Character();
        character1.setType("GENÉRICO");
        character1.setPower(10);
        character1.setHealth(100);

        Strength strength = new Strength();
        strength.setName("Fuerte");
        strength.setValue(5);
        ArrayList<Strength> strengths = new ArrayList<>();
        strengths.add(strength);
        character1.setStrengths(strengths);

        Weakness weakness = new Weakness();
        weakness.setName("Débil");
        weakness.setValue(3);
        ArrayList<Weakness> weaknesses = new ArrayList<>();
        weaknesses.add(weakness);
        character1.setWeaknesses(weaknesses);

        challenger.setCharacter(character1);

        Character character2 = new Character();
        character2.setType("GENÉRICO");
        character2.setPower(10);
        character2.setHealth(100);
        rival.setCharacter(character2);

        // Modificadores activos en la ronda
        ArrayList<Modifier> modifiers = new ArrayList<>();
        Modifier roundMod1 = new Modifier();
        roundMod1.setName("Fuerte");
        modifiers.add(roundMod1);

        Modifier roundMod2 = new Modifier();
        roundMod2.setName("Débil");
        modifiers.add(roundMod2);

        // Ejecutar
        round.startRound(100, 100, challenger, rival, modifiers);

        // Verificar que no se han producido errores y los HP se han actualizado
        assertTrue(round.getHpChallengerEnd() > 0, "HP del challenger debe seguir siendo positivo");
        assertTrue(round.getHpRivalEnd() > 0, "HP del rival debe seguir siendo positivo");
    }

    @Test
    public void testGetHp_NoDamageWhenDefenseEqualsAttack() {
        Client mockClient1 = new Client();
        mockClient1.setCharacter(new Character());
        Client mockClient2 = new Client();
        mockClient2.setCharacter(new Character());

        int attack = 30;
        int defense = 30;
        int hp = 100;

        int result = round.getHp(attack, defense, hp, mockClient1, mockClient2);

        assertEquals(100, result, "Si ataque y defensa son iguales, el HP no debe reducirse");
    }

    @Test
    public void testGetHp_NoNegativeHp() {
        int attack = 999;
        int defense = 0;
        int hp = 50;

        Client mockClient1 = new Client();
        mockClient1.setCharacter(new Character());
        Client mockClient2 = new Client();
        mockClient2.setCharacter(new Character());

        int result = round.getHp(attack, defense, hp, mockClient1, mockClient2);

        assertTrue(result >= 0, "El HP nunca debe ser negativo");
    }

    @Test
    public void testStartRound_ZeroInitialHp() {
        Character c1 = new Character();
        c1.setPower(10);
        c1.setHealth(0);
        challenger.setCharacter(c1);

        Character c2 = new Character();
        c2.setPower(10);
        c2.setHealth(0);
        rival.setCharacter(c2);

        ArrayList<Modifier> modifiers = new ArrayList<>();
        boolean result = round.startRound(0, 0, challenger, rival, modifiers);

        assertEquals(0, round.getHpChallengerEnd(), "HP final debe ser 0 si comienza en 0");
        assertEquals(0, round.getHpRivalEnd(), "HP final debe ser 0 si comienza en 0");
        assertTrue(result, "Debe indicar que alguien ha sido derrotado si ambos empiezan con 0");
    }
}
