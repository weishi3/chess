package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;
import helpers.*;
import chessboardDesign.*;
import playChess.*;


public class QueenTest {

	@Test
	public void canBeBlocked() {
		Game game = new Game();
		
		//blocked by pawn
		Action move = new Action (new Coordinate(3,0),new Coordinate(3,5));
		game.newAttempt(move);
		game.Move();
		
		assertEquals(game.getGameTurn(),PlayerIE.red);
		WOG target = game.getBoard().getAGrid(new Coordinate(3,5)).getEleOnGrid();
		assertEquals(null,target);
	}
	
	
	
	@Test
	public void IamRook() {
		Game game = new Game();
		
		
		
		Action moveR = new Action (new Coordinate(4,1),new Coordinate(4,3));
		game.newAttempt(moveR);
		game.Move();
		
		//black advance two to the opposite
		Action moveB = new Action (new Coordinate(3,6),new Coordinate(3,4));
		game.newAttempt(moveB);
		game.Move();
		
		
		//now red pawn capture black
		moveR = new Action (new Coordinate(4,3),new Coordinate(3,4));
		game.newAttempt(moveR);
		game.Move();
		
		//but killed by black queen
		moveB = new Action (new Coordinate(3,7),new Coordinate(3,4));
		game.newAttempt(moveB);
		game.Move();
		
		WOG target = game.getBoard().getAGrid(new Coordinate(3,4)).getEleOnGrid();
		assertEquals(PlayerIE.black,target.getPlayer());
		assertEquals(ChessType.queen,target.getType());
		assertEquals(game.getGameTurn(),PlayerIE.red);
	}
	
	
	
	
	@Test
	public void IamBishopAlso() {
		Game game = new Game();
		
		
		//red pawn advance
		Action moveR = new Action (new Coordinate(4,1),new Coordinate(4,3));
		game.newAttempt(moveR);
		game.Move();
		
		//black pawn advance two 
		Action moveB = new Action (new Coordinate(7,6),new Coordinate(7,4));
		game.newAttempt(moveB);
		game.Move();
		
		
		//now red queen capture black pawn
		moveR = new Action (new Coordinate(3,0),new Coordinate(7,4));
		game.newAttempt(moveR);
		game.Move();
		
		
		
		WOG target = game.getBoard().getAGrid(new Coordinate(7,4)).getEleOnGrid();
		assertEquals(PlayerIE.red,target.getPlayer());
		assertEquals(ChessType.queen,target.getType());
		assertEquals(game.getGameTurn(),PlayerIE.black);
	}
	

}
