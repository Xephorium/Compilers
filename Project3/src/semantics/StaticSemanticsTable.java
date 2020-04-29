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
    }



    /*--- Private Methods ---*/

    private void insert() {

    }

    private void verify() {

    }
}
