
/* Chris Cruzen
 * Compilers
 * 02.19.2020
 *
 * Tree represents a parse tree of strings with bidirectional
 * nodes and an arbitrary size. This class encapsulates tree
 * construction and traversal logic with utility output methods.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Tree {


    /*--- Variable Declarations ---*/

    private List<String> inputs;
    private Node rootNode;


    /*--- Public Methods ---*/

    // Note: This method serves the purpose of a constructor
    //       and is standalone to meet project requirements.
    public void buildTree(String input) {

        // Split Input
        inputs = Arrays.asList(input.split("\\s"));

        // Build Parse Tree
        rootNode = new Node(inputs.get(0), 0);
        for (int x = 1; x < inputs.size(); x++) {
            rootNode.sortValue(inputs.get(x));
        }
    }

    public void printInorder() {
        // TODO - Print Inorder Traversal
    }

    public void printPreorder() {
        // TODO - Print Preorder Traversal
    }

    public void printPostorder() {
        // TODO - Print Postorder Traversal
    }

}