package Entities;

public class MinionsComposit {
    /**A continuación se definen los atributos**/
    private String name;
    private int hp;
    private String type;

    /**A continuación se definen los Getters y Setters**/

    public String getType() {return type;}
    public void setType(String tipo) {
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String nombre) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }
    public void setHp(int hp) {
        this.hp = hp;
    }

}
