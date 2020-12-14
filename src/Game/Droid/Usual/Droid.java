package Game.Droid.Usual;

import Game.Weapon.Weapon;







public class Droid implements Cloneable {
    private String name;
    private int health;
    private int maxHealth;
    private Weapon weapon;


    public Droid(String name, int health, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.weapon = weapon;
        this.maxHealth = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) throws Exception {
        if (maxHealth < 0) {
            throw new Exception("Incorrect value of max health!");
        }
        this.maxHealth = maxHealth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health)throws Exception {
        if (health < 0) {
            throw new Exception("Health can not be negative!");
        }
        this.health = health;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        if (weapon == null) {
            throw new NullPointerException("Weapon is null pointer!");
        }
        this.weapon = weapon;
    }

    public int takeHit(int damage) throws Exception{
        int current_health = this.getHealth() - damage;
        if (current_health < 0) {
            damage += this.getHealth();
            this.health = 0;
        }
        else{
            this.setHealth(current_health);
        }
        return damage;
    }

    public int hitOpponent(Droid opponent)throws Exception {
        int damage = this.weapon.makeShot();
        return opponent.takeHit(damage);
    }

    public void regenerateHealth(Droid teammate) throws Exception{
        if ((double) this.health / (double) this.maxHealth >= 0.5) {
            teammate.health += 5;
            this.health -= 5;
            if (teammate.health > teammate.maxHealth) {
                teammate.health = teammate.maxHealth;
            }
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public Droid clone() {
        return new Droid(this.name, this.health, this.weapon);
    }

    @Override
    public String toString() {
        return "   Droid: " + name + "\n   health = " + health + weapon.toString();
    }
}
