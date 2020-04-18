package parser;

import model.Token;
import model.Token.Type;
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
    private Token currentToken;


    /*--- Constructor ---*/

    public Parser(java.util.Scanner inputScanner) {
        scanner = new Scanner(inputScanner);
        currentToken = scanner.getNextToken();
    }


    /*--- Public Methods ---*/

    public void parse() {
        program();
    }


    /*--- Private Methods ---*/

    private void program() {
        variables();
        block();
        return;
    }

    private void block() {
        checkForToken(Type.OpenBrace);
        variables();
        //statements();
        checkForToken(Type.CloseBrace);
        return;
    }

    private void variables() {
        if (currentToken.getType() == Type.Declare) {
            consumeToken();
        } else {
            // No More Variables - Do Nothing
            return;
        }

        checkForToken(Type.Identifier);
        checkForToken(Type.SassyColon);
        checkForToken(Type.Number);
        checkForToken(Type.Semicolon);
        variables();
        return;
    }

    private Token checkForToken(Type type) {
        if (currentToken.getType() == type) {
            Token tempToken = currentToken;
            consumeToken();
            return tempToken;
        } else {
            error(type);
            return null; // Unreachable
        }
    }

    private void consumeToken() {
        System.out.println("Token consumed: " + currentToken.getInstance() + " (" + currentToken.getType() + ").");
        currentToken = scanner.getNextToken();
    }

    private void error(Token.Type expected) {
        System.out.println("Error (" + currentToken.getLine() + "): Expected " + expected + " token, found \'"
                + currentToken.getInstance() + "\'."
        );
        System.exit(1);
    }

}
