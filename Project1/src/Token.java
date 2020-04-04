
/* Chris Cruzen
 * Compilers
 * 04.04.2020
 *
 * Token represents a discreet grammatical unit of our fictional
 * programing language. This class is a triple representing
 * tokenType, tokenInstance, and lineNumber.
 */


public class Token {


    /*--- Variable Declarations ---*/

    private String instance;


    /*--- Constructor ---*/

    public Token(String instance) {
        this.instance = instance;
    }


    /*--- Public Methods ---*/

    @Override
    public String toString() {
        return "{" + instance + "}";
    }
}
