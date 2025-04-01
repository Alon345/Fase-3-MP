package System;

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
    public void showMenu() {
        System.out.println("************MENU************");
        System.out.println("1.Registrar personaje");
        System.out.println("2.Eliminar personaje");
        System.out.println("3.Seleccionar equipo");
        System.out.println("4.Desafiar");
        System.out.println("5.Consultar combates");
        System.out.println("6.Consultar ranking");
        System.out.println("7.Salir");
        System.out.println("8.Borrar cuenta");
        System.out.println("****************************");
    }
    public void confirmDelete() {
        System.out.println("¿Estas seguro de querer eliminar la cuenta?");
        System.out.println("1.Si");
        System.out.println("2.No");
    }

    public void error() {
        System.out.println("Error");
    }
    public void askNameUser() {
        System.out.println("Introduce tu nombre y apellidos");
    }
    public void askNick() {
        System.out.println("Introduce tu nick de usuario");
    }
    public void nickExists() {
        System.out.println("El nick introducido ya existe");
    }
    public void askPassword() {
        System.out.println("Introduce la contraseña de tu cuenta");
    }
    public void confirmPassword() {
        System.out.println("Confirme la contraseña introducida");
    }
    public void errorPassword() {
        System.out.println("Contraseña incorrecta, pruebe de nuevo");
    }

    public void showFactories() {
        System.out.println("Seleccione que tipo de personaje va a crear:");
        System.out.println("1.Vampiro");
        System.out.println("2.Licantropo");
        System.out.println("3.Cazador");
    }
    public void deleteCharacter() {
        System.out.println("Para crear un personaje nuevo antes tienes que eliminar el existente");
    }
    public void confirmDeleteCharacter() {
        System.out.println("Seguro que desea eliminar el personaje?");
        System.out.println("1.Si");
        System.out.println("2.No");
    }
    public void deltedCharacter() {
        System.out.println("Personaje eliminado correctamente");
    }
    public void logout() {
        System.out.println("Cerrando sesion...");
    }
}
