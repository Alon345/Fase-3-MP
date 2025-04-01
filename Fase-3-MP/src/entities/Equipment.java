package Entities;

public class Equipment {
    private String name;
    private int attackModifier;
    private int defenseModifier;

    public Equipment() {}

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttackModifier() {
        return this.attackModifier;
    }

    public void setAttackModifier(int attackModifier) {
        this.attackModifier = attackModifier;
    }

    public int getDefenseModifier() {
        return this.defenseModifier;
    }

    public void setDefenseModifier(int defenseModifier) {
        this.defenseModifier = defenseModifier;
    }
}



