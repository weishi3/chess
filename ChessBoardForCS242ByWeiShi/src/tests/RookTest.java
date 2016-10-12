package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;
import helpers.*;
import chessboardDesign.*;
import playChess.*;


public class RookTest {

	@Test
	public void moveToBlankArea() {
		Game game = new Game();
		
		//blocked by pawn
		Action move = new Action (new Coordinate(0,0),new Coordinate(0,5));
		game.newAttempt(move);
		game.Move();
		
		assertEquals(game.getGameTurn(),PlayerIE.red);
		WOG target = game.getBoard().getAGrid(new Coordinate(0,5)).getEleOnGrid();
		assertEquals(null,target);
		
		
		
		//valid case:
		Action moveR = new Action (new Coordinate(0,1),new Coordinate(0,3));
		game.newAttempt(moveR);
		game.Move();
		
		//black advance two to the opposite
		Action moveB = new Action (new Coordinate(0,6),new Coordinate(0,4));
		game.newAttempt(moveB);
		game.Move();
		
		// now red rook can move
		moveR = new Action (new Coordinate(0,0),new Coordinate(0,2));
		game.newAttempt(moveR);
		game.Move();
		
		target = game.getBoard().getAGrid(new Coordinate(0,2)).getEleOnGrid();
		assertEquals(PlayerIE.red,target.getPlayer());
		assertEquals(game.getGameTurn(),PlayerIE.black);
		
		
	}
	
	
	
	@Test
	public void capture() {
		
		Game game = new Game();
		
		
		
		Action moveR = new Action (new Coordinate(1,1),new Coordinate(1,3));
		game.newAttempt(moveR);
		game.Move();
		
		//black advance two to the opposite
		Action moveB = new Action (new Coordinate(0,6),new Coordinate(0,4));
		game.newAttempt(moveB);
		game.Move();
		
		
		//now red pawn capture black
		moveR = new Action (new Coordinate(1,3),new Coordinate(0,4));
		game.newAttempt(moveR);
		game.Move();
		
		//but killed by black rook
		moveB = new Action (new Coordinate(0,7),new Coordinate(0,4));
		game.newAttempt(moveB);
		game.Move();
		
		WOG target = game.getBoard().getAGrid(new Coordinate(0,4)).getEleOnGrid();
		assertEquals(PlayerIE.black,target.getPlayer());
		assertEquals(ChessType.rook,target.getType());
		assertEquals(game.getGameTurn(),PlayerIE.red);
		
		
	}

}
