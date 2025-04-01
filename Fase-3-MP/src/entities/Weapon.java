package Entities;

public class Weapon extends Equipment {
    private boolean singleHanded;

    public Weapon() {}

    public boolean isSingleHand() {
        return this.singleHanded;
    }

    public void setSingleHand(boolean singleHanded) {
        this.singleHanded = singleHanded;
    }
}
