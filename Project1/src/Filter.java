
/* Chris Cruzen
 * Compilers
 * 04.04.2020
 *
 * Filter is a simple utility class that returns the next word of a
 * provided input stream. This class encapsulates the removal of extra
 * whitespace and comments, as well as line number tracking.
 */


public class Filter {


    /*--- Variable Declarations ---*/

    private java.util.Scanner inputScanner;
    private int line;
    private boolean readingComment;
    private boolean endOfFileReached = false;


    /*--- Constructor ---*/

    public Filter(java.util.Scanner inputScanner) {
        this.inputScanner = inputScanner;
        this.inputScanner.useDelimiter("");
        this.line = 0;
        readingComment = false;
    }


    /*--- Public Methods ---*/

    // Returns next available valid word, or null if no further words are available.
    public Word getNextWord() {

        // Short Circuit Stream Read
        if (endOfFileReached) {
            return null;
        }

        // Declare Variables
        String word = null;
        boolean wordStarted = false;
        boolean wordFinished = false;
        boolean iterateLineNumber = false;

        // Read Word
        while (!wordFinished) {

            // Read Next Character
            if (inputScanner.hasNext()) {
                char character = inputScanner.next().charAt(0);

                switch (character) {

                    // Parse Whitespace
                    case ' ':
                        if (wordStarted) {
                            wordFinished = true;
                        }
                        break;

                    // Parse Newline
                    case '\n':
                        if (wordStarted) {
                            wordFinished = true;
                            iterateLineNumber = true;
                        } else {
                            line++;
                        }
                        break;

                    // Parse Comment
                    case '#':
                        if (wordStarted) {
                            wordFinished = true;
                        }
                        readingComment = !readingComment;
                        break;

                    // Parse Everything Else
                    default:
                        if (!readingComment) {
                            if (word == null) word = "";
                            word += character;
                            if (!wordStarted) wordStarted = true;
                        }
                }


            } else {

                // No More Input, Word Complete
                wordFinished = true;
            }

        }

        // End of File Reached, Return Empty Word
        if (word == null) {
            endOfFileReached = true;
            return new Word("", line);
        }

        // Remove Any Remaining End of Line Characters ('\r' != '\n')
        word = word.trim();

        // Create Word
        Word finalWord = new Word(word.trim(), line);

        // Conditionally Iterate Line Number
        if (iterateLineNumber) {
            line++;
        }

        if (word.isEmpty()) {

            // Empty Word Found; Find Next
            return getNextWord();

        } else {

            // Valid Word Found; Return
            return finalWord;
        }
    }

}
