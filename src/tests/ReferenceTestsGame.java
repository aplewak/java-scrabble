package tests;

import static org.junit.Assert.assertTrue;

import java.awt.List;
import java.util.AbstractMap.SimpleImmutableEntry;

import org.junit.Before;
import org.junit.Test;

import code.interfaces.*;
import code.Player;
import code.model.Game;

public class ReferenceTestsGame {
	
	private code.model.Game _game;
	
	@Before
	public void setup() {
	
		_game = new Game();
	
	}
	
	
	@Test
	// no players
	public void test_start0() {
		//_game.start();
		boolean expected = false;
		boolean actual = _game.start();
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected == actual);
	}
	
	// register two players
	@Test
	public void test_start1() {
		Player p = new Player("" , _game);
		Player p1 = new Player("" , _game);
		_game.register(p1);
		_game.register(p);
		
		boolean expected = true;
		boolean actual = _game.start();
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected == actual);
	
	}
	
	// /trying to register 1 player
	@Test
	public void test_start2() {
		Player p1 = new Player("" , _game);
		_game.register(p1);
		//_game.start();
		boolean expected = false;
		boolean actual = _game.start();
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected == actual);
	
	}
	
	@Test
	// register one player and obtain a score from that player
	public void score0() {
		Player player = new Player("" , _game);
		_game.register(player);
		_game.score(player);
		int score = _game.score(player);
		int expected = _game.score(player);
		
		int actual = score;
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected == actual);
	
	}
	
	@Test
	// register 2 players and obtain both scores
	public void score1() {
		Player player = new Player("" , _game);
		_game.start();
		int actual = _game.score(player);
		int expected = -1;
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected == actual);
	
	}
	
	@Test
	// //register no players and try to obtain a score
	public void score2() {
		Player p1 = new Player("" , _game);
		Player p2 = new Player("" , _game);
		_game.register(p1);
		_game.register(p2);
		_game.start();
		int actual = _game.score(p1);
		int expected = p1.getScore();
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected == actual);
		
	}
	
	@Test
	// //register 1 players
	public void register0() {
		Player player = new Player("" , _game);
		_game.register(player);
		
		boolean expected = _game.start();
		boolean actual = false;
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected == actual);
		
	}
	
	@Test
	// //register 2 players 
	public void register1() {
		
		Player p1 = new Player("" , _game);
		_game.register(p1);
		boolean actual =_game.register(p1);
		boolean expected = true;
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected == actual);
	
	}
	
	@Test
	// //register no players 
		public void register2() {
		Player player = null;
		boolean expected = false;// should return false
		boolean actual = _game.register(player);
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected == actual);
		
	}
	
	
	@Test
	// //register one player players and obtain order
	public void order0() {
	
		Player p1 = new Player("", _game);
		Player p2 = new Player("", _game);
		_game.register(p1);
		_game.register(p2);
		_game.start();
		
		java.util.List<Player> actual = _game.orderOfPlay();
		java.util.List<Player> expected1 = new java.util.ArrayList<Player>();
		expected1.set(0, p1);
		expected1.set(1, p2);
		java.util.List<Player> expected2 = new java.util.ArrayList<Player>();
		expected2.set(0, p2);
		expected2.set(1, p1);
		assertTrue("",expected1.equals(actual)||expected2.equals(actual));
	}
	
	@Test
	// //get two scored from the game, and call the method to retrieve last turn
	// word score
	public void lasttwscores() {
		Player player = new Player("" , _game);
		Player player1 = new Player("" , _game);
		_game.register(player);
		_game.register(player1);
		_game.start();
		_game.score(player);
		_game.score(player);
		
		java.util.List<SimpleImmutableEntry<String, Integer>> actual = _game.lastTurnWordScores();
		java.util.List<SimpleImmutableEntry<String, Integer>> expected = new java.util.ArrayList<SimpleImmutableEntry<String, Integer>>();
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected.equals(actual));
	}
	
	@Test
	// //register two players start the game then end the turn
	public void endTurn() {
		Player player = new Player("" , _game);
		Player p1 = new Player("" , _game);
		_game.register(player);
		_game.register(p1);
		_game.start();
		_game.endTurn();
		
		
		boolean expected = _game.endTurn();
		boolean actual = true;
		
		assertTrue("expected: " + expected + "  " + "actual: " + " " + actual,
		expected == actual);
	}
	
	}// end

