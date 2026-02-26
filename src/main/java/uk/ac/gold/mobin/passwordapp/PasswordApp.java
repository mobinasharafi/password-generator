package uk.ac.gold.mobin.passwordapp;

import uk.ac.gold.mobin.passwordapp.utils.Generator;

public class PasswordApp {

    public static void main(String[] args) {

        int passwordLength = 10;

        // Parse command-line argument if provided
        if (args.length == 1) {
            try {
                passwordLength = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument " + args[0] + " must be an integer.");
                System.exit(1);
            }
        }

        // Create generator
        Generator generator = new Generator();

        // Generate password
        String password = generator.generate(passwordLength);

        // Print result
        System.out.println("Generated password (" + passwordLength + " chars): " + password);
    }
}