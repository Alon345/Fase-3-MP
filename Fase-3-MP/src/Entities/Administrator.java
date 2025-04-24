package Entities;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
        BanFileReader banFileReader = new BanFileReader();
        BanFileWriter banFileWriter = new BanFileWriter();
        Terminal terminal = new Terminal();
        Scanner sc = new Scanner(System.in);

        // Leer usuarios y baneados
        ArrayList<Client> userList = userFileReader.userFileReader();
        ArrayList<Client> bannedClients = banFileReader.readBannedUsers();

        if (bannedClients.isEmpty()) {
            terminal.noUsersBannedError();
            return;
            }

        // Mostrar lista de baneados numerada
        terminal.showBannedUsers(bannedClients);
        terminal.whatUserToUnBan();

        try {
            int selection = sc.nextInt();
            sc.nextLine();

            if (selection == 0) {
                terminal.cancelOperation();
                return;
            }

            if (selection < 1 || selection > bannedClients.size()) {
                terminal.noNumberIn();
                return;
            }

            Client userToUnban = bannedClients.get(selection - 1);
            String bannedNick = userToUnban.getNick();

            terminal.confirmUnban(bannedNick);
            String confirm = sc.nextLine().trim().toUpperCase();

            if (confirm.equals("DESBANEAR")) {
                // 1. Eliminar del archivo de baneados
                bannedClients.remove(userToUnban);
                userToUnban.setBanMotive("");
                banFileWriter.rewriteBanFile(bannedClients);
                terminal.unbbanedUser(bannedNick);

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
        BanFileReader  banFileReader  = new BanFileReader();
        BanFileWriter  banWriter      = new BanFileWriter();
        Terminal       terminal       = new Terminal();
        Scanner        sc             = new Scanner(System.in);

        // 1) Cargo todas las listas
        ArrayList<Client> allUsers      = userFileReader.userFileReader();
        ArrayList<Client> bannedClients = banFileReader.readBannedUsers();

        // 2) Preparo la lista de candidatos a banear (todos - ya baneados)
        Set<String> bannedNicks = bannedClients.stream()
                .map(Client::getNick)
                .filter(Objects::nonNull)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());

        ArrayList<Client> toBan = allUsers.stream()
                .filter(u -> {
                    String nick = u.getNick();
                    return nick != null && !bannedNicks.contains(nick.toLowerCase());
                })
                .collect(Collectors.toCollection(ArrayList::new));

        if (toBan.isEmpty()) {
            terminal.noUsersToBanError();  // “No hay usuarios disponibles para banear”
            return;
        }

        // 3) Muestro sólo los no baneados
        terminal.allUsers(toBan);
        terminal.whatUserToBan();

        try {
            int selection = sc.nextInt();
            sc.nextLine();

            if (selection == 0) {
                terminal.cancelOperation();
                return;
            }
            if (selection < 1 || selection > toBan.size()) {
                terminal.invalidSelecction();
                return;
            }

            Client userToBan = toBan.get(selection - 1);
            String nick     = userToBan.getNick();

            terminal.confirmBan(nick);
            String confirm = sc.nextLine().trim().toUpperCase();
            if (!confirm.equals("BANEAR")) {
                terminal.cancelOperation();
                return;
            }

            // Razón del baneo
            terminal.whyDoYouBannedThisUser(nick);
            String motivo = sc.nextLine().trim();
            if (motivo.isEmpty()) motivo = "Sin motivo especificado";

            // Fijamos datos de baneo
            userToBan.setBanMotive(motivo);
            LocalDateTime ahora = LocalDateTime.now();
            userToBan.setBanDateTime(ahora);

            // 4) Añadimos al fichero de baneos
            banWriter.banUser(userToBan);

            terminal.banned(nick);

        } catch (InputMismatchException e) {
            terminal.noNumberIn();
            sc.nextLine();
        } catch (Exception e) {
            terminal.error();
            e.printStackTrace();
        }
    }

}
