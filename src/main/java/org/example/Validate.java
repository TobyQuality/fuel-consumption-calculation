package org.example;

public class Validate {
    public boolean validate(String[] args) {
        boolean isValid = true;

        if (args == null || args.length == 0) {
            System.out.println("Provide arguments to run the program.");
            isValid = false;
        }
        if (args.length != 2) {

        }

        return isValid;
    }
}
