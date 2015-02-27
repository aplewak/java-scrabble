package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;

import code.Player;
import code.Tile;
import code.model.Game;

public class TileRackEventHandler implements ActionListener {
	/**
	 * The JLabel place on the button
	 */
	private JLabel _j;
	/**
	 * The tile being held in that space in the tile rack
	 */
	private Tile _t;
	/**
	 * the gui
	 */
	private Gui _g;
	/**
	 * the position of the tile in the tile rack
	 */
	private int _k;
	/**
	 * the player who this tile rack belongs to
	 */
	private Player _play;

	/**
	 * The constructor for the Tile Rack event handler
	 * 
	 * @param g
	 *            the current gui
	 * @param j
	 *            the jlabel the button is placed on
	 * @param t
	 *            the tile saved in that spot
	 * @param k
	 *            the position of the tile in the tile rack
	 * @param playa
	 *            the player who this tile rack belongs to
	 */
	public TileRackEventHandler(Gui g, JLabel j, Tile t, int k, Player playa) {

		_g = g;
		_j = j;
		_t = t;
		_k = k;
		_play = playa;
	}

	/**
	 * if it is the current players tile rack clicking on a tile saves the tile
	 * clicked on in the gui class. It removes the text from the button in the
	 * tile rack. Finally it saves the position of the clicked tile in the gui.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (_play == _g.getGame().orderOfPlay().get(0)) {
			_g.setTileSelected(_t);
			_j.setText("");
			_g.setInt(_k);
		}
	}

}
