package tests;


import static org.junit.Assert.*;

import org.junit.Test;

import helpers.*;
import chessboardDesign.*;
import playChess.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PawnTest {
	
	
	//both red and black pawn can move one grid 
	@Test
	public void advanceOne() {
		Game game = new Game();
		
		//move red
		Action moveR = new Action (new Coordinate(1,1),new Coordinate(1,2));
		game.newAttempt(moveR);
		game.Move();
		
		WOG redPawn=game.getBoard().getAGrid(new Coordinate(1,2)).getEleOnGrid();
		assertEquals(game.getGameTurn(),PlayerIE.black);
		assertEquals(PlayerIE.red, redPawn.getPlayer());
		
		
		//move black
		Action moveB = new Action (new Coordinate(1,6),new Coordinate(1,5));
		game.newAttempt(moveB);
		game.Move();
		
		WOG blackPawn=game.getBoard().getAGrid(new Coordinate(1,5)).getEleOnGrid();
		assertEquals(game.getGameTurn(),PlayerIE.red);
		assertEquals(PlayerIE.black, blackPawn.getPlayer());
		
		
		
	}
	
	
	//both red and black can advance two when a certain pawn walk the first time
	public void advanceTwo(){
		Game game = new Game();
		
		//red advance two
		Action moveR = new Action (new Coordinate(1,1),new Coordinate(1,3));
		game.newAttempt(moveR);
		game.Move();
		
		WOG redPawn = game.getBoard().getAGrid(new Coordinate(1,3)).getEleOnGrid();
		assertEquals(game.getGameTurn(),PlayerIE.black);
		assertEquals(PlayerIE.red, redPawn.getPlayer());
		
		//black advance two
		Action moveB = new Action (new Coordinate(7,6),new Coordinate(7,4));
		game.newAttempt(moveB);
		game.Move();
		WOG blackPawn = game.getBoard().getAGrid(new Coordinate(7,4)).getEleOnGrid();
		assertEquals(game.getGameTurn(),PlayerIE.red);
		assertEquals(PlayerIE.black, blackPawn.getPlayer());
		
		//fail to advance two at the second time
		moveR=new Action (new Coordinate(1,3),new Coordinate(1,5));
		game.newAttempt(moveR);
		game.Move();
		assertEquals(game.getGameTurn(),PlayerIE.red);
		
		//advance another red pawn 2 grid
		moveR=new Action (new Coordinate(2,1),new Coordinate(2,3));
		game.newAttempt(moveR);
		game.Move();
		assertEquals(game.getGameTurn(),PlayerIE.black);
		
	}
	
	//capture diagonally
	//can be blocked when a piece of chess is right in the front
	public void captureAndBlock(){
		Game game = new Game();
		
		//red advance two
		Action moveR = new Action (new Coordinate(1,1),new Coordinate(1,3));
		game.newAttempt(moveR);
		game.Move();
		
		
		//black advance two to the opposite
		Action moveB = new Action (new Coordinate(1,6),new Coordinate(1,4));
		game.newAttempt(moveB);
		game.Move();
		
		
		// red fail to advance one anymore
		moveR = new Action (new Coordinate(1,3),new Coordinate(1,4));
		game.newAttempt(moveR);
		game.Move();
		assertEquals(game.getGameTurn(),PlayerIE.red);
		
		//advance closely-right red pawn 2 grid
		moveR = new Action (new Coordinate(2,1),new Coordinate(2,3));
		game.newAttempt(moveR);
		game.Move();
		assertEquals(game.getGameTurn(),PlayerIE.black);
		
		//black capture red
		moveB = new Action (new Coordinate(1,4),new Coordinate(2,3));
		game.newAttempt(moveB);
		game.Move();
		assertEquals(game.getGameTurn(),PlayerIE.red);
		WOG blackPawn = game.getBoard().getAGrid(new Coordinate(2,3)).getEleOnGrid();
		assertEquals(blackPawn.getPlayer(),PlayerIE.black);
		
		
	}
	
	//without a enemy, it cannot go diagonally
	public void walkDiagonally(){
		Game game = new Game();
		
		//red advance two
		Action moveR = new Action (new Coordinate(1,1),new Coordinate(2,2));
		game.newAttempt(moveR);
		game.Move();
		
		
		WOG target = game.getBoard().getAGrid(new Coordinate(2,2)).getEleOnGrid();
		assertEquals(game.getGameTurn(),PlayerIE.red);
		assertEquals(null,target);
		
	}
	
	
	
	
}
