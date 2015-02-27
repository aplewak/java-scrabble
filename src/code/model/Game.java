package code.model;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import code.Player;
import code.TilePile;
import code.interfaces.IGame;

public class Game implements IGame {
	/**
	 * The player with the highest score.
	 */
	private Player _winningPlayer;
	/**
	 * A boolean variable representing if the game is finished.
	 */
	private boolean _hasFinished;
	/**
	 * Denotes the number of words played.
	 */
	private int _wordsplayed;
	/**
	 * References a Array list of all players registered in the game.
	 */
	private ArrayList<Player> _list;
	/**
	 * A boolean variable which is true if the game has been started, false if
	 * not.
	 */
	private boolean _started;
	/**
	 * An instance variable which holds a reference to the player whose turn it
	 * is.
	 */
	private Player turnholder;
	/**
	 * An instance variable that holds a temporary reference to _list.
	 */
	private ArrayList<Player> _temp;
	/**
	 * A temporary reference to the turnholder.
	 */
	private Player playertemp;
	/**
	 * An instance variable that holds a reference to the board in association
	 * with the game class.
	 */
	private Board _board;
	/**
	 * A variable holding a reference to the tilepile instantiated in the start
	 * method.
	 */
	private TilePile _tilepile;

	/**
	 * The constructor for the Game class. Creates a new array list to hold each
	 * player registered for the game. Sets started to false.
	 */
	public Game() {
		_list = new ArrayList<Player>();
		_started = false;
		_hasFinished = false;
		_wordsplayed = 0;
		_board = new Board();
	}

	/**
	 * A method which returns a java.util.List object containing Player objects
	 * in the order of their turns.
	 * 
	 * @return a list of players
	 */
	@Override
	public ArrayList<Player> orderOfPlay() {
		return _list;
	}

	/**
	 * A method which returns the current score of a given player. The behavior
	 * of this method if Player is not valid is undefined.
	 * 
	 * @param player
	 *            - the Player whose score we desire
	 * @return the Player's score
	 */
	@Override
	public int score(Player player) {
		if (_started != false) {
			return player.getScore();
		} else {
			return -1;
		}
	}

	/**
	 * A method which returns the current score of a given player. The behavior
	 * of this method if Player is not valid is undefined.
	 * 
	 * @param player
	 *            - the Player whose score we desire
	 * @return the Player's score
	 */
	@Override
	public boolean register(Player player) {
		if (_started == false && player != null) {
			_list.add(player);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Attempt to start the game. A game can be started only if it has not
	 * already been started, and if two or more Players have been registered.
	 * 
	 * @return true if starting the game was successful, false otherwise
	 */
	@Override
	public boolean start() {
		// TODO Auto-generated method stub

		if (_started == false && _list.size() > 1) {
			Collections.shuffle(_list);
			turnholder = orderOfPlay().get(0);
			turnholder.setTurn();
			_board.setCurrentPlayer(turnholder);
			_tilepile = new TilePile();
			for (int i = 0; i < _list.size(); i++) {
				_list.get(i).addToTileRack();
			}
			_started = true;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Attempt to the end the current Player's turn. A Player's turn can be
	 * ended only if either no word was placed, or the word placed is valid
	 * according to the rules of the game.
	 * 
	 * For more details on word placement validity, see
	 * http://www.cse.buffalo.edu
	 * /faculty/alphonce/Courses/Spring2008/cse116/Project/Stage1/
	 * 
	 * @return true if the current Player's turn was ended, false otherwise
	 */
	@Override
	public boolean endTurn() {
		ArrayList<String> currentword = new ArrayList<String>();
		currentword.add(_board.getCurrentWord());
		if (_list.get(0).checkTurn()
				&& (_board.wordCheck(currentword).size() > 0)) {
			turnholder.setScore(_board.scoreWord(_board.getCurrentWord()));
			_board.resetCurrentWord();
			for (int i = 0; i < this.lastTurnWordScores().size(); i++) {
				turnholder
						.setScore(this.lastTurnWordScores().get(i).getValue());
			}
			if (_tilepile.isEmpty() && turnholder.emptyTileRack()) {
				_winningPlayer = this.winningPlayer();
			}
			turnholder.addToTileRack();
			_temp = _list;
			turnholder.resetTurn();
			playertemp = turnholder;
			_temp.remove(turnholder);
			_temp.add(playertemp);
			_list = _temp;
			turnholder = _list.get(0);
			turnholder.setTurn();
			_board.setCurrentPlayer(turnholder);
			_board.resetTempPlaceList();
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method returns the words and corresponding scores which were placed
	 * on the board on the last turn. The words are represented as Strings, the
	 * scores as Integers. The words and scores are paired in a
	 * SimpleImmutableEntry, a class whose instances hold two values (a "key"
	 * (the word) and a corresponding "value" (the score)).
	 * 
	 * @return a list of word-score pairs.
	 */
	@Override
	public List<SimpleImmutableEntry<String, Integer>> lastTurnWordScores() {
		List<SimpleImmutableEntry<String, Integer>> scores = new ArrayList<SimpleImmutableEntry<String, Integer>>();
		ArrayList<String> s = _board.wordCheck(_board.getWordList());
		for (int i = 0; i < s.size(); i++) {
			SimpleImmutableEntry<String, Integer> entry = new AbstractMap.SimpleImmutableEntry<String, Integer>(
					s.get(i), _board.scoreWord(s.get(i)));
			scores.add(entry);
		}
		return scores;
	}

	/**
	 * An acessor method that returns the tilepile.
	 * 
	 * @return Returns a reference to a pile of tiles.
	 */
	public TilePile getTilePile() {
		return _tilepile;
	}

	/**
	 * Calculates the player with the highest score
	 * 
	 * @return the player with the highest score
	 */
	public Player winningPlayer() {
		Player highestScorer = _list.get(0);
		for (int i = 0; i < _list.size(); i++) {
			if (_list.get(i).getScore() > _list.get(0).getScore()) {
				highestScorer = _list.get(i);
			}
		}
		return highestScorer;
	}

	/**
	 * this method which returns an int, randomizes the dead space in the
	 * corners of the board. Returning the _board within the gui.
	 * 
	 * @return a reference to the dead space on the corner of the board in the
	 *         gui in the y-axis
	 */
	public int getRandy() {
		return _board.getRandy();
	}

	/**
	 * similarily to getRandy but is getting the dead zone of the gui within the
	 * x axis
	 * 
	 * @return integer reference to the board in the gui which has dead space in
	 *         the x axis
	 */
	public int getRandx() {
		return _board.getRandx();
	}

	/**
	 * A method to return as a boolean of when the game has started.
	 * 
	 * @return true or false depending on how many players are subjected to play
	 */
	public boolean hasStarted() {
		return _started;
	}

	/**
	 * A method of type board which returns the representation of the board in
	 * the center of the flowlayout
	 * 
	 * @return a new board for every player that registers through the arguments
	 */
	public Board getBoard() {
		return _board;
	}
}
