package model.nodes;


public class OutNode extends Node {


    /*--- Constructor ---*/

    public OutNode(int depth) {
        super(NodeType.Out, depth);
    }


    /*--- Public Methods ---*/

    public void seExpression(Node node) {
        this.setChild(0, node);
    }

    // TODO - Update Expression Node
    public Node getExpression() {
        return this.getChild(0);
    }

    @Override
    public String toString() {
        return generateIndent() + getType().toString();
    }
}
