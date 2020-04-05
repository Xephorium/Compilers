import java.util.ArrayList;
import java.util.Arrays;

public class FsaTable {


    /*--- Variable Declarations ---*/

    private ArrayList<ArrayList<Integer>> fsaTable;
    private String[] fsaCharacterRegexList = {
            "\\s",              // Whitespace
            "[a-zA-Z]",         // Letter
            "\\d",              // Number
            "=",
            "\\*",
            "/",
            "\\+",
            "-",
            "%",
            "<",
            ">",
            ":",
            ";",
            "\\.",
            ",",
            "\\(",
            "\\)",
            "\\{",
            "}",
            "\\[",
            "]"
    };



    /*--- Constructor ---*/

    public FsaTable() {
        fsaTable = new ArrayList<>();
        setupFsaTable();
    }


    /*--- Public Methods ---*/

    public int getNextState(int currentState, char character) {
        return fsaTable.get(currentState).get(getCharacterIndex(character));
    }


    /*--- Private Methods ---*/

    private int getCharacterIndex(char character) {
        int index = -1;

        // Check for Match
        for (int x = 0; x < fsaCharacterRegexList.length; x++) {
            if ((character + "").matches(fsaCharacterRegexList[x])) {
                index = x;
            }
        }

        // No Match
        if (index == -1) {
            index = fsaCharacterRegexList.length;
        }

        return index;
    }

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
        //                  W    L    N    =    *    /    +    -    %    <    >    :    ;    .    ,    (    )    {    }    [    ]    O
        Integer a0[]  = {   0,   1,   2,   3,   4,   5,   6,   7,   8,   9,  10,  11,  12,  13,  14,  15,  16,  17,  18,  19,  20,  -1 }; // Initial State
        Integer a1[]  = { 501,   1,   1, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501, 501 }; // Identifier
        Integer a2[]  = { 502, 502,   2, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502, 502 }; // Number
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
        fsaTable.add(new ArrayList<>(Arrays.asList(a0)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a1)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a2)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a3)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a4)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a5)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a6)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a7)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a8)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a9)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a10)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a11)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a12)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a13)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a14)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a15)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a16)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a17)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a18)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a19)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a20)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a21)));
        fsaTable.add(new ArrayList<>(Arrays.asList(a22)));
    }
}
