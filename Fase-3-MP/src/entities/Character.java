package Entities;

import java.util.ArrayList;

public class Character {
    private String name;
    private Ability ability;
    private ArrayList<Weapon> weapons;
    private ArrayList<Weapon> activeWeapons;
    private ArrayList<Armor> armors;
    private Armor activeArmor;
    private ArrayList<Weakness> weaknesses;
    private ArrayList<Strength> strengths;
    private int gold;
    private int health;
    private int power;
    private String type;

    public Character() {
        this.weapons = new ArrayList<>();
        this.activeWeapons = new ArrayList<>();
        this.armors = new ArrayList<>();
        this.weaknesses = new ArrayList<>();
        this.strengths = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ability getAbility() {
        return this.ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public ArrayList<Weapon> getWeapons() {
        return this.weapons;
    }

    public void setWeapons(ArrayList<Weapon> weapons) {
        this.weapons = weapons;
    }

    public ArrayList<Weapon> getActiveWeapons() {
        return this.activeWeapons;
    }

    public void setActiveWeapons(ArrayList<Weapon> activeWeapons) {
        this.activeWeapons = activeWeapons;
    }

    public ArrayList<Armor> getArmors() {
        return this.armors;
    }

    public void setArmors(ArrayList<Armor> armors) {
        this.armors = armors;
    }

    public Armor getActiveArmor() {
        return this.activeArmor;
    }

    public void setActiveArmor(Armor activeArmor) {
        this.activeArmor = activeArmor;
    }

    public ArrayList<Weakness> getWeaknesses() {
        return this.weaknesses;
    }

    public void setWeaknesses(ArrayList<Weakness> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public ArrayList<Strength> getStrengths() {
        return this.strengths;
    }

    public void setStrengths(ArrayList<Strength> strengths) {
        this.strengths = strengths;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getPower() {
        return this.power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

