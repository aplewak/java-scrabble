package code;

import java.util.ArrayList;
import java.util.List;

import code.model.Game;

/**
 * A stub Player class to prevent compilation errors in the IGame interface.
 */
public class Player {
	/**
	 * An instance variable holding a players total score.
	 */
	private int _score;
	/**
	 * An instance variable that denotes if it is the player's turn.
	 */
	private int _turn;
	/**
	 * An instance variable holding the Tile rack for that specific player
	 */
	private ArrayList<Tile> _tilerack;
	/**
	 * A string variable holding the word a player forms on the board
	 */
	private String _wordtry;
	/**
	 * A string variable holding the players name.
	 */
	private String _name;
	/**
	 * An instance variable referencing the game a player is registered with.
	 */
	private Game _game;

	/**
	 * The constructor for the Player class. Assigns the name of a player to a
	 * string instance variable. Sets the player's score to 0. Creates a new
	 * tile rack for the player. Sets the turn to 0. Assigns the game param to
	 * the _game instance variable.
	 * 
	 * @param name
	 *            The inputed player name to be assigned to the _name variable.
	 * @param game
	 *            The game that the player is to be registered with.
	 */
	public Player(String name, Game game) {
		_name = name;
		_score = 0;
		_tilerack = new ArrayList<Tile>(12);
		_turn = 0;
		_game = game;
	}

	/**
	 * An acessor method for the players score.
	 * 
	 * @return Returns the saved value of the player's score.
	 */
	public int getScore() {
		return _score;
	}

	/**
	 * A method to add a word score to the players score.
	 * 
	 * @param a
	 *            A variable holding the word score to be added.
	 */
	public void setScore(int a) {
		_score = _score + a;
	}

	/**
	 * A method that returns true if it is the players turn.
	 * 
	 * @return A boolean value. True if it is the players turn, false if not.
	 */
	public boolean checkTurn() {
		if (_turn != 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * A method that sets an int variable to 1 if it is the players turn.
	 */
	public void setTurn() {
		_turn = 1;
	}

	/**
	 * A method that sets an int variable to 0 if it is no longer the players
	 * turn.
	 */
	public void resetTurn() {
		_turn = 0;
	}

	/**
	 * A method that fills a players tile rack with tiles.
	 */
	public void addToTileRack() {
		while (_tilerack.size() < 12) {
			_tilerack.add(_game.getTilePile().tileGrab());
		}
	}

	/**
	 * 
	 * @param t
	 */
	public void addTR(Tile t) {
		_tilerack.add(t);
	}

	/**
	 * A method to check if the tile rack is empty
	 * 
	 * @return true if empty
	 */
	public boolean emptyTileRack() {
		return _tilerack.isEmpty();
	}

	/**
	 * Returns the players tile rack
	 * 
	 * @return this players tile rack
	 */
	public ArrayList<Tile> getTileRack() {
		return _tilerack;
	}

	/**
	 * Returns the players name
	 * @return a string representation of the players name
	 */
	public String getName() {
		return _name;
	}

	/**
	 * removes a tile from the players tile rack
	 * @param k the tile to be removed
	 */
	public void removeTile(int k) {
		_tilerack.set(k, null);
	}
}
