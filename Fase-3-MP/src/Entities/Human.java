package Entities;

public class Human extends MinionsComposit {
    /**A continuación se definen los atributos y  operaciones**/
    private Loyalty loyalty;
    public enum Loyalty{
        ALTA, MEDIA, BAJA
    }
    public Loyalty getLoyalty() {
        return loyalty;
    }
    public void setLealtad(Loyalty lealtad) {
        this.loyalty = lealtad;
    }
}
