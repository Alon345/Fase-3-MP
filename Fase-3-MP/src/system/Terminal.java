package system;

public class Terminal {
    public Terminal(){

    }
    public void showStart() {
        System.out.println("1.Registrarse");
        System.out.println("2.Iniciar sesión(modo cliente)");
        System.out.println("3.Iniciar sesión(modo administrador)");
    }
    public void userRegistrerMenu() {
        System.out.println("Registrar nuevo usuario");
        System.out.println("----------------");
        System.out.println("¿En qué modo se desea registrar?");
        System.out.println("1.Modo cliente");
        System.out.println("2.Modo operador");
        System.out.println("3.Salir");
    }
    public void confirmDelete() {
        System.out.println("¿Estas seguro de querer eliminar la cuenta?");
        System.out.println("1.Si");
        System.out.println("2.No");
    }

    public void error() {
    }
}
