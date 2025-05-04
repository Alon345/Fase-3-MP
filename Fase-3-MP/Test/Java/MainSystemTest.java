import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import System.*;

public class MainSystemTest {

    private final String USER_FILE = "Fase-3-MP/src/Files/UserRegister.txt";  // Ruta real de tu archivo
    private final String BAN_FILE = "Fase-3-MP/src/Files/BanRegister.txt";
    private MainSystem system;
    private PrintStream originalOut;

    @BeforeEach
    void setUp() {
        system = new MainSystem();
        originalOut = System.out;
    }

    @Test
    void testLoginAsPlayer_ValidCredentials_Success() throws Exception {
        // 1. Preparar entorno: Crear usuario de prueba
        Files.write(Paths.get(USER_FILE),
                Arrays.asList("user_2025,1q2w3e4r"),
                StandardCharsets.UTF_8,
                StandardOpenOption.APPEND
        );

        // 2. Simular entrada completa: Opción 2 + nick + password + salir
        String input = "2\nuser_2025\n1q2w3e4r\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // 3. Capturar salida
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // 4. Ejecutar flujo completo
        system.selector();

        // 5. Verificar mensaje de bienvenida
        String consoleOutput = outputStream.toString();
        assertTrue(consoleOutput.contains("Bienvenido de nuevo user_2025"));
    }

    @AfterEach
    void tearDown() throws IOException {
        // Restaurar streams
        System.setIn(System.in);
        System.setOut(originalOut);

        // Limpiar: Eliminar usuario de prueba
        Path path = Paths.get(USER_FILE);
        Files.write(path,
                Files.readAllLines(path)
                        .stream()
                        .filter(line -> !line.startsWith("user_2025,"))
                        .toList()
        );
    }
}

