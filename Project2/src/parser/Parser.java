package parser;

import model.Token;
import scanner.Scanner;

/* Chris Cruzen
 * Compilers
 * 04.18.2020
 *
 * Parser represents the second step in the process of translating a custom
 * programming language. This class encapsulates the logic of using recursive
 * descent parsing to evaluate a program's grammatical correctness and build
 * a corresponding parse tree.
 */


public class Parser {


    /*--- Variable Declarations ---*/

    private Scanner scanner;


    /*--- Constructor ---*/

    public Parser(java.util.Scanner inputScanner) {
        scanner = new Scanner(inputScanner);
        parse();
    }


    /*--- Private Methods ---*/

    private void parse() {

        // Print Tokens
        System.out.println("Token List: ({id, instance, line})");
        Token token = scanner.getNextToken();
        while (token != null) {
            System.out.println("\t" + token);
            token = scanner.getNextToken();
        }
    }
}
