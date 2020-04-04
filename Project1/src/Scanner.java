
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

    private Filter filter;


    /*--- Constructor ---*/

    public Scanner(java.util.Scanner inputScanner) {
        this.filter = new Filter(inputScanner);
    }


    /*--- Public Methods ---*/

    public Token getNextToken() {
        Word word = filter.getNextWord();

        if (word != null) {
            return new Token(word.toString());
        } else {
            return new Token("");
        }
    }

}
