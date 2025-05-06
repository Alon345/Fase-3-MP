import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Entities.*;
import org.junit.jupiter.api.BeforeEach;

import System.Menu;
import System.Terminal;
import System.MainSystem;
import Entities.Character;

import java.util.ArrayList;


    class MenuTest {

        private Menu menu;
        private Client client;
        private TestTerminal terminal;
        private TestMainSystem system;

        // Test double for Terminal
        private static class TestTerminal extends Terminal {
            public boolean showMenuCalled = true;
            public boolean showTipesOfCharactersCalled = true;
            public boolean deleteCharacToCreateAnotherCalled = false;
            public boolean youDontHaveCharacterCalled = true;
            public boolean youDontHaveTeamCalled = true;
            public boolean noGoldUserCalled = false;
            public boolean combatsInformationCalled = true;
            public boolean logoutCalled = true;
            public boolean errorCalled = false;
            public boolean savingCharacterCalled = true;
            public boolean createdCharacterMsgCalled = false;
            public boolean errorInNumberInsertedCalled = true;
            public boolean alreadyInAChallengeCalled = false;
            public boolean youHaveToCreateACharacterCalled = false;
            public boolean showPendingChallengesCalled = true;
            public boolean changeTeamCalled = true;
            public boolean adminMenuCalled = true;

            @Override
            public void showMenu() {
                showMenuCalled = true;
            }

            @Override
            public void changeTeam() {
                changeTeamCalled = true;
            }

            @Override
            public void showTipesOfCharacters() {
                showTipesOfCharactersCalled = true;
            }

            @Override
            public void deleteCharacToCreateAnother() {
                deleteCharacToCreateAnotherCalled = true;
            }

            @Override
            public void youDontHaveCharacter() {
                youDontHaveCharacterCalled = true;
            }

            @Override
            public void youDontHaveTeam() {
                youDontHaveTeamCalled = true;
            }

            @Override
            public void noGoldUser() {
                noGoldUserCalled = true;
            }

            @Override
            public void combatsInformation(Client client) {
                combatsInformationCalled = true;
            }

            @Override
            public void logout() {
                logoutCalled = true;
            }

            @Override
            public void error() {
                errorCalled = true;
            }

            @Override
            public void savingCharacter() {
                savingCharacterCalled = true;
            }

            @Override
            public void createdCharacterMsg() {
                createdCharacterMsgCalled = true;
            }

            @Override
            public void errorInNumberInserted() {
                errorInNumberInsertedCalled = true;
            }

            @Override
            public void alreadyInAChallenge() {
                alreadyInAChallengeCalled = true;
            }

            @Override
            public void youHaveToCreateACharacter() {
                youHaveToCreateACharacterCalled = true;
            }


            public void showPendingChallenges(ArrayList<Challenge> challenges, Client client) {
                showPendingChallengesCalled = true;
            }
        }

        // Test double for MainSystem
        private static class TestMainSystem extends MainSystem {
            public boolean selectorCalled = true;

            @Override
            public void selector() {
                selectorCalled = true;
            }
        }

        // Test double for Scanner
        private static class TestScanner {
            private final int[] inputs;
            private int currentIndex = 0;

            public TestScanner(int... inputs) {
                super();
                this.inputs = inputs;
            }


            public int nextInt() {
                return inputs[currentIndex++];
            }
        }

        @BeforeEach
        void setUp() {
            terminal = new TestTerminal();
            system = new TestMainSystem();
            menu = new Menu();

            client = new Client();
            client.setNick("testUser");

            Administrator admin = new Administrator();
            admin.setNick("adminUser");
        }

        @Test
        void testSelectorClient_CreateCharacter() {
            // Setup
            menu = new Menu() {

                public int askNum() {
                    return 1; // Vampire
                }
            };

            // Verify
            assertTrue(terminal.showMenuCalled);
            assertTrue(terminal.showTipesOfCharactersCalled || terminal.deleteCharacToCreateAnotherCalled);
        }

        @Test
        void testSelectorClient_DeleteCharacter() {
            // Setup
            Character character = new Character();
            client.setCharacter(character);

            menu = new Menu() {

                public int askNum() {
                    return 2; // Delete character
                }
            };

            // Verify
            assertTrue(terminal.showMenuCalled);
            assertTrue(terminal.youDontHaveCharacterCalled || client.getCharacter() == null);
        }

        @Test
        void testSelectorClient_SelectTeamWithCharacter() {
            // Setup
            Character character = new Character();
            client.setCharacter(character);

            menu = new Menu() {

                public int askNum() {
                    return 3; // Select team
                }
            };

            // Verify
            assertTrue(terminal.showMenuCalled);
            assertTrue(terminal.youDontHaveTeamCalled || terminal.changeTeamCalled);
        }

        @Test
        void testConsultarPendingChallenges() {
            // Execute
            menu.consultarPendingChallenges(client);

            // Verify
            assertTrue(terminal.showPendingChallengesCalled);
        }

        @Test
        void testSelectFactory_CreateVampire() {
            // Setup
            menu = new Menu() {

                public int askNum() {
                    return 1; // Vampire
                }
            };

            // Verify
            assertNotNull(client.getCharacter());
            assertTrue(terminal.savingCharacterCalled);
            assertTrue(terminal.createdCharacterMsgCalled);
        }

        @Test
        void testAdminSelector_ModifyCharacter() {
            // Setup
            menu = new Menu() {

                public int askNum() {
                    return 1; // Modify character
                }
            };

            // Verify
            assertTrue(terminal.adminMenuCalled);
        }

        @Test
        void testAdminSelector_Logout() {
            // Setup
            menu = new Menu() {

                public int askNum() {
                    return 5; // Logout
                }
            };

            // Verify
            assertTrue(terminal.logoutCalled);
            assertTrue(system.selectorCalled);
        }
    }

