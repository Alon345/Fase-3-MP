package Entities;

public class User {
    /**A continuación se definen los atributos**/
    private String name;
    private String nick;
    private String password;
    private String banMotive;
    private int bannedForHours; //baneado x horas

    /**A continuación se definen los Getters y Setters**/
    public String getName() {
        return name;
    }
    public void setName(String name) {
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

    public String getBanMotive(){return banMotive;}
    public void setBanMotive(String banMotive) {this.banMotive = banMotive;}

    public int getBannedForHours(){return bannedForHours;}
    public void setHoursOfBan(int bannedForHours){
        this.bannedForHours = bannedForHours;
    }
}//FIN

