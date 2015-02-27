package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import code.model.Game;

/**
 * A Square class which represents each space on the board.
 */

public class Square {
	/**
	 * An instance variable of type int holding the word multiplier
	 */
	private int _wrdmult;
	/**
	 * An instance variable of type int holding the letter multiplier.
	 */
	private int _lettermult;
	/**
	 * An instance variable holding the tile placed on the square, if there is one.
	 */
	private Tile _tile;
	/**
	 * An instance variable holding the row number.
	 */
	private int _rownumber;
	/**
	 * An instance variable holding the column number.
	 */
	private int _colnumber;
	/**
	 * The constructor of the Square class. Assigns a value to _rownumber and _colnumber.
	 * @param rownumber The value to be assigned to _rownumber.
	 * @param colnumber The value to be assigned to _colnumber.
	 */
	public Square(int colnumber, int rownumber){
		ArrayList<Integer> lm = new ArrayList<Integer>();
		for(int i = 0; i<9; i++){
			lm.add(2);
		}
		for(int i = 0; i<79; i++){
			lm.add(1);
		}
		for(int i = 0; i<4; i++){
			lm.add(0);
		}
		for(int i = 0; i<5; i++){
			lm.add(-1);
		}
		Collections.shuffle(lm);
		_lettermult = lm.get(0);
		ArrayList<Integer> wm = new ArrayList<Integer>();
		for(int i = 0; i<4; i++){
			wm.add(3);
		}
		for(int i = 0; i<14; i++){
			wm.add(2);
		}
		for(int i = 0; i<74; i++){
			wm.add(1);
		}
		for(int i = 0; i<4; i++){
			wm.add(0);
		}
		Collections.shuffle(wm);
		_wrdmult = wm.get(0);
		_rownumber=rownumber;
		_colnumber=colnumber;
		_tile = null;
	}
	/**
	 * A method which returns the row that the square resides in.
	 * @return Returns the row number as a type int.
	 */
	public int getRow(){
		return _rownumber;
	}
	/**
	 * A method which returns the column that the square resided in.
	 * @return Returns the column number as a type int.
	 */
	public int getCol(){
		return _colnumber;
	}
	/**
	 * A method that sets the tile instance variable to the value of the tile parameter.
	 * @param tile A parameter denoting the tile to be assigned to the _tile variable.
	 * @return
	 */
	public boolean place(Tile tile){
		_tile = tile;
		return true;
	}
	/**
	 * Accessor method for _tile.
	 * @return Returns the instance variable of the tile on that square.
	 */
	public Tile getTile(){
		return _tile;
	}
	/**  
	 *  method to remove _tile from square
	 *
	 */
	public void removeTile(){
		_tile = null;
	}
	/**
	 * method to check if Square is empty.
	 * @return true if _tile is equal to null.
	 */
	public boolean isEmpty(){
		return _tile == null;
	}
	/**
	 * Gets the word multiplier for this square
	 * @return the word multiplier
	 */
	public int getWordMult(){
		return _wrdmult;
	}
	/**
	 * gets the letter multiplier
	 * @return the letter multiplier
	 */
	public int getLetterMult(){
		return _lettermult;
	}
}
