package tests;

import static org.junit.Assert.*;
import helpers.*;
import chessboardDesign.*;
import playChess.*;

import org.junit.Test;




public class KingTest {

	@Test
	public void moveToBlankArea() {
		Game game = new Game();
		
		//position occupied by pawn in the same team
		Action moveR = new Action (new Coordinate(4,0),new Coordinate(4,1));
		game.newAttempt(moveR);
		game.Move();
		assertEquals(game.getGameTurn(),PlayerIE.red);
		
		
		//advance the pawn
		moveR = new Action (new Coordinate(4,1),new Coordinate(4,3));
		game.newAttempt(moveR);
		game.Move();
		
		//black does random stuff
		Action moveB = new Action (new Coordinate(4,6),new Coordinate(4,4));
		game.newAttempt(moveB);
		game.Move();
		assertEquals(game.getGameTurn(),PlayerIE.red);
		
		
		//king try to move two grid 
		moveR = new Action (new Coordinate(4,0),new Coordinate(4,2));
		game.newAttempt(moveR);
		game.Move();
		assertEquals(game.getGameTurn(),PlayerIE.red);
		
		//king has to move one grid
		moveR = new Action (new Coordinate(4,0),new Coordinate(4,1));
		game.newAttempt(moveR);
		game.Move();
		
		WOG target = game.getBoard().getAGrid(new Coordinate(4,1)).getEleOnGrid();
		WOG depart = game.getBoard().getAGrid(new Coordinate(4,0)).getEleOnGrid();
		assertEquals(depart,null);
		assertEquals(target.getType(),ChessType.king);
		
		
		
	}
	
	
	
	
	
	
	

}
