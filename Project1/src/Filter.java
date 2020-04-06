
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
    private boolean readingWindowsNewline;
    private boolean endOfFileReached = false;
    private FilteredCharacter currentCharacter;
    private FilteredCharacter lookAheadCharacter;


    /*--- Constructor ---*/

    public Filter(java.util.Scanner inputScanner) {
        this.inputScanner = inputScanner;
        this.inputScanner.useDelimiter("");
        this.line = 0;
        this.readingComment = false;
        this.readingWindowsNewline = false;

        lookAheadCharacter = getNextValidCharacter();
        advanceCharacter();
    }


    /*--- Public Methods ---*/

    public FilteredCharacter getCurrentCharacter() {
        return currentCharacter;
    }

    public FilteredCharacter getLookAheadCharacter() {
        return lookAheadCharacter;
    }

    public void advanceCharacter() {
        currentCharacter = lookAheadCharacter;
        lookAheadCharacter = getNextValidCharacter();
    }


    /*--- Private Methods ---*/

    private FilteredCharacter getNextValidCharacter() {

        // Short Circuit Stream Read
        if (endOfFileReached) {
            return null;
        }

        // Read Next Valid Character
        FilteredCharacter character;
        boolean validCharacter;
        do {
            validCharacter = true;
            if (inputScanner.hasNext()) {
                character = new FilteredCharacter(inputScanner.next().charAt(0), line);

                // Nonstandard Newline
                if ((character.getCharacter() + "").matches("\\r")) {
                    readingWindowsNewline = true;
                    character.setCharacter(' ');
                    character.setLine(line);
                    line++;
                }

                // Standard Newline
                if ((character.getCharacter() + "").matches("\\n") && !readingWindowsNewline) {
                    character.setCharacter(' ');
                    character.setLine(line);
                    line++;
                }

                // Windows Newline
                if ((character.getCharacter() + "").matches("\\n") && readingWindowsNewline) {
                    validCharacter = false;
                }

                // Consecutive Whitespace
                if ((character.getCharacter() + "").matches("\\s")
                        && (lookAheadCharacter.getCharacter() + "").matches("\\s")) {
                    validCharacter = false;
                }

                // Comment
                if ((character.getCharacter() + "").matches("#")) {
                    readingWindowsNewline = false;
                    readingComment = !readingComment;
                    validCharacter = false;
                }

                // Otherwise
                if (readingComment) {
                    validCharacter = false;
                }

            } else {
                endOfFileReached = true;
                character = null;
            }

        } while (!validCharacter);

        return character;
    }

}
