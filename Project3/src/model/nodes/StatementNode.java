package model.nodes;


public class StatementNode extends Node {


    /*--- Constructor ---*/

    public StatementNode(int depth) {
        super(NodeType.Statement, depth);
    }


    /*--- Public Methods ---*/

    public void setStatementNode(Node node) {
        this.setChild(0, node);
    }

    public Node getStatementNode() {
        return this.getChild(0);
    }

    public void setOtherStatementNode(Node node) {
        this.setChild(1, node);
    }

    public Node getOtherStatementNode() {
        return this.getChild(1);
    }


    @Override
    public String toString() {
        return generateIndent() + getType().toString();
    }
}
