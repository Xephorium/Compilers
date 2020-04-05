
/* Chris Cruzen
 * Compilers
 * 04.04.2020
 *
 * Scanner represents the first step in the process of translating a custom
 * programming language. This class encapsulates the logic of converting raw
 * scanner input to tokens and returning those tokens to be used by a parser.
 */


import java.util.ArrayList;
import java.util.Arrays;

public class Scanner {


    /*--- Variable Declarations ---*/

    private FsaTable fsaTable;
    private Filter filter;


    /*--- Constructor ---*/

    public Scanner(java.util.Scanner inputScanner) {
        this.fsaTable = new FsaTable();
        this.filter = new Filter(inputScanner);
    }


    /*--- Public Methods ---*/

    // Returns the next available token, or null if no further tokens are available.
    public Token getNextToken() {
        FilteredCharacter currentCharacter = filter.getCurrentCharacter();
        FilteredCharacter lookAheadCharacter = filter.getLookAheadCharacter();
        filter.iterate();


        if (currentCharacter == null) {
            return null;
        } else {
            return new Token(
                    Token.Type.Undefined,
                    currentCharacter.getCharacter() + "",
                    currentCharacter.getLine()
            );
        }
    }
}
