public class Manager {
    public Manager(){

    }
    public void start(){
        System system = new System();
        while (true) {
            system.selector();
        }
    }
}
