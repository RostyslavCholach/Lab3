package Game.BattleArena;

import Game.Droid.Usual.Droid;

import java.util.Random;


public class TeamBattle {
    Droid[] yourTeam;
    Droid[] opponentTeam;

    public TeamBattle(Droid[] yourTeam, Droid[] opponentTeam) {
        this.yourTeam = yourTeam;
        this.opponentTeam = opponentTeam;
    }

    public Droid[] getYourTeam() {
        return yourTeam;
    }

    public void setYourTeam(Droid[] yourTeam) throws NullPointerException {
        if (yourTeam == null) {
            throw new NullPointerException("Your team is a null pointer!");
        }
        this.yourTeam = yourTeam;
    }

    public Droid[] getOpponentTeam() {
        return opponentTeam;
    }

    public void setOpponentTeam(Droid[] opponentTeam) throws NullPointerException {
        if (opponentTeam == null) {
            throw new NullPointerException("Opponent team is a null pointer!");
        }
        this.opponentTeam = opponentTeam;
    }

    public boolean teamIsAlive(Droid[] team) {
        for (int i = 0; i < team.length; i++) {
            if (team[i].isAlive()) {
                return true;
            }
        }
        return false;
    }

    public void fight(Droid[] yourTeam, Droid[] opponentTeam) throws Exception{
        int lastDamage;
        System.out.println("-------------------------------------------------------------------");
        Random random = new Random();
        while (teamIsAlive(yourTeam) && teamIsAlive(opponentTeam)) {

            for (int i = 0; i < yourTeam.length; i++) {
                if (yourTeam[i].isAlive()) {
                    lastDamage = yourTeam[i].hitOpponent(opponentTeam[i]);
                    System.out.printf("Your droid %s hit opponent droid %s with damage %d\n", yourTeam[i].getName(), opponentTeam[i].getName(), lastDamage);
                    yourTeam[random.nextInt(yourTeam.length)].regenerateHealth(yourTeam[random.nextInt(yourTeam.length)]);
                }
                if (opponentTeam[i].isAlive()) {
                    opponentTeam[i].hitOpponent(yourTeam[i]);
                    lastDamage = opponentTeam[i].hitOpponent(opponentTeam[i]);
                    System.out.printf("Opponent droid %s hit your droid %s with damage %d\n", opponentTeam[i].getName(), yourTeam[i].getName(), lastDamage);
                    opponentTeam[random.nextInt(yourTeam.length)].regenerateHealth(opponentTeam[random.nextInt(yourTeam.length)]);
                }
            }
        }
        System.out.println("-------------------------------------------------------------------");
        if (teamIsAlive(yourTeam)) {
            System.out.println("\n\nYOUR TEAM WON!!!\n");
        } else {
            System.out.println("\n\nYOUR TEAM LOST!\n");

        }
    }
}
