import Entities.Administrator;
import Entities.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import System.*;

public class MainSystemTest {

    @Test
    public void testRegisterUser_ClientSuccess() throws IOException {
        String input = "Juan\njuanito\nP@ssw0rd123\nP@ssw0rd123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainSystem auth = new MainSystem();
        auth.registerUser(1);

        ArrayList<Client> users = new UserFileReader().userFileReader();
        assertTrue(users.stream().anyMatch(u -> u.getNick().equals("juanito")));
    }

    @Test
    public void testRegisterUser_DuplicateNick() throws IOException {
        // Prepara un usuario existente
        Client existing = new Client();
        existing.setNick("ana");
        new UserFileWriter().userRegister(existing);

        String input = "Ana\nana\nanita\nSecurePass12\nSecurePass12\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainSystem auth = new MainSystem();
        auth.registerUser(1);

        ArrayList<Client> users = new UserFileReader().userFileReader();
        assertTrue(users.stream().anyMatch(u -> u.getNick().equals("anita")));
    }

    @Test
    public void testLoginClient_Success() {
        // Prepara un usuario
        Client testUser = new Client();
        testUser.setNick("juanito");
        testUser.setPassword("SecurePass12");
        new UserFileWriter().userRegister(testUser);

        String input = "juanito\nSecurePass12\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainSystem auth = new MainSystem();
        Client result = auth.loginClient(new Client());

        assertNotNull(result);
        assertEquals("juanito", result.getNick());
    }
    @Test
    public void testLoginClient_BannedUser() {
        // Prepara usuario baneado
        Client banned = new Client();
        banned.setNick("malUsuario");
        new BanFileWriter().banUser(banned);

        String input = "malUsuario\nclaveCualquiera\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainSystem auth = new MainSystem();
        Client result = auth.loginClient(new Client());

        assertNull(result);
    }
    @Test
    public void testLoginAdmin_Success() {
        // Prepara un admin
        Administrator admin = new Administrator();
        admin.setNick("superadmin"); //no existe nick, poner en documento
        admin.setPassword("admin123");
        new AdministratorFileWriter().adminRegister(admin);

        String input = "superadmin\nadmin123\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainSystem auth = new MainSystem();
        Administrator result = auth.loginAdmin(new Administrator());

        assertNotNull(result);
        assertEquals("superadmin", result.getNick());
    }
    @Test
    public void testLoginAdmin_RetryPassword() {
        Administrator admin = new Administrator();
        admin.setNick("admin");
        admin.setPassword("contraseñaCorrecta");
        new AdministratorFileWriter().adminRegister(admin);

        String input = "admin\nincorrecta\ncontraseñaCorrecta\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        MainSystem auth = new MainSystem();
        Administrator result = auth.loginAdmin(new Administrator());

        assertNotNull(result);
    }
}

