package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Integer startingHeight = null;
        Integer finalHeight = null;
        if (args == null || args.length == 0) {
            System.out.println("Provide arguments to run the program.");
            System.exit(0);
        }
        if (args.length != 2) {
            System.out.println("Give exactly two arguments!");
            System.exit(0);
        }
        try {
            startingHeight = Integer.parseInt(args[0]);
            finalHeight = Integer.parseInt(args[1]);
        } catch(NumberFormatException e) {
            System.out.println("Give numbers representing feet as parameters!");
        }
        if (startingHeight < 0 || finalHeight < 0) {
            System.out.println("Give positive numbers as parameters!");
            System.exit(0);
        }
    }
}
