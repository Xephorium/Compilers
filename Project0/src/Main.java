

/* Chris Cruzen
 * Compilers
 * 02.19.2020
 *
 * Main is the root class of Project0 and contains the
 * project's core execution logic.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {


    /*----- Global Constants -----*/

    private final static String INPUT_FILE_EXTENSION = ".sp2020";


    /*----- Main Method -----*/

    public static void main(String[] args) {


        /*--- Variable Declarations ---*/

        String input = "";


        /*--- Parse Input ---*/

        if (args.length > 1) {
            System.out.println("Too many arguments. Aborting.");
            System.exit(0);
        } else if (args.length == 1) {
            input = readFile(args[0]);
        } else {
            input = readInputStream();
        }


        /*--- Validate Input ---*/

        if (input.length() == 0) {
            System.out.println("Empty input provided. Aborting.");
            System.exit(0);

        }


        /*--- Build Parse Tree ---*/

        Tree parseTree = new Tree();
        parseTree.buildTree(input);
        System.out.println("Input: " + input);


        /*--- Print Outputs ---*/

        parseTree.printInorder();
        parseTree.printPreorder();
        parseTree.printPostorder();
    }


    /*----- Utility Methods -----*/

    private static String readFile(String filename) {

        // Verify Filename Format
        Pattern pattern = Pattern.compile(INPUT_FILE_EXTENSION + "\\$");
        Matcher matcher = pattern.matcher(filename);
        if (!matcher.matches()) {
            filename = filename + INPUT_FILE_EXTENSION;
        }

        try {

            // Read From File
            File inputFile = new File(filename);
            Scanner scanner = new Scanner(inputFile);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNext()) {
                builder.append(" ");
                builder.append(scanner.next());
            }
            scanner.close();

            // Return Contents
            return builder.toString().trim();

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file. Aborting.");
            System.exit(0);
            return "";
        }
    }

    private static String readInputStream() {

        // Read Input Stream
        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNext()) {
            builder.append(" ");
            builder.append(scanner.next());
        }
        scanner.close();

        // Return Input
        return builder.toString().trim();
    }
}
