package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public TreeMap<Integer, Integer> loadFuelData(String filename) {
        TreeMap<Integer, Integer> fuelMap = new TreeMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    int altitude = Integer.parseInt(parts[0].trim());
                    int fuel = Integer.parseInt(parts[1].trim());
                    fuelMap.put(altitude, fuel);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return fuelMap;
    }

    public int calculate(int startingHeight, int finalHeight,
            String fileName1, String fileName2) {
        final TreeMap<Integer, Integer> climbFuelConsumptionTable = loadFuelData(fileName1);
        final TreeMap<Integer, Integer> descentFuelConsumptionTable = loadFuelData(fileName2);
        // Have to resort to "magical numbers"
        double climbConsumptionBelowFiveK = 5000.0 / 280.0;
        double climbConsumptionAboveFiveK = 5000.0 / 90.0;
        double descentConsumptionAboveTwentyK = 5000.0 / 20.0;
        double descentConsumptionFromTwentyKtoFifteenK = 5000.0 / 30.0;
        double descentConsumptionFromFifteenKtoTenK = 5000.0 / 20.0;
        double descentConsumptionFromTenKtoFiveK = 5000.0 / 15.0;
        double descentConsumptionBelowFiveK = 5000.0 / 5.0;
        // if the plane is ascending
        if (startingHeight < finalHeight) {
            if (startingHeight < finalHeight) {
                if (startingHeight < 5000) {
                    int belowFiveThousandConsumption = (int) (climbConsumptionBelowFiveK * (5000 - startingHeight));
                    int aboveFiveThousandConsumption = (int) (climbConsumptionAboveFiveK * (finalHeight - 5000));
                    return belowFiveThousandConsumption + aboveFiveThousandConsumption;
                } else {
                    return (int) (climbConsumptionAboveFiveK * (finalHeight - 5000));
                }
            }
        }
        // if the plane is descending
        if (finalHeight < startingHeight) {
            int calculateDescentFuelConsumption = 0;
            if (5000 > startingHeight) {
                if (finalHeight < 0) {
                    int finalHeightToPositive = -finalHeight;
                    calculateDescentFuelConsumption += (int) descentConsumptionBelowFiveK * finalHeightToPositive;
                }
                calculateDescentFuelConsumption += (int) descentConsumptionBelowFiveK * startingHeight;
            }
        }
        // If there is no change in altitude
        return 0;
    }

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
        } catch (NumberFormatException e) {
            System.out.println("Give numbers representing feet as parameters!");
        }
    }

}
