package Entities;

public class Vampire extends Character {
    private int age;
    private int blood;

    public Vampire() {
    }
    public int getBlood() {
        return this.blood;
    }
    public void setBlood(int blood) {
        this.blood = blood;
    }
    public int getAge() {
        System.out.println("El vampiro tien  " + this.age + " años");
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void selectCharacter() {
        System.out.println("1 Elige un personaje");
    }
}
