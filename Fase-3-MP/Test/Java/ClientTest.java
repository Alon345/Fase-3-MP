
import Entities.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


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

}