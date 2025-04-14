package System;

import Entities.Client;
import Entities.Combat;
import Entities.User;

import java.util.ArrayList;

public class Terminal {
    public Terminal(){
    }
    /**Mensajes iniciales**/
    public void wellcome(){System.out.println("       Bienvenido a Shadow Clash!");} //no cambiar
    public void showStart() {
            System.out.println("=======================================");
            System.out.println("  Por favor, selecciona una opción");
            System.out.println("  1 Registrarse");
            System.out.println("  2 Iniciar sesión como Jugador");
            System.out.println("  3 Iniciar sesión como Administrador");
            System.out.println("  4 Salir");
            System.out.println("=======================================");
        }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showMenu() {
        System.out.println("=======================================");
        System.out.println("               MENU JUGADOR ");
        System.out.println("=======================================");
        System.out.println("  1 Registrar personaje");
        System.out.println("  2 Eliminar personaje");
        System.out.println("  3 Seleccionar equipo");
        System.out.println("  4 Desafiar a un jugador");
        System.out.println("  5 Consultar combates");
        System.out.println("  6 Consultar ranking global");
        System.out.println("  7 Salir");
        System.out.println("  8 Borrar cuenta");
        System.out.println("=======================================");
    }

    /**Mensajes de los usuarios**/

    public void userRegistrerMenu() {
        System.out.println("=======================================");
        System.out.println("      Registro de nuevo usuario");
        System.out.println("=======================================");
        System.out.println("  ¿En qué modo desea registrarse?");
        System.out.println("  1 Modo Jugador");
        System.out.println("  2 Modo Administrador");
        System.out.println("  3 Salir");
        System.out.println("=======================================");
    }
    public void rankingMessage() {
        System.out.println("=======================================");
        System.out.println("             RANKING GLOBAL");
        System.out.println("=======================================");
    }

    public void showGoldRanking(ArrayList<Client> list) {
        for (int numUser = 0; numUser < list.size(); numUser++) { //recorremos toda la lista de usuarios para mostrarlos a todos
            System.out.println(numUser + 1 + ": " + list.get(numUser).getNick() + "\t\t" + list.get(numUser).getCharacter().getGold() + " Monedas de Oro");
        }
    }

    /**Mensajes Mini**/

    public void error() {
        System.out.println("!Error!. Algo inesperado ocurrió");
    }
    public void askNameUser() {
        System.out.println("Introduce tu nombre y apellidos");
    }
    public void askNick() {
        System.out.println("Introduce tu nick de usuario");
    }
    public void nickExists() {System.out.println("El nick introducido ya existe");}
    public void reenterNewNick() {System.out.println("Introduce otro nick de usuario");}
    public void askPassword() {
        System.out.println("Introduce la contraseña de tu cuenta");
    }
    public void confirmPassword() {System.out.println("Por favor, confirme la contraseña introducida");}
    public void noUsersError(){System.out.println("No hay usuarios registrados en este videojuego \n        !Regístrate para jugar!");}
    public void nickNotFoundError(){System.out.println(" El nick no existe en este videojuego, \n" +
                                                       "         comprueba los campos");}
    public void hiAgainUser(String username){System.out.println("  !Un placer verte de nuevo, "+ username+"!");}
    public void emptyPassword(){System.out.println("!El campo 'Contraseña' no debe estar vacio!");}
    public void emptyNick(){System.out.println("!El campo 'Nick' no debe estar vacio!");}
    public void emptyName(){System.out.println("!El campo 'Nombre' no debe estar vacio!");}
    public void deletedAccountOK(){System.out.println("Tu cuenta ha sido eliminada con éxito...");}
    public void noAccountAvaliable(){System.out.println("No se ha encontrado esta cuenta en el sistema");}
    public void cancelOperation(){System.out.println("         Operación Cancelada");}
    public void closedSesion4Security(){System.out.println("Hemos cerrado tu sesión por seguridad.");}
    public void deleteCharacToCreateAnother() {
        System.out.println("Debes eliminar a un personaje para volver a crear otro nuevo.");
    }
    public void invalidSelecction(){System.out.println("          Opción invalida");}
    public void advertency(){
        System.out.println("=========================================");
        System.out.println("IMPORTANTE: La siguiente acción puede ser \nirreversible, asegurate de que deseas hacerlo.");
        System.out.println("=========================================");
    }
    public void writeConfirm(){System.out.println("Escriba 'ELIMINAR' para confirmar esta acción, \nsi deseas cancelar pulsa cualquier tecla.");}

    public void confirmNewUser(String username){
        System.out.println("=======================================");
        System.out.println("Bienvenido al sistema, "+ username + ".\nInicia sesión con tus credenciales para acceder");}
    public void confirmNewAdmin(String username){
        System.out.println("=======================================");
        System.out.println("Bienvenido al sistema Administrador, "+ username + ".\nInicia sesión con tus credenciales para acceder");}
    public void errorPassword() {
        System.out.println("Contraseña incorrecta, pruebe de nuevo");
    }
    public void logout() {
        System.out.println("Cerrando sesion... Bye");
    }

    /**Mensajes de los personajes y lo relacionado a ellos**/

    public void deleteCharacter() {
        System.out.println("Para crear un personaje nuevo antes tienes que eliminar el existente");
    }
    public void confirmDeleteCharacter() {
        System.out.println("=========================================");
        System.out.println("       ELIMINACIÓN DE PERSONAJE");
        System.out.println("=========================================");
        System.out.println(" ¿Seguro que desea eliminar el personaje?");
        System.out.println("   1 Si");
        System.out.println("   2 No");
    }
    public void deletedCharacter() {
        System.out.println("Personaje eliminado correctamente");
    }

    public void showTipesOfCharacters() {
        System.out.println("=========================================");
        System.out.println("        CREACIÓN DE PERSONAJE");
        System.out.println("=========================================");
        System.out.println(" ¿Qué tipo de Personaje quieres crear");
        System.out.println("  1 Vampiro");
        System.out.println("  2 Licantropo");
        System.out.println("  3 Cazador");
    }

    /**Mensajes de los desafíos**/

    public void welcomeChallenge() {
        System.out.println("=========================================");
        System.out.println("     BIENVENIDO AL MENU DE DESAFÍOS");
        System.out.println("=========================================");
        System.out.println("   Rivales disponibles. Escoge a uno. ");
    }
    public void notAvaliableRival() {System.out.println("No hay rivales disponibles en este momento!");}
    public void showAvaliableRivals(ArrayList<Client> clientArrayList, Client client) {
        System.out.println("=======================================");
        System.out.println("0 Cancelar");
        for (int numClient = 0; numClient < clientArrayList.size(); numClient++) {
            if (clientArrayList.get(numClient).getCharacter() != null && !clientArrayList.get(numClient).getNick().equals(client.getNick())) {
                System.out.println((numClient + 1) + ": " + clientArrayList.get(numClient).getNick());
            }
        }
        System.out.println("=======================================");
    }
    public void validNumber() {
        System.out.println("Elige un numero valido");
    }
    public void askForGoldBet() {
        System.out.println("Introduce la cantidad de oro que deseas apostar (>100)");
    }
    public void challengeCreated(){System.out.println("Desafío creado y enviado al rival!");}

    /**Mensajes de los esbirros**/
    public void askMinionType() {
        System.out.println("=======================================");
        System.out.println("    Selecciona el tipo de esbirro");
        System.out.println("=======================================");
        System.out.println("    1 Humano");
        System.out.println("    2 Ghoul");
        System.out.println("    3 Demonio");
        System.out.println("=======================================");
    }
    public void askMinionName() {
        System.out.println("Introduce el nombre del esbirro ");
    }
    public void askForHp() {
        System.out.println("Introduce la cantidad de vida (0-3)");
    }
    public void askForMinionsNum() {
        System.out.println("Introduce el numero de esbirros que deseas");
    }


    /**Mensajes de los Humanos**/
    public void errorHuman() {
        System.out.println("Como vampiro, los humanos no pueden ser tus esbirros. Elige otro tipo de criatura.");
    }
    public void askForLoyalty() {
        System.out.println("=======================================");
        System.out.println("   Introduce la lealtad de tu humano");
        System.out.println("=======================================");
        System.out.println("  1 Alta");
        System.out.println("  2 Media");
        System.out.println("  3 Baja");
        System.out.println("=======================================");
    }

    /**Mensajes de los Demonios**/
    public void askForPact() {
        System.out.println("=======================================");
        System.out.println("    Introduce el pacto del demonio");
        System.out.println("=======================================");
    }

    /**Mensajes de los GHOULS**/
    public void askForDependency() {
        System.out.println("=======================================");
        System.out.println("      ¿Que dependencia deseas? ");
        System.out.println("=======================================");
    }

    /**Mensajes de las RONDAS**/
    public void showRounds(Combat combat) {
        for (int numOfRound = 0; numOfRound < combat.getRounds().size(); numOfRound++) {
            System.out.println("=======================================");
            System.out.println(" Ronda " + (numOfRound+1) + " :");
            System.out.println(" Vida de " + combat.getChallenger().getCharacter().getName() + " al final de la ronda " + combat.getRounds().get(numOfRound).getHpChallengerEnd());
            System.out.println(" Vida de " + combat.getRival().getCharacter().getName() + " al final de la ronda " + combat.getRounds().get(numOfRound).getHpRivalEnd());
        }
        System.out.println("        !FIN DEL COMBATE!");
        if (combat.getWinner() != null) {
            System.out.println(" Vencedor " + combat.getWinner().getNick());
        } else {
            System.out.println("    !Ha habido un empate!");
        }
        System.out.println("=======================================");
    }
    public void showRound(int numOfRound) {
        System.out.println("Ronda número " + numOfRound + ":");
    }

    public void startRound(int hpChallenger, int hpRival, String nick, String nick2, int challengerAttackPotential, int challengerDefencePotential, int rivalAttackPotential, int rivalDefencePotential) {
        System.out.println("=======================================");
        System.out.println(nick + ":");
        System.out.println("- Vida " + hpChallenger);
        System.out.println("- Potencial ataque " + challengerAttackPotential);
        System.out.println("- Potencial defensa " + challengerDefencePotential);
        System.out.println();
        System.out.println(nick2 + ":");
        System.out.println("- Vida " + hpRival);
        System.out.println("- Potencial ataque " + rivalAttackPotential);
        System.out.println("- Potencial defensa " + rivalDefencePotential);
        System.out.println();
        System.out.println("=======================================");
    }
    public void attackAbility(String character, String ability) {
        System.out.println(character + " usa " + ability + " para potenciar su ataque");
    }
    public void defenceAbility(String character, String ability) {
        System.out.println(character + " usa " + ability + " para potenciar su defensa");
    }

    /**Mensajes de los Administradores**/
    public void adminMenu() {
        System.out.println("=======================================");
        System.out.println("              MENU ADMIN ");
        System.out.println("=======================================");
        System.out.println(" 1 Modificar personaje");
        System.out.println(" 2 Validar desafío");
        System.out.println(" 3 Banear a un usuario");
        System.out.println(" 4 Desbanear a un usuario");
        System.out.println(" 5 Salir");
        System.out.println(" 6 Borrar cuenta");
        System.out.println("=======================================");
    }
    public void allUsers(ArrayList<Client> users) {
        System.out.println("=======================================");
        System.out.println("    LISTA DE USUARIOS EN EL SISTEMA");
        System.out.println("=======================================");
        for (int i = 0; i < users.size(); i++) {
            Client user = users.get(i);
            System.out.println((i+1) + " " + user.getNick() + " - " +
                    user.getName());
        }
    }
    public void whatUserToBan(){
        System.out.println("========================================");
        System.out.println("Introduce el número del usuario a banear");
        System.out.println("========================================");
    }
    public void confirmBan(String username){
        System.out.println("======================================");
        System.out.println("               IMPORTANTE");
        System.out.println("========================================");
        System.out.println("¿Confirmas que deseas banear a " + username +"?");
        System.out.println("Para confirmar escribe 'BANEAR', para cancelar \npulsa cualquier tecla");
    }
    public void banned(String username){
        System.out.println("El usuario "+username+" ha sido baneado con éxito");
    }
    public void noNumberIn(){System.out.println("Debes introducir un número de usuario");}
    public void whyDoYouBannedThisUser(String username){System.out.println("¿Por qué se ha baneado a "+username+"? (opcional)");}
    public void howManyHours(){System.out.println("¿Cuantas horas deseas banearle (mínimo 1h)?");}
    public void notifyBanExpired(){System.out.println("Bienvenido de nuevo, tu baneo ha expirado.");}
    public void invalidNumberOfHours(){System.out.println("Introduce un numero de horas adecuado.");}
    // Método para mostrar la lista de usuarios baneados
    public void noUsersToBanError(){
        System.out.println("No hay usuarios en el sistema.");
    }
    public void allBannedUsers(ArrayList<String> bannedClients) {
        if (bannedClients.isEmpty()) {
            System.out.println("No hay usuarios baneados.");
        } else {
            System.out.println("Usuarios baneados:");
            for (int i = 0; i < bannedClients.size(); i++) {
                String[] parts = bannedClients.get(i).split("\\|");
                String nick = parts[0];
                String motivo = parts.length > 1 ? parts[1] : "Sin motivo";
                System.out.println((i + 1) + ". " + nick + " - Motivo: " + motivo);
            }
        }
    }
    public void whatUserToUnBan(){
            System.out.println("===========================================");
            System.out.println("Introduce el número del usuario a desbanear");
            System.out.println("===========================================");
    }
    public void unbbanedUser(String username){
        System.out.println("Has desbaneado a "+username);
    }
    public void noUsersBannedError(){
        System.out.println("No hay usuarios baneados.");
    }
    public void confirmUnban(String confirmation){
        System.out.println("Para confirmar el desbaneo escribe 'DESBANEAR', para cancelar \npulsa cualquier tecla");
    }
    public void showBannedUsers(ArrayList<Client> bannedUsers) {
        System.out.println("\n=== USUARIOS BANEADOS ===");
        for (int i = 0; i < bannedUsers.size(); i++) {
            Client user = bannedUsers.get(i);
            System.out.printf("%d. %s (%s) - Registro: %s\n",
                    i + 1,
                    user.getNick(),
                    user.getName(),
                    user.getRegister());
        }
    }


}//FIN
