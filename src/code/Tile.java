package code;

/**
 * A tile class which accepts a parameter of type char in its constructor.
 */
public class Tile {
	/**
	 * An instance variable holding the char.
	 */
	private char _c;
	/**
	 * The constructor of the Tile class. Assigns a char value to each tile at initialization. 
	 * @param c A variable of type char which represents the letter on each tile.
	 */
	public Tile(char c){
		_c=c;
	}
	
	/**
	 * Accsessor method for the instance variable _c
	 * @return Returns the value of _c.
	 */
	public char getChar(){
		return _c;
	}
}
