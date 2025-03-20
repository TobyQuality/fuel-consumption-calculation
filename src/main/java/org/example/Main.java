package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
// import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    /**
     * Calculates the fuel consumption based on altitude changes during ascent or
     * descent.
     * 
     * @param startingHeight The initial altitude in feet.
     * @param finalHeight    The final altitude in feet.
     * @return The amount of fuel consumed during the altitude change.
     */
    /**
     * Calculates the fuel consumption based on altitude changes during ascent or
     * descent.
     * 
     * @param startingHeight The initial altitude in feet.
     * @param finalHeight    The final altitude in feet.
     * @return The amount of fuel consumed during the altitude change.
     */
    public static int calculate(int startingHeight, int finalHeight) {
        // Have to resort to "magical numbers"
        double climbConsumptionBelowFiveK = 280.0 / 5000;
        double climbConsumptionAboveFiveK = 90.0 / 5000;
        double descentConsumptionAboveTwentyK = 20.0 / 5000;
        double descentConsumptionFromTwentyKtoFifteenK = 30.0 / 5000;
        double descentConsumptionFromFifteenKtoTenK = 20.0 / 5000;
        double descentConsumptionFromTenKtoFiveK = 15.0 / 5000;
        double descentConsumptionBelowFiveK = 5.0 / 5000;
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

            if (startingHeight < 5000) {
                if (finalHeight < 0) {
                    int finalHeightToPositive = Math.abs(finalHeight);
                    calculateDescentFuelConsumption += (int) (descentConsumptionBelowFiveK *
                            finalHeightToPositive);
                    calculateDescentFuelConsumption += (int) (descentConsumptionBelowFiveK * startingHeight);
                    return calculateDescentFuelConsumption;
                }
                return calculateDescentFuelConsumption += (int) (descentConsumptionBelowFiveK
                        * (startingHeight - finalHeight));
            }

            if (startingHeight >= 20000) {
                if (finalHeight >= 20000) {
                    return (int) (descentConsumptionAboveTwentyK * (startingHeight - finalHeight));
                }
                calculateDescentFuelConsumption += (int) (descentConsumptionAboveTwentyK * (startingHeight - 20000));
            }
            if (startingHeight >= 15000) {
                if (startingHeight < 20000 && startingHeight >= 15000) {
                    if (finalHeight >= 15000) {
                        calculateDescentFuelConsumption += (int) (descentConsumptionFromTwentyKtoFifteenK
                                * (startingHeight - finalHeight));
                        return (int) calculateDescentFuelConsumption;
                    }
                    calculateDescentFuelConsumption += (int) (descentConsumptionFromTwentyKtoFifteenK
                            * ((startingHeight - 15000)));
                } else {
                    if (finalHeight >= 15000) {
                        calculateDescentFuelConsumption += (int) (descentConsumptionFromTwentyKtoFifteenK
                                * (20000 - finalHeight));
                        return (int) calculateDescentFuelConsumption;
                    }
                    calculateDescentFuelConsumption += (int) (descentConsumptionFromTwentyKtoFifteenK * 5000);
                }
            }
            if (startingHeight >= 10000) {
                if (startingHeight < 15000 && startingHeight >= 10000) {
                    if (finalHeight >= 10000) {
                        calculateDescentFuelConsumption += (int) (descentConsumptionFromFifteenKtoTenK
                                * (startingHeight - finalHeight));
                        return (int) calculateDescentFuelConsumption;
                    }
                    calculateDescentFuelConsumption += (int) (descentConsumptionFromFifteenKtoTenK
                            * (startingHeight - 10000));
                } else {
                    if (finalHeight >= 10000) {
                        calculateDescentFuelConsumption += (int) (descentConsumptionFromFifteenKtoTenK
                                * (15000 - finalHeight));
                        return (int) calculateDescentFuelConsumption;
                    }
                    calculateDescentFuelConsumption += (int) (descentConsumptionFromFifteenKtoTenK * 5000);
                }
            }
            if (startingHeight > 5000) {
                if (startingHeight < 10000 && startingHeight > 5000) {
                    if (finalHeight >= 5000) {
                        calculateDescentFuelConsumption += (int) (descentConsumptionFromTenKtoFiveK
                                * (startingHeight - finalHeight));
                        return (int) calculateDescentFuelConsumption;
                    }
                    calculateDescentFuelConsumption += (int) (descentConsumptionFromTenKtoFiveK
                            * (startingHeight - 5000));
                } else {
                    if (finalHeight > 5000) {
                        calculateDescentFuelConsumption += (int) (descentConsumptionFromTenKtoFiveK
                                * (10000 - finalHeight));
                        return (int) calculateDescentFuelConsumption;
                    }
                    calculateDescentFuelConsumption += (int) (descentConsumptionFromTenKtoFiveK * 5000);
                }
            }
            if (finalHeight < 0) {
                int finalHeightToPositive = Math.abs(finalHeight);
                calculateDescentFuelConsumption += (int) (descentConsumptionBelowFiveK *
                        finalHeightToPositive);
                calculateDescentFuelConsumption += (int) (descentConsumptionBelowFiveK * 5000);
                return calculateDescentFuelConsumption;
            }
            calculateDescentFuelConsumption += (int) (descentConsumptionBelowFiveK * (5000 - finalHeight));
            return calculateDescentFuelConsumption;
        }
        // If there is no change in altitude
        return 0;
    }

    /**
     * The main method that reads altitude values from command-line arguments,
     * parses them into integers, and calculates fuel consumption.
     *
     * @param args Command-line arguments representing starting and final altitudes.
     */
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
        int fuelConsumptionCalculation = calculate(startingHeight, finalHeight);
        System.out.println("The fuel consumption is: " + fuelConsumptionCalculation);
    }

    
    
    // public TreeMap<Integer, Integer> loadFuelData(String filename) {
    // TreeMap<Integer, Integer> fuelMap = new TreeMap<>();

    // try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
    // String line;
    // while ((line = br.readLine()) != null) {
    // String[] parts = line.split(",");
    // if (parts.length == 2) {
    // int altitude = Integer.parseInt(parts[0].trim());
    // int fuel = Integer.parseInt(parts[1].trim());
    // fuelMap.put(altitude, fuel);
    // }
    // }
    // } catch (IOException e) {
    // System.err.println("Error reading file: " + e.getMessage());
    // }

    // return fuelMap;
    // }
}
