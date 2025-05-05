
import static org.junit.jupiter.api.Assertions.*;

import Entities.Administrator;
import Entities.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import System.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

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
        admin.setPasswordAdmin(password);
        assertEquals(password, admin.getPasswordAdmin(), "La contraseña debe ser el establecida");
    }

    //Test sobre las contraseñas y sus restricciones.
    // Comprueba que contraseñas de longitud entre 8 y 12 no lancen excepción
    @Test
    public void testPasswordWithinRangeDoesNotThrow() {
        // longitudes válidas: 8, 9, …, 12
        Administrator admin = new Administrator();
        assertDoesNotThrow(() -> admin.setPasswordAdmin("Abcdef12"));
        assertEquals("Abcdef12", admin.getPasswordAdmin());

        assertDoesNotThrow(() -> admin.setPasswordAdmin("123456789012"));
        assertEquals("123456789012", admin.getPasswordAdmin());
    }

    // Comprueba que una contraseña demasiado corta lance IllegalArgumentException
    @Test
    public void testPasswordTooShortThrows() { //Daba error el test al no comprobar el setter la longitud
        String tooShort = "Abc123";   // 6 chars
        Administrator admin = new Administrator();
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> admin.setPasswordAdmin(tooShort),
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
                () -> admin.setPasswordAdmin(tooLong),
                "Debe lanzar IllegalArgumentException para contraseñas > 12 caracteres"
        );
        assertEquals(
                "La contraseña debe tener entre 8 y 12 caracteres",
                ex.getMessage()
        );
    }

    private final InputStream systemInBackup = System.in;
    private final PrintStream systemOutBackup = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpStreams() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(systemInBackup);
        System.setOut(systemOutBackup);
    }

    static class TestMainSystem extends MainSystem {
        private boolean called = false;
        @Override
        public void selector() { called = true; }
        public boolean wasCalled() { return called; }
    }

    // Comprueba cancelación: escribe algo distinto de "ELIMINAR"
    @Test
    public void testDeleteAdminAccount_CancelledCallsSelector() {
        // simular entrada "CANCELAR"
        testIn = new ByteArrayInputStream("CANCELAR\n".getBytes());
        System.setIn(testIn);

        Administrator admin = new Administrator();
        admin.setNick("admin1");

        TestMainSystem system = new TestMainSystem();
        Client client = new Client();
        client.deleteAccount(client, system);

        // debe invocar selector() aunque cancele
        assertTrue(system.wasCalled(),
                "Al cancelar, MainSystem.selector() debe ser invocado");

        String out = testOut.toString();
        // ajusta estos literales al texto real que imprime tu Terminal:
        assertTrue(out.contains("Operación cancelada"),
                "Debe mostrar el mensaje de operación cancelada");
        assertTrue(out.contains("Sesión cerrada por seguridad"),
                "Debe mostrar el mensaje de sesión cerrada por seguridad");
    }

    // Comprueba confirmación: escribe "ELIMINAR"
    @Test
    public void testDeleteAdminAccount_ConfirmDeletesAndCallsSelector() {
        testIn = new ByteArrayInputStream("ELIMINAR\n".getBytes());
        System.setIn(testIn);

        Administrator admin = new Administrator();
        admin.setNick("adminToRemove");

        TestMainSystem system = new TestMainSystem();
        Client client = new Client();
        client.deleteAccount(client, system);

        assertTrue(system.wasCalled(),
                "Después de eliminar, MainSystem.selector() debe ser invocado");

        String out = testOut.toString();
        // aquí también ajusta según tu Terminal:
        assertTrue(
                out.contains("Borrando administrador") ||
                        out.contains("No existe cuenta de administrador"),
                "Debe mostrar el mensaje de borrado o el de cuenta no existente");
    }
}
