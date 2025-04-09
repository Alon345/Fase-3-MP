import System.mainSystem;
import System.Terminal;

public class Main {
    public static void main(String[] args) {
        mainSystem system = new mainSystem();
        Terminal terminal = new Terminal();
        terminal.wellcome();
        while (true) {
            system.selector();
        }
    }
}