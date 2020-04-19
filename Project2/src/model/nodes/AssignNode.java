package model.nodes;

import model.Token;

public class AssignNode extends Node {


    /*--- Constructor ---*/

    public AssignNode(int depth) {
        super(NodeType.Assign, depth);
    }


    /*--- Public Methods ---*/

    public void setVariableName(Token token) {
        this.setToken(0, token);
    }

    public Token getVariableName() {
        return this.getToken(0);
    }

    public void setExpression(Node node) {
        this.setChild(0, node);
    }

    // TODO - Update Expression Node
    public Node getExpression() {
        return (Node) this.getChildren().get(0);
    }

    @Override
    public String toString() {
        return generateIndent()
                + getType().toString()
                + ": "
                + getVariableName().getInstance();
    }
}
