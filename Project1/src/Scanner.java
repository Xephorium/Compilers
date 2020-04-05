
/* Chris Cruzen
 * Compilers
 * 04.04.2020
 *
 * Scanner represents the first step in the process of translating a custom
 * programming language. This class encapsulates the logic of converting raw
 * scanner input to tokens and returning those tokens to be used by a parser.
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

    // Returns the next available token, or null if no further tokens are available.
    public Token getNextToken() {

        // Short Circuit when Complete
        if (scanComplete) {
            return null;
        }

        // Create Variables
        String tokenInstance = "";
        FilteredCharacter currentCharacter;
        FilteredCharacter lookAheadCharacter;
        int state = 0;
        int nextState = 0;

        // Begin Assembly Loop
        while (true) {

            // Get Characters
            currentCharacter = filter.getCurrentCharacter();
            lookAheadCharacter = filter.getLookAheadCharacter();

            // Handle Final Character
            if (lookAheadCharacter == null) {
                if (tokenInstance.isEmpty()) {
                    scanComplete = true;
                    return new Token(Token.Type.EndOfFile, "", currentCharacter.getLine());
                } else {
                    return new Token(Token.Type.Undefined, tokenInstance + currentCharacter.getCharacter(), currentCharacter.getLine());
                }
            }

            // Get State
            state = fsaTable.getNextState(state, currentCharacter.getCharacter());
            nextState = fsaTable.getNextState(state, lookAheadCharacter.getCharacter());

            // Check Initial State For Error
            if (state == -1) {
                System.out.println("SCANNER ERROR: Invalid character '" + currentCharacter.getCharacter() + "' at line " + currentCharacter.getLine());
                System.exit(1);
            }

            // Check Next State For Error
            if (nextState == -1) {
                System.out.println("SCANNER ERROR: Invalid character '" + lookAheadCharacter.getCharacter() + "' at line " + lookAheadCharacter.getLine());
                System.exit(1);
            }

            // Update Token Instance
            tokenInstance += currentCharacter.getCharacter();

            filter.iterate();

            // Check for Token End
            if (nextState > 500) {
                // Token Complete. Return.
                return new Token(Token.Type.Undefined, tokenInstance, currentCharacter.getLine());
            }
        }
    }
}
