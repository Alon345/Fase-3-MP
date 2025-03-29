import java.io.Console;
import java.util.Scanner;
public class PrincipalSystem {
    public static Console out;
    private static String in;

    public void selector(){
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);
        terminal.mostrarInicio();
        int opcion = sc.nextInt();

    }
}
