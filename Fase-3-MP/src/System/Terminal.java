package System;

import Entities.*;
import Entities.Character;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Terminal {

    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";


    /**Mensajes iniciales**/
    public void wellcome() {
        String red = "\033[1;31m"; // Rojo brillante
        String reset = "\033[0m"; // Reset de color

        System.out.println("=======================================");
        System.out.println("      Bienvenido a " + red + "Shadow Clash" + reset + "!");
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

    public void invalidInput() {
        System.out.println("Error: Entrada no válida. Por favor, ingrese un número.");
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

    public void showCombatLogs(List<String> logs) {
        System.out.println("=======================================");
        System.out.println("         HISTORIAL DE COMBATES");
        System.out.println("=======================================");
        if (logs.isEmpty()) {
            System.out.println("No hay combates registrados.");
        } else {
            for (String log : logs) {
                System.out.println(log);
            }
        }
        System.out.println("=======================================");
    }
    public void startCombatMessage(Character challenger, Character rival) {
        System.out.println(CYAN + "⚔️ ¡El combate comienza entre " + challenger.getName() + " y " + rival.getName() + "! ⚔️" + RESET);
    }

    public void combatWinner(String winnerNick, int winnerPower, int loserPower) {
        System.out.println(GREEN + "🏆 ¡" + winnerNick + " ha ganado el combate! 🏆" + RESET);
        System.out.println(YELLOW + "Poder del ganador: " + winnerPower + " | Poder del perdedor: " + loserPower + RESET);
    }

    public void combatDraw(int challengerPower, int rivalPower) {
        System.out.println(RED + "🤝 ¡El combate terminó en empate! 🤝" + RESET);
        System.out.println(YELLOW + "Poder de ambos jugadores: " + challengerPower + RESET);
    }

    public void combatDetails(String challengerNick, int challengerAttack, int challengerDefense,
                              String rivalNick, int rivalAttack, int rivalDefense) {
        System.out.println(BLUE + "📜 Detalles del combate:" + RESET);
        System.out.println(GREEN + challengerNick + " (Ataque: " + challengerAttack + ", Defensa: " + challengerDefense + ")" + RESET);
        System.out.println(RED + rivalNick + " (Ataque: " + rivalAttack + ", Defensa: " + rivalDefense + ")" + RESET);
    }

    public void combatEnd() {
        System.out.println(CYAN + "⚔️ El combate ha finalizado. ⚔️" + RESET);
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
        final String YELLOW_BRIGHT = "\033[1;93m"; // Amarillo brillante
        final String RESET = "\033[0m"; // Reset de color
        System.out.println("=======================================" );
        System.out.println(YELLOW_BRIGHT + "          🏆 RANKING GLOBAL 🏆" + RESET);
        System.out.println( "=======================================" );
    }
    public void showGoldRankingSimple(List<Map.Entry<String, Integer>> usuarios) {
        for (int i = 0; i < usuarios.size(); i++) {
            Map.Entry<String, Integer> user = usuarios.get(i);
            String nombre = user.getKey();
            String oro = user.getValue() != null ? String.valueOf(user.getValue()) : "0";
            // Personalización para los top 3
            if (i < 3) {
                String crown = "";
                String colorCode = "";
                switch(i) {
                    case 0:
                        crown = "👑";
                        colorCode = "\033[1;33m"; // Amarillo brillante
                        break;
                }
                System.out.printf(colorCode + "%s%d %-15s (GOLD %5s)\033[0m\n",
                        crown,
                        i + 1,
                        nombre,
                        oro);
            } else {
                System.out.printf("%d %-15s (GOLD %5s)\n",
                        i + 1,
                        nombre,
                        oro);
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

    public void askWeaknessValue() {System.out.println("Introduce el valor de la debilidad. De 1 a 5 (incluídos)");}
    public void askNumWeakness() {System.out.println("Introduce el numero de debilidades a añadir. (>=0) ");}
    public void askWeaknessName() {System.out.println("Introduce el nombre de la debilidad ");}

    public void askStrengthValue() {System.out.println("Introduce el valor de la fortaleza. De 1 a 5 (incluídos)");}
    public void askNumStrengths() {System.out.println("Introduce el numero de fortalezas a añadir. De 1 a 5 (incluídos)");}
    public void askStrengthName() {System.out.println("Introduce el nombre de la fortaleza ");}

    public void askNumArmors() {System.out.println("Introduce el número de armaduras a equipar (mínimo 1) ");}
    public void askNameArmors() {System.out.println("Introduce el nombre de la armadura ");}
    public void askForDefenceArmor() {System.out.println("Introduce la defensa máxima de la armadura, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos), si no tiene defensa escribe 0 ");}
    public void askForAttackeArmor() {System.out.println("Introduce el ataque máximo de la armadura, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos), si no tiene ataque escribe 0 ");}

    public void askNumWeapons() {System.out.println("Introduce el número de armas a equipar (mínimo 1) ");}
    public void askWeapName() {System.out.println("Introduce el nombre del arma ");}
    public void askWeapAttack() {System.out.println("Introduce el ataque máximo del arma, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos)");}
    public void askWeapDefence() {System.out.println("Introduce la defensa máxima del arma, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos), si no tiene defensa escribe 0 ");}

    public void askGold() {
        final String YELLOW_BRIGHT = "\u001B[93m"; // Amarillo brillante
        final String RESET = "\u001B[0m";           // Resetear color
        System.out.println("Introduce la cantidad de " + YELLOW_BRIGHT + "Monedas de Oro" + RESET + " del personaje, \nNOTA: debe ser mayor o igual a 0");
    }
    public void askPassword() {System.out.println("Introduce la contraseña de tu cuenta");}
    public void confirmPassword() {System.out.println("Por favor, confirme la contraseña introducida");}
    public void noUsersError(){System.out.println("!No hay usuarios registrados en este videojuego \n        !Regístrate para jugar!");}
    public void nickNotFoundError(){System.out.println(" El nick no existe en este videojuego, \n" +
                                                       "         comprueba los campos");}
    public void hiAgainUser(String username) {
        final String BLUE_BRIGHT   = "\033[1;94m"; // Azul brillante
        final String RESET         = "\033[0m";    // reset de color

        System.out.println("   !Un placer verte de nuevo, "
                + BLUE_BRIGHT + username + RESET +  "!");
    }
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

    public void exit() {
        System.out.println("Guardando cambios... Saliendo...");
    }

    public void showCharacName(Character character) {
        System.out.println("Este es el nombre actual del personaje " + character.getName());
    }
    public void newAtributeValue() {
        System.out.println("¿Cual es el nuevo valor del atributo?");
    }

    public void showHp(Client client) {
        System.out.println("Estos son los puntos de vida (HP) actuales "+ client.getCharacter().getHp());
    }

    public void showPower(Client client) {
        System.out.println("Este es el poder actual "+ client.getCharacter().getPower());
    }

    public void showCharacType(Client client) {
        System.out.println("Este es el tipo de personaje actual "+ client.getCharacter().getType());
    }
    public void showDon(Don don) {
        System.out.println("=========================================");
        System.out.println("Nombre de habilidad " + don.getName());
        System.out.println("Ataque de habilidad " + don.getAttack());
        System.out.println("Defensa de habilidad " + don.getDefense());
        System.out.println("Edad de habilidad " + don.getMinimumValue());
        System.out.println("=========================================");
    }


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
        final String RESET = "\033[0m";
        String[] spinner = {"|", "/", "-", "\\"}; // Los 4 estados del "spinner"

        System.out.print("Cerrando sesión...");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones reducido
                System.out.print("\rCerrando sesión..." + spinner[i % 4]); // Sobreescribir la línea
                Thread.sleep(100); // Pausa de 100ms entre cada rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Imprimir una nueva línea después del "spinner"
    }

    /**Mensajes de los personajes y lo relacionado a ellos**/

    public void confirmDeleteCharacter() {
        String colorCodeRed = "\033[0;31m"; // Rojo
        String resetCode = "\033[0m";       // Reset de color
        System.out.println("============================================");
        System.out.println("         " + colorCodeRed + "ELIMINACIÓN DE PERSONAJE" + resetCode);
        System.out.println("============================================");
        System.out.println("¿Confirmas que deseas eliminar el personaje?");
        System.out.println("Para confirmar escribe '"+colorCodeRed+"ELIMINAR"+resetCode+"', \npara cancelar pulsa cualquier tecla");
        System.out.println("============================================");
    }
    public void deletedCharacter() {
        final String RED_BRIGHT = "\033[1;91m"; // rojo brillante
        final String RESET = "\033[0m";
        System.out.println(RED_BRIGHT + "!Tu criatura ha sido eliminada y devuelta al inframundo!" + RESET);
    }
    public void selectType() {
        final String RED_BRIGHT = "\033[1;91m"; // Rojo brillante para Vampiro
        final String GREEN_BRIGHT = "\033[1;92m"; // Verde brillante para Cazador
        final String BLUE_BRIGHT = "\033[1;94m"; // Azul brillante para Licantropo
        final String RESET = "\033[0m"; // Reset de color

        System.out.println("============================================");
        System.out.println("   MODIFICACION DEL TIPO DE PERSONAJE");
        System.out.println("============================================");
        System.out.println("¿De qué tipo quiere que sea el personaje?");
        System.out.println(RED_BRIGHT + " 1  Vampiro" + RESET);
        System.out.println(GREEN_BRIGHT + " 2  Cazador" + RESET);
        System.out.println(BLUE_BRIGHT + " 3  Licantropo" + RESET);
        System.out.println("============================================");
    }
    public void characStrengths(ArrayList<Strength> strengths) {
        System.out.println("Estas son las fortalezas del personaje");
        for (int iterator = 0; iterator < strengths.size(); iterator++) {
            System.out.println(iterator + 1 + " " + strengths.get(iterator).getName());
        }
    }
    public void characWeakness(ArrayList<Weakness> weaknesses) {
        System.out.println("Estas son las debilidades del personaje");
        for (int iterator = 0; iterator < weaknesses.size(); iterator++) {
            System.out.println(iterator + 1 + " " + weaknesses.get(iterator).getName());
        }
    }
    public void modifyWeaknesses() {
        System.out.println("============================================");
        System.out.println("           MODIFICAR DEBILIDADES");
        System.out.println("============================================");
        System.out.println("¿Que desea realizar?");
        System.out.println(" 1  Añadir debilidad");
        System.out.println(" 2  Eliminar debilidad");
        System.out.println(" 3  Volver");
        System.out.println("============================================");
    }
    public void modifyStrengths() {
        System.out.println("============================================");
        System.out.println("           MODIFICAR FORTALEZAS");
        System.out.println("============================================");
        System.out.println("¿Que desea realizar?");
        System.out.println(" 1  Añadir debilidad");
        System.out.println(" 2  Eliminar debilidad");
        System.out.println(" 3  Volver");
        System.out.println("============================================");
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
    public void invalidValue() {
        System.out.println("El valor introducido no es válido");
    }
    public void lessThanZero() {
        System.out.println("El valor introducido no puede ser menor que 0");
    }
    public void moreThanMyGold(Integer gold) {
        final String YELLOW_BRIGHT = "\u001B[93m"; // Amarillo brillante
        final String RESET = "\u001B[0m";           // Resetear color
        System.out.println("El valor introducido no puede ser mayor \nque tu cantidad de oro, actualmente tienes "
                + YELLOW_BRIGHT + gold + " Monedas de Oro" + RESET + "!");
    }
    public void notAvaliableRival() {System.out.println("!No hay rivales disponibles en este momento!");}
    public void showAvaliableRivals(ArrayList<Client> users) {
        for (int i = 0; i < users.size(); i++) {
            Client user = users.get(i);
            System.out.printf(" %d %s \n", // 1 nick01
                    i + 1,
                    user.getNick());
        }
        System.out.println("===========================================");
    }
    public void alreadyInAChallenge() {
        System.out.println("!Ya estás participando en un desafío. No puedes iniciar otro.!");
    }

    public void validNumber() {
        System.out.println("Elige un numero valido");
    }
    public void characterArmors(List<Armor> armors) {
        System.out.println("Estas son las armas del personaje");
        for (int iterator = 0; iterator < armors.size(); iterator++) {
            System.out.println(iterator + 1 + " " + armors.get(iterator).getName());
        }
    }
    public void askForGoldBet(Client client) {
        final String YELLOW_BRIGHT = "\u001B[93m"; // Amarillo brillante
        final String RESET = "\u001B[0m";           // Resetear color
        System.out.println("Introduce la cantidad de oro que deseas apostar (>0) \nNOTA: Tu oro actual es de "
                + YELLOW_BRIGHT + client.getCharacter().getGold() + " Monedas de Oro" + RESET);
    }
    public void modifyArmor() {
        System.out.println("=======================================");
        System.out.println("       MODIFICACION DE ARMADURA");
        System.out.println("=======================================");
        System.out.println("       ¿Que deseas hacer?");
        System.out.println(" 1  Añadir armadura");
        System.out.println(" 2  Eliminar armadura");
        System.out.println(" 3  Volver");
        System.out.println("=======================================");
    }
    public void askArmorToDelete() {
        System.out.println("Introduce la armadura que quiere eliminar");
    }
    public void errorActiveArmor() {
        System.out.println("La armadura esta activa, no se puede eliminar");
    }
    public void showType() {
        System.out.println("Este es el tipo de personaje actual");
    }

    public void challengeCreated(){System.out.println(" \uD83D\uDDE1\uFE0F!Desafío creado y enviado al rival!\uD83D\uDDE1\uFE0F");}
    public void changeTeam() {
        System.out.println("=====================================================");
        System.out.println("                   CAMBIO DE EQUIPO");
        System.out.println("=====================================================");
        System.out.println("¿Quieres cambiar las armas y armaduras del personaje?");
        System.out.println(" 1 SI");
        System.out.println(" 2 NO");
        System.out.println("=====================================================");
    }
    public void inCombat() {
        System.out.println("En combate...");
    }
    public void mostrarCombate(Combat combate) {
        System.out.println("Combate: " + combate.getRegister());
        System.out.println("Desafiante: " + combate.getChallenger().getNick());
        System.out.println("Contrincante: " + combate.getRival().getNick());
        System.out.println("Fecha: " + combate.getDate());
        if (combate.isChallengerMinion()) {
            System.out.println("Esbirros de " + combate.getChallenger().getNick() + " : vivos");
        } else {
            System.out.println("Esbirros de " + combate.getChallenger().getNick() + " : muertos");
        }
        if (combate.isRivalMinion()) {
            System.out.println("Esbirros de " + combate.getRival().getNick() + " : vivos");
        } else {
            System.out.println("Esbirros de " + combate.getRival().getNick() + " : muertos");
        }
        System.out.println("Oro apostado: " + combate.getGold());
        System.out.println("Modificadores:");
        for (int numModificador = 0; numModificador < combate.getModifiers().size(); numModificador++) {
            System.out.println(combate.getModifiers().get(numModificador).getName());
        }
        System.out.println("RONDAS:");
        showRounds(combate);
    }

    public void askChallenge(Challenge challenge) {
        final String YELLOW_BRIGHT = "\u001B[93m"; // Amarillo brillante
        final String RESET = "\u001B[0m";           // Resetear color

        System.out.println("=======================================");
        System.out.println("           DESAFÍO ENTRANTE");
        System.out.println("=======================================");
        System.out.println("El usuario " + challenge.getChallenger().getNick() + " te ha desafiado.");
        System.out.println("Su apuesta es de " + YELLOW_BRIGHT + challenge.getGold() + " Monedas de Oro" + RESET);
        System.out.println("=======================================");
        System.out.println("     ¿Quieres aceptar el desafío?");
        System.out.println("=======================================");
        System.out.println("  1 SI ACEPTAR");
        System.out.println("  2 NO ACEPTAR");
        System.out.println("NOTA: si no aceptas perderás " + YELLOW_BRIGHT + (challenge.getGold() / 10) + " Monedas de Oro" + RESET);
        System.out.println("=======================================");
    }
    public void noDesafiosParaValidar() {
        System.out.println("No hay desafios a validar en este momento");
    }

    public void invalidQuantity(Client client) {
       System.out.println("Cantidad inválida. Introduce un valor entre 1 y " + client.getCharacter().getGold());
    }
    public void noCharacter(){
        System.out.println("El rival seleccionado no tiene personaje válido.");
    }
    public void autoRival(){
        System.out.println("Se ha seleccionado automáticamente al único rival disponible.");
    }
    public void preguntarBan(String desafiante, String contrincante) {
        System.out.println("El desafiante " + desafiante + " ha incumplido las normas de desafio, desafiando a " + contrincante + ", ¿Desea banearle?");
        System.out.println(" 1  Si");
        System.out.println(" 2  No");
    }
    public void showNicks(ArrayList<Client> clients) {
        System.out.println("=====================================");
        System.out.println("LISTA DE NICKS CON PERSONAJES CREADOS");
        System.out.println("=====================================");
        for (Client client : clients) {
            System.out.println(client.getNick());
        }
        System.out.println("=====================================");
    }
    public void characMinions(ArrayList<MinionsComposit> minionsComposits) {
        System.out.println("Estas son las fortalezas del personaje");
        for (int iterator = 0; iterator < minionsComposits.size(); iterator++) {
            System.out.println(iterator + 1 + ": " + minionsComposits.get(iterator).getName());
        }
    }
    public void modifyMinions() {
        System.out.println("=======================================");
        System.out.println("       MODIFICACION DE ESBIRROS");
        System.out.println("=======================================");
        System.out.println("¿Que desea realizar?");
        System.out.println(" 1  Añadir esbirro");
        System.out.println(" 2  Eliminar esbirro");
        System.out.println(" 3  Volver");
        System.out.println("=======================================");
    }
    public void askMinionToDelete() {
        System.out.println("Introduce el esbirro que quiere eliminar");
    }
    public void showGold(Client client) {
        System.out.println("Este es el oro actual "+ client.getCharacter().getGold());
    }

    public void askNickToAdmin() {
        System.out.println("Introduce el nick del usuario que desea modificar su personaje");
    }
    public void errorNick() {
        System.out.println("El nick introducido no corresponde con ningun cliente, \ncompueba los campos e introduce de nuevo el nick");
    }

    public void menuModifyCharacterAtributes() {
        System.out.println("===========================================");
        System.out.println("    MENU DE MODIFICACIÓN DE PERSONAJE");
        System.out.println("===========================================");
        System.out.println("Seleccione el atributo que desea modificar");
        System.out.println(" 1  Nombre");
        System.out.println(" 2  Habilidad");
        System.out.println(" 3  Armas");
        System.out.println(" 4  Armas activas");
        System.out.println(" 5  Armaduras");
        System.out.println(" 6  Armafuras activas");
        System.out.println(" 7  Esbirros");
        System.out.println(" 8  Oro");
        System.out.println(" 9  Hp");
        System.out.println(" 10 Poder");
        System.out.println(" 11 Debilidades");
        System.out.println(" 12 Fortalezas");
        System.out.println(" 13 Tipo");
        System.out.println(" 14 Salir para guardar cambios");
        System.out.println("===========================================");
    }
    public void savingChanges() {
        final String RESET = "\033[0m";
        String[] spinner = {"|", "/", "-", "\\"}; // Los 4 estados del "spinner"

        System.out.print("Guardando cambios... ");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones reducido
                System.out.print("\rGuardando cambios... " + spinner[i % 4]); // Sobreescribir la línea
                Thread.sleep(150); // Pausa de 150ms entre cada rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Imprimir una nueva línea después del "spinner"
    }
    public void deletingUser() {
        final String RESET = "\033[0m";
        String[] spinner = {"|", "/", "-", "\\"}; // Los 4 estados del "spinner"

        System.out.print("Eliminando usuario... ");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones reducido
                System.out.print("\rEliminando usuario... " + spinner[i % 4]); // Sobreescribir la línea
                Thread.sleep(150); // Pausa de 150ms entre cada rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Imprimir una nueva línea después del "spinner"
    }
    public void deletingCharacter() {
        final String RESET = "\033[0m";
        final String RED = "\033[91m";
        final String GREEN = "\033[92m";
        final String YELLOW = "\033[93m";
        final String BLUE = "\033[94m";

        String[] spinner = {
                RED + "|" + RESET,
                GREEN + "/" + RESET,
                YELLOW + "-" + RESET,
                BLUE + "\\" + RESET
        }; // Cada símbolo del spinner con un color diferente
        System.out.print("Eliminando personaje... ");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones
                System.out.print("\rEliminando personaje... " + spinner[i % 4]); // Sobreescribir la línea
                Thread.sleep(150); // Pausa de 150ms entre cada rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Nueva línea después del spinner
    }

    public void savingCharacter() {
        final String RESET = "\033[0m";
        final String RED = "\033[91m";
        final String GREEN = "\033[92m";
        final String YELLOW = "\033[93m";
        final String BLUE = "\033[94m";

        String[] spinner = {
                RED + "|" + RESET,
                GREEN + "/" + RESET,
                YELLOW + "-" + RESET,
                BLUE + "\\" + RESET
        }; // Cada símbolo con su color

        System.out.print("Dando vida a una criatura... ");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones
                System.out.print("\rDando vida a una criatura... " + spinner[i % 4]);
                Thread.sleep(200); // Pausa de 150ms
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Nueva línea después del spinner
    }
    public void equipingWeapons() {
        final String RESET = "\033[0m";
        String[] spinner = {"|", "/", "-", "\\"}; // Los 4 estados del "spinner"

        System.out.print("Configurando equipamiento...");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones reducido
                System.out.print("\rConfigurando equipamiento..." + spinner[i % 4]); // Sobreescribir la línea
                Thread.sleep(200); // Pausa de 150ms entre cada rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Imprimir una nueva línea después del "spinner"
    }
    public void userHaveOneWeapon(){
        System.out.println("Ya tienes un arma equipada, no puedes equipar otra");
    }

    public void savingNewUser() {
        final String RESET = "\033[0m";
        String[] spinner = {"|", "/", "-", "\\"}; // Los 4 estados del "spinner"

        System.out.print("Guardando nuevo usuario en el sistema... ");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones reducido
                System.out.print("\rGuardando nuevo usuario en el sistema... " + spinner[i % 4]); // Sobreescribir la línea
                Thread.sleep(200); // Pausa de 150ms entre cada rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Imprimir una nueva línea después del "spinner"
    }
    public void bannigUser() {
        final String RESET = "\033[0m";
        String[] spinner = {"|", "/", "-", "\\"}; // Los 4 estados del "spinner"

        System.out.print("Desterrando del reino digital... ");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones reducido
                System.out.print("\rDesterrando del reino digital... " + spinner[i % 4]); // Sobreescribir la línea
                Thread.sleep(200); // Pausa de 150ms entre cada rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Imprimir una nueva línea después del "spinner"
    }
    public void searchingRivals() {
        final String RESET = "\033[0m";
        String[] spinner = {"|", "/", "-", "\\"}; // Los 4 estados del "spinner"

        System.out.print("Explorando el reino en busca de nuevos rivales... ");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones reducido
                System.out.print("\rExplorando el reino en busca de nuevos rivales... " + spinner[i % 4]); // Sobreescribir la línea
                Thread.sleep(200); // Pausa de 150ms entre cada rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Imprimir una nueva línea después del "spinner"
    }
    public void unbanningUser() {
        final String RESET = "\033[0m";
        String[] spinner = {"|", "/", "-", "\\"}; // Los 4 estados del "spinner"

        System.out.print("Rompiendo las cadenas del exilio... ");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones reducido
                System.out.print("\rRompiendo las cadenas del exilio... " + spinner[i % 4]); // Sobreescribir la línea
                Thread.sleep(200); // Pausa de 150ms entre cada rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Imprimir una nueva línea después del "spinner"
    }
    public void showClashAnimation() {
        final String RESET = "\033[0m";
        final String RED = "\033[91m";
        final String YELLOW = "\033[93m";
        final String ORANGE = "\033[38;5;208m"; // Color naranja (código especial)

        String[] frames = {
                RED + "        >===>" + RESET + "                 " + RED + "<===<" + RESET,
                RED + "         >===>" + RESET + "               " + RED + "<===<" + RESET,
                ORANGE + "          >===>" + RESET + "             " + ORANGE + "<===<" + RESET,
                ORANGE + "           >===>" + RESET + "           " + ORANGE + "<===<" + RESET,
                YELLOW + "            >===>" + RESET + "         " + YELLOW + "<===<" + RESET,
                YELLOW + "             >===>" + RESET + "       " + YELLOW + "<===<" + RESET,
                YELLOW + "              >===>" + RESET + "     " + YELLOW + "<===<" + RESET,
                YELLOW + "               >===>" + RESET + "   " + YELLOW + "<===<" + RESET,
                YELLOW + "                >===>" + RESET + " " + YELLOW + "<===<" + RESET
        };

        String clashFrame = "\033[93;1m" + "              ⚔ CLASH ⚔" + RESET; // ⚔ CLASH ⚔ super amarillo brillante + negrita

        try {
            // Animación de las espadas
            for (String frame : frames) {
                System.out.print("\r" + frame);
                Thread.sleep(150); // 150ms entre frames
            }

            // Explosión épica en el CLASH
            System.out.print("\r" + clashFrame);
            Thread.sleep(600); // Mantener el CLASH un rato

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Salto de línea final
    }

    public void chargingUsers() {
        final String RESET = "\033[0m";
        String[] spinner = {"|", "/", "-", "\\"}; // Los 4 estados del "spinner"

        System.out.print("Cargando lista de usuarios... ");
        try {
            for (int i = 0; i < 10; i++) { // Número de iteraciones reducido
                System.out.print("\rCargando lista de usuarios... " + spinner[i % 4]); // Sobreescribir la línea
                Thread.sleep(100); // Pausa de 150ms entre cada rotación
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(); // Imprimir una nueva línea después del "spinner"
    }

    public void changesSaved() {
        final String GREEN_BRIGHT = "\033[1;92m";//verde brillante
        final String RESET = "\033[0m";
        System.out.println(GREEN_BRIGHT + "!Cambios guardados con éxito!" + RESET);
    }
    public void createdCharacterMsg() {
        final String MAGENTA_BRIGHT = "\033[1;95m"; // magenta brillante
        final String RESET = "\033[0m";
        System.out.println(MAGENTA_BRIGHT + "!Has dado vida a una criatura con éxito!" + RESET);
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
    public void askForHp() {System.out.println("Introduce la cantidad de vida, \nNOTA: Debe ser un valor entre 0 y 5 (incluídos)");}
    public void askForMinionsNum() {System.out.println("Introduce el numero de esbirros que deseas, \nNOTA: Debe ser un valor entre 0 y 3 (incluídos)");}
    public void askPower(){System.out.println("Introduce su poder, \nNOTA: Debe ser un valor entre 1 y 5 (incluídos)");}
    public void passwordTooShort(){
        System.out.println("Ups!, La contraseña debe ser de mínimo 8 caracteres y máximo 12.");
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

    /**Mensajes de los Vampiros**/
    public void askVampireAge() {
        System.out.println("=======================================");
        System.out.println("     ¿Qué edad tiene tu vampiro?");
        System.out.println("=======================================");
    }
    public void askVampireBlood() {System.out.println("Introduce la cantidad de sangre del vampiro, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos) ");}
    public void askVampireName() {System.out.println("Introduce el nombre del vampiro ");}
    public void askAbilityName() {System.out.println("Introduce el nombre de la habilidad ");}
    public void askAbilityAttack() {System.out.println("Introduce el valor máximo de ataque de la habilidad, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos) ");}
    public void askAbilityDefence() {System.out.println("Introduce el valor máximo de defensa de la habilidad, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos) ");}
    public void askCostAbility() {System.out.println("Introduce el coste máximo de la habilidad, \nNOTA: Debe ser un valor entre 1 y 3 (incluídos) ");}
    public void youHaveToCreateACharacter(){System.out.println("!Para poder desafiar a un usuario \ndebes antes crear un personaje!");}
    public void youDontHaveCharacter(){System.out.println("  No tienes ningún personaje creado" +
                                                          "\n      !Create un personaje!");}
    public void youDontHaveTeam() {System.out.println("No portas armas ni armaduras en tu travesía... ");}
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
    public List<Modifier> showChallengeModifiers(Client desafiante, Client contrincante) {
        List<Modifier> lista = new ArrayList<>();
        UserFileReader userFileReader = new UserFileReader();

        // Añadir fortalezas y debilidades del desafiante
        if (desafiante.getCharacter() != null) {
            List<Strength> fortalezasDes = desafiante.getCharacter().getStrengths();
            if (fortalezasDes != null) {
                for (Strength f : fortalezasDes) {
                    System.out.println(f.getName());
                    lista.add(f);
                }
            }
            List<Weakness> debilidadesDes = desafiante.getCharacter().getWeaknesses();
            if (debilidadesDes != null) {
                for (Weakness d : debilidadesDes) {
                    System.out.println(d.getName());
                    lista.add(d);
                }
            }
        }

        // Añadir fortalezas y debilidades del contrincante
        if (contrincante.getCharacter() != null) {
            List<Strength> fortalezasCon = contrincante.getCharacter().getStrengths();
            if (fortalezasCon != null) {
                for (Strength f : fortalezasCon) {
                    System.out.println(f.getName());
                    lista.add(f);
                }
            }
            List<Weakness> debilidadesCon = contrincante.getCharacter().getWeaknesses();
            if (debilidadesCon != null) {
                for (Weakness d : debilidadesCon) {
                    System.out.println(d.getName());
                    lista.add(d);
                }
            }
        }
        return lista;
    }

    public void validateChallenge() {
        System.out.println("========================================");
        System.out.println("     ¿Quieres validar este desafio?");
        System.out.println("========================================");
        System.out.println(" 1 SI");
        System.out.println(" 2 NO");
    }
    public void electModifiers() {
        System.out.println("Introduce las modificaciones para el combate," +
                           "\n      cuando acabes escribe 'salir'");
    }
    public void errorMod() {
        System.out.println(" El modificador introducido es erroneo,\n por favor indique los modificadores mostrados anteriormente");
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
    public void allusersAreBanned(){
        String colorCodeRed = "\033[0;31m"; // Rojo
        String resetCode = "\033[0m";
        System.out.println("Todos los usuarios del sistema han sido" +colorCodeRed+" baneados"+resetCode+ "\nNOTA: para acceder puedes registrarte como nuevo usuario\no, por otro lado, esperar a cumplir la condena.");}
    public void noNumberIn(){System.out.println("Debes introducir un número de usuario");}
    public void whyDoYouBannedThisUser(String username){System.out.println("¿Por qué se ha baneado a "+username+"? (opcional)");}
    public void userIsBanned(String nick){
        String colorCodeRed = "\033[0;31m"; // Rojo
        String resetCode = "\033[0m";
        System.out.println("El jugador '"+ nick + "' ha sido"+colorCodeRed+" baneado "+resetCode+"\npor el Administrador del sistema.");}

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
    public void finishEquipar() {
        System.out.println("Arma(s) y armadura equipadas correctamente...");
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
        System.out.println("Para confirmar el desbaneo escribe '"+colorCodeRed+"DESBANEAR"+resetCode+"', \npara cancelar pulsa cualquier tecla");
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

    public void askHunterName() {System.out.println("Introduce el nombre del cazador ");}

    public void askAbilityAge() {System.out.println("Introduce la edad a la que el cazador adquirio el talento:");}

    public void askWerewolfName() {System.out.println("Introduce el nombre del licántropo ");}

    public void askAbilityRage() {System.out.println("Introduce el valor minimo para activar la rabia. \nNOTA: Debe ser un valor entre 0 y 3 (incluídos)");}

    public void errorInNumberInserted() {System.out.println("!Has introducido una opción inválida!");}

    public void showTalent(Talent talent) {
        System.out.println("========================================");
        System.out.println("Nombre de habilidad " + talent.getName());
        System.out.println("Ataque de habilidad " + talent.getAttack());
        System.out.println("Defensa de habilidad " + talent.getDefense());
        System.out.println("Edad de habilidad " + talent.getAge());
        System.out.println("========================================");
    }
    public void showDiscipline(Discipline discipline) {
        System.out.println("========================================");
        System.out.println("Nombre de habilidad " + discipline.getName());
        System.out.println("Ataque de habilidad " + discipline.getAttack());
        System.out.println("Defensa de habilidad " + discipline.getDefense());
        System.out.println("Coste de habilidad " + discipline.getCost());
        System.out.println("========================================");
    }
    public void characWeapons(List<Weapon> weapons) {
        System.out.println("Estas son las armas del personaje");
        for (int iterator = 0; iterator < weapons.size(); iterator++) {
            System.out.println(iterator + 1 + " " + weapons.get(iterator).getName());
        }
    }
    public void askWeapToDelete() {
        System.out.println("Introduce el arma que quiere eliminar");
    }
    public void errorWeaponIsActive() {
        System.out.println("El arma esta activa, no se puede eliminar");
    }

    public void modifyWeapon() {
        System.out.println("========================================");
        System.out.println("         MODIFICACION DE ARMA");
        System.out.println("========================================");
        System.out.println("         ¿Que deseas modificar?");
        System.out.println(" 1  Añadir arma");
        System.out.println(" 2  Eliminar arma");
        System.out.println(" 3  Volver");
        System.out.println("========================================");
    }


}//FIN
