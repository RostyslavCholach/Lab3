package Game.Droid.Helper;

import Game.Droid.Usual.Droid;
import Game.Weapon.Weapon;

public class HelperDroid extends Droid {
    private int medicineChest;

    public HelperDroid(String name, int health, Weapon weapon, int medicineChest) {
        super(name, health, weapon);
        this.medicineChest = medicineChest;
    }

    public int getMedicineChest() {
        return medicineChest;
    }

    public void setMedicineChest(int medicineChest) throws Exception {
        if (medicineChest < 0) {
            throw new Exception("Incorrect value of medicine chest!");
        }
        this.medicineChest = medicineChest;
    }

    @Override
    public void regenerateHealth(Droid teammate)throws Exception {
        if (this.medicineChest >= 20) {
            teammate.setHealth(teammate.getHealth() + 20);
            if (teammate.getHealth() > teammate.getMaxHealth()) {
                teammate.setHealth(teammate.getMaxHealth());
            }
            this.medicineChest -= 20;
        }
    }

    @Override
    public HelperDroid clone() {
        return new HelperDroid(this.getName(), this.getHealth(), this.getWeapon(), this.medicineChest);
    }

    @Override
    public String toString() {
        return "   Helper droid: " + this.getName() + "\n   health = " + this.getHealth() + "\n   first aid kit = " + medicineChest + this.getWeapon().toString();
    }
}
