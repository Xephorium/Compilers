
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

    enum Type {
        Undefined,
        EndOfFile
    }

    private Type type;
    private String instance;
    private int line;


    /*--- Constructor ---*/

    public Token(Type type, String instance, int line) {
        this.type = type;
        this.instance = instance;
        this.line = line;
    }


    /*--- Public Methods ---*/

    public Type getType() {
        return type;
    }

    public String getInstance() {
        return instance;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        if (instance.isEmpty()) {
            return "Token: {" + type + ", " + line + "}";
        } else {
            return "Token: {" + type + ", " + instance + ", " + line + "}";
        }
    }
}
