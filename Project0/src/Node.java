
/* Chris Cruzen
 * Compilers
 * 02.19.2020
 *
 * Node represents a single bidirectional node in a parse
 * tree of strings.
 */

import java.util.ArrayList;
import java.util.List;

class Node {


    /*--- Variable Declarations ---*/

    private static final String EMPTY_VALUE = "Cannot create node from empty string. Aborting.";

    private String label;
    private Node left;
    private Node right;
    private List<String> values;
    private int depth;


    /*--- Constructor ---*/

    Node(String value, int depth) {
        this.label = createLabel(value);
        this.values = new ArrayList<>();
        this.values.add(value);
        this.depth = depth;
    }


    /*--- Public Methods ---*/

    public void sortValue(String value) {
        String newLabel = createLabel(value);

        // Add Value to This Node
        if (label.equals(newLabel)) {
            this.addValue(value);

        } else if (newLabel.compareTo(label) > 0) {

            // Sort Down Right Node
            if (right != null) {
                right.sortValue(value);
            } else {
                right = new Node(value, depth + 1);
            }

        } else {

            // Sort Down Left Node
            if (left != null) {
                left.sortValue(value);
            } else {
                left = new Node(value, depth + 1);
            }
        }
    }

    public String traversePreorder() {
        String output = "";

        output += this;
        if (left != null) output += "\n" + left.traversePreorder();
        if (right != null) output += "\n" + right.traversePreorder();

        return output;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(generateIndent() + label + ": ");
        for (int x = 0; x < values.size(); x++) {
            builder.append(values.get(x));
            builder.append(" ");
        }
        return builder.toString();
    }

    /*--- Private Methods ---*/

    private String createLabel(String value) {
        if (value.length() == 0) {
            System.out.println(EMPTY_VALUE);
            System.exit(0);
            return "";
        } else if (value.length() > 1) {
            return value.substring(0, 2);
        } else {
            return "" + value.charAt(0);
        }
    }

    private void addValue(String value) {
        if (!values.contains(value)) {
            values.add(value);
        }
    }

    private String generateIndent() {
        StringBuilder builder = new StringBuilder();
        for (int x = 0; x < depth; x++) {
            builder.append("  ");
        }
        return builder.toString();
    }
}