

/* Chris Cruzen
 * Compilers
 * 02.19.2020
 *
 * Main is the root class of Project0 and contains the
 * project's core execution logic.
 */

public class Main {

    public static void main(String[] args) {

        // Parse Input & Handle Error States

        // Create Parse Tree
        Tree parseTree = new Tree();
        parseTree.buildTree("test string here");

        // Print Outputs
        parseTree.printInorder();
        parseTree.printPreorder();
        parseTree.printPostorder();
    }
}
