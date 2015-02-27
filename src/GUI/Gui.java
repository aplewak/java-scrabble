package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import code.Player;
import code.Square;
import code.Tile;
import code.model.Game;

public class Gui implements Runnable {
	/**
	 * I-Variable of type JLabel used for characters being placed
	 */
	private JLabel _letter;
	/**
	 * I-Variable of type ArrayList<JFrame> used for holding a relationship with the windows being used.
	 */
	private ArrayList<JFrame> _framelist;
	/**
	 * I-Variable of type ARrayList<Player> used to hold a reference to the order of play in the game class.
	 */
	private ArrayList<Player> _playalist;
	/**
	 * I-Variable of type Game for the current Game.
	 */
	private Game _game;
	/**
	 * I-Variable of type JButton[][] used for the matrix of buttons the characters will be placed on.
	 */
	private JButton[][] _bmatrix;
	/**
	 * I-Variable of type String[], holding a reference to the Arguments entered in the Driver class.
	 */
	private String[] _args;
	/**
	 * I-Variable of type String[] used as a list holding all the players names.
	 */
	private String[] _names;
	/**
	 * instance variable of type Tile holding a reference to the tile currently selected
	 */
	public Tile _tile;
	/**
	 * Variable of type string used for the name configurations
	 */
	String _nameconfig;
	/**
	 * Variable of type string used for the current tile choosen
	 */
	String _tilechoosen;
	/**
	 * I-Varialbe of type ArrayList<Tile> used to hold the list of tiles for each players tilerack.
	 */
	private ArrayList<Tile> tileList;
	/**
	 * Instance variable of type int, used for counting.
	 */
	private int _k;
	/**
	 * I-Variable of type JFrame, used for creating each players tilerack.
	 */
	private JFrame _window;
	/**
	 * I-Variable of type int, holds a reference to the homsquare row number.
	 */
	private int _hmsqrow;
	/**
	 * 
	 */
	private int _hmsqcol;
	private Player _current;
	/**
	 * An instance variable dat holds how many playas in da game
	 */
	private int _playas;

	public Gui(String[] args) {
		_args = args;
	}
/**
 * Create the board and separate tile rack for each player
 */
	@Override
	
	public void run() {
		
		
		_framelist = new ArrayList<JFrame>();
		_playas = 0;
		for (int i = 0; i < _args.length; i++) {
			String s = "-p";
			if (_args[i].equals(s)) {
				_playas = _playas + 1;
			}
		}
		_names = new String[_playas];
		int j = 0;
		for (int i = 0; i < _args.length; i++) {
			String s = "-p";
			if (_args[i].equals(s)) {
				_names[j] = _args[i + 1];
				j = j + 1;
			}
		}
		_game = new Game();
		for (int i = 0; i < _playas; i++) {
			_game.register(new Player(_names[i], _game));
		}
		
		_game.start();
		_playalist = _game.orderOfPlay();
		if (_game.hasStarted() == true) {
			JFrame window1 = new JFrame("Main Board");
			window1.setLayout(new BorderLayout());
			_hmsqrow = _game.getBoard().homeSquareRow();
			_hmsqcol = _game.getBoard().homeSquareCol();
			JPanel centerBoard = new JPanel();
			centerBoard.setSize(30, 40);
			GridLayout bl = new GridLayout(20, 20);
			centerBoard.setLayout(bl);
			_bmatrix = new JButton[20][20];
			for (int col = 0; col < (20 - _game.getRandx()); col++) {
				for (int row = 0; row < _game.getRandy(); row++) {
					JButton b = new JButton();
					JLabel letter = new JLabel("");
					b.add(letter);
					b.addActionListener(new BoardEventHandler(this, letter,
							row, col));
					_bmatrix[col][row] = b;
				}
			}
			for (int col = 0; col < 20; col++) {
				for (int row = _game.getRandy(); row < (20 - _game
						.getRandy()); row++) {
					JButton b = new JButton();
					JLabel letter = new JLabel("");
					b.add(letter);
					b.addActionListener(new BoardEventHandler(this, letter,
							row, col));

					_bmatrix[col][row] = b;
				}
			}
			for (int col = _game.getRandx(); col < 20; col++) {
				for (int row = (20 - _game.getRandy()); row < 20; row++) {
					JButton b = new JButton();
					JLabel letter = new JLabel("");
					b.add(letter);
					b.addActionListener(new BoardEventHandler(this, letter,
							row, col));
					_bmatrix[col][row] = b;
				}
			}
			for (int col = (20 - _game.getRandx()); col < 20; col++) {
				for (int row = 0; row < _game.getRandy(); row++) {
					JButton b = new JButton();
					b.setVisible(false);
					_bmatrix[col][row] = b;
				}
			}
			for (int col = 0; col < _game.getRandx(); col++) {
				for (int row = (20 - _game.getRandy()); row < 20; row++) {
					JButton b = new JButton();
					b.setVisible(false);
					_bmatrix[col][row] = b;
				}
			}
			for (int col = 0; col < 20; col++) {
				for (int row = 0; row < 20; row++) {
					centerBoard.add(_bmatrix[col][row]);
				}
			}
			_bmatrix[_hmsqcol][_hmsqrow].setBackground(Color.MAGENTA);
			window1.add(centerBoard, BorderLayout.CENTER);
			window1.setVisible(true);
			window1.setSize(new Dimension(1200,750));
			JPanel scores = new JPanel();
			scores.add(new JTextArea("Turnholder is "
					+ _game.orderOfPlay().get(0).getName()));
			scores.setVisible(true);
			window1.add(scores, BorderLayout.LINE_END);
			JPanel gameName = new JPanel();
			JLabel name = new JLabel("THE GAME OF SHRABBLE");
			gameName.add(name);
			window1.add(gameName, BorderLayout.PAGE_START);
			JPanel endTurn = new JPanel();
			endTurn.setLayout(new GridLayout(2, 0));
			JButton end = new JButton("End Turn");
			end.addActionListener(new EndTurnEH(this));
			JButton retract = new JButton("Retract");
			endTurn.add(end);
			endTurn.add(retract);
			window1.add(endTurn, BorderLayout.LINE_START);
			window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			for (int i = 0; i < _playas; i++) {
				
				_window = new JFrame(_game.orderOfPlay().get(i).getName());
				_window.setVisible(true);
				_framelist.add(_window);

				// JButton west = new JButton("West");
				// panel to hold all the center buttons, tile rack and scores
				
				JPanel tileRack = new JPanel();
				
				
				// ... Create content pane, set layout, add components
				JPanel content = new JPanel();
				content.setLayout(new BorderLayout());
				content.add(tileRack, BorderLayout.CENTER);
				//content.add(centerBoard, BorderLayout.CENTER);

				
				// dimensions so board will stay consistant
				
				
				
				// opened up the tile rack loop up. eventually the references to
				// each button will be used to update the jLabel with the char
				// of
				// the tile.
				
				tileList = _playalist.get(i).getTileRack();
				for (int k = 0; k < 12; k++) {
					JButton c = new JButton();
					String s = "";
					s += tileList.get(k).getChar();
					JLabel letter = new JLabel(s);
					c.add(letter);
					c.addActionListener(new TileRackEventHandler(this, letter,
							tileList.get(k), k, _game.orderOfPlay().get(i)));
					tileRack.add(c);
				}
				
				// /adding a title to the game
				

				// ... Set window characteristics.
				_window.setContentPane(content);
				_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				_window.pack();
			}
		} else {
			JFrame failWindow = new JFrame();
			failWindow.setVisible(true);
			failWindow.setSize(1000, 750);
			JPanel failPanel = new JPanel();
			JLabel failLabel = new JLabel("Two players are required to play.");
			failPanel.add(failLabel);
			failWindow.add(failPanel);
		}
	}
	/**
	 * accesor method for get game
	 * @return the game
	 */
	public Game getGame() {
		return _game;
	}
/**
 * returns a  tile of most recent clicked
 * @return a tile object
 */
	public Tile getTileSelected() {
		return _tile;
	}
	/**mutator method for the selected tile to have communication for the user
	 * 
	 * @param t
	 */

	public void setTileSelected(Tile t) {
		_tile = t;
	}
/**
 * mutator method for the position of tile in the tile rack 
 * @param k
 */
	public void setInt(int k) {
		_k = k;
	}
/**
 * accesor method for the position of tile in the tile rack
 * @return position
 */
	public int getK() {
		return _k;
	}
/**
 * accessor method for the players tile rack
 * @return the tile rack
 */
	public ArrayList<Tile> getTileList() {
		return tileList;
	}
/**
 * mutator method for the tile list
 * @param t
 */
	public void setTileList(ArrayList<Tile> t) {
		tileList = t;
	}
	
}