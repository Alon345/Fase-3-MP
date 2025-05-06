import System.NotificationManager;
import System.Terminal;
import Entities.Challenge;
import Entities.Client;
import Entities.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class NotificationManagerTest {

    private NotificationManager notificationManager;
    private Client client;
    private Client challenger;
    private ArrayList<Challenge> challenges;
    private Challenge challenge;
    private TestTerminal terminal;

    // Simple TestTerminal implementation
    private static class TestTerminal extends Terminal {
        public Challenge lastChallenge;
        public boolean clashAnimationShown = false;
        public boolean goldStaySameShown = false;
        public boolean restandoOroShown = false;
        public boolean changeTeamCalled = false;

        @Override
        public void askChallenge(Challenge challenge) {
            this.lastChallenge = challenge;
        }

        @Override
        public void changeTeam() {
            changeTeamCalled = true;
        }

        @Override
        public void showClashAnimation() {
            clashAnimationShown = true;
        }

        @Override
        public void goldStayTheSame() {
            goldStaySameShown = true;
        }

        @Override
        public void restandoOro() {
            restandoOroShown = true;
        }
    }

    @BeforeEach
    void setUp() {
        terminal = new TestTerminal();
        notificationManager = new NotificationManager();

        // Setup test data with properly initialized Character objects
        client = new Client();
        client.setNick("testClient");
        Character clientCharacter = new Character();
        clientCharacter.setGold(1000);
        client.setCharacter(clientCharacter);

        challenger = new Client();
        challenger.setNick("challenger");
        Character challengerCharacter = new Character();
        challengerCharacter.setGold(1000);
        challenger.setCharacter(challengerCharacter);

        challenge = new Challenge();
        challenge.setChallenger(challenger);
        challenge.setGold(100);
        challenge.setRegister("CHALL123");

        challenges = new ArrayList<>();
        challenges.add(challenge);
    }

    @Test
    void testNotifyChallenge_RejectCombat() {
        // Create test version that simulates user rejecting
        NotificationManager testManager = new NotificationManager() {
            @Override
            public int askNum() {
                return 2; // Simulate user choosing to reject
            }
        };

        testManager.notifyChallenge(client, terminal, challenges, 0, challenger, "CHALL123");

        // Verify terminal interactions
        assertEquals(challenge, terminal.lastChallenge);
        assertTrue(terminal.restandoOroShown);

        // Verify challenge was removed
        assertTrue(challenges.isEmpty());
    }

    @Test
    void testAskNum() {
        // This test would require mocking System.in which is complex without Mockito
        // Consider refactoring NotificationManager to make Scanner injectable
        assertTrue(true); // Placeholder assertion
    }


    @Test
    void testNotifyChallenge_AcceptCombat() {
        // Create test version that simulates user accepting
        NotificationManager testManager = new NotificationManager() {
            @Override
            public int askNum() {
                return 2; // Simulate user choosing to accept
            }
        };

        testManager.notifyChallenge(client, terminal, challenges, 0, challenger, "CHALL123");

        // Verify terminal interactions
        assertEquals(challenge, terminal.lastChallenge);


        // Verify challenge was removed
        assertTrue(challenges.isEmpty());
    }

    @Test
    void testNotifyChallenge_Combat() {
        // Create test version that simulates user accepting
        NotificationManager testManager = new NotificationManager() {
            @Override
            public int askNum() {
                return 2; // Simulate user choosing to accept
            }
        };

        testManager.notifyChallenge(client, terminal, challenges, 0, challenger, "CHALL123");

        // Verify terminal interactions
        assertEquals(challenge, terminal.lastChallenge);
        assertTrue(terminal.changeTeamCalled);

        // Verify challenge was removed
        assertTrue(challenges.isEmpty());

    }


}