package Game.Droid.Armored;


import Game.Droid.Usual.Droid;
import Game.Weapon.Weapon;


public class ArmoredDroid extends Droid implements Cloneable {
    private int armor;

    public ArmoredDroid(String name, int health, Weapon weapon, int armor) {
        super(name, health, weapon);
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) throws Exception {
        if(armor<0){
            throw new Exception("Incorrect value of armor!");
        }
        this.armor = armor;
    }

    @Override
    public int takeHit(int damage)throws Exception {
        if (this.armor >= damage) {
            this.armor -= damage;
            this.setHealth(this.getHealth()-damage/2);
            return damage/2;
        } else {
            return super.takeHit(damage);
        }
    }
    @Override
    public ArmoredDroid clone() {
        return new ArmoredDroid(this.getName(),this.getHealth(),this.getWeapon(),this.armor);
    }
    @Override
    public String toString() {
        return "   Armored droid: " + this.getName() + "\n   health = "  +this.getHealth()+"\n   armor = "+this.getArmor() + this.getWeapon().toString();
    }
}
