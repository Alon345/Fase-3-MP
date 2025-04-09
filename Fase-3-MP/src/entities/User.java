package Entities;

public class User {
    /**A continuación se definen los atributos**/
    private String name;
    private String nick;
    private String password;

    /**A continuación se definen los Getters y Setters**/
    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

