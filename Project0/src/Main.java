
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Chris Cruzen
 * Compilers
 * 02.19.2020
 *
 * Main is the root class of Project0. This class takes user input
 * either as a filename to read or as a command line input stream.
 * It then passes that input to the Tree class and delegates the
 * construction and traversal of a bidirectional parse tree of
 * strings.
 */

public class Main {


    /*----- Global Constants -----*/

    private final static String INPUT_FILE_EXTENSION = ".sp2020";
    private final static String ERROR_EXTRA_ARGUMENTS = "Too many arguments. Aborting.";
    private final static String ERROR_EMPTY_INPUT = "Empty input provided. Aborting.";
    private final static String ERROR_READING_FILE = "Error reading file. Aborting.";


    /*----- Main Method -----*/

    public static void main(String[] args) {


        /*--- Variable Declarations ---*/

        String input = "";


        /*--- Parse Input ---*/

        if (args.length > 1) {
            System.out.println(ERROR_EXTRA_ARGUMENTS);
            System.exit(0);
        } else if (args.length == 1) {
            input = readFile(args[0]);
        } else {
            input = readInputStream();
        }


        /*--- Validate Input ---*/

        if (input.length() == 0) {
            System.out.println(ERROR_EMPTY_INPUT);
            System.exit(0);

        }


        /*--- Build Parse Tree ---*/

        Tree parseTree = new Tree();
        parseTree.buildTree(input);


        /*--- Print Outputs ---*/

        //parseTree.printInorder();
        //parseTree.printPreorder();
        parseTree.printPostorder();
    }


    /*----- Utility Methods -----*/

    private static String readFile(String filename) {

        // Verify Filename Format
        if (!filename.contains(INPUT_FILE_EXTENSION)) {
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
            System.out.println(ERROR_READING_FILE);
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
