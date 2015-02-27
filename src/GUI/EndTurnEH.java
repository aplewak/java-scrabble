package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EndTurnEH implements ActionListener {
	/**
	 * instance variable of type GUI
	 */
	private Gui _g;
	/**
	 * Constructor that assigns an instance variable of type GUI to the GUI that was carried in
	 * @param g (GUI)
	 */
	public EndTurnEH(Gui g) {
		_g = g;
	}
	/**
	 * When clicked, calls the end turn method within the Game class and refreshes the GUI
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		_g.getGame().endTurn();
		
	}
}
