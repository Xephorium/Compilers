
/* Chris Cruzen
 * Compilers
 * 04.04.2020
 *
 * Scanner represents the first step in the process of translating a custom
 * programming language. This class encapsulates the logic of converting raw
 * string input to tokens and returning those tokens to be used by a parser.
 */


public class Scanner {


    /*--- Variable Declarations ---*/

    private String input;
    private int index;


    /*--- Constructor ---*/

    public Scanner(String input) {
        this.input = input;
        this.index = 0;
    }


    /*--- Public Methods ---*/

    public Token getNextToken() {
        while (index < input.length() && input.charAt(index) == ' ' && index + 1 < input.length() - 1) {
            index++;
        }

        // Read Next Word
        String word = "";
        while (index < input.length() && input.charAt(index) != ' ') {
            word += input.charAt(index);
            index++;
        }


        if (!word.equals("")) {
            return new Token(word);
        } else {
            return new Token("");
        }
    }

}
