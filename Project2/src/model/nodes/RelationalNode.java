package model.nodes;

import model.Token;

public class RelationalNode extends Node {


    /*--- Constructor ---*/

    public RelationalNode(int depth) {
        super(NodeType.Relational, depth);
    }


    /*--- Public Methods ---*/

    public void setFirstToken(Token token) {
        setToken(0, token);
    }

    public Token getFirstToken() {
        return getToken(0);
    }

    public void setSecondToken(Token token) {
        setToken(1, token);
    }

    public Token getSecondToken() {
        return getToken(1);
    }

    @Override
    public String toString() {
        if (getSecondToken() != null) {
            return generateIndent()
                    + getType().toString()
                    + ": " + getFirstToken().getInstance()
                    + getSecondToken().getInstance();
        } else {
            return generateIndent()
                    + getType().toString()
                    + ": "
                    + getFirstToken().getInstance();
        }
    }
}
