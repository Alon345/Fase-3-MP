package System;

import Entities.*;

import java.util.ArrayList;
import java.util.List;

public class Terminal {
    public Terminal(){
    }
    /**Mensajes iniciales**/
    public void wellcome() {
        String boldBlue = "\033[1;34m"; // Azul en negrita
        String reset = "\033[0m";
        System.out.println("=======================================");
        System.out.println("     Bienvenido a " + boldBlue + "Shadow Clash" + reset + "!");
    }
    public void showStart() {
            System.out.println("=======================================");
            System.out.println("  Por favor, selecciona una opción");
            System.out.println("  1 Registrarse");
            System.out.println("  2 Iniciar sesión como Jugador");
            System.out.println("  3 Iniciar sesión como Administrador");
            System.out.println("  4 Salir del Sistema");
            System.out.println("=======================================");
        }

    public void showMenu() {
        String colorCodeGreen = "\033[0;32m"; // Verde
        String resetCode = "\033[0m";         // Reset de color
        System.out.println("=======================================");
        System.out.println(colorCodeGreen + "              MENU JUGADOR " + resetCode);
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
        System.out.println("       Registro de nuevo usuario");
        System.out.println("=======================================");
        System.out.println("  ¿En qué modo desea registrarse?");
        System.out.println("  1 Modo Jugador");
        System.out.println("  2 Modo Administrador");
        System.out.println("  3 Salir");
        System.out.println("=======================================");
    }
    public void rankingMessage() {
        System.out.println("=======================================");
        System.out.println("          🏆 RANKING GLOBAL 🏆");
        System.out.println("=======================================");
    }

    public void showGoldRanking(ArrayList<Client> users) {
        for (int i = 0; i < users.size(); i++) {
            Client user = users.get(i);
            String gold = user.getCharacter() != null ?
                    String.valueOf(3) : "0"; //aqui va el oro de cada personaje del usuario x
            // Personalización para los top 3
            if (i < 3) {
                String crown = "";
                String colorCode = "";
                switch(i) {
                    case 0: // TOP 1
                        crown = "👑";
                        colorCode = "\033[1;33m"; // Amarillo brillante al top 1
                        break;
                    case 1: // TOP 2
                        crown = "🥈";
                        colorCode = "\033[0;36m"; // Cian
                        break;
                    case 2: // TOP 3
                        crown = "🥉";
                        colorCode = "\033[0;35m"; // Magenta
                        break;
                }
                System.out.printf(colorCode + "%s%d. %-15s (GOLD %5s)\033[0m\n",
                        crown,
                        i+1,
                        user.getNick(),
                        gold);
            } else {
                // Formato normal para el resto
                System.out.printf("%d. %-15s (GOLD %5s)\n",
                        i+1,
                        user.getNick(),
                        gold);
            }
        }
        System.out.println("=======================================");
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

    public void askWeaknessValue() {System.out.println("Introduce el valor de la debilidad ");}
    public void askNumWeakness() {System.out.println("Introduce el numero de debilidades a añadir ");}
    public void askWeaknessName() {System.out.println("Introduce el nombre de la debilidad ");}

    public void askStrengthValue() {System.out.println("Introduce el valor de la fortaleza ");}
    public void askNumStrengths() {System.out.println("Introduce el numero de fortalezas a añadir ");}
    public void askStrengthName() {System.out.println("Introduce el nombre de la fortaleza ");}

    public void askNumArmors() {System.out.println("Introduce el número de armaduras a equipar ");}
    public void askNameArmors() {System.out.println("Introduce el nombre de la armadura ");}
    public void askForDefenceArmor() {System.out.println("Introduce la defensa máxima de la armadura, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos), si no tiene defensa escribe 0 ");}
    public void askForAttackeArmor() {System.out.println("Introduce el ataque máximo de la armadura, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos), si no tiene ataque escribe 0 ");}

    public void askNumWeapons() {System.out.println("Introduce el número de armas a equipar ");}
    public void askWeapName() {System.out.println("Introduce el nombre del arma ");}
    public void askWeapAttack() {System.out.println("Introduce el ataque máximo del arma, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos)");}
    public void askWeapDefence() {System.out.println("Introduce la defensa máxima del arma, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos), si no tiene defensa escribe 0 ");}

    public void askGold(){System.out.println("Introduce la cantidad de Monedas de Oro del personaje, \nNOTA: debe ser mayor o igual a 0");}
    public void askPassword() {System.out.println("Introduce la contraseña de tu cuenta");}
    public void confirmPassword() {System.out.println("Por favor, confirme la contraseña introducida");}
    public void noUsersError(){System.out.println("No hay usuarios registrados en este videojuego \n        !Regístrate para jugar!");}
    public void nickNotFoundError(){System.out.println(" El nick no existe en este videojuego, \n" +
                                                       "         comprueba los campos");}
    public void hiAgainUser(String username){System.out.println("    !Un placer verte de nuevo, "+ username+"!");}
    public void emptyPassword(){System.out.println("!El campo 'Contraseña' no debe estar vacio!");}
    public void emptyNick(){System.out.println("!El campo 'Nick' no debe estar vacio!");}
    public void emptyName(){System.out.println("!El campo 'Nombre' no debe estar vacio!");}
    public void deletedAccountOK(){System.out.println("Tu cuenta ha sido eliminada con éxito...");}
    public void noAccountAvaliable(){System.out.println("No se ha encontrado esta cuenta en el sistema");}
    public void cancelOperation(){System.out.println("         Operación Cancelada");}
    public void closedSesion4Security(){System.out.println("Hemos cerrado tu sesión por seguridad.");}
    public void noCorrectNumSelecction() {
        System.out.println("El numero introducido no es válido ");
    }
    public void anotherWeapon(List<Weapon> weapons, Weapon weapon) {
        System.out.println("==========================================");
        System.out.println("¿Quiere equipar otro arma de una sola mano?");
        System.out.println("==========================================");
        System.out.println("0 Ahora no");
        for (int numWeap = 0; numWeap < weapons.size(); numWeap++) {
            if (weapons.get(numWeap).isSingleHand() && weapons.get(numWeap) != weapon) {
                System.out.println(numWeap + 1 + " " + weapons.get(numWeap).getName());
            }
        }
        System.out.println("==========================================");
    }
    public void showWeapons(List<Weapon> weapons) { //mostramos lista de armas que tiene el personaje
        System.out.println("=======================================");
        System.out.println("      ¿Que arma quieres equipar?");
        System.out.println("=======================================");
        for (int numArma = 0; numArma < weapons.size(); numArma++) {
            System.out.println(numArma + 1 + " " + weapons.get(numArma).getName());
        }
        System.out.println("=======================================");
    }
    public void isWeaponSingleHanded() {
        System.out.println("========================================");
        System.out.println("¿El arma se puede usar con un sola mano?");
        System.out.println("========================================");
        System.out.println(" 1 Si");
        System.out.println(" 2 No");
        System.out.println("========================================");
    }
    public void deleteCharacToCreateAnother() {
        System.out.println("Debes eliminar a un personaje para volver a crear otro nuevo.");
    }
    public void invalidSelecction(){System.out.println("          Opción invalida");}
    public void advertency() {
        String colorCodeRed = "\033[0;31m"; // Rojo
        String resetCode = "\033[0m";       // Reset de color
        System.out.println("=========================================");
        System.out.println(colorCodeRed + "IMPORTANTE:" + resetCode + " La siguiente acción puede ser \nirreversible, asegúrate de que deseas hacerlo.");
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
        System.out.println("         CREACIÓN DE PERSONAJE");
        System.out.println("=========================================");
        System.out.println(" ¿Qué tipo de Personaje quieres crear");
        System.out.println("  1 Vampiro");
        System.out.println("  2 Licantropo");
        System.out.println("  3 Cazador");
        System.out.println("=========================================");
    }

    /**Mensajes de los desafíos**/

    public void welcomeChallenge() {
        System.out.println("=========================================");
        System.out.println("     BIENVENIDO AL MENU DE DESAFÍOS");
        System.out.println("=========================================");
        System.out.println("    Rivales disponibles. Escoge a uno. ");
        System.out.println("===========================================");

    }
    public void notAvaliableRival() {System.out.println("No hay rivales disponibles en este momento!");}
    public void showAvaliableRivals(ArrayList<Client> users) {
        for (int i = 0; i < users.size(); i++) {
            Client user = users.get(i);
            System.out.printf("%d %s \n", // 1 nick01
                    i + 1,
                    user.getNick());
        }
        System.out.println("===========================================");
    }
    public void validNumber() {
        System.out.println("Elige un numero valido");
    }
    public void askForGoldBet() {
        System.out.println("Introduce la cantidad de oro que deseas apostar (>100)");
    }
    public void challengeCreated(){System.out.println("Desafío creado y enviado al rival!");}
    public void askChallenge(Challenge challenge) {
        System.out.println("=======================================");
        System.out.println("           DESAFÍO ENTRANTE");
        System.out.println("=======================================");
        System.out.println("El usuario " + challenge.getChallenger().getNick()+" te ha desafiado.");
        System.out.println("Su apuesta es de " + challenge.getGold() + " Monedas de Oro");
        System.out.println("=======================================");
        System.out.println("     ¿Quieres aceptar el desafio?");
        System.out.println("=======================================");
        System.out.println("  1 SI ACEPTAR");
        System.out.println("  2 NO ACEPTAR");
        System.out.println("NOTA: si no aceptas perderás " + challenge.getGold() / 10 + " de tus Monedas de Oro");
        System.out.println("=======================================");

    }

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
    public void askForHp() {System.out.println("Introduce la cantidad de vida, \nNOTA: Debe ser un valor entre 0 y 3 (incluídos)");}
    public void askForMinionsNum() {System.out.println("Introduce el numero de esbirros que deseas, \nNOTA: Debe ser un valor entre 0 y 3 (incluídos)");}
    public void askPower(){System.out.println("Introduce su poder, \nNOTA: Debe ser un valor entre 1 y 5 (incluídos)");}

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

    /**Mensajes de los Vampiros**/
    public void askVampireAge() {
        System.out.println("=======================================");
        System.out.println("     ¿Qué edad tiene tu vampiro?");
        System.out.println("=======================================");
    }
    public void askVampireBlood() {System.out.println("Introduce la cantidad de sangre del vampiro ");}
    public void askVampireName() {System.out.println("Introduce el nombre del vampiro ");}
    public void askAbilityName() {System.out.println("Introduce el nombre de la habilidad ");}
    public void askAbilityAttack() {System.out.println("Introduce el valor máximo de ataque de la habilidad, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos) ");}
    public void askAbilityDefence() {System.out.println("Introduce el valor máximo de defensa de la habilidad, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos) ");}
    public void askCostAbility() {System.out.println("Introduce el coste máximo de la habilidad, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos) ");}

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
        String colorCodeBlue = "\033[0;34m"; // Azul
        String resetCode = "\033[0m";        // Reset de color
        System.out.println("=======================================");
        System.out.println("              " + colorCodeBlue + "MENU ADMIN" + resetCode);
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
        String colorCodeRed = "\033[0;31m"; // Rojo
        String resetCode = "\033[0m";       // Reset de color
        System.out.println("======================================");
        System.out.println("              " + colorCodeRed + "IMPORTANTE" + resetCode);
        System.out.println("======================================");
        System.out.println("¿Confirmas que deseas banear a " + username +"?");
        System.out.println("Para confirmar escribe '"+colorCodeRed+"BANEAR"+resetCode+"', para cancelar \npulsa cualquier tecla");
    }
    public void banned(String username){
        System.out.println("El usuario "+username+" ha sido baneado con éxito");
    }
    public void noNumberIn(){System.out.println("Debes introducir un número de usuario");}
    public void whyDoYouBannedThisUser(String username){System.out.println("¿Por qué se ha baneado a "+username+"? (opcional)");}
    public void howManyHours(){System.out.println("¿Cuantas horas deseas banearle (mínimo 1h)?");}
    public void notifyBanExpired(){System.out.println("Bienvenido de nuevo, tu baneo ha expirado.");}
    public void invalidNumberOfHours(){System.out.println("Introduce un numero de horas adecuado.");}
    public void userIsBanned(String nick){
        String colorCodeRed = "\033[0;31m"; // Rojo
        String resetCode = "\033[0m";
        System.out.println("El jugador '"+ nick + "' ha sido"+colorCodeRed+" baneado "+resetCode+"por el Administrador del sistema.");}
    public void showArmors(List<Armor> armors) {
        System.out.println("========================================");
        System.out.println("    ¿Que armadura quieres equipar?");
        System.out.println("========================================");
        for (int numArmor = 0; numArmor < armors.size(); numArmor++) {
            System.out.println(numArmor + 1 + " " + armors.get(numArmor).getName());
        }
        System.out.println("========================================");
    }
    // Método para mostrar la lista de usuarios baneados
    public void noUsersToBanError(){
        System.out.println("No hay usuarios en el sistema.");
    }
    public void allBannedUsers(ArrayList<String> bannedClients) {
        if (bannedClients.isEmpty()) {
            System.out.println("No hay usuarios baneados.");
        } else {
            System.out.println("Usuarios baneados");
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

    public void confirmUnban(String username){
        String colorCodeRed = "\033[0;31m"; // Rojo
        String resetCode = "\033[0m";       // Reset de color
        System.out.println("======================================");
        System.out.println("              " + colorCodeRed + "IMPORTANTE" + resetCode);
        System.out.println("======================================");
        System.out.println("¿Confirmas que deseas banear a " + username +"?");
        System.out.println("Para confirmar el desbaneo escribe '"+colorCodeRed+"BANEAR"+resetCode+"', \npara cancelar pulsa cualquier tecla");
    }
    public void showBannedUsers(ArrayList<Client> bannedUsers) {
        System.out.println("===========================================");
        System.out.println("             USUARIOS BANEADOS");
        System.out.println("===========================================");
        for (int i = 0; i < bannedUsers.size(); i++) {
            Client user = bannedUsers.get(i);
            System.out.printf("%d %s (%s) - Registro: %s\n",
                    i + 1,
                    user.getNick(),
                    user.getName(),
                    user.getRegister());
        }
    }

}//FIN
