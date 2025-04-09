package Entities;

public class Vampire extends Character {
    /**A continuación se definen los atributos**/
    private int age;
    private int blood;

    /**A continuación se define el constructor**/
    public Vampire() {
    }

    /**A continuación se definen los Getters y Setters**/
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

    /**A continuación se definen las operaciones**/
    public void selectCharacter() {
        System.out.println("1 Elige un personaje");
    }
}
