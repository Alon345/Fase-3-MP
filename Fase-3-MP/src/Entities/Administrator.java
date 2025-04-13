package Entities;

import System.mainSystem;
import  System.AdministratorFileReader;
import System.AdministratorFileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import System.Terminal;

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
    public void unbanUser(){}
}
