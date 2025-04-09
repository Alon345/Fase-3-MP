package Entities;

import System.Terminal;

import java.util.ArrayList;

public class Round {
    /**
     * A continuación se definen los atributos
     **/
    private int hpChallengerEnd;
    private int hpRivalEnd;

    /**
     * A continuación se definen las operaciones
     **/
    public int getHpChallengerEnd() {
        return hpChallengerEnd;
    }

    public void setHpDesafianteEnd(int hpChallengerEnd) {
        this.hpChallengerEnd = hpChallengerEnd;
    }

    public int getHpRivalEnd() {
        return hpRivalEnd;
    }

    public void setHpRivalEnd(int hpRivalEnd) {
        this.hpRivalEnd = hpRivalEnd;
    }
   // public void startRound(hpChallengerEnd, hpRivalEnd) {

    //}
}