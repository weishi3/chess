package tests;

import static org.junit.Assert.*;
import helpers.*;
import chessboardDesign.*;
import playChess.*;

import org.junit.Test;




public class hanWarriorTest {

	@Test
	public void move() {
		Game game = new Game();
		
		
		game.getBoard().getAGrid(new Coordinate(4,2)).setWOG(PlayerIE.red, ChessType.hanWarrior);
		Action move = new Action (new Coordinate(4,2),new Coordinate(4,4));
		game.newAttempt(move);
		game.Move();
		
		ChessType type = game.getBoard().getAGrid(new Coordinate(4,2)).getEleOnGrid().getType();
		WOG dest = game.getBoard().getAGrid(new Coordinate(5,5)).getEleOnGrid();
		assertEquals(ChessType.hanWarrior,type);
		assertEquals(PlayerIE.red,game.getGameTurn());
		assertEquals(dest,null);
		
		
		move = new Action (new Coordinate(4,2),new Coordinate(5,3));
		game.newAttempt(move);
		game.Move();
		assertEquals(PlayerIE.red,game.getGameTurn());
		
		
		
		move = new Action (new Coordinate(4,2),new Coordinate(5,2));
		game.newAttempt(move);
		game.Move();
		assertEquals(PlayerIE.red,game.getGameTurn());
		
		move = new Action (new Coordinate(4,2),new Coordinate(4,3));
		game.newAttempt(move);
		game.Move();
		assertEquals(PlayerIE.black,game.getGameTurn());
		
		
		
		
		
		
		
	}
	
	@Test
	public void attackTest() {
		Game game = new Game();
		
		
		game.getBoard().getAGrid(new Coordinate(4,2)).setWOG(PlayerIE.black, ChessType.hanWarrior);
		Action move = new Action (new Coordinate(5,1),new Coordinate(5,2));
		game.newAttempt(move);
		game.Move();
		assertEquals(PlayerIE.black,game.getGameTurn());
		
		
		move = new Action (new Coordinate(4,2),new Coordinate(5,2));
		game.newAttempt(move);
		game.Move();
		
		
		
		
		ChessType type = game.getBoard().getAGrid(new Coordinate(5,2)).getEleOnGrid().getType();
		WOG remain = game.getBoard().getAGrid(new Coordinate(4,2)).getEleOnGrid();
		assertEquals(ChessType.hanWarrior,type);
		assertEquals(PlayerIE.red,game.getGameTurn());
		assertEquals(remain,null);
		
	
	}
	
	
	
	
	
	
	

}
