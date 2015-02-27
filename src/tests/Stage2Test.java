package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import code.Player;
import code.Square;
import code.Tile;
import code.TilePile;
import GUI.Gui;
import code.model.Board;
import code.model.Game;

public class Stage2Test {
	private Game _game;
	@Before
	public void setUp(){
		_game = new Game();
		Player p1 = new Player("Alex" , _game);
		Player p2 = new Player("Dan" , _game);
		_game.register(p1);
		_game.register(p2);
		_game.start();
	}
	@Test
	public void setTileSelectedTest() {
		Gui example = new Gui(null);
		Tile select = new Tile('c');
		example.setTileSelected(select);
		Tile expected = select;
		Tile actual = example.getTileSelected();
		assertTrue("",actual == expected);
	}
	@Test
	public void isEmptyTest() {
		TilePile example = new TilePile();
		boolean actual = example.isEmpty();
		assertTrue("",actual != false);
	}
	@Test
	public void setTileListTest() {
		ArrayList<Tile> i = new ArrayList<Tile>();
		i.add(new Tile('b'));
		Gui example = new Gui(null);
		example.setTileList(i);
		ArrayList<Tile> expected = example.getTileList();
		ArrayList<Tile> actual = i;
		assertTrue("",expected == actual);
	}
	@Test
	public void setTileListTest2() {
		ArrayList<Tile> i = new ArrayList<Tile>();
		i.add(new Tile('b'));
		ArrayList<Tile> w = new ArrayList<Tile>();
		i.add(new Tile('a'));
		Gui example = new Gui(null);
		example.setTileList(i);
		ArrayList<Tile> expected = example.getTileList();
		ArrayList<Tile> actual = w;
		assertFalse("",expected.equals(actual));
	}
	@Test
	public void tileGrabTest() {
		TilePile example = new TilePile();
		boolean b = false;
		if (example.tileGrab() != null){
			b=true;
		}
		boolean actual = b;
		assertTrue("",actual == true);
	}
	
	@Test
	public void checkAdjacencyTest1() {
		Board b = new Board();
		Tile g = new Tile('A');
		Tile x = new Tile('A');
		b.place(g, 5, 2);
		b.place(x, 6, 2);
		boolean actual = b.checkAdjacency(6, 2);
		boolean expected = true;
		assertTrue("", expected==actual);
	}
	@Test
	public void checkAdjacencyTest2() {
		Board b = new Board();
		Tile g = new Tile('A');
		Tile x = new Tile('A');
		b.place(g, 5, 2);
		b.place(x, 5, 3);
		boolean actual = b.checkAdjacency(5, 2);
		boolean expected = true;
		assertTrue("", expected==actual);
	}
	@Test
	public void resetWords() {
		Board b = new Board();
		b.resetCurrentWord();
		String expected = "";
		String actual = b.getCurrentWord();
		assertTrue("", expected==actual);
	}
	
}
