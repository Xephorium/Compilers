package model.nodes;

import model.Token;

import java.util.ArrayList;
import java.util.List;

/* Chris Cruzen
 * Compilers
 * 04.18.2020
 *
 * Node is a simple base class that represents a single
 * node with an ambiguous number of child nodes and stored
 * tokens in a parse tree that represents a program.
 */


public class Node {


    /*--- Variable Declarations ---*/

    private static final int MAX_TOKENS = 4;
    private static final int MAX_CHILDREN = 4;

    private NodeType type;
    private List<Token> tokens;
    private List<Node> children;
    private int depth;


    /*--- Constructor ---*/

    public Node(NodeType type, int depth) {
        this.type = type;
        this.depth = depth;

        tokens = new ArrayList<>();
        for (int x = 0; x < MAX_TOKENS; x++) {
            tokens.add(null);
        }
        children = new ArrayList<>();
        for (int x = 0; x < MAX_CHILDREN; x++) {
            children.add(null);
        }
    }


    /*--- Public Methods ---*/

    public NodeType getType() {
        return type;
    }

    public int getDepth() {
        return depth;
    }

    public void setToken(int index, Token token) {
        tokens.set(index, token);
    }

    public Token getToken(int index) {
        return tokens.get(index);
    }

    public List<Token> getTokenList() {
        return tokens;
    }

    public void setChild(int index, Node node) {
        children.set(index, node);
    }

    public Node getChild(int index) {
        return children.get(index);
    }

    public List<Node> getChildren() {
        return children;
    }

    // Returns tree as formatted string, walked: Root, Left, Right
    public String traverse() {
        String output = "";
        output += this + "\n";
        for (int x = 0; x < MAX_CHILDREN; x++) {
            if (getChild(x) != null) {
                output += getChild(x).traverse();
            }
        }
        return output;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(generateIndent() + type.toString() + ": ");
        builder.append("Base Node.");
        return builder.toString();
    }

    public String generateIndent() {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < depth; x++) {
            builder.append("  ");
        }
        return builder.toString();
    }


    /*--- NodeType Enum ---*/

    public enum NodeType {
        Program,
        Variable,
        Block,
        Statement,
        In,
        Out,
        Assign,
        Label,
        GoTo
    }
}