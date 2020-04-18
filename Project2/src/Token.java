
/* Chris Cruzen
 * Compilers
 * 04.04.2020
 *
 * Token represents a discreet grammatical unit of our fictional
 * programing language. This class is a triple representing
 * tokenType, tokenInstance, and lineNumber. The Type enum contains
 * utility methods for initialization from FSA Table code and keyword
 * identification.
 */


public class Token {


    /*--- Variable Declarations ---*/

    private Type type;
    private String instance;
    private int line;


    /*--- Constructor ---*/

    public Token(Type type, String instance, int line) {

        // Check Identifier For Keyword
        if (type == Type.Identifier) {
            this.type = Type.fromIdentifier(instance);
        } else {
            this.type = type;
        }

        // Set Instance and Line
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
        return "{" + type + ", " + instance + ", " + line + "}";
    }


    /*--- Type Enum ---*/

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
        Modulo(509),
        LessThan(510),
        GreaterThan(511),
        Colon(512),
        SassyColon(513),
        Semicolon(514),
        Period(515),
        Comma(516),
        OpenParen(517),
        CloseParen(518),
        OpenBrace(519),
        CloseBrace(520),
        OpenBracket(521),
        CloseBracket(522),
        EndOfFile(523),
        Label(524),
        Goto(525),
        Loop(526),
        Void(527),
        Declare(528),
        Return(529),
        In(530),
        Out(531),
        Program(532),
        Iffy(533),
        Then(534),
        Assign(535),
        Data(536);

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

        public static Type fromIdentifier(String identifier) {
            switch (identifier) {
                case "label":
                    return Type.Label;
                case "goto":
                    return Type.Goto;
                case "loop":
                    return Type.Loop;
                case "void":
                    return Type.Void;
                case "declare":
                    return Type.Declare;
                case "return":
                    return Type.Return;
                case "in":
                    return Type.In;
                case "out":
                    return Type.Out;
                case "program":
                    return Type.Program;
                case "iffy":
                    return Type.Iffy;
                case "then":
                    return Type.Then;
                case "assign":
                    return Type.Assign;
                case "data":
                    return Type.Data;
                default:
                    return Type.Identifier;

            }
        }
    }
}
