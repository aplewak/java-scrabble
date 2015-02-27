package code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import code.Tile;
public class TilePile {
	/**
	 * A list holding all of the tiles.
	 */
	private List<Tile> _tilePile; 
	/**
	 * A constructor that adds all the required tiles to the 
	 * tile list. Shuffles the pile of tiles.
	 */
	public TilePile(){
		_tilePile = new ArrayList<Tile>(400);
		for (int i = 0; i < 29; i++){
			_tilePile.add(new Tile('A'));
			_tilePile.add(new Tile('E'));
			_tilePile.add(new Tile('I'));
			_tilePile.add(new Tile('O'));
			_tilePile.add(new Tile('U'));
			
			
		}
		for (int i = 0; i < 15; i++){
			_tilePile.add(new Tile('Y'));
		}
		for (int i = 0; i < 12; i++){
			_tilePile.add(new Tile('B'));
			_tilePile.add(new Tile('C'));
			_tilePile.add(new Tile('D'));
			_tilePile.add(new Tile('F'));
			_tilePile.add(new Tile('G'));
			_tilePile.add(new Tile('H'));
			_tilePile.add(new Tile('J'));
			_tilePile.add(new Tile('K'));
			_tilePile.add(new Tile('L'));
			_tilePile.add(new Tile('M'));
			_tilePile.add(new Tile('N'));
			_tilePile.add(new Tile('P'));
			_tilePile.add(new Tile('Q'));
			_tilePile.add(new Tile('R'));
			_tilePile.add(new Tile('S'));
			_tilePile.add(new Tile('T'));
			_tilePile.add(new Tile('V'));
			_tilePile.add(new Tile('W'));
			_tilePile.add(new Tile('X'));
			_tilePile.add(new Tile('Y'));
			_tilePile.add(new Tile('Z'));
		}
		Collections.shuffle(_tilePile);
		
	}
	/**
	 * A method that returns a new tile from the tile list.
	 * @return A variable holding the tile to be returned
	 */
	public Tile tileGrab(){
		Tile tempTile;
		tempTile = _tilePile.get(0);
		_tilePile.remove(0);
		return tempTile;
	}
	/**
	 * Method to check if the tile pile is empty or not, 
	 * @return boolean
	 */
	public boolean isEmpty(){
		if (_tilePile.size() == 0){
			return true;
		}
		else {
			return false;
		}
		
	}
}
