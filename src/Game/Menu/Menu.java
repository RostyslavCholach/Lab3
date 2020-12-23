package Game.Menu;
//
import Game.BattleArena.SingleBattle;
import Game.BattleArena.TeamBattle;
import Game.Droid.Armored.ArmoredDroid;
import Game.Droid.Helper.HelperDroid;
import Game.Droid.Usual.Droid;
import Game.Weapon.Weapon;


import java.util.Random;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);
            Weapon[] weapons = new Weapon[10];
            weapons[0] = new Weapon("No weapon", 0, 0);
            weapons[1] = new Weapon("Blaster pistol DL-21", 8, 90);
            weapons[2] = new Weapon("Blaster pistol SE-44C", 9, 88);
            weapons[3] = new Weapon("Blaster pistol WESTAR-35", 12, 80);
            weapons[4] = new Weapon("Blaster rifle EL-16HFE", 12, 80);
            weapons[5] = new Weapon("Blaster rifle E-11", 14, 65);
            weapons[6] = new Weapon("Blaster rifle A280", 18, 85);
            weapons[7] = new Weapon("FWMB-10 repeating blaster", 22, 60);
            weapons[8] = new Weapon("Grenade launcher", 80, 20);
            weapons[9] = new Weapon("DXR-6 disruptor rifle", 250, 30);

            Droid[] droids = new Droid[6];
            droids[0] = new Droid("B1", 100, weapons[0]);
            droids[1] = new Droid("G-100 MagnaGuard", 100, weapons[0]);
            droids[2] = new ArmoredDroid("Super B2", 150, weapons[0], 50);
            droids[3] = new ArmoredDroid("Super Tactical Droid", 170, weapons[0], 30);
            droids[4] = new HelperDroid("Medical FX-6", 70, weapons[0], 100);
            droids[5] = new HelperDroid("Medical 21B", 80, weapons[0], 90);


            System.out.println("Press 1 - show list of available droids");
            System.out.println("Press 2 - show list of available weapon");
            System.out.println("Press 3 - choose mode of battle");
            System.out.println("Press 4 - exit");
            while (true) {
                System.out.print("\n\nWhat do you want to do?\nYour choice: ");
                int menu = in.nextInt();
                switch (menu) {
                    case 1: {
                        showDroids(droids);
                        break;
                    }
                    case 2: {
                        showWeapon(weapons);
                        break;
                    }
                    case 3: {
                        System.out.println("\nChoose the mode of battle: \n1 - Droid vs droid\n2 - Team vs team");
                        System.out.print("\nYour choice: ");
                        int mode = in.nextInt();
                        if (mode == 1) {
                            Droid yourDroid = prepareDroidForBattle(droids, weapons);
                            Droid opponent = createOpponent(droids, weapons);
                            System.out.println("\nYour opponent is: \n" + opponent);
                            SingleBattle singleBattle = new SingleBattle(yourDroid, opponent);
                            singleBattle.Fight();
                        }
                        if (mode == 2) {
                            Droid[] yourTeam = prepareTeamForBattle(droids, weapons);
                            Droid[] opponentTeam = createOpponentsTeam(droids, weapons, yourTeam.length);
                            System.out.println("\n\nYour team is:");
                            showTeam(yourTeam);
                            System.out.println("\n\nOpponent team is:");
                            showTeam(opponentTeam);
                            TeamBattle teamBattle = new TeamBattle(yourTeam, opponentTeam);
                            teamBattle.fight(yourTeam, opponentTeam);
                        }
                        break;
                    }
                    case 4: {
                        return;
                    }
                    default: {
                        throw new Exception("Incorrect value of choice in menu!");
                    }
                }
            }
        } catch (Exception error) {
            System.out.println(error);
        }
    }

    public static void showTeam(Droid[] team) {
        System.out.println("///////////////////////////////////");
        for (int i = 0; i < team.length; i++) {
            System.out.println(team[i]);
            System.out.println("----------------------------------");
        }
        System.out.println("///////////////////////////////////");
    }

    public static Droid[] prepareTeamForBattle(Droid[] droids, Weapon[] weapons) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("\nChoose how many droids in team you want\nPress 1 - 2 droids\nPress 2 - 5 droids\nPress 3 - 7 droids\n");
        System.out.print("Your choice: ");
        int numberOfDroids = in.nextInt();
        switch (numberOfDroids) {
            case 1: {
                numberOfDroids = 2;
                break;
            }
            case 2: {
                numberOfDroids = 5;
                break;
            }
            case 3: {
                numberOfDroids = 7;
                break;
            }
            default: {
                throw new Exception("Incorrect value of number of droids!");
            }
        }
        Droid[] team = new Droid[numberOfDroids];
        for (int i = 0; i < numberOfDroids; i++) {
            team[i] = prepareDroidForBattle(droids, weapons);
            System.out.flush();
        }
        return team;
    }

    public static Droid[] createOpponentsTeam(Droid[] droids, Weapon[] weapons, int numberOfTeammate) {
        Droid[] opponentTeam = new Droid[numberOfTeammate];
        for (int i = 0; i < numberOfTeammate; i++) {
            opponentTeam[i] = createOpponent(droids, weapons);
        }
        return opponentTeam;
    }

    public static Droid createOpponent(Droid[] droids, Weapon[] weapons) {
        Random random = new Random();
        int index = random.nextInt(6);
        Droid opponent = droids[index].clone();
        index = random.nextInt(9) + 1;
        opponent.setWeapon(weapons[index]);
        return opponent;
    }

    public static Droid prepareDroidForBattle(Droid[] droids, Weapon[] weapons) {
        Droid droid = chooseDroid(droids);
        droid.setWeapon(chooseWeapon(weapons));
        System.out.println("\nYour droid is ready for battle!\n" + droid);
        return droid;
    }


    public static Droid chooseDroid(Droid[] droids) {
        Scanner in = new Scanner(System.in);
        System.out.println("Print list of available droids for game again?\n1 - yes \\ 0 - no");
        if (in.nextInt() == 1) {
            showDroids(droids);
        }
        System.out.print("Choose droid: ");
        int indexOfDroid = in.nextInt();
        System.out.println("Your choice:\n" + droids[indexOfDroid - 1]);
        return droids[indexOfDroid - 1].clone();
    }

    public static Weapon chooseWeapon(Weapon[] weapons) {
        Scanner in = new Scanner(System.in);
        System.out.println("Print list of available weapon for game again?\n1 - yes \\ 0 - no");
        if (in.nextInt() == 1) {
            showWeapon(weapons);
        }
        System.out.print("Choose weapon: ");
        int indexOfWeapon = in.nextInt();
        System.out.println("Your choice:\n" + weapons[indexOfWeapon]);
        return weapons[indexOfWeapon];
    }

    public static void showWeapon(Weapon[] weapons) {
        System.out.println("\nList of available for game weapons");
        System.out.println("----------------------------------");
        for (int i = 1; i < weapons.length; i++) {
            System.out.printf("Press %d\n%s\n\n", i, weapons[i]);
        }
        System.out.println("----------------------------------");
    }

    public static void showDroids(Droid[] droids) {
        System.out.println("\nList of available for game droids");
        System.out.println("----------------------------------");
        for (int i = 0; i < droids.length; i++) {
            System.out.printf("Press %d\n%s\n\n", i + 1, droids[i]);
        }
        System.out.println("----------------------------------");

    }
}
