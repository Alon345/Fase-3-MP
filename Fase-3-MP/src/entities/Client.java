package entities;
import system.mainSystem;
import system.Terminal;
import java.util.Scanner;
import java.util.Arrays;

public class Client extends User {

    private String register;
    private Character character;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

}
