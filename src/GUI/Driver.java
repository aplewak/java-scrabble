package GUI;


import javax.swing.SwingUtilities;
/**
 * The Driver class contains a definition of main. 
 */
public class Driver{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Gui(args));
	}
}