package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import code.Square;
import code.Tile;
import code.model.Board;

public class BoardTests {
	
	private Board _board;
	private Square _homesquare;
	
	@Before
	public void setup(){
		_board = new Board();
		_homesquare = _board.getHomesquare();
	}

	@Test
	public void homeSquareRowTest() {
		int actual = _board.homeSquareRow();
		int expected = _homesquare.getRow();
		assertTrue("", actual==expected);
	}
	@Test
	public void homesquareColTest(){
		int actual = _board.homeSquareCol();
		int expected = _homesquare.getCol();
		assertTrue("", actual==expected);
	}
	@Test
	public void getTest1(){
		Tile t = new Tile('A');
		_board.place(t, 3, 4);
		Tile actual = _board.get(3, 4);
		Tile expected = t;
		assertTrue("",actual.equals(expected));
	}
	@Test
	public void getTest2(){
		Tile actual = _board.get(3, 3);
		Tile expected = null;
		assertTrue("", actual==expected);
	}
	@Test
	public void getTest3(){
		Tile actual = _board.get(10000, 700000);
		Tile expected = null;
		assertTrue("",actual==expected);
	}
	@Test
	public void placeTest1(){
		Tile t = new Tile('A');
		boolean actual = _board.place(t, 10000000, 999999);
		boolean expected = false;
		assertTrue("",actual==expected);
		
	}
	@Test
	public void placeTest2(){
		Tile expected = new Tile('A');
		_board.place(expected, 3, 4);
		Tile actual = _board.get(3, 4);
		assertTrue("", expected.equals(actual));
	}
	@Test
	public void placeTest3(){
		Tile t = new Tile('A');
		_board.place(t, 1, 4);
		boolean actual = _board.isEmpty(1, 4);
		boolean expected = false;
		assertTrue("",actual==expected);
	}
	@Test
	public void testRetract1(){
		Tile t1 = new Tile('A');
		Tile t2 = new Tile('A');
		_board.place(t1, 5, 2);
		_board.retract(t1);
		boolean actual = _board.place(t2, 5, 2);
		boolean expected = true;
		assertTrue("", expected==actual);
	}
	@Test
	public void testRetract2(){
		Tile t = new Tile('A');
		_board.place(t, 2, 1);
		boolean actual = _board.retract(t);
		boolean expected = true;
		assertTrue("", actual==expected);
	}
	@Test
	public void testIsEmpty1(){
		boolean actual = _board.isEmpty(1000000, 997579);
		boolean expected = false;
		assertTrue("", actual==expected);
	}
	@Test
	public void testIsEmpty2(){
		Tile t = new Tile('A');
		_board.place(t,3,1);
		boolean actual = _board.isEmpty(3,1);
		boolean expected = false;
		assertTrue("",actual==expected);
	}
	@Test
	public void testIsEmpty3(){
		boolean actual = _board.isEmpty(1, 1);
		boolean expected = true;
		assertTrue("",actual==expected);
	}
	@Test
	public void boardConfigTest1(){
		String actual = _board.boardConfiguration();
		String expected = "______________      </n>______________      </n>______________      </n>______________      </n>______________      </n>______________      </n>____________________</n>____________________</n>____________________</n>____________________</n>____________________</n>____________________</n>____________________</n>____________________</n>      ______________</n>      ______________</n>      ______________</n>      ______________</n>      ______________</n>      ______________</n>";
		assertTrue("",actual.equals(expected));
	}
	@Test
	public void boardConfigTest2(){
		Tile t1 = new Tile('A');
		Tile t2 = new Tile('B');
		Tile t3 = new Tile('C');
		Tile t4 = new Tile('D');
		Tile t5 = new Tile('A');
		_board.place(t1, 0, 0);
		_board.place(t2, 2, 4);
		_board.place(t3, 10, 9);
		_board.place(t4, 19, 14);
		_board.place(t5, 19, 19);
		String actual = _board.boardConfiguration();
		String expected = "A_____________      </n>______________      </n>____B_________      </n>______________      </n>______________      </n>______________      </n>____________________</n>____________________</n>____________________</n>____________________</n>_________C__________</n>____________________</n>____________________</n>____________________</n>      ______________</n>      ______________</n>      ______________</n>      ______________</n>      ______________</n>      ________D____A</n>";
		assertTrue("actual is "+ actual,actual.equals(expected));
	}
}
