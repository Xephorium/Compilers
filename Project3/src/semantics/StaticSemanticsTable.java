package semantics;

import model.Token;
import model.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/* Chris Cruzen
 * Compilers
 * 04.29.2020
 *
 * StaticSemanticsTable represents the third step in the process of translating
 * a custom programming language. This class encapsulates the logic of building
 * a semantics table to track variable declaration/use within our language and
 * printing any discovered errors.
 */


public class StaticSemanticsTable {


    /*--- Variable Declarations ---*/

    private List<Token> table;


    /*--- Constructor ---*/

    public StaticSemanticsTable(Node parseTree) {
        this.table = new ArrayList<Token>();

        // Traverse Tree, adding Tokens
        parseTree.buildSemanticsTable(this);
    }



    /*--- Private Methods ---*/

    public void insert(Token identifier) {
        for (Token entry : table) {
            if (entry.getInstance().equals(identifier.getInstance())) {
                System.out.println("Error: Cannot redeclare identifier '"
                        + entry.getInstance()
                        + "' on line "
                        + identifier.getLine()
                        + ". Exiting.");
                System.exit(1);
            }
        }
        table.add(identifier);
    }

    public void verify(Token identifier) {
        boolean found = false;
        for (Token entry : table) {
            if (entry.getInstance().equals(identifier.getInstance())) {
                found = true;
            }
        }

        if (!found) {
            System.out.println("Error: identifier '"
                    + identifier.getInstance()
                    + "' on line "
                    + identifier.getLine()
                    + " not declared.");
        }
    }
}
