
import Entities.*;
import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import Entities.*;
import System.*;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.MockedStatic;

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
        String type = "VAMPIRO";
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

    @Mock
    private Terminal mockedTerminal; // Mock del Terminal

    // Ruta al archivo de usuarios (puede ajustarse según tu implementación)
    private static final String USER_FILE_PATH = "ruta/a/usuarios.txt";

   /** @Test
    public void testGlobalRanking_SortsEntriesDescendingWithNullAsZero() throws Exception {
        // 1. Configurar mocks para Terminal y UserFileReader
        mockStatic(UserFileReader.class);
        whenNew(Terminal.class).withNoArguments().thenReturn(mockedTerminal);

        // 2. Crear datos de prueba desordenados, incluyendo un null
        List<Map.Entry<String, Integer>> mockEntries = new ArrayList<>();
        mockEntries.add(new AbstractMap.SimpleEntry<>("User2", null)); // Equivalente a 0
        mockEntries.add(new AbstractMap.SimpleEntry<>("User3", 45));    // Máximo oro
        mockEntries.add(new AbstractMap.SimpleEntry<>("User1", 30));

        // 3. Simular que UserFileReader retorna esta lista
        when(UserFileReader.goldReaderForRanking(USER_FILE_PATH)).thenReturn(mockEntries);

        // 4. Ejecutar el método a testear
        Client ranking = new Client(); // Clase que contiene globalRanking()
        ranking.globalRanking();

        // 5. Verificar que se muestra el mensaje de ranking
        verify(mockedTerminal).rankingMessage();

        // 6. Capturar la lista ordenada enviada a Terminal
        ArgumentCaptor<List<Map.Entry<String, Integer>>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockedTerminal).showGoldRankingSimple(captor.capture());

        // 7. Verificar el orden correcto
        List<Map.Entry<String, Integer>> sortedList = captor.getValue();

        // Comprobar el orden descendente (45, 30, 0)
        assertEquals("User3", sortedList.get(0).getKey());
        assertEquals(45, (int) sortedList.get(0).getValue());

        assertEquals("User1", sortedList.get(1).getKey());
        assertEquals(30, (int) sortedList.get(1).getValue());

        assertEquals("User2", sortedList.get(2).getKey());
        assertNull(sortedList.get(2).getValue()); // Valor original sigue siendo null
    }**/

    @Test
    public void testGlobalRanking_EmptyList() throws Exception {
        List<Map.Entry<String, Integer>> emptyList = new ArrayList<>();

        try (MockedStatic<UserFileReader> userFileReaderMock = mockStatic(UserFileReader.class)) {
            userFileReaderMock.when(() -> UserFileReader.goldReaderForRanking(USER_FILE_PATH))
                    .thenReturn(emptyList);

            try (MockedConstruction<Terminal> mockedTerminal = mockConstruction(Terminal.class,
                    (mock, context) -> {
                        doNothing().when(mock).showGoldRankingSimple(emptyList);
                    })) {

                Client ranking = new Client();
                ranking.globalRanking();

                Terminal terminalInstance = mockedTerminal.constructed().get(0);
                verify(terminalInstance).showGoldRankingSimple(emptyList);
            }
        }
    }

   /** @Test
    public void testGlobalRanking_AllNullEntries() throws Exception {
        mockStatic(UserFileReader.class);
        whenNew(Terminal.class).withNoArguments().thenReturn(mockedTerminal);

        List<Map.Entry<String, Integer>> nullEntries = new ArrayList<>();
        nullEntries.add(new AbstractMap.SimpleEntry<>("UserA", null));
        nullEntries.add(new AbstractMap.SimpleEntry<>("UserB", null));

        when(UserFileReader.goldReaderForRanking(USER_FILE_PATH)).thenReturn(nullEntries);

        Client ranking = new Client();
        ranking.globalRanking();

        ArgumentCaptor<List<Map.Entry<String, Integer>>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockedTerminal).showGoldRankingSimple(captor.capture());

        List<Map.Entry<String, Integer>> sortedList = captor.getValue();
        assertEquals(2, sortedList.size()); // Orden no cambia (todos son 0)
    }**/

}