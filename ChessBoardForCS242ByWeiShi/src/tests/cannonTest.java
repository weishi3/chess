package tests;

import static org.junit.Assert.*;
import helpers.*;
import chessboardDesign.*;
import playChess.*;

import org.junit.Test;




public class cannonTest {

	@Test
	public void moveTest() {
		
		
		Game game = new Game();
		game.getBoard().getAGrid(new Coordinate(4,4)).setWOG(PlayerIE.red, ChessType.chineseCannon);
		Action move = new Action (new Coordinate(4,4),new Coordinate(5,5));
		game.newAttempt(move);
		game.Move();
		ChessType type = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid().getType();
		
		WOG dest = game.getBoard().getAGrid(new Coordinate(5,5)).getEleOnGrid();
		assertEquals(ChessType.chineseCannon,type);
		assertEquals(PlayerIE.red,game.getGameTurn());
		assertEquals(dest,null);
		
		 move = new Action (new Coordinate(4,4),new Coordinate(2,4));
		game.newAttempt(move);
		game.Move();
		type = game.getBoard().getAGrid(new Coordinate(2,4)).getEleOnGrid().getType();
	    
		WOG remain = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid();
		assertEquals(ChessType.chineseCannon,type);
		assertEquals(PlayerIE.black,game.getGameTurn());
		assertEquals(remain,null);
		
	}
	
	@Test
	public void attackTest() {
		
		
		Game game = new Game();
		game.getBoard().getAGrid(new Coordinate(4,4)).setWOG(PlayerIE.red, ChessType.chineseCannon);
		Action move = new Action (new Coordinate(4,4),new Coordinate(4,6));
		game.newAttempt(move);
		game.Move();
		ChessType type = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid().getType();
		
		WOG dest = game.getBoard().getAGrid(new Coordinate(5,5)).getEleOnGrid();
		assertEquals(ChessType.chineseCannon,type);
		assertEquals(PlayerIE.red,game.getGameTurn());
		assertEquals(dest,null);
		
		//black king is checkmate if init a red cannon at (4,4)
		game.isKingCheckMate(PlayerIE.red, new Coordinate(4,7));
		
		
		
		
		
		
	}
	
	
	
	
	
	
	

}

