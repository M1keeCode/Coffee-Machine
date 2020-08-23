package com.jukov.max;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CoffeeMachine {
    /*
    Coffee-Machine emulator.
    Made by Max Zhukov.
     */
    private static final Scanner moneyAmountIn = new Scanner(System.in);
    private static final Scanner chooseIn = new Scanner(System.in);
    private static final String[] drinkNames = {"Cappuccino", "Hot Milk", "Espresso", "Water"};
    private static final double[] drinksPrices = {1.99, 1.49, 1.19, 0.49};
    private static String[] chooseNumsCheck = {"1", "2", "3", "4"};
    private static boolean[] canBuySmth = {false, false, false, false};
    private static double moneyAmount = 0;
    private static int checkArr;
    private static boolean rightVar = false;
    private static String choose;

    public static void main(String[] args) {

        say("---Coffee Machine---");
        wait(500);
        say("Loading...");
        wait(1500);

        say("We have the following drinks: ");

        for (int num = 0; num < drinkNames.length; num++) {
            wait(350);
            say(num + 1 + ": " + drinkNames[num] + " - " + drinksPrices[num] + "$");
        }
        wait(500);

        say("\n" + "Insert money ($): ");
        try {
            moneyAmount = moneyAmountIn.nextDouble();
        } catch (InputMismatchException e) {
            say("(×) Invalid number entered. ");
            System.exit(0);
        }

        moneyAmount = Math.round(moneyAmount * 100.0) / 100.0;
        say("Your money: " + moneyAmount + "$");
        wait(350);

        moneyInit();

        while (!rightVar) { // Checking user choose
            say("\n" + "Your choose: (enter number of the drink)");
            choose = chooseIn.nextLine();
            for (checkArr = 0; checkArr < canBuySmth.length; checkArr++) {
                if (canBuySmth[checkArr] && choose.equals(chooseNumsCheck[checkArr]) || choose.equalsIgnoreCase(drinkNames[checkArr])) {
                    rightVar = true;
                    choose();
                }
            }
        }

        moneyAmountIn.close();
        chooseIn.close();
    }

    public static void say(String message) { // A simplified output method
        System.out.println(message);
    }

    public static void wait(int time) { // A simplified thread.sleep method
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void moneyInit() { // Checking money amount method
        for (int moneyInit = 0; moneyInit < canBuySmth.length; moneyInit++) {
            if (moneyAmount >= drinksPrices[moneyInit]) {
                canBuySmth[moneyInit] = true;
            } else if (moneyAmount < drinksPrices[3]) {
                say("(×) You don't have money for any drinks.");
                System.exit(0);
            }
        }
    }

    public static void choose() { // Choosed drink method
        wait(500);
        say("You choosed: " + drinkNames[checkArr]);
        moneyAmount = moneyAmount - drinksPrices[checkArr];
        wait(500);
        say("Preparing your drink. Wait please...");
        wait(500);
        for (int i = 0; i < 10; i++) {
            wait(400);
            System.out.print("▉ ");
        }
        say("\n");
        wait(400);
        say("Your " + drinkNames[checkArr] + " is ready! (✔)");
        wait(400);
        say("Your change: ");
        System.out.printf("%.2f", moneyAmount);
        System.out.print("$");
        wait(250);
        say("\n" + "Bye bye! <3");
        say("---------------------------------");
        say("© Made by Max Zhukov.");
    }
}
