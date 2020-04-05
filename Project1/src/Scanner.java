
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

    private ArrayList<ArrayList<Integer>> fsaTable = new ArrayList<>();


    private Filter filter;


    /*--- Constructor ---*/

    public Scanner(java.util.Scanner inputScanner) {
        setupFsaTable();
        this.filter = new Filter(inputScanner);
    }


    /*--- Public Methods ---*/

    // Returns the next available token, or null if no further tokens are available.
    public Token getNextToken() {
        FilteredCharacter current = filter.getCurrentCharacter();
        FilteredCharacter next = filter.getLookAheadCharacter();
        filter.iterate();


        if (current == null) {
            return null;
        } else if (next == null) {
            return new Token(Token.Type.Undefined, current.getCharacter() + ": null", current.getLine());
        } else {
            return new Token(Token.Type.Undefined, current.getCharacter() + ": " + next.getCharacter(), current.getLine());
        }

    }


    /*--- Private Methods ---*/

    private void setupFsaTable() {

        /* Finite State Automaton Table
         *
         * This table contains the logic for parsing individual tokens of our fictional
         * programming language. Each row represents a state the parser can be in. Each
         * column represents the next state given a certain input.
         *
         * Key
         *   W = Whitespace
         *   L = Letter
         *   N = Number
         *   O = Other
         *
         * Codes
         *   -1        Invalid character. Halt parsing.
         *   0-20      Valid next state. Go to row.
         *   500-520   Invalid next state.
         *
         */
        //                 W    L    N    =    *    /    +    -    <    >     :    ;    .    ,    (    )    {    }    [    ]    O
        Integer a1[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

        // Build Final ArrayList
        fsaTable.add(new ArrayList<>(Arrays.asList(a1)));
    }

}
