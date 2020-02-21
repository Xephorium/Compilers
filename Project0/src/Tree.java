
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/* Chris Cruzen
 * Compilers
 * 02.19.2020
 *
 * Tree represents a parse tree of strings with bidirectional
 * nodes and an arbitrary size. This class encapsulates tree
 * construction and traversal logic with utility output methods.
 */

class Tree {


    /*--- Variable Declarations ---*/

    private static final String OUTPUT_FILE_LOCATION = "out\\";
    private static final String OUTPUT_FILE_NAME = "output";
    private static final String EXTENSION_INORDER = ".inorder";
    private static final String EXTENSION_PREORDER = ".preorder";
    private static final String EXTENSION_POSTORDER = ".postorder";
    private static final String ERROR_FILE_WRITE = "Error writing to output file. Aborting.";
    private String outputFileName;
    private Node rootNode;


    /*--- Public Methods ---*/

    // Note: This method serves the purpose of a constructor
    //       and is standalone to meet project requirements.
    public void buildTree(String input) {

        // Split Input
        List<String> inputs = Arrays.asList(input.split("\\s"));

        // Build Parse Tree
        rootNode = new Node(inputs.get(0), 0);
        for (int x = 1; x < inputs.size(); x++) {
            rootNode.sortValue(inputs.get(x));
        }
    }

    public void setOutputFileName(String fileName, String inputExtension) {
        if (fileName == null) {
            outputFileName = OUTPUT_FILE_NAME;
        } else {
            outputFileName = parseFileName(fileName, inputExtension);
        }
    }

    public void printInorder() {
        writeToFile(EXTENSION_INORDER, rootNode.traverseInorder());
    }

    public void printPreorder() {
        writeToFile(EXTENSION_PREORDER, rootNode.traversePreorder());
    }

    public void printPostorder() {
        writeToFile(EXTENSION_POSTORDER, rootNode.traversePostorder());
    }


    /*--- Utility Methods ---*/

    private String parseFileName(String filename, String inputExtension) {

        // Remove Extension
        String newName = filename.replaceAll(inputExtension, "");

        // Remove Leading Directory
        int lastIndex = 0;
        if (newName.contains("\\")) {
            lastIndex = newName.lastIndexOf("\\");
        } else if (newName.contains("/")) {
            lastIndex = newName.lastIndexOf("/");
        }
        newName = newName.substring(lastIndex + 1, newName.length());

        return newName;
    }

    private void writeToFile(String extension, String contents) {

        // Setup Output File
        try {
            FileWriter fileWriter = new FileWriter(
                    OUTPUT_FILE_LOCATION + outputFileName + extension,
                    false
            );
            fileWriter.write(contents);
            fileWriter.close();

        } catch (IOException exception) {
            System.out.println(ERROR_FILE_WRITE);
            System.exit(0);
        }
    }

}