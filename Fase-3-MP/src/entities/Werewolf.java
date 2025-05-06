package Entities;

public class Werewolf extends Character {
    /**A continuación se definen los atributos**/
    private int rage;

    /**A continuación se define el constructor**/
    public Werewolf() {
        super();
        this.type = "LICANTROPO";
    }

    /**A continuación se definen los Getters y Setters**/

    public int getRage() {
        return rage;
    }

    public void setRage(int rage) {
        this.rage = rage;
    }
}
