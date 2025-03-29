package system;

import entities.Client;

import java.io.Console;
import java.util.Scanner;
public class mainSystem {
    public static Console out;
    private static String in;

    public void selector(){
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);
        terminal.showStart();
        int option = sc.nextInt();
        switch (option) {
            case 1: {
                //REGISTRO DE USUARIO
                terminal.userRegistrerMenu();
                int user = sc.nextInt();
                registerUser(user);
            }
        }
    }
    public void registerUser(int option) {
        Scanner sc = new Scanner(System.in);
        Terminal terminal = new Terminal();
        switch (option) {
            case 1: {
                Client client = new Client();
            }
            case 3: {
            }
            default: terminal.error();
        }
    }
}
