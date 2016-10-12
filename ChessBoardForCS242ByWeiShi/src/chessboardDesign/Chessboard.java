package chessboardDesign;
import helpers.*;
import playChess.*;
public class Chessboard {
	
	private int boardX, boardY;
	private Grid [][]board;
	
	
	//extend??board size can only be initialized in constructor.
	
	public int getBoardX(){
		return boardX;
		
	}
	
	public int getBoardY(){
		return boardY;
		
	}
	public Grid[][] getBoard() {
		return board;
	}
	
	/*get a grid at certain coordinate object from a board
	 * @param coorG :  coordinate of Grid object
	 * @return the Grid object required
	 */
	public Grid getAGrid(Coordinate coorG)
	{
		return board[coorG.getX()][coorG.getY()];
	}
	
	// it's only called in 8*8 board constructor, so it's safe to assume 8*8 here!
	private void boardIni()
	{
		
		for(PlayerIE player : PlayerIE.values())
		{
			
			//initial the empty board
			if(player == PlayerIE.white)
			{	
				//Grids where no piece is present initially
				for(int i = 2; i < 6; i++)
					for(int j = 0; j < 8; j++)
						board[j][i] = new Grid(new Coordinate(j,i));
			}
			else
			{	//initial the red and black player's pieces of chess
				//red Y={0,1} 
				//black Y={6,7}
				
				
				//pawns
				for(int i=0; i<8;i++)
					board[i][player == PlayerIE.red ? 1 : 6] = new Grid(player == PlayerIE.red ?new Coordinate (i, 1):new Coordinate (i, 6), player, ChessType.pawn);
					
				
				
					//other types
				board[0][player == PlayerIE.red ? 0 : 7] = new Grid (player == PlayerIE.red ? new Coordinate (0, 0):new Coordinate (0, 7),player, ChessType.rook);
				board[7][player == PlayerIE.red ? 0 : 7] = new Grid (player == PlayerIE.red ? new Coordinate (7, 0):new Coordinate (7, 7),player, ChessType.rook);
				board[1][player == PlayerIE.red ? 0 : 7] = new Grid (player == PlayerIE.red ? new Coordinate (6, 0):new Coordinate (6, 7),player, ChessType.knight);
				board[6][player == PlayerIE.red ? 0 : 7] = new Grid (player == PlayerIE.red ? new Coordinate (1, 0):new Coordinate (1, 7),player, ChessType.knight);
				board[2][player == PlayerIE.red ? 0 : 7] = new Grid (player == PlayerIE.red ? new Coordinate (2, 0):new Coordinate (2, 7),player, ChessType.bishop);
				board[5][player == PlayerIE.red ? 0 : 7] = new Grid (player == PlayerIE.red ? new Coordinate (5, 0):new Coordinate (5, 7),player, ChessType.bishop);
				board[3][player == PlayerIE.red ? 0 : 7] = new Grid (player == PlayerIE.red ? new Coordinate (3, 0):new Coordinate (3, 7),player, ChessType.queen);
				board[4][player == PlayerIE.red ? 0 : 7] = new Grid (player == PlayerIE.red ? new Coordinate (4, 0):new Coordinate (4, 7),player, ChessType.king);
			}
		}
	}
	
	//initialize a normal 8*8 board
	public Chessboard()
	{
		this.boardX	= 8;
		this.boardY = 8;
		this.board = new Grid[8][8];
		boardIni();
	}
	
	
	/*
	 * another constructor
	 * which is a deep copy of another board 
	 */
	public Chessboard(Chessboard origin)
	{
		this.board = new Grid[origin.boardX][origin.boardY];
		this.boardX = origin.boardX;
		this.boardY = origin.boardY;
		
		for(int i = 0; i < origin.boardX; i++)
			for(int j = 0; j < origin.boardY; j++)
				this.board[i][j] = new Grid(origin.getAGrid(new Coordinate(i,j)));
					

		
	}
	
	
	public void undoOne(Action undoAction) {
		Grid GridInitiallyAtTo = board[undoAction.getDepartX()][undoAction.getDepartY()];
		Grid GridInititallyAtFrom = board[undoAction.getDestX()][undoAction.getDestY()];
		
		board[undoAction.getDepartX()][undoAction.getDepartY()] = GridInititallyAtFrom;
		board[undoAction.getDepartX()][undoAction.getDepartY()].setCoor(new Coordinate(undoAction.getDepartX(), undoAction.getDepartY()));
		
		if(undoAction.getCapType() == ChessType.nothing) {
			board[undoAction.getDestX()][undoAction.getDestY()] = GridInitiallyAtTo;
			board[undoAction.getDestX()][undoAction.getDestY()].setCoor(new Coordinate(undoAction.getDestX(), undoAction.getDestY()));
		}
		else {
			board[undoAction.getDestX()][undoAction.getDestY()] = new Grid (new Coordinate(undoAction.getDestX(),undoAction.getDestY()), undoAction.getCapColor(), undoAction.getCapType());
		}			
	}
	
	
	
	public boolean moveAChess(Action move)
	{
		
		
		int departX = move.getDepartX();
		int departY = move.getDepartY();
		int destX = move.getDestX();
		int destY = move.getDestY();
		Grid departG = board[departX][departY];
		Grid destG = board[destX][destY];
		
		
		boolean capture = true;
		
		//replace the destinationGrid with the departureGrid, the only difference is coordinate:
		board[destX][destY] = departG;
		board[destX][destY].setCoor(move.getDestCoor());
		
		
		//target is empty:just switch the target grid with departure grid
		if(destG.getEleOnGrid() == null)	{
			capture = false;
			board[departX][departY] = destG;
			board[departX][departY].setCoor(move.getDepartCoor());
		
			
		}
		else	{
			
			//capture a chess, then initialize a new empty grid in departure grid
			board[departX][departY] = new Grid(move.getDepartCoor());
			move.setCapColor(destG.getEleOnGrid().getPlayer());
			move.setCapType(destG.getEleOnGrid().getType());
		}
			
			
		
		
		
		
		return capture;
	}
	
	
	
}
