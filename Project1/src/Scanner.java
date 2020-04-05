
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
        } else {
            return new Token(Token.Type.Undefined, current.getCharacter() + "", current.getLine());
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
         *   501-522   Valid Token.
         *
         */
        //                 W    L    N    =    *    /    +    -    %    <    >    :    ;    .    ,    (    )    {    }    [    ]    O
        Integer a0[]  = {   0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,  16,  17,  18,  19,  20,  -1 }; // Initial State
        Integer a1[]  = { 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501 }; // Identifier
        Integer a2[]  = { 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502 }; // Number
        Integer a3[]  = { 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503, 503 }; // =
        Integer a4[]  = { 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504, 504 }; // ==
        Integer a5[]  = { 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505, 505 }; // *
        Integer a6[]  = { 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506, 506 }; // /
        Integer a7[]  = { 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507, 507 }; // +
        Integer a8[]  = { 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508, 508 }; // -
        Integer a9[]  = { 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509, 509 }; // %
        Integer a10[] = { 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510, 510 }; // <
        Integer a11[] = { 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511, 511 }; // >
        Integer a12[] = { 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512, 512 }; // :
        Integer a13[] = { 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513, 513 }; // :=
        Integer a14[] = { 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514, 514 }; // ;
        Integer a15[] = { 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515, 515 }; // .
        Integer a16[] = { 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516, 516 }; // ,
        Integer a17[] = { 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517, 517 }; // (
        Integer a18[] = { 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518, 518 }; // )
        Integer a19[] = { 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519, 519 }; // {
        Integer a20[] = { 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520, 520 }; // }
        Integer a21[] = { 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521, 521 }; // [
        Integer a22[] = { 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522, 522 }; // ]

        // Build Final ArrayList
        fsaTable.add(new ArrayList<>(Arrays.asList(a1)));
    }

}
