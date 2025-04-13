package Entities;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import System.*;


public class Administrator extends User{
    /**A continuación se definen Atributos**/
    private String register;
    /**A continuación se define el constructor**/

    /**A continuación se definen los setters y getters**/

    public String getRegister() {
        return register;
    }
    public void setRegister(String register) {
        this.register = register;
    }

    /**A continuación se definen las Operaciones**/

    /**
     * Elimina permanentemente una cuenta del sistema
     * @param admin Usuario logueado (puede ser Client o Administrator)
     * @param system Referencia al sistema principal
     */
    public void deleteAdminAccount(Administrator admin, mainSystem system) {
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);

        // Mostrar advertencia y solicitar confirmación
        terminal.advertency();
        terminal.writeConfirm();

        // Leer confirmación
        String confirmation = sc.nextLine().trim();

        if (confirmation.equalsIgnoreCase("ELIMINAR")) {
            try {
                // Leer lista actual de administradores
                AdministratorFileReader adminFileReader = new AdministratorFileReader();
                ArrayList<Administrator> adminList = adminFileReader.adminFileReader();

                // Buscar y eliminar por nick (case-sensitive)
                boolean removed = adminList.removeIf(a -> a.getNick().equals(admin.getNick()));

                if (removed) {
                    // Guardar lista actualizada
                    AdministratorFileWriter adminFileWriter = new AdministratorFileWriter();
                    adminFileWriter.rewriteUserFile(adminList);

                    // Cerrar sesión
                    terminal.deletedAccountOK();
                    terminal.logout();
                    system.selector();
                } else {
                    terminal.noAccountAvaliable();
                }
            } catch (Exception e) {
                terminal.error();
                terminal.error();
                e.getMessage(); // Log para depuración
            }
        } else {
            terminal.cancelOperation();
            terminal.closedSesion4Security();
            system.selector();
        }
    }

    public void modifyCharacter(){}
    public void validatingChallenge(){}


    //DESBANEAR
    public void unbanUser() {
        UserFileReader userFileReader = new UserFileReader();
        UserFileWriter userFileWriter = new UserFileWriter();
        BanFileReader banFileReader = new BanFileReader();
        BanFileWriter banFileWriter = new BanFileWriter();
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);

        // Leer los usuarios y baneos
        ArrayList<Client> userList = userFileReader.userFileReader();
        ArrayList<String> bannedClients = banFileReader.bannedReader();

        if (bannedClients.isEmpty()) {
            terminal.noUsersBannedError();
            return;
        }

        terminal.allBannedUsers(bannedClients);
        terminal.whatUserToUnBan();

        try {
            int selection = sc.nextInt();
            sc.nextLine();

            if (selection == 0) {
                return;
            }

            if (selection < 1 || selection > bannedClients.size()) {
                terminal.invalidSelecction();
                return;
            }

            String bannedUserInfo = bannedClients.get(selection - 1);
            String[] bannedParts = bannedUserInfo.split("\\|");
            String bannedNick = bannedParts[0];

            terminal.confirmUnban(bannedNick);
            String confirm = sc.nextLine().trim().toUpperCase();

            if (confirm.equals("DESBANEAR")) {
                // 1. Eliminar de la lista de baneados
                bannedClients.remove(selection - 1);

                // 2. Buscar el cliente en la lista de usuarios
                Client unbannedClient = null;
                for (Client client : userList) {
                    if (client.getNick().equals(bannedNick)) {
                        unbannedClient = client;
                        break;
                    }
                }

                if (unbannedClient != null) {
                    // 3. Verificar si ya está en userList (por si acaso)
                    boolean alreadyInList = false;
                    for (Client client : userList) {
                        if (client.getNick().equals(bannedNick)) {
                            alreadyInList = true;
                            break;
                        }
                    }

                    if (!alreadyInList) {
                        userList.add(unbannedClient);
                    }

                    // 4. Reescribir ambos archivos
                    userFileWriter.rewriteUserFile(userList); // Esto mantendrá el formato especial
                    banFileWriter.rewriteBanFile(bannedClients);

                    terminal.unbbanedUser(bannedNick);
                } else {
                    terminal.error();
                }
            } else {
                terminal.cancelOperation();
            }
        } catch (InputMismatchException e) {
            terminal.noNumberIn();
            sc.nextLine();
        } catch (Exception e) {
            terminal.error();
            e.printStackTrace();
        }
    }
    //BANEO
    public void banUser(Client client) {
        UserFileReader userFileReader = new UserFileReader();
        UserFileWriter userFileWriter = new UserFileWriter();
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);

        ArrayList<Client> userList = userFileReader.userFileReader();
        terminal.allUsers(userList); // Mostrar lista de usuarios

        terminal.whatUserToBan(); // Mensaje tipo "¿Qué usuario deseas banear?"
        int selection;

        try {
            selection = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            if (selection == 0) {
                terminal.cancelOperation();
                return;
            }

            if (selection < 1 || selection > userList.size()) {
                terminal.invalidSelecction();
                return;
            }

            Client userToBan = userList.get(selection - 1);
            String username = userToBan.getNick();

            terminal.confirmBan(username);
            String confirm = sc.nextLine().trim().toUpperCase();

            terminal.whyDoYouBannedThisUser(username);
            String bannedBecause = sc.nextLine().trim();

            terminal.howManyHours();

            int numHours = -1;
            // Validar que el número de horas sea positivo
            while (numHours <= 0) {
                if (sc.hasNextInt()) {
                    numHours = sc.nextInt();
                    sc.nextLine(); // Limpiar buffer después de leer el número
                    if (numHours <= 0) {
                        terminal.invalidNumberOfHours(); // Asegúrate de tener este mensaje en tu Terminal
                    }
                } else {
                    terminal.invalidNumberOfHours(); // Mensaje para cuando el valor no es un número
                    sc.nextLine(); // Limpiar buffer
                }
            }

            if (confirm.equals("BANEAR")) {
                BanFileWriter banWriter = new BanFileWriter();
                banWriter.banUser(userToBan, bannedBecause, numHours);

                userList.remove(selection - 1); // Eliminar de la lista
                userFileWriter.rewriteUserFile(userList); // Reescribir el archivo sin el baneado

                terminal.banned(username);
            } else {
                terminal.cancelOperation();
            }

        } catch (InputMismatchException e) {
            terminal.noNumberIn();
            sc.nextLine(); // Limpiar input inválido
        } catch (Exception e) {
            terminal.error();
            e.printStackTrace();
        }
    }


}
