
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Entities.Administrator;
import Entities.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import System.*;
import java.nio.file.Files;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AdministratorTest {

    //Tests que comprueba que Name, Nick y Password se establecen correctamente.
    @Test
    public void setNameTest() {
        String name = "admin_name_test";
        Administrator admin = new Administrator();
        admin.setName(name);
        assertEquals(name, admin.getName(), "El nombre debe ser el establecido");
    }
    @Test
    public void setNickTest() {
        String nick = "admin_nick_test";
        Administrator admin = new Administrator();
        admin.setNick(nick);
        assertEquals(nick, admin.getNick(), "El nick debe ser el establecido");
    }
    @Test
    public void setPasswordTest() {
        String password = "My_Password";
        Administrator admin = new Administrator();
        admin.setPassword(password);
        assertEquals(password, admin.getPassword(), "La contraseña debe ser el establecida");
    }

    //Test sobre las contraseñas y sus restricciones.
    // Comprueba que contraseñas de longitud entre 8 y 12 no lancen excepción
    @Test
    public void validPassword_NoException() {
        // longitudes válidas: 8, 9, …, 12
        Administrator admin = new Administrator();
        assertDoesNotThrow(() -> admin.setPassword("Abcdef12"));
        assertEquals("Abcdef12", admin.getPassword());

        assertDoesNotThrow(() -> admin.setPassword("123456789012"));
        assertEquals("123456789012", admin.getPassword());
    }

    // Comprueba que una contraseña demasiado corta lance IllegalArgumentException
    @Test
    public void testPasswordTooShortThrows() { //Daba error el test al no comprobar el setter la longitud
        String tooShort = "Abc123";   // 6 chars
        Administrator admin = new Administrator();
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> admin.setPassword(tooShort),
                "Debe lanzar IllegalArgumentException para contraseñas < 8 caracteres"
        );
        assertEquals(
                "La contraseña debe tener entre 8 y 12 caracteres",
                ex.getMessage()
        );
    }

    // Comprueba que una contraseña demasiado larga lance IllegalArgumentException
    @Test
    public void testPasswordTooLongThrows() { //Daba error el test al no comprobar el setter la longitud
        String tooLong = "ABCDEFGHIJKLM";  // 13 chars
        Administrator admin = new Administrator();
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> admin.setPassword(tooLong),
                "Debe lanzar IllegalArgumentException para contraseñas > 12 caracteres"
        );
        assertEquals(
                "La contraseña debe tener entre 8 y 12 caracteres",
                ex.getMessage()
        );
    }

    // Comprueba cancelación: escribe algo distinto de "ELIMINAR"
    @Test
    public void testDeleteAdminAccount_ConfirmacionExitosa() throws IOException {
        // Configurar un administrador de prueba
        Administrator admin = new Administrator();
        admin.setNick("admin_test");
        admin.setPassword("admin123");
        new AdministratorFileWriter().adminRegister(admin);

        // Simular entrada de confirmación
        String input = "ELIMINAR\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainSystem system = new MainSystem();
        admin.deleteAdminAccount(admin, system);

        // Verificar eliminación
        ArrayList<Administrator> admins = new AdministratorFileReader().adminFileReader();
        assertFalse(admins.stream().anyMatch(a -> a.getNick().equals("admin_test")));
    }

    @Test
    public void testDeleteAdminAccount_CancelarOperacion() throws IOException {
        // Guardamos el System.in original para restaurarlo luego
        InputStream sysInBackup = System.in;

        try {
            // Configurar administrador con nick y contraseña válida
            Administrator admin = new Administrator();
            admin.setNick("admin_test");
            admin.setPassword("admin1234");  // ¡8 caracteres válidos!
            new AdministratorFileWriter().adminRegister(admin);

            // Simular entrada de cancelación (simplemente un ENTER)
            ByteArrayInputStream in = new ByteArrayInputStream("\n".getBytes());
            System.setIn(in);

            // MainSystem “mockeado” para que selector() no haga nada y no bloquee
            MainSystem system = new MainSystem() {
                @Override
                public void selector() {
                    // no-op
                }
            };

            // Ejecución: como la entrada no es "ELIMINAR", debe ir por el branch de cancelar
            admin.deleteAdminAccount(admin, system);

            // Verificar que sigue en el archivo
            ArrayList<Administrator> admins = new AdministratorFileReader().adminFileReader();
            assertTrue(
                    admins.stream().anyMatch(a -> a.getNick().equals("admin_test")),
                    "El admin debería seguir presente tras cancelar la eliminación"
            );
        } finally {
            // Restauramos System.in original para no afectar otros tests
            System.setIn(sysInBackup);
        }
    }

    @Test
    public void testDeleteAdminAccount_AdminNoExiste() throws IOException {
        // Administrador que no existe
        Administrator nonExistingAdmin = new Administrator();
        nonExistingAdmin.setNick("fantasma");

        // Simular confirmación
        String input = "ELIMINAR\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Ejecutar con admin no existente
        Administrator admin = new Administrator();
        MainSystem system = new MainSystem();
        admin.deleteAdminAccount(nonExistingAdmin, system);

        // Verificar que no hay cambios (asumiendo archivo vacío)
        ArrayList<Administrator> admins = new AdministratorFileReader().adminFileReader();
        assertTrue(admins.isEmpty());
    }

    private static final String USER_FILE_PATH = "Fase-3-MP/src/Files/UserRegister.txt";
    private final InputStream sysInBackup = System.in;
    private final PrintStream sysOutBackup = System.out;

    @BeforeEach
    public void setUp() throws IOException {
        // Redirigimos stdout para no ensuciar la consola durante el test
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        // Preparamos el fichero de usuarios con un único cliente:
        // Formato: nick;campo2;campo3;character=null
        String line = "user1;foo;bar;null";
        Path path = Paths.get(USER_FILE_PATH);
        Files.createDirectories(path.getParent());
        Files.write(path, List.of(line), StandardCharsets.UTF_8);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Restauramos System.in y System.out
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
        // Limpiamos el fichero de prueba
        Files.deleteIfExists(Paths.get(USER_FILE_PATH));
    }

    @Test
    public void testModifyCharacter_LeerTxt_YSalirSinCambios() throws IOException {
        // Simulamos la entrada:
        // 1) "user1" + ENTER → selecciona el nick
        // 2) "13"    + ENTER → opción de "guardar y salir" sin cambios
        String input = String.join(System.lineSeparator(), "user1", "13");
        System.setIn(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8)));

        // Ejecutamos el método bajo prueba
        Administrator admin = new Administrator();
        admin.modifyCharacter();

        // Leemos de nuevo el fichero para comprobar que no ha cambiado
        Path path = Paths.get(USER_FILE_PATH);
        List<String> linesAfter = Files.readAllLines(path, StandardCharsets.UTF_8);

        assertEquals(1, linesAfter.size(), "Debe seguir habiendo exactamente una línea en el fichero");
        assertEquals("user1;foo;bar;null", linesAfter.get(0), "El contenido de la línea no debe modificarse");
    }
    private static final String CHALLENGE_FILE_PATH  = "Fase-3-MP/src/Files/ChallengeRegister.txt";

    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUp_2() throws IOException {
        // Preparamos un fichero de usuarios (aunque no se usa en este flujo)
        Files.createDirectories(Paths.get(USER_FILE_PATH).getParent());
        Files.write(Paths.get(USER_FILE_PATH),
                // un solo usuario válido
                List.of("user1;foo;bar;null"),
                StandardCharsets.UTF_8);

        // Creamos el fichero de desafíos con todos ya validados:
        Files.createDirectories(Paths.get(CHALLENGE_FILE_PATH).getParent());
        Files.write(Paths.get(CHALLENGE_FILE_PATH),
                // aquí cada línea representaría un Challenge serializado con isValidated=true
                List.of("challenge1;user1;user2;true;"),
                StandardCharsets.UTF_8);

        // Redirigimos stdout para capturar el mensaje en pantalla
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void tearDown_2() throws IOException {
        // Restauramos System.in y System.out
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
        // Limpiamos los ficheros de prueba
        Files.deleteIfExists(Paths.get(USER_FILE_PATH));
        Files.deleteIfExists(Paths.get(CHALLENGE_FILE_PATH));
    }

    @Test
    public void testValidatingChallenge_NoPending() throws IOException {
        // No se necesita entrada por teclado, pero aseguramos un stream vacío
        System.setIn(new ByteArrayInputStream(new byte[0]));

        // Ejecutamos el método bajo prueba
        Administrator system = new Administrator();
        system.validatingChallenge();

        // Verificamos que se imprimió el mensaje de "no hay desafíos para validar"
        String salida = testOut.toString(StandardCharsets.UTF_8);
        assertTrue(salida.contains("noDesafiosParaValidar"),
                "Se esperaba el mensaje de que no hay desafíos pendientes");

        // Comprobamos que el fichero de desafíos no ha cambiado (sigue conteniendo la misma línea)
        List<String> after = Files.readAllLines(Paths.get(CHALLENGE_FILE_PATH), StandardCharsets.UTF_8);
        assertEquals(1, after.size(), "El fichero debe seguir teniendo un único desafío");
        assertTrue(after.get(0).startsWith("challenge1;user1;user2;true;"),
                "El contenido del desafío debe permanecer intacto");
    }

    private static final String BAN_FILE_PATH = "Fase-3-MP/src/Files/BanRegister.txt";
    @Test
    public void testUnbanUser_Successful() throws IOException {
        // Seleccionamos el primer (y único) baneado: índice 1, y confirmamos DESBANEAR
        String simulatedInput = String.join(System.lineSeparator(),
                "1",         // elegir user2
                "DESBANEAR"  // confirmar
        );
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));

        // Ejecutamos el método bajo prueba
        Administrator system = new Administrator();
        system.unbanUser();

        // Tras ejecutar, el fichero de baneados debe estar vacío
        List<String> bansAfter = Files.readAllLines(Paths.get(BAN_FILE_PATH), StandardCharsets.UTF_8);
        assertTrue(bansAfter.isEmpty(), "El usuario debe haber sido removido del fichero de baneados");
    }
    @Test
    void testBanUser() {
        // Crear objetos mock
        UserFileReader userFileReader = mock(UserFileReader.class);
        BanFileReader banFileReader = mock(BanFileReader.class);
        BanFileWriter banWriter = mock(BanFileWriter.class);
        Terminal terminal = mock(Terminal.class);
        Scanner sc = mock(Scanner.class);

        // Crear usuarios ficticios
        Client user1 = new Client();
        user1.setNick("user1");
        Client user2 = new Client();
        user2.setNick("user2");

        ArrayList<Client> allUsers = new ArrayList<>(Arrays.asList(user1, user2));
        ArrayList<Client> bannedClients = new ArrayList<>();

        // Configurar los mocks
        when(userFileReader.userFileReader()).thenReturn(allUsers);
        when(banFileReader.readBannedUsers()).thenReturn(bannedClients);

        // Instanciar la clase que contiene el método banUser
        Administrator yourClass = new Administrator();

        // Suponemos que el usuario selecciona el primer usuario para banear
        when(sc.nextInt()).thenReturn(1);  // Selecciona el primer usuario
        when(sc.nextLine()).thenReturn("BANEAR");  // Confirma el baneo

        // Ejecutar el método banUser
        yourClass.banUser(user1);

        // Verificar que se haya llamado al método adecuado
        verify(banWriter).banUser(user1);
        verify(terminal).bannigUser();
        verify(terminal).banned(user1.getNick());
    }


}
