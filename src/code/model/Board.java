package code.model;

import java.util.ArrayList;
import java.util.List;

import util.general.CharacterFromFileReader;
import util.general.CharacterFromFileReader;
import code.Player;
import code.Square;
import code.Tile;
import code.interfaces.IBoard;

public class Board implements IBoard {

	/**
	 * Instance variable that holds a reference to words placed on the board
	 * this turn.
	 */
	private ArrayList<String> _tempwordlist;
	/**
	 * Instance variable of type int to hold a reference to a random number used
	 * for void sectors in the board;
	 */
	private int _randx;
	/**
	 * Instance variable of type int to hold a reference to a random number used
	 * for void sectors in the board;
	 */
	private int _randy;
	/**
	 * A string representing the current word try.
	 */
	private String _currentWord;
	/**
	 * An int holding the Score in all words changed.
	 */
	private int _scoreCount;
	/**
	 * An instance variable holding a reference to a matrix of squares.
	 */
	private Square[][] _sqmatrix;
	/**
	 * An instance variable refering to the home square.
	 */
	private Square _homesquare;
	/**
	 * A temporary list holding the squares that have tiles placed on them
	 * during a turn.
	 */
	private List<Square> _tempplacelist;
	/**
	 * A variable holding a reference to the player whose turn it is.
	 */
	private Player _currentPlayer;
	/**
	 * A temporary variable used in the set adjacent words method.
	 */
	private int temp;

	/**
	 * The constructor for the BOard class. Creates a new array list to hold the
	 * squares that have tiles placed on them during a turn. Creates a new
	 * square matrix that holds all of the squares in play. Adds a square to
	 * each valid spot on the board. Assigns a random row and column to the home
	 * square.
	 */
	public Board() {
		_tempplacelist = new ArrayList<Square>();
		_tempwordlist = new ArrayList<String>();
		_sqmatrix = new Square[20][20];
		_currentWord = "";
		_randx = 3 + (int) (Math.random() * 5);
		_randy = 3 + (int) (Math.random() * 5);
		for (int col = 0; col < (20 - _randx); col++) {
			for (int row = 0; row < _randy; row++) {
				_sqmatrix[col][row] = new Square(col, row);
			}
		}
		for (int col = 0; col < 20; col++) {
			for (int row = _randy; row < (20 - _randx); row++) {
				_sqmatrix[col][row] = new Square(col, row);
			}
		}
		for (int col = _randx; col < 20; col++) {
			for (int row = (20 - _randy); row < 20; row++) {
				_sqmatrix[col][row] = new Square(col, row);
			}
		}
		for (int col = (20 - _randx); col < 20; col++) {
			for (int row = 0; row < _randy; row++) {
				_sqmatrix[col][row] = null;
			}
		}
		for (int col = 0; col < _randx; col++) {
			for (int row = (20 - _randy); row < 20; row++) {
				_sqmatrix[col][row] = null;
			}
		}
		int state = 0;
		while (state != 1) {
			switch (state) {
			case 0:
				int rndmRow = (int) (Math.random() * 20);
				int rndmCol = (int) (Math.random() * 20);
				if (_sqmatrix[rndmCol][rndmRow] == null) {
					state = 0;
				} else {
					_homesquare = _sqmatrix[rndmCol][rndmRow];
					state = 1;
				}
				break;
			}
		}
	}
	/**
	 * Accessor method to get the random "y" value
	 * @return int
	 */
	public int getRandy() {
		return _randy;
	}
	/**
	 * Accessor method to get the random "x" value
	 * @return int
	 */
	public int getRandx() {
		return _randx;
	}

	/**
	 * The board has a square identified as the "home square". This method
	 * returns the row in which the "home square" is located. Rows are numbered
	 * starting at one (i.e. the top row of the board is row number one).
	 * 
	 * @return an int representing the row in which the "home square" appears
	 */
	@Override
	public int homeSquareRow() {
		return _homesquare.getRow();
	}

	/**
	 * The board has a square identified as the "home square". This method
	 * returns the column in which the "home square" is located. Columns are
	 * numbered starting at one (i.e. the leftmost column of the board is column
	 * number one).
	 * 
	 * @return an int the column in which the "home square" appears
	 */
	@Override
	public int homeSquareCol() {
		return _homesquare.getCol();
	}

	/**
	 * An accessor method for the Tile on a given square of the board. The
	 * square is indicated by its row and column position on the board. The
	 * behavior of this method is undefined if the row and column values given
	 * do not refer to a position on the board, or if that position does not
	 * contain a Tile.
	 * 
	 * @param row
	 *            - the row of the square being queried, expressed as an int
	 * @param col
	 *            - the column of the square being queried, expressed as an int
	 * 
	 * @return - the Tile on the square indicated by row and col, if that square
	 *         exists and has a Tile on it
	 */
	@Override
	public Tile get(int row, int col) {
		if (row < 20 && col < 20) {
			return _sqmatrix[col][row].getTile();
		} else {
			return null;
		}
	}

	/**
	 * Attempt to place a given tile on the square indicated. The tile can be
	 * placed only if the square indicated by the row and column positions
	 * exists and does not already contain a Tile.
	 * 
	 * @param tile
	 *            - the Tile to place
	 * @param row
	 *            - the row in which to place the Tile, expressed as an int
	 * @param col
	 *            - the column in which to place the Tile, expressed as an int
	 * 
	 * @return true if the placement was successful, false otherwise
	 */
	@Override
	public boolean place(Tile tile, int row, int col) {
		if (this.isEmpty(row, col)) {
			_sqmatrix[col][row].place(tile);
			_tempplacelist.add(_sqmatrix[col][row]);
			_currentWord += tile.getChar();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Attempt to remove a given tile from the board. A tile can be removed from
	 * the board only if it is on the board, and only during the turn that it
	 * was placed there.
	 * 
	 * @param tile
	 *            - the Tile to be removed
	 * 
	 * @return true if the tile was successfully removed, false otherwise
	 */
	@Override
	public boolean retract(Tile tile) {
		// TODO Auto-generated method stub

		for (int col = 0; col < 20; col++) {

			for (int row = 0; row < 20; row++) {
				if ((_sqmatrix[col][row].getTile() != null)
						&& (_sqmatrix[col][row].getTile() == tile)) {
					for (int i = 0; i < _tempplacelist.size(); i++) {
						if (_sqmatrix[col][row] == _tempplacelist.get(i)) {
							// This _currentplayer needs to be handled in an
							// eventhandler not -here-
							_currentPlayer.addTR(_sqmatrix[_tempplacelist
									.get(i).getCol()][_tempplacelist.get(i)
									.getRow()].getTile());
							_sqmatrix[_tempplacelist.get(i).getCol()][_tempplacelist
									.get(i).getRow()].removeTile();
							// _currentWord = _currentWord. tile.getChar();
							// need to add tile to player here also.
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	/**
	 * Determine whether the square at the indicated row and column location
	 * contains a Tile or not. This method must return false if the given row
	 * and column do not refer to a location on the board.
	 * 
	 * @param row
	 *            - the row of the square being queried, expressed as an int
	 * @param col
	 *            - the column of the square being queried, expressed as an int
	 * 
	 * @return true if the squared indicated by row and col exists and does not
	 *         contain a Tile, false otherwise (i.e. if the square does not
	 *         exist, or if it does exist but contains a Tile).
	 */
	@Override
	public boolean isEmpty(int row, int col) {
		if (row < 20 && col < 20) {
			if (_sqmatrix[col][row] == null) {
				return false;
			} else {
				return _sqmatrix[col][row].getTile() == null;
			}
		} else {
			return false;
		}
	}

	/**
	 * Provides a String representation of the current state of the board. The
	 * String representation must represent the rows of the board separated by
	 * newline characters. Within a row, each column is represented by a single
	 * character. An empty square is represented by an underscore, '_', while an
	 * occupied square is represented by the letter on its tile, e.g. 'A' for a
	 * square with a Tile whose letter is 'A'. Each row must have the same
	 * number of characters in it (so the representation is square). Since the
	 * board itself is not square, any cell in the representation which does not
	 * correspond to a square on the board is represented by space.
	 * 
	 * @return a String representing the current state of the board.
	 */
	@Override
	public String boardConfiguration() {
		String s = "";
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				if (_sqmatrix[j][i] == null) {
					s += ' ';
				} else {
					if (_sqmatrix[j][i].getTile() == null) {
						s += '_';
					} else {
						char c = _sqmatrix[j][i].getTile().getChar();
						s += c;
					}
				}
			}
			s += "</n>";
		}
		return s;
	}

	/**
	 * Returns the instance variable containing the homesquare.
	 * 
	 * @return the instance variable containing the homesquare.
	 */
	public Square getHomesquare() {
		return _homesquare;
	}

	/**
	 * Method to check North, South, East, West bounds to see if there are
	 * adjacent tiles.
	 * 
	 * @param row
	 *            The Row number of the Square in question.
	 * @param col
	 *            The Column number of the Square in question.
	 * @return true if at least one Adjacency.
	 */
	public boolean checkAdjacency(int row, int col) {
		if ((_sqmatrix[col + 1][row].isEmpty() != true)
				|| (_sqmatrix[col - 1][row].isEmpty() != true)
				|| (_sqmatrix[col][row + 1].isEmpty() != true)
				|| (_sqmatrix[col][row - 1].isEmpty() != true)) {
			return true;
		}

		return false;
	}

	/**
	 * Method to obtain Words horizontally and vertically then put in a String
	 * list to be evaluated.
	 * 
	 * @param row
	 *            The row of the square with adjacent words
	 * @param col
	 *            The column of the square with adjacent words.
	 * @return Returns a list containing all adjacent words.
	 */
	public void getAdjacentWords(int row, int col) {
		char c = 0;
		String s = "";
		temp = 0;
		List<Square> templistnorth = new ArrayList<Square>();
		List<Square> templistsouth = new ArrayList<Square>();
		List<Square> templistwest = new ArrayList<Square>();
		List<Square> templisteast = new ArrayList<Square>();
		List<Square> templisthori = new ArrayList<Square>();
		List<Square> templistverti = new ArrayList<Square>();

		// North Loop
		if (_sqmatrix[col][row].isEmpty() == false) {
			temp = row;
			templisteast.add(_sqmatrix[col][row]);
			templistsouth.add(_sqmatrix[col][row]);
			while ((_sqmatrix[col][row--].isEmpty() == false) && (row != 0)) {
				templistnorth.add(_sqmatrix[col][row]);
			}
			row = temp;
			while ((_sqmatrix[col][row++].isEmpty() == false) && (row != 20)) {
				templistsouth.add(_sqmatrix[col][row]);
			}
			row = temp;
			temp = col;
			while ((_sqmatrix[col--][row].isEmpty() == false) && (col != 0)) {
				templistwest.add(_sqmatrix[col][row]);
			}
			col = temp;
			while ((_sqmatrix[col++][row].isEmpty() == false) && (col != 20)) {
				templisteast.add(_sqmatrix[col][row]);
			}
			col = temp;
			for (int y = templistwest.size(); y > 1; y--) {
				templisthori.add(templistwest.get(y));
			}
			templisthori.add(_sqmatrix[col][row]);
			for (int w = 1; w < templisteast.size(); w++) {
				templisthori.add(templisteast.get(w));
			}
			for (int x = templistnorth.size(); x > 1; x--) {
				templistverti.add(templistnorth.get(x));
			}
			templistverti.add(_sqmatrix[col][row]);
			for (int z = 1; z < templisteast.size(); z++) {
				templistverti.add(templistsouth.get(z));
			}
			for (int e = 0; e < templistverti.size(); e++) {
				c = templistverti.get(e).getTile().getChar();
				s = s + c;
			}
			if (!s.matches(_currentWord)) {
				_tempwordlist.add(s);
			}
			s = "";
			for (int w = 0; w < templisthori.size(); w++) {
				c = templisthori.get(w).getTile().getChar();
				s = s + c;
			}
			if (!s.matches(_currentWord)) {
				_tempwordlist.add(s);
			}
			s = "";
		}

	}

	/**
	 * Method that generates an arraylist of type string, this list contains
	 * words currently affected by tiles placed this turn. array list is
	 * generated with the get adjacent words method and is initialized with the
	 * current word that the play placed
	 * 
	 * @return an arraylist of "words" (String)
	 */
	public ArrayList<String> getWordList() {
		String p = "";
		for (int i = 0; i < _currentWord.length(); i++) {
			p += _currentWord.charAt(i);
		}
		_tempwordlist.add(p);
		for (int o = _tempplacelist.size(); o > 0; o--) {
			getAdjacentWords(_tempplacelist.get(o).getRow(), _tempplacelist
					.get(o).getCol());
		}
		return _tempwordlist;
	}

	/**
	 * Method that obtains a list of words in the Dictionary
	 * 
	 * @return arraylist of words
	 */

	public List<String> getWordBank() {
		String p = "";
		List<String> wordlist = new ArrayList<String>();
		CharacterFromFileReader cffr = new CharacterFromFileReader(
				"/projects/CSE116/words");

		while (cffr.hasNext()) {
			char c = cffr.next();
			if (c != '\n') {
				p += c;
			} else {
				wordlist.add(p);
				p = "";
			}
		}
		System.out.println(wordlist);
		return wordlist;
	}
	
	public static void main(String[] args){
		new Board();
	}

	/**
	 * A method that checks a string to see if it is a valid word.
	 * 
	 * @param s
	 *            The string to be checked.
	 * @return True if the word is valid, false if not.
	 */

	public ArrayList<String> wordCheck(ArrayList<String> s) {
		ArrayList<String> rightWords = new ArrayList<>();
		for (int i =0; i < s.size(); i++){
			if (getWordBank().contains(s.get(i))) {
				rightWords.add(s.get(i));
			}
		}
		return rightWords;
	}

	/**
	 * Resets the _currentWord instance variable to an empty string variable.
	 */
	public void resetCurrentWord() {
		_currentWord = "";
	}

	/**
	 * A method that sums the int value of each char in a string.
	 * 
	 * @param s
	 *            The string whose chars are to be summed.
	 * @return An int holding the sum of the word score.
	 */
	public Integer scoreWord(String s) {
		_scoreCount = 0;
		Character c;
			for(int x = 0; x<s.length(); x++){
				  c = s.charAt(x);
				  if (c == 'E'){
					  _scoreCount = _scoreCount + 1;}
				  else if (c == 'T'){
					  _scoreCount = _scoreCount + 2;}
				  else if ((c == 'A') || (c == 'O')){
					  _scoreCount = _scoreCount + 3;}
				  else if ((c == 'H') || (c == 'I') || (c == 'N') || (c == 'R') || (c == 'S')){
					  _scoreCount = _scoreCount + 4;} 
				  else if ((c == 'D') || (c == 'L')){
					  _scoreCount = _scoreCount + 5;}
				  else if ((c == 'C') || (c == 'M') || (c == 'U') || (c == 'W')){
					  _scoreCount = _scoreCount + 6;}  
				  else if ((c == 'B') || (c == 'F') || (c == 'G') || (c == 'K') || (c == 'P') || (c == 'V') || (c == 'Y')){
					  _scoreCount = _scoreCount + 7;} 
				  else if ((c == 'J') || (c == 'Q') || (c == 'X') || (c == 'Z')){
					  _scoreCount = _scoreCount + 8;}  
			  
		}
		return _scoreCount;
	}
			//1 -E, 
			//2 -T
			//3 -A, O
			//4	-H, I, N, R, S
			//5	-D, L
			//6	-C, M, U, W
			//7	-B, F, G, K, P, V, Y
			//8	-J, Q, X, Z
			

	/**
	 * An acsessor method for the instance variable holding the current word
	 * try.
	 * 
	 * @return
	 */
	public String getCurrentWord() {
		return _currentWord;
	}

	/**
	 * A mutator method that sets the _currentPlayer variable to the current
	 * player determined by the board class.
	 * 
	 * @param p
	 *            The current player determined by the game class.
	 */
	public void setCurrentPlayer(Player p) {
		_currentPlayer = p;
	}
	/**
	 * Method to clear the templplace list, will be called in End Turn
	 */
	public void resetTempPlaceList(){
		_tempplacelist = new ArrayList<Square>();
	}
}
