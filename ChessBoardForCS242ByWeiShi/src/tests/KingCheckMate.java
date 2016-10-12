package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import helpers.*;
import chessboardDesign.*;
import playChess.*;



public class KingCheckMate {

	/**
	 * A long story describe how the red king is checked but haven't been check mate.
	 */
	@Test
	public void anotherstory() {
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
		
		// black queen move back
		moveB = new Action (new Coordinate(3,4),new Coordinate(2,5));
		game.newAttempt(moveB);
		game.Move();
		
		//red king advance
		moveR = new Action (new Coordinate(4,0),new Coordinate(4,1));
		game.newAttempt(moveR);
		game.Move();
		assertEquals(game.getBoard().getAGrid(new Coordinate(4,1)).getEleOnGrid().getType(),ChessType.king);
		
		
		
		// black queen kill the red pawn and check the red king
		moveB = new Action (new Coordinate(2,5),new Coordinate(4,3));
		game.newAttempt(moveB);
		game.Move();
		assertEquals(game.getBoard().getAGrid(new Coordinate(4,1)).getEleOnGrid().getType(),ChessType.king);
		/*Black Queen: Check the red king! In the name of my husband!*/
		
		assertEquals(game.kingHasNoGuard(game.kingRCoor,game.aliveBlack,game.aliveRed),true);
		assertEquals(game.getGameTurn(), PlayerIE.red);
		assertEquals(game.isKingCheckMate(PlayerIE.red, new Coordinate(4,1)),true);
		
		/*Red King: Am i going to die*/
		/*Black Queen: Yes! Definitely!*/
		//So:
		
		
		
		
		
		
		
	}

}
