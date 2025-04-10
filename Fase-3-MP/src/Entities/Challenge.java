package Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import System.*;

public class Challenge {
    /**A continuación se definen los atributos**/
    private Client rival;
    private Client challenger;
    private int gold;
    private ArrayList<Modifier> modifiers;
    private boolean validated;
    private String register;
    private Date date;

    /**A continuación se definen los setters y getters**/
    public void setRival(Client rival) {this.rival = rival;}
    public Client getRival() {return rival;}

    public void setChallenger(Client challenger) {this.challenger = challenger;}
    public Client getChallenger() {return challenger;}

    public void setGold(int gold) {this.gold = gold;}
    public int getGold() {return gold;}

    public void setModifiers(ArrayList<Modifier> modifiers) {this.modifiers = modifiers;}
    public ArrayList<Modifier> getModifiers() {return modifiers;}

    public void setValidated(boolean validated) {this.validated = validated;}
    public boolean isValidated() {return validated;}

    public void setDate(Date date) {this.date = date;}
    public Date getDate() {return date;}

    public void setRegister(String register) {this.register = register;}
    public String getRegister() {return register;}

    /**A continuación se definen las operaciones**/
    public void  createChallenge(Client client){
        Terminal terminal = new Terminal();
        Scanner scanner = new Scanner(System.in);
        UserFileReader userFileReader = new UserFileReader();
        ArrayList<Client> clientsLits = userFileReader.readUserFile();
        terminal.wellcomeChallenge();
        int goldAmount = -1;
        int rivalNum = -1;

        if (clientsLits.size() == 1) {
            terminal.notAvaliableRival();
        } else{
            terminal.showAvaliableRivals(clientsLits, client);
            do {
                terminal.validNumber();
                rivalNum = askForNumber();
            } while (rivalNum < 0 || rivalNum > clientsLits.size() + 1 || clientsLits.get(rivalNum - 1).getCharacter() == null);
            setRival(clientsLits.get(rivalNum - 1));
            terminal.askForGoldBet();
            do {
                terminal.validNumber();
                goldAmount = askForNumber();
            } while (goldAmount <= 0 || goldAmount > client.getCharacter().getGold());
            setGold(goldAmount);
            client.getCharacter().setGold(client.getCharacter().getGold() - goldAmount);
            for (int numCliente = 0; numCliente< clientsLits.size(); numCliente++){
                if (clientsLits.get(numCliente).getNick().equals(client.getNick())){
                    clientsLits.remove(numCliente);
                    clientsLits.add(client);
                    break;
                }
            }
            setChallenger(client);
            setValidated(false);
            String registro = generateRegisterNumber();
            setRegister(registro);
            ArrayList<Modifier> modifier = new ArrayList<>();
            setModifiers(modifier);
            Date todaysDate = new Date();
            setDate(todaysDate);
            terminal.challengeCreated();
            UserFileWriter userFileWriter = new UserFileWriter()   ;
            userFileWriter.rewriteUserFile(clientsLits);
            NotificationManager NotificationManager = new NotificationManager();
            NotificationManager.notifyChallenge(this);

        }
    }
    public int askForNumber(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    public String generateRegisterNumber(){
        return null;
    }
}//FIN
