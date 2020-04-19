package model.nodes;

import model.Token;


public class VariableNode extends Node {


    /*--- Constructor ---*/

    public VariableNode(int depth) {
        super(NodeType.Variable, depth);
    }


    /*--- Public Methods ---*/

    public void setVariableName(Token token) {
        this.setToken(0, token);
    }

    public Token getVariableName() {
        return this.getToken(0);
    }

    public void setInitialValue(Token token) {
        this.setToken(1, token);
    }

    public Token getInitialValue() {
        return this.getToken(1);
    }

    public void setVariableNode(Node node) {
        this.setChild(0, node);
    }

    public VariableNode getVariableNode() {
        return (VariableNode) this.getChildren().get(0);
    }

    @Override
    public String toString() {
        return generateIndent()
                + getType().toString()
                + ": " + "name = "
                + getVariableName().getInstance()
                + ", initial value = " +
                getInitialValue().getInstance();
    }

}
