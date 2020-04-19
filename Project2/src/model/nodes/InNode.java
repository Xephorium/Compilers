package model.nodes;

import model.Token;

public class InNode extends Node {


    /*--- Constructor ---*/

    public InNode(int depth) {
        super(NodeType.In, depth);
    }


    /*--- Public Methods ---*/

    public void setName(Token token) {
        this.setToken(0, token);
    }

    public Token getNameName() {
        return this.getToken(0);
    }

    @Override
    public String toString() {
        return generateIndent() + getType().toString() + ": " + "name = " + getNameName().getInstance();
    }
}
