
/* Chris Cruzen
 * Compilers
 * 04.05.2020
 *
 * FilteredCharacter represents a character and its associated metadata
 * as returned from the Filter class.
 */


public class FilteredCharacter {


    /*--- Variable Declarations ---*/

    private Character character;
    private int line;


    /*--- Constructor ---*/

    public FilteredCharacter(Character character, int line) {
        this.character = character;
        this.line = line;
    }


    /*--- Public Methods ---*/

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }
}
