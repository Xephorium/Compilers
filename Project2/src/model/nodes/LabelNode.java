package model.nodes;

import model.Token;

public class LabelNode extends Node {


    /*--- Constructor ---*/

    public LabelNode(int depth) {
        super(NodeType.Label, depth);
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
                + ": name = " + getName().getInstance();
    }
}
