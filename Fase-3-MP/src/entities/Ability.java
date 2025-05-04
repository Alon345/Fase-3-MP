package Entities;

public class Ability {

    /** Atributos **/
    private String name;
    private int attack;
    private int defense;

    /** Constructor **/
    public Ability() {
        this.name = "";
        this.attack = 0;
        this.defense = 0;
    }

    public Ability(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    /** Getters y Setters **/
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    /** Métodos adicionales **/
    @Override
    public String toString() {
        return "Ability{name='" + name + "', attack=" + attack + ", defense=" + defense + "}";
    }
}