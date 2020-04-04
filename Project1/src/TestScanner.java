
import java.io.File;
import java.io.FileNotFoundException;

/* Chris Cruzen
 * Compilers
 * 04.04.2020
 *
 * TestScanner is the root class of Project1. This class takes user input
 * either as a filename to read or as a command line input stream. It then
 * passes a stream of that input to the Scanner class and iterates through
 * each token generated by the scanner.
 */


public class TestScanner {


    /*----- Global Constants -----*/

    private final static String INPUT_FILE_EXTENSION = ".sp2020";
    private final static String ERROR_EXTRA_ARGUMENTS = "Too many arguments. Aborting.";
    private final static String ERROR_EMPTY_INPUT = "Empty input provided. Aborting.";
    private final static String ERROR_READING_FILE = "Error reading file. Aborting.";


    /*----- Main Method -----*/

    public static void main(String[] args) {


        /*--- Variable Declarations ---*/

        String inputFileName = null;
        java.util.Scanner inputScanner = new java.util.Scanner(System.in);


        /*--- Parse Input ---*/

        if (args.length > 1) {
            System.out.println(ERROR_EXTRA_ARGUMENTS);
            System.exit(0);
        } else if (args.length == 1) {

            // Verify Filename Format
            inputFileName = args[0];
            if (!inputFileName.contains(INPUT_FILE_EXTENSION)) {
                inputFileName = inputFileName + INPUT_FILE_EXTENSION;
            }

            // Get File Scanner
            inputScanner = readFile(inputFileName);
        } else {

            // Get Input Scanner
            inputScanner = readInputStream();
        }


        /*--- Validate Input ---*/

        if (!inputScanner.hasNext()) {
            System.out.println(ERROR_EMPTY_INPUT);
            System.exit(0);

        }


        /*--- Create Scanner ---*/

        Scanner scanner = new Scanner(inputScanner);


        /*--- Print Tokens ---*/

        Token token = scanner.getNextToken();
        while (!token.toString().equals("{}")) {
            System.out.println(token);
            token = scanner.getNextToken();
        }

    }


    /*----- Utility Methods -----*/

    private static java.util.Scanner readFile(String filename) {

        try {

            // Return File Scanner
            File inputFile = new File(filename);
            return new java.util.Scanner(inputFile);

        } catch (FileNotFoundException exception) {

            // Handle File Read Error
            System.out.println(ERROR_READING_FILE);
            System.exit(0);
            return null;
        }


//            java.util.Scanner fileScanner = new java.util.Scanner(inputFile);
//            StringBuilder builder = new StringBuilder();
//            while (fileScanner.hasNext()) {
//                builder.append(" ");
//                builder.append(fileScanner.next());
//            }
//            fileScanner.close();
//
//            // Return Contents
//            return builder.toString().trim();
    }

    private static java.util.Scanner readInputStream() {

        // Return Input Stream
        return new java.util.Scanner(System.in);

//        java.util.Scanner scanner = new java.util.Scanner(System.in);
//
//        StringBuilder builder = new StringBuilder();
//        while (scanner.hasNext()) {
//            builder.append(" ");
//            builder.append(scanner.next());
//        }
//        scanner.close();
//
//        // Return Input
//        return builder.toString().trim();
    }
}
