package model;

import java.util.List;

/* Chris Cruzen
 * Compilers
 * 04.18.2020
 *
 * Node represents a single node with an ambiguous number of
 * child nodes in a parse tree of tokens.
 */

class Node {


    /*--- Variable Declarations ---*/

    private String label; // Name of Function
    private List<Token> tokens;
    private List<Node> children;
    private int depth;


    /*--- Constructor ---*/

    public Node(String production, int depth) {
        this.label = production;
        this.depth = depth;
    }


    /*--- Public Methods ---*/

    public void addToken(Token token) {
        tokens.add(token);
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void addChild(Node node) {
        children.add(node);
    }

    public List<Node> getChildren() {
        return children;
    }

//    // Returns tree as formatted string, walked: Left, Root, Right
//    public String traverseInorder() {
//        String output = "";
//        if (left != null) output += left.traverseInorder();
//        output += this + "\n";
//        if (right != null) output += right.traverseInorder();
//
//        return output;
//    }
//
//    // Returns tree as formatted string, walked: Root, Left, Right
//    public String traversePreorder() {
//        String output = "";
//        output += this + "\n";
//        if (left != null) output += left.traversePreorder();
//        if (right != null) output += right.traversePreorder();
//
//        return output;
//    }
//
//    // Returns tree as formatted string, walked: Left, Right, Root
//    public String traversePostorder() {
//        String output = "";
//        if (left != null) output += left.traversePostorder();
//        if (right != null) output += right.traversePostorder();
//        output += this + "\n";
//
//        return output;
//    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(generateIndent() + label + ": ");
        for (int x = 0; x < tokens.size(); x++) {
            builder.append(tokens.get(x).getInstance());
            builder.append(" ");
        }
        return builder.toString();
    }


    /*--- Private Methods ---*/

    private String generateIndent() {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < depth; x++) {
            builder.append("  ");
        }
        return builder.toString();
    }
}