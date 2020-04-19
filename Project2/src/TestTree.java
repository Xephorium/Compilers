import model.nodes.Node;

public class TestTree {


    /*--- Variable Declarations ---*/

    private Node parseTree;


    /*--- Constructor ---*/

    public TestTree(Node parseTree) {
        this.parseTree = parseTree;
    }


    /*--- Public Methods ---*/

    // Prints tree as formatted string, walked: Root, Left, Right
    public void printTree() {
        System.out.println(parseTree.traverse());
    }

}
