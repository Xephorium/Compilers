package model;
/* Chris Cruzen
 * Compilers
 * 04.04.2020
 *
 * Word represents a simple string of characters and associated line
 * number.
 */


public class Word {


    /*--- Variable Declarations ---*/

    private String word;
    private int line;


    /*--- Constructor ---*/

    public Word(String word, int line) {
        this.word = word;
        this.line = line;
    }


    /*--- Public Methods ---*/

    public String getWord() {
        return word;
    }

    public int getLine() {
        return line;
    }
}
