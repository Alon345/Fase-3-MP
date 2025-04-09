package System;

import Entities.Client;
import Entities.Combat;

import java.util.ArrayList;

public class Terminal {
    public Terminal(){
    }
    /**Mensajes iniciales**/

    public void showStart() {
        System.out.println("---------------------------------------");
        System.out.println("Bienvenido a Shadow Clash!");
        System.out.println("¿Que deseas hacer?");
        System.out.println("  1 Registrarse");
        System.out.println("  2 Iniciar sesión (modo jugador)"); //jugador = cliente
        System.out.println("  3 Iniciar sesión (modo administrador)");
        System.out.println("---------------------------------------");
    }
    public void showMenu() {
        System.out.println("------------ MENU ------------");
        System.out.println("  1 Registrar personaje");
        System.out.println("  2 Eliminar personaje");
        System.out.println("  3 Seleccionar equipo");
        System.out.println("  4 Desafiar a un jugador");
        System.out.println("  5 Consultar combates");
        System.out.println("  6 Consultar ranking global");
        System.out.println("  7 Salir");
        System.out.println("  8 Borrar cuenta :(");
        System.out.println("-----------------------------");
    }

    /**Mensajes de los usuarios**/

    public void userRegistrerMenu() {
        System.out.println("Registro de nuevo usuario");
        System.out.println("--------------------------------");
        System.out.println("¿En qué modo se desea registrar?");
        System.out.println("  1 Modo Jugador");
        System.out.println("  2 Modo Administrador");
        System.out.println("  3 Salir");
        System.out.println("--------------------------------");
    }
    public void confirmDeleteAccount() {
        System.out.println("¿Estas seguro de querer eliminar la cuenta?");
        System.out.println(" 1 Si :(");
        System.out.println(" 2 No :)");
    }
    public void error() {
        System.out.println("!Error!");
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
    public void logout() {
        System.out.println("Cerrando sesion... Un momento");
    }

    /**Mensajes de los personajes y lo relacionado a ellos**/

    public void deleteCharacter() {
        System.out.println("Para crear un personaje nuevo antes tienes que eliminar el existente");
    }
    public void confirmDeleteCharacter() {
        System.out.println("¿Seguro que desea eliminar el personaje?");
        System.out.println(" 1 Si");
        System.out.println(" 2 No");
    }
    public void deltedCharacter() {
        System.out.println("Personaje eliminado correctamente");
    }

    public void showFactories() {
        System.out.println("Seleccione que tipo de personaje va a crear:");
        System.out.println(" 1 Vampiro");
        System.out.println(" 2 Licantropo");
        System.out.println(" 3 Cazador");
    }

    /**Mensajes de los desafíos**/

    public void wellcomeChallenge() {
        System.out.println("Bienvenido al menu de desafios");
        System.out.println(" Escoge a un rival ");
    }
    public void notAvaliableRival() {System.out.println("No hay rivales disponibles en este momento!");}
    public void showAvaliableRivals(ArrayList<Client> clientArrayList, Client client) {
        System.out.println("------------------------");
        System.out.println("0 Cancelar");
        for (int numClient = 0; numClient < clientArrayList.size(); numClient++) {
            if (clientArrayList.get(numClient).getCharacter() != null && !clientArrayList.get(numClient).getNick().equals(client.getNick())) {
                System.out.println((numClient + 1) + ": " + clientArrayList.get(numClient).getNick());
            }
        }
        System.out.println("------------------------");
    }
    public void validNumber() {
        System.out.println("Elige un numero valido");
    }
    public void askForGoldBet() {
        System.out.println("Introduce la cantidad de oro que deseas apostar ");
    }
    public void challengeCreated(){System.out.println("Desafío creado y enviado al rival!");}

    /**Mensajes de los esbirros**/
    public void askMinionType() {
        System.out.println("Selecciona tu tipo de esbirro:");
        System.out.println("------------------------------");
        System.out.println("1 Humano");
        System.out.println("2 Ghoul");
        System.out.println("3 Demonio");
        System.out.println("------------------------------");
    }
    public void askMinionName() {
        System.out.println("Introduce el nombre del esbirro: ");
    }
    public void askForHp() {
        System.out.println("Introduce la cantidad de vida:");
    }
    public void askForMinionsNum() {
        System.out.println("Introduce el numero de esbirros que deseas:");
    }


    /**Mensajes de los Humanos**/
    public void errorHuman() {
        System.out.println("Como vampiro, los humanos no pueden ser tus esbirros. Elige otro tipo de criatura.");
    }
    public void askForLoyalty() {
        System.out.println("Introduce la lealtad de tu humano:");
        System.out.println("----------------------------------");
        System.out.println("1 Alta");
        System.out.println("2 Media");
        System.out.println("3 Baja");
        System.out.println("---------------------------------");
    }

    /**Mensajes de los Demonios**/
    public void askForPact() {
        System.out.println("Introduce el pacto del demonio:");
    }


    /**Mensajes de las RONDAS**/
    public void showRounds(Combat combat) {
        for (int numOfRound = 0; numOfRound < combat.getRounds().size(); numOfRound++) {
            System.out.println("Ronda " + (numOfRound+1) + " :");
            System.out.println("Vida de " + combat.getChallenger().getCharacter().getName() + " al final de la ronda: " + combat.getRounds().get(numOfRound).getHpChallengerEnd());
            System.out.println("Vida de " + combat.getRival().getCharacter().getName() + " al final de la ronda: " + combat.getRounds().get(numOfRound).getHpRivalEnd());
        }
        System.out.println("FIN DEL COMBATE");
        if (combat.getWinner() != null) {
            System.out.println("Vencedor " + combat.getWinner().getNick());
        } else {
            System.out.println("!Ha habido un empate!");
        }
    }
    public void showRound(int numOfRound) {
        System.out.println("Ronda número " + numOfRound + ":");
    }

} //FIN
