package model.nodes;

import model.Token;


public class ExpressionNode extends Node {


    /*--- Constructor ---*/

    public ExpressionNode(int depth) {
        super(NodeType.Expression, depth);
    }


    /*--- Public Token Methods ---*/

    public void setToken(Token token) {
        this.setToken(0, token);
    }

    public Token getToken() {
        return this.getToken(0);
    }


    /*--- Public Child Methods ---*/

    public void setLeftExpression(Node node) {
        this.setChild(0, node);
    }

    public ExpressionNode getLeftExpression() {
        return (ExpressionNode) this.getChildren().get(0);
    }

    public void setRightExpression(Node node) {
        this.setChild(1, node);
    }

    public ExpressionNode getRightExpression() {
        return (ExpressionNode) this.getChildren().get(1);
    }


    /*--- Public Utility Methods ---*/

    @Override
    public String toString() {
        String output = generateIndent() + getType().toString();
        if (getToken() != null) {
            output += ": " + getToken().getInstance();
        }
        return output;
    }
}
