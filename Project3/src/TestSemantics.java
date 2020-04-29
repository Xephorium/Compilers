
import model.nodes.Node;
import parser.Parser;
import semantics.StaticSemanticsTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* Chris Cruzen
 * Compilers
 * 04.29.2020
 *
 * TestSemantics is the root class of Project3. This class takes source code
 * either as a filename to read or as a command line input stream. It then
 * passes a stream of that input to the Parser, which validates the code's
 * grammatical correctness and builds a parse tree of tokens. Finally, this
 * class passes the tree to StaticSemanticsTable which verifies correct
 * source code variable usage.
 */


public class TestSemantics {


    /*----- Global Constants -----*/

    private final static String INPUT_FILE_EXTENSION = ".sp2020";
    private final static String ERROR_EXTRA_ARGUMENTS = "Too many arguments. Aborting.";
    private final static String ERROR_EMPTY_INPUT = "Empty input provided. Aborting.";
    private final static String ERROR_READING_FILE = "Error reading file. Aborting.";


    /*----- Main Method -----*/

    public static void main(String[] args) {


        /*--- Variable Declarations ---*/

        String inputFileName;
        Scanner inputScanner = new Scanner(System.in);


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

        if (inputScanner != null && !inputScanner.hasNext()) {
            System.out.println(ERROR_EMPTY_INPUT);
            System.exit(0);

        }


        /*--- Build Parse Tree ---*/

        Parser parser = new Parser(inputScanner);
        Node parseTree = parser.parse();


        /*--- Verify Static Semantics ---*/

        StaticSemanticsTable table = new StaticSemanticsTable(parseTree);


    }


    /*----- Utility Methods -----*/

    private static Scanner readFile(String filename) {

        try {

            // Return File Scanner
            File inputFile = new File(filename);
            return new Scanner(inputFile);

        } catch (FileNotFoundException exception) {

            // Handle File Read Error
            System.out.println(ERROR_READING_FILE);
            System.exit(0);
            return null;
        }
    }

    private static Scanner readInputStream() {

        // Return Input Stream
        return new Scanner(System.in);
    }
}
