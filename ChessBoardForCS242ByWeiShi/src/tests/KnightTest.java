package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;

import helpers.*;
import chessboardDesign.*;
import playChess.*;
public class KnightTest {

		@Test
		public void jumpValid() {
			Game game = new Game();
			
			//move a red knight
			Action moveR = new Action (new Coordinate(1,0),new Coordinate(2,2));
			game.newAttempt(moveR);
			game.Move();
			
			PlayerIE player = game.getBoard().getAGrid(new Coordinate(2,2)).getEleOnGrid().getPlayer();
			ChessType type = game.getBoard().getAGrid(new Coordinate(2,2)).getEleOnGrid().getType();
			
			
			assertEquals(ChessType.knight,type);
			assertEquals(PlayerIE.red,player);
			assertEquals(game.getGameTurn(),PlayerIE.black);
			
			//try a black one in the opposite direction
			Action moveB = new Action (new Coordinate(1,7),new Coordinate(0,5));
			game.newAttempt(moveB);
			game.Move();
			
			assertEquals(game.getGameTurn(),PlayerIE.red);
			
			
		}
		
		//capture exercise for black knight
		@Test
		public void capture() {
			Game game = new Game();
			
			//red advance pawn
			Action moveR = new Action (new Coordinate(3,1),new Coordinate(3,2));
			game.newAttempt(moveR);
			game.Move();
			
			//black do random stuff
			Action moveB = new Action (new Coordinate(6,6),new Coordinate(6,5));
			game.newAttempt(moveB);
			game.Move();
			
			
			//move a red bishop
			moveR = new Action (new Coordinate(2,0),new Coordinate(7,5));
			game.newAttempt(moveR);
			game.Move();
			
			
			
			//black knight capture red bishop
			moveB = new Action (new Coordinate(6,7),new Coordinate(7,5));
			game.newAttempt(moveB);
			game.Move();
			
			
			PlayerIE player = game.getBoard().getAGrid(new Coordinate(7,5)).getEleOnGrid().getPlayer();
			ChessType type = game.getBoard().getAGrid(new Coordinate(7,5)).getEleOnGrid().getType();
			assertEquals(game.getGameTurn(),PlayerIE.red);
			assertEquals(ChessType.knight,type);
			assertEquals(PlayerIE.black,player);
			
			
		}


}
