package System;

public class Manager {
    public Manager(){

    }
    public void start(){
        mainSystem system = new mainSystem();
        while (true) {
            system.selector();
        }
    }
}
