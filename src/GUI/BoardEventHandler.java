package GUI;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import code.Tile;

public class BoardEventHandler implements ActionListener {
	/**
	 * Refers to the gui
	 */
	private Gui _g;
	/**
	 * the j label the event handler is placed on
	 */
	private JLabel _j;
	/**
	 * reffers to the row that the button is in
	 */
	private int _row;
	/**
	 * Refers to the col that the button is in
	 */
	private int _col;

	/**
	 * Constructor for board
	 * 
	 * @param g
	 *            takes in a gui and assigns it to _g
	 * @param j
	 *            takes in a JLabel and assigns it to _j
	 * @param row
	 *            takes in a int that refers to the row of the button
	 * @param col
	 *            takes in a int that refers to the col of the button
	 */
	public BoardEventHandler(Gui g, JLabel j, int row, int col) {
		// TODO Auto-generated constructor stub
		_g = g;
		_j = j;
		_row = row;
		_col = col;
	}

	/**
	 * When a the button is clicked this method is run. It gets the char from
	 * the tile and displays it graphically on the board. This also places the
	 * tile on the board through the board.place() method. Finally it removes
	 * the tile from the current players tilerack and sets the tile selected
	 * variable in the gui class to null.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		char c = _g.getTileSelected().getChar();
		String s = "";
		s += c;
		_j.setText(s);
		_g.getGame().getBoard().place(_g.getTileSelected(), _row, _col);
		_g.getGame().orderOfPlay().get(0).removeTile(_g.getK());
		_g.setTileSelected(null);

	}

}
