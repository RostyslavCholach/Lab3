package Game.BattleArena;

import Game.Droid.Usual.Droid;
import java.util.Random;

public class SingleBattle {
    private Droid yourDroid;
    private Droid opponent;

    public SingleBattle(Droid yourDroid, Droid opponent) {
        this.yourDroid = yourDroid;
        this.opponent = opponent;
    }

    public Droid getYourDroid() {
        return yourDroid;
    }

    public void setYourDroid(Droid yourDroid) {
        this.yourDroid = yourDroid;
    }

    public Droid getOpponent() {
        return opponent;
    }

    public void setOpponent(Droid opponent) {
        this.opponent = opponent;
    }

    public void Fight()throws Exception {
        Random random = new Random();
        Droid winner;
        int lastDamage;
        System.out.println("\nBattle log");
        System.out.println("-------------------------------------------------------------------");
        while (yourDroid.isAlive() && opponent.isAlive()) {
            if (random.nextInt(2) == 0) {
                lastDamage = yourDroid.hitOpponent(opponent);
                System.out.printf("Your droid %s hit opponent droid %s with damage %d\n", yourDroid.getName(), opponent.getName(), lastDamage);
            } else {
                lastDamage = opponent.hitOpponent(yourDroid);
                System.out.printf("Opponent droid %s hit your droid %s with damage %d\n", opponent.getName(), yourDroid.getName(), lastDamage);
            }
            System.out.printf("Your health = %d\nOpponent health = %d\n",yourDroid.getHealth(),opponent.getHealth());
        }
        System.out.println("-------------------------------------------------------------------");
        if (yourDroid.isAlive()) {
            System.out.println("\n\nYOUR DROID WON!!!\n");
        } else {
            System.out.printf("\n\nYour droid lost!\nThe winner is:\n%s", opponent);
        }
    }
}
