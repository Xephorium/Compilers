package scanner;

import model.FilteredCharacter;
import model.Token;

/* Chris Cruzen
 * Compilers
 * 04.04.2020
 *
 * Scanner represents the first step in the process of translating a custom
 * programming language. This class encapsulates the FSA Table lookup logic
 * responsible for converting raw stream input to tokens. Token identification
 * is performed with only a single character lookahead.
 */


public class Scanner {


    /*--- Variable Declarations ---*/

    private FsaTable fsaTable;
    private Filter filter;
    private boolean scanComplete = false;


    /*--- Constructor ---*/

    public Scanner(java.util.Scanner inputScanner) {
        this.fsaTable = new FsaTable();
        this.filter = new Filter(inputScanner);
    }


    /*--- Public Methods ---*/

    /* Returns the next available token until stream is empty, after which
     * a single EndOfFile Token is returned. Further calls will produce null.
     */
    public Token getNextToken() {

        // Short Circuit when Complete
        if (scanComplete) {
            return null;
        }

        // Declare Variables
        String tokenInstance = "";
        FilteredCharacter currentCharacter;
        FilteredCharacter lookAheadCharacter;
        int state = 0;
        int nextState = 0;

        // Begin Token Assembly
        while (true) {

            // Read Characters
            currentCharacter = filter.getCurrentCharacter();
            lookAheadCharacter = filter.getLookAheadCharacter();

            // Handle Final Character
            if (lookAheadCharacter == null) {
                if (tokenInstance.isEmpty()) {
                    scanComplete = true;
                    return new Token(Token.Type.EndOfFile, "", currentCharacter.getLine());
                } else {
                    nextState = fsaTable.getNextState(state, ' ');
                    return new Token(Token.Type.fromCode(nextState), tokenInstance + currentCharacter.getCharacter(), currentCharacter.getLine());
                }
            }

            // Get Current State
            state = fsaTable.getNextState(state, currentCharacter.getCharacter());

            // Check Current State For Error
            if (state == -1) {
                System.out.println("SCANNER ERROR: Invalid character '" + currentCharacter.getCharacter() + "' at line " + currentCharacter.getLine());
                System.exit(1);
            }

            // Get LookAhead State
            nextState = fsaTable.getNextState(state, lookAheadCharacter.getCharacter());

            // Check LookAhead State For Error
            if (nextState == -1) {
                System.out.println("SCANNER ERROR: Invalid character '" + lookAheadCharacter.getCharacter() + "' at line " + lookAheadCharacter.getLine());
                System.exit(1);
            }

            // Update Token Instance
            if (currentCharacter.getCharacter() != ' ') tokenInstance += currentCharacter.getCharacter();

            // Advance Stream Character
            filter.advanceCharacter();

            // Return Completed Token
            if (nextState > 500) {

                // Token Complete. Return.
                return new Token(Token.Type.fromCode(nextState), tokenInstance, currentCharacter.getLine());
            }
        }
    }
}
