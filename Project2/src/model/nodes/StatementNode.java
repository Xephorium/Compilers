package model.nodes;

import model.Token;

public class StatementNode extends Node {


    /*--- Constructor ---*/

    public StatementNode(int depth) {
        super(NodeType.Statement, depth);
    }


    /*--- Public Methods ---*/

    public void setStatementNode(Node node) {
        this.setChild(0, node);
    }

    public Token getStatementNode() {
        return this.getToken(0);
    }

    public void setOtherStatementNode(Node node) {
        this.setChild(1, node);
    }

    public Token getOtherStatementNode() {
        return this.getToken(1);
    }


    @Override
    public String toString() {
        return generateIndent() + getType().toString();
    }
}
