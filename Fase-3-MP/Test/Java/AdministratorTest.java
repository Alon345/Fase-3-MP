
import static org.junit.jupiter.api.Assertions.*;

import Entities.Administrator;
import Entities.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import System.*;

import java.io.*;
import java.util.ArrayList;

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
        // Configurar administrador
        Administrator admin = new Administrator();
        admin.setNick("admin_test");
        new AdministratorFileWriter().adminRegister(admin);

        // Simular entrada de cancelación
        String input = "cancelar\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainSystem system = new MainSystem();
        admin.deleteAdminAccount(admin, system);

        // Verificar que sigue en el archivo
        ArrayList<Administrator> admins = new AdministratorFileReader().adminFileReader();
        assertTrue(admins.stream().anyMatch(a -> a.getNick().equals("admin_test")));
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
}
