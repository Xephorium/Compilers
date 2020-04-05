
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
        Undefined(500),
        Identifier(501),
        Number(502),
        Assignment(503),
        Equality(504),
        Multiplication(505),
        Division(506),
        Addition(507),
        Subtraction(508),
        Modulus(509),
        Less(510),
        Greater(511),
        Colon(512),
        SassyColon(513),
        Semicolon(514),
        Period(515),
        Comma(516),
        OpenParenthesis(517),
        CloseParenthesis(518),
        OpenBrace(519),
        CloseBrace(520),
        OpenBracket(521),
        CloseBracket(522),
        EndOfFile(523);

        private int code;

        Type(int code) {
            this.code = code;
        }

        public static Type fromCode(int code) {
            for (int x = 1; x < Type.values().length; x++) {
                if (Type.values()[x].code == code) return Type.values()[x];
            }
            return Type.values()[0];
        }
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
        return "Token: {" + type + ", " + instance + ", " + line + "}";
    }
}
