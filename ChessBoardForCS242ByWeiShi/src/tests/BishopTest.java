package tests;






import static org.junit.Assert.*;
import org.junit.Test;


import helpers.*;
import chessboardDesign.*;
import playChess.*;
/*
 *What happens when a player tries to move a piece to an empty space on the board?
 *What happens when a player tries to move a piece to an invalid space (off the board)?
 *What happens when a player captures another piece? Does the captured piece disappear?
 *What happens when a player tries to move to a space already containing one of his/her pieces?
 *What happens when the player's king is put in "check"?
 *Which player moves first?
 * 
 * 
 */
public class BishopTest {

		
	/*
	 * As function name:
	 * check both destination and departure conditions
	 * 	
	 */
	
	@Test
	public void moveToBlankArea() {
		Game game = new Game();
		game.getBoard().getAGrid(new Coordinate(4,4)).setWOG(PlayerIE.red, ChessType.bishop);
		Action move = new Action (new Coordinate(4,4),new Coordinate(5,5));
		game.newAttempt(move);
		game.Move();
		ChessType type = game.getBoard().getAGrid(new Coordinate(5,5)).getEleOnGrid().getType();
		PlayerIE player = game.getBoard().getAGrid(new Coordinate(5,5)).getEleOnGrid().getPlayer();
		WOG remain = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid();
		assertEquals(ChessType.bishop,type);
		assertEquals(PlayerIE.red,player);
		assertEquals(null,remain);
		
	}
	
	//capture a blocked piece and a unblocked piece
	@Test
	public void capture() {
		Game game = new Game();
		game.getBoard().getAGrid(new Coordinate(4,4)).setWOG(PlayerIE.red, ChessType.bishop);
		
		
		//move to (7,7) --blocked by black pawn at (6,6)--fail
		
		WOG beforeMove = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid();
		
		Action move = new Action (new Coordinate(4,4),new Coordinate(7,7));
		game.newAttempt(move);
		game.Move();
		
		ChessType type = game.getBoard().getAGrid(new Coordinate(7,7)).getEleOnGrid().getType();
		PlayerIE player = game.getBoard().getAGrid(new Coordinate(7,7)).getEleOnGrid().getPlayer();
		WOG remain = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid();
		
		assertEquals(ChessType.rook,type);
		assertEquals(PlayerIE.black,player);
		assertEquals(beforeMove,remain);
		
		
		
		//move to (2,6) instead?
		//should success
		
		move = new Action (new Coordinate(4,4),new Coordinate(2,6));
		game.newAttempt(move);
		game.Move();
		
		 type = game.getBoard().getAGrid(new Coordinate(2,6)).getEleOnGrid().getType();
		 player = game.getBoard().getAGrid(new Coordinate(2,6)).getEleOnGrid().getPlayer();
		 remain = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid();
		 
		assertEquals(ChessType.bishop,type);
		assertEquals(PlayerIE.red,player);
		assertEquals(null,remain);
		
		
	}
	
	//move from a null grid, move outside the bound or "capture" a mate
	@Test
	public void invalidPlaces() throws Exception {
		Game game = new Game();
		game.getBoard().getAGrid(new Coordinate(4,4)).setWOG(PlayerIE.red, ChessType.bishop);
		
		
		//move to (8,8) 
		
		WOG beforeMove = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid();
		
		Action move = new Action (new Coordinate(4,4),new Coordinate(8,8));
		game.newAttempt(move);
		game.Move();
		
		
		WOG remain = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid();
		
		assertEquals(game.getGameTurn(),PlayerIE.red);
		assertEquals(beforeMove,remain);
		
		//move from (5,5) to (4,4)
		
		Action badmove = new Action (new Coordinate(5,5),new Coordinate(6,6));
		game.newAttempt(badmove);
		game.Move();
		
		
		WOG remain2 = game.getBoard().getAGrid(new Coordinate(5,5)).getEleOnGrid();
		
		assertEquals(game.getGameTurn(),PlayerIE.red);
		assertEquals(null,remain2);
	
		
		//move from (5,5) to (1,1)
		
		Action killmate = new Action (new Coordinate(5,5),new Coordinate(1,1));
		game.newAttempt(killmate);
		game.Move();
		
		
		WOG remain3 = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid();
		WOG target = game.getBoard().getAGrid(new Coordinate(1,1)).getEleOnGrid();
		assertEquals(game.getGameTurn(),PlayerIE.red);
		assertEquals(ChessType.bishop,remain3.getType());
		assertEquals(target.getType(),ChessType.pawn);
		
		
		
	}
	
	//try to move twice at a time?
	@Test
	public void moveTwice() {
		Game game = new Game();
		game.getBoard().getAGrid(new Coordinate(4,4)).setWOG(PlayerIE.red, ChessType.bishop);
		Action move = new Action (new Coordinate(4,4),new Coordinate(5,5));
		game.newAttempt(move);
		game.Move();
		
		move = new Action (new Coordinate(5,5),new Coordinate(6,6));
		game.newAttempt(move);
		game.Move();
		
		ChessType type = game.getBoard().getAGrid(new Coordinate(5,5)).getEleOnGrid().getType();
		PlayerIE player = game.getBoard().getAGrid(new Coordinate(5,5)).getEleOnGrid().getPlayer();
		WOG remain = game.getBoard().getAGrid(new Coordinate(4,4)).getEleOnGrid();
		WOG future = game.getBoard().getAGrid(new Coordinate(6,6)).getEleOnGrid();
		assertEquals(ChessType.bishop,type);
		assertEquals(PlayerIE.red,player);
		assertEquals(null,remain);
		assertEquals(ChessType.pawn,future.getType());
		
	}
	
	

}
