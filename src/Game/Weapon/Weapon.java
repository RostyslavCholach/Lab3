package Game.Weapon;

import java.util.Random;

public class Weapon {
    private String name;
    private int damage;
    private int accuracy;

    public Weapon(String name, int damage, int accuracy) {
        this.name = name;
        this.damage = damage;
        this.accuracy = accuracy;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int makeShot() {
        Random random = new Random();
        int damage = this.damage;
        int percent = random.nextInt(21) - 10;
        int probabilityOfMiss = random.nextInt(100 - this.accuracy + 1);
        if (probabilityOfMiss > 100 - this.accuracy) {
            return 0;
        } else {
            return (int)(damage + (percent * damage)/100.0);
        }
    }

    @Override
    public String toString() {
        if (this.damage > 0) {
            return "\n   Weapon: " + name + "\n   damage = " + damage + "\n   accuracy = " + accuracy + "%";
        }
        return "";
    }
}
