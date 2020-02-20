
import java.util.Arrays;
import java.util.List;

/* Chris Cruzen
 * Compilers
 * 02.19.2020
 *
 * Tree represents a parse tree of strings with bidirectional
 * nodes and an arbitrary size. This class encapsulates tree
 * construction and traversal logic with utility output methods.
 */

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
        System.out.println("\nInorder Traversal");
        System.out.println(rootNode.traverseInorder());
    }

    public void printPreorder() {
        System.out.println("\nPreorder Traversal");
        System.out.println(rootNode.traversePreorder());
    }

    public void printPostorder() {
        System.out.println("\nPostorder Traversal");
        System.out.println(rootNode.traversePostorder());
    }

}