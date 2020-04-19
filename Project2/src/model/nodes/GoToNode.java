package model.nodes;

import model.Token;

public class GoToNode extends Node {


    /*--- Constructor ---*/

    public GoToNode(int depth) {
        super(NodeType.GoTo, depth);
    }


    /*--- Public Methods ---*/

    public void setName(Token token) {
        this.setToken(0, token);
    }

    public Token getName() {
        return this.getToken(0);
    }

    @Override
    public String toString() {
        return generateIndent() + getType().toString()
                + ": label = " + getName().getInstance();
    }
}
