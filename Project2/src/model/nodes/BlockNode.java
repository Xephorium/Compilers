package model.nodes;


public class BlockNode extends Node {


    /*--- Constructor ---*/

    public BlockNode(int depth) {
        super(NodeType.Block, depth);
    }


    /*--- Public Methods ---*/

    public void setVariableNode(Node node) {
        this.setChild(0, node);
    }

    public VariableNode getVariableNode() {
        return (VariableNode) this.getChildren().get(0);
    }

    public void setStatementNode(Node node) {
        this.setChild(1, node);
    }

    public BlockNode getStatementNode() {
        return (BlockNode) this.getChildren().get(1);
    }

    @Override
    public String toString() {
        return generateIndent() + getType().toString();
    }
}
