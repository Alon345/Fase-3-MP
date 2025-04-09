package System;
//Sobra, hemos visto q no es necesario, pero puede que más adelante si lo sea
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
