package semantics;

import model.Token;
import model.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/* Chris Cruzen
 * Compilers
 * 04.29.2020
 *
 * SymbolTable represents the third step in the process of translating
 * a custom programming language. This class encapsulates the logic of
 * building a symbol table to track variable declaration/use within our
 * language and printing any discovered errors.
 */


public class SymbolTable {


    /*--- Variable Declarations ---*/

    private List<Token> table;


    /*--- Constructor ---*/

    public SymbolTable(Node parseTree) {
        this.table = new ArrayList<>();

        // Traverse Tree, adding Tokens
        parseTree.buildSemanticsTable(this);
    }



    /*--- Private Methods ---*/

    public void insert(Token symbol) {
        boolean error = false;
        for (Token entry : table) {
            if (entry.getInstance().equals(symbol.getInstance())) {
                error = true;
                System.out.println("Semantics Error: Cannot redeclare symbol '"
                        + entry.getInstance()
                        + "' on line "
                        + symbol.getLine()
                        + ".");
            }
        }

        if (!error) {
            table.add(symbol);
        }
    }

    public void verify(Token symbol) {
        boolean found = false;
        for (Token entry : table) {
            if (entry.getInstance().equals(symbol.getInstance())) {
                found = true;
            }
        }

        if (!found) {
            System.out.println("Semantics Error: symbol '"
                    + symbol.getInstance()
                    + "' on line "
                    + symbol.getLine()
                    + " not declared.");
        }
    }
}
