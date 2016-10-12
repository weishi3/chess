package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import helpers.*;
import chessboardDesign.*;
import playChess.*;





public class KingInCheck {

	
	/**
	 * A long story describe how the red king is checked but haven't been check mate.
	 */
	@Test
	public void story() {
		Game game = new Game();
		
		//advance red pawn
		Action moveR = new Action (new Coordinate(2,1),new Coordinate(2,3));
		game.newAttempt(moveR);
		game.Move();
		
		
		
		//advance black pawn
		Action moveB = new Action (new Coordinate(3,6),new Coordinate(3,4));
		game.newAttempt(moveB);
		game.Move();
		
		//red pawn kill black pawn
		moveR = new Action (new Coordinate(2,3),new Coordinate(3,4));
		game.newAttempt(moveR);
		game.Move();
		
		
		//black queen kill red pawn
		moveB = new Action (new Coordinate(3,7),new Coordinate(3,4));
		game.newAttempt(moveB);
		game.Move();
		
		//red pawn advance
		moveR = new Action (new Coordinate(4,1),new Coordinate(4,3));
		game.newAttempt(moveR);
		game.Move();
		
		
		// black queen kill the red pawn and check the red king
		moveB = new Action (new Coordinate(3,4),new Coordinate(4,3));
		game.newAttempt(moveB);
		game.Move();
		
		assertEquals(game.getGameTurn(),PlayerIE.red);
		/*Black Queen: Check the red king! In the name of my husband!*/
		
		
		//the fool king try to move one grid, but..
		moveR = new Action (new Coordinate(4,0),new Coordinate(4,1));
		game.newAttempt(moveR);
		game.Move();
		
		WOG temp=game.getBoard().getAGrid(new Coordinate(4,0)).getEleOnGrid();
		
		
		temp=game.getBoard().getAGrid(new Coordinate(4,1)).getEleOnGrid();
		
		assertEquals(game.getGameTurn(),PlayerIE.red);
		
		/*Red King: Am i going to die*/
		//But black haven't win!
		assertEquals(game.getWinner(),PlayerIE.white);
		
		
		/*bishop:I am ready to sacrifice*/
		moveR = new Action (new Coordinate(5,0),new Coordinate(4,1));
		game.newAttempt(moveR);
		game.Move();
		assertEquals(game.getGameTurn(),PlayerIE.black);
		
		/*king: No,you want. Since now, I am protecting you!*/
		
		
	}

}
