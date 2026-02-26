package uk.ac.gold.mobin.passwordapp;

import uk.ac.gold.mobin.passwordapp.utils.Generator;
import org.apache.commons.cli.*;

public class PasswordApp {

    public static void main(String[] args) {

        Options options = new Options();

        Option lengthOption = Option.builder("l")
                .longOpt("length")
                .hasArg(true)
                .argName("number")
                .desc("Length of the password")
                .required(false)
                .build();

        options.addOption(lengthOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        int passwordLength = 10;

        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("l")) {
                passwordLength = Integer.parseInt(cmd.getOptionValue("l"));
            }

        } catch (Exception e) {
            formatter.printHelp("password-generator", options);
            System.exit(1);
        }

        Generator generator = new Generator();
        String password = generator.generate(passwordLength);

        System.out.println("Generated password (" + passwordLength + " chars): " + password);
    }
}