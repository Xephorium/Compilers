package model.nodes;

public class IfNode extends Node {


    /*--- Constructor ---*/

    public IfNode(int depth) {
        super(NodeType.If, depth);
    }


    /*--- Public Methods ---*/

    public void setExpressionOne(Node node) {
        this.setChild(0, node);
    }

    // TODO - Fix Expression
    public Node getExpressionOne() {
        return this.getChild(0);
    }

    public void setRelational(Node node) {
        this.setChild(1, node);
    }

    // TODO - Fix Relational
    public Node getRelational() {
        return this.getChild(1);
    }

    public void setExpressionTwo(Node node) {
        this.setChild(2, node);
    }

    // TODO - Fix Expression
    public Node getExpressionTwo() {
        return this.getChild(2);
    }

    public void setStatement(Node node) {
        this.setChild(3, node);
    }

    public StatementNode getStatement() {
        return (StatementNode) this.getChild(3);
    }

    @Override
    public String toString() {
        return generateIndent() + getType().toString();
    }

}
