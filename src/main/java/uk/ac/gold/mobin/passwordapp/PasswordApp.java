package uk.ac.gold.mobin.passwordapp;

import uk.ac.gold.mobin.passwordapp.utils.Generator;
import org.apache.commons.cli.*;

public class PasswordApp {

    public static void main(String[] args) {

        Options options = new Options();

        // Core configuration options
        options.addOption(Option.builder("l")
                .longOpt("length")
                .hasArg(true)
                .argName("number")
                .desc("Password length (default: 10)")
                .build());

        options.addOption(Option.builder("n")
                .longOpt("count")
                .hasArg(true)
                .argName("number")
                .desc("Number of passwords to generate (default: 1)")
                .build());

        // Character set exclusions
        options.addOption(Option.builder().longOpt("no-upper").desc("Exclude uppercase letters").build());
        options.addOption(Option.builder().longOpt("no-lower").desc("Exclude lowercase letters").build());
        options.addOption(Option.builder().longOpt("no-digits").desc("Exclude digits").build());
        options.addOption(Option.builder().longOpt("no-symbols").desc("Exclude symbols").build());

        // Required character constraints
        options.addOption(Option.builder().longOpt("require-upper").desc("Require at least one uppercase letter").build());
        options.addOption(Option.builder().longOpt("require-lower").desc("Require at least one lowercase letter").build());
        options.addOption(Option.builder().longOpt("require-digit").desc("Require at least one digit").build());
        options.addOption(Option.builder().longOpt("require-symbol").desc("Require at least one symbol").build());

        options.addOption(Option.builder("h").longOpt("help").desc("Show help").build());

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        int length = 10;
        int count = 1;

        boolean includeUpper = true;
        boolean includeLower = true;
        boolean includeDigits = true;
        boolean includeSymbols = true;

        boolean requireUpper = false;
        boolean requireLower = false;
        boolean requireDigit = false;
        boolean requireSymbol = false;

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h")) {
                formatter.printHelp("password-generator", options);
                return;
            }

            if (cmd.hasOption("l")) {
                length = Integer.parseInt(cmd.getOptionValue("l"));
            }

            if (cmd.hasOption("n")) {
                count = Integer.parseInt(cmd.getOptionValue("n"));
            }

            // Apply inclusion/exclusion flags
            if (cmd.hasOption("no-upper")) includeUpper = false;
            if (cmd.hasOption("no-lower")) includeLower = false;
            if (cmd.hasOption("no-digits")) includeDigits = false;
            if (cmd.hasOption("no-symbols")) includeSymbols = false;

            // Apply required constraints
            if (cmd.hasOption("require-upper")) requireUpper = true;
            if (cmd.hasOption("require-lower")) requireLower = true;
            if (cmd.hasOption("require-digit")) requireDigit = true;
            if (cmd.hasOption("require-symbol")) requireSymbol = true;

            if (length < 1) {
                throw new IllegalArgumentException("Length must be >= 1");
            }

            if (count < 1) {
                throw new IllegalArgumentException("Count must be >= 1");
            }

            // Prevent logically impossible configurations
            int requiredClasses = 0;
            if (requireUpper) requiredClasses++;
            if (requireLower) requiredClasses++;
            if (requireDigit) requiredClasses++;
            if (requireSymbol) requiredClasses++;

            if (length < requiredClasses) {
                throw new IllegalArgumentException("Length is too small for the required character classes.");
            }

            Generator generator = new Generator(
                    includeUpper, includeLower, includeDigits, includeSymbols,
                    requireUpper, requireLower, requireDigit, requireSymbol
            );

            for (int i = 0; i < count; i++) {
                System.out.println(generator.generate(length));
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            formatter.printHelp("password-generator", options);
            System.exit(1);
        }
    }
}