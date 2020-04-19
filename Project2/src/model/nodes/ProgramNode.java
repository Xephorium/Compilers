package model.nodes;


public class ProgramNode extends Node {


    /*--- Constructor ---*/

    public ProgramNode(int depth) {
        super(NodeType.Program, depth);
    }


    /*--- Public Methods ---*/

    public void setVariableNode(Node node) {
        this.setChild(0, node);
    }

    public VariableNode getVariableNode() {
        return (VariableNode) this.getChildren().get(0);
    }

    public void setBlockNode(Node node) {
        this.setChild(1, node);
    }

    public BlockNode getBlockNode() {
        return (BlockNode) this.getChildren().get(1);
    }

    @Override
    public String toString() {
        return generateIndent() + getType().toString();
    }
}
