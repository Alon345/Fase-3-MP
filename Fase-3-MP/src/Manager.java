public class Manager {
    public Manager(){

    }
    public void start(){
        PrincipalSystem system = new PrincipalSystem();
        while (true) {
            system.selector();
        }
    }
}
